package com.thedomination.model;

import java.io.Serializable;
import java.util.Random;

import com.thedomination.builder.ConcreteGameBuilder;
import com.thedomination.builder.GameBuilder;
import com.thedomination.builder.GameDirector;
import com.thedomination.controller.CardOperations;
import com.thedomination.controller.MapOperations;
import com.thedomination.controller.PlayerOperations;
import com.thedomination.view.DominationCardView;
import com.thedomination.view.DominationPhaseView;
import com.thedomination.view.WorldDominationView;
import com.thedomination.controller.TournamentController;
public class RandomPlayer implements Strategy, Serializable {


	/** Generated Serilaized Id */
	private static final long serialVersionUID = 1L;
	/** DominationPhase  Object*/
	private DominationPhase dominationPhase;
	/** WorldDomination  Object*/
	private WorldDomination worldDomination;
	/** WorldDominationView  Object*/
	private WorldDominationView worldDominationView;
	/** DominationPhaseView  Object*/
	private DominationPhaseView dominationPhaseView;
	/** DominationCards  Object*/
	private DominationCards dominationCard;
	/** DominationCardView  Object*/
	private DominationCardView dominationCardView;
	
	/**
	 * Constructor for RandomPlayer class.
	 * 
	 */

	public RandomPlayer() {
		dominationPhase=new DominationPhase();
		worldDomination=new WorldDomination();
		worldDominationView=new WorldDominationView(worldDomination);
		dominationPhaseView=new DominationPhaseView(dominationPhase);
		dominationCard = new DominationCards();
		dominationCardView = new DominationCardView(dominationCard);
	}

	/**
	 * Method for reinforcementPhase.
	 * @param countryName name of the new country.
	 * @param num number of Player.
	 * @return Desired Message
	 * 	 */
	@Override
	public String reinforcementPhase(String countryName, int num) {

		PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());
		Random random = new Random();

		//exchange cards
		System.out.println(CardOperations.getInstance().selfCardExchange(currentPlayer.getCardList()));

		//get reinforcement no of armies and assign these armies to player
		PlayerOperations.getInstance().getReInforcementArmies();
		currentPlayer.setnoOfArmyInPlayer(PlayerOperations.getInstance().getReInforceNoOfArmy());

		System.out.println("Random Reinforcement Phase Starts - player name " + currentPlayer.getPlayerName());
		System.out.println();
		while (currentPlayer.getnoOfArmyInPlayer() > 0) {
			int randomArmies = 1+random.nextInt(currentPlayer.getnoOfArmyInPlayer());
			int index = random.nextInt(currentPlayer.getPlayerCountryList().size());
			CountryModel countryModel = currentPlayer.getPlayerCountryList().get(index);
			System.out.println("Reinforcement Armies " +randomArmies+ " placed on " + countryModel.getCountryName());
			countryModel.setNoOfArmiesCountry(countryModel.getNoOfArmiesCountry() + randomArmies);
			currentPlayer.setnoOfArmyInPlayer(currentPlayer.getnoOfArmyInPlayer()-randomArmies);

			//Call to Observer
			PlayerOperations.getInstance().playerWorldDominationStateChange(currentPlayer);
		}

		System.out.println("Random Reinforcement Phase Ends - player name " + currentPlayer.getPlayerName());

		// Trigerring the attack phase
		dominationPhase.setCurrentGamePhase(DominationPhaseType.ATTACK);
		dominationPhase.setCurrentPlayerName(currentPlayer.getPlayerName());
		dominationPhase.setCurrentAction("Starting Attack");

		return "";
	}


	/**
	 * Method for attackPhase.
	 * @param countrynamefrom name of the country.
	 * @param countrynameto name of the country.
	 * @param numdice dice number
	 * 
	 * */
	@Override
	public void attackPhase(String countrynamefrom, String countrynameto, int numdice) {

		PlayerModel tempPlayerModelAttackCountry = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());
		System.out.println("Random Attack Phase Starts - player name " + tempPlayerModelAttackCountry.getPlayerName());

		PlayerModel defender;
		Random random = new Random();
		int noOfTimeToAttack = random.nextInt(tempPlayerModelAttackCountry.getPlayerCountryList().size());
		System.out.println("Number of Attacks " + noOfTimeToAttack);
		System.out.println();
		for (int i=0; i<noOfTimeToAttack; i++) {
			int countryIndex = random.nextInt(tempPlayerModelAttackCountry.getPlayerCountryList().size());
			CountryModel sourceCountry = tempPlayerModelAttackCountry.getPlayerCountryList().get(countryIndex);
			if (sourceCountry.getNoOfArmiesCountry() > 1) {
				for (Integer neighbour : sourceCountry.getListOfNewNeighbours()) {
					CountryModel neighbourCountry = MapOperations.getInstance().getCountryList().get(neighbour-1);
					if(tempPlayerModelAttackCountry.searchCountry(neighbourCountry.getCountryName()) == null) {
						int attackDiceValue = PlayerOperations.getInstance().rollDice();
						int defenderDiceValue = PlayerOperations.getInstance().rollDice();
						defender = PlayerOperations.getInstance().returnDefendModel(neighbourCountry.getCountryName());
						if (attackDiceValue > defenderDiceValue) {
							neighbourCountry.setNoOfArmiesCountry(neighbourCountry.getNoOfArmiesCountry()-1);
							System.out.println(sourceCountry.getCountryName() + " attacks on " + neighbourCountry.getCountryName());
							System.out.println(neighbourCountry.getCountryName() + " looses one army");
							System.out.println(neighbourCountry.getCountryName() + " has armies " + neighbourCountry.getNoOfArmiesCountry());

							//call of obeserver for defender
							PlayerOperations.getInstance().playerWorldDominationStateChange(defender);


							if(neighbourCountry.getNoOfArmiesCountry() == 0) {
								System.out.println("Attacker has won " + neighbourCountry.getCountryName());

								neighbourCountry.setNoOfArmiesCountry(1);
								sourceCountry.setNoOfArmiesCountry(sourceCountry.getNoOfArmiesCountry()-1);

								System.out.println("Armies on " + sourceCountry.getCountryName() + " " + sourceCountry.getNoOfArmiesCountry());
								System.out.println("Armies on " + neighbourCountry.getCountryName() + " " + neighbourCountry.getNoOfArmiesCountry());

								//remove country
								CountryModel removeCountry = defender.RemoveCountry(neighbourCountry);

								//add country
								tempPlayerModelAttackCountry.getPlayerCountryList().add(removeCountry);

								//call of obeserver for defender
								PlayerOperations.getInstance().playerWorldDominationStateChange(defender);

								//Call of Observer for attacker
								PlayerOperations.getInstance().playerWorldDominationStateChange(tempPlayerModelAttackCountry);

								//reset the continent control
								if(removeCountry.getBelongsTo().isHasWonContinent()==true) {
									ContinentModel tempContinent = MapOperations.getInstance().searchContinentWithCountryName(removeCountry.getCountryName());  
									tempContinent.setHasWonContinent(false);

								}

								//assign card
								CardOperations.getInstance().assignCard(true, tempPlayerModelAttackCountry);

								if(defender.getPlayerArmiesInCountries() == 0 && defender.getPlayerCountryList().size() == 0) {
									System.out.println();
									System.out.println("****"+defender.getPlayerName() + " has lost the game."+"****");

									//remove cards of lost player and assign it to current player
									for(CardsModel tempCard : defender.getCardList()) {
										tempPlayerModelAttackCountry.getCardList().add(tempCard);
									}
									defender.setCardList(null);
									//remove from game
									//lostPlayers.add(defender.getPlayerName());
									PlayerOperations.getInstance().setLostPlayers(defender.getPlayerName());
									if(PlayerOperations.getInstance().getPlayerCounter() > PlayerOperations.getInstance().getPlayersList().indexOf(defender)+1) {
										PlayerOperations.getInstance().setPlayerCounter(PlayerOperations.getInstance().getPlayerCounter() -1);
									}
									PlayerOperations.getInstance().getPlayersList().remove(defender);
								}
								if(PlayerOperations.getInstance().getPlayersList().size()==1) {
									System.out.println();
									System.out.println("*****"+PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName()+" HAS WON THE GAME" +"*****");
									System.out.println();
									System.out.println("******* GAME END ************");
									if(TournamentController.getInstance().tournamentFlag == false)
									System.exit(0);
									return;
								}

							}

						}
						else {
							System.out.println(sourceCountry.getCountryName() + " attack on " + neighbourCountry.getCountryName());
							System.out.println("Attacker looses one army");
							sourceCountry.setNoOfArmiesCountry(sourceCountry.getNoOfArmiesCountry()-1);
							System.out.println(sourceCountry.getCountryName() + " has armies " + sourceCountry.getNoOfArmiesCountry());
							
							//Call of Observer for attacker
							PlayerOperations.getInstance().playerWorldDominationStateChange(tempPlayerModelAttackCountry);

						}
					}
					else {
						continue;
					}
					break;
				}
			}
			else if(sourceCountry.getNoOfArmiesCountry() == 1) {
				System.out.println("Cannot attack from country " + sourceCountry.getCountryName());
			}	
		}
		System.out.println();
		System.out.println("Random Attack Phase Ends - player name " + tempPlayerModelAttackCountry.getPlayerName());

		//Triggering Fortify Phase
		dominationPhase.setCurrentGamePhase(DominationPhaseType.FORTIFY);
		dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
		dominationPhase.setCurrentAction("Starting Fortify");
	}
	
	/**
	 * Method for allOutAttack.
	 * @param countrynamefrom name of the country.
	 * @param countrynameto name of the country.
	 * 
	 * */
	@Override
	public void allOutAttack(String countrynamefrom, String countrynameto) {
	}
	/**
	 * attackMove method to make a moving attack.
	 * @param num number of attacking armies.
	 */
	@Override
	public void attackMove(int num) {
	}

	/**
	 * method to defend
	 * @param num of dice
	 */
	@Override
	public void defendCountry(int numdice) {
	}
	/**
	 * Method for FortificationPhase.
	 * @param countrynamefrom name of the country.
	 * @param countrynameto name of the country.
	 * @param numdice dice number
	 * 
	 * */
	@Override
	public String fortificationPhase(String fromCountry, String toCountry, String num) {

		String message ="";
		PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());

		System.out.println("Random Fortification Phase Starts - player name " + currentPlayer.getPlayerName());
		System.out.println();
		if(currentPlayer.getPlayerCountryList().size() < 2) {
			System.out.println("Fortification Not possible");
			System.out.println();
			System.out.println("Random Fortification Phase Ends - player name " + currentPlayer.getPlayerName());

			PlayerOperations.getInstance().setPlayerCounter(PlayerOperations.getInstance().getPlayerCounter() +1);
			CardOperations.getInstance().setCardExchangeFlag(true);

			System.out.println();
			GameDirector gameDirector = new GameDirector();
			GameBuilder gameBuilder = new ConcreteGameBuilder();
			gameDirector.setGameBuilder(gameBuilder);
			gameDirector.buildGame();
			gameDirector.saveGame("AutoSavedGame");
			
			
			//Triggering phase view observer		
			dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
			dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
			dominationPhase.setCurrentAction("Starting Card Exchange");
			
			//Call to Card Exchange View
			dominationCard.setPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
			dominationCard.setListCards(CardOperations.getInstance().cardStrings(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getCardList()));

	

			return message;
		}

		for(int i=0; i<currentPlayer.getPlayerCountryList().size(); i++) {
			CountryModel tempCountry = currentPlayer.getPlayerCountryList().get(i);
			if(tempCountry.getNoOfArmiesCountry()>1 && isNeighbourExists(tempCountry)!=null) {
				break;
			}
			else if(i == currentPlayer.getPlayerCountryList().size()-1) {
				System.out.println("Not enough armies for fortification ");
				System.out.println();
				System.out.println("Random Fortification Phase Ends - player name " + currentPlayer.getPlayerName());

				PlayerOperations.getInstance().setPlayerCounter(PlayerOperations.getInstance().getPlayerCounter() +1);
				CardOperations.getInstance().setCardExchangeFlag(true);

				System.out.println();
				GameDirector gameDirector = new GameDirector();
				GameBuilder gameBuilder = new ConcreteGameBuilder();
				gameDirector.setGameBuilder(gameBuilder);
				gameDirector.buildGame();
				gameDirector.saveGame("AutoSavedGame");
				
				
				//Triggering phase view observer		
				dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
				dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
				dominationPhase.setCurrentAction("Starting Card Exchange");

				//Call to Card Exchange View
				dominationCard.setPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
				dominationCard.setListCards(CardOperations.getInstance().cardStrings(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getCardList()));

				return message;
			}
		}

		CountryModel sourceCountry = null;
		CountryModel neighbourCountry = null;

		do {
			Random random = new Random();
			int index = random.nextInt(currentPlayer.getPlayerCountryList().size());
			sourceCountry = currentPlayer.getPlayerCountryList().get(index);
			neighbourCountry = isNeighbourExists(sourceCountry);
			if( sourceCountry.getNoOfArmiesCountry()>1 && neighbourCountry!=null)
				break;
		}
		while(true);

		neighbourCountry.setNoOfArmiesCountry(neighbourCountry.getNoOfArmiesCountry()+sourceCountry.getNoOfArmiesCountry()-1);
		sourceCountry.setNoOfArmiesCountry(1);
		System.out.println("Fortification Done from " + sourceCountry.getCountryName() + " to " + neighbourCountry.getCountryName());
		System.out.println();
		System.out.println("Random Fortification Phase Ends - player name " + currentPlayer.getPlayerName());

		PlayerOperations.getInstance().setPlayerCounter(PlayerOperations.getInstance().getPlayerCounter() +1);
		CardOperations.getInstance().setCardExchangeFlag(true);

		System.out.println();
		GameDirector gameDirector = new GameDirector();
		GameBuilder gameBuilder = new ConcreteGameBuilder();
		gameDirector.setGameBuilder(gameBuilder);
		gameDirector.buildGame();
		gameDirector.saveGame("AutoSavedGame");
		
		//Triggering phase view observer		
		dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
		dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
		dominationPhase.setCurrentAction("Starting Card Exchange");

		//Call to Card Exchange View
		dominationCard.setPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
		dominationCard.setListCards(CardOperations.getInstance().cardStrings(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getCardList()));

		return message;
	}

	/**
	 * check oif neighbour exist for the country
	 * @param country
	 * @return countryModel
	 */
	public CountryModel isNeighbourExists(CountryModel country) {

		PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());
		for(CountryModel neighbourCountry : currentPlayer.getPlayerCountryList()) {
			if(MapOperations.getInstance().searchNeighbourCountry(country.getCountryName(), neighbourCountry.getCountryPosition())!=null){
				return neighbourCountry;
			}
		}
		return null;
	}

}
