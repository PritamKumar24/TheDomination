/**
 * 
 */
package com.thedomination.model;

import com.thedomination.view.WorldDominationObserver;

/**
 * <h2> World Domination Observable</h2>
 * The Interface WorldDominationObservable.An asynchronous update interface for receiving notifications
 * about WorldDomination information as the WorldDomination is constructed.
 *
 * @author Manpreet Singh
 * @version 2.0
 */
public interface WorldDominationObservable {

	/**
	 * Adds the observer.
	 *
	 * @param observer the observer
	 */
	public void addObserver(WorldDominationObserver observer);
	
	/**
	 * Removes the observer.
	 *
	 * @param observer the observer
	 */
	public void removeObserver(WorldDominationObserver observer);
	
	/**
	 * Notify all observers.
	 */
	public void notifyAllObservers(); 
}
