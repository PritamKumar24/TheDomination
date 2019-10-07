/**
 * 
 */
package com.thedomination.model;

import java.util.ArrayList;

/**
 * CountryModel Class Initializes country name String, a ContinentModel
 * {@link ContinentModel} as belongsTo and a ArrayList {@link ArrayList} as
 * listofNeighbours.
 *
 * @author Pritam Kumar
 */
public class CountryModel {

	/** The country name. */
	private String countryName;

	private int countryPosition;

	/** The belongs to. */
	private ContinentModel belongsTo;

	/** The no of armies country. */
	private int noOfArmiesCountry = 1;
	
	private ArrayList<Integer> listOfNewNeighbours;

	/**
	 * ContryModel Constructor Create a new empty ArrayList.
	 */
	public CountryModel() {
		this.listOfNewNeighbours = new ArrayList<>();
	}
	
	/**   chane the comment Ankur Singla
	 * CountryModel Parameterized Constructor.
	 *
	 * @param countryName      Name of country
	 * @param continentModel   Continent object which contains this country
	 * @param listOfNeighbours List of all the neighbours country names
	 */

	public CountryModel(int countryPosition, String countryName) {
		this.countryPosition = countryPosition;
		this.countryName = countryName;
	}
	
	/**
	 * CountryModel Parameterized Constructor.
	 *
	 * @param countryName name of the new country
	 * @param continent   Continent object which contains this country
	 */
	public CountryModel(String countryName, ContinentModel continent) {
		this.setCountryName(countryName);
		this.setBelongsTo(continent);
	}
	 
	/**
	 * getCountryName Method Getter Function to get String name.
	 *
	 * @return the countryName Name of country
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * setCountryName Method Setter Function to set name of country.
	 *
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	
	public int getCountryPosition() {
		return countryPosition;
	}

	public void setCountryPosition(int countryPosition) {
		this.countryPosition = countryPosition;

	}

	/**
	 * getBelongsTo Method Method of type ContinentModel.
	 *
	 * @return the belongsTo
	 */
	public ContinentModel getBelongsTo() {
		return belongsTo;
	}

	/**
	 * setBelongsTo Method.
	 *
	 * @param belongsTo the belongsTo to set
	 */
	public void setBelongsTo(ContinentModel belongsTo) {
		this.belongsTo = belongsTo;
	}

	public boolean addNeighbour(int neighbourCountryPosition) {
		return this.listOfNewNeighbours.add(neighbourCountryPosition);
	}

	
	public ArrayList<Integer> getListOfNewNeighbours() {
		return listOfNewNeighbours;
		//we may be need this
	}

	/**
	 * getNoOfArmiesCountry Method Getter Function to get noOfArmiesCountry.
	 *
	 * @return the noOfArmiesCountry Integer
	 */
	public int getNoOfArmiesCountry() {
		return noOfArmiesCountry;
	}

	/**
	 * setNoOfArmiesCountry Method.
	 *
	 * @param noOfArmiesCountry the noOfArmiesCountry to set
	 */
	public void setNoOfArmiesCountry(int noOfArmiesCountry) {
		this.noOfArmiesCountry = noOfArmiesCountry;
	}

	/**
	 * Adds the no of armies country.
	 */
	public void addNoOfArmiesCountry() {
		noOfArmiesCountry++;
	}

	/**
	 * Removes the no of armies country.
	 */
	public void removeNoOfArmiesCountry() {
		noOfArmiesCountry--;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.countryPosition == ((CountryModel) obj).getCountryPosition();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CountryModel [countryName=" + countryName + ", belongsTo=" + belongsTo + ", noOfArmiesCountry="
				+ noOfArmiesCountry + ", listOfNewNeighbours=" + listOfNewNeighbours + "]";
	}

}
