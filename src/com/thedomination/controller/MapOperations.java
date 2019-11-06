package com.thedomination.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

import com.thedomination.model.CountryModel;
import com.thedomination.model.PlayerModel;
import com.thedomination.model.ContinentModel;
import com.thedomination.utilities.MapLocator;
import com.thedomination.utilities.MapReader;


/**
 * The Class MapOperations Initializes the Map file name as DefaultName
 * object for MapReader class mapReader
 * ArrayList of ContinentModel type as continentsList
 * ArrayList of CountryModel type as countryList
 * object for MapOperation as uniqueInstance
 * 
 * @author Ankur Singla
 * @author Pritam Kumar
 * @version 1.0.0
 */
public class MapOperations {


	/**The object for MapReader Class */
	private MapReader mapReader;

	/**The ArrayList of ContinentModel type*/
	private ArrayList<ContinentModel> continentsList;

	/**The ArrayList of ContinentModel type*/
	private ArrayList<CountryModel> countryList;

	/**The ArrayList of  ConnectedNodes.*/
	private ArrayList<ArrayList<String> > listOfConnectedNodes;
	
	/**
	 * getListOfConnectedNodes method to get all the connected nodes.
	 * @return listOfConnectedNodes list of connected nodes.
	 */
	public ArrayList<ArrayList<String>> getListOfConnectedNodes() {
		return listOfConnectedNodes;
	}
	/**
	 * setListOfConnectedNodes setter method to set the list of connected nodes.
	 * @param listOfConnectedNodes ArrayList of connected nodes.
	 */

	public void setListOfConnectedNodes(ArrayList<ArrayList<String>> listOfConnectedNodes) {
		this.listOfConnectedNodes = listOfConnectedNodes;
	}

	/** The object of MapOperation class.*/
	private static MapOperations UniqueInstance;
	
	/** The value error flag. */
	private boolean valErrorFlag = false;
	
	/** The error message. */
	private String errorMsg = "Map is invalid";
	
	/**
	 * getInstance Method to generate the object for the MapOPeration Class. 
	 * 
	 * @return object for the MapOperations class.
	 */
	public static MapOperations getInstance() {
		if(MapOperations.UniqueInstance == null) {
			MapOperations.UniqueInstance = new MapOperations();
		}
		return MapOperations.UniqueInstance;
	}

	/**
	 * Constructor for the MapOperations class
	 */
	private MapOperations() {
		this.continentsList = new ArrayList<>();
		this.countryList = new ArrayList<>();
	}
	
	/**
	 * Parameterized Constructor for the MapOperations class
	 * 
	 * @param conquestMapName  Map name.
	 * @param totalCountries   Total number of countries.
	 */
	public MapOperations(String conquestMapName, int totalCountries) {
		this.continentsList = new ArrayList<ContinentModel>();
		this.countryList = new ArrayList<>();
	}

    /**
	 * getContinentList method Gets Continent List. Getter function for
	 * Continent List.
	 * 
	 * @return continentsList List of continents.
	 */
	public ArrayList<ContinentModel> getContinentsList() {
		return continentsList;
	}

	/**
	 * setContinentList method sets the list of continents. Setter function for
	 * list of continents.
	 * 
	 * @param continentsList list of continents.
	 */
	public void setContinentsList(ArrayList<ContinentModel> continentsList) {
		this.continentsList = continentsList;
	}

	/**
	 * getCountryList method Gets Country List. Getter function for
	 * Country List.
	 * 
	 * @return getCountryList List of countries.
	 */
	public ArrayList<CountryModel> getCountryList() {
		return countryList;
	}

	/**
	 * setCountryList method sets the list of countries. Setter function for
	 * list of countries.
	 * 
	 * @param setCountryList ArrayList of Countries.
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
	 * searchContinent Method to search the Continent.
	 * 
	 * 
	 * @param continentName String Name of the Continent to be searched.
	 * @return loopContinent name of the continent that was searched else null if not found. 
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
	 * addContinent Method to add the continent
	 * 
	 * @param continentName name of the continent.
	 * @param continentValue control value for the continent.
	 */
	public void  addContinent(String continentName,int continentValue) {
		if (continentName != null && !continentName.trim().isEmpty() && continentValue>0 && searchContinent(continentName)==null) {
			ContinentModel newContinent = new ContinentModel(continentName,continentValue);
			getContinentsList().add(newContinent);
			System.out.println("The Continent " + continentName +" has been added");
		}
		else {
			System.out.println("Not allowed");
		}		
	}
	
/**
 * deleteContinent is Method to delete the Continent.
 * 
 * @param continentName String for the name of Continent.
 * @return delete the continent if the continent is found else print not found statement.
 */
	public String deleteContinent(String continentName) {
		ContinentModel deleteContinent = searchContinent(continentName);
		if (deleteContinent != null) {
			if (deleteContinent.getCountriesList().size() > 0) {
				return "Continent " + continentName + " contains countries inside it. It cannot be deleted";
			}
			getContinentsList().remove(deleteContinent);
			deleteContinent = null;
		}
		else {
			return "This Continent doesnot exist";
		}
		System.out.println("The Continent " + continentName +" has been deleted");
		return "";
	}

	/**
	 * searchCountry Method to search the Country.
	 * 
	 * 
	 * @param countryName String Name of the Country to be searched.
	 * @return tempCountry name of the country if got else null if not found. 
	 */
	public CountryModel searchCountry(String countryName) {
		for(CountryModel tempCountry : getCountryList()) {
			if (tempCountry.getCountryName().equals(countryName))
				return tempCountry;
		}
		return null;
	}
	
	/**
	 * addCountry Method to add the country
	 * 
	 * @param continentName name of the continent in which country will be added.
	 * @param countryName name of the country to be added.
	 */
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
		addConnectedGraphCountry(countryName);

		System.out.println("The Country " + countryName +" has been added");
		return "";
	}
	
	/**
	 * addConnectedGraphCountry method adds country to the connected graph.
	 * 
	 * @param countryName name of the country to be added.
	 */
	public void addConnectedGraphCountry(String countryName) {
		ArrayList<ArrayList<String> > listOfConnectedNodes = MapOperations.getInstance().getListOfConnectedNodes();
		listOfConnectedNodes.get(0).add(countryName);
		
		ArrayList<String> list =new ArrayList<String>();
		list.add(countryName);
		listOfConnectedNodes.add(list);
		
		for (int i = 1; i < listOfConnectedNodes.size(); i++) { 
			listOfConnectedNodes.get(i).add("0");
			listOfConnectedNodes.get(listOfConnectedNodes.size()-1).add("0");
        }
	}

	/**
	 * addNeighbourCountry Method to add the neighbour to a country. 
	 * 
	 * @param countryName name of the country to which neighbour will be added.
	 * 
	 * @param neighbourCountryName name of neighbour country to be added.
	 */
	public void addNeighbourCountry(String countryName, String neighbourCountryName) {
		CountryModel neighbourCountry = searchCountry(neighbourCountryName);
		CountryModel countryFound = searchCountry(countryName);

		if (neighbourCountry == null || countryFound == null) {
			System.out.println("Countries |" + neighbourCountryName +" OR "+ countryName + "| Not found");
		}
		else {
			int neighbourCountryPosition = neighbourCountry.getCountryPosition();
			if((MapOperations.getInstance().searchNeighbourCountry(countryFound.getCountryName(), neighbourCountryPosition)) == null) {
				countryFound.addNeighbour(neighbourCountryPosition);
				addNeighbourCountry(neighbourCountryName, countryName);
				addConnectedGraphNeighbour(neighbourCountryName, countryName);
				System.out.println(countryName + " and " + neighbourCountryName +" are neighbours now");
			}
		}
	}
	
	/**
	 * addConnectedGraphNeighbour method to add neighboour to the connected graph.
	 * 
	 * @param neighbourCountryName name of the neighbouring country to be added.
	 * 
	 * @param countryName name of the country to which neighbour has to be added.
	 */
	public void addConnectedGraphNeighbour(String neighbourCountryName, String countryName) {
		ArrayList<ArrayList<String> > listOfConnectedNodes = MapOperations.getInstance().getListOfConnectedNodes();
		int indexCountry = listOfConnectedNodes.get(0).indexOf(countryName);
		int indexNeighbourCountry=listOfConnectedNodes.get(0).indexOf(neighbourCountryName);
		
		listOfConnectedNodes.get(indexCountry).set(indexNeighbourCountry,"1");
		listOfConnectedNodes.get(indexNeighbourCountry).set(indexCountry,"1");
	}
	
	/**
	 * searchNeighbourCountry method to search the neighbour country.
	 * 
	 * @param countryName name of the country to be searched.
	 * 
	 * @param neighbourCountryPosition integer value of the country position.
	 * 
	 * @return country model object if found else null.
	 */
	public CountryModel searchNeighbourCountry(String countryName, int neighbourCountryPosition) {

		CountryModel country = MapOperations.getInstance().searchCountry(countryName);

		for(Integer tempNeighbourPosition : country.getListOfNewNeighbours()) {
			if (tempNeighbourPosition == neighbourCountryPosition) {
				return country;
			}
		}
		return null;
	}
	
	/**
	 * deleteNeighbourCountry Method to delete the Neighbouring country.
	 * 
	 * @param countryName Country Name 
	 * 
	 * @param neighbourCountryName Neighbouring country to be deleted.
	 */
	public void deleteNeighbourCountry(String countryName, String neighbourCountryName) {
		
		CountryModel neighbourCountry = searchCountry(neighbourCountryName);
		CountryModel countryFound = searchCountry(countryName);
		
		if (neighbourCountry == null || countryFound == null) {
			System.out.println("Countries |" + neighbourCountryName +" OR "+ countryName + "| Not found"); 
		}
		else {
			int neighbourCountryPosition = neighbourCountry.getCountryPosition();
			if((MapOperations.getInstance().searchNeighbourCountry(countryFound.getCountryName(), neighbourCountryPosition)) != null) {
				countryFound.deleteNeighbour(neighbourCountryPosition);
				deleteNeighbourCountry(neighbourCountryName, countryName);
				deleteConnectedGraphNeighbour(neighbourCountryName, countryName);
				System.out.println(countryName + " and " + neighbourCountryName +" are neighbours no more");
			}
		}
	}
	
	/**
	 * deleteConnectedGraphNeighbour method to delete the neighbour from connected graph.
	 * 
	 * @param neighbourCountryName neighbour country name to be deleted.
	 * 
	 * @param countryName country name of which neighbour is to be deleted.
	 */
	public void deleteConnectedGraphNeighbour(String neighbourCountryName, String countryName) {
		ArrayList<ArrayList<String> > listOfConnectedNodes = MapOperations.getInstance().getListOfConnectedNodes();
		int indexCountry = listOfConnectedNodes.get(0).indexOf(countryName);
		int indexNeighbourCountry=listOfConnectedNodes.get(0).indexOf(neighbourCountryName);
		
		listOfConnectedNodes.get(indexCountry).set(indexNeighbourCountry,"0");
		listOfConnectedNodes.get(indexNeighbourCountry).set(indexCountry,"0");
	}
	
	/**deleteCountry Method to delete the Country.
	 * 
	 * @param countryName Name of the country to be deleted.
	 * 
	 * @return delete the country if found else print not found message.
	 */
	public String deleteCountry(String countryName) {
		CountryModel deleteCountry = searchCountry(countryName);

		if(deleteCountry == null) {
			return "There is no country with this name.";
		}
		else if(deleteCountry.getListOfNewNeighbours().size()>0) {
			return "Please remove the neighbours of this country before you delete it.";
		}
		
		deleteCountry.getBelongsTo().deleteCountry(deleteCountry);
		getCountryList().remove(deleteCountry);
		deleteCountry = null;
		System.out.println("The Country " + countryName +" has been deleted");
		deleteConnectedGraphCountry(countryName);
		return "";
	}
	/**
	 * DeleteConnectedGraphCountry method to delete the country from the connected graph.
	 * 
	 * @param countryName name of the country to be deleted.
	 */
	
	public void deleteConnectedGraphCountry(String countryName) {
		ArrayList<ArrayList<String> > listOfConnectedNodes = MapOperations.getInstance().getListOfConnectedNodes();
		int index=listOfConnectedNodes.get(0).indexOf(countryName);
		
		for (int i = 0; i < listOfConnectedNodes.size(); i++) { 
			listOfConnectedNodes.get(i).remove(index);
        }
		
		listOfConnectedNodes.remove(index);
	}

     /**
	 * load the map from existing file if there is any
	 * if not then it will create a new file from scratch
	 * @param fileName
	 */
	public void editMap(String fileName) {
		
		File tempFile = new File(System.getProperty("user.dir")+ "/resources/" + fileName);
		boolean exists = tempFile.exists();
		
		listOfConnectedNodes =  new ArrayList<ArrayList<String>>();
		ArrayList<String> insideList =  new ArrayList<String>();
		insideList.add("C/C");
		listOfConnectedNodes.add(insideList);
		
		if(exists) {
			System.out.println("There is a map file "+fileName);
			//MapLocator.mapLocation(fileName);
			loadMap(fileName);
			System.out.println("Map file "+fileName+" has been loaded.. you can start editing the file using commands..");
		}
		else {
			System.out.println("There is no map file "+fileName);
			try {
				PrintWriter out = new PrintWriter(fileName + ".map");
				System.out.println("A new file has been created named "+fileName);
				System.out.println("Please start entering new Continents, Countries and their Neighbors using commands");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
		}	
	}
	
	/**
	 * loadMap method to load a user-saved map file
	 * 
	 * @param fileName name of file.
	 */
	public void loadMap(String fileName) {
		
		MapLocator.mapLocation(fileName);
		listOfConnectedNodes =  new ArrayList<ArrayList<String>>();
		ArrayList<CountryModel> loopCountryList = MapOperations.getInstance().getCountryList();
		ArrayList<String> insideList =  new ArrayList<String>();
		insideList.add("C/C");
		for (CountryModel loop : loopCountryList) {
			insideList.add(loop.getCountryName());
		}
		listOfConnectedNodes.add(insideList);
		
		for (CountryModel loop : loopCountryList) {
			ArrayList<String> insideList1 =  new ArrayList<String>();
			insideList1.add(loop.getCountryName());
			listOfConnectedNodes.add(insideList1);
		}
		
		for (int i = 1; i < listOfConnectedNodes.size(); i++) { 
            for (int j = 1; j < listOfConnectedNodes.size(); j++) { 
            	listOfConnectedNodes.get(i).add("0");
            }
        }
		
		int i = 1;
		for (CountryModel loop : loopCountryList) {
			for (Integer j : loop.getListOfNewNeighbours()) {
				listOfConnectedNodes.get(i).set(j, "1");
				listOfConnectedNodes.get(j).set(i, "1");
			}
			i++;
		}
		
		MapOperations.getInstance().setListOfConnectedNodes(listOfConnectedNodes);
		System.out.println(MapOperations.getInstance().validateMap());
	}
	
	/**
	 * validateMap method for Verification of map correctness. 
	 * 
	 * @return message according to state of map.
	 */
	public String validateMap() {
		String message="";
		Stack<Integer> stack = new Stack<Integer>();
		int source = 1;
		ArrayList<ArrayList<String> > listOfConnectedNodes = MapOperations.getInstance().getListOfConnectedNodes();
		//int number_of_nodes = connectedGraph[source].length - 1;
		int number_of_nodes = listOfConnectedNodes.size() - 1;
		//System.out.println(number_of_nodes);
		int[] visited = new int[number_of_nodes + 1];
		int i, element;
		visited[source] = 1;
		stack.push(source);
		
		while (!stack.isEmpty()) {
			element = stack.pop();
			i = 1;
			while (i <= number_of_nodes) {
				if (listOfConnectedNodes.get(element).get(i) == "1" && visited[i] == 0) {
					stack.push(i);
					visited[i] = 1;
				}
				i++;
			}
		}

		int count = 0;
		for (int v = 1; v <= number_of_nodes; v++) {
			if (visited[v] == 1)
			{
				count++;
			}
		}
		if (count == number_of_nodes) {
			message="This is a valid Graph.";
		}  
		else {
			message="Invalid Graph - Disconnected Graph";
		}
		return message;
	}
	
	/**
	 * showMapEditor method to  show all continents and countries and their neighbors.
	 */
	public void showMapEditor() {
		System.out.println("*****List of Continents*****");
		for(ContinentModel loopContinent : continentsList) {
			System.out.println("Continent Name: " +loopContinent.getContinentName() +" Continent Value: " + loopContinent.getControlValue());
			
			for(CountryModel loopCountry: loopContinent.getCountriesList()) {
				System.out.println("     Country Name: " + loopCountry.getCountryName());
				
				for(Integer loopNeighbour : loopCountry.getListOfNewNeighbours()) {
					System.out.println("          Neighbours: " + MapOperations.getInstance().searchOnCountryPosition(loopNeighbour));
				}
			}
			System.out.println();
		}
		
		
	}
	
	public String searchContinentbyCountry(String countryName) {
		
		for (ContinentModel loopContinent : getContinentsList()) {
			

			
			if (loopContinent.searchCountry(countryName) != null) {
				
				return loopContinent.getContinentName();
			}
		
		
		}
		return null;
	}
	
	/**
	 * searchOnCountryPosition method to search country according to position.
	 * 
	 * @param countryPosition position of country to be found.
	 * 
	 * @return country name.
	 */
	public String searchOnCountryPosition(Integer countryPosition) {
		for(CountryModel loopCountry : MapOperations.getInstance().getCountryList()) {
			if(loopCountry.getCountryPosition() == countryPosition) {
				return loopCountry.getCountryName();
			}
		}
		return "";
	}
	
	/**
	 * showMapGamePlay method to show all countries and continents, armies on each country, ownership, and connectivity.
	 */
	public void showMapGamePlay() {
		showMapEditor();
		
		for(PlayerModel loopPlayer : PlayerOperations.getInstance().getPlayersList()) {
			System.out.println("Player Name: " +loopPlayer.getPlayerName());
			for(CountryModel loopCountry : loopPlayer.getPlayerCountryList()) {
				System.out.println("     Country: " + loopCountry.getCountryName());
				System.out.println("          Armies: "+ loopCountry.getNoOfArmiesCountry());
			}
		}
	}
}
