package com.thedomination.model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * The Class CardsModel.
 * Pritam Kumar
 */
public class CardsModel extends Observable {

	private String cardName;

	private int type;


	public CardsModel(String name, int type) {
		this.cardName = name;
		this.type = type;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


	private void updateChanges() {
		setChanged();
		notifyObservers(this);
	}

	@Override
	public String toString() {
		return "CardsModel [cardName=" + cardName + ", type=" + type + "]";
	}
	
	
}
