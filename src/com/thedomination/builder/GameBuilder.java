package com.thedomination.builder;

import java.io.Serializable;

import com.thedomination.controller.CardOperations;
import com.thedomination.controller.MapOperations;
import com.thedomination.controller.PlayerOperations;
import com.thedomination.model.PlayerModel;

/**
 * The GameBuilder Interface extends Serializable.
 * 
 * @author Pritam Kumar
 *
 */
public interface GameBuilder extends Serializable {

	/** The buildCardOperations */
	public void buildCardOperations();

	/** The buildPlayerOperations */
	public void buildPlayerOperations();

	/** The buildPlayerOperations */
	public void buildMapOperations();

	/**
	 * The reStartGame method to restart game.
	 * 
	 * @param gameBuilder of ConcreteGameBuilder type.
	 * @return String.
	 */
	public String reStartGame(ConcreteGameBuilder gameBuilder);

	/** The buildPlayerOperations 
	 * 
	 * @return object of PlayerOperations.
	 */
	public PlayerOperations getPlayerOperations();
	
	/** The buildPlayerOperations 
	 * 
	 * @return object of MapOperations.
	 * */
	public MapOperations getMapOperations();

	/** The buildPlayerOperations
	 * 
	 * @return object of CardOperations. 
	 */
	public CardOperations getCardOperations();

}
