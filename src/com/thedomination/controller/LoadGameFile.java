package com.thedomination.controller;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.security.sasl.AuthorizeCallback;

import com.thedomination.model.CardsModel;
import com.thedomination.model.ContinentModel;
import com.thedomination.model.CountryModel;
import com.thedomination.model.GameModel;
import com.thedomination.model.HumanPlayer;
import com.thedomination.model.PlayerModel;

/**
 * The Class LoadGameWindow.
 * @author Pritam Kumar
 */
public class LoadGameFile {
	/**
	 * Method to load game from disk.
	 *
	 * @param inputFile The file that need to be loaded
	 * @return gameModel
	 */
	public GameModel loadGameModel(String inputFile) {
		GameModel gameModel = null;
		ObjectInputStream input = null;

	
		try {
			if (inputFile.trim().isEmpty()) {
				System.out.println("File name invalid");
			} else {

				input = new ObjectInputStream(
						new FileInputStream(System.getProperty("user.dir") + "/save/" + inputFile));
				
				gameModel = (GameModel) input.readObject();
				MapOperations mapOperations = gameModel.getMapOperations();
				
				//get the data
				ArrayList<CountryModel> countries = mapOperations.getCountryList();
				ArrayList<ContinentModel> countinents = mapOperations.getContinentsList();
				ArrayList<PlayerModel> players = gameModel.getPlayerList();
				ArrayList<String> lostPlayers = gameModel.getLostPlayers();
				ArrayList<CardsModel> cardDeck = gameModel.getCardDeck();
				
				//set the data
				MapOperations.getInstance().setCountryList(countries);
				MapOperations.getInstance().setContinentsList(countinents);
				
				
				PlayerOperations.getInstance().setPlayerModelList(players);
				PlayerOperations.getInstance().setLostPlayersList(lostPlayers);
				PlayerOperations.getInstance().setPlayerCounter(gameModel.getPlayerCounter());
				PlayerModel currentPlayer = gameModel.getCurrentPlayer();
							
				CardOperations.getInstance().setCardDeck(cardDeck);
				CardOperations.getInstance().setCardCounter(gameModel.getCardCounter());
				
				//All flags
				CardOperations.getInstance().setCardExchangeFlag(gameModel.cardExchangeFlag);
				PlayerOperations.getInstance().setReinforceFlag(gameModel.reinforceFlag);
				
				//check Strategy for flags
				if(currentPlayer.getStrategy() instanceof HumanPlayer) {
					HumanPlayer humanPlayer = (HumanPlayer) currentPlayer.getStrategy();
					
					humanPlayer.setReinforceArmyFlag(gameModel.reinforceArmyFlag);
					humanPlayer.setAttackFlag(gameModel.attackFlag);
					humanPlayer.setFortifyArmyFlag(gameModel.fortifyArmyFlag);
					
				}
				
				GameModel.getInstance().setCurrentPlayer(currentPlayer);
				GameModel.getInstance().reStartGame(gameModel);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return gameModel;
	}

}
