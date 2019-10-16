package com.thedomination.model;

import java.util.ArrayList;

import com.thedomination.model.CountryModel;

/**
 * The Class ContinentModel.
 *
 * @author Ankur Singla
 */
public class ContinentModel {

	/** The continent name. */
	private int continentPosition;
	
	private String continentName;
	
	/** The countries list. */
	private ArrayList<CountryModel> countriesList;

	/** The control value. */
	private int controlValue;

	/**
	 * Constructor for Continent class.
	 * 
	 * @param continentPosition name of the new continent
	 */
	public ContinentModel(int continentPosition) {
		this.setContinentPosition(continentPosition);
		//this.countriesList = new ArrayList<CountryModel>();
	}
	
	public ContinentModel(String continentName) {
		this.continentName = continentName;
		this.countriesList = new ArrayList<CountryModel>();
	}
	
	//edit 2019
	public ContinentModel(String continentName, int controlValue) {
		this.continentName = continentName;
		this.controlValue = controlValue;
		this.countriesList = new ArrayList<CountryModel>();
	}

	/**
	 * getContinentPosition method Gets the continent position. Getter function for
	 * continent position
	 * 
	 * @return the continentPosition
	 */
	public int getContinentPosition() {
		return continentPosition;
	}

	/**
	 * setContinentName Method Sets the continent name. Setter function for
	 * continent name
	 * 
	 * @param continentName the continentName to set
	 */
	public void setContinentPosition(int continentPosition) {
		this.continentPosition = continentPosition;
	}
	
	

	public String getContinentName() {
		return continentName;
	}



	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	/**
	 * getCountriesList Method Gets the countries list. Getter function for country
	 * list
	 * 
	 * @return the countriesList
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
	 * Method to delete a country from countryList.
	 * 
	 * @param country country object to delete
	 */
	public void deleteCountry(CountryModel country) {
		countriesList.remove(country);
	}
	
	/**
	 * searchCountry Method Function to search a country in this continent.
	 * 
	 * @param countryName the name of the country
	 * @return the country object found in this continent or null if can't find
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
//		//return "ContinentModel [continentName=" + continentName + "]";
//		return "Continent Model [" + continentPosition + "]" + continentName ;
//	}


	
}
