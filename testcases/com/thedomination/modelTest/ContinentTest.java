package com.thedomination.modelTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thedomination.model.CountryModel;
import com.thedomination.model.ContinentModel;

import com.thedomination.controller.MapOperations;;

/**
 * The Class ContinentModelTest.
 * @author Piyush
 */
public class ContinentTest{

	/** The countrymodel object of ContinentModel class */
		ContinentModel countrymodel = new ContinentModel("ASIA"); ;
		
		/** The firstCountry object of CountryModel class.*/
		CountryModel firstCountry;
		
		/** The secondCountry object of CountryModel class.*/
		CountryModel secondCountry;
		
		/**
		 * The countryModelsArray ArrayList of String type.
		 */
		public ArrayList<String> countryModelsArray;
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
		firstCountry = new CountryModel(1,"RUSSIA");
		
		countrymodel.addCountry(firstCountry);
		
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
	public void searchCountryTest() {
		
		

		assertEquals("RUSSIA", countrymodel.searchCountry("RUSSIA").getCountryName());
		

	

	}
	/**
	 * Method to check the deletion of country.
	 */
	@Test
	public void deleteCountryTest()
	{
		countrymodel.deleteCountry(firstCountry);
		assertNull(countrymodel.searchCountry("RUSSIA"));
	}
	/**
	 * Method to check the addition of country.
	 */
	@Test
	public void addCountryTest()
	{
		secondCountry = new CountryModel(2,"MEXICO");
		countrymodel.addCountry(secondCountry);
		assertEquals("MEXICO", countrymodel.searchCountry("MEXICO").getCountryName());
		
	}
	/**
	 * Method to check the controlValue of country.
	 */
	@Test
	public void controlValueTest()
	{
		countrymodel.setControlValue(6);
		assertEquals(6,countrymodel.getControlValue());
	}


}
	

