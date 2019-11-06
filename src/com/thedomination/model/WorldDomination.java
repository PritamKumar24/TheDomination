/**
 * 
 */
package com.thedomination.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.thedomination.view.WorldDominationObserver;



/**
 * <h2> World Domination </h2>
 * The Class WorldDomination. The players world domination view displays: 
 * (1) the percentage of the map controlled by every player 
 * (2) the continents controlled by every player 
 * (3) the total number of armies owned by every player.
 *
 * @author Manpreet Singh
 * @version 2.0
 */
public class WorldDomination implements WorldDominationObservable {
	
	private String playerName;

	/** The percent map contr. */
	private String percentMapContr;

	/** The continents contr. */
	private HashSet<String> continentsContr;

	/** The armies owned. */
	private int armiesOwned;

	/** The domination observers. */
	List<WorldDominationObserver> dominationObservers;

	/**
	 * Instantiates a new world domination.
	 */
	public WorldDomination() {
		dominationObservers = new ArrayList<WorldDominationObserver>();
		continentsContr =new HashSet<String>();
	}
	
	

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * Sets the percent map contr.
	 *
	 * @param percentMapContr the percentMapContr to set
	 */
	public void setPercentMapContr(Double percentMapContr) {
		this.percentMapContr = percentMapContr.toString();
		notifyAllObservers();
	}

	/**
	 * Sets the continents contr.
	 *
	 * @param listOfContinent the continentsContr to set
	 */
	public void setContinent(String continentName) {
		this.continentsContr.add(continentName);
	}

	/**
	 * Sets the armies owned.
	 *
	 * @param armiesOwned the armiesOwned to set
	 */
	public void setArmiesOwned(int armiesOwned) {
		this.armiesOwned = armiesOwned;
	}


	@Override
	public void addObserver(WorldDominationObserver observer) {
		dominationObservers.add(observer);

	}

	@Override
	public void removeObserver(WorldDominationObserver observer) {
		dominationObservers.remove(observer);

	}

	/* (non-Javadoc)
	 * @see com.thedomination.model.DominationDominationObservable#notifyAllObservers()
	 */
	@Override
	public void notifyAllObservers() {
		for (WorldDominationObserver worldDominationObserver : dominationObservers) {
			worldDominationObserver.update(this.playerName,  this.percentMapContr, this.continentsContr, this.armiesOwned);
		}

	}

}
