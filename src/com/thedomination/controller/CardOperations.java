package com.thedomination.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import com.thedomination.model.CardsModel;
import com.thedomination.model.PlayerModel;

/**
 * The Class CardOperations.
 * CardOperation class to manage the card Operations.
 * @author Pritam Kumar
 */
public class CardOperations {
	
	/**CardDeck ArrayList*/
	ArrayList<CardsModel> cardDeck;
	
/**The randomCard */
	private Random randomCard;
	
	/**The cardNoOfArmy */
	private int cardNoOfArmy;
	
	/**The cardCounter */
	private int cardCounter;
	
	/**The cardExchangeFlag */
	private boolean cardExchangeFlag = true;
	
	/**The object of CardOperations */
	private static CardOperations UniqueInstance;
	
/**
 * getInstance method to make object of cardOperations Class.
 * 
 * @return object of cardOpeartion.
 */
	public static CardOperations getInstance() {
		if(CardOperations.UniqueInstance == null) {
			CardOperations.UniqueInstance = new CardOperations();
		}
		return CardOperations.UniqueInstance;
	}
	
/**
 * CardOperations constructor of cardOperations class.
 */
	private CardOperations() {
		randomCard = new Random();
		cardDeck = new ArrayList<CardsModel>();
		cardCreation();
	}

/**
 * cardCreation method to create new cards.
 */
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
	
/**
 * generateRandomCard method to generate the random cards.
 * @return object of the cardsModel the random card generated.
 */
	public CardsModel generateRandomCard() {
		if (cardDeck.size() > 0) {
			int index = randomCard.nextInt(cardDeck.size());
			CardsModel randomCard = cardDeck.get(index);
			//System.out.println(newCard);
			return randomCard;
		} else
			return null;
	}
	
/**
 * assignCard method to assign cards to players.
 * 
 * @param hasWonTerritory boolean either true or false.
 * @param player object of playerModel to which card is to be assigned.
 */
	public void assignCard(boolean hasWonTerritory, PlayerModel player) {
		if(hasWonTerritory) {
			CardsModel randomCard = generateRandomCard();
			player.addCard(randomCard);
			//remove from the deck code
			cardDeck.remove(randomCard);
		}
	}

	//	public String exchangeCards(String firstType, int secondType, int thirdType) {
	//		PlayerModel tempPlayerModel = PlayerOperations.getInstance().getCurrentReinforcementPlayer();
	//		String message="";
	//		if(firstType.equalsIgnoreCase("-none")) {
	//			message="Player choose not to exchange cards";
	//			//code to exit the card exchange view using observer pattern
	//			//write code to check if more the 5 cards then can't simple use -none he must exchange cards
	//			return message;
	//		}
	//		if(tempPlayerModel.searchCard(Integer.parseInt(firstType))==null || tempPlayerModel.searchCard(secondType)==null || tempPlayerModel.searchCard(thirdType) ==null) {
	//			return "The player doesn't have the card(s) entered";
	//		}
	//			//calculate no of reinforcement armies without cards
	//			PlayerOperations.getInstance().getReInforcementArmies();
	//			cardNoOfArmy=cardNoOfArmy+5;
	//			System.out.println("Card Armies player got "+cardNoOfArmy);
	//			int reinforceArmies = PlayerOperations.getInstance().getReInforceNoOfArmy();
	//			PlayerOperations.getInstance().setReInforceNoOfArmy(reinforceArmies+cardNoOfArmy);
	//			System.out.println("Reforcement armies after card exchange "+ PlayerOperations.getInstance().getReInforceNoOfArmy());
	//			System.out.println(">>> Before card exchange Player "+tempPlayerModel);
	//			//remove cards from player's card list
	//			tempPlayerModel.removeCards(Integer.parseInt(firstType),secondType, thirdType);
	//			//add the cards in the deck
	//			addCards(Integer.parseInt(firstType), secondType, thirdType);
	//			System.out.println(">>> After card exchange player "+tempPlayerModel);
	//
	//		return "";
	//	}

	/**
	 * exchangeCards method to exchange the cards between the players. 
	 * @param firstPosition first card of player
	 * @param secondPosition second card of player
	 * @param thirdPosition third card of player
	 * @return appropriate message.
	 */
	public String exchangeCards(String firstPosition, int secondPosition, int thirdPosition) {
		String message="";
		if(cardExchangeFlag) {
			PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getAttackCountryCounter());
		if(firstPosition.equalsIgnoreCase("-none")) {
			if(currentPlayer.getCardList().size()>=5) {
				message="Number of cards is 5 or more, You must exchange your cards";
				return message;
			}
			else {
				message="Player choose not to exchange cards";
				cardExchangeFlag = false;
				//code to exit the card exchange view using observer pattern
				//write code to check if more the 5 cards then can't simple use -none he must exchange cards
				return message;
			}
		}

		CardsModel firstCard  = currentPlayer.getInHandCard(Integer.parseInt(firstPosition.trim()));
		CardsModel secondCard = currentPlayer.getInHandCard(secondPosition);
		CardsModel thirdCard  = currentPlayer.getInHandCard(thirdPosition);


		if(firstCard==null || secondCard==null || thirdCard ==null) {
			message = "The player doesn't have the card(s) entered in hand, please check the cards and try again";
			return message;
		}

		
			if(checkSameCards(firstCard.getCardName(), secondCard.getCardName(), thirdCard.getCardName()) || 
					checkDifferentCards(firstCard.getCardName(), secondCard.getCardName(), thirdCard.getCardName())) {

				cardCounter++;

				cardNoOfArmy = 5*cardCounter;
				//calculate no of reinforcement armies without cards
				//PlayerOperations.getInstance().getReInforcementArmies();
				//cardNoOfArmy=cardNoOfArmy+5;
				System.out.println("Armies got as card exchange "+cardNoOfArmy);

				//	if(PlayerOperations.getInstance().isReinforceFlag()) {
				//		int reinforceArmies = PlayerOperations.getInstance().getReInforceNoOfArmy();
				//		PlayerOperations.getInstance().setReInforceNoOfArmy(reinforceArmies+cardNoOfArmy);
				//	}
				//	System.out.println("Reforcement armies after card exchange "+ PlayerOperations.getInstance().getReInforceNoOfArmy());
				System.out.println(">>> Before card exchange cards were ");
				currentPlayer.showCards();
				//remove cards from player's card list
				//	System.out.println("Cards positions "+ firstPosition +" "+ secondPosition +" "+thirdPosition);
				currentPlayer.removeCards(firstCard,secondCard, thirdCard);
				//add the cards in the deck
				addCards(firstCard, secondCard, thirdCard);
				System.out.println(">>> After card exchange cards are ");
				currentPlayer.showCards();
			}
			else {
				message = "Cards entered should be all identical or all different, TRY AGAIN!!";
				return message;
			}
			//before moving to reinforcement phase again check if the current player's card list size is more than 5 or not
			if(currentPlayer.getCardList().size()>=5) {
				message="Number of cards is 5 or more, You must exchange your cards";
				return message;
			}
			else if (currentPlayer.getCardList().size()<3) {
				cardExchangeFlag = false;
			}
		}
		else {
			message = "Illegal Move";
		}
		
		return message;
	}

 /**
 * checkSameCards method to check if player has same cards.
 * 
 * @param firstCard of player
 * @param secondCard secondCard of player.
 * @param thirdCard thirdCard of player.
 * @return true or false accordingly.
 */
	public boolean checkSameCards(String firstCard, String secondCard, String thirdCard) {
		System.out.println("In the same check");
		if(firstCard.equalsIgnoreCase("Infantry") && secondCard.equalsIgnoreCase("Infantry") && thirdCard.equalsIgnoreCase("Infantry") ) {
			System.out.println("Infantry true");
			return true;
		}
		else if (firstCard.equalsIgnoreCase("Cavalry") && secondCard.equalsIgnoreCase("Cavalry") && thirdCard.equalsIgnoreCase("Cavalry")) {
			System.out.println("Cavalry true");
			return true;
		}
		else if(firstCard.equalsIgnoreCase("Artillery") && secondCard.equalsIgnoreCase("Artillery") && thirdCard.equalsIgnoreCase("Artillery")) {
			System.out.println("Artillery true");
			return true;
		}

		return false;
	}

/**
 * checkDifferentCards checks the different cards.
 * 
 * @param firstCard of player
 * @param secondCard secondCard of player.
 * @param thirdCard thirdCard of player.
 * @return true or false accordingly.
 * @return
 */
	public boolean checkDifferentCards(String firstCard, String secondCard, String thirdCard) {
		ArrayList<String> checkList = new ArrayList<String>();
		checkList.add("Infantry");
		checkList.add("Cavalry");
		checkList.add("Artillery");

		String[] checkArray = {firstCard,secondCard,thirdCard};

		for(int i=0; i<3;i++) {
			if(checkList.contains(checkArray[i])) {
				checkList.remove(checkArray[i]);
			}
		}

		if(checkList.size()==0) {
			return true;
		}
		return false;
	}

/**
 * searchCard method to search cards.
 * 
 * @param cardType cardtype to be searched.
 * @return object of card if found.
 */
	public CardsModel searchCard(int cardType) {
		for (CardsModel tempCard : cardDeck) {
			if (tempCard.getType() == cardType) {
				return tempCard;
			}
		}
		return null;
	}
	
/**
 * showPlayerCards method to show players cards.
 */
	public void showPlayerCards() {
		//PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getReInforceCountryCounter());
		PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getAttackCountryCounter());

		currentPlayer.showCards();

	}
	
/**
 * cardDisplay method to display players cards.
 */
	public void cardDisplay() {
		for(CardsModel tempCard : cardDeck) {
			System.out.println(tempCard);
		}
		System.out.println("Totol no of cards "+cardDeck.size());
	}

	/**
	 * transferAllCards method to transfer the cards between attacker and defender.
	 * 
	 * @param attacker object of playerModel.
	 * @param defender object of PlayerModel.
	 * @return appropriate message.
	 */
	public String transferAllCards(PlayerModel attacker, PlayerModel defender) {
		ArrayList<CardsModel> defenderCardList = defender.getCardList();
		attacker.setCardList(defenderCardList);
		defender.setCardList(null);
		return "Defender's all cards has been transferred";
	}

	//not in use now
	//	public void deleteCards(int firstType, int secondType, int thirdType) {
	//		int[] removeTypes = {firstType, secondType, thirdType}; 
	//		int j=0;
	//		for(int i=0; i<3;i++) {
	//			CardsModel card = searchCard(removeTypes[j]);
	//			cardDeck.remove(card);
	//			j++;
	//		}
	//	}

	// I have to delete this method as this is for testing purpose because i can simply use cardDeck.remove(card) to remove from deck
	public CardsModel deleteCard(int type) {
		CardsModel card = searchCard(type);
		if (cardDeck.remove(card)) {
			return card;
		}
		return null;
	}

	//	public void addCards(int firstType, int secondType, int thirdType) {
	//		int[] addTypes = {firstType, secondType, thirdType}; 
	//		int j=0;
	//		for(int i=0; i<3;i++) {
	//			CardsModel card = searchCard(addTypes[j]);
	//			cardDeck.add(card);
	//			j++;
	//		}
	//	}
	
	/**
	 * addCards method to add the cards.
	 * @param firstCard first card of player to be added
	 * @param secondCard secondCard of player to be added
	 * @param thirdCard thirdCard of player to be added
	 */
	public void addCards(CardsModel firstCard, CardsModel secondCard, CardsModel thirdCard) {
		cardDeck.add(firstCard);
		cardDeck.add(secondCard);
		cardDeck.add(thirdCard);
	}
	
/**
 * getCardNoOfArmy getter method to get the card number of army.
 * 
 * @return card number.
 */
	public int getCardNoOfArmy() {
		return cardNoOfArmy;
	}
	
/**
 * setCardNoOfArmy setter Method to set the card number of army.
 * 
 * @param cardNoOfArmy Integer value of the card number to be set.
 */
	public void setCardNoOfArmy(int cardNoOfArmy) {
		this.cardNoOfArmy = cardNoOfArmy;
	}
	
/**
 * isCardExchangeFlag method to check whether the card is exchanged or not.
 * @return true or false.
 */
	public boolean isCardExchangeFlag() {
		return cardExchangeFlag;
	}
	
	

	//	public void testingAddCards() {
	//		PlayerModel tempPlayerModel = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getReInforceCountryCounter());
	//
	//		CardsModel c1 = new CardsModel("Infantry", 1);
	//		tempPlayerModel.addCard(c1);
	//		System.out.println("Card added in player and removed from deck "+ deleteCard(c1.getType()));
	//
	//		CardsModel c2 = new CardsModel("Cavalry", 2);
	//		tempPlayerModel.addCard(c2);
	//		System.out.println("Card added in player and removed from deck "+ deleteCard(c2.getType()));
	//
	//		CardsModel c3 = new CardsModel("Artillery", 3);
	//		tempPlayerModel.addCard(c3);
	//		System.out.println("Card added in player and removed from deck "+ deleteCard(c3.getType()));
	//
	//		CardsModel c4 = new CardsModel("Artillery", 3);
	//		tempPlayerModel.addCard(c4);
	//		System.out.println("Card added in player and removed from deck "+ deleteCard(c4.getType()));
	//
	//		CardsModel c5 = new CardsModel("Infantry", 1);
	//		tempPlayerModel.addCard(c5);
	//		System.out.println("Card added in player and removed from deck "+ deleteCard(c5.getType()));
	//
	//	}

}
