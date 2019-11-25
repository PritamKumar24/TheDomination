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
	 * Generated Serialized Id
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

}
