package com.thedomination.utilities;

/**
 * The Class MapLocator class to fetch the map from folder.
 *
 * @author Ankur Singla
 * @version 1.0.0
 */
public class MapLocator {
/**
 * mapLocation method to fetch the map from given loaction.
 * @param mapName name of the map ile.
 */
	
	public static void mapLocation(String mapName) {
		
		MapReader mapReader = new MapReader(); 
		mapReader.parseAndValidateMap(System.getProperty("user.dir") + "/resources/" + mapName);
	}
}
