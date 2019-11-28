package com.thedomination.view;

import java.util.Scanner;
import com.thedomination.controller.MapOperations;
import com.thedomination.controller.PlayerOperations;
import com.thedomination.utilities.ReadInputCommand;

/**
 * TheDomination is the main class that reads all the command from console and calls the corresponding methods.
 *
 * @author Ankur Singla
 * @version 1.0.0
 */

public class TheDomination {

	/**
	 * The main method that calls commandReader.
	 * 
	 * @param args command line arguments.
	 */
	public static void main(String args[]) {
		System.out.println("Welcome to Domination GAME!!!");
		System.out.println();
		
		System.out.println("Game Starting");
		System.out.println("Please type the desired command :");
		ReadInputCommand.commandReader();
	}

	
}

