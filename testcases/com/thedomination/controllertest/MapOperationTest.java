package com.thedomination.controllerTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thedomination.model.ContinentModel;
import com.thedomination.controller.MapOperations;;

/**
 * The Class MapHierarchyModelTest.
 */
public class MapOperationTest {


	/** The map hierarchy 1. */
		MapOperations mapOperations = MapOperations.getInstance();

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
		mapOperations.addContinent("RUSSIA2",12);
		mapOperations.addContinent("INDIANA",14);
		mapOperations.addContinent("MEXICO",17);
		mapOperations.addContinent("PANAMA",19);
		
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
	 * deleteContinentTest Unit Test Test used to check the deletion of continents that
	 * are added through add continent method.
	 */
	@Test
	public void deleteContinentTest() {
		
		assertEquals("",mapOperations.deleteContinent("RUSSIA2"));
		assertEquals("",mapOperations.deleteContinent("MEXICO"));	
}
	/**
	 * searchContinentTest Unit Test Test used to check the name of continents that
	 * are added through add continent method.
	 */

	@Test
	public void searchContinentTest() {
		
		
		assertEquals("INDIANA",mapOperations.searchContinent("INDIANA").getContinentName());
		assertEquals("PANAMA",mapOperations.searchContinent("PANAMA").getContinentName());	
	}
}

