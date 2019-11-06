package com.thedomination.controllertest;

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
import com.thedomination.model.ContinentModel;
import com.thedomination.model.CountryModel;
import com.thedomination.model.PlayerModel;
/**
 * 
 * @author Aditi
 *
 */
public class PlayerOperationTest {
	PlayerOperations playerOperations;

	/**
	 * Sets the up before class.
	 * 
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**k
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
		playerOperations = new PlayerOperations();
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
		int[] intArray = new int[]{ 1,2,3,4 }; 
		PlayerOperations.getInstance().setDiceAttackArray(intArray);
		playerOperations.addPlayer("Aditi");
		playerOperations.addPlayer("Manpreet");
		playerOperations.addPlayer("Piyush");
		playerOperations.addPlayer("Ankur");

	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * method used to add the players.
	 */

	@Test
	public void addPlayer() {

		playerOperations.addPlayer("Aditi");
		playerOperations.addPlayer("Manpreet");
		playerOperations.addPlayer("Pritam");
		playerOperations.addPlayer("Piyush");

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

	/**
	 * Method used to check the fortification is done when none of the army is assigned.

	 */

	@Test
	public void fortifytestNone() {

		String fromCountry = "none";
		String toCountry = "";
		String num = "";
		String expected = "Illegal Move";
		String result = playerOperations.fortifyCountry(fromCountry, toCountry, num);
		Assert.assertEquals(expected, result);

	}

	/**
	 * Method used to check the reinforcement is done when when the army is assigned.

	 */

	@Test
	public void reinforceTest() {
		int num = 2;

		System.out.println(PlayerOperations.getInstance().getPlayersList().size());
		String countryName = "India";

		String result = playerOperations.reInforce(countryName, num);

		String expected = "Armies got as reinforment " + num;

		Assert.assertNotEquals(expected, result);

	}

	/**
	 * Method used to check the fortification is done when  the army is assigned.

	 */

	@Test
	public void fortify() {

		System.out.println(PlayerOperations.getInstance().getPlayersList().size());
		String fromCountry = "India";
		String toCountry = "India12";
		String num = "2";

		String expected = "Fortification Done.";
		String result = playerOperations.fortifyCountry(fromCountry, toCountry, num);
		System.out.println(result + "result");
		Assert.assertNotEquals(expected, result);

	}
	/**
	 * Method to check the Attackimg phase.
	 */

	@Test
	public void attackEvaluationTest() {
		MapOperations mapHierarchyModel = new MapOperations("Test", 4);
		ArrayList<ContinentModel> continentsList = new ArrayList<ContinentModel>();
		ContinentModel continentModel = new ContinentModel("ASIA");
		CountryModel countryModel = new CountryModel(1, "BHUTAN");
		CountryModel countryModel1 = new CountryModel(2, "INDIA");
		CountryModel countryModel2 = new CountryModel(3, "NEPAL");
		CountryModel countryModel3 = new CountryModel(4, "SRILANKA");
		continentModel.addCountry(countryModel);
		continentModel.addCountry(countryModel1);
		continentModel.addCountry(countryModel2);
		continentModel.addCountry(countryModel3);
		continentsList.add(continentModel);
		ArrayList<CountryModel> countryList = new ArrayList<>();
		countryList.add(countryModel);
		countryList.add(countryModel1);
		countryList.add(countryModel2);
		countryList.add(countryModel3);
		mapHierarchyModel.setContinentsList(continentsList);
		mapHierarchyModel.setCountryList(countryList);
		ArrayList<CountryModel> countryModelArrayList = new ArrayList<CountryModel>();
		countryModelArrayList.add(countryModel1);
		countryModelArrayList.add(countryModel2);
		ArrayList<CountryModel> countryModelArrayList1 = new ArrayList<CountryModel>();
		countryModelArrayList1.add(countryModel);
		countryModelArrayList1.add(countryModel3);

		countryModel.addNeighbour(3);
		countryModel1.addNeighbour(4);
		countryModel.addNeighbour(2);
		ArrayList<PlayerModel> playerModels = new ArrayList<PlayerModel>();
		PlayerModel pm1= new PlayerModel("Player1", countryModelArrayList);
		PlayerModel pm2= new PlayerModel("Player2", countryModelArrayList1);


		ArrayList<PlayerModel> playerModelList = new ArrayList<PlayerModel>();
		playerModelList.add(pm1);
		playerModelList.add(pm2);
		countryModel.setNoOfArmiesCountry(4);
		countryModel1.setNoOfArmiesCountry(3);
		countryModel2.setNoOfArmiesCountry(3);
		countryModel3.setNoOfArmiesCountry(3);
		PlayerOperations.getInstance().setAttackFlag(true);
		PlayerOperations.getInstance().attackCountry("INDIA", "NEPAL", 2);
		//System.out.println(PlayerOperations.getInstance().getDiceAttackArray().length+" abcdhhijklk ");

		assertEquals(4, PlayerOperations.getInstance().getDiceAttackArray().length);
		System.out.println("Player 1 country size after winning:" + PlayerOperations.getInstance().getPlayersList().size());

	}

	/**
	 * test to validated whether an attacker can attack with 1 army.
	 */

	@Test
	public void attackTest() 

	{
		MapOperations mapHierarchyModel = new  MapOperations("Test", 4); 
		ArrayList<ContinentModel> continentsList = new ArrayList<ContinentModel>(); 
		ContinentModel continentModel = new  ContinentModel("ASIA");

		CountryModel countryModel1 = new CountryModel(6,"INDIA");
		continentModel.addCountry(countryModel1); continentsList.add(continentModel);
		ArrayList<CountryModel> countryList = new ArrayList<>();
		countryList.add(countryModel1);
		mapHierarchyModel.setContinentsList(continentsList);
		mapHierarchyModel.setCountryList(countryList); ArrayList<CountryModel>
		countryModelArrayList = new ArrayList<CountryModel>();
		countryModelArrayList.add(countryModel1);

		PlayerModel[] playerModels = new PlayerModel[1]; 
		playerModels[0] = new PlayerModel("Player1", countryModelArrayList);

		countryModel1.setNoOfArmiesCountry(1);
		System.out.println(countryModel1.getNoOfArmiesCountry());
		assertEquals("false", String.valueOf(PlayerOperations.getInstance().isAttackFlag())); 

	}
}
