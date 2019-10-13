package com.thedomination.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.thedomination.controller.MapOperations;
import com.thedomination.model.CountryModel;
import com.thedomination.model.PlayerModel;
import com.thedomination.utilities.MapLocator;
import com.thedomination.utilities.MapReader;

/**
 * The Class Main.
 *
 * @author Ankur Singla
 * @version 1.0.0
 */

public class TheDomination {

	static ArrayList<String>playerNameList= new ArrayList<String>();
	static  int playerno=0;

	public static void main(String args[]) {
		commandReader();
	}

	public static void exit() {
		System.exit(0);
	}

	public static void commandReader() {
		Scanner scan = new Scanner(System.in);

		String readLine= scan.nextLine();
		 ArrayList<String>countList= new ArrayList<String>();



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
				MapLocator.mapLocation(inputCommand[1]);
				ArrayList<CountryModel> loopCountryList = MapOperations.getInstance().getCountryList();
				String [][] connectedGraph = new String[loopCountryList.size()+1][loopCountryList.size()+1];
				for(int i=0;i<=loopCountryList.size();i++) {
					for(int j=0;j<=loopCountryList.size();j++) {
						connectedGraph[i][j]="0";
					}
				}
//				Arrays.fill(connectedGraph,"0");
				connectedGraph[0][0]="C/C";
				int i=1;
				for(CountryModel loop:loopCountryList) {
					connectedGraph[i][0]=loop.getCountryName() ;
					connectedGraph[0][i]=loop.getCountryName();

					for(Integer j:loop.getListOfNewNeighbours()) {
						connectedGraph[i][j]="1";
						connectedGraph[j][i]="1";
//						System.out.println("j=" + j);
//						System.out.println("Country Position" + loop.getCountryPosition());
//						System.out.println();
//						while(j==loop.getCountryPosition()) {
//							connectedGraph[i][j]="1";
//							connectedGraph[j][i]="1";
//						}
					}
					i++;
				}
				for(int k=0;k<=loopCountryList.size();k++) {
					for(int j=0;j<=loopCountryList.size();j++) {
						System.out.print(connectedGraph[k][j] +"");
					}
				System.out.println();
				}

			}
			else if (inputCommand[0].equalsIgnoreCase("gameplayer")) {

				  for (int i = 1; i < inputCommand.length; i++) {

					  countList.add(inputCommand[i]);
				}

				  for (int i = 0; i < countList.size(); i++) {


					  if(countList.get(i).equalsIgnoreCase("-add"))
					  {

							if(playerNameList!=null) {


								if(!playerNameList.contains(countList.get(i+1))) {


									playerNameList.add(countList.get(i+1));
									System.out.println("Player " + countList.get(i+1)+ " has been added");
								}else {
									System.out.println("Player " + countList.get(i+1)+ " already exists");
								}
							}
					  }

					  else if(countList.get(i).equalsIgnoreCase("-remove"))
					  {
							if(playerNameList!=null) {
								if(playerNameList.contains(countList.get(i+1))) {
									playerNameList.remove(countList.get(i+1));
									System.out.println("Player " + countList.get(i+1)+ " has been removed");
								}else {
									System.out.println("Player " + countList.get(i+1)+ " does not exist");
								}
							}
					  }
				}

				  playerno=playerNameList.size();


				}

			else  if(inputCommand[0].equalsIgnoreCase("populateCountries")) {
				PlayerModel p=new PlayerModel();

				p.populateCountries(playerno);
			}
			}

			commandReader();
		}
	}

