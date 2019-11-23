package com.thedomination.utilities;

import com.thedomination.controller.ConquestMapSaveFile;
import com.thedomination.controller.MapOperations;
import com.thedomination.controller.SaveMapFile;

public class SaveMapAdapter extends SaveMapFile {

	private ConquestMapSaveFile cmr;

	public SaveMapAdapter(ConquestMapSaveFile cm) {

		this.cmr = cm;
	}

	/**
	 * The Adapter provides the Target's method and translates it to the
	 * corresponding Adaptee's method call.
	 */
	public boolean saveMapFile(MapOperations MapOperations, String fileName) {
		return cmr.saveMapFile(MapOperations, fileName);

	}

}