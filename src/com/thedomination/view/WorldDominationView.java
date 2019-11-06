/**
 * @author Manpreet Singh
 */
package com.thedomination.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.thedomination.model.WorldDominationObservable;

/**
 * <h2> World Domination View class </h2>
 * The Class WorldDominationView. The players world domination view displays: 
 * (1) the percentage of the map controlled by every player 
 * (2) the continents controlled by every player 
 * (3) the total number of armies owned by every player.
 *
 * @author Manpreet Singh
 * @version 2.0
 */
public class WorldDominationView implements WorldDominationObserver {

	/** The percent map contr. */
	private String percentMapContr;
	
	/** The continents contr. */
	private HashSet<String> continentsContr;
	
	/** The armies owned. */
	private int armiesOwned;
	
	/** The world domination observable. */
	WorldDominationObservable worldDominationObservable;

	private String playerName;
	
	/**
	 * Instantiates a new World domination view.
	 *
	 * @param worldDominationObservable the world domination observable
	 */
	public WorldDominationView(WorldDominationObservable worldDominationObservable) {
		this.worldDominationObservable = worldDominationObservable;
		this.worldDominationObservable.addObserver(this);
	}

	/**
	 * printing phase view.
	 */
	private void showPhaseView() {
		System.out.println();
		System.out.println("*************************************************************");
		System.out.println("*                  World Domination View                    *");
		System.out.println("*************************************************************");
		System.out.println("Player's Name " + playerName);
		System.out.printf("The percentage of map controlled by player: "  + "%.3f\n" , Double.parseDouble(this.percentMapContr));
		System.out.println("The continents controlled by player: " + this.continentsContr);
		System.out.println("The total number of armies owned by player: " + this.armiesOwned);
		System.out.println("*************************************************************");
	}

	@Override
	public void update(String playerName, String percentMapContr, HashSet<String> continentsContr, int armiesOwned) {
		        this.playerName = playerName;
				this.percentMapContr = percentMapContr;
				this.continentsContr = continentsContr;
				this.armiesOwned = armiesOwned;

				showPhaseView();
		
	}



}
