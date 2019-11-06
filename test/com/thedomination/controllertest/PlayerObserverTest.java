package com.thedomination.controllertest;

package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thedomination.controller.MapOperations;
import com.thedomination.controller.PlayerOperations;
import com.thedomination.model.CountryModel;
import com.thedomination.model.DominationPhase;
import com.thedomination.model.PlayerModel;


public class PlayerObserverTest {

	/** The map name. */
	private String mapName = "The World";

	/** The no of countries. */
	private int noOfCountries = 10;
	
	DominationPhase riskPhase ;

	
	
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
	 * 
	 * @throws Exception the exception
	 */
	@Before
	public void beforeTest() throws Exception {
		ArrayList<PlayerModel> playerModelArrayList = new ArrayList<PlayerModel>();
		ArrayList<CountryModel> countryModelArrayList = new ArrayList<CountryModel>();
		PlayerOperations playerOperations = new PlayerOperations();
	      riskPhase =new DominationPhase();
		
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
		playerOperations.populateCountries();
		//playerOperations.placeAll();
	}
	
	/**
	 * Tear down.
	 * 
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void populateCountryTest() {
		

		assertEquals("false", PlayerOperations.getInstance().isStartup_phaseFlag());

	}
	
	@Test
	public void placeallTest() {
		

		assertEquals("false", PlayerOperations.getInstance().isStartup_phaseFlag());

	}

}