package com.thedomination.view;

import java.util.Scanner;
import com.thedomination.controller.MapOperations;
import com.thedomination.controller.PlayerOperations;
import com.thedomination.controller.SaveMapFile;

/**
 * The Class Main.
 *
 * @author Ankur Singla
 * @version 1.0.0
 */

public class TheDomination {

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
				System.out.println(MapOperations.getInstance().validateMap());
			}
			else if (inputCommand[0].equalsIgnoreCase("gameplayer")) {
				for(int i=1;i<inputCommand.length;i++) {
					if((inputCommand[i]).equalsIgnoreCase("-add")) {
						PlayerOperations.getInstance().addPlayer(inputCommand[i+1]);
						i=i+1;
					}
					else if((inputCommand[i]).equalsIgnoreCase("-remove")) {
						PlayerOperations.getInstance().removePlayer(inputCommand[i+1]);
						i=i+1;
					}
				}
				}

			else  if(inputCommand[0].equalsIgnoreCase("populatecountries")) {
				PlayerOperations.getInstance().populateCountries();
			}
			
			else if (inputCommand[0].equalsIgnoreCase("placearmy")) {
				PlayerOperations.getInstance().placeArmy(inputCommand[1]);
			}
			
			else if (inputCommand[0].equalsIgnoreCase("placeall")) {
				PlayerOperations.getInstance().placeAll();
			}
			
			else if(inputCommand[0].equalsIgnoreCase("fortify")) {
				if(inputCommand[1].equalsIgnoreCase("none")) {
					PlayerOperations.getInstance().fortifyCountry(inputCommand[1],"","");
				}
				else {
					PlayerOperations.getInstance().fortifyCountry(inputCommand[1] , inputCommand[2], inputCommand[3]);
				}
				
			}
			else if(inputCommand[0].equalsIgnoreCase("showmap")) {
				if(PlayerOperations.getInstance().getPlayersList().size()==0) {
					MapOperations.getInstance().showMapEditor();	
				}
				else {
					MapOperations.getInstance().showMapGamePlay();
				}
			}
			else if((inputCommand[0]).equalsIgnoreCase("savemap")) {
			      SaveMapFile smf = new SaveMapFile();
			      smf.getMapOperationConcateString(MapOperations.getInstance(), inputCommand[1]);
			      smf.saveMapFile(MapOperations.getInstance(), inputCommand[1]);
			      System.out.println(inputCommand[1]+ " Map file has been created");
	
			}
			else if (inputCommand[0].equalsIgnoreCase("reinforce")) {
				PlayerOperations.getInstance().reInforce(inputCommand[1], Integer.parseInt(inputCommand[2]));
			}
		}

		commandReader();
	}
}

