package com.thedomination.utilitiestest;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thedomination.controller.MapOperations;
import com.thedomination.utilities.MapReader;

/**
 * 
 * @author Aditi
 *
 */
public class MapReaderTest {
	
	/** The world map file path. */
	private static String worldMapFilePath="world.map";
	private static String invalidworldMapFilePath="Invalid.map";
	
	/** The world invalid map file path. */
	private static String worldInvalidMapFilePath;
	

	
	MapOperations mapOperations1= new MapOperations();
	
	MapReader mapReader= new MapReader();
	
	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		worldMapFilePath =System.getProperty("user.dir") + "/resources/" + worldMapFilePath;
		worldInvalidMapFilePath = System.getProperty("user.dir") + "\\resources\\"+ invalidworldMapFilePath;
	}

	/**
	 * Tear down after class.
	 *
	 * @throws Exception the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
    
	
	
	
	
	
	
	@Test
	public void inValidMapTest() {
		
		String expected="Map is invalid";
		String result="";
		
		mapReader.parseAndValidateMap(worldInvalidMapFilePath);
		
		result=mapOperations1.getErrorMsg();
		
		System.out.println(result);
		
		Assert.assertEquals(expected, result);
	}

	@Test
	public void validMapTest() {
		
		boolean result= false;
		
		mapReader.parseAndValidateMap(worldMapFilePath);
		boolean expected=mapOperations1.isValErrorFlag();		
	   Assert.assertEquals(expected, result);
	}
	@Test
	public void loadAndValidateConnectedMap() {
		mapOperations1.loadMap("world.map");
		
		String expected="This is a valid Graph.";
		
		String result=mapOperations1.validateMap();
		   Assert.assertEquals(expected, result);
	}
	
	@Test
	public void loadAndValidateUnConnectedMap() {
		mapOperations1.loadMap("UnconnectedGraph.map");
		
		String expected="Invalid Graph - Disconnected Graph";
		
		String result=mapOperations1.validateMap();
		   Assert.assertEquals(expected, result);
	}
	
	@Test
	public void loadAndValidateUnConnectedContinentMap() {
		mapOperations1.loadMap("UnconnectedGraph.map");
		
		String expected="Invalid Graph - Disconnected Graph";
		
		String result=mapOperations1.validateMap();
		   Assert.assertEquals(expected, result);
	}
	

}
