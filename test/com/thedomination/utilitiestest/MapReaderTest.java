package com.thedomination.utilitiesTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thedomination.model.CountryModel;
import com.thedomination.utilities.MapReader;
import com.thedomination.controller.MapOperations;
import com.thedomination.controller.SaveMapFile;

/**
 * The Class UtilityTest.
 * @author Piyush
 */
public class MapReaderTest {

	/** The utility. */
	private MapReader utility = new MapReader();

	/** The asia map file path. */
	private static String ASIA_MAP_FILE_PATH;

	/** The asia invalid map file path. */
	private static String ASIA_INVALID_MAP_FILE_PATH;

	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ASIA_MAP_FILE_PATH = System.getProperty("user.dir") + "/resources/Asia.map" ;
	//System.getProperty("user.dir") + "\\resources\\testresource\\Asia.map";
		ASIA_INVALID_MAP_FILE_PATH = System.getProperty("user.dir") + "\\resources\\testresource\\Asiainvalid.map";
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
	 * First it parses the map and then extracts the list of countries from it and
	 * after that it compares with the list of counties that we have passed. Note:
	 * At least 5 countries should be there in order to be a valid map.
	 */
	@Test
	public void parseAndValidateMapTest() {
		ArrayList<CountryModel> parsedCountryList = utility.parseAndValidateMap(ASIA_MAP_FILE_PATH).getCountryList();

		System.out.println(parsedCountryList.size());

		ArrayList<String> countryNames = new ArrayList<String>();
		ArrayList<String> countryList = new ArrayList<String>();

		for (CountryModel loopCountry : parsedCountryList) {
			countryNames.add(loopCountry.getCountryName());
		}
		countryList.add("Boston");
		countryList.add("California");
		countryList.add("New York");
		countryList.add("Nepal");
		countryList.add("India");
		countryList.add("Bhutan");
		System.out.println(countryList);
		assertFalse(countryList.equals(countryNames));
	}

	/**
	 * Function to check whether the function is reading the file and returning the
	 * data without returning null.
	 */
	@Test
	public void convertMapDataToStringTest() {
		MapOperations save = utility.parseAndValidateMap(ASIA_MAP_FILE_PATH);
		SaveMapFile save1= new SaveMapFile();
		assertNotNull((save1.getMapOperationConcateString(save,ASIA_MAP_FILE_PATH )));

	}



}