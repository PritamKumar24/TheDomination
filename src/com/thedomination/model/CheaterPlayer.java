package com.thedomination.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

/**
 * The CheaterPlayer Class. implements Strategy and Sereializable Class,
 * Initializes ReinforcementPhase Attack Phase and Fortification Phase.
 *
 * @author Pritam Kumar
 */
public class CheaterPlayer implements Strategy, Serializable {

	/**
	 * /** Generated Serialized Id
	 */
	private static final long serialVersionUID = 1L;
	/** DominationPhase Object */
	private DominationPhase dominationPhase;
	/** WorldDomination Object */
	private WorldDomination worldDomination;
	/** WorldDominationView Object */
	private WorldDominationView worldDominationView;
	/** DominationPhaseView Object */
	private DominationPhaseView dominationPhaseView;
	/** DominationCards Object */
	private DominationCards dominationCard;
	/** DominationCardView Object */
	private DominationCardView dominationCardView;

	/**
	 * Constructor for CheaterPlayer class.
	 * 
	 */
	public CheaterPlayer() {
		dominationPhase=new DominationPhase();
		worldDomination=new WorldDomination();
		worldDominationView=new WorldDominationView(worldDomination);
		dominationPhaseView=new DominationPhaseView(dominationPhase);
		dominationCard = new DominationCards();
		dominationCardView = new DominationCardView(dominationCard);
	}

	/**
	 * Method for reinforcementPhase.
	 * 
	 * @param countryName name of the new country.
	 * @param num         number of Player.
	 * @return Desired Message
	 */
	@Override
	public String reinforcementPhase(String countryName, int num) {

		PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());

		//exchange cards
		//CardOperations.getInstance().setCardExchangeFlag(false);
		System.out.println(CardOperations.getInstance().selfCardExchange(currentPlayer.getCardList()));

	

		System.out.println("Cheater Reinforcement Phase starts - player name " + currentPlayer.getPlayerName());
		System.out.println();
		for (CountryModel countryModel : currentPlayer.getPlayerCountryList()) {
			int armies = countryModel.getNoOfArmiesCountry();
			armies = armies * 2;
			countryModel.setNoOfArmiesCountry(armies);

			//Call to Observer
			PlayerOperations.getInstance().playerWorldDominationStateChange(currentPlayer);
		}

	

		System.out.println("Cheater Reinforcement Phase ends - player name " + currentPlayer.getPlayerName());

		// Trigerring the attack phase
		dominationPhase.setCurrentGamePhase(DominationPhaseType.ATTACK);
		dominationPhase.setCurrentPlayerName(currentPlayer.getPlayerName());
		dominationPhase.setCurrentAction("Starting Attack");


		return "";
	}

	/**
	 * Method for FortificationPhase.
	 * 
	 * @param countrynamefrom name of the country.
	 * @param countrynameto   name of the country.
	 * @param numdice         dice number
	 * 
	 */
	@Override
	public String fortificationPhase(String fromCountry, String toCountry, String num) {

		String message ="";
		PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());
		System.out.println("Cheater Fortification Phase starts - player name " + currentPlayer.getPlayerName());
		System.out.println();

	

		for(CountryModel sourceCountry : currentPlayer.getPlayerCountryList()) {
			for(Integer neighbourPosition: sourceCountry.getListOfNewNeighbours()) {
				CountryModel neighbourCountry = MapOperations.getInstance().getCountryList().get(neighbourPosition-1);
				if(currentPlayer.searchCountry(neighbourCountry.getCountryName()) == null) {
					int armies = sourceCountry.getNoOfArmiesCountry();
					armies = armies * 2;
					sourceCountry.setNoOfArmiesCountry(armies);
				}
			}
		}
		System.out.println();
		System.out.println("Cheater Fortification Phase ends - player name " + currentPlayer.getPlayerName());
	
		PlayerOperations.getInstance().setPlayerCounter(PlayerOperations.getInstance().getPlayerCounter() +1);
		CardOperations.getInstance().setCardExchangeFlag(true);
	
		System.out.println();
		GameDirector gameDirector = new GameDirector();
		GameBuilder gameBuilder = new ConcreteGameBuilder();
		gameDirector.setGameBuilder(gameBuilder);
		gameDirector.buildGame();
		gameDirector.saveGame("AutoSavedGame");
		
		//Call to Card Exchange View
		//Triggering phase view observer		
		dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
		dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
		dominationPhase.setCurrentAction("Starting Card Exchange");

		dominationCard.setPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
		dominationCard.setListCards(CardOperations.getInstance().cardStrings(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getCardList()));



		return "";
	}

	/**
	 * Method for attackPhase.
	 * 
	 * @param countrynamefrom name of the country.
	 * @param countrynameto   name of the country.
	 * @param numdice         dice number
	 * 
	 */
	@Override
	public void attackPhase(String countrynamefrom, String countrynameto, int numdice) {
		PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());
		System.out.println("Cheater Attack Phase starts - player name " + currentPlayer.getPlayerName());
		System.out.println();
		List<CountryModel> attackingCountryList = new ArrayList<CountryModel>(currentPlayer.getPlayerCountryList());


		for(int i =0; i<attackingCountryList.size(); i++) {
			CountryModel attackingCountry = attackingCountryList.get(i);
			for(Integer neighbourPosition: attackingCountry.getListOfNewNeighbours()) {
				CountryModel neighbourCountry = MapOperations.getInstance().getCountryList().get(neighbourPosition-1);
				PlayerModel defender = PlayerOperations.getInstance().returnDefendModel(neighbourCountry.getCountryName());

				if(currentPlayer.searchCountry(neighbourCountry.getCountryName()) == null) {
					if(attackingCountry.getNoOfArmiesCountry()>1) {

						neighbourCountry.setNoOfArmiesCountry(attackingCountry.getNoOfArmiesCountry()/2);
						attackingCountry.setNoOfArmiesCountry(attackingCountry.getNoOfArmiesCountry() - neighbourCountry.getNoOfArmiesCountry());
						System.out.println("Attacking country " + attackingCountry.getCountryName() + " Armies " + attackingCountry.getNoOfArmiesCountry());
						System.out.println("Defending country " + neighbourCountry.getCountryName() + " Armies " + neighbourCountry.getNoOfArmiesCountry());

						//add country to country in player
						currentPlayer.AddCountry(neighbourCountry);

						//remove from defender
						defender.RemoveCountry(neighbourCountry);
						
						//Observer call for attack for countries
						PlayerOperations.getInstance().playerWorldDominationStateChange(currentPlayer);

						//Observer call for defender for countries
						PlayerOperations.getInstance().playerWorldDominationStateChange(defender);
						
						//reset the continent control
						if(neighbourCountry.getBelongsTo().isHasWonContinent()==true) {
							ContinentModel tempContinent = MapOperations.getInstance().searchContinentWithCountryName(neighbourCountry.getCountryName());  
							tempContinent.setHasWonContinent(false);

						}

						//assign cards
						CardOperations.getInstance().assignCard(true, currentPlayer);

						if(defender.getPlayerArmiesInCountries() == 0 && defender.getPlayerCountryList().size() == 0) {
							System.out.println();
							System.out.println("****"+defender.getPlayerName() + " has lost the game."+"****");

							//remove cards of lost player and assign it to current player
							for(CardsModel tempCard : defender.getCardList()) {
								currentPlayer.getCardList().add(tempCard);
							}
							defender.setCardList(null);
	
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
					else if(attackingCountry.getNoOfArmiesCountry()==1) {
						break;
					}
				}

			}
		}
		System.out.println("Cheater Attack Phase Ends - player name " + currentPlayer.getPlayerName());
		System.out.println();
		//Triggering Fortify Phase
				dominationPhase.setCurrentGamePhase(DominationPhaseType.FORTIFY);
				dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
				dominationPhase.setCurrentAction("Starting Fortify");

	}

	/**
	 * Method for allOutAttack.
	 * 
	 * @param countrynamefrom name of the country.
	 * @param countrynameto   name of the country.
	 * 
	 */
	@Override
	public void allOutAttack(String countrynamefrom, String countrynameto) {
	}

	/**
	 * Method for attackMove.
	 * 
	 * @param num number of army.
	 * 
	 */
	@Override
	public void attackMove(int num) {
	}

	/**
	 * Method for defendCountry.
	 * 
	 * @param numdice number of dice.
	 * 
	 */

	@Override
	public void defendCountry(int numdice) {
	}

}
