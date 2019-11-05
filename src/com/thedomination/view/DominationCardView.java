/**
 * 
 */
package com.thedomination.view;


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
public class DominationCardView implements DominationCardObserver{


	private List<String> listCards;
	
	private String playerName;
	
	
	private DominationCardViewObservable cardviewObsevable;
	
	
	public DominationCardView(DominationCardViewObservable cardviewObsevable) {
		this.cardviewObsevable=cardviewObsevable;
		this.cardviewObsevable.addObserver(this);
	}
	
	
	@Override
	public void update(String playerName, List<String> listCards) {
		this.playerName = playerName;
		this.listCards = listCards;

		showCardview();
		
	}

	/**
	 * Show CardExchangeView.
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
