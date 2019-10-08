package com.thedomination.view;

import java.util.HashMap;
import java.util.Scanner;

import com.thedomination.controller.MapOperations;
import com.thedomination.utilities.MapReader;

/**
 * The Class MapHierarchyModel.
 *
 * @author Ankur Singla
 * @version 1.0.0
 */

public class TheDomination {


	MapReader mapReader;


	public static void main(String ar[]) {
		MapReader mapReader = new MapReader();

		mapReader.parseAndValidateMap("C:/Users/Dell/Downloads/APP/world.map");
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

			commandReader();
		}
	}
}
