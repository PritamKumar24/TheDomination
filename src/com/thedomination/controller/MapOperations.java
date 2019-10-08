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

	/** The conquest map name. */
	private String conquestMapName = "Default";
	
	//edit 2019
	private MapReader mapReader;
	
	/** The continents list. */
	private ArrayList<ContinentModel> continentsList;
	
	/** The country list. */
	private ArrayList<CountryModel> countryList;

	/** The value error flag. */
	private boolean valErrorFlag = false;

	/** The error message. */
	private String errorMsg = "Map is invalid";
	
	//2019
	private static MapOperations UniqueInstance;
	//2019
	public static MapOperations getInstance() {
		if(MapOperations.UniqueInstance == null) {
			MapOperations.UniqueInstance = new MapOperations();
		}
		return MapOperations.UniqueInstance;
	}

	/**
	 * MapHierarchyModel Constructor Instantiates a new map hierarchy model.
	 */
	public MapOperations() {
		this.continentsList = new ArrayList<>();
		this.countryList = new ArrayList<>();
	}

	/**
	 * MapHierarchyModel Parameterized Constructor Instantiates a new map hierarchy
	 * model.
	 * 
	 * @param conquestMapName the conquest map name
	 * @param totalCountries  the total countries
	 */
	public MapOperations(String conquestMapName, int totalCountries) {
		this.conquestMapName = conquestMapName;
		this.continentsList = new ArrayList<ContinentModel>();
		this.countryList = new ArrayList<>();
	}

	/**
	 * Gets the conquest map name. Getter function to get the map name
	 * 
	 * @return the conquestMapName
	 */
	public String getConquestMapName() {
		return conquestMapName;
	}

	/**
	 * Sets the conquest map name. Setter function to set the map name
	 * 
	 * @param conquestMapName the conquestMapName to set
	 */
	public void setConquestMapName(String conquestMapName) {
		this.conquestMapName = conquestMapName;
	}

	/**
	 * Gets the continents list.
	 * 
	 * @return the continentsList
	 */
	public ArrayList<ContinentModel> getContinentsList() {
		return continentsList;
	}

	/**
	 * Sets the continents list.
	 * 
	 * @param continentsList the continentsList to set
	 */
	public void setContinentsList(ArrayList<ContinentModel> continentsList) {
		this.continentsList = continentsList;
	}

	/**
	 * Gets the country list.
	 * 
	 * @return the countryList
	 */
	public ArrayList<CountryModel> getCountryList() {
		return countryList;
	}

	/**
	 * Sets the country list.
	 * 
	 * @param countryModels the countryList to set
	 */
	public void setCountryList(ArrayList<CountryModel> countryModels) {
		this.countryList = countryModels;
	}

	/**
	 * Checks if is value error flag.
	 * 
	 * @return the valErrorFlag
	 */
	public boolean isValErrorFlag() {
		return valErrorFlag;
	}

	/**
	 * Sets the value error flag.
	 * 
	 * @param valErrorFlag the valErrorFlag to set
	 */
	public void setValErrorFlag(boolean valErrorFlag) {
		this.valErrorFlag = valErrorFlag;
	}

	/**
	 * Gets the error message.
	 * 
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Sets the error message.
	 * 
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * Method to search the continent according to the continent's name.
	 * 
	 * @param continentName continent's name
	 * @return continent that found or null if not exits
	 */
	public ContinentModel searchContinent(String continentName) {
		for (ContinentModel loopContinent : getContinentsList()) {
			if (loopContinent.getContinentName().equalsIgnoreCase(continentName)) {
				return loopContinent;
			}
		}
		return null;
	}

	/**
	 * Method to add a new continent.
	 * 
	 * @param continentName name of the new continent
	 * @return the string
	 */
	public void  addContinent(String continentName,int continentValue) {
//		if (searchContinent(continentName) != null) {
//			return "Continent <" + continentName + "> already exists";
//		}
		if (continentName != null && !continentName.trim().isEmpty() && continentValue>0 ) {
			ContinentModel newContinent = new ContinentModel(continentName,continentValue);
			getContinentsList().add(newContinent);
			//System.out.println(newContinent.toString());
			//System.out.println("hello****************************");
			//System.out.println(mapReader.continentModels);
			//mapReader.continentModels.add(newContinent);
			
		}
		for(ContinentModel temp1:continentsList)
		{
		System.out.println(temp1);}
		//return "";	
	}

	/**
	 * Method to delete a continent.
	 * 
	 * @param continentName name of the continent want to delete
	 * @return succeed or failed error message
	 */
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

	/**
	 * Method to rename a continent.
	 * 
	 * @param continentName    name of the continent want to rename
	 * @param newContinentName new name of the continent
	 * @return succeed or failed error message
	 */
	public String renameContinent(String continentName, String newContinentName) {
		ContinentModel continent = searchContinent(continentName);
		if (continent == null) {
			return "Continent  '" + continentName + "'  you want to change does not exists";
		}
		if (searchContinent(newContinentName) != null) {
			return "Continent '" + newContinentName + "' already exits";
		} else {
			continent.setContinentName(newContinentName);
		}
		return "";
	}

	/**
	 * Method to rename a continent.
	 * 
	 * @param countryName     name of the continent want to rename
	 * @param newCountrytName new name of the continent
	 * @return succeed or failed error message
	 */
	public String renameCountry(String countryName, String newCountrytName) {
		CountryModel country = searchCountry(countryName);
		if (country == null) {
			return "Country  '" + countryName + "'  you want to change does not exists";
		}
		if (searchCountry(newCountrytName) != null) {
			return "Country '" + newCountrytName + "' already exits";
		} else {
			country.setCountryName(newCountrytName);
		}
		return "";
	}

	/**
	 * Method to find the country according to the country's name.
	 * 
	 * @param countryName country's name
	 * @return country that found or null if not exits
	 */
	public CountryModel searchCountry(String countryName) {
		CountryModel country = null;
//		for (ContinentModel loopContinent : getContinentsList()) {
//			country = loopContinent.searchCountry(countryName);
//			if (country != null)
//				return country;
//		}
		for(CountryModel tempCountry : countryList) {
			if (tempCountry.getCountryName().equals(countryName))
			return tempCountry;
		}
		return null;
	}
	
	/**
	 * Method to add a new country to an existing continent.
	 * 
	 * @param countryName   name of the new country
	 * @param continentName name of the existing continent that the new country
	 *                      adding in
	 * @return Error message
	 */
	public String addCountry(String countryName, String continentName) {
		ContinentModel targetContinent = searchContinent(continentName);
		if (targetContinent == null) {
			return "Continent <" + continentName + "> does not exists";
		}
		if (searchCountry(countryName) != null) {
			return "Country <" + countryName + "> already exists";
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

	
	/**
	 * Method to delete a country.
	 * 
	 * @param countryName name of the country want to delete
	 * @return succeed or failed error message
	 */
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

	/**
	 * Method to move a country.
	 *
	 * @param countryName   name of the country you want to move
	 * @param continentName name of the continent to which you want to move
	 * @return succeed or failed error message
	 */
	public String moveCountry(String countryName, String continentName) {
		ContinentModel toContinent = searchContinent(continentName);
		CountryModel moveCountry = searchCountry(countryName);
		if (toContinent == null)
			return "Continent  '" + continentName + "'  you want to move does not exists";
		else if (continentName.equalsIgnoreCase(moveCountry.getBelongsTo().getContinentName()))
			return "Continent name cannot be same as existing one";
		else {
			moveCountry.getBelongsTo().getCountriesList().remove(moveCountry);
			toContinent.getCountriesList().add(moveCountry);
			moveCountry.setBelongsTo(toContinent);
		}
		return "";
	}


//	/**
//	 * Method to add a new country to an existing continent with neighbours.
//	 * 
//	 * @param countryName      name of the new country
//	 * @param continentName    name of the existing continent that the new country
//	 *                         adding in
//	 * @param listOfNeighbours list of neighboring countries.
//	 * @return Error message
//	 */
//	public String addCountry(String countryName, String continentName, ArrayList<String> listOfNeighbours) { //we have to change String to Integer
//		ContinentModel targetContinent = searchContinent(continentName);
//		if (targetContinent == null) {
//			return "Continent <" + continentName + "> does not exists";
//		}
//		if (searchCountry(countryName) != null) {
//			return "Country <" + countryName + "> already exists";
//		}
//
//		totalCountries++;
//		CountryModel newCountry = new CountryModel(countryName, targetContinent, listOfNeighbours);
//		targetContinent.addCountry(newCountry);
//		countryList.add(newCountry);
//
//		return "";
//	}
}
