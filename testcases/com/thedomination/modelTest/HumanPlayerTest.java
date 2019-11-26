package com.thedomination.modelTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.thedomination.controller.MapOperations;
import com.thedomination.controller.PlayerOperations;
import com.thedomination.model.CountryModel;
import com.thedomination.model.HumanPlayer;
import com.thedomination.model.PlayerModel;

/**
 * The attackTest class.
 * 
 * @author Piyush
 *
 */
class HumanPlayerTest {
	/** The indiaarmies */
	boolean indiaarmies;

	/** The chinaarmies */
	boolean chinaarmies;
	
	

	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}


	/**
	 * The attackTesting test case is used for testing the valid move of armies after conquering.
	 */
	@Test
	void attackTesting() {
		MapOperations.getInstance().loadMap("domination.map");

		CountryModel countryIndia = MapOperations.getInstance().searchCountry("India");  

		countryIndia.setNoOfArmiesCountry(20);

		CountryModel countryChina = MapOperations.getInstance().searchCountry("China");

		countryChina.setNoOfArmiesCountry(1);
		CountryModel countryNigeria = MapOperations.getInstance().searchCountry("Nigeria");

		countryNigeria.setNoOfArmiesCountry(10);

		//System.out.println(countryIndia.getNoOfArmiesCountry());


		PlayerOperations.getInstance().addPlayer("Ravi", "Human");
		PlayerModel currentPlayer = PlayerOperations.getInstance().searchPlayer("Ravi");
		currentPlayer.AddCountry(countryIndia);

		PlayerOperations.getInstance().addPlayer("Kishan", "Human");
		PlayerModel currentPlayer1 = PlayerOperations.getInstance().searchPlayer("Kishan");
		currentPlayer1.AddCountry(countryChina);
		currentPlayer1.AddCountry(countryNigeria);



		HumanPlayer humanPlayer = (HumanPlayer) currentPlayer.getStrategy();
		PlayerOperations.getInstance().setAttackFlag(true);
		PlayerOperations.getInstance().setMoveArmyFlag(true);



		HumanPlayer humanPlayer1 = (HumanPlayer) currentPlayer1.getStrategy();

		PlayerOperations.getInstance().setAttackFlag(true);
		PlayerOperations.getInstance().setMoveArmyFlag(true);

		PlayerOperations.getInstance().allOutAttack("India", "China");

		PlayerOperations.getInstance().attackMove(3);
		assertEquals(3,PlayerOperations.getInstance().getDiceAttackArray().length);
	}
	
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp1() throws Exception {
		MapOperations.getInstance().loadMap("domination.map");

		CountryModel countryIndia = MapOperations.getInstance().searchCountry("India");  

		countryIndia.setNoOfArmiesCountry(20);

		CountryModel countryChina = MapOperations.getInstance().searchCountry("China");

		countryChina.setNoOfArmiesCountry(3);
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown1() throws Exception {
	}
	/**
	 * attackerDefenderValidationTesting test case to check attacker defender validations.
	 */
	@Test
	void attackerDefenderValidationTesting() {
		MapOperations.getInstance().loadMap("domination.map");

		CountryModel countryIndia = MapOperations.getInstance().searchCountry("India");  

		countryIndia.setNoOfArmiesCountry(20);

		CountryModel countryChina = MapOperations.getInstance().searchCountry("China");

		countryChina.setNoOfArmiesCountry(3);

		int position =countryChina.getCountryPosition();

		assertNotNull(MapOperations.getInstance().searchNeighbourCountry("India",position));



		PlayerOperations.getInstance().addPlayer("Ravi", "Human");
		PlayerModel currentPlayer = PlayerOperations.getInstance().searchPlayer("Ravi");
		currentPlayer.AddCountry(countryIndia);

		PlayerOperations.getInstance().addPlayer("Kishan", "Human");
		PlayerModel currentPlayer1 = PlayerOperations.getInstance().searchPlayer("Kishan");
		currentPlayer1.AddCountry(countryChina);




		assertEquals("India",currentPlayer.getPlayerCountryList().get(0).getCountryName() );
		assertEquals("China",currentPlayer1.getPlayerCountryList().get(0).getCountryName() );


		int numberofArmiesOfIndia=countryIndia.getNoOfArmiesCountry();
		if(numberofArmiesOfIndia>1) {

			indiaarmies=true;}

		int numberofArmiesOfChina=countryChina.getNoOfArmiesCountry();
		if(numberofArmiesOfChina>1) {

			chinaarmies=true;}

		assertTrue(indiaarmies);
		assertTrue(chinaarmies);
	}
	

}
