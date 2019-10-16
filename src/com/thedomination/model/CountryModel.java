package com.thedomination.model;

import java.util.ArrayList;

/**
 *	The CountryModel Class.
 * Initializes countryName String,
 * a ContinentModel {@link ContinentModel} as belongsTo and
 * a ArrayList {@link ArrayList} as
 * listofNewNeighbours.
 * Initializes countryPosition as Integer.
 *
 * @author Pritam Kumar
 */
public class CountryModel {

	/** The country name. */
	private String countryName;

	private int countryPosition;

	/** The belongs to. */
	private ContinentModel belongsTo;

	/** The no of armies in country. */
	private int noOfArmiesCountry;
	
	/**The list of Neighbours */
	private ArrayList<Integer> listOfNewNeighbours;

	/**
	 * ContryModel Constructor Create a new empty ArrayList.
	 */
	public CountryModel() {
		
		this.listOfNewNeighbours = new ArrayList<>();
	}
	
	/**   
	 * CountryModel Parameterized Constructor.
	 *
	 * @param countryName      Name of country
	 * @param countryPosition Position of country.
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
	 * CountryModel Parameterized Constructor.
	 *
	 * @param countryName name of the new country
	 * @param countryPosition  Position of the country
	 * @belongsTo Continent object which contains this country.
	 */
	public CountryModel(String countryName, int countryPosition, ContinentModel belongsTo) {
		this.countryName = countryName;
		this.countryPosition = countryPosition;
		this.belongsTo = belongsTo;
		this.listOfNewNeighbours = new ArrayList<>();
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
	
	/**
	 * getCountryPosition Method Getter Function to get Position of country.
	 *
	 * @return the countryPosition Position of country
	 */
	
	public int getCountryPosition() {
		return countryPosition;
	}
	
	/**
	 * setCountryPosition Method Setter Function to set Position of country.
	 *
	 * @param countryPosition the countryPosition to set.
	 */
	public void setCountryPosition(int countryPosition) {
		this.countryPosition = countryPosition;

	}

	/**
	 * getBelongsTo Method Method of type ContinentModel.
	 *
	 * @return the belongsTo ContinentModel type.
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
	
	/**
	 * addNeighbour method adds the Neighbour.
	 * 
	 * @param neighbourCountryPosition Integer value for the neighbour country Position.
	 * @return true if the Neighbour is add else false.
	 */
	public boolean addNeighbour(int neighbourCountryPosition) {
	
		return this.listOfNewNeighbours.add(neighbourCountryPosition);
	}
	
	
	/**
	 * deleteNeighbour method delete the Neighbour.
	 * 
	 * @param neighbourCountryPosition Integer value for the neighbour country Position.
	 * @return true if the Neighbour is deleted else false.
	 */
	public boolean deleteNeighbour(int neighbourCountryPosition) {
		
		return this.listOfNewNeighbours.remove(Integer.valueOf(neighbourCountryPosition));
		 
	}
	/**
	 *getListOfNewNeighbours Method Getter Function to get list of Neighbour.
	 *
	 * @return the countryName Name of country
	 */
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
	 * @param noOfArmiesCountry the no Of Armies Country to set
	 */
	public void setNoOfArmiesCountry(int noOfArmiesCountry) {
		this.noOfArmiesCountry = noOfArmiesCountry;
	}

	/**
	 * Adds the number of armies country.
	 */
	public void addNoOfArmiesCountry() {
		noOfArmiesCountry++;
	}

	/**
	 * Removes the number of armies country.
	 */
	public void removeNoOfArmiesCountry() {
		noOfArmiesCountry--;
	}

	@Override
	/**
	 * Equals Method to compare the country position from list to the object of the countryPosition.
	 * 
	 * @return true if the countryPosition is equal to the object of the getcountryPosition.
	 */
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.countryPosition == ((CountryModel) obj).getCountryPosition();
	}

	/**
	 *toString Method to print the object in String form.
	 *
	 * @return CountryModel countryPosition countryName belongsTo noOfArmiesCountry listOfNewNeighbours.
	 */
//	@Override
//	public String toString() {
//		return "CountryModel [" + countryPosition + "]" + countryName + ", belongsTo=" + belongsTo + ", noOfArmiesCountry="
//				+ noOfArmiesCountry + ", listOfNewNeighbours=" + listOfNewNeighbours + "]";
//	}

}
