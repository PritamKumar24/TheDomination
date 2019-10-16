package com.thedomination.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.thedomination.model.ContinentModel;
import com.thedomination.model.CountryModel;
import com.thedomination.model.PlayerModel;
/**
 * The Class PlayerModel.
 *
 * @author Aditi
 * @version 1.0.0
 */
public class PlayerOperations {

	private ArrayList<PlayerModel> playerModelList;
	private int  armiesToAssign = 0;
	private  int placeArmyCounter =0;
	private  int fortifyCountryCounter =0;
	private  int reInforceCountryCounter =1;
	boolean reinforceFlag = true;
	private int reInforceNoOfArmy;
	
	private static PlayerOperations UniqueInstance;
	
	public static PlayerOperations getInstance() {
		if(PlayerOperations.UniqueInstance == null) {
			PlayerOperations.UniqueInstance = new PlayerOperations();
		}
		return PlayerOperations.UniqueInstance;
	}
	
	public PlayerOperations() {
		this.playerModelList = new ArrayList<>();
	}

	public ArrayList<PlayerModel> getPlayersList() {
		return playerModelList;
	}
	
	public int getArmiesToAssign() {
		return armiesToAssign;
	}

	public void setArmiesToAssign(int armiesToAssign) {
		this.armiesToAssign = armiesToAssign;
	}
	public void  addPlayer(String playerName) {
		if (playerName != null && !playerName.trim().isEmpty() ) {
			if(searchPlayer(playerName)==null) {
				PlayerModel newPlayer = new PlayerModel(playerName);
				getPlayersList().add(newPlayer);
				System.out.println("Player name "+playerName+" added");
			}
			else {
				System.out.println("Player name "+playerName+"Already exists");
			}
		}
	}
	public PlayerModel searchPlayer(String playerName) {
		for (PlayerModel loopPlayer : playerModelList) {
			if (loopPlayer.getPlayerName().equalsIgnoreCase(playerName)) {
				return loopPlayer;
			}
		}
		return null;
	}

		public void  removePlayer(String playerName) {
			if (playerName != null && !playerName.trim().isEmpty() ) {
				if(searchPlayer(playerName)==null){
					System.out.println("Player name "+playerName+" does not exist");
				}
				else{
					getPlayersList().remove(searchPlayer(playerName));
					System.out.println("Player name "+playerName+" deleted");
				}
			}
		}
		
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
//			for(i=0;i<getPlayersList().size();i++) {
//				System.out.println("Player " + i);
//				System.out.println("Countries for this player" + playerModelList.get(i).getPlayerCountryList());
//			}
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

//		      for(PlayerModel tempPlayer : playerModelList) {
//		  		System.out.println(tempPlayer.toString());	
//		  		}
			
		}
		
		public void placeArmy(String countryName) {
			
			placeArmyCounter++;
			//System.out.println(placeArmyCounter);
		
			int playerIndex = 0;
			int playerPosition = 0;
			playerPosition = placeArmyCounter % PlayerOperations.getInstance().getPlayersList().size();
			
			if(playerPosition == 0) {
				playerIndex =  (placeArmyCounter-1)%(PlayerOperations.getInstance().getPlayersList().size());
			}
			else {
				playerIndex = playerPosition-1;
			}
	
			PlayerModel tempPlayerModel = PlayerOperations.getInstance().getPlayersList().get(playerIndex);
			//System.out.println(tempPlayerModel);
			CountryModel tempCountryModel = tempPlayerModel.searchCountry(countryName);
			tempCountryModel.setNoOfArmiesCountry(tempCountryModel.getNoOfArmiesCountry()+1);
			tempPlayerModel.setnoOfArmyInPlayer(tempPlayerModel.getnoOfArmyInPlayer()-1);
			
//			for(PlayerModel loopPlayer: PlayerOperations.getInstance().getPlayersList()) {
//			System.out.println(loopPlayer);
//			}
			System.out.println("The army has been placed on the country: " + countryName);
			
		}
		
		public void placeAll() {
			int pickedNumber;
			Random randomNumber = new Random();
			for(PlayerModel loopPlayer: PlayerOperations.getInstance().getPlayersList()) {
				for(int j=loopPlayer.getnoOfArmyInPlayer();j!=0;j--) {
					pickedNumber=randomNumber.nextInt(loopPlayer.getPlayerCountryList().size());
					CountryModel loopCountry = loopPlayer.getPlayerCountryList().get(pickedNumber);
					loopCountry.setNoOfArmiesCountry(loopCountry.getNoOfArmiesCountry()+1);
					loopPlayer.setnoOfArmyInPlayer(loopPlayer.getnoOfArmyInPlayer()-1);
					//System.out.println("getnoOfArmyInPlayer()" + loopPlayer.getnoOfArmyInPlayer());
				}
			}
//			for(PlayerModel loopPlayer: PlayerOperations.getInstance().getPlayersList()) {
//			System.out.println(loopPlayer);
//			}
			System.out.println("The the armies have been assigned randomly");
		}
		
		public String fortifyCountry(String fromCountry,String toCountry, String num) {
			if(fromCountry.equalsIgnoreCase("none")) {
				System.out.println("No countries chosen for fortification");
				return "";
			}
		
			fortifyCountryCounter++;
			int playerIndex = 0;
			int playerPosition = 0;
			playerPosition = fortifyCountryCounter % PlayerOperations.getInstance().getPlayersList().size();
			
			if(playerPosition == 0) {
				playerIndex =  (fortifyCountryCounter-1)%(PlayerOperations.getInstance().getPlayersList().size());
			}
			else {
				playerIndex = playerPosition-1;
			}
	
			PlayerModel tempPlayerModel = PlayerOperations.getInstance().getPlayersList().get(playerIndex);
			CountryModel tempFromCountryModel = tempPlayerModel.searchCountry(fromCountry);
			CountryModel tempToCountryModel = tempPlayerModel.searchCountry(toCountry);
			if(tempFromCountryModel.getNoOfArmiesCountry() > 1 && tempFromCountryModel.getNoOfArmiesCountry() > Integer.parseInt(num)) {
				tempToCountryModel.setNoOfArmiesCountry((tempToCountryModel.getNoOfArmiesCountry()+Integer.parseInt(num)));
				tempFromCountryModel.setNoOfArmiesCountry((tempFromCountryModel.getNoOfArmiesCountry()-Integer.parseInt(num)));
				System.out.println("Fortification Done.");
//				System.out.println(tempFromCountryModel);
//				System.out.println(tempToCountryModel);
			}
			else {
				System.out.println("Fortification Not possible.");
			}
			return "";
		}
		
		
		public String reInforce(String countryName, int num) {

			int playerIndex = 0;
			int playerPosition = 0;
			playerPosition = reInforceCountryCounter % PlayerOperations.getInstance().getPlayersList().size();

			if(playerPosition == 0) {
				playerIndex =  (reInforceCountryCounter-1)%(PlayerOperations.getInstance().getPlayersList().size());
			}
			else {
				playerIndex = playerPosition-1;
			}
			
			PlayerModel tempPlayerModel = PlayerOperations.getInstance().getPlayersList().get(playerIndex);
			System.out.println(tempPlayerModel.getPlayerName()+" is going to reinforce his armies..");
			System.out.println("Total number of countries "+tempPlayerModel.getPlayerName()+" player owns is "+ tempPlayerModel.getPlayerCountryList().size());
			
			//Calculate reInforce army
			if(reinforceFlag) {
				Double tempReInforceNoOfArmy = (tempPlayerModel.getPlayerCountryList().size())/3.0;
				reInforceNoOfArmy = (int) Math.floor(tempReInforceNoOfArmy);
				//check if armies is < 3 if yes assign 3 number of armies else assign based on the calculated one
				reInforceNoOfArmy = reInforceNoOfArmy<3 ? 3 : reInforceNoOfArmy;

				for(ContinentModel tempContinentModel : MapOperations.getInstance().getContinentsList()) {
					List<CountryModel> tempCountryModelList = new ArrayList<>(tempContinentModel.getCountriesList());
					Iterator<CountryModel> iterator = tempCountryModelList.iterator();
					while(iterator.hasNext()) {
						CountryModel getCountry = iterator.next();
						CountryModel tempPlayerCountry = tempPlayerModel.searchCountry(getCountry.getCountryName());
						if(tempPlayerCountry != null) {
							iterator.remove();
						}
					}

					if(tempCountryModelList.size()==0) {
						reInforceNoOfArmy = reInforceNoOfArmy + tempContinentModel.getControlValue();
					}
				}
				reinforceFlag = false;
			}
			
			System.out.println("Armies got as reinforment "+reInforceNoOfArmy);
			tempPlayerModel.setnoOfArmyInPlayer(reInforceNoOfArmy);
			
			while(reInforceNoOfArmy>0) {
				CountryModel countryAssignedArmy = tempPlayerModel.searchCountry(countryName);
				countryAssignedArmy.setNoOfArmiesCountry(countryAssignedArmy.getNoOfArmiesCountry() + num);
				tempPlayerModel.setnoOfArmyInPlayer(tempPlayerModel.getnoOfArmyInPlayer()-num);
				reInforceNoOfArmy = reInforceNoOfArmy - num;
				break;
				
			}
			System.out.println("No of Armies left to assign "+ reInforceNoOfArmy );
			if(reInforceNoOfArmy == 0) {
				reInforceCountryCounter++;
				reinforceFlag = true;
			}
			//System.out.println(tempPlayerModel);
			return "";
		}
}