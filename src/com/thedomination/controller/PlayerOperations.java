package com.thedomination.controller;

import java.util.ArrayList;
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
				System.out.println("Player: " + i + " Player name: " + playerModelList.get(i).getPlayerName());
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
			
			for(i=0;i<getPlayersList().size();i++) {
				System.out.println("Player " + i);
				System.out.println("Countries for this player" + playerModelList.get(i).getPlayerCountryList());
			}
		      switch (getPlayersList().size()) {
		          case 2:
		        	  for(i=0;i<getPlayersList().size();i++) {
		        		  getPlayersList().get(i).setnoOfArmyInPlayer(40);
		        	  }
		              System.out.println("Players have been assigned 40 armies.");
		              break;
		          case 3:
		        	  for(i=0;i<getPlayersList().size();i++) {
		        		  getPlayersList().get(i).setnoOfArmyInPlayer(35);
		        	  }
		              System.out.println("Players have been assigned 35 armies.");
		              break;
		          case 4:
		        	  for(i=0;i<getPlayersList().size();i++) {
		        		  getPlayersList().get(i).setnoOfArmyInPlayer(30);
		        	  }
		              System.out.println("Players have been assigned 30 armies.");
		              break;
		          case 5:
		        	  for(i=0;i<getPlayersList().size();i++) {
		        		  getPlayersList().get(i).setnoOfArmyInPlayer(25);
		        	  }
		              System.out.println("Players have been assigned 25 armies.");
		              break;
		          case 6:
		        	  for(i=0;i<getPlayersList().size();i++) {
		        		  getPlayersList().get(i).setnoOfArmyInPlayer(20);
		        	  }
		              System.out.println("Players have been assigned 20 armies.");
		              break;
		      }

		      for(PlayerModel tempPlayer : playerModelList) {
		  		System.out.println(tempPlayer.toString());	
		  		}
			
		}
		
		public void placeArmy(String countryName) {
			
			placeArmyCounter++;
			System.out.println(placeArmyCounter);
		
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
			System.out.println(tempPlayerModel);
			CountryModel tempCountryModel = tempPlayerModel.searchCountry(countryName);
			tempCountryModel.setNoOfArmiesCountry(tempCountryModel.getNoOfArmiesCountry()+1);
			tempPlayerModel.setnoOfArmyInPlayer(tempPlayerModel.getnoOfArmyInPlayer()-1);
			
			for(PlayerModel loopPlayer: PlayerOperations.getInstance().getPlayersList()) {
			System.out.println(loopPlayer);
			}
			
		}
}