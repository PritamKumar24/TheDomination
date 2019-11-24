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
public class AggressivePlayer implements Strategy, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DominationPhase dominationPhase;
	private WorldDomination worldDomination;
	private WorldDominationView worldDominationView;
	private DominationPhaseView dominationPhaseView;
	private DominationCards dominationCard;
	private DominationCardView dominationCardView;

	public AggressivePlayer() {
		dominationPhase=new DominationPhase();
		worldDomination=new WorldDomination();
		worldDominationView=new WorldDominationView(worldDomination);
		dominationPhaseView=new DominationPhaseView(dominationPhase);
		dominationCard = new DominationCards();
		dominationCardView = new DominationCardView(dominationCard);
	}

	@Override
	public String reinforcementPhase(String countryName, int num) {

		PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());

		//exchange cards
		System.out.println(CardOperations.getInstance().selfCardExchange(currentPlayer.getCardList()));


		//get & set the reinforcement armies to player
		PlayerOperations.getInstance().getReInforcementArmies();
		currentPlayer.setnoOfArmyInPlayer(PlayerOperations.getInstance().getReInforceNoOfArmy());

		System.out.println("Aggresive Reinforcement Phase starts - player name " + currentPlayer.getPlayerName());
		System.out.println();
		if (currentPlayer.getnoOfArmyInPlayer() > 0) {
			CountryModel countryModel = getCountryWithMaxArmies(currentPlayer);
			countryModel.setNoOfArmiesCountry(countryModel.getNoOfArmiesCountry() + currentPlayer.getnoOfArmyInPlayer());
			currentPlayer.setnoOfArmyInPlayer(0);
			System.out.println("Reinforcement done on " + countryModel.getCountryName());

			//Call to Observer
			PlayerOperations.getInstance().playerWorldDominationStateChange(currentPlayer);
		}


		System.out.println("Aggresive Reinforcement Phase ends - player name " + currentPlayer.getPlayerName());

		// Trigerring the attack phase
		dominationPhase.setCurrentGamePhase(DominationPhaseType.ATTACK);
		dominationPhase.setCurrentPlayerName(currentPlayer.getPlayerName());
		dominationPhase.setCurrentAction("Starting Attack");

		return ""; 
	}



	@Override
	public void attackPhase(String countrynamefrom, String countrynameto, int numdice) {
		String message = "";
		PlayerModel tempPlayerModelAttackCountry = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());

		System.out.println("Aggresive Attack Phase starts - player name " + tempPlayerModelAttackCountry.getPlayerName());
		System.out.println();

		while(getCountryWithMaxArmies(tempPlayerModelAttackCountry).getNoOfArmiesCountry()!=1 ) {
			CountryModel attackCountry = getCountryWithMaxArmies(tempPlayerModelAttackCountry);
			int diceAttack[] = new int[attackCountry.getNoOfArmiesCountry() > 3 ? 3 : attackCountry.getNoOfArmiesCountry()-1];
			System.out.println("Attacking country " + attackCountry.getCountryName());
			System.out.println("Attacker dice roll");
			for(int i=0; i<(diceAttack.length);i++) {
				diceAttack[i]=PlayerOperations.getInstance().rollDice();
				System.out.print(diceAttack[i] + " ");
			}
			CountryModel neighbourCountry=null;
			System.out.println();

			diceAttack = PlayerOperations.getInstance().sortArray(diceAttack);

			for(Integer neighbourPosition: attackCountry.getListOfNewNeighbours()) {
				neighbourCountry = MapOperations.getInstance().getCountryList().get(neighbourPosition-1);
				if(tempPlayerModelAttackCountry.searchCountry(neighbourCountry.getCountryName())==null) {
					break;
				}
			}
			PlayerModel defender = PlayerOperations.getInstance().returnDefendModel(neighbourCountry.getCountryName());
			System.out.println("Defending country " + neighbourCountry.getCountryName());
			int diceDefend[]=new int[neighbourCountry.getNoOfArmiesCountry() > 2 ? 2 : neighbourCountry.getNoOfArmiesCountry()];
			System.out.println("Defender dice roll");
			for(int i=0; i<(diceDefend.length);i++) {
				diceDefend[i]=PlayerOperations.getInstance().rollDice();
				System.out.print(diceDefend[i] + " ");
			}
			System.out.println();
			
			diceDefend = PlayerOperations.getInstance().sortArray(diceDefend);

			for(int i = 0;i<(diceDefend.length > diceAttack.length ? diceAttack.length : diceDefend.length);i++) {
				if((diceAttack[i] <= diceDefend[i])) {
					System.out.println("Attacker looses one army");
					attackCountry.setNoOfArmiesCountry(attackCountry.getNoOfArmiesCountry()-1);
					System.out.println(attackCountry.getCountryName() + " has armies " + attackCountry.getNoOfArmiesCountry());

					//Observer call for attack for armies
					PlayerOperations.getInstance().playerWorldDominationStateChange(tempPlayerModelAttackCountry);
				}
				else {
					neighbourCountry.setNoOfArmiesCountry(neighbourCountry.getNoOfArmiesCountry()-1);
					System.out.println("Defender looses one army");
					System.out.println(neighbourCountry.getCountryName() + " has armies " + neighbourCountry.getNoOfArmiesCountry());

					//Observer call for defender for armies
					PlayerOperations.getInstance().playerWorldDominationStateChange(defender);
				}
			}
			System.out.println();
			if(neighbourCountry.getNoOfArmiesCountry() == 0) {
				System.out.println("Attacker has won " + neighbourCountry.getCountryName());
				neighbourCountry.setNoOfArmiesCountry(diceAttack.length);
				attackCountry.setNoOfArmiesCountry(attackCountry.getNoOfArmiesCountry()-diceAttack.length);
				System.out.println("Armies on " + attackCountry.getCountryName() + " " + attackCountry.getNoOfArmiesCountry());
				System.out.println("Armies on " + neighbourCountry.getCountryName() + " " + neighbourCountry.getNoOfArmiesCountry());

				//remove country
				CountryModel removeCountry = defender.RemoveCountry(neighbourCountry);

				//add country
				tempPlayerModelAttackCountry.getPlayerCountryList().add(removeCountry);

				//Observer call for attack for armies
				PlayerOperations.getInstance().playerWorldDominationStateChange(tempPlayerModelAttackCountry);

				//Observer call for defender for countries
				PlayerOperations.getInstance().playerWorldDominationStateChange(defender);

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
		System.out.println("The player cannot attack");
		System.out.println();
		System.out.println("Aggresive Attack Phase Ends - player name " + tempPlayerModelAttackCountry.getPlayerName());

		if(PlayerOperations.getInstance().getPlayersList().size()!=1) {
		//Triggering Fortify Phase
		dominationPhase.setCurrentGamePhase(DominationPhaseType.FORTIFY);
		dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
		dominationPhase.setCurrentAction("Starting Fortify");
		}

	}

	@Override
	public String fortificationPhase(String fromCountry, String toCountry, String num) {

		String message ="";
		PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());

		System.out.println("Aggresive Fortification Phase starts - player name " + currentPlayer.getPlayerName());
		System.out.println();
		if(currentPlayer.getPlayerCountryList().size() < 2) {
			System.out.println("Fortification Not possible");
			System.out.println();
			System.out.println("Aggresive Fortification Phase Ends - player name " + currentPlayer.getPlayerName());
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

			
			return message;
		}

		for(int i=0; i<currentPlayer.getPlayerCountryList().size(); i++) {
			CountryModel tempCountry = currentPlayer.getPlayerCountryList().get(i);
			if(tempCountry.getNoOfArmiesCountry()>1) {
				break;
			}
			else if(i == currentPlayer.getPlayerCountryList().size()-1) {
				System.out.println("Not enough armies for fortification ");
				System.out.println();
				System.out.println("Aggresive Fortification Phase Ends - player name " + currentPlayer.getPlayerName());

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


				return message;
			}
		}
		CountryModel maxArmyCountry = getCountryWithMaxArmies(currentPlayer);

		for(CountryModel neighbourCountry : currentPlayer.getPlayerCountryList()) {
			if(!(neighbourCountry.getCountryName().equalsIgnoreCase(maxArmyCountry.getCountryName())) 
					
					&& MapOperations.getInstance().searchNeighbourCountry(maxArmyCountry.getCountryName(), neighbourCountry.getCountryPosition())!=null) {

				maxArmyCountry.setNoOfArmiesCountry(maxArmyCountry.getNoOfArmiesCountry()+(neighbourCountry.getNoOfArmiesCountry()-1));
				neighbourCountry.setNoOfArmiesCountry(1);
				System.out.println("Fortification Done from " + maxArmyCountry.getCountryName() + " to " + neighbourCountry.getCountryName()); 
				System.out.println();
				System.out.println("Aggresive Fortification Phase Ends - player name " + currentPlayer.getPlayerName());
				
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


				return message;
			}	
		}
		System.out.println("No country found for fortification");
		System.out.println();
		System.out.println("Aggresive Fortification Phase Ends - player name " + currentPlayer.getPlayerName());

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



	@Override
	public void allOutAttack(String countrynamefrom, String countrynameto) {
	}

	@Override
	public void attackMove(int num) {
	}

	@Override
	public void defendCountry(int numdice) {
	}

	private CountryModel getCountryWithMaxArmies(PlayerModel playerModel) {
		CountryModel countryModel = playerModel.getPlayerCountryList().get(0);
		int noOfArmies = countryModel.getNoOfArmiesCountry();
		for (CountryModel country : playerModel.getPlayerCountryList()) {
			if (country.getNoOfArmiesCountry() > noOfArmies && checkNeighboursContainCount(country)!=country.getListOfNewNeighbours().size()) {
				countryModel = country;
				noOfArmies = country.getNoOfArmiesCountry();
			}			
		}
		return countryModel;
	}

	public int checkNeighboursContainCount(CountryModel attackCountry) {
		PlayerModel tempPlayerModelAttackCountry = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());
		int count=0;
		for(Integer neighbourPosition: attackCountry.getListOfNewNeighbours()) {
			CountryModel neighbourCountry = MapOperations.getInstance().getCountryList().get(neighbourPosition-1);
			if(tempPlayerModelAttackCountry.searchCountry(neighbourCountry.getCountryName()) != null) {
				count++;
			}
		}
		return count;
	}

}
