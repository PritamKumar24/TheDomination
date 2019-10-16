package com.thedomination.model;

import java.util.ArrayList;

import com.thedomination.model.CountryModel;

/**
 * The Class ContinentModel.
 *
 * @author Ankur Singla
 */
public class ContinentModel {

	/** The continent Position. */
	private int continentPosition;
	
	/** The continent name. */
	private String continentName;
	
	/** The countries list. */
	private ArrayList<CountryModel> countriesList;

	/** The control value. */
	private int controlValue;

	/**
	 * Constructor for Continent class.
	 * 
	 * @param continentPosition position of the new continent taken as Integer.
	 */
	public ContinentModel(int continentPosition) {
		this.setContinentPosition(continentPosition);
		//this.countriesList = new ArrayList<CountryModel>();
	}
	/**
	 * Constructor for Continent class.
	 * 
	 * @param continentName name of the new continent ..
	 */
	
	public ContinentModel(String continentName) {
		this.continentName = continentName;
		this.countriesList = new ArrayList<CountryModel>();
	}
	/**
	 * Constructor for Continent class.
	 * 
	 * @param continentName and controlValue  name of the new continent and controlValue for the ContinentModel.
	 */
	
	public ContinentModel(String continentName, int controlValue) 
	{
		this.continentName = continentName;
		this.controlValue = controlValue;
		this.countriesList = new ArrayList<CountryModel>();
	}

	/**
	 * getContinentPosition method Gets the continent position. Getter function for
	 * continent position.
	 * 
	 * @return the continentPosition
	 */
	public int getContinentPosition() {
		return continentPosition;
	}

	/**
	 * setContinentName Method Sets the continent name. Setter function for
	 * continent Position.
	 * 
	 * @param continentName the continentName to set
	 */
	public void setContinentPosition(int continentPosition) {
		this.continentPosition = continentPosition;
	}
	
	
	/**
	 * getContinentName method Gets the continent Name. Getter function for
	 * continent Name.
	 * 
	 * @return continentName Name of the continent.
	 */
	public String getContinentName() {
		return continentName;
	}
	
	
	/**
	 * setContinentName method sets the continent Name. setter function for
	 * continent Name.
	 * 
	 * @param continentName Name of the Continent.
	 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	/**
	 * getCountriesList method Gets the countries List. Getter function for
	 * country List.
	 * 
	 * @return countriesList list of the countries.
	 */
	public ArrayList<CountryModel> getCountriesList() {
		return countriesList;
	}

	/**
	 * setCountriesList method Sets the countries list. Setter function for country
	 * list
	 * 
	 * @param countriesList the countriesList to set
	 */
	public void setCountriesList(ArrayList<CountryModel> countriesList) {
		this.countriesList = countriesList;
	}

	/**
	 * addCountry Method Method to add a country to countryList.
	 * 
	 * @param country new country object
	 */
	public void addCountry(CountryModel country) {
		countriesList.add(country);
	}

	/**
	 * Method to remove a country from countryList.
	 * 
	 * @param country country object to be removed.
	 */
	public void deleteCountry(CountryModel country) {
		countriesList.remove(country);
	}
	
	/**
	 * searchCountry Method Function to search a country in this continent.
	 * 
	 * @param countryName the name of the country to be searched.
	 * @return the country object if found in this continent or null if not found.
	 */
	public CountryModel searchCountry(String countryName) {
		countryName = countryName.toLowerCase();
		for (CountryModel loopCountry : countriesList) {
			if (loopCountry.getCountryName().equalsIgnoreCase(countryName)) {
				return loopCountry;
			}
		}
		return null;
	}
	
	/**
	 * setControlValue Method Sets the control value.
	 * 
	 * @param controlValue the controlValue to set
	 */
	public void setControlValue(int controlValue) {
		this.controlValue = controlValue;
	}

	/**
	 * getControlValue Method Gets the control value.
	 * 
	 * @return the controlValue
	 */
	public int getControlValue() {
		return controlValue;
	}

//	@Override
//	public String toString() {
//		return "ContinentModel [continentName=" + continentName + ", countriesList=" + countriesList + ", controlValue="
//				+ controlValue + "]";
//	}

	
}
