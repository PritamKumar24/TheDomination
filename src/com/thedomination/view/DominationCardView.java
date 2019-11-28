/**
 * 
 */
package com.thedomination.view;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thedomination.model.CardsModel;
import com.thedomination.model.DominationCardViewObservable;

/**
 * <h2> Domination Card View class </h2>
 *
 * @author Pritam Kumar
 * @version 2.0
 */
public class DominationCardView implements DominationCardObserver, Serializable{


	/**
	 * The constant serialVersionUID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/** The List of listCards */
	private List<String> listCards;
	
	/** The playerName */
	private String playerName;
	
	/** The cardviewObsevable */
	private DominationCardViewObservable cardviewObsevable;
	
	/**
	 * Parameterized constructor of DominationCardView class.
	 * 
	 * @param cardviewObsevable of DominationCardViewObservable type.
	 */
	public DominationCardView(DominationCardViewObservable cardviewObsevable) {
		this.cardviewObsevable=cardviewObsevable;
		this.cardviewObsevable.addObserver(this);
	}
	
	
	/**
	 * The update method to set playerName and listCards.
	 * 
	 * @param playerName name of player 
	 * @param listCards list of cards.
	 * 
	 * 
	 */
	@Override
	public void update(String playerName, List<String> listCards) {
		this.playerName = playerName;
		this.listCards = listCards;

		showCardview();
		
	}

	/**
	 * Show CardExchangeView.
	 * with player's name and list of cards he or she has
	 * 
	 */
	private void showCardview() {
		System.out.println();
		System.out.println("*************************************************************");
		System.out.println("*                  Card Exchange View                       *");
		System.out.println("*************************************************************");
		System.out.println("Player's Name: "+this.playerName);
		System.out.println("Cards in hand: "+this.listCards);
		System.out.println("*************************************************************");
	}



}
