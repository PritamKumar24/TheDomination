package com.thedomination.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Stack;

import com.thedomination.controller.MapOperations;
import com.thedomination.model.ContinentModel;
import com.thedomination.model.CountryModel;

/**
 * The Class MapReader initializes the ArrayList continentModels of ContinentModel type,
 * ArrayList countryModels of CountryModel type.
 *
 * @author Pritam Kumar
 */
public class MapReader {

	/**The continentModels ArrayList */
	ArrayList<ContinentModel> continentModels = new ArrayList<>();

	/**The countryModels ArrayList */
	ArrayList<CountryModel> countryModels = new ArrayList<>();

	/**
	 * getContinentModels method to get ArrayList of continents.
	 * 
	 * @return continentModels  ArralyList of continents.
	 */

	public ArrayList<ContinentModel> getContinentModels() {
		return continentModels;
	}

	/**
	 * getCountryModels method to get ArrayList of countries.
	 * 
	 * @return countryModels ArralyList of countries.
	 */

	public ArrayList<CountryModel> getCountryModels() {
		return countryModels;
	}

	/**
	 * parseAndValidateMap method to parse the loaded map and save countries, continents, neighbours, in their corresponding
	 * data structures.
	 * 
	 * @param filePath                       path of the file where the map file is placed.
	 * @return MapOperations.getInstance()   object of the MapOperation class 
	 */

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
					if (!isContinent && continentModels.size() == 0) {
						String valErrorMessage = "Map is invalid";
						MapOperations.getInstance().setValErrorFlag(true);
						MapOperations.getInstance().setErrorMsg(valErrorMessage);
						System.out.println("Map is invalid as there are no continents defined");
						break;
					}
					continue;
				}
				if(currentLine.equalsIgnoreCase("[borders]")) {
					isBorders = true;
					isCountry = false;
					isContinent = false;

					if (!isCountry && countryModels.size() == 0) {
						String valErrorMessage = "Map is invalid";
						MapOperations.getInstance().setValErrorFlag(true);
						MapOperations.getInstance().setErrorMsg(valErrorMessage);
						System.out.println("Map is invalid as there are no countries defined");
						break;
					}
					continue;

				}
				if (isContinent) {
					if(currentLine.indexOf(" ")>0) {
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

				if (isCountry) {
					String[] countryValues = currentLine.split(" ");
					CountryModel countryModel = new CountryModel();

					for (int i = 0; i < countryValues.length; i++) {
						if (i == 0) {
							countryModel.setCountryPosition(Integer.parseInt(countryValues[i]));	
						}
						else if(i == 1){
							countryModel.setCountryName(countryValues[i]);
						} 
						else if(i == 2) {
							ContinentModel continentModel = continentModels.get(Integer.parseInt(countryValues[i])-1);
							countryModel.setBelongsTo(continentModel);
						}
						else {
							// do nothing as we have skipped the coordinates
						}
					}

					countryModels.add(countryModel);


					if (countryValues.length > 0) {
						for (ContinentModel continentModelValue : continentModels) {
							if (continentModelValue.getContinentName().trim()
									.equalsIgnoreCase(countryModel.getBelongsTo().getContinentName().trim())) {
								continentModelValue.addCountry(countryModel);
							}
						}

					}
				}

				if(isBorders) {
					String[] borderValues = currentLine.split(" ");


					for (CountryModel tempCountryModel : countryModels) {
						CountryModel checkCountryModel = new CountryModel();
						checkCountryModel.setCountryPosition(Integer.parseInt(borderValues[0]));
						if (tempCountryModel.equals(checkCountryModel)) {
							for(int i=1; i<borderValues.length; i++) {
								tempCountryModel.addNeighbour(Integer.parseInt(borderValues[i]));
							}
						}
					}
				}
			}
			if(isBorders == false) {
				MapOperations.getInstance().setValErrorFlag(true);
				System.out.println("As it is invalid please pass a correct map");
			}
			else {
				MapOperations.getInstance().setContinentsList(continentModels);
				MapOperations.getInstance().setCountryList(countryModels);			
			}	
		} catch (Exception e) {
			System.out.println("No file found");
		}
		return MapOperations.getInstance();
	}

}
