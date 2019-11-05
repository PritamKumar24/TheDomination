package com.thedomination.model;

import java.util.ArrayList;
import java.util.List;
import com.thedomination.view.DominationCardObserver;


/**
 * The Class CardsModel.
 * @author Pritam Kumar
 */
public class DominationCards implements DominationCardViewObservable {
	
	List<String> listCards;
	
	List<DominationCardObserver> cardObserver;
	
	String playerName;

	public List<String> getListCards() {
		return listCards;
	}

	public void setListCards(List<String> listCards) {
		this.listCards = listCards;
		notifyAllObservers();
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public DominationCards() {
		listCards = new ArrayList<String>();
		cardObserver = new ArrayList<DominationCardObserver>();
	}

	@Override
	public void addObserver(DominationCardObserver observer) {
		cardObserver.add(observer);
		
	}

	@Override
	public void removeObserver(DominationCardObserver observer) {
		cardObserver.remove(observer);
		
	}

	@Override
	public void notifyAllObservers() {
		for (DominationCardObserver tempCardObserver : cardObserver) {
			tempCardObserver.update(playerName , listCards);
		}
		
	}
	
	

}
