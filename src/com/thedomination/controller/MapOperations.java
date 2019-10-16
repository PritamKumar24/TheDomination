package com.thedomination.controller;

import java.util.ArrayList;
import java.util.Stack;

import com.thedomination.model.CountryModel;
import com.thedomination.model.PlayerModel;
import com.thedomination.model.ContinentModel;
import com.thedomination.utilities.MapLocator;
import com.thedomination.utilities.MapReader;


/**
 * The Class 
 *
 * @author Ankur Singla
 * @author Pritam Kumar
 * @version 1.0.0
 */
public class MapOperations {
	private MapReader mapReader;

	private ArrayList<ContinentModel> continentsList;

	private ArrayList<CountryModel> countryList;

	private ArrayList<ArrayList<String> > listOfConnectedNodes;

	public ArrayList<ArrayList<String>> getListOfConnectedNodes() {
		return listOfConnectedNodes;
	}


	public void setListOfConnectedNodes(ArrayList<ArrayList<String>> listOfConnectedNodes) {
		this.listOfConnectedNodes = listOfConnectedNodes;
	}

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

		if (continentName != null && !continentName.trim().isEmpty() && continentValue>0 ) {
			ContinentModel newContinent = new ContinentModel(continentName,continentValue);
			getContinentsList().add(newContinent);

		}
//		for(ContinentModel temp1:continentsList)
//		{
//			System.out.println(temp1);}	
		System.out.println("The Continent " + continentName +" has been added");
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
//		for(ContinentModel temp1:continentsList) {
//			System.out.println(temp1);
//		}
		System.out.println("The Continent " + continentName +" has been deleted");
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
		addConnectedGraphCountry(countryName);

		//System.out.println(countryList.toString());
		System.out.println("The Country " + countryName +" has been added");
		return "";
	}
	
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

	public void addNeighbourCountry(String countryName, String neighbourCountryName) {

		if (searchCountry(neighbourCountryName) == null || searchCountry(countryName) == null) {
			System.out.println("Countries |" + neighbourCountryName +" OR "+ countryName + "| Not found");
		}

		else if (searchCountry(neighbourCountryName) != null && searchCountry(countryName) !=null) {
			CountryModel neighbourCountry = searchCountry(neighbourCountryName);
			int neighbourCountryPosition = neighbourCountry.getCountryPosition();
			CountryModel countryFound = searchCountry(countryName);

			if((MapOperations.getInstance().searchNeighbourCountry(countryFound.getCountryName(), neighbourCountryPosition)) == null) {
				countryFound.addNeighbour(neighbourCountryPosition);
				addNeighbourCountry(neighbourCountryName, countryName);
				addConnectedGraphNeighbour(neighbourCountryName, countryName);
				//System.out.println(countryFound);
				System.out.println(countryName + " and " + neighbourCountryName +" are neighbours now");
			}
		}
	}
	
	public void addConnectedGraphNeighbour(String neighbourCountryName, String countryName) {
		ArrayList<ArrayList<String> > listOfConnectedNodes = MapOperations.getInstance().getListOfConnectedNodes();
		int indexCountry = listOfConnectedNodes.get(0).indexOf(countryName);
		int indexNeighbourCountry=listOfConnectedNodes.get(0).indexOf(neighbourCountryName);
		
		listOfConnectedNodes.get(indexCountry).set(indexNeighbourCountry,"1");
		listOfConnectedNodes.get(indexNeighbourCountry).set(indexCountry,"1");
	}

	public CountryModel searchNeighbourCountry(String countryName, int neighbourCountryPosition) {

		CountryModel country = MapOperations.getInstance().searchCountry(countryName);

		for(Integer tempNeighbourPosition : country.getListOfNewNeighbours()) {
			if (tempNeighbourPosition == neighbourCountryPosition) {
				return country;
			}
		}
		return null;
	}

	public void deleteNeighbourCountry(String countryName, String neighbourCountryName) {

		if (searchCountry(neighbourCountryName) == null || searchCountry(countryName) == null) {
			System.out.println("Countries |" + neighbourCountryName +" OR "+ countryName + "| Not found"); 
		}

		else if (searchCountry(neighbourCountryName) != null && searchCountry(countryName) !=null) {
			CountryModel neighbourCountry = searchCountry(neighbourCountryName);
			int neighbourCountryPosition = neighbourCountry.getCountryPosition();
			CountryModel countryFound = searchCountry(countryName);

			if((MapOperations.getInstance().searchNeighbourCountry(countryFound.getCountryName(), neighbourCountryPosition)) != null) {
				countryFound.deleteNeighbour(neighbourCountryPosition);
				deleteNeighbourCountry(neighbourCountryName, countryName);
				deleteConnectedGraphNeighbour(neighbourCountryName, countryName);
				//System.out.println(countryFound);
				System.out.println(countryName + " and " + neighbourCountryName +" are neighbours no more");
			}
		}
	}

	public void deleteConnectedGraphNeighbour(String neighbourCountryName, String countryName) {
		ArrayList<ArrayList<String> > listOfConnectedNodes = MapOperations.getInstance().getListOfConnectedNodes();
		int indexCountry = listOfConnectedNodes.get(0).indexOf(countryName);
		int indexNeighbourCountry=listOfConnectedNodes.get(0).indexOf(neighbourCountryName);
		
		listOfConnectedNodes.get(indexCountry).set(indexNeighbourCountry,"0");
		listOfConnectedNodes.get(indexNeighbourCountry).set(indexCountry,"0");
	}
	
	public String deleteCountry(String countryName) {
		
		CountryModel deleteCountry = searchCountry(countryName);
		if (deleteCountry != null) {
			deleteCountry.getBelongsTo().deleteCountry(deleteCountry);
			getCountryList().remove(deleteCountry);
			deleteCountry = null;
		} else {
			return "There is no country with this name";
		}
		//System.out.println(countryList.toString());
		System.out.println("The Country " + countryName +" has been deleted");
		DeleteConnectedGraphCountry(countryName);
		return "";
	}
	
	public void DeleteConnectedGraphCountry(String countryName) {
		ArrayList<ArrayList<String> > listOfConnectedNodes = MapOperations.getInstance().getListOfConnectedNodes();
		int index=listOfConnectedNodes.get(0).indexOf(countryName);
		
		for (int i = 0; i < listOfConnectedNodes.size(); i++) { 
			listOfConnectedNodes.get(i).remove(index);
        }
		
		listOfConnectedNodes.remove(index);
	}
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
//		for (int k = 0; k < listOfConnectedNodes.size(); k++) { 
//            for (int j = 0; j < listOfConnectedNodes.get(k).size(); j++) { 
//                System.out.print(listOfConnectedNodes.get(k).get(j) + " "); 
//            } 
//            System.out.println(); 
//        }
		
		MapOperations.getInstance().setListOfConnectedNodes(listOfConnectedNodes);

		System.out.println(MapOperations.getInstance().validateMap());
	}

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
		//System.out.println("after stack");
		while (!stack.isEmpty()) {
			element = stack.pop();
			i = 1;// element;
			while (i <= number_of_nodes) {
				if (listOfConnectedNodes.get(element).get(i) == "1" && visited[i] == 0) {
					stack.push(i);
					visited[i] = 1;
				}
				i++;
			}
		}

		//System.out.println("The source node " + source + " is connected to: ");
		int count = 0;
		for (int v = 1; v <= number_of_nodes; v++) {
			if (visited[v] == 1)
			{
				//System.out.print(v + " ");
				count++;
			}
		}
		if (count == number_of_nodes) {
			message="This is a valid Graph.";
		}  
		else {
			message="Invalid Graph - Disconnected Graph";
		}
//		System.out.println();
//		for (int k = 0; k < listOfConnectedNodes.size(); k++) { 
//            for (int j = 0; j < listOfConnectedNodes.get(k).size(); j++) { 
//                System.out.print(listOfConnectedNodes.get(k).get(j) + " "); 
//            } 
//            System.out.println(); 
//        }
		return message;
	}
	
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
	
	public String searchOnCountryPosition(Integer countryPosition) {
		for(CountryModel loopCountry : MapOperations.getInstance().getCountryList()) {
			if(loopCountry.getCountryPosition() == countryPosition) {
				return loopCountry.getCountryName();
			}
		}
		return "";
	}
	
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
