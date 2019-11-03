package com.thedomination.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import com.thedomination.model.CardsModel;
import com.thedomination.model.PlayerModel;

/**
 * The Class CardOperations.
 * @author Pritam Kumar
 */
public class CardOperations {

	ArrayList<CardsModel> cardDeck;

	private Random randomCard;

	private int cardNoOfArmy;

	private int cardCounter;

	private boolean cardExchangeFlag = true;

	private static CardOperations UniqueInstance;

	public static CardOperations getInstance() {
		if(CardOperations.UniqueInstance == null) {
			CardOperations.UniqueInstance = new CardOperations();
		}
		return CardOperations.UniqueInstance;
	}

	private CardOperations() {
		randomCard = new Random();
		cardDeck = new ArrayList<CardsModel>();
		cardCreation();
	}


	private void cardCreation() {
		String[] names = { "Infantry", "Cavalry", "Artillery" };
		int[] types = { 1, 2, 3 };
		int j = 0;
		for (int i = 0; i < MapOperations.getInstance().getCountryList().size(); i++) {
			CardsModel card = new CardsModel(names[j], types[j]);
			cardDeck.add(card);
			j++;
			if(j==3) {
				j = 0;
			}
		}
	}

	public CardsModel generateRandomCard() {
		if (cardDeck.size() > 0) {
			int index = randomCard.nextInt(cardDeck.size());
			CardsModel randomCard = cardDeck.get(index);
			//System.out.println(newCard);
			return randomCard;
		} else
			return null;
	}

	public void assignCard(boolean hasWonTerritory, PlayerModel player) {
		if(hasWonTerritory) {
			CardsModel randomCard = generateRandomCard();
			player.addCard(randomCard);
			//remove from the deck code
			cardDeck.remove(randomCard);
		}
	}

	
}
