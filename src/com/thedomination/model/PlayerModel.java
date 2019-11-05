	package com.thedomination.model;


	import java.util.ArrayList;
	import java.util.List;

	/**
	 * The Class PlayerModel.
	 *
	 * @author Aditi
	 * @version 1.0.0
	 */
	public class PlayerModel {

	/** The player country list. */
	private List<CountryModel> playerCountryList;

	/** The player name. */
	private String playerName;

	/** The no of army for player. */
	private int noOfArmyInPlayer;
	
	/** The hasWonTerritory */
	private boolean hasWonTerritory;
	
	private ArrayList<CardsModel> cardList;

	/**
	 * PlayerModel Constructor Instantiates a new player model.
	 *
	 * @param playerName the player name
	 */
	public PlayerModel(String playerName) {
		this.playerName = playerName;
		this.playerCountryList = new ArrayList<>();
		cardList = new ArrayList<CardsModel>();
	}

	/**
	 * PlayerModel Constructor Instantiates a new player model.
	 *
	 * @param playerName            the player name
	 * @param countryModelArrayList array list of coutries of the player.
	 */
	public PlayerModel(String playerName, ArrayList<CountryModel> countryModelArrayList) {
		this.playerName = playerName;
		this.playerCountryList = new ArrayList<>();
		this.playerCountryList.addAll(countryModelArrayList);
	}

	/**
	 * noOfArmyinPlayer Method No of army for player.
	 *
	 * @param number the number
	 */
	public void setnoOfArmyInPlayer(int number) {
		this.noOfArmyInPlayer = number;
	}

	/**
	 * Reduce army for player.
	 */
	public void reduceArmyInPlayer() {
		this.noOfArmyInPlayer--;
	}

	/**
	 * Adds the army in player.
	 */
	public void addArmyInPlayer() {
		this.noOfArmyInPlayer++;
	}
    /**
	* addControlValueToNoOfArmy method to add controlValue to army.
	* @param controlValue Integer controlValue to be added.
	*/
	public void addControlValueToNoOfArmy(int controlValue) {
		this.noOfArmyInPlayer = this.noOfArmyInPlayer + controlValue;
	}

	/**
	 * Gets the no of army for player.
	 *
	 * @return the no of army for player
	 */
	public int getnoOfArmyInPlayer() {
		return noOfArmyInPlayer;
	}

	/**
	 * getPlayerName Method Gets the player name.
	 *
	 * @return the player name
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * setPlayerName Method Sets the player name.
	 *
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	/**
	*isHasWonTerritory method to check the winning territory.
	*/
	public boolean isHasWonTerritory() {
		return hasWonTerritory;
	}

	/*
	*setHasWonTerritory method to set if any player won territory.
	* @param hasWonTerritory boolean either true or false.
	*/
	public void setHasWonTerritory(boolean hasWonTerritory) {
		this.hasWonTerritory = hasWonTerritory;
	}
	/**
	*getCardList method to get cardList of card Model type.
	*@return cardList ArrayList of CardList.
	*/
	public ArrayList<CardsModel> getCardList() {
		return cardList;
	}
	/**
	*setCardList setter method to set the parameterized cardList.
	*@param cardList ArrayList of cardList of cardModel type.
	*/
	public void setCardList(ArrayList<CardsModel> cardList) {
		this.cardList = cardList;
	}

	/**
	 * AddCountry Method Adds the country.
	 *
	 * @param countryName the country name
	 */
	public void AddCountry(CountryModel countryName) {
		playerCountryList.add(countryName);

	}

	/**
	 * Removes the country.
	 */
	public CountryModel RemoveCountry(CountryModel countryName) {
		playerCountryList.remove(countryName);
		return countryName;
	}

	/**
	 * Gets the player country list.
	 *
	 * @return the player country list
	 */
	public List<CountryModel> getPlayerCountryList() {
		return playerCountryList;
	}

	/**
	 * Search a country by the country Name.
	 *
	 * @param countryName : Name of the country to be searched.
	 * @return Returns the Country Model.
	 */

	public CountryModel searchCountry(String countryName) {
		for (CountryModel country : playerCountryList) {
			if (country.getCountryName().trim().equalsIgnoreCase(countryName.trim())) {
				return country;
			}
		}
		return null;
	}
	/**
	* searchCard method to search a card.
	*@param cardName card name to be searched.
	*@return CardsModel type object of card found.
	*
	*/
	public CardsModel searchCard(String cardName) {
		for (CardsModel card : cardList) {
			if (card.getCardName().trim().equalsIgnoreCase(cardName.trim())) {
				return card;
			}
		}
		return null;
	}
	
	//method returns 
	/**
	* getInHandCard method to get a card a player owns.
	* @param cardPosition Position of card.
	* @return the card at given position.
	*/
	public CardsModel getInHandCard(int cardPosition) {
		if(cardPosition>0 && cardPosition<=cardList.size()) {
			int j = cardPosition-1;
			CardsModel card = cardList.get(j);
			if(card!=null) {
				return card;
			}
		}
		return null;
	}
	/**
	* addCard method to add cards.
	*@param card card to be added.
	*/
	public void addCard(CardsModel card) {
			cardList.add(card);
	}
	
//	public void removeCards(int firstPosition, int secondPosition, int thirdPosition) {
//		System.out.println("During removal position "+ firstPosition+ " "+secondPosition+" "+thirdPosition);
//		int[] removePositions = {firstPosition, secondPosition, thirdPosition}; 
//		System.out.println("Array created for removal "+removePositions.toString());
//		int j=0;
//		for(int i=0; i<3;i++) {
//			CardsModel card = getInHandCard(removePositions[j]);
//			System.out.println("card which is going to be removed "+ card);
//			cardList.remove(card);
//			j++;
//			System.out.println("value of j "+j);
//		}
//	}
	/**
	*removeCards method to remove cards.
	* @param firstCard card at first position.
	* @param secondCard card at second position.
	* @param thirdCard card at third position.
	*/
	public void removeCards(CardsModel firstCard, CardsModel secondCard, CardsModel thirdCard) {
		CardsModel[] removeCards = {firstCard, secondCard, thirdCard}; 
		int j=0;
		for(int i=0; i<3;i++) {
			CardsModel card = searchCard(removeCards[j].getCardName());
			cardList.remove(card);
			j++;
		}
	}
	
	/**
	*showCards method to show cards.
	*/
	  public void showCards() {
		  int i = 1;
		  for(CardsModel tempCard: cardList) {
			  System.out.print("At position "+i+" "+tempCard.getCardName());
			  System.out.println();
			  i++;
		  }
		  
	  }
  
	
//	@Override
//	public String toString() {
//		return "PlayerModel [playerCountryList=" + playerCountryList + ", playerName=" + playerName
//				+ ", noOfArmyInPlayer=" + noOfArmyInPlayer + "]";
//	}

}
