package com.thedomination.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


import com.thedomination.model.CardsModel;
import com.thedomination.model.PlayerModel;
import com.thedomination.model.Strategy;
import com.thedomination.model.DominationCards;
import com.thedomination.model.WorldDomination;
import com.thedomination.model.DominationPhase;
import com.thedomination.model.DominationPhaseType;
import com.thedomination.model.HumanPlayer;
import com.thedomination.view.DominationCardView;
import com.thedomination.view.WorldDominationView;
import com.thedomination.view.DominationPhaseView;

/**
 * The Class CardOperations.
 * CardOperation class to manage the card Operations.
 * @author Pritam Kumar
 */
public class CardOperations implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**CardDeck ArrayList*/
	ArrayList<CardsModel> cardDeck;

	/**The randomCard */
	private Random randomCard;

	/**The cardNoOfArmy */
	private int cardNoOfArmy;

	/**The cardCounter */
	private int cardCounter;

	public boolean cardExchangeFlag = false;

	//private HumanPlayer humanPlayer;

	private static CardOperations cardOperationInstance;

	/**
	 * getInstance method to make object of cardOperations Class.
	 * 
	 * @return object of cardOpeartion.
	 */
	public static CardOperations getInstance() {
		if(CardOperations.cardOperationInstance == null) {
			CardOperations.cardOperationInstance = new CardOperations();
		}
		return CardOperations.cardOperationInstance;
	}

	DominationPhase dominationPhase;

	WorldDomination worldDomination;

	WorldDominationView worldDominationView;

	DominationPhaseView dominationPhaseView;

	DominationCards dominationCards;

	DominationCardView dominationCardView;
	/**
	 * CardOperations constructor of cardOperations class.
	 */
	private CardOperations() {
		randomCard = new Random();
		cardDeck = new ArrayList<CardsModel>();
		//humanPlayer = new HumanPlayer();
		cardCreation();

		dominationPhase=new DominationPhase();
		worldDomination=new WorldDomination();
		worldDominationView=new WorldDominationView(worldDomination);
		dominationPhaseView=new DominationPhaseView(dominationPhase);
		dominationCards = new DominationCards();
		dominationCardView = new DominationCardView(dominationCards);
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
		//if (cardDeck.size() > 0) {
		int index = randomCard.nextInt(cardDeck.size());
		CardsModel randomCard = cardDeck.get(index);
		//System.out.println(newCard);
		return randomCard;
		//		} 
		//		else
		//			return null;
	}

	/**
	 * assignCard method to assign cards to players.
	 * 
	 * @param hasWonTerritory boolean either true or false.
	 * @param player object of playerModel to which card is to be assigned.
	 */
	public void assignCard(boolean hasWonTerritory, PlayerModel player) {
		if(hasWonTerritory) {

			if(cardDeck.size()>0 ) {
				CardsModel randomCard = generateRandomCard();
				player.addCard(randomCard);
				//remove from the deck code
				cardDeck.remove(randomCard);
			}

		}
	}

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
			PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());

			if(firstPosition.equalsIgnoreCase("-none")) {
				if(currentPlayer.getCardList().size()>=5) {
					message="Number of cards is 5 or more, You must exchange your cards";
					return message;
				}
				else {
					System.out.println("Player choose not to exchange cards");

					//PlayerOperations.getInstance().setReinforceArmyFlag(true);
					//PlayerOperations.getInstance().setReinforceFlag(true);

					//HumanPlayer humanPlayer = (HumanPlayer) currentPlayer.getStrategy();

					//Flags
					cardNoOfArmy=0;
					cardExchangeFlag = false;

					PlayerOperations.getInstance().setReinforceArmyFlag(true);
					PlayerOperations.getInstance().setReinforceFlag(true);

					//Moving to normal reinforcement
					dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
					dominationPhase.setCurrentPlayerName(currentPlayer.getPlayerName());
					dominationPhase.setCurrentAction("Starting Reinforcement");

//
//					//Setting the Game Model
//					System.out.println();
//					System.out.println("Would you like to save the Game? If Yes, please use the appropriate command");
//					GameModel.getInstance().setGamePhaseStage(1);
//					GameModel.getInstance().setCurrentPlayer(currentPlayer);
//					GameModel.getInstance().setPlayerCounter(PlayerOperations.getInstance().getPlayerCounter());
//					GameModel.getInstance().setCardCounter(cardCounter);
//					GameModel.getInstance().setCardDeck(cardDeck);
//					GameModel.getInstance().reinforceArmyFlag = true;
//					GameModel.getInstance().reinforceFlag = true;
//					GameModel.getInstance().cardExchangeFlag = false;

					return message;
				}
			}

			if (currentPlayer.getCardList().size()<3) {
				message="You don't have enough cards to exchange. Please use the >>exchangecards -none<< command";
				return message;
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
				System.out.println("Armies got as card exchange "+cardNoOfArmy);
				System.out.println("Card Exchange Done!");

				currentPlayer.removeCards(firstCard,secondCard, thirdCard);
				addCards(firstCard, secondCard, thirdCard);
			}
			else {
				message = "Cards entered should be all identical or all different, TRY AGAIN!!";
				return message;
			}
			//Before moving to reinforcement phase again check if the current player's card list size is more than 5 or not
			if(currentPlayer.getCardList().size()>=5) {
				message="Number of cards is 5 or more, You must exchange your cards";
				return message;
			}
		}
		else {
			message = "Illegal Move";
		}

		return message;
	}

	public String selfCardExchange(ArrayList<CardsModel> cardList) {
		PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());

		String message = "";
		//List<String> stringList = cardStrings(cardList);
		//Collections.sort(stringList);
		//System.out.println(cardList.toString());

		if(cardList.size() > 3) {
		//	System.out.println("Cards you have "+ CardOperations.getInstance().cardStrings(currentPlayer.getCardList()));
			Collections.sort(cardList, new SortCards());

			for(int i=0 ; i<cardList.size()-2 ; i++) {
				CardsModel firstCard = currentPlayer.getCardList().get(i);
				CardsModel secondCard = currentPlayer.getCardList().get(i+1);
				CardsModel thirdCard = currentPlayer.getCardList().get(i+2);

				if(firstCard==null || secondCard==null || thirdCard ==null) {
					message = "The player doesn't have the card(s) in hand to exchange";
					cardNoOfArmy=0;
					cardExchangeFlag = false;
					PlayerOperations.getInstance().setReinforceFlag(true);
					return message;
				}


				if(checkSameCards(firstCard.getCardName(), secondCard.getCardName(), thirdCard.getCardName()) || 
						checkDifferentCards(firstCard.getCardName(), secondCard.getCardName(), thirdCard.getCardName())) {

					cardCounter++;

					cardNoOfArmy = 5*cardCounter;
					System.out.println("Armies got as card exchange "+cardNoOfArmy);

					currentPlayer.removeCards(firstCard,secondCard, thirdCard);
					addCards(firstCard, secondCard, thirdCard);
					System.out.println("Self Card Exchange Done!");
					cardExchangeFlag = false;
					PlayerOperations.getInstance().setReinforceFlag(true);

					//Moving to normal reinforcement
					dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
					dominationPhase.setCurrentPlayerName(currentPlayer.getPlayerName());
					dominationPhase.setCurrentAction("Starting Reinforcement");
//
//					//Setting the Game Model, Write code for autosave
//					System.out.println();
//					GameModel.getInstance().setGamePhaseStage(1);
//					GameModel.getInstance().setCurrentPlayer(currentPlayer);
//					GameModel.getInstance().setPlayerCounter(PlayerOperations.getInstance().getPlayerCounter());
//					GameModel.getInstance().setCardCounter(cardCounter);
//					GameModel.getInstance().setCardDeck(cardDeck);
//					GameModel.getInstance().reinforceArmyFlag = true;
//					GameModel.getInstance().reinforceFlag = true;
//					GameModel.getInstance().cardExchangeFlag = false;
					break;
				}	
			}	
		}
		else {
			//System.out.println("Cards you have "+ CardOperations.getInstance().cardStrings(currentPlayer.getCardList()));
			System.out.println(currentPlayer.getPlayerName()+" player doesn't have enough no of cards to exchange!! ");

			cardNoOfArmy=0;
			cardExchangeFlag = false;
			PlayerOperations.getInstance().setReinforceFlag(true);

			//Moving to normal reinforcement
			dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
			dominationPhase.setCurrentPlayerName(currentPlayer.getPlayerName());
			dominationPhase.setCurrentAction("Starting Reinforcement");

			//Setting the Game Model, Write code for autosave
//			System.out.println();
//			GameModel.getInstance().setGamePhaseStage(1);
//			GameModel.getInstance().setCurrentPlayer(currentPlayer);
//			GameModel.getInstance().setPlayerCounter(PlayerOperations.getInstance().getPlayerCounter());
//			GameModel.getInstance().setCardCounter(cardCounter);
//			GameModel.getInstance().setCardDeck(cardDeck);
//			GameModel.getInstance().reinforceArmyFlag = true;
//			GameModel.getInstance().reinforceFlag = true;
//			GameModel.getInstance().cardExchangeFlag=false;

		}
		return "";
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
		if(firstCard.equalsIgnoreCase("Infantry") && secondCard.equalsIgnoreCase("Infantry") && thirdCard.equalsIgnoreCase("Infantry") ) {
			return true;
		}
		else if (firstCard.equalsIgnoreCase("Cavalry") && secondCard.equalsIgnoreCase("Cavalry") && thirdCard.equalsIgnoreCase("Cavalry")) {

			return true;
		}
		else if(firstCard.equalsIgnoreCase("Artillery") && secondCard.equalsIgnoreCase("Artillery") && thirdCard.equalsIgnoreCase("Artillery")) {

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
		PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());

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


	public CardsModel deleteCard(int type) {
		CardsModel card = searchCard(type);
		if (cardDeck.remove(card)) {
			return card;
		}
		return null;
	}

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

	public List<String> cardStrings(List<CardsModel> listCards){

		List<String> stringList = new ArrayList<>();

		for(CardsModel tempCard: listCards) {
			stringList.add(tempCard.getCardName());
		}
		return stringList;
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

	public void setCardExchangeFlag(boolean cardExchangeFlag) {
		this.cardExchangeFlag = cardExchangeFlag;
	}

	public ArrayList<CardsModel> getCardDeck() {
		return cardDeck;
	}

	public void setCardDeck(ArrayList<CardsModel> cardDeck) {
		this.cardDeck = cardDeck;
	}

	public int getCardCounter() {
		return cardCounter;
	}

	public void setCardCounter(int cardCounter) {
		this.cardCounter = cardCounter;
	}

	public static void setCardOperationInstance(CardOperations cardOperationInstance) {
		CardOperations.cardOperationInstance = cardOperationInstance;
	}

	

	public void clear() {
		this.cardCounter=0;
		this.cardDeck.clear();
		this.cardNoOfArmy=0;
		this.cardExchangeFlag = false;
	}	

}


class SortCards implements Comparator<CardsModel>{
	@Override
	public int compare(CardsModel firstCard, CardsModel secondCard) {
		return firstCard.getCardName().compareTo(secondCard.getCardName());
	}

}
