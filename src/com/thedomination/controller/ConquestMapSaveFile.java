


package com.thedomination.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.thedomination.model.ContinentModel;
import com.thedomination.model.CountryModel;

/**
 * The ConquestMapSaveFile class save the conquest Map file.
 * 
 * @author Aditi
 *
 */
public class ConquestMapSaveFile {
	/**
	 * getMapOperationConcateString method to align the map file according to continents and countries.
	 *
	 * @param MapOperations object for the MapOperations class.
	 * @param fileName name of the map file.
	 * @return the concatenated map file in string format.
	 */

	public String getMapOperationConcateString(MapOperations mapOperations, String fileName) {
		String data = "";

		if (mapOperations.getContinentsList() != null && mapOperations.getContinentsList().size() > 0) {
			data = data.concat("[Continents]" + System.lineSeparator());
			data = data.concat(System.lineSeparator());
			for (ContinentModel continentModel : mapOperations.getContinentsList()) {
				String continentData = continentModel.getContinentName() + "="
						+ continentModel.getCountriesList().size();
				data = data.concat(continentData + System.lineSeparator());
			}
			data = data.concat(System.lineSeparator());
			data = data.concat("[Territories]" + System.lineSeparator());
			data = data.concat(System.lineSeparator());
			for (ContinentModel continentModel : mapOperations.getContinentsList()) {
				data = data.concat(getContinentString(continentModel));
			}
		}

		return data;
	}
	/**
	 * saveMapFile method to save mapFile in text format.
	 *
	 * @param MapOperations object for MapOperations class.
	 * @param fileName name of map file.
	 * @return boolean true if file saved else false.
	 */


	public boolean saveMapFile(MapOperations MapOperations, String fileName) {

		File checkFile = new File(System.getProperty("user.dir")+"/resources/"+fileName);
		boolean isFileSaved = false;
		boolean exists = checkFile.exists();
		String concateString = getMapOperationConcateString(MapOperations, fileName);

		if (!(concateString == null || concateString.isEmpty() || concateString.trim().equalsIgnoreCase("")) && exists == false) {
			PrintWriter out = null;
			try {
				out = new PrintWriter(System.getProperty("user.dir")+ "/resources/" +fileName +".map");
				out.println(concateString);
				isFileSaved = true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				isFileSaved = false;
			} finally {
				out.close();
			}
		}

		else if (!(concateString == null || concateString.isEmpty() || concateString.trim().equalsIgnoreCase("")) && exists == false) {
			try {
				PrintWriter out = new PrintWriter(System.getProperty("user.dir")+ "/resources/" +checkFile);
				out.println(concateString);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		return isFileSaved;

	}

	/**
	 * Gets the continent string.
	 *
	 * @param continentModel the continent model
	 * @return the continent string
	 */
	public String getContinentString(ContinentModel continentModel) {
		String continentString = "";
		for (CountryModel countryModel : continentModel.getCountriesList()) {
			String countryData = countryModel.getCountryName() + ",0,0," + continentModel.getContinentName();
			for (String countryName : countryModel.getListOfNeighbours()) {
				if (!countryModel.getCountryName().trim().equalsIgnoreCase(countryName.trim()))
					countryData = countryData.concat("," + countryName);
			}
			countryData = countryData.concat(System.lineSeparator());
			continentString = continentString.concat(countryData);
		}
		return continentString;
	}
}
