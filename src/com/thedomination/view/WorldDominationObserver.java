/**
 * 
 */
package com.thedomination.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * <h2> World Domination Observer </h2>
 * The interface WorldDominationObserver. An asynchronous update interface for receiving notifications
 * about WorldDomination information as the WorldDomination is constructed.
 *
 * @author Aditi Bhayana
 * @version 2.0
 */
public interface WorldDominationObserver {
	
	/**
	 * This method is called when information about an WorldDomination
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param percentMapContr the percent map contr
	 * @param continentsContr the continents contr
	 * @param armiesOwned the armies owned
	 */
	public void update(String playerName, String percentMapContr, HashSet<String> continentsContr, int armiesOwned);
}
