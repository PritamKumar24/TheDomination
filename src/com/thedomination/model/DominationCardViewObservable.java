/**
 * 
 */
package com.thedomination.model;

import com.thedomination.view.DominationCardObserver;

/**
 * <h2> Domination Card View Observable</h2> 
 * The Interface DominationCardViewObservable. An asynchronous update interface for receiving notifications
 * about DominationCardview information as the DominationCardview is constructed.
 *
 * @author Aditi Bhayana
 * @version 2.0
 */
public interface DominationCardViewObservable {
	
	/**
	 * Adds the observer.
	 *
	 * @param observer the observer
	 */
	public void addObserver(DominationCardObserver observer);
	
	/**
	 * Removes the observer.
	 *
	 * @param observer the observer
	 */
	public void removeObserver(DominationCardObserver observer);
	
	/**
	 * Notify all observers.
	 */
	public void notifyAllObservers(); 
}
