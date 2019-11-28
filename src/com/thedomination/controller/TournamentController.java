package com.thedomination.controller;

import java.util.ArrayList;

public class TournamentController {

	/** Mapfiles names */
	private ArrayList<String> mapFiles;
	
	/** player startegy names */
	private ArrayList<String> playerStrategy;
	
	/** player names */
	private ArrayList<String> playerName;
	
	/** The noOfGames. */
	private int noOfGames = 0;

	/** The noOfTurns. */
	private int noOfTurns = 0;
	
	/** Tournament Flag */
	public boolean tournamentFlag = false;
	
	/** result in two dimension array */
	public String result[][];
	
	/** winner's name */
	public String winner ="";
	
	/** get number of games */
	public int getNoOfGames() {
		return noOfGames;
	}
	/** set number of games */
	public void setNoOfGames(int noOfGames) {
		this.noOfGames = noOfGames;
	}
	/** get number of turns */
	public int getNoOfTurns() {
		return noOfTurns;
	}
	/** set number of turns */
	public void setNoOfTurns(int noOfTurns) {
		this.noOfTurns = noOfTurns;
	}
	/** get number of Map files */
	public ArrayList<String> getMapFiles() {
		return mapFiles;
	}
	/** set number of map files */
	public void setMapFiles(String mapFile) {
		this.mapFiles.add(mapFile);
	}
	/** get player Strategy */
	public ArrayList<String> getPlayerStrategy() {
		return playerStrategy;
	}
	/** set player Strategy */
	public void setPlayerStrategy(String playerStrategy) {
		this.playerStrategy.add(playerStrategy);
	}
	/** get player name */
	public ArrayList<String> getPlayerName() {
		return playerName;
	}
	/** set player name */
	public void setPlayerName(String playerName) {
		this.playerName.add(playerName);
	}
	
	/** Tournament constructor */
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
	
	/**
	 * startTournament method starts the tournament
	 * and keeps on running util all the turns are finished, draw
	 * or player won the game at particular turn
	 * and display the result
	 */
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
					PlayerOperations.getInstance().populateCountries();
					PlayerOperations.getInstance().placeAll();
					result[k][i]=winner;
					winner="";
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
