/**
 * 
 */
package com.thedomination.model;

import com.thedomination.view.DominationPhaseObserver;

/**
 * <h2> Domination Phase Observable </h2>
 * The Interface DominationPhaseObservable. An asynchronous update interface for receiving notifications
 * about DominationPhase information as the DominationPhase is constructed.
 *
 * @author Manpreet Singh
 * @version 2.0
 */
public interface DominationPhaseObservable {

	/**
	 * Adds the observer.
	 *
	 * @param observer the observer
	 */
	public void addObserver(DominationPhaseObserver observer);
	
	/**
	 * Removes the observer.
	 *
	 * @param observer the observer
	 */
	public void removeObserver(DominationPhaseObserver observer);
	
	/**
	 * Notify all observers.
	 */
	public void notifyAllObservers(); 
}
