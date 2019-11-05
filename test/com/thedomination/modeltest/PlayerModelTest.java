package com.thedomination.modelTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thedomination.model.CountryModel;
import com.thedomination.model.PlayerModel;

/**
 * The Class PlayerModelTest.
 * @author Piyush
 */
public class PlayerModelTest {

	/** The country model array list. */
	private ArrayList<CountryModel> countryModelArrayList;

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
	 * Before test.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void beforeTest() throws Exception {
		CountryModel countryModel = new CountryModel(1,"INDOCANADA");
		CountryModel countryModel1 = new CountryModel(2,"CANADA");
		CountryModel countryModel2 = new CountryModel(3,"BRAZIL");
		CountryModel countryModel3 = new CountryModel(4,"INDIANA");
		CountryModel countryModel4 = new CountryModel(5,"SRILANKA");
		countryModelArrayList = new ArrayList<CountryModel>();
		countryModelArrayList.add(countryModel);
		countryModelArrayList.add(countryModel1);
		countryModelArrayList.add(countryModel2);
		countryModelArrayList.add(countryModel3);
		countryModelArrayList.add(countryModel4);

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
	 * Method used to check whether the player is having the corresponding country
	 * or not.
	 */
	@Test
	public void searchCountrytest() {
		PlayerModel playerModel = new PlayerModel("PIYUSH", countryModelArrayList);
		assertEquals("CANADA", playerModel.searchCountry("CANADA").getCountryName());
		assertEquals("SRILANKA", playerModel.searchCountry("SRILANKA").getCountryName());

	}
	/**
	 * Method to check the number of armies assigned.
	 */
	@Test
	public void getNoOfArmiesTest()
	{
		PlayerModel playerModel = new PlayerModel("ANKUR", countryModelArrayList);
		playerModel.setnoOfArmyInPlayer(4);
		assertEquals(4, playerModel.getnoOfArmyInPlayer());
	}


	/**
	 * Basic test conducted to check whether all the methods are functioning
	 * properly or not.
	 */
	@Test
	public void overallTest() {
		PlayerModel playerModel = new PlayerModel("ANKUR", countryModelArrayList);
		CountryModel countryModel4 = new CountryModel(7,"MELBOURNE");
		
		assertEquals("ANKUR", playerModel.getPlayerName());
		playerModel.AddCountry(countryModel4);
		assertEquals("MELBOURNE", playerModel.searchCountry("MELBOURNE").getCountryName());
		

	}

}
