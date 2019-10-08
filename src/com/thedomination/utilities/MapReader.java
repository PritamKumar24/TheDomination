package com.thedomination.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;

import com.thedomination.controller.MapOperations;
import com.thedomination.model.ContinentModel;
import com.thedomination.model.CountryModel;

/**
 * The Class MapReader.
 *
 * @author Pritam Kumar
 */
public class MapReader {

	 ArrayList<ContinentModel> continentModels = new ArrayList<>();
	 ArrayList<CountryModel> countryModels = new ArrayList<>();
	
	public ArrayList<ContinentModel> getContinentModels() {
		return continentModels;
	}

	public ArrayList<CountryModel> getCountryModels() {
		return countryModels;
	}
	
	public MapOperations parseAndValidateMap(String filePath) {		
		
		boolean isContinent = false;
		boolean isCountry = false;
		boolean isBorders = false;

		
		try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				if (currentLine.isEmpty())
					continue;
				if (currentLine.equalsIgnoreCase("[continents]")) {
					isContinent = true;
					isCountry = false;
					isBorders = false;
					continue;
				}
				if (currentLine.equalsIgnoreCase("[countries]")) {
					isCountry = true;
					isContinent = false;
					isBorders = false;
					continue;
				}
				if(currentLine.equalsIgnoreCase("[borders]")) {
					isBorders = true;
					isCountry = false;
					isContinent = false;
					continue;
					//write code if it throws any error
				}
				if (isContinent) {
					if(currentLine.indexOf(" ")>0)
					{
					String[] continentValues = currentLine.split(" ");
					if (continentValues.length > 0) {
						ContinentModel continentModel = new ContinentModel(continentValues[0]);
						if (continentValues.length > 1)
							continentModel.setControlValue(Integer.valueOf(continentValues[1]));
						continentModels.add(continentModel);
					}
					}
					continue;
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MapOperations.getInstance(); 
	}

}
