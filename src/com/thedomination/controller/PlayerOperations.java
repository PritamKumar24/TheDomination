package com.thedomination.controller;

import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.thedomination.model.Strategy;
import com.thedomination.builder.GameBuilder;
import com.thedomination.builder.GameDirector;
import com.thedomination.model.AggressivePlayer;
import com.thedomination.model.BenevolentPlayer;
import com.thedomination.model.CardsModel;
import com.thedomination.model.CheaterPlayer;
import com.thedomination.model.ContinentModel;
import com.thedomination.model.CountryModel;
import com.thedomination.model.PlayerModel;
import com.thedomination.model.RandomPlayer;
import com.thedomination.model.DominationCards;
import com.thedomination.model.DominationCardViewObservable;
import com.thedomination.model.WorldDomination;
import com.thedomination.utilities.ReadInputCommand;
import com.thedomination.model.DominationPhase;
import com.thedomination.model.DominationPhaseType;
import com.thedomination.model.HumanPlayer;
import com.thedomination.view.DominationCardObserver;
import com.thedomination.view.DominationCardView;
import com.thedomination.view.WorldDominationView;
import com.thedomination.view.DominationPhaseView;
/**
 * The Class PlayerModel Initializes ArrayList of PlayerModel type as playerModelList,
 * armiesToAssign of Integer type, placeArmyCounter of Integer type,fortifyCountryCounter of Integer type,
 * UniqueInstance object for PlayetrOperations class.
 * 
 * @author Aditi
 * @author Pritam Kumar
 * @author Ankur Singla
 * @version 1.0.0
 */
public class PlayerOperations implements Serializable{

	/**
	 * The constant  serialVersionUID value for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/** The playerModelList ArrayList of PlayerModel type */
	private ArrayList<PlayerModel> playerModelList;

	/** The ArrayList of lostPlayers */
	private ArrayList<String> lostPlayers;

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
	boolean placeArmyFlag = false;

	private boolean populateFlag=false;

	/** The countrynameto */
	public String countrynameto;

	/**The countrynamefrom */
	public String countrynamefrom;

	/** The dice for player's no of dices**/
	public int[] diceAttackArray;

	/**The diceDefendArray Array */
	private int[] diceDefendArray;

	/**The armies to Assign */
	private int  armiesToAssign = 0;

	/**Initializes the integer placeArmyCounter */
	public  int placeArmyCounter =1;

	/** The reInforceNoOfArmy */
	private int reInforceNoOfArmy;

	/** The Strategy Object */
	private Strategy strategy;

	/** The startup phase flag */
	private static String startup_phaseFlag="false";

	/** no of turns for tournament */
	public int noOfTurns=1;

	/**The attackCountryCounter */
	private  int playerCounter = 1;

	/** The variable a for number of armies */
	private int a=0;

	/** The gameBuilder's reference*/
	private GameBuilder gameBuilder;

	/** The gameDirector reference */
	private GameDirector gameDirector;

	/**
	 * isStartup_phaseFlag returns the value of Startup phase Flag.
	 * @return String
	 */
	public String isStartup_phaseFlag() {
		return startup_phaseFlag;
	}

	/**
	 * setStartup_phaseFlag setter method to set the Startup phase Flag.
	 * @param startup_phaseFlag of String type.
	 */
	public void setStartup_phaseFlag(String startup_phaseFlag) {
		PlayerOperations.startup_phaseFlag = startup_phaseFlag;
	}

	/**
	 * getLostPlayers getter method to get the List of lost players.
	 * 
	 * @return ArrayList lostPlayers.
	 */
	public ArrayList<String> getLostPlayers() {
		return lostPlayers;
	}

	/**
	 * setLostPlayersList setter method to set the List of lost players.
	 * 
	 * @param ArrayList lostPlayers.
	 */
	public void setLostPlayersList(ArrayList<String> lostPlayers) {
		this.lostPlayers = lostPlayers;
	}

	/**
	 * setLostPlayers setter method to set single Lost player.
	 * @param player String foe player's name
	 */
	public void setLostPlayers(String player) {
		this.lostPlayers.add(player);
	}


	/**
	 * getPlayerCounter getter method to get the counter value of player.
	 * 
	 * @return integer value.
	 */
	public int getPlayerCounter() {
		return playerCounter;
	}

	/**
	 * setPlayerCounter setter method to set the counter value of player.
	 * 
	 * @param integer value.
	 */
	public void setPlayerCounter(int playerCounter) {
		this.playerCounter = playerCounter;
	}

	/**
	 * getDiceAttackArray Method Getter Function to get Array of Attack dice.
	 * 
	 * @return array diceAttackArray.
	 */
	public int[] getDiceAttackArray() {
		return diceAttackArray;
	}
	/**
	 * setDiceAttackArray Method setter Function to set Array of Attack dice.
	 * 
	 * @param diceAttackArray Array of attacking dice.
	 */
	public void setDiceAttackArray(int[] diceAttackArray) {
		this.diceAttackArray = diceAttackArray;
	}

	/**
	 * getDiceDefendArray Method Getter Function to get Array of defend dice.
	 * 
	 * @return array diceDefendArray.
	 */

	public int[] getDiceDefendArray() {
		return diceDefendArray;
	}

	/**
	 * setDiceDefendArray Method setter Function to set Array of Defender dice.
	 * 
	 * @param diceAttackArray Array of Defender dice.
	 */
	public void setDiceDefendArray(int[] diceDefendArray) {
		this.diceDefendArray = diceDefendArray;
	}

	/**
	 * isReinforceArmyFlag checks flag value of reinforce flag.
	 * @return boolean
	 */
	public boolean isReinforceArmyFlag() {
		return reinforceArmyFlag;
	}

	/**
	 * setReinforceArmyFlag setter method to set the ReinforceArmyFlag.
	 * 
	 * @param reinforceArmyFlag boolean
	 */
	public void setReinforceArmyFlag(boolean reinforceArmyFlag) {
		this.reinforceArmyFlag = reinforceArmyFlag;
	}

	/**
	 * setAttackFlag setter method to set the ArmyFlag
	 * @param attackFlag boolean
	 */
	public void setAttackFlag(boolean attackFlag) {
		this.attackFlag = attackFlag;
	}

	/**
	 * setDefendFlag setter method to set the DefendFlag
	 * 
	 * @param defendFlag boolean.
	 */
	public void setDefendFlag(boolean defendFlag) {
		this.defendFlag = defendFlag;
	}

	/**
	 * setFortifyArmyFlag setter method to set the fortifyArmyFlag
	 * 
	 * @param fortifyArmyFlag boolean.
	 */
	public void setFortifyArmyFlag(boolean fortifyArmyFlag) {
		this.fortifyArmyFlag = fortifyArmyFlag;
	}

	/**
	 * setMoveArmyFlag setter method to set the moveArmyFlag
	 * 
	 * @param moveArmyFlag boolean.
	 */
	public void setMoveArmyFlag(boolean moveArmyFlag) {
		this.moveArmyFlag = moveArmyFlag;
	}

	/**
	 * getPlayersList to get list of players.
	 * @return playerModelList players list.
	 */
	public ArrayList<PlayerModel> getPlayersList() {
		return playerModelList;
	}

	/** setPlayerModelList sets playerModelList */
	public void setPlayerModelList(ArrayList<PlayerModel> playerModelList) {
		this.playerModelList = playerModelList;
	}

	/**
	 * getArmiesToAssign Method Getter Function to get armies to assign
	 *
	 * @return the armiesToAssign integer value of total number of armies.
	 */
	public int getArmiesToAssign() {
		return armiesToAssign;
	}

	/**
	 * setArmiesToAssign Method Setter Function to set armies of country.
	 *
	 * @param armiesToAssign the total number of armies .
	 */
	public void setArmiesToAssign(int armiesToAssign) {
		this.armiesToAssign = armiesToAssign;
	}

	/**
	 * setReinforceFlag setter method to set the reinforceFlag flag
	 * 
	 * @param reinforceFlag boolean 
	 */

	public void setReinforceFlag(boolean reinforceFlag) {
		this.reinforceFlag = reinforceFlag;
	}

	/**
	 * isReinforceFlag Method to check the State of flag.
	 * 
	 * @return reinforceFlag boolen true or false.
	 */
	public boolean isReinforceFlag() {
		return reinforceFlag;
	}
	/**
	 * getReInforceNoOfArmy Method Getter Function to get Reinforce number of Army.
	 * 
	 * @return reInforceNoOfArmy Integer number of Armies.
	 */
	public int getReInforceNoOfArmy() {
		return reInforceNoOfArmy;
	}
	/**
	 * setReInforceNoOfArmy Method Setter Function to set Reinforcing armies.
	 *
	 * @param reInforceNoOfArmy the total number of armies .
	 */
	public void setReInforceNoOfArmy(int reInforceNoOfArmy) {
		this.reInforceNoOfArmy = reInforceNoOfArmy;
	}

	/** The dominationPhase */
	private DominationPhase dominationPhase;
	/** The worldDomination */
	private WorldDomination worldDomination;
	/** The worldDominationView */
	private WorldDominationView worldDominationView;
	/** The dominationPhaseView */
	private DominationPhaseView dominationPhaseView;
	/** The dominationCard */
	private DominationCards dominationCard;
	/** The dominationCardView */
	private DominationCardView dominationCardView;

	/** The object for the player operation */
	private static PlayerOperations playerOperationInstance;

	/**
	 * Constructor for the PlayerOperation class.
	 * 
	 * @return object for the PlayerOperation class.
	 */
	public static PlayerOperations getInstance() {
		if(PlayerOperations.playerOperationInstance == null) {
			PlayerOperations.playerOperationInstance = new PlayerOperations();
		}
		return PlayerOperations.playerOperationInstance;
	}

	/** 
	 * Constructor for the PlayerOperation class.
	 */
	private PlayerOperations() {
		this.playerModelList = new ArrayList<>();
		this.lostPlayers = new ArrayList<String>();
		dominationPhase=new DominationPhase();
		worldDomination=new WorldDomination();
		worldDominationView=new WorldDominationView(worldDomination);
		dominationPhaseView=new DominationPhaseView(dominationPhase);
		dominationCard = new DominationCards();
		dominationCardView = new DominationCardView(dominationCard);

	}

	/**
	 * playerWorldDomination method for domination view.
	 */
	public void playerWorldDomination() {
		for(PlayerModel tempPlayer: PlayerOperations.getInstance().getPlayersList()) {
			HashSet<String> listOfContinent = new HashSet<String>();
			if(MapOperations.getInstance().continentConquered(tempPlayer)!=null) {
				listOfContinent.addAll(MapOperations.getInstance().continentConquered(tempPlayer));
			}
			worldDomination.setContinentsContr(listOfContinent);
			worldDomination.setPlayerName(tempPlayer.getPlayerName());
			worldDomination.setArmiesOwned(a);
			worldDomination.setPercentMapContr(tempPlayer.getPlayerCountryList().size() * 100.0/MapOperations.getInstance().getCountryList().size());
		}

	}

	/**
	 * playerWorldDominationpopulate method for domination view.
	 */
	public void playerWorldDominationpopulate() {
		for(PlayerModel tempPlayer: PlayerOperations.getInstance().getPlayersList()) {
			HashSet<String> listOfContinent = new HashSet<String>();
			if(MapOperations.getInstance().continentConquered(tempPlayer).size()>0) {
				listOfContinent.addAll(MapOperations.getInstance().continentConquered(tempPlayer));
			}
			worldDomination.setContinentsContr(listOfContinent);
			worldDomination.setPlayerName(tempPlayer.getPlayerName());
			for(int i=0;i<getPlayersList().size();i++) {

				worldDomination.setArmiesOwned(getPlayersList().get(i).getnoOfArmyInPlayer());
			}		
			worldDomination.setPercentMapContr(tempPlayer.getPlayerCountryList().size() * 100.0/MapOperations.getInstance().getCountryList().size());			
		}
	}

	/**
	 * playerWorldDominationplaceArmy method for domination view.
	 */
	public void playerWorldDominationplaceArmy() {
		for(PlayerModel tempPlayer: PlayerOperations.getInstance().getPlayersList()) {
			HashSet<String> listOfContinent = new HashSet<String>();
			if(MapOperations.getInstance().continentConquered(tempPlayer).size()>0) {
				listOfContinent.addAll(MapOperations.getInstance().continentConquered(tempPlayer));
			}
			worldDomination.setContinentsContr(listOfContinent);
			worldDomination.setPlayerName(tempPlayer.getPlayerName());
			worldDomination.setArmiesOwned(a-tempPlayer.getnoOfArmyInPlayer());
			worldDomination.setPercentMapContr(tempPlayer.getPlayerCountryList().size() * 100.0/MapOperations.getInstance().getCountryList().size());
		}
	}

	/**
	 * playerWorldDominationStateChange method for domination view.
	 * @param player for which to show world domination view
	 */
	public void playerWorldDominationStateChange(PlayerModel player) {
		HashSet<String> listOfContinent = new HashSet<String>();
		if(MapOperations.getInstance().continentConquered(player).size()>0) {
			listOfContinent.addAll(MapOperations.getInstance().continentConquered(player));
		}
		worldDomination.setContinentsContr(listOfContinent);
		worldDomination.setPlayerName(player.getPlayerName());
		worldDomination.setArmiesOwned(player.getPlayerArmiesInCountries());
		worldDomination.setPercentMapContr(player.getPlayerCountryList().size() * 100.0/MapOperations.getInstance().getCountryList().size());
	}

	/**
	 * currentPlayer method to show the current player making the moves.
	 * 
	 * @param counter player index
	 * @return PlayerModel Index of the player.
	 */

	public PlayerModel currentPlayer(int counter) {
		if(counter> PlayerOperations.getInstance().getPlayersList().size()) {
			playerCounter = 1;
			counter = 1;
			this.noOfTurns++;
		}
		return PlayerOperations.getInstance().getPlayersList().get(counter-1);
	}

	/**
	 * getPlayerStrategy getter method to get the Strategy .
	 * @param strategy String value.
	 * @return object of Strategy class.
	 */
	public Strategy getPlayerStrategy(String strategy) {
		if (strategy.equalsIgnoreCase("Human"))
			return new HumanPlayer();	
		else if (strategy.equalsIgnoreCase("Aggressive"))
			return new AggressivePlayer();
		else if (strategy.equalsIgnoreCase("Benevolent"))
			return new BenevolentPlayer();
		else if (strategy.equalsIgnoreCase("Random"))
			return new RandomPlayer();
		else if (strategy.equalsIgnoreCase("Cheater"))
			return new CheaterPlayer();
		return null;
	}

	/**
	 * addPlayer to add players to playerModelList. 
	 * @param playerName name of player to be added.
	 */
	public void  addPlayer(String playerName, String strategy) {
		if (playerName != null && !playerName.trim().isEmpty() && searchPlayer(playerName)==null ) {
			PlayerModel newPlayer = new PlayerModel(playerName);
			newPlayer.setStrategy(getPlayerStrategy(strategy));
			getPlayersList().add(newPlayer);
			System.out.println("Player name "+playerName+" added");
		}
		else {
			System.out.println("Player name "+playerName+"Already exists");
		}
	}

	/**
	 * searchPlayer to search player from playerModelList.
	 * 
	 * @param playerName name of player to be searched.
	 * @return loopPlayer name of player if player found else return null. 
	 */
	public PlayerModel searchPlayer(String playerName) {
		for (PlayerModel loopPlayer : playerModelList) {
			if (loopPlayer.getPlayerName().equalsIgnoreCase(playerName)) {
				return loopPlayer;
			}
		}
		return null;
	}

	/**
	 * removePlayer to remove player from playerModelList.
	 * 
	 * @param playerName name of player to be removed. 
	 */
	public void  removePlayer(String playerName) {
		if (playerName != null && !playerName.trim().isEmpty() && searchPlayer(playerName)==null ) {
			System.out.println("Player name "+playerName+" does not exist");
		}
		else{
			getPlayersList().remove(searchPlayer(playerName));
			System.out.println("Player name "+playerName+" deleted");
		}
	}
	/**
	 * populateCountries method to allocate a number of initial armies, depending on the number of players .
	 * 
	 */
	public void populateCountries() {
		System.out.println("Game Starting..");
		System.out.println();
		//Triggering phase view observer
		dominationPhase.setCurrentGamePhase(DominationPhaseType.STARTUP);
		dominationPhase.setCurrentPlayerName("All Players in the game");
		dominationPhase.setCurrentAction("Startup Phase started");

		populateFlag=true;
		int i=0;
		for(i=0;i<getPlayersList().size();i++) {
			System.out.println("Player: " + ((int)(i+1)) + " Player Name: " + playerModelList.get(i).getPlayerName());
		}
		i=0;
		for(CountryModel loopCountry: MapOperations.getInstance().getCountryList()) {
			if(i<getPlayersList().size()-1) {
				getPlayersList().get(i).AddCountry(loopCountry);
				i++;
			}
			else {
				getPlayersList().get(i).AddCountry(loopCountry);
				i=0;
			}
		}
		System.out.println("The countries have been populated");
		placeArmyFlag = true;
		switch (getPlayersList().size()) {
		case 2:
			for(i=0;i<getPlayersList().size();i++) {
				getPlayersList().get(i).setnoOfArmyInPlayer(40);
				a=40;
			}
			System.out.println("Players have been assigned 40 armies each.");
			break;
		case 3:
			for(i=0;i<getPlayersList().size();i++) {
				getPlayersList().get(i).setnoOfArmyInPlayer(35);
				a=35;
			}
			System.out.println("Players have been assigned 35 armies each.");
			break;
		case 4:
			for(i=0;i<getPlayersList().size();i++) {
				getPlayersList().get(i).setnoOfArmyInPlayer(30);
				a=30;
			}
			System.out.println("Players have been assigned 30 armies each.");
			break;
		case 5:
			for(i=0;i<getPlayersList().size();i++) {
				getPlayersList().get(i).setnoOfArmyInPlayer(25);
				a=25;
			}
			System.out.println("Players have been assigned 25 armies each.");
			break;
		case 6:
			for(i=0;i<getPlayersList().size();i++) {
				getPlayersList().get(i).setnoOfArmyInPlayer(20);
				a=20;
			}
			System.out.println("Players have been assigned 20 armies each.");
			break;
		}	
		playerWorldDominationpopulate();
	}

	/**
	 * placeArmy method places army by each player until all players have placed all their armies
	 * @param countryName anme of country for which army has to be placed.
	 */

	public void placeArmy(String countryName) {

		if(placeArmyFlag == false) {
			System.out.println("Illegal Command");
		}
		else {

			PlayerModel tempPlayerModel = currentPlayer(placeArmyCounter);
			CountryModel tempCountryModel = tempPlayerModel.searchCountry(countryName);
			if(tempCountryModel == null) {
				System.out.println("Country doesnot belong to the player");
			}
			else {
				tempCountryModel.setNoOfArmiesCountry(tempCountryModel.getNoOfArmiesCountry()+1);
				tempPlayerModel.setnoOfArmyInPlayer(tempPlayerModel.getnoOfArmyInPlayer()-1);
				System.out.println("The army has been placed on the country: " + countryName);
				placeArmyFlag = true;
				placeArmyCounter++;

				playerWorldDominationplaceArmy();


				if(PlayerOperations.getInstance().getPlayersList().get(PlayerOperations.getInstance().getPlayersList().size()-1).getnoOfArmyInPlayer() == 0 ) {

					playerWorldDomination();

					placeArmyFlag=false;
					CardOperations.getInstance().setCardExchangeFlag(true);

					//Triggering Reinforcement phase View
					dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
					dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(playerCounter).getPlayerName());
					dominationPhase.setCurrentAction("Starting Card Exchange");

					// Triggering Card exchange view 
					dominationCard.setPlayerName(PlayerOperations.getInstance().currentPlayer(playerCounter).getPlayerName());
					dominationCard.setListCards(CardOperations.getInstance().cardStrings(PlayerOperations.getInstance().currentPlayer(playerCounter).getCardList()));


					if(TournamentController.getInstance().tournamentFlag == true)
						tournamentGameEngine();
					else {
						gameEngine();
					}

				}

			}
		}
	}

	/**
	 * placeAll method to randomly place all remaining unplaced armies for all players.
	 */
	public void placeAll() {
		if(placeArmyFlag == false) {
			System.out.println("Illegal Command");
		}
		else {
			int pickedNumber;
			Random randomNumber = new Random();

			for(PlayerModel loopPlayer: PlayerOperations.getInstance().getPlayersList()) {
				for(CountryModel loopCountry: loopPlayer.getPlayerCountryList()) {
					if(loopCountry.getNoOfArmiesCountry() == 0) {
						loopCountry.setNoOfArmiesCountry(1);
						loopPlayer.setnoOfArmyInPlayer(loopPlayer.getnoOfArmyInPlayer()-1);
					}
				}
			}
			for(PlayerModel loopPlayer: PlayerOperations.getInstance().getPlayersList()) {
				for(int j=loopPlayer.getnoOfArmyInPlayer();j!=0;j--) {
					pickedNumber=randomNumber.nextInt(loopPlayer.getPlayerCountryList().size());
					CountryModel loopCountry = loopPlayer.getPlayerCountryList().get(pickedNumber);
					loopCountry.setNoOfArmiesCountry(loopCountry.getNoOfArmiesCountry()+1);
					loopPlayer.setnoOfArmyInPlayer(loopPlayer.getnoOfArmyInPlayer()-1);
				}
			}
			System.out.println("The the armies have been assigned randomly");

			//World Domination View
			playerWorldDomination();

			//Flags
			placeArmyFlag = false;
			CardOperations.getInstance().setCardExchangeFlag(true);

			//Triggering Reinforcement phase View
			dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
			dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(playerCounter).getPlayerName());
			dominationPhase.setCurrentAction("Starting Card Exchange");

			// Triggering Card exchange view 
			dominationCard.setPlayerName(PlayerOperations.getInstance().currentPlayer(playerCounter).getPlayerName());
			dominationCard.setListCards(CardOperations.getInstance().cardStrings(PlayerOperations.getInstance().currentPlayer(playerCounter).getCardList()));

			if(TournamentController.getInstance().tournamentFlag == true)
				tournamentGameEngine();
			else {
				gameEngine();
			}

		}
	}


	/**
	 * getReInforcementArmies the Method to get the number of reinforcing Armies.
	 */
	public void getReInforcementArmies() {
		PlayerModel tempPlayerModel = currentPlayer(playerCounter);
		if(reinforceFlag) {
			System.out.println("Total number of countries "+tempPlayerModel.getPlayerName()+" player owns is "+ tempPlayerModel.getPlayerCountryList().size());
			Double tempReInforceNoOfArmy = (tempPlayerModel.getPlayerCountryList().size())/3.0;
			reInforceNoOfArmy = (int) Math.floor(tempReInforceNoOfArmy);

			//check if armies is < 3 if yes assign 3 number of armies else assign based on the calculated one
			reInforceNoOfArmy = reInforceNoOfArmy<3 ? 3 : reInforceNoOfArmy;

			for(ContinentModel tempContinentModel : MapOperations.getInstance().getContinentsList()) {
				if(tempContinentModel.getCountriesList().size()>0 ) {
					List<CountryModel> tempCountryModelList = new ArrayList<>(tempContinentModel.getCountriesList());
					Iterator<CountryModel> iterator = tempCountryModelList.iterator();
					while(iterator.hasNext()) {
						CountryModel getCountry = iterator.next();
						CountryModel tempPlayerCountry = tempPlayerModel.searchCountry(getCountry.getCountryName());
						if(tempPlayerCountry != null) {
							iterator.remove();
						}
					}

					//This condition means this player owns all the countries of that particular continent
					if(tempCountryModelList.size()==0) {
						if(tempContinentModel.isHasWonContinent() == false) {
							reInforceNoOfArmy = reInforceNoOfArmy + tempContinentModel.getControlValue();
							tempContinentModel.setHasWonContinent(true);
						}
					}
				}

			}
			if(CardOperations.getInstance().isCardExchangeFlag() == false) {
				reInforceNoOfArmy = reInforceNoOfArmy + CardOperations.getInstance().getCardNoOfArmy();

				System.out.println("Total no of armies after reinforcement "+reInforceNoOfArmy);

			}
			reinforceFlag = false;
		}
	}

	/**
	 * reInforce method to reinforce the country with armies.
	 * 
	 * @param countryName name of the country to be reinforced.
	 * @param num number of armies to be reinforced with.
	 * @return message 
	 */

	public String reInforce(String countryName, int num) {

		PlayerModel currentPlayer = currentPlayer(playerCounter);
		strategy = currentPlayer.getStrategy();
		System.out.println(strategy.reinforcementPhase(countryName, num));

		return "";
	}


	/**
	 * attackCountry method for the attacking phase.
	 * 
	 * @param countrynamefrom name of the country of attacker.
	 * @param countrynameto name of country to be targeted.
	 * @param numdice number of dice to roll.
	 */
	public void attackCountry(String countrynamefrom, String countrynameto, int numdice) {
		PlayerModel currentPlayer = currentPlayer(playerCounter);
		strategy = currentPlayer.getStrategy();
		strategy.attackPhase(countrynamefrom, countrynameto, numdice);
	}
	/**
	 * allOutAttack method to is attack until no attack is possible using maximum number of
	 dice to attack/defend
	 * @param countrynamefrom name of the attacking country.
	 * @param countrynameto name of the defending country.
	 */
	public void allOutAttack(String countrynamefrom, String countrynameto) {
		PlayerModel currentPlayer = currentPlayer(playerCounter);
		strategy = currentPlayer.getStrategy();
		strategy.allOutAttack(countrynamefrom, countrynameto);

	}

	/**
	 * fortifyCountry implementation of a valid fortification move from one country to another.
	 * 
	 * @param fromCountry   country name from which armies has to be moved.
	 * @param toCountry     country name to which armies have to be moved.
	 * @param num			number of armies to be moved.
	 * @return				empty string.
	 */
	public String fortifyCountry(String fromCountry,String toCountry, String num) {
		PlayerModel currentPlayer = currentPlayer(playerCounter);
		strategy = currentPlayer.getStrategy();
		System.out.println(strategy.fortificationPhase(fromCountry, toCountry, num));

		return "";
	}


	/**
	 * attackMove method to make a moving attack.
	 * @param num number of attacking armies.
	 */
	public void attackMove(int num) {
		PlayerModel currentPlayer = currentPlayer(playerCounter);
		strategy = currentPlayer.getStrategy();
		strategy.attackMove(num);
	}

	/**
	 * defendCountry method to 
	 * 
	 * @param numdice number of dice
	 */
	public void defendCountry(int numdice) {
		PlayerModel currentPlayer = currentPlayer(playerCounter);
		strategy = currentPlayer.getStrategy();
		strategy.defendCountry(numdice);
	}

	/**
	 * modelOfDefender method returns country model based on country given.
	 * 
	 * @param countrynameto country name.
	 * @return countryModel based on country.
	 */
	public CountryModel modelOfDefender(String countrynameto) {
		for(PlayerModel loopPlayer : PlayerOperations.getInstance().getPlayersList()) {
			for(CountryModel loopCountry : loopPlayer.getPlayerCountryList()) {
				if(loopCountry.getCountryName().equalsIgnoreCase(countrynameto)) {
					return loopCountry;
				}
			}
		}
		return null;
	}
	/**
	 * returnDefenderModel method returns PlayerModel according to country given.
	 * 
	 * @param countrynameto name of the country.
	 * @return PlayerModel.
	 */
	public PlayerModel returnDefendModel (String countrynameto) {
		for(PlayerModel loopPlayer : PlayerOperations.getInstance().getPlayersList()) {
			for(CountryModel loopCountry : loopPlayer.getPlayerCountryList()) {
				if(loopCountry.getCountryName().equalsIgnoreCase(countrynameto)) {
					return loopPlayer;
				}
			}
		}
		return null;
	}
	/**
	 * sortArray method to sort an Array given.
	 * @param array array to be sorted.
	 * @return Array sorted Array.
	 */
	public int[] sortArray(int array[]) {
		for (int i = 0; i < array.length-1; i++) {
			for (int j = 0; j < array.length-i-1; j++) {
				if (array[j] < array[j+1]) 
				{ 
					int temp = array[j]; 
					array[j] = array[j+1]; 
					array[j+1] = temp; 
				} 
			}
		}
		return array;
	}

	/**
	 * gameEngine method to start the game whenever strategy is loaded.
	 */
	public void gameEngine() {

		while(lostPlayers.size()!=playerModelList.size()-1 || playerModelList.size()!=1) {
			PlayerModel currentPlayer = currentPlayer(playerCounter);
			Strategy strategy = currentPlayer.getStrategy();
			if(strategy instanceof HumanPlayer) {
				ReadInputCommand.commandReader();
			}
			else {
				PlayerOperations.getInstance().reInforce("", 0);
				PlayerOperations.getInstance().attackCountry("", "", 0);
				PlayerOperations.getInstance().fortifyCountry("", "", "");

			}
		}
		System.exit(0);	
	}

	/**
	 * tournamentGameEngine method for the tournament mode.
	 * 
	 */
	public void tournamentGameEngine () {
		while(playerModelList.size()!=1 && PlayerOperations.getInstance().noOfTurns<TournamentController.getInstance().getNoOfTurns()) {			
			PlayerModel currentPlayer = currentPlayer(playerCounter);
			System.out.println("*************Turn Number: "+ PlayerOperations.getInstance().noOfTurns + " *********************");
			System.out.println();
			Strategy strategy = currentPlayer.getStrategy();
			if(strategy instanceof HumanPlayer) {
				System.out.println("Human Player "+ currentPlayer.getPlayerName());
				CardOperations.getInstance().setCardExchangeFlag(true);
				ReadInputCommand readCommand = new ReadInputCommand();
				readCommand.commandReader();
			}
			else {
				PlayerOperations.getInstance().reInforce("", 0);
				PlayerOperations.getInstance().attackCountry("", "", 0);

				if(playerModelList.size()==1) {
					TournamentController.getInstance().winner=playerModelList.get(0).getPlayerName();
					return;
				}

				PlayerOperations.getInstance().fortifyCountry("", "", "");

			}
		}
		TournamentController.getInstance().winner="DRAW";
		return;
	}
	/**
	 * rollDice method to generate the random number between 1 to 6.
	 * @return random number generated.
	 */
	public int rollDice() {
		int pickedNumber;
		Random number = new Random();
		pickedNumber = number.nextInt(6);
		return pickedNumber + 1;
	}

	/**
	 * clear method to clear the values.
	 */
	public void clear() {
		this.playerModelList.clear();
		this.armiesToAssign = 0;
		this.placeArmyCounter =1;
		this.reinforceFlag = true;
		this.placeArmyFlag = false;
		this.reInforceNoOfArmy=0;
		this.startup_phaseFlag="false";
		this.noOfTurns=1;
		this.playerCounter = 1;
		this.populateFlag=false;
		this.a=0;
		this.lostPlayers.clear();;

	}

	/**
	 * setPlayerOperationInstance sets the object of PlayerOperations class.
	 * 
	 * @param playerOperationInstance object of PlayerOperations type.
	 */
	public static void setPlayerOperationInstance(PlayerOperations playerOperationInstance) {
		PlayerOperations.playerOperationInstance = playerOperationInstance;
	}
}
