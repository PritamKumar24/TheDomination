
package com.thedomination.view;

import com.thedomination.model.DominationPhaseType;

/**
 * <h2> Domination Phase Observer </h2>
 * The interface DominationPhaseObserver. An asynchronous update interface for receiving notifications
 * about DominationPhase information as the DominationPhase is constructed.
 *
 * @author Aditi Bhayana
 * @version 1.0
 */
public interface DominationPhaseObserver {

	/**
	 * This method is called when information about an DominationPhase
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param currentGamePhase the current game phase
	 * @param currentPlayerName the current player name
	 * @param currentAction the current action
	 */
	public void update(DominationPhaseType currentGamePhase, String currentPlayerName, String currentAction);
}
