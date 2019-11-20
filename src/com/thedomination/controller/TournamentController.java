package com.thedomination.controller;

import java.util.ArrayList;

public class TournamentController {

	private ArrayList<String> mapFiles;
	
	private ArrayList<String> playerStrategy;
	
	private ArrayList<String> playerName;
	
	/** The noOfGames. */
	private int noOfGames = 0;

	/** The noOfTurns. */
	private int noOfTurns = 0;
	
	public boolean tournamentFlag = false;
	
	public String result[][];
	
	public String winner ="";
	
	public int getNoOfGames() {
		return noOfGames;
	}

	public void setNoOfGames(int noOfGames) {
		this.noOfGames = noOfGames;
	}

	public int getNoOfTurns() {
		return noOfTurns;
	}

	public void setNoOfTurns(int noOfTurns) {
		this.noOfTurns = noOfTurns;
	}
	
	public ArrayList<String> getMapFiles() {
		return mapFiles;
	}

	public void setMapFiles(String mapFile) {
		this.mapFiles.add(mapFile);
	}

	public ArrayList<String> getPlayerStrategy() {
		return playerStrategy;
	}

	public void setPlayerStrategy(String playerStrategy) {
		this.playerStrategy.add(playerStrategy);
	}

	public ArrayList<String> getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName.add(playerName);
	}
	 
	public TournamentController() {
		this.mapFiles =new ArrayList<String>();
		this.playerStrategy = new ArrayList<String>();
		this.playerName = new ArrayList<String>();
	}
	
	
	/** The object for the player operation */
	private static TournamentController UniqueInstance;
	/**
	 * Constructor for the PlayerOperation class.
	 * 
	 * @return object for the PlayerOperation class.
	 */
	public static TournamentController getInstance() {
		if(TournamentController.UniqueInstance == null) {
			TournamentController.UniqueInstance = new TournamentController();
		}
		return TournamentController.UniqueInstance;
	}
	
	public void startTournament() {
		tournamentFlag = true;
		result = new String[mapFiles.size()][noOfGames];
		for(int k=0;k<mapFiles.size();k++) {
			
			System.out.println("Map name " + mapFiles.get(k));
			for(int i=0;i<noOfGames;i++) {
				System.out.println("********************New game start********************");
				System.out.println();
				MapOperations.getInstance().loadMap(mapFiles.get(k));
				for(int p=0;p<playerName.size();p++) {
					PlayerOperations.getInstance().addPlayer(playerName.get(p),playerStrategy.get(p));
				}
				//while(this.noOfTurns < PlayerOperations.getInstance().noOfTurns) {
					PlayerOperations.getInstance().populateCountries();
					PlayerOperations.getInstance().placeAll();
					result[k][i]=winner;
					winner="";
				//}
			}
			
		}
		for(int k=0;k<mapFiles.size();k++) {
			System.out.println("Map name: " + mapFiles.get(k));
			for(int i=0;i<noOfGames;i++) {
				System.out.print("Game Number: " + (int)(i+1) + " " + result[k][i]);
				System.out.println();
			}
			System.out.println();
		}
		System.exit(0);
	}
	
}
