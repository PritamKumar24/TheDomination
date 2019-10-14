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
				if (!currentLine.equalsIgnoreCase("[continents]")||!currentLine.equalsIgnoreCase("[countries]")||!currentLine.equalsIgnoreCase("[continents]")) {
					System.out.println("Map Invalid");
					break;
				}
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
			
			MapOperations.getInstance().setContinentsList(continentModels); //2019
			
			MapOperations.getInstance().setCountryList(countryModels); //2109
			
			
		for(ContinentModel temp: continentModels) {   //2019
				System.out.println("------Continents--------");
				System.out.println(temp.toString());
		}
		System.out.println("");
		
		for(CountryModel temp: countryModels) {
			System.out.println("------Countries--------");
			System.out.println(temp.toString());
	}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MapOperations.getInstance(); //2109
	}

}
