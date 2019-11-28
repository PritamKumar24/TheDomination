package com.thedomination.builder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.thedomination.controller.CardOperations;
import com.thedomination.controller.MapOperations;
import com.thedomination.controller.PlayerOperations;
import com.thedomination.model.CardsModel;
import com.thedomination.model.ContinentModel;
import com.thedomination.model.CountryModel;
import com.thedomination.model.PlayerModel;

/**
 * The GameBuilder Interface extends Serializable.
 * 
 * @author Pritam Kumar
 *
 */

public class GameDirector implements Serializable{

	/**
	 * The constant serialVersionUID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/** The gameBuilder */
	private GameBuilder gameBuilder;

	/**
	 * setGameBuilder setter method to set Game Builder.
	 * 
	 * @param gameBuilder of GameBuilder type.
	 */
	public void setGameBuilder(GameBuilder gameBuilder) {
		this.gameBuilder = gameBuilder;
	}

	/**
	 * The buildGame method to combine all the operations at one place.
	 */
	public void buildGame() {
		gameBuilder.buildCardOperations();
		gameBuilder.buildMapOperations();
		gameBuilder.buildPlayerOperations();
		//gameBuilder.reStartGame(gameBuilder);
	}

	/**
	 * The getGameBuilder getter method to get gameBuilder.
	 * 
	 * @return GameBuilder.
	 */
	public GameBuilder getGameBuilder() {
		return gameBuilder;
	}


	/**
	 * The saveGame method to save game.
	 * 
	 * @param fileName1 name of file to be saved.
	 * @return boolean either true or false.
	 */
	public boolean saveGame(String fileName1) {
		boolean isGameSaved = false;
		ObjectOutputStream output = null;
		try {
			String fileName = fileName1;
			output = new ObjectOutputStream(new FileOutputStream("./save/" + fileName));
			output.writeObject(gameBuilder);
			isGameSaved = true;

			System.out.println("Game has been successfully saved to >>" + fileName + "<< file");
		}

		catch (NotSerializableException exception) {
			System.out.println("exception");
			exception.printStackTrace();
		}

		catch (IOException e) {
			isGameSaved = false;
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return isGameSaved;
	}

	/**
	 * The loadGameModel to load the game.
	 * 
	 * @param inputFile file to be loaded.
	 * @return GameBuilder.
	 */
	public String loadGameModel(String inputFile) {
		String message = "";
		ConcreteGameBuilder concreteGameBuilder = null;
		ObjectInputStream input = null;


		try {
			if (inputFile.trim().isEmpty()) {
				message= "Invalid File Name";
				return message;
			} else {

				input = new ObjectInputStream(
						new FileInputStream(System.getProperty("user.dir") + "/save/" + inputFile));

				concreteGameBuilder = (ConcreteGameBuilder) input.readObject();

				MapOperations mapOperations = concreteGameBuilder.getMapOperations();
				PlayerOperations playerOperations = concreteGameBuilder.getPlayerOperations();
				CardOperations cardOperations = concreteGameBuilder.getCardOperations();

				//get the data
				ArrayList<CountryModel> countries = mapOperations.getCountryList();
				ArrayList<ContinentModel> countinents = mapOperations.getContinentsList();
				ArrayList<ArrayList<String>> listOfConnectedNodes = mapOperations.getListOfConnectedNodes();
				ArrayList<PlayerModel> players = playerOperations.getPlayersList();
				ArrayList<String> lostPlayers = playerOperations.getLostPlayers();
				ArrayList<CardsModel> cardDeck = cardOperations.getCardDeck();

				//set the data
				MapOperations.getInstance().setCountryList(countries);
				MapOperations.getInstance().setContinentsList(countinents);
				MapOperations.getInstance().setListOfConnectedNodes(listOfConnectedNodes);					
				PlayerOperations.getInstance().setPlayerModelList(players);
				PlayerOperations.getInstance().setLostPlayersList(lostPlayers);
				PlayerOperations.getInstance().setPlayerCounter(playerOperations.getPlayerCounter());
				PlayerModel currentPlayer = playerOperations.getInstance().currentPlayer(playerOperations.getPlayerCounter());

				CardOperations.getInstance().setCardDeck(cardDeck);
				CardOperations.getInstance().setCardCounter(cardOperations.getCardCounter());

				//All flags
				CardOperations.getInstance().setCardExchangeFlag(cardOperations.cardExchangeFlag);
				PlayerOperations.getInstance().setReinforceFlag(playerOperations.reinforceArmyFlag);
				PlayerOperations.getInstance().setReinforceArmyFlag(playerOperations.reinforceArmyFlag);
				PlayerOperations.getInstance().setAttackFlag(playerOperations.attackFlag);
				PlayerOperations.getInstance().setFortifyArmyFlag(playerOperations.fortifyArmyFlag);
				PlayerOperations.getInstance().setReinforceArmyFlag(playerOperations.reinforceArmyFlag);
				PlayerOperations.getInstance().setAttackFlag(playerOperations.attackFlag);
				PlayerOperations.getInstance().setFortifyArmyFlag(playerOperations.fortifyArmyFlag);

				System.out.println("File has been loaded");

				gameBuilder.reStartGame((ConcreteGameBuilder) concreteGameBuilder);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		//return gameBuilder;
		return "";
	}
}
