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
	private  int placeArmyCounter =0;
	
	/**Initializes the integer fortifyCountryCounter  */
	private  int fortifyCountryCounter =0;
	
	/**Initializes the integer reInforceCountryCounter  */
	private  int reInforceCountryCounter =1;
	
	/**The reinforceFlag  */
	boolean reinforceFlag = true;

	boolean moveArmyFlag =false;
	
	/** The reInforceNoOfArmy */
	private int reInforceNoOfArmy;
	
	private  int attackCountryCounter = 1;

	private  int defendCountryCounter = 1;

	private boolean attackFlag = true;

	private boolean defendFlag = true;

	private String countrynamefrom;

	private int[] diceAttackArray;

	public int[] getDiceAttackArray() {
		return diceAttackArray;
	}

	public void setDiceAttackArray(int[] diceAttackArray) {
		this.diceAttackArray = diceAttackArray;
	}

	public int[] getDiceDefendArray() {
		return diceDefendArray;
	}

	public void setDiceDefendArray(int[] diceDefendArray) {
		this.diceDefendArray = diceDefendArray;
	}

	private int[] diceDefendArray;
	
	public String getCountrynamefrom() {
		return countrynamefrom;
	}

	public void setCountrynamefrom(String countrynamefrom) {
		this.countrynamefrom = countrynamefrom;
	}

	private String countrynameto;
	
	public String getCountrynameto() {
		return countrynameto;
	}

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
	
	
	
	
	public boolean isReinforceFlag() {
		return reinforceFlag;
	}
	
	public int getReInforceNoOfArmy() {
		return reInforceNoOfArmy;
	}
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
			
			placeArmyCounter++;
		
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
		tempCountryModel.setNoOfArmiesCountry(tempCountryModel.getNoOfArmiesCountry()+1);
		tempPlayerModel.setnoOfArmyInPlayer(tempPlayerModel.getnoOfArmyInPlayer()-1);

		//			for(PlayerModel loopPlayer: PlayerOperations.getInstance().getPlayersList()) {
		//			System.out.println(loopPlayer);
		//			}
		System.out.println("The army has been placed on the country: " + countryName);
			
		}
		
		/**
		 * placeAll method to randomly place all remaining unplaced armies for all players.
		 */
		public void placeAll() {
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
		if(fromCountry.equalsIgnoreCase("-none")) {
				message="No countries chosen for fortification";
				fortifyCountryCounter++;
				return message;
			}

		fortifyCountryCounter++;
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
		PlayerModel tempPlayerModel = currentPlayer(fortifyCountryCounter);
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
			}
			else {
				message="Fortification Not possible";
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
		public void getReInforcementArmies() {
		PlayerModel tempPlayerModel = currentPlayer(reInforceCountryCounter);
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
				System.out.println("Armies got as reInforcement "+reInforceNoOfArmy);

				reinforceFlag = false;
			}
		}
		
		public String reInforce(String countryName, int num) {
			String message="";
			
		PlayerModel currentPlayer = currentPlayer(reInforceCountryCounter);
			System.out.println(currentPlayer.getPlayerName()+" is going to reinforce his armies..");
			
			getReInforcementArmies();
			currentPlayer.setnoOfArmyInPlayer(reInforceNoOfArmy);
			
			while(reInforceNoOfArmy>0) {
				if(num>reInforceNoOfArmy || num<0) {
					System.out.println("!!!!Reinforcement Not Possible!!!!");
					break;
				}
				CountryModel countryAssignedArmy = currentPlayer.searchCountry(countryName);
				countryAssignedArmy.setNoOfArmiesCountry(countryAssignedArmy.getNoOfArmiesCountry() + num);
				currentPlayer.setnoOfArmyInPlayer(currentPlayer.getnoOfArmyInPlayer()-num);
				reInforceNoOfArmy = reInforceNoOfArmy - num;
				break;
				
			}
			System.out.println("No of armies left to assign "+ reInforceNoOfArmy );
			message="No of Armies left to assign "+ reInforceNoOfArmy;
			if(reInforceNoOfArmy == 0) {
				reInforceCountryCounter++;
				reinforceFlag = true;
			}
			return message;
		}
	public void attackCountry(String countrynamefrom, String countrynameto, int numdice) {
		
		if (attackFlag == false) {
			System.out.println("Invalid move");
		}
		else if(countrynamefrom.equalsIgnoreCase("-noattack")) {
			defendCountryCounter++;
			attackCountryCounter++;
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
				attackCountryCounter++;
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
				attackCountryCounter++;
				System.out.println("Player doesnot have enough armies to attack any other territory.");
				System.out.println("Skipping turn of attack for Player " + tempPlayerModelAttackCountry.getPlayerName());
				defendCountryCounter++;
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
					for(int i=0; i<(diceAttack.length);i++) {
						diceAttack[i]=rollDice();
						System.out.print(diceAttack[i] + " ");
					}
					System.out.println();
					PlayerOperations.getInstance().setDiceAttackArray(sortArray(diceAttack));

					//CountryModel loopCountryTo = tempPlayerModelDefendCountry.searchCountry(AttackOperations.getInstance().getCountrynameto());
					int diceDefend[]=new int[loopCountryTo.getNoOfArmiesCountry() > 2 ? 2 : loopCountryTo.getNoOfArmiesCountry()];
					for(int i=0; i<(diceDefend.length);i++) {
						diceDefend[i]=rollDice();
						System.out.print(diceDefend[i] + " ");
					}
					System.out.println();
					PlayerOperations.getInstance().setDiceDefendArray(sortArray(diceDefend));

					for(int i = 0;i<(PlayerOperations.getInstance().getDiceDefendArray().length > PlayerOperations.getInstance().getDiceAttackArray().length ? PlayerOperations.getInstance().getDiceAttackArray().length : PlayerOperations.getInstance().getDiceDefendArray().length);i++) {
						if((PlayerOperations.getInstance().getDiceAttackArray()[i] < PlayerOperations.getInstance().getDiceDefendArray()[i])) {
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
						attackFlag = false;
					}

					//if(tempPlayerModelDefendCountry.searchCountry(countrynameto).getNoOfArmiesCountry() == 0) {
					if(loopCountryTo.getNoOfArmiesCountry() == 0) {
						System.out.println("Attacker has won " + countrynameto);
						//System.out.println("Armies on " + countrynamefrom + " " + tempPlayerModelAttackCountry.searchCountry(countrynamefrom).getNoOfArmiesCountry());
						System.out.println("Armies on " + countrynamefrom + " " + loopCountryTo.getNoOfArmiesCountry());
						//attackFlag = false;
						moveArmyFlag=true;
						System.out.println("Now move the armies from " + countrynamefrom + " " + countrynameto);
					}
					else if(loopCountryFrom.getNoOfArmiesCountry() == 1) {
						System.out.println("Defender has won " + countrynamefrom);
						System.out.println("Player cannot attack more. 1 army left on attacking country");
						//System.out.println("Armies on " + countrynameto + " " + tempPlayerModelDefendCountry.searchCountry(countrynameto).getNoOfArmiesCountry());
						System.out.println("Armies on " + countrynameto + " " + loopCountryFrom.getNoOfArmiesCountry());
						//attackFlag = false;
					}
				}
			}
		}
	}
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
					if((PlayerOperations.getInstance().getDiceAttackArray()[i] < PlayerOperations.getInstance().getDiceDefendArray()[i])) {
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
				
				
			}
			
		}
		else {
			System.out.println("Illegal move");
		}
	}
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
	
	public int rollDice() {
		int pickedNumber;
		Random number = new Random();
		pickedNumber = number.nextInt(6);
		//System.out.println("Roll Dice Value: " + pickedNumber);
		return pickedNumber + 1;
	}
}