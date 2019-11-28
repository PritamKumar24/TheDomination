/**
 * 
 */
package com.thedomination.model;

import java.io.Serializable;
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
public class WorldDomination implements WorldDominationObservable, Serializable {
	
	/**
	 * generated serial id
	 */
	private static final long serialVersionUID = 1L;
	/** The player Name. */
	private String playerName;

	/** The percent map contr. */
	private String percentMapContr;

	/** The continents contr. */
	private HashSet<String> continentsContr;
	/**
	 * Gets the ContinentControlled.
	 *
	 *
	 */
	public HashSet<String> getContinentsContr() {
		return continentsContr;
	}


	/**
	 * Sets the ContinentControlled.
	 *@param  HashSetof continentsContr
	 *
	 */
	public void setContinentsContr(HashSet<String> continentsContr) {
		this.continentsContr = continentsContr;
	}

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

	/**
	 * Sets the SetPlayerName.
	 *
	 * @param listOfContinent the continentsContr to set
	 */

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

	/**
	 * Sets the addObserver .
	 *
	 * @param WorldDominationObserver
	 */

	@Override
	public void addObserver(WorldDominationObserver observer) {
		dominationObservers.add(observer);

	}
	/**
	 * Removes  the removeObserver .
	 *
	 * @param WorldDominationObserver
	 */
	@Override
	public void removeObserver(WorldDominationObserver observer) {
		dominationObservers.remove(observer);

	}
	/**
	 * Notify all Observers .
	 *
	 *
	 */
	@Override
	public void notifyAllObservers() {
		for (WorldDominationObserver worldDominationObserver : dominationObservers) {
			worldDominationObserver.update(this.playerName,  this.percentMapContr, this.continentsContr, this.armiesOwned);
		}

	}

}
