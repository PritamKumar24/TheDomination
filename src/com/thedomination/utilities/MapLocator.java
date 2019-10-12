package com.thedomination.utilities;

public class MapLocator {

	public static void mapLocation(String mapName) {
		
		MapReader mapReader = new MapReader(); 
		mapReader.parseAndValidateMap(System.getProperty("user.dir") + "/resources/" + mapName);
	}
}
