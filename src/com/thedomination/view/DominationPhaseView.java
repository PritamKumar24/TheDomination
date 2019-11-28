
package com.thedomination.view;

import java.io.Serializable;

import com.thedomination.model.DominationPhaseObservable;
import com.thedomination.model.DominationPhaseType;

/**
 * <h2> Domination Phase View </h2>
 * The Class DominationPhaseView. The phase view displays: 
 * (1) the name of the game phase currently being played 
 * (2) the current player’s name 
 * (3) information about actions that are taking place during this phase.
 *
 * @author Aditi Bhayana
 * @version 2.0
 */
public class DominationPhaseView implements DominationPhaseObserver, Serializable {

	/**
	 * The constant serialVersionUID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/** The current game phase. */
	private DominationPhaseType currentGamePhase;
	
	/** The current player name. */
	private String currentPlayerName;
	
	/** The current action. */
	private String currentAction;
	
	/** The phase observable. */
	private DominationPhaseObservable phaseObservable;
	
	/**
	 * Instantiates a new domination phase view.
	 *
	 * @param phaseObservable the phase observable
	 */
	public DominationPhaseView(DominationPhaseObservable phaseObservable) {
		this.phaseObservable=phaseObservable;
		this.phaseObservable.addObserver(this);
	}

	/**
	  * The update method sets the  currentGamePhase,currentPlayerName,currentAction.
	 * 
	 * @param currentPlayerName name of current player.
	 * @param currentGamePhase current game phase.
	 *  @param currentAction current Action.
	 */
	@Override
	public void update(DominationPhaseType currentGamePhase, String currentPlayerName, String currentAction) {
		this.currentGamePhase = currentGamePhase;
		this.currentPlayerName = currentPlayerName;
		this.currentAction = currentAction;
		showPhaseView();
	}

	/**
	 * Printing Domination Phase View.
	 * with current phase, current player name and current action
	 */
	private void showPhaseView() {
		System.out.println();
		System.out.println("*************************************************************");
		System.out.println("                  "+this.currentGamePhase+" Phase"+"         ");
		System.out.println("*************************************************************");
		System.out.println("Current Player: "+this.currentPlayerName);
		System.out.println("Current Action: "+this.currentAction);
		System.out.println("*************************************************************");
	}

}
