package com.thedomination.modelTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thedomination.controller.MapOperations;
import com.thedomination.controller.PlayerOperations;
import com.thedomination.model.CountryModel;
import com.thedomination.model.PlayerModel;
/**
 * 
 * @author Aditi
 *
 */
public class PlayerModelTest {
	PlayerOperations playerOperations;

	/**
	 * Sets the up before class.
	 * 
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Tear down after class.
	 * 
	 * @throws Exception the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	/**
	 * setting up the values of continent and countries before conducting test.
	 * setting up the values of players
	 * @throws Exception the exception
	 */

	@Before
	public void beforeTest() throws Exception {
		playerOperations = PlayerOperations.getInstance();
		ArrayList<PlayerModel> playerModelArrayList = new ArrayList<PlayerModel>();
		ArrayList<CountryModel> countryModelArrayList = new ArrayList<CountryModel>();
		CountryModel countryModel = new CountryModel(1, "India");
		CountryModel countryModel1 = new CountryModel(3, "India12");
		CountryModel countryModel2 = new CountryModel(12, "India23");
		countryModelArrayList.add(countryModel);
		countryModelArrayList.add(countryModel1);
		countryModelArrayList.add(countryModel2);

		PlayerModel playerModel = new PlayerModel("aditi", countryModelArrayList);
		PlayerModel playerModel1 = new PlayerModel("Aditi1", countryModelArrayList);
		PlayerModel playerModel2 = new PlayerModel("Aditi2", countryModelArrayList);
		playerModelArrayList.add(playerModel);
		playerModelArrayList.add(playerModel1);
		playerModelArrayList.add(playerModel2);

		PlayerOperations.getInstance().setPlayerModelList(playerModelArrayList);
		playerOperations.addPlayer("Aditi", "Human");
		playerOperations.addPlayer("Manpreet","Human");
		playerOperations.addPlayer("Piyush","Human");
		playerOperations.addPlayer("Ankur","Human");

	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * method used to add the players.
	 */

	@Test
	public void addPlayer() {

		playerOperations.addPlayer("Aditi", "Human");
		playerOperations.addPlayer("Manpreet","Human");
		playerOperations.addPlayer("Piyush","Human");
		playerOperations.addPlayer("Ankur","Human");

	}

	/**
	 * Method used to check whether the player is in the list or not.
	 *
	 */
	@Test
	public void searchPlayertest() {

		assertEquals("Aditi", playerOperations.searchPlayer("Aditi").getPlayerName());
		assertEquals("Piyush", playerOperations.searchPlayer("Piyush").getPlayerName());
		assertNotEquals("Pritam", playerOperations.searchPlayer("Piyush").getPlayerName());

	}

	/**
	 * Method used to remove the player from the list.
	 *
	 */
	@Test
	public void removePlayertest() {

		playerOperations.removePlayer("Aditi");
		playerOperations.removePlayer("sdf");
		playerOperations.removePlayer("aswer");
		playerOperations.removePlayer("asd");
		assertNull(playerOperations.searchPlayer("Aditi"));
	}

	
	/**
	 * Method used to add the country.
	 * Method used to check player name
	 * Method use to check army assign
	 */
	@Test
	public void overallTest() {
		PlayerModel playermodel = new PlayerModel("FORD");
		CountryModel countrymodel4 = new CountryModel(1, "BANGLADESH");
		playerOperations.setArmiesToAssign(4);
		assertEquals("FORD", playermodel.getPlayerName());
		playermodel.AddCountry(countrymodel4);
		assertEquals("BANGLADESH", playermodel.searchCountry("BANGLADESH").getCountryName());
		assertEquals(4, playerOperations.getArmiesToAssign());

	}
	
}
