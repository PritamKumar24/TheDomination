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


	private static final long serialVersionUID = 1L;

	private DominationPhase dominationPhase;
	private WorldDomination worldDomination;
	private WorldDominationView worldDominationView;
	private DominationPhaseView dominationPhaseView;
	private DominationCards dominationCard;
	private DominationCardView dominationCardView;

	public RandomPlayer() {
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




	
	@Override
	public void allOutAttack(String countrynamefrom, String countrynameto) {
	}

	@Override
	public void attackMove(int num) {
	}

	@Override
	public void defendCountry(int numdice) {
	}



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
