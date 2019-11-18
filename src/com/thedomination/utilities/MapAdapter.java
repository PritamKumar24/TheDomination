package com.thedomination.utilities;

import com.thedomination.controller.MapOperations;

public class MapAdapter extends MapReader {

	private ConquestMapReader cmr;
	public MapAdapter(ConquestMapReader cm) {

	    this.cmr = cm;}
	  /**
	   * The Adapter provides the Target's method and translates it
	   * to the corresponding Adaptee's method call.
	   */
	public MapOperations parseAndValidateMap(String filePath) {
		return cmr.parseAndValidateConuestMap(filePath);
		}

}
