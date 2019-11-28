package com.thedomination.controllerTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import com.thedomination.model.AggressivePlayer;
import com.thedomination.model.CheaterPlayer;
import com.thedomination.model.ContinentModel;
import com.thedomination.model.CountryModel;
import com.thedomination.model.HumanPlayer;
import com.thedomination.model.PlayerModel;
import com.thedomination.model.Strategy;

/**
 * The PlayerOperationTest.
 * 
 * @author Aditi
 *
 */
public class PlayerOperationTest {

	/** The object for the PlayerOperations class. */
	PlayerOperations playerOperations;

	/** The object for the Strategy class. */
	private Strategy strategy;

	/**
	 * Sets the up before class.
	 * 
	 * @throws Exception the exception
	 */
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * k Tear down after class.
	 * 
	 * @throws Exception the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * setting up the values of continent and countries before conducting test.
	 * setting up the values of players
	 * 
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
		PlayerOperations.getInstance().setArmiesToAssign(2);
		playerOperations.addPlayer("Aditi", "Human");
		playerOperations.addPlayer("Manpreet", "Human");
		playerOperations.addPlayer("Piyush", "Human");
		playerOperations.addPlayer("Ankur", "Human");
	}

	/**
	 * method used to add the players.
	 */
	@Test
	public void addPlayer() {
		playerOperations.addPlayer("Aditi", "Human");
		playerOperations.addPlayer("Manpreet", "Human");
		playerOperations.addPlayer("Piyush", "Human");
		playerOperations.addPlayer("Ankur", "Human");
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
	 * Method used to add the country. Method used to check player name Method use
	 * to check army assign
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
	
	/**
	 * The test case fortification testing the validation of a correct fortification phase.
	 */
	@Test
	public void fortificationTesting() {
		MapOperations.getInstance().loadMap("Asia.map");


		MapOperations.getInstance().addCountry("NEPAL", "Asia");
		MapOperations.getInstance().addCountry("PAKISTAN", "Asia");

		CountryModel countryNepal = MapOperations.getInstance().searchCountry("NEPAL");
		CountryModel countryPakistan = MapOperations.getInstance().searchCountry("PAKISTAN");

		countryNepal.setNoOfArmiesCountry(20);
		countryPakistan.setNoOfArmiesCountry(10);

		MapOperations.getInstance().addNeighbourCountry("NEPAL", "PAKISTAN");

		PlayerOperations.getInstance().addPlayer("Ravi", "Human");
		PlayerModel currentPlayer = PlayerOperations.getInstance().searchPlayer("Ravi");
		currentPlayer.AddCountry(countryNepal);
		currentPlayer.AddCountry(countryPakistan);

		HumanPlayer humanPlayer = (HumanPlayer) currentPlayer.getStrategy();
		PlayerOperations.getInstance().setFortifyArmyFlag(true);

		PlayerOperations.getInstance().fortifyCountry("NEPAL", "PAKISTAN", "5");

		assertEquals(15, countryNepal.getNoOfArmiesCountry());
		assertEquals(15, countryPakistan.getNoOfArmiesCountry());

	}
	
	/**
	 * The reinforcementTesting is used for calculation of number of reinforcement
	 * armies.
	 * 
	 */
	@Test
	public void reinforcementTesting() {
		CountryModel country = new CountryModel(1, "INDIA");

		CountryModel country1 = new CountryModel(2, "BHUTAN");
		ArrayList<CountryModel> countryModels = new ArrayList<>();
		countryModels.add(country);
		countryModels.add(country1);

		PlayerModel player = new PlayerModel("Player1", countryModels);
		PlayerOperations.getInstance().getPlayersList().add(player);

		PlayerOperations.getInstance().getReInforcementArmies();

		assertEquals(3, PlayerOperations.getInstance().getReInforceNoOfArmy());
	}
	
	
	
	@Test
	public void reinforce()
	{
		MapOperations.getInstance().loadMap("Asia.map");
		
		MapOperations.getInstance().addContinent("Australia", 4);
		MapOperations.getInstance().addCountry("Melbourne", "Australia");
		MapOperations.getInstance().addNeighbourCountry("Melbourne", "India");
		
	}
	
	/**
	 * populateCountryTest method to test populate countries.
	 * 
	 */
	@Test
	public void populateCountryTest() {
		

		assertEquals("false", PlayerOperations.getInstance().isStartup_phaseFlag());

	}
	/**
	 * placeallTest method to test placeall method.
	 */
	@Test
	public void placeallTest() {
		

		assertEquals("false", PlayerOperations.getInstance().isStartup_phaseFlag());

	}
	
	/**
	 * endGameTesting test case to test the end game.
	 * 
	 */
	@Test
	public void endGameTesting() {

		MapOperations.getInstance().loadMap("domination.map");

		// TournamentController.getInstance().tournamentFlag=true;

		CountryModel countryIndia = MapOperations.getInstance().searchCountry("India");

		countryIndia.setNoOfArmiesCountry(20);

		CountryModel countryChina = MapOperations.getInstance().searchCountry("China");

		countryChina.setNoOfArmiesCountry(1);

		CountryModel countryNigeria = MapOperations.getInstance().searchCountry("Nigeria");

		countryNigeria.setNoOfArmiesCountry(2);

		CountryModel countryCanberra = MapOperations.getInstance().searchCountry("Canberra");

		countryCanberra.setNoOfArmiesCountry(2);

		PlayerOperations.getInstance().returnDefendModel("Nigeria");

		PlayerOperations.getInstance().addPlayer("Ravi", "Aggresive");
		PlayerModel currentPlayer = PlayerOperations.getInstance().searchPlayer("Ravi");
		currentPlayer.AddCountry(countryIndia);
		AggressivePlayer AggresivePlayer = (AggressivePlayer) currentPlayer.getStrategy();

		PlayerOperations.getInstance().addPlayer("Kishan", "Cheater");
		PlayerModel currentPlayer1 = PlayerOperations.getInstance().searchPlayer("Kishan");

		currentPlayer1.AddCountry(countryChina);
		currentPlayer1.AddCountry(countryCanberra);
		currentPlayer1.AddCountry(countryNigeria);

		CheaterPlayer CheaterPlayer1 = (CheaterPlayer) currentPlayer1.getStrategy();

		CheaterPlayer1.attackPhase("India", "China", 0);

		assertEquals("Ravi", PlayerOperations.getInstance().getPlayersList().get(0).getPlayerName());

		assertNotNull(PlayerOperations.getInstance().getPlayersList().get(0).getPlayerName());
		assertEquals(PlayerOperations.getInstance().getPlayersList().size(), 1);
	}
}
