package com.thedomination.controller;

import java.util.ArrayList;
import java.util.Stack;

import com.thedomination.model.ContinentModel;
import com.thedomination.model.CountryModel;
import com.thedomination.utilities.MapLocator;
import com.thedomination.utilities.MapReader;

/**
 * The Class
 *
 * @author Ankur Singla
 * @version 1.0.0
 */
public class MapOperations {

	private String DefaultName = "NewMapFile";

	// 2019
	private MapReader mapReader;

	private ArrayList<ContinentModel> continentsList;

	private ArrayList<CountryModel> countryList;
	private Stack<Integer> stack;

	// 2019
	private static MapOperations UniqueInstance;

	private String[][] connectedGraph;

	// 2019
	public static MapOperations getInstance() {
		if (MapOperations.UniqueInstance == null) {
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

	public String[][] getConnectedGraph() {
		return connectedGraph;
	}

	public void setConnectedGraph(String[][] connectedGraph) {
		this.connectedGraph = connectedGraph;
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

	/**
	 * 
	 * @param continentName
	 * @return
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
	 * 
	 * @param continentName
	 * @param continentValue
	 */

	public void addContinent(String continentName, int continentValue) {

		if (continentName != null && !continentName.trim().isEmpty() && continentValue > 0) {
			ContinentModel newContinent = new ContinentModel(continentName, continentValue);
			getContinentsList().add(newContinent);

		}
		for (ContinentModel temp1 : continentsList) {
			System.out.println(temp1);
		}
	}

	/**
	 * 
	 * @param continentName
	 * @return
	 */
	public String deleteContinent(String continentName) {
		ContinentModel deleteContinent = searchContinent(continentName);
		if (deleteContinent != null) {
			if (deleteContinent.getCountriesList().size() > 0) {
				return "Continent '" + continentName + "' is not there";
			}
			getContinentsList().remove(deleteContinent);
			deleteContinent = null;
		}
		for (ContinentModel temp1 : continentsList) {
			System.out.println(temp1);
		}
		return "";
	}

	/**
	 * 
	 * @param countryName
	 * @return
	 */

	public CountryModel searchCountry(String countryName) {
		CountryModel country = null;

		for (CountryModel tempCountry : countryList) {
			if (tempCountry.getCountryName().equals(countryName))
				return tempCountry;
		}
		return null;
	}

	/**
	 * 
	 * @param countryName
	 * @param continentName
	 * @return
	 */
	public String addCountry(String countryName, String continentName) {
		ContinentModel targetContinent = searchContinent(continentName);
		if (targetContinent == null) {
			return "Continent |" + continentName + "| does not exists";
		}
		if (searchCountry(countryName) != null) {
			return "Country |" + countryName + "| already exists";
		}

		CountryModel newCountry = new CountryModel(countryName, (countryList.size()) + 1, targetContinent);
		targetContinent.addCountry(newCountry);
		countryList.add(newCountry);

		System.out.println(countryList.toString());
		return "";
	}

	/**
	 * 
	 * @param countryName
	 * @param neighbourCountryName
	 * @return
	 */
	public String addNeighbourCountry(String countryName, String neighbourCountryName) {

		if (searchCountry(neighbourCountryName) == null || searchCountry(countryName) == null) {
			// System.out.println(neighbourCountryName);
			return "Countries <" + neighbourCountryName + countryName + "> Not found";
		}

		else if (searchCountry(neighbourCountryName) != null && searchCountry(countryName) != null) {

			CountryModel neighbourCountry = searchCountry(neighbourCountryName);

			int neighbourCountryPosition = neighbourCountry.getCountryPosition();

			CountryModel countryFound = searchCountry(countryName);

			countryFound.addNeighbour(neighbourCountryPosition);

			System.out.println(countryList.toString());
		}

		return "";
	}

	/**
	 * 
	 * @param countryName
	 * @param neighbourCountryName
	 * @return
	 */

	public String deleteNeighbourCountry(String countryName, String neighbourCountryName) {

		if (searchCountry(neighbourCountryName) == null || searchCountry(countryName) == null) {
			// System.out.println(neighbourCountryName);
			return "Countries <" + neighbourCountryName + countryName + "> Not found";
		}

		else if (searchCountry(neighbourCountryName) != null && searchCountry(countryName) != null) {

			CountryModel neighbourCountry = searchCountry(neighbourCountryName);

			int neighbourCountryPosition = neighbourCountry.getCountryPosition();

			CountryModel countryFound = searchCountry(countryName);

			// countryFound.addNeighbour(neighbourCountryPosition);
			countryFound.deleteNeighbour(neighbourCountryPosition);

			System.out.println(countryList.toString());
		}

		return "";
	}

	/**
	 * 
	 * @param countryName
	 * @return
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
	 * 
	 * @param v              current bode
	 * @param visited        visted node
	 * @param connectedGraph connectedGraph as adcancy matrix
	 * @return true if graph is connected
	 */

	public boolean DFSUtil(int v, boolean[] visited, String connectedGraph[][]) {
		// Mark the current node as visited and print it

		visited[v] = true;
		// System.out.print(v + " ");

		// Recur for all the vertices adjacent to this vertex
		String[] data = connectedGraph[v];
		String[] city = connectedGraph[v];
		int i = 0;
		while (i < data.length) {
			//
			if (!visited[i]) {
				DFSUtil(i, visited, connectedGraph);
				city = connectedGraph[i];
				// System.out.println(city.toString());

			}
			i++;
		}
		boolean isConnected = true;

		for (boolean vis : visited) {
			if (!vis) {
				isConnected = false;
				break;
			}

		}
		for (int j = 0; j <= 42; j++)
			System.out.println(city[j]);
		return isConnected;
	}

	public void loadMap(String fileName) {
		
		MapLocator.mapLocation(fileName);

		ArrayList<CountryModel> loopCountryList = MapOperations.getInstance().getCountryList();

		String[][] connectedGraph = new String[loopCountryList.size() + 1][loopCountryList.size() + 1];
		for (int i = 0; i <= loopCountryList.size(); i++) {
			for (int j = 0; j <= loopCountryList.size(); j++) {
				connectedGraph[i][j] = "0";
			}
		}
		// Arrays.fill(connectedGraph,"0");
		connectedGraph[0][0] = "C/C";
		int i = 1;
		for (CountryModel loop : loopCountryList) {
			connectedGraph[i][0] = loop.getCountryName();
			connectedGraph[0][i] = loop.getCountryName();

			for (Integer j : loop.getListOfNewNeighbours()) {
				connectedGraph[i][j] = "1";
				connectedGraph[j][i] = "1";
			}
			i++;
		}
		MapOperations.getInstance().setConnectedGraph(connectedGraph);
		
		for (int k = 0; k <= loopCountryList.size(); k++) {
			for (int j = 0; j <= loopCountryList.size(); j++) {
				System.out.print(connectedGraph[k][j] + "");
			}

			System.out.println();
		}
		MapOperations.getInstance().validateMap();
		

	}

	
	 public void validateMap()
	    {
		    stack = new Stack<Integer>();
		    int source = 1;
			String[][] connectedGraph = MapOperations.getInstance().getConnectedGraph();
	        int number_of_nodes = connectedGraph[source].length - 1;
	        System.out.println(number_of_nodes);
	        int[] visited = new int[number_of_nodes + 1];
	        int i, element;
	        visited[source] = 1;
	        stack.push(source);
	        //System.out.println("after stack");
	        while (!stack.isEmpty())
	        {
	            element = stack.pop();
	            i = 1;// element;
	            while (i <= number_of_nodes)
	            {
	                if (connectedGraph[element][i] == "1" && visited[i] == 0)
	                {
	                    stack.push(i);
	                    visited[i] = 1;
	                }
	                i++;
	            }
	        }
	 
	        System.out.print("The source node " + source + " is connected to: ");
	        int count = 0;
	        for (int v = 1; v <= number_of_nodes; v++)
	            if (visited[v] == 1)
	            {
	                System.out.print(v + " ");
	                count++;
	            }
	 
	        if (count == number_of_nodes)
	            System.out.print("\nThe Graph is Connected ");
	        else
	            System.out.print("\nThe Graph is Disconnected ");
	    }
	

}
