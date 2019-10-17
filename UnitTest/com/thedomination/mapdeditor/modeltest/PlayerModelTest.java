package com.thedomination.mapdeditor.modeltest;

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

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

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
		playerOperations.addPlayer("Aditi");
		playerOperations.addPlayer("Manpreet");
		playerOperations.addPlayer("Piyush");
		playerOperations.addPlayer("Ankur");

	}

	@After
	public void tearDown() throws Exception {
	}

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

	@Test
	public void removePlayertest() {

		playerOperations.removePlayer("Aditi");
		playerOperations.removePlayer("sdf");
		playerOperations.removePlayer("aswer");
		playerOperations.removePlayer("asd");
		assertNull(playerOperations.searchPlayer("Aditi"));
	}

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

	@Test
	public void fortifytestNone() {

		String fromCountry = "none";
		String toCountry = "";
		String num = "";
		String expected = "No countries chosen for fortification";
		String result = playerOperations.fortifyCountry(fromCountry, toCountry, num);
		Assert.assertEquals(expected, result);

	}

	@Test
	public void reinforceTest() {
		int num = 2;

		System.out.println(PlayerOperations.getInstance().getPlayersList().size());
		String countryName = "India";

		String result = playerOperations.reInforce(countryName, num);

		String expected = "Armies got as reinforment " + num;
		// String expected = "No countries chosen for fortification";
		// String result= playerOperations.fortifyCountry(fromCountry, toCountry, num);
		Assert.assertNotEquals(expected, result);

	}

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
}
