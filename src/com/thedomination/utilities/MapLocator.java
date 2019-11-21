package com.thedomination.utilities;

import com.thedomination.controller.MapOperations;

/**
 * The Class MapLocator class to fetch the map from folder.
 *
 * @author Ankur Singla
 * @version 1.0.0
 */
public class MapLocator {
	/**
	 * mapLocation method to fetch the map from given loaction.
	 *
	 * @param mapName name of the map ile.
	 */
	//public static boolean conquestmap = false;

	public static void mapLocation(String mapName) {

		MapReader mapReader = new MapReader();

		ConquestMapReader conquestMapReader = new ConquestMapReader();

		MapReader reader = new MapAdapter(conquestMapReader);

		MapOperations op = reader.parseAndValidateMap(System.getProperty("user.dir") + "/resources/" + mapName);

		if (op == null) {

			MapOperations op1 = mapReader.parseAndValidateMap(System.getProperty("user.dir") + "/resources/" + mapName);


		}

	}
}
