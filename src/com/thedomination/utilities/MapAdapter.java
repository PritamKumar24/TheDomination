package com.thedomination.utilities;

import com.thedomination.controller.MapOperations;

/**
 * The MapAdapter class changes teh domination.map to conquest map.
 * 
 * @author Aditi
 *
 */
public class MapAdapter extends MapReader {

	/**
	 * The ConquestMapReader .
	 */
	private ConquestMapReader cmr;
	
	/**
	 * The MapAdapter constructor of MapAdapter class.
	 * @param cm of ConquestMapReader type.
	 */
	public MapAdapter(ConquestMapReader cm) {

	    this.cmr = cm;}
	
	/**
	 * The parseAndValidateMap method to parse and validate map.
	 * 
	 * @param filePath path of file to be parse and validate.
	 * @return object of MapOperations.
	 * 
	 */
	public MapOperations parseAndValidateMap(String filePath) {
		return cmr.parseAndValidateConuestMap(filePath);
		}

}
