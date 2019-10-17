package com.thedomination.controllertest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thedomination.controller.MapOperations;
import com.thedomination.model.CountryModel;

/**
 * 
 * @author Manpreet
 *
 */
public class MapOperationsTest {

	
	/** The map name. */
	private String mapName = "The World";

	/** The no of countries. */
	private int noOfCountries = 10;
	
	

	
	MapOperations mapOperations1= new MapOperations();
	
	MapOperations mapOperations= new MapOperations(mapName, noOfCountries);
	CountryModel countryModel = new CountryModel(1,"INDIA");
	CountryModel countryModel1 = new CountryModel(2,"BHUTAN");
    CountryModel cm= new CountryModel();
	
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
		mapOperations1.addContinent("ASIA",1);
		mapOperations1.addContinent("RUSSIA",2);
		mapOperations1.addContinent("ROME",2);
		mapOperations1.addContinent("INDIA",2);
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
	 * searchContinentTest Unit Test Test used to check the name of continents that
	 * are added through add continent method.
	 */

	@Test
	public void searchContinentTest() {

		assertEquals("ASIA", mapOperations1.searchContinent("ASIA").getContinentName());
		assertEquals("RUSSIA", mapOperations1.searchContinent("RUSSIA").getContinentName());

	}
	
	
	/**
	 * Method to check whether the continent is deleted or not.
	 */
	@Test
	public void deleteContinentTest() {
		String value = mapOperations1.deleteContinent("RUSSIA");
		if (value.trim().isEmpty()) {
			assertNull(mapOperations1.searchContinent("RUSSIA"));
		} else {
			mapOperations1.deleteCountry("SAINT");
			mapOperations1.deleteCountry("KREM");
			mapOperations1.deleteCountry("JOHN");
			mapOperations1.deleteContinent("RUSSIA");
			assertNull(mapOperations1.searchContinent("RUSSIA"));
		}

	}
	
}
