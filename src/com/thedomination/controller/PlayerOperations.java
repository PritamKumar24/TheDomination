package com.thedomination.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.thedomination.model.ContinentModel;
import com.thedomination.model.CountryModel;
import com.thedomination.model.PlayerModel;
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
public class PlayerOperations {

	/** The playerModelList ArrayList of PlayerModel type */
	private ArrayList<PlayerModel> playerModelList;

	/**The armies to Assign */
	private int  armiesToAssign = 0;

	/**Initializes the integer placeArmyCounter */
	private  int placeArmyCounter =1;
	
//	/**Initializes the integer fortifyCountryCounter  */
//	private  int fortifyCountryCounter =0;
//	
//	/**Initializes the integer reInforceCountryCounter  */
//	private  int reInforceCountryCounter =1;
	
	/**The reinforceFlag  */
	boolean reinforceFlag = true;
	
	/**The moveArmyFlag */
	boolean moveArmyFlag =false;
	/**The placeArmyFlag*/
	boolean placeArmyFlag = false;
	
	/**The attackFlag */
	private boolean attackFlag = false;

	/**The defendFlag*/
	private boolean defendFlag = false;
	
	/**The reinforceArmyFlag */
	private boolean reinforceArmyFlag = false;
	/**The  fortifyArmyFlag */
	private boolean fortifyArmyFlag = false;
	
	/**The countrynamefrom */
	private String countrynamefrom;
	
	/** The reInforceNoOfArmy */
	private int reInforceNoOfArmy;

	/**The attackCountryCounter */
	private  int attackCountryCounter = 1;
	
	



	/**The diceAttackArray Array */
	private int[] diceAttackArray;

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
	/**The diceDefendArray Array */
	private int[] diceDefendArray;

	/**
	 *  getCountrynamefrom Method Getter Function to get Country Name.
	 * 
	 * @return countrynamefrom String Country Name .
	 */
	public String getCountrynamefrom() {
		return countrynamefrom;
	}
	/**
	 * setCountrynamefrom Method setter Function to set name of country.
	 * 
	 * @param String countrynamefrom name of Country. 
	 */
	public void setCountrynamefrom(String countrynamefrom) {
		this.countrynamefrom = countrynamefrom;
	}
	/** The countrynameto */
	private String countrynameto;

	/**
	 * getCountrynameto Method Getter Function to get Country Name.
	 * 
	 * @return countrynameto Name of Country.
	 */
	public String getCountrynameto() {
		return countrynameto;
	}
	/**
	 * setCountrynameto Method setter Function to set name of country.
	 * 
	 * @param countrynameto name of Country.
	 */
	public void setCountrynameto(String countrynameto) {
		this.countrynameto = countrynameto;
	}

	/** The object for the player operation */
	private static PlayerOperations UniqueInstance;
	/**
	 * Constructor for the PlayerOperation class.
	 * 
	 * @return object for the PlayerOperation class.
	 */
	public static PlayerOperations getInstance() {
		if(PlayerOperations.UniqueInstance == null) {
			PlayerOperations.UniqueInstance = new PlayerOperations();
		}
		return PlayerOperations.UniqueInstance;
	}
	/** 
	 * Constructor for the PlayerOperation class.
	 */
	public PlayerOperations() {
		this.playerModelList = new ArrayList<>();
	}

	/**
	 * getPlayersList to get list of players.
	 * @return playerModelList players list.
	 */
	public ArrayList<PlayerModel> getPlayersList() {
		return playerModelList;
	}

	//	/** getPlayerModelList returns playerModelList */
	//	public ArrayList<PlayerModel> getPlayerModelList() {
	//		return playerModelList;
	//	}

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
	 * getAttackCountryCounter Method Getter Function to get Attack Country Counter.
	 * 
	 * @return attackCountryCounter Integer Counter value.
	 */
	public int getAttackCountryCounter() {
		return attackCountryCounter;
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
	/**
	 * addPlayer to add players to playerModelList. 
	 * @param playerName name of player to be added.
	 */
	public void  addPlayer(String playerName) {
		if (playerName != null && !playerName.trim().isEmpty() && searchPlayer(playerName)==null ) {
			PlayerModel newPlayer = new PlayerModel(playerName);
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
			}
			System.out.println("Players have been assigned 40 armies each.");
			break;
		case 3:
			for(i=0;i<getPlayersList().size();i++) {
				getPlayersList().get(i).setnoOfArmyInPlayer(35);
			}
			System.out.println("Players have been assigned 35 armies each.");
			break;
		case 4:
			for(i=0;i<getPlayersList().size();i++) {
				getPlayersList().get(i).setnoOfArmyInPlayer(30);
			}
			System.out.println("Players have been assigned 30 armies each.");
			break;
		case 5:
			for(i=0;i<getPlayersList().size();i++) {
				getPlayersList().get(i).setnoOfArmyInPlayer(25);
			}
			System.out.println("Players have been assigned 25 armies each.");
			break;
		case 6:
			for(i=0;i<getPlayersList().size();i++) {
				getPlayersList().get(i).setnoOfArmyInPlayer(20);
			}
			System.out.println("Players have been assigned 20 armies each.");
			break;
		}			
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

			//		int playerIndex = 0;
			//		int playerPosition = 0;
			//		playerPosition = placeArmyCounter % PlayerOperations.getInstance().getPlayersList().size();
			//
			//		if(playerPosition == 0) {
			//			playerIndex =  (placeArmyCounter-1)%(PlayerOperations.getInstance().getPlayersList().size());
			//		}
			//		else {
			//			playerIndex = playerPosition-1;
			//		}

			//PlayerModel tempPlayerModel = PlayerOperations.getInstance().getPlayersList().get(playerIndex);
			PlayerModel tempPlayerModel = currentPlayer(placeArmyCounter);
			//System.out.println(tempPlayerModel);
			CountryModel tempCountryModel = tempPlayerModel.searchCountry(countryName);
			if(tempCountryModel == null) {
				System.out.println("Country doesnot belong to the player");
			}
			else {
				tempCountryModel.setNoOfArmiesCountry(tempCountryModel.getNoOfArmiesCountry()+1);
				tempPlayerModel.setnoOfArmyInPlayer(tempPlayerModel.getnoOfArmyInPlayer()-1);

				//			for(PlayerModel loopPlayer: PlayerOperations.getInstance().getPlayersList()) {
				//			System.out.println(loopPlayer);
				//			}
				System.out.println("The army has been placed on the country: " + countryName);
				placeArmyFlag = true;
				placeArmyCounter++;
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
				placeArmyFlag = false;
				reinforceArmyFlag = true;
	}
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
		String message="";
			if(fortifyArmyFlag) {
		if(fromCountry.equalsIgnoreCase("-none")) {
			message="No countries chosen for fortification";
					attackCountryCounter++;
					fortifyArmyFlag = false;
					reinforceArmyFlag = true;
					reinforceFlag = true;
			return message;
		}

				//fortifyCountryCounter++;
				//		int playerIndex = 0;
				//		int playerPosition = 0;
				//		playerPosition = fortifyCountryCounter % PlayerOperations.getInstance().getPlayersList().size();
				//
				//		if(playerPosition == 0) {
				//			playerIndex =  (fortifyCountryCounter-1)%(PlayerOperations.getInstance().getPlayersList().size());
				//		}
				//		else {
				//			playerIndex = playerPosition-1;
				//		}

		//PlayerModel tempPlayerModel = PlayerOperations.getInstance().getPlayersList().get(playerIndex);
				PlayerModel tempPlayerModel = currentPlayer(attackCountryCounter);
		CountryModel tempFromCountryModel = tempPlayerModel.searchCountry(fromCountry);
		CountryModel tempToCountryModel = tempPlayerModel.searchCountry(toCountry);
		if(tempFromCountryModel == null || tempToCountryModel == null) {
			message = "Fortification Not possible";
		}
		else if(MapOperations.getInstance().searchNeighbourCountry(tempFromCountryModel.getCountryName(),tempToCountryModel.getCountryPosition()) == null) {
			message = "Countries are not neighbors, fortification Not possible";
		}
		else if(tempFromCountryModel.getNoOfArmiesCountry() > 1 && tempFromCountryModel.getNoOfArmiesCountry() > Integer.parseInt(num)) {
			tempToCountryModel.setNoOfArmiesCountry((tempToCountryModel.getNoOfArmiesCountry()+Integer.parseInt(num)));
			tempFromCountryModel.setNoOfArmiesCountry((tempFromCountryModel.getNoOfArmiesCountry()-Integer.parseInt(num)));
			message="Fortification Done.";
					fortifyArmyFlag = false;
					attackCountryCounter++;
					reinforceArmyFlag = true;
					reinforceFlag = true;
		}
		else {
			message="Fortification Not possible";
		}
			}
			else {
				message = "Illegal Move";
			}
		return message;
	}

	/**
	 * reInforce method for player to place all reinforcement armies on the map
	 * 
	 * @param countryName name of country on which armies has to be placed.
	 * @param num number of armies to be placed.
	 * @return empty String
	 */

	//	public PlayerModel getCurrentReinforcementPlayer() {
	//		int playerIndex = 0;
	//		int playerPosition = 0;
	//		playerPosition = reInforceCountryCounter % PlayerOperations.getInstance().getPlayersList().size();
	//		if(playerPosition == 0) {
	//			playerIndex =  (reInforceCountryCounter-1)%(PlayerOperations.getInstance().getPlayersList().size());
	//		}
	//		else {
	//			playerIndex = playerPosition-1;
	//		}
	//		return PlayerOperations.getInstance().getPlayersList().get(playerIndex);
	//	}

	//	public String reInforce(String countryName, int num) {
	//
	//		String message="";
	////		int playerIndex = 0;
	////		int playerPosition = 0;
	////		playerPosition = reInforceCountryCounter % PlayerOperations.getInstance().getPlayersList().size();
	////
	////		if(playerPosition == 0) {
	////			playerIndex =  (reInforceCountryCounter-1)%(PlayerOperations.getInstance().getPlayersList().size());
	////		}
	////		else {
	////			playerIndex = playerPosition-1;
	////		}
	//
	//		//PlayerModel tempPlayerModel = PlayerOperations.getInstance().getPlayersList().get(playerIndex);
	//		
	//		PlayerModel tempPlayerModel = playerIndex(reInforceCountryCounter);
	//		System.out.println(tempPlayerModel.getPlayerName()+" is going to reinforce his armies..");
	//		System.out.println("Total number of countries "+tempPlayerModel.getPlayerName()+" player owns is "+ tempPlayerModel.getPlayerCountryList().size());
	//
	//		//Calculate reInforce army
	//		if(reinforceFlag) {
	//			Double tempReInforceNoOfArmy = (tempPlayerModel.getPlayerCountryList().size())/3.0;
	//			reInforceNoOfArmy = (int) Math.floor(tempReInforceNoOfArmy);
	//			//check if armies is < 3 if yes assign 3 number of armies else assign based on the calculated one
	//			reInforceNoOfArmy = reInforceNoOfArmy<3 ? 3 : reInforceNoOfArmy;
	//
	//			for(ContinentModel tempContinentModel : MapOperations.getInstance().getContinentsList()) {
	//				if(tempContinentModel.getCountriesList().size() >0) {
	//					List<CountryModel> tempCountryModelList = new ArrayList<>(tempContinentModel.getCountriesList());
	//					Iterator<CountryModel> iterator = tempCountryModelList.iterator();
	//					while(iterator.hasNext()) {
	//						CountryModel getCountry = iterator.next();
	//						CountryModel tempPlayerCountry = tempPlayerModel.searchCountry(getCountry.getCountryName());
	//						if(tempPlayerCountry != null) {
	//							iterator.remove();
	//						}
	//					}
	//
	//					if(tempCountryModelList.size()==0) {
	//						reInforceNoOfArmy = reInforceNoOfArmy + tempContinentModel.getControlValue();
	//					}
	//				}
	//			}
	//			reinforceFlag = false;
	//		}
	//		message="Armies got as reinforment "+reInforceNoOfArmy;
	//		System.out.println("Armies got as reinforment "+reInforceNoOfArmy);
	//		tempPlayerModel.setnoOfArmyInPlayer(reInforceNoOfArmy);
	//
	//		while(reInforceNoOfArmy>0) {
	//			if(num>reInforceNoOfArmy || num<0) {
	//				System.out.println("Not possible");
	//				break;
	//			}
	//			CountryModel countryAssignedArmy = tempPlayerModel.searchCountry(countryName);
	//			countryAssignedArmy.setNoOfArmiesCountry(countryAssignedArmy.getNoOfArmiesCountry() + num);
	//			tempPlayerModel.setnoOfArmyInPlayer(tempPlayerModel.getnoOfArmyInPlayer()-num);
	//			reInforceNoOfArmy = reInforceNoOfArmy - num;
	//			break;
	//
	//		}
	//		System.out.println("No of Armies left to assign "+ reInforceNoOfArmy );
	//		message="No of Armies left to assign "+ reInforceNoOfArmy;
	//		if(reInforceNoOfArmy == 0) {
	//			reInforceCountryCounter++;
	//			reinforceFlag = true;
	//		}
	//		//System.out.println(tempPlayerModel);
	//		return message;
	//	}
	
	
	/**
	 * getReInforcementArmies the Method to get the number of reinforcing Armies.
	 */
	public void getReInforcementArmies() {
		PlayerModel tempPlayerModel = currentPlayer(attackCountryCounter);
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
						reInforceNoOfArmy = reInforceNoOfArmy + tempContinentModel.getControlValue();
					}
				}
			}
			System.out.println("Number of armies after reinforcement "+reInforceNoOfArmy);

			if(CardOperations.getInstance().isCardExchangeFlag() == false) {
				reInforceNoOfArmy = reInforceNoOfArmy + CardOperations.getInstance().getCardNoOfArmy();

				System.out.println("Total no of armies after reinforcement and card exchange "+reInforceNoOfArmy);
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
		String message="";
			if(reinforceArmyFlag == false) {
				message = "Illegal Command";
				return message;
			}
			PlayerModel currentPlayer = currentPlayer(attackCountryCounter);
			CountryModel countryAssignedArmy = currentPlayer.searchCountry(countryName);
			if(countryAssignedArmy == null) {
				message = countryName + " doesn't belong to this player. TRY AGAIN!!";
				return message;
			}
		getReInforcementArmies();
		currentPlayer.setnoOfArmyInPlayer(reInforceNoOfArmy);

		while(reInforceNoOfArmy>0) {
				if(num>reInforceNoOfArmy || num<=0) {
				System.out.println("!!!!Reinforcement Not Possible!!!!");
				break;
			}
				System.out.println(currentPlayer.getPlayerName()+" is going to reinforce his armies..");
				countryAssignedArmy.setNoOfArmiesCountry(countryAssignedArmy.getNoOfArmiesCountry() + num);
				currentPlayer.setnoOfArmyInPlayer(currentPlayer.getnoOfArmyInPlayer()-num);
				reInforceNoOfArmy = reInforceNoOfArmy - num;
				break;
			}
			System.out.println("No of armies left to assign "+ reInforceNoOfArmy );
			message="No of Armies left to assign "+ reInforceNoOfArmy;
			if(reInforceNoOfArmy == 0) {
				//reInforceCountryCounter++;
				//reinforceFlag = true;
				attackFlag =true;
				reinforceArmyFlag = false;
			}
			return message;
		}
		
	public boolean isAttackFlag() {
		return attackFlag;
	}

	public void setAttackFlag(boolean attackFlag) {
		this.attackFlag = attackFlag;
	}

	/**
	 * attackCountry method for the attacking phase.
	 * 
	 * @param countrynamefrom name of the country of attacker.
	 * @param countrynameto name of country to be targeted.
	 * @param numdice number of dice to roll.
	 */
	public void attackCountry(String countrynamefrom, String countrynameto, int numdice) {

		if (attackFlag == false) {
			System.out.println("Invalid move");
		}
		else if(countrynamefrom.equalsIgnoreCase("-noattack")) {
			//defendCountryCounter++;
			fortifyArmyFlag = true;
			//attackCountryCounter++;
			attackFlag = false;
			System.out.println("No attack chosen");
		}
		else {
			defendFlag = false;
			moveArmyFlag = false;
			PlayerOperations.getInstance().setCountrynamefrom(countrynamefrom);
			PlayerOperations.getInstance().setCountrynameto(countrynameto);		
			//		}
			//		if(attackFlag) {
			int diceAttack[] = new int[numdice];

			//int playerIndex = playerIndex(attackCountryCounter);

			//PlayerModel tempPlayerModelAttackCountry = PlayerOperations.getInstance().getPlayersList().get(playerIndex);

			PlayerModel tempPlayerModelAttackCountry = PlayerOperations.getInstance().currentPlayer(attackCountryCounter);
			CountryModel loopCountryFrom = tempPlayerModelAttackCountry.searchCountry(countrynamefrom);

			//PlayerModel tempPlayerModelDefendCountry = modelOfDefender(PlayerOperations.getInstance().getCountrynameto());

			//CountryModel loopCountryTo = tempPlayerModelDefendCountry.searchCountry(PlayerOperations.getInstance().getCountrynameto());
			CountryModel loopCountryTo = modelOfDefender(countrynameto);
			int count=0;
			for(CountryModel loopCountry : tempPlayerModelAttackCountry.getPlayerCountryList()) {
				if(loopCountry.getNoOfArmiesCountry()<=1) {
					count++;
				}
			}
			if(count == tempPlayerModelAttackCountry.getPlayerCountryList().size()) {
				//attackCountryCounter++;
				fortifyArmyFlag = true;
				System.out.println("Player doesnot have enough armies to attack any other territory.");
				System.out.println("Skipping turn of attack for Player " + tempPlayerModelAttackCountry.getPlayerName());
			}
			else if(loopCountryFrom == null) {
				System.out.println("Attacking country doesnot belong to the player");
			}
			else if(tempPlayerModelAttackCountry.searchCountry(countrynameto) != null){
				System.out.println("Defending country cannot belong to the same player");
			}
			//			else if(MapOperations.getInstance().searchNeighbourCountry(countrynamefrom, modelOfDefender(getCountrynameto()).searchCountry(countrynameto).getCountryPosition()) == null) {
			//				System.out.println("Both of the countries should be neighbors");
			//			}
			else if(MapOperations.getInstance().searchNeighbourCountry(countrynamefrom, modelOfDefender(countrynameto).getCountryPosition()) == null) {
				System.out.println("Both of the countries should be neighbors");
			}
			else if(loopCountryFrom.getNoOfArmiesCountry()<2) {
				System.out.println("Armies of attacking country should be more than 1");
			}
			else if(loopCountryTo.getNoOfArmiesCountry()<1) {
				System.out.println("Defending country doesnot have any army");
			}
			else if(numdice>3 || numdice>loopCountryFrom.getNoOfArmiesCountry()-1 || numdice<=0) {
				System.out.println("Invalid number of dice");
			}
			else {
				System.out.print("Dice roll value ");
				System.out.println();
				for(int i=0; i<numdice; i++) {
					diceAttack[i]=rollDice();
					System.out.print(diceAttack[i] + " ");
				}
				System.out.println();
				PlayerOperations.getInstance().setDiceAttackArray(sortArray(diceAttack));
				System.out.println("Player " + tempPlayerModelAttackCountry.getPlayerName() + " is ready to attack");
				defendFlag=true;
				attackFlag = false;
			}		
		}

	}
	/**
	 * allOutAttack method to is attack until no attack is possible using maximum number of
dice to attack/defend
	 * @param countrynamefrom name of the attacking country.
	 * @param countrynameto name of the defending country.
	 */
	public void allOutAttack(String countrynamefrom, String countrynameto) {

		if(attackFlag == false) {
			System.out.println("Invalid move");
		}
		else {
			defendFlag=false;
			moveArmyFlag = false;
			PlayerOperations.getInstance().setCountrynamefrom(countrynamefrom);
			PlayerOperations.getInstance().setCountrynameto(countrynameto);

			//int playerIndex=playerIndex(attackCountryCounter);
			PlayerModel tempPlayerModelAttackCountry = PlayerOperations.getInstance().currentPlayer(attackCountryCounter);
			CountryModel loopCountryFrom = tempPlayerModelAttackCountry.searchCountry(countrynamefrom);
			attackFlag=true;

			//PlayerModel tempPlayerModelAttackCountry = PlayerOperations.getInstance().getPlayersList().get(playerIndex);
			//PlayerModel tempPlayerModelDefendCountry = modelOfDefender(AttackOperations.getInstance().getCountrynameto());

			CountryModel loopCountryTo = modelOfDefender(countrynameto);
			int count=0;

			for(CountryModel loopCountry : tempPlayerModelAttackCountry.getPlayerCountryList()) {
				if(loopCountry.getNoOfArmiesCountry()<=1) {
					count++;
				}
			}
			if(count == tempPlayerModelAttackCountry.getPlayerCountryList().size()) {
				//attackCountryCounter++;
				fortifyArmyFlag = true;
				System.out.println("Player doesnot have enough armies to attack any other territory.");
				System.out.println("Skipping turn of attack for Player " + tempPlayerModelAttackCountry.getPlayerName());
				//defendCountryCounter++;
			}
			else if(loopCountryFrom == null) {
				System.out.println("Attacking country doesnot belong to the player");
			}
			else if(tempPlayerModelAttackCountry.searchCountry(countrynameto) != null){
				System.out.println("Defending country cannot belong to the same player");
			}
			else if(MapOperations.getInstance().searchNeighbourCountry(countrynamefrom, modelOfDefender(countrynameto).getCountryPosition()) == null) {
				System.out.println("Both of the countries should be neighbors");
			}	
			else if(loopCountryFrom.getNoOfArmiesCountry()<2) {
				System.out.println("Armies of attacking country should be more than 1");
			}
			else if(loopCountryTo.getNoOfArmiesCountry()<1) {
				System.out.println("Defending country doesnot have any army");
			}
			else {
				while(attackFlag) {
					int diceAttack[] = new int[tempPlayerModelAttackCountry.searchCountry(countrynamefrom).getNoOfArmiesCountry() > 3 ? 3 : tempPlayerModelAttackCountry.searchCountry(countrynamefrom).getNoOfArmiesCountry()-1];
					System.out.println("Attacker dice roll");
					for(int i=0; i<(diceAttack.length);i++) {
						diceAttack[i]=rollDice();
						System.out.print(diceAttack[i] + " ");
					}
					System.out.println();
					PlayerOperations.getInstance().setDiceAttackArray(sortArray(diceAttack));

					//CountryModel loopCountryTo = tempPlayerModelDefendCountry.searchCountry(AttackOperations.getInstance().getCountrynameto());
					int diceDefend[]=new int[loopCountryTo.getNoOfArmiesCountry() > 2 ? 2 : loopCountryTo.getNoOfArmiesCountry()];
					System.out.println("Defender dice roll");
					for(int i=0; i<(diceDefend.length);i++) {
						diceDefend[i]=rollDice();
						System.out.print(diceDefend[i] + " ");
					}
					System.out.println();
					PlayerOperations.getInstance().setDiceDefendArray(sortArray(diceDefend));

					for(int i = 0;i<(PlayerOperations.getInstance().getDiceDefendArray().length > PlayerOperations.getInstance().getDiceAttackArray().length ? PlayerOperations.getInstance().getDiceAttackArray().length : PlayerOperations.getInstance().getDiceDefendArray().length);i++) {
						if((PlayerOperations.getInstance().getDiceAttackArray()[i] <= PlayerOperations.getInstance().getDiceDefendArray()[i])) {
							//tempPlayerModelAttackCountry.searchCountry(countrynamefrom).setNoOfArmiesCountry(tempPlayerModelAttackCountry.searchCountry(countrynamefrom).getNoOfArmiesCountry()-1);
							loopCountryFrom.setNoOfArmiesCountry(loopCountryFrom.getNoOfArmiesCountry()-1);
							System.out.println("Attacker looses one army");
							//System.out.println(tempPlayerModelAttackCountry.searchCountry(countrynamefrom).getCountryName() + " has armies " + tempPlayerModelAttackCountry.searchCountry(countrynamefrom).getNoOfArmiesCountry());
							System.out.println(loopCountryFrom.getCountryName() + " has armies " + loopCountryFrom.getNoOfArmiesCountry());
						}
						else {
							//tempPlayerModelDefendCountry.searchCountry(countrynameto).setNoOfArmiesCountry(tempPlayerModelDefendCountry.searchCountry(countrynameto).getNoOfArmiesCountry()-1);
							loopCountryTo.setNoOfArmiesCountry(loopCountryTo.getNoOfArmiesCountry()-1);
							System.out.println("Defender looses one army");
							//System.out.println(tempPlayerModelDefendCountry.searchCountry(countrynameto).getCountryName() + " has armies " + tempPlayerModelDefendCountry.searchCountry(countrynameto).getNoOfArmiesCountry());
							System.out.println(loopCountryTo.getCountryName() + " has armies " + loopCountryTo.getNoOfArmiesCountry());
						}						
					}
					System.out.println();
					if(loopCountryTo.getNoOfArmiesCountry() == 0) {
						attackFlag = false;
						moveArmyFlag=true;
						System.out.println("Attacker has won " + countrynameto);
						System.out.println("Armies on " + countrynamefrom + " " + loopCountryFrom.getNoOfArmiesCountry());
						System.out.println("Now move the armies from " + countrynamefrom + " " + countrynameto);
					}
					else if(loopCountryFrom.getNoOfArmiesCountry() == 1){
						attackFlag = true;
						moveArmyFlag = false;
						System.out.println("Attacker has lost" );
						System.out.println("Player cannot attack more. 1 army left on attacking country");
						System.out.println("Armies on " + countrynameto + " " + loopCountryTo.getNoOfArmiesCountry());
						break;
					}
				}
			}
		}
	}
	/**
	 * defendCoubtry method to 
	 * 
	 * @param numdice number of dice
	 */
	public void defendCountry(int numdice) {

		if(defendFlag == false) {
			System.out.println("Invalid move");
		}
		//int playerIndex=playerIndex(defendCountryCounter);

		//PlayerModel tempPlayerModelAttackCountry = PlayerOperations.getInstance().getPlayersList().get(playerIndex);
		else {
			moveArmyFlag = false;
			attackFlag = false; 
			PlayerModel tempPlayerModelAttackCountry = PlayerOperations.getInstance().currentPlayer(attackCountryCounter);
			CountryModel loopCountryFrom = tempPlayerModelAttackCountry.searchCountry(countrynamefrom);

			//PlayerModel tempPlayerModelDefendCountry = modelOfDefender(AttackOperations.getInstance().getCountrynameto());

			//CountryModel loopCountryTo = tempPlayerModelDefendCountry.searchCountry(AttackOperations.getInstance().getCountrynameto());

			CountryModel loopCountryTo = modelOfDefender(countrynameto);

			if(numdice>2 || numdice>loopCountryTo.getNoOfArmiesCountry() || numdice<=0) {
				System.out.println("Invalid number of dice");
			}
			else {
				int diceDefend[] = new int[numdice];
				System.out.print("Dice roll value ");
				for(int i=0; i<numdice; i++) {
					diceDefend[i]=rollDice();
					System.out.print(diceDefend[i] + " ");
				}
				System.out.println();

				PlayerOperations.getInstance().setDiceDefendArray(sortArray(diceDefend));


				for(int i = 0;i<(PlayerOperations.getInstance().getDiceDefendArray().length > PlayerOperations.getInstance().getDiceAttackArray().length ? PlayerOperations.getInstance().getDiceAttackArray().length : PlayerOperations.getInstance().getDiceDefendArray().length);i++) {
					if((PlayerOperations.getInstance().getDiceAttackArray()[i] <= PlayerOperations.getInstance().getDiceDefendArray()[i])) {
						//					for(CountryModel loopCountry : tempPlayerModelAttackCountry.getPlayerCountryList()) {
						//						if(loopCountry.getCountryName().equalsIgnoreCase(AttackOperations.getInstance().getCountrynamefrom())) {
						loopCountryFrom.setNoOfArmiesCountry(loopCountryFrom.getNoOfArmiesCountry()-1);
						System.out.println("Attacker looses one army");
						System.out.println(loopCountryFrom.getCountryName() + " has armies " + loopCountryFrom.getNoOfArmiesCountry());
						//						}
						//					}
					}
					else {
						//					for(CountryModel loopCountry : tempPlayerModelDefendCountry.getPlayerCountryList()) {
						//						if(loopCountry.getCountryName().equalsIgnoreCase(AttackOperations.getInstance().getCountrynameto())) {
						loopCountryTo.setNoOfArmiesCountry(loopCountryTo.getNoOfArmiesCountry()-1);
						System.out.println("Defender looses one army");
						System.out.println(loopCountryTo.getCountryName() + " has armies " + loopCountryTo.getNoOfArmiesCountry());
					}
					defendFlag = false;
					attackFlag = true;
				}

				if(loopCountryTo.getNoOfArmiesCountry() == 0) {
					System.out.println("Attacker has won " + countrynameto);
					//System.out.println("Armies on " + countrynamefrom + " " + tempPlayerModelAttackCountry.searchCountry(countrynamefrom).getNoOfArmiesCountry());
					System.out.println("Armies on " + countrynamefrom + " " + loopCountryFrom.getNoOfArmiesCountry());
					//attackFlag = false;
					moveArmyFlag=true;
					attackFlag = false;
					defendFlag = false;
					System.out.println("Now move the armies from " + countrynamefrom + " " + countrynameto);
				}
			}
		}
	}

	/**
	 * attackMove method to make a moving attack.
	 * @param num number of attacking armies.
	 */
	public void attackMove(int num) {
		if(moveArmyFlag) {
			if(num>modelOfDefender(countrynamefrom).getNoOfArmiesCountry()-1) {
				System.out.println("You can move upto " + (modelOfDefender(countrynamefrom).getNoOfArmiesCountry()-1) + " army");
			}
			else {
				modelOfDefender(countrynameto).setNoOfArmiesCountry(num);
				modelOfDefender(countrynamefrom).setNoOfArmiesCountry(modelOfDefender(countrynamefrom).getNoOfArmiesCountry()-num);
				System.out.println("Armies have been moved");
				System.out.println("Armies on " + countrynameto + " " + modelOfDefender(countrynameto).getNoOfArmiesCountry());
				System.out.println("Armies on " + countrynamefrom + " " + modelOfDefender(countrynamefrom).getNoOfArmiesCountry());
				moveArmyFlag = false;
				attackFlag = true;
				CountryModel removeCountry = returnDefendModel(countrynameto).RemoveCountry(modelOfDefender(countrynameto));
				PlayerOperations.getInstance().currentPlayer(attackCountryCounter).getPlayerCountryList().add(removeCountry);
				//Card got as player conquered a country
				CardOperations.getInstance().assignCard(true, PlayerOperations.getInstance().currentPlayer(attackCountryCounter));

			}

		}
		else {
			System.out.println("Illegal move");
		}
	}
	/**
	 * currentPlayer method to show the current player making the moves.
	 * 
	 * @param counter player index
	 * @return PlayerModel Index of the player.
	 */
	public PlayerModel currentPlayer(int counter) {
		int playerIndex = 0;
		int playerPosition = 0;
		playerPosition = counter % PlayerOperations.getInstance().getPlayersList().size();

		if(playerPosition == 0) {
			playerIndex =  (counter-1)%(PlayerOperations.getInstance().getPlayersList().size());
		}
		else {
			playerIndex = playerPosition-1;
		}
		return PlayerOperations.getInstance().getPlayersList().get(playerIndex);
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
 * rollDice method to generate the random number between 1 to 6.
 * @return random number generated.
 */
	public int rollDice() {
		int pickedNumber;
		Random number = new Random();
		pickedNumber = number.nextInt(6);
		//System.out.println("Roll Dice Value: " + pickedNumber);
		return pickedNumber + 1;
	}
}