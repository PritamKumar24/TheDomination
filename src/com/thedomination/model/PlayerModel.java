package com.thedomination.model;

import java.util.*;





public class PlayerModel {

  static int count=0;
 public static  int armiesToAssign;
  public int armiesAvailableToAssign = -1;


  static ArrayList<String>playerNameList= new ArrayList<String>();

  public static ArrayList<String> gamePlayer(String command)
  {
	  ArrayList<String>countList= new ArrayList<String>();

	  String[] countPlayer=command.split(" ");

	  for (int i = 1; i < countPlayer.length; i++) {

		  countList.add(countPlayer[i]);
	}


	  for (int i = 0; i < countList.size(); i++) {


		  if(countList.get(i).equalsIgnoreCase("-add"))
		  {

				if(playerNameList!=null) {


					if(!playerNameList.contains(countList.get(i+1))) {


						playerNameList.add(countList.get(i+1));
						System.out.println("Player " + countList.get(i+1)+ " has been added");

						count++;
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
						count--;
						System.out.println("Player " + countList.get(i+1)+ " has been removed");
					}else {
						System.out.println("Player " + countList.get(i+1)+ " does not exist");
					}
				}
		  }
	}





	return playerNameList;




  }

  private HashMap<String, PlayerModel> players = new HashMap<>();
 // private HashMap<String, Country> countries = new HashMap<>();
  //private HashMap<String, Continent> continents = new HashMap<>();
  public void populateCountries(int playerno) {



      switch (playerno) {
          case 2:
              armiesToAssign = 40;
              System.out.println("Players have been assigned 40 armies.");
              break;
          case 3:
              armiesToAssign = 35;
              System.out.println("Players have been assigned 40 armies.");
              break;
          case 4:
              armiesToAssign = 30;
              System.out.println("Players have been assigned 30 armies.");
              break;
          case 5:
              armiesToAssign = 25;
              System.out.println("Players have been assigned 25 armies.");
              break;
          case 6:
              armiesToAssign = 20;
              System.out.println("Players have been assigned 20 armies.");
              break;
      }


  }
}









