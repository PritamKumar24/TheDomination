package com.thedomination.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.thedomination.controller.CardOperations;
import com.thedomination.controller.MapOperations;
import com.thedomination.controller.PlayerOperations;
import com.thedomination.view.DominationCardView;
import com.thedomination.view.DominationPhaseView;
import com.thedomination.view.WorldDominationView;

/**
 * The Class GameModel.
 *
 * @author Aditi
 * @author Pritam Kumar
 * @version 1.0.0
 */
public class GameModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private MapOperations mapOperations;

	//0=startup 1=Reinforcement 2=Attack 3=Fortification
	private int gamePhaseStage = 0;

	public ArrayList<String> lostPlayers;

	private ArrayList<PlayerModel> playerList;

	private ArrayList<CardsModel> cardDeck;

	private  int playerCounter;

	private int cardCounter;

	private PlayerModel currentPlayer;

	//All flags
	/**The attackFlag */
	public boolean attackFlag = false;

	/**The defendFlag*/
	public boolean defendFlag = false;

	/**The reinforceArmyFlag */
	public boolean reinforceArmyFlag = false;

	/**The  fortifyArmyFlag */
	public boolean fortifyArmyFlag = false;

	/**The moveArmyFlag */
	public boolean moveArmyFlag =false;

	/**The reinforceFlag  */
	public boolean reinforceFlag = true;

	/**The placeArmyFlag*/
	public boolean placeArmyFlag = false;

	public boolean populateFlag=false;

	public boolean cardExchangeFlag = false;

	public static GameModel UniqueInstance;

	private DominationPhase dominationPhase;
	private WorldDomination worldDomination;
	private WorldDominationView worldDominationView;
	private DominationPhaseView dominationPhaseView;
	private DominationCards dominationCard;
	private DominationCardView dominationCardView;

	private GameModel() {
		this.mapOperations = mapOperations;
		playerList = new ArrayList<PlayerModel>();
		lostPlayers = new ArrayList<String>();
		cardDeck = new ArrayList<CardsModel>();
		
		dominationPhase=new DominationPhase();
		worldDomination=new WorldDomination();
		worldDominationView=new WorldDominationView(worldDomination);
		dominationPhaseView=new DominationPhaseView(dominationPhase);
		dominationCard = new DominationCards();
		dominationCardView = new DominationCardView(dominationCard);
	}

	private GameModel(MapOperations mapOperations) {
		this.mapOperations = mapOperations;
		playerList = new ArrayList<PlayerModel>();
	}

	public static GameModel getInstance() {
		if(GameModel.UniqueInstance == null) {
			GameModel.UniqueInstance = new GameModel();
		}
		return GameModel.UniqueInstance;
	}

	public String reStartGame(GameModel gameModel) {
		String message = "";
		if (gameModel == null) {
			message = "Doesn't have anything saved";
			return message;
		}

		if(gameModel.getGamePhaseStage() == 0) {
			dominationPhase.setCurrentGamePhase(DominationPhaseType.STARTUP);
			dominationPhase.setCurrentPlayerName("All Players");
			dominationPhase.setCurrentAction("Startup Phase");
			PlayerOperations.getInstance().GameEngine();
		}

		else if(gameModel.getGamePhaseStage() == 1) {
			if(gameModel.cardExchangeFlag == true) {
				dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
				dominationPhase.setCurrentPlayerName(gameModel.currentPlayer.getPlayerName());
				dominationPhase.setCurrentAction("Starting Card Exchange");
				
				dominationCard.setPlayerName(gameModel.currentPlayer.getPlayerName());
				dominationCard.setListCards(CardOperations.getInstance().cardStrings(gameModel.currentPlayer.getCardList()));
				PlayerOperations.getInstance().GameEngine();
			}
			else {
				dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
				dominationPhase.setCurrentPlayerName(gameModel.currentPlayer.getPlayerName());
				dominationPhase.setCurrentAction("Staring Reinforcement");
				PlayerOperations.getInstance().GameEngine();
			}
		}

		else if(gameModel.getGamePhaseStage() == 2) {
			dominationPhase.setCurrentGamePhase(DominationPhaseType.ATTACK);
			dominationPhase.setCurrentPlayerName(gameModel.currentPlayer.getPlayerName());
			dominationPhase.setCurrentAction("Starting Attack");
			PlayerOperations.getInstance().GameEngine();
		}

		else if(gameModel.getGamePhaseStage() == 3) {
			dominationPhase.setCurrentGamePhase(DominationPhaseType.FORTIFY);
			//dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
			dominationPhase.setCurrentPlayerName(gameModel.currentPlayer.getPlayerName());
			dominationPhase.setCurrentAction("Starting Fortify");
			PlayerOperations.getInstance().GameEngine();
		}

		return "";
	}

	public void addPlayer(PlayerModel player) {
		this.playerList.add(player);
	}

	public void removePlayer(PlayerModel player) {
		this.playerList.remove(player);
	}

	public MapOperations getMapOperations() {
		return mapOperations;
	}

	public void setMapOperations(MapOperations mapOperations) {
		this.mapOperations = mapOperations;
	}

	public ArrayList<PlayerModel> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<PlayerModel> playerList) {
		this.playerList = playerList;
	}

	public int getGamePhaseStage() {
		return gamePhaseStage;
	}

	public void setGamePhaseStage(int gamePhaseStage) {
		this.gamePhaseStage = gamePhaseStage;
	}


	public int getPlayerCounter() {
		return playerCounter;
	}

	public void setPlayerCounter(int playerCounter) {
		this.playerCounter = playerCounter;
	}

	public PlayerModel getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(PlayerModel currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public ArrayList<String> getLostPlayers() {
		return lostPlayers;
	}

	public void setLostPlayers(ArrayList<String> lostPlayers) {
		this.lostPlayers = lostPlayers;
	}

	public ArrayList<CardsModel> getCardDeck() {
		return cardDeck;
	}

	public void setCardDeck(ArrayList<CardsModel> cardDeck) {
		this.cardDeck = cardDeck;
	}

	public int getCardCounter() {
		return cardCounter;
	}

	public void setCardCounter(int cardCounter) {
		this.cardCounter = cardCounter;
	}

	@Override
	public String toString() {
		return "GameModel [mapOperations=" + mapOperations + ", playerList=" + playerList + "]";
	}


}
