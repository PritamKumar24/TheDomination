package com.thedomination.model;

import java.io.Serializable;

/**
 * The Class CardsModel.
 * Pritam Kumar
 */
public class CardsModel implements Serializable{

  
	/**Id for serilization **/
	private static final long serialVersionUID = 1L;
	/** name of card */
	private String cardName;
	/** type of card */
	private int type;

	/**
	 * constructor for cardsmodel
	 * 
	 * @param name
	 * @param type
	 */
	public CardsModel(String name, int type) {
		this.cardName = name;
		this.type = type;
	}

	/**
	 * return the card name
	 * 
	 * @return card name
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * set the card name
	 * 
	 * @param cardName
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	/**
	 * 
	 * @return type
	 */
	public int getType() {
		return type;
	}

	/**
	 * set the type
	 * 
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * to String to return string form of the object
	 */
	@Override
	public String toString() {
		return "CardsModel [cardName=" + cardName + ", type=" + type + "]";
	}
	
	
}
