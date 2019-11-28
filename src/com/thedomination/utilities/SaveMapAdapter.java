package com.thedomination.utilities;

import com.thedomination.controller.ConquestMapSaveFile;
import com.thedomination.controller.MapOperations;
import com.thedomination.controller.SaveMapFile;

/**
 * The SaveMapAdapter class to write conquest and save map.
 * 
 * @author Aditi
 *
 */
public class SaveMapAdapter extends SaveMapFile {

	/**
	 * The object of ConquestMapSaveFile type.
	 */
	private ConquestMapSaveFile cmr;

	/**
	 * The parameterized constructor of SaveMapAdapter class.
	 * @param cm of ConquestMapSaveFile type.
	 */
	public SaveMapAdapter(ConquestMapSaveFile cm) {

		this.cmr = cm;
	}

	/**
	 * The saveMapFile method to save and write the map files.
	 * 
	 * @param MapOperations MapOperations
	 * @param fileName String type.
	 * 
	 * @return boolean either true or false.
	 * 
	 */
	public boolean saveMapFile(MapOperations MapOperations, String fileName) {
		return cmr.saveMapFile(MapOperations, fileName);

	}

}