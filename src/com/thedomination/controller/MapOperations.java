package com.thedomination.controller;

import java.util.ArrayList;

import com.thedomination.model.CountryModel;
import com.thedomination.model.ContinentModel;
import com.thedomination.utilities.MapReader;


/**
 * The Class 
 *
 * @author Ankur Singla
 * @version 1.0.0
 */
public class MapOperations {

	private String DefaultName = "NewMapFile";
	
	//2019
	private MapReader mapReader;
	
	private ArrayList<ContinentModel> continentsList;
	
	private ArrayList<CountryModel> countryList;
	
	//2019
	private static MapOperations UniqueInstance;
	//2019
	public static MapOperations getInstance() {
		if(MapOperations.UniqueInstance == null) {
			MapOperations.UniqueInstance = new MapOperations();
		}
		return MapOperations.UniqueInstance;
	}


	public MapOperations() {
		this.continentsList = new ArrayList<>();
		this.countryList = new ArrayList<>();
	}

	public MapOperations(String conquestMapName, int totalCountries) {
		this.DefaultName = conquestMapName;
		this.continentsList = new ArrayList<ContinentModel>();
		this.countryList = new ArrayList<>();
	}

	public String getDefaultName() {
		return DefaultName;
	}

	public void setDefaultName(String defaultName) {
		DefaultName = defaultName;
	}

	public ArrayList<ContinentModel> getContinentsList() {
		return continentsList;
	}

	public void setContinentsList(ArrayList<ContinentModel> continentsList) {
		this.continentsList = continentsList;
	}

	public ArrayList<CountryModel> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<CountryModel> countryModels) {
		this.countryList = countryModels;
	}

	public ContinentModel searchContinent(String continentName) {
		for (ContinentModel loopContinent : getContinentsList()) {
			if (loopContinent.getContinentName().equalsIgnoreCase(continentName)) {
				return loopContinent;
			}
		}
		return null;
	}


	public void  addContinent(String continentName,int continentValue) {

		if (continentName != null && !continentName.trim().isEmpty() && continentValue>0 ) {
			ContinentModel newContinent = new ContinentModel(continentName,continentValue);
			getContinentsList().add(newContinent);

		}
		for(ContinentModel temp1:continentsList)
		{
		System.out.println(temp1);}	
	}

	public String deleteContinent(String continentName) {
		ContinentModel deleteContinent = searchContinent(continentName);
		if (deleteContinent != null) {
			if (deleteContinent.getCountriesList().size() > 0) {
				return "Continent '" + continentName + "' is not there";
			}
			getContinentsList().remove(deleteContinent);
			deleteContinent = null;
		}
			for(ContinentModel temp1:continentsList)
			{
			System.out.println(temp1);}
		return "";
	}



	public CountryModel searchCountry(String countryName) {
		CountryModel country = null;

		for(CountryModel tempCountry : countryList) {
			if (tempCountry.getCountryName().equals(countryName))
			return tempCountry;
		}
		return null;
	}
	
	public String addCountry(String countryName, String continentName) {
		ContinentModel targetContinent = searchContinent(continentName);
		if (targetContinent == null) {
			return "Continent |" + continentName + "| does not exists";
		}
		if (searchCountry(countryName) != null) {
			return "Country |" + countryName + "| already exists";
		}
		
		CountryModel newCountry = new CountryModel(countryName, (countryList.size())+1, targetContinent);
		targetContinent.addCountry(newCountry);
		countryList.add(newCountry);

		System.out.println(countryList.toString());
		return "";
	}
	
	public String addNeighbourCountry(String countryName, String neighbourCountryName) {
		
		if (searchCountry(neighbourCountryName) == null || searchCountry(countryName) == null) {
		//	System.out.println(neighbourCountryName);
			return "Countries <" + neighbourCountryName + countryName + "> Not found";
		}
		
		else if (searchCountry(neighbourCountryName) != null && searchCountry(countryName) !=null) {
			
			CountryModel neighbourCountry = searchCountry(neighbourCountryName);
			
			int neighbourCountryPosition = neighbourCountry.getCountryPosition();
			
			CountryModel countryFound = searchCountry(countryName);
		
			countryFound.addNeighbour(neighbourCountryPosition);
			
			System.out.println(countryList.toString());
		}
		
		return "";
	}
	
	public String deleteNeighbourCountry(String countryName, String neighbourCountryName) {
		
		if (searchCountry(neighbourCountryName) == null || searchCountry(countryName) == null) {
		//	System.out.println(neighbourCountryName);
			return "Countries <" + neighbourCountryName + countryName + "> Not found";
		}
		
		else if (searchCountry(neighbourCountryName) != null && searchCountry(countryName) !=null) {
			
			CountryModel neighbourCountry = searchCountry(neighbourCountryName);
			
			int neighbourCountryPosition = neighbourCountry.getCountryPosition();
			
			CountryModel countryFound = searchCountry(countryName);
		
			//countryFound.addNeighbour(neighbourCountryPosition);
			countryFound.deleteNeighbour(neighbourCountryPosition);
			
			System.out.println(countryList.toString());
		}
		
		return "";
	}

	
	public String deleteCountry(String countryName) {
		CountryModel deleteCountry = searchCountry(countryName);
		if (deleteCountry != null) {
			deleteCountry.getBelongsTo().deleteCountry(deleteCountry);
			getCountryList().remove(deleteCountry);
			deleteCountry = null;
		} else {
			return "Can't find country with this name";
		}
		System.out.println(countryList.toString());
		return "";
	}

}
