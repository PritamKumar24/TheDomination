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
