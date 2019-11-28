package com.thedomination.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thedomination.controller.PlayerOperations;
import com.thedomination.view.DominationPhaseObserver;


/**
 * <h2> Domination Phase </h2>
 * The Class DominationPhase.The phase view displays: 
 * (1) the name of the game	phase currently being played 
 * (2) the current player’s name 
 * (3) information about actions that are taking place during this phase.
 *
 * @author Manpreet Singh
 * @version 1.0
 */
public class DominationPhase implements DominationPhaseObservable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The current game phase. */
	private DominationPhaseType currentGamePhase;
	
	/** The current player name. */
	private String currentPlayerName;
	
	/** The current action. */
	private String currentAction;
	
	/** The domination phase observers. */
	private List<DominationPhaseObserver> dominationPhaseObservers;
	
	
	/**
	 * Instantiates a new domination phase.
	 */
	public DominationPhase() {
		dominationPhaseObservers=new ArrayList<DominationPhaseObserver>();
	}	
	
	/**
	 * Sets the current game phase.
	 *
	 * @param currentGamePhase the currentGamePhase to set
	 */
	public void setCurrentGamePhase(DominationPhaseType currentGamePhase) {
		this.currentGamePhase = currentGamePhase;
	}


	/**
	 * Sets the current player name.
	 *
	 * @param currentPlayerName the currentPlayerName to set
	 */
	public void setCurrentPlayerName(String currentPlayerName) {
		this.currentPlayerName = currentPlayerName;
	}


	/**
	 * setting current action and calling notifyAllObservers method of Observable .
	 *
	 * @param currentAction the currentAction to set
	 */
	public void setCurrentAction(String currentAction) {
		this.currentAction = currentAction;
		//PlayerOperations.getInstance().setStartup_phaseFlag("true");
		notifyAllObservers();
	}

/**
 * adding the observer
 */
	@Override
	public void addObserver(DominationPhaseObserver observer) {
		dominationPhaseObservers.add(observer);
		
	}
	
/**
 * removing the observer
 */
	@Override
	public void removeObserver(DominationPhaseObserver observer) {
		dominationPhaseObservers.remove(observer);
		
	}

	/**
	 * notifying all observer
	 */
	@Override
	public void notifyAllObservers() {
		for (DominationPhaseObserver dominationPhaseObserver : dominationPhaseObservers) {
			dominationPhaseObserver.update(this.currentGamePhase, this.currentPlayerName, this.currentAction);
		}
		
	}
}
