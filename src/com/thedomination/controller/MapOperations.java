package com.thedomination.controller;

import java.util.ArrayList;

import com.thedomination.model.CountryModel;
import com.thedomination.model.ContinentModel;
import com.thedomination.utilities.MapReader;


/**
 * Class MapOperations.
 *
 * @author Ankur Singla
 * @version 1.0.0
 */
public class MapOperations {
	
	//edited
	private MapReader mapReader;
	
	private ArrayList<ContinentModel> continentsList;
	
	private ArrayList<CountryModel> countryList;

	
	//edited
	private static MapOperations UniqueInstance;
	//edited
	
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
		this.conquestMapName = conquestMapName;
		this.continentsList = new ArrayList<ContinentModel>();
		this.countryList = new ArrayList<>();
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
//		if (searchContinent(continentName) != null) {
//			return "Continent <" + continentName + "> already exists";
//		}
		if (continentName != null && !continentName.trim().isEmpty() && continentValue>0 ) {
			ContinentModel newContinent = new ContinentModel(continentName,continentValue);
			getContinentsList().add(newContinent);
			
		}
		for(ContinentModel temp1:continentsList) {
		System.out.println(temp1);}
	}

	public String deleteContinent(String continentName) {
		ContinentModel deleteContinent = searchContinent(continentName);
		if (deleteContinent != null) {
			if (deleteContinent.getCountriesList().size() > 0) {
				return "Continent '" + continentName + "' is not empty, Please delete or move all the countries in it.";
			}
			getContinentsList().remove(deleteContinent);
			deleteContinent = null;
		}
			for(ContinentModel temp1:continentsList)
			{
			System.out.println(temp1);}
		return "";
	}

}
