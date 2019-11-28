package com.thedomination.builder;

import java.io.Serializable;

import com.thedomination.controller.CardOperations;
import com.thedomination.controller.MapOperations;
import com.thedomination.controller.PlayerOperations;
import com.thedomination.model.DominationCards;
import com.thedomination.model.DominationPhase;
import com.thedomination.model.DominationPhaseType;
import com.thedomination.model.PlayerModel;
import com.thedomination.model.WorldDomination;
import com.thedomination.view.DominationCardView;
import com.thedomination.view.DominationPhaseView;
import com.thedomination.view.WorldDominationView;

/**
 * The class ConcreteGameBuilder builds the concrete objects of MapOperations
 * and PlayerOperations and CardOperations.
 * 
 * @author Pritam Kumar
 *
 */
public class ConcreteGameBuilder implements GameBuilder, Serializable {

	/**
	 * The constant serialVersionUID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/** The playerOperations */
	private PlayerOperations playerOperations;

	/** The mapOperations */
	private MapOperations mapOperations;

	/** The cardOperations */
	private CardOperations cardOperations;

	/** The GameBuilder */
	private ConcreteGameBuilder GameBuilder;

	/** The dominationPhase */
	private DominationPhase dominationPhase;

	/** The worldDomination */
	private WorldDomination worldDomination;

	/** The worldDominationView */
	private WorldDominationView worldDominationView;

	/** The dominationPhaseView */
	private DominationPhaseView dominationPhaseView;

	/** The dominationCard */
	private DominationCards dominationCard;

	/** The dominationCardView */
	private DominationCardView dominationCardView;

	/**
	 * The constructor for ConcreteGameBuilder class.
	 */
	public ConcreteGameBuilder() {
		dominationPhase = new DominationPhase();
		worldDomination = new WorldDomination();
		worldDominationView = new WorldDominationView(worldDomination);
		dominationPhaseView = new DominationPhaseView(dominationPhase);
		dominationCard = new DominationCards();
		dominationCardView = new DominationCardView(dominationCard);
	}

	/**
	 * buildCardOperations set the concrete object in getInstance. 
	 */
	@Override
	public void buildCardOperations() {
		this.cardOperations = CardOperations.getInstance();

	}

	/**
	 * buildPlayerOperations set the concrete object in getInstance. 
	 */
	@Override
	public void buildPlayerOperations() {
		this.playerOperations = PlayerOperations.getInstance();

	}

	/**
	 * buildMapOperations set the concrete object in getInstance. 
	 */
	@Override
	public void buildMapOperations() {
		this.mapOperations = MapOperations.getInstance();

	}

	/**
	 * The restartGame method restarts the game.
	 * 
	 * @param gameBuilderConcrete of ConcreteGameBuilder type.
	 * @return String 
	 */
	@Override
	public String reStartGame(ConcreteGameBuilder gameBuilderConcrete) {
		String message = "";
		if (gameBuilderConcrete == null) {
			message = "Doesn't have anything saved";
			return message;
		}

		else if (gameBuilderConcrete.cardOperations.isCardExchangeFlag() == true) {
			dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
			dominationPhase.setCurrentPlayerName(gameBuilderConcrete.playerOperations
					.currentPlayer(gameBuilderConcrete.playerOperations.getPlayerCounter()).getPlayerName());
			dominationPhase.setCurrentAction("Starting Card Exchange");

			dominationCard.setPlayerName(gameBuilderConcrete.playerOperations
					.currentPlayer(gameBuilderConcrete.playerOperations.getPlayerCounter()).getPlayerName());
			dominationCard
			.setListCards(gameBuilderConcrete.cardOperations.cardStrings(gameBuilderConcrete.playerOperations
					.currentPlayer(gameBuilderConcrete.playerOperations.getPlayerCounter()).getCardList()));
			PlayerOperations.getInstance().gameEngine();
		}

		else if (gameBuilderConcrete.playerOperations.reinforceArmyFlag == true) {
			dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
			dominationPhase.setCurrentPlayerName(gameBuilderConcrete.playerOperations
					.currentPlayer(gameBuilderConcrete.playerOperations.getPlayerCounter()).getPlayerName());
			dominationPhase.setCurrentAction("Staring Reinforcement");
			PlayerOperations.getInstance().gameEngine();
		} else if (gameBuilderConcrete.playerOperations.attackFlag == true) {
			dominationPhase.setCurrentGamePhase(DominationPhaseType.ATTACK);
			dominationPhase.setCurrentPlayerName(gameBuilderConcrete.getPlayerOperations()
					.currentPlayer(gameBuilderConcrete.getPlayerOperations().getPlayerCounter()).getPlayerName());
			dominationPhase.setCurrentAction("Starting Attack");
			PlayerOperations.getInstance().gameEngine();
		}

		else if (gameBuilderConcrete.playerOperations.fortifyArmyFlag == true) {
			dominationPhase.setCurrentGamePhase(DominationPhaseType.FORTIFY);
			dominationPhase.setCurrentPlayerName(gameBuilderConcrete.playerOperations
					.currentPlayer(gameBuilderConcrete.playerOperations.getPlayerCounter()).getPlayerName());
			dominationPhase.setCurrentAction("Starting Fortify");
			PlayerOperations.getInstance().gameEngine();
		}

		return "";
	}


	/**
	 * getPlayerOperations getter method to get PlayerOperations.
	 * 
	 */
	@Override
	public PlayerOperations getPlayerOperations() {
		return playerOperations;
	}

	/**
	 * setPlayerOperations setter method to set PlayerOperations
	 * 
	 * @param playerOperations of PlayerOperations type.
	 */
	public void setPlayerOperations(PlayerOperations playerOperations) {
		this.playerOperations = playerOperations;
	}

	/**
	 * getMapOperations getter method to get MapOperations
	 */
	@Override
	public MapOperations getMapOperations() {
		return mapOperations;
	}

	/**
	 * setMapOperations setter method to set MapOperations
	 * 
	 * @param mapOperations of MapOperations type.
	 */
	public void setMapOperations(MapOperations mapOperations) {
		this.mapOperations = mapOperations;
	}

	/**
	 * getCardOperations getter method to get cardOperations
	 */
	@Override
	public CardOperations getCardOperations() {
		return cardOperations;
	}

	/**
	 * setCardOperations setter method to set CardOperations
	 * 
	 * @param cardOperations of CardOperations type.
	 */
	public void setCardOperations(CardOperations cardOperations) {
		this.cardOperations = cardOperations;
	}

}
