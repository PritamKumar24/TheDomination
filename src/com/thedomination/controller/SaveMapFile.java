package com.thedomination.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.thedomination.controller.MapOperations;
import com.thedomination.model.ContinentModel;
import com.thedomination.model.CountryModel;

/**
 *saveMapFile class for saving a map to a text file.
 *
 * @author Pritam Kumar
 * @version 1.0.0
 */

public class SaveMapFile {
	/**
	 * getMapOperationConcateString method to align the map file according to continents and countries.
	 * 
	 * @param MapOperations object for the MapOperations class.
	 * @param fileName name of the map file.
	 * @return the concatenated map file in string format. 
	 */

	public String getMapOperationConcateString(MapOperations MapOperations, String fileName) {
		String concateString = "name "+fileName +" Map"+ System.lineSeparator();
		concateString = concateString.concat(System.lineSeparator());
		
		if (MapOperations.getContinentsList() != null ) {
			concateString = concateString.concat("[continents]" + System.lineSeparator());
			for (ContinentModel tempContinentModel : MapOperations.getInstance().getContinentsList()) {
				String continentConcateString = tempContinentModel.getContinentName() + " "
						+ tempContinentModel.getControlValue();
				concateString = concateString.concat(continentConcateString + System.lineSeparator());
			}
			concateString = concateString.concat(System.lineSeparator());
			concateString = concateString.concat("[countries]" + System.lineSeparator());
			
			for (CountryModel countryModel : MapOperations.getCountryList()) {
				String countryConcateString = countryModel.getCountryPosition()+" "+countryModel.getCountryName()+ " "+
				(MapOperations.getInstance().getContinentsList().indexOf(countryModel.getBelongsTo())+1)+" 0 0";
				countryConcateString = countryConcateString.concat(System.lineSeparator());
				concateString = concateString.concat(countryConcateString);
			}
			
			concateString = concateString.concat(System.lineSeparator());
			concateString = concateString.concat("[borders]" + System.lineSeparator());
			
			for(CountryModel countryModel : MapOperations.getCountryList()) {
				String countryNeighbourList = ""+countryModel.getCountryPosition();
				for(Integer neighbourPosition : countryModel.getListOfNewNeighbours()) {
					countryNeighbourList = countryNeighbourList+" "+neighbourPosition;
				}
				countryNeighbourList = countryNeighbourList.concat(System.lineSeparator());
				concateString = concateString.concat(countryNeighbourList);	
			}	
		}
		return concateString;
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
}
