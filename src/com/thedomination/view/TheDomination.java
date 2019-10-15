package com.thedomination.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.thedomination.controller.MapOperations;
import com.thedomination.model.CountryModel;
import com.thedomination.utilities.MapLocator;
import com.thedomination.utilities.MapReader;

/**
 * The Class Main.
 *
 * @author Ankur Singla
 * @version 1.0.0
 */

public class TheDomination {
	
	
	//static ArrayList<CountryModel> loopCountryList = MapOperations.getInstance().getCountryList();
	//System.out.println(loopCountryList);

	//static String [][] connectedGraph = new String[loopCountryList.size()+1][loopCountryList.size()+1];
	
	
	
	public static void main(String args[]) {
		commandReader();
	}

	public static void exit() {  
		System.exit(0);
	}

	public static void commandReader() {
		Scanner scan = new Scanner(System.in);

		String readLine= scan.nextLine();

		if(readLine.equalsIgnoreCase("exit")) {
			exit();
		}
		else {
			String[] inputCommand = readLine.split("\\s+");
			if((inputCommand[0]).equalsIgnoreCase("editcontinent")) {
				for(int i=1;i<inputCommand.length;i++) {
					if((inputCommand[i]).equalsIgnoreCase("-add")) { 
						MapOperations.getInstance().addContinent(inputCommand[i+1],Integer.parseInt(inputCommand[i+2]));
						i=i+2;
					}
					else if((inputCommand[i]).equalsIgnoreCase("-remove")) {
						MapOperations.getInstance().deleteContinent(inputCommand[i+1]);
						i=i+1;
					}
				}
			}
			else if((inputCommand[0]).equalsIgnoreCase("editcountry")) {
				for(int i=1;i<inputCommand.length;i++) {
					if((inputCommand[i]).equalsIgnoreCase("-add")) {
						MapOperations.getInstance().addCountry(inputCommand[i+1],inputCommand[i+2]);
						i=i+2;
					}
					else if((inputCommand[i]).equalsIgnoreCase("-remove")) {
						MapOperations.getInstance().deleteCountry(inputCommand[i+1]);
						i=i+1;
					}
				}
			}
			else if((inputCommand[0]).equalsIgnoreCase("editneighbor")) {
				for(int i=1;i<inputCommand.length;i++) {
					if((inputCommand[i]).equalsIgnoreCase("-add")) {
						MapOperations.getInstance().addNeighbourCountry(inputCommand[i+1],inputCommand[i+2]);
						i=i+2;
					}
					else if((inputCommand[i]).equalsIgnoreCase("-remove")) {
						MapOperations.getInstance().deleteNeighbourCountry(inputCommand[i+1],inputCommand[i+2]);
						i=i+2;
					}
				}
			}
			else if(inputCommand[0].equalsIgnoreCase("loadmap")) {
				for (int i = 1; i < inputCommand.length; i++) {
					MapOperations.getInstance().loadMap(inputCommand[i]);
				}
			}
			else if(inputCommand[0].equalsIgnoreCase("validatemap")) {
				MapOperations.getInstance().validateMap();
			}
			
				commandReader();
		}
	}
}
