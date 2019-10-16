package com.thedomination.utilities;

/**
 * The Class MapLocator.
 *
 * @author Ankur Singla
 * @version 1.0.0
 */
public class MapLocator {

	public static void mapLocation(String mapName) {
		
		MapReader mapReader = new MapReader(); 
		mapReader.parseAndValidateMap(System.getProperty("user.dir") + "/resources/" + mapName);
	}
}
