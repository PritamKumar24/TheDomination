package com.thedomination.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.thedomination.view.DominationCardObserver;


/**
 * The Class CardsModel.
 * @author Pritam Kumar
 */
public class DominationCards implements DominationCardViewObservable, Serializable {
	
	/** Generated Serilaized Id */
	private static final long serialVersionUID = 1L;
	/**list of cards*/
	List<String> listCards;
	/** list of DominationCardObserver */
	List<DominationCardObserver> cardObserver;
	/** player name */
	String playerName;
  /**
   * 
   * @return list of cards
   */
	public List<String> getListCards() {
		return listCards;
	}

	
	/**
	 * set list of cards
	 * @param listCards
	 */
	
	public void setListCards(List<String> listCards) {
		this.listCards = listCards;
		notifyAllObservers();
	}
	
	/**
	 * 
	 * @return playername
	 */

	public String getPlayerName() {
		return playerName;
	}
     /**
      * set player name
      * @param playerName
      */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * constructor for domination cards
	 */
	public DominationCards() {
		listCards = new ArrayList<String>();
		cardObserver = new ArrayList<DominationCardObserver>();
	}

	/**
	 * adding the observer
	 */
	@Override
	public void addObserver(DominationCardObserver observer) {
		cardObserver.add(observer);
		
	}

	/**
	 * removing the observer
	 */
	@Override
	public void removeObserver(DominationCardObserver observer) {
		cardObserver.remove(observer);
		
	}
	/**
	 * notifying all the observers
	 */

	@Override
	public void notifyAllObservers() {
		for (DominationCardObserver tempCardObserver : cardObserver) {
			tempCardObserver.update(playerName , listCards);
		}
		
	}
	
	

}
