package view;

/**
 *
 */


import java.awt.Canvas;



/**
 * Game class Main class of project
 *
 * @author Aditi
 */
public class Game {
	private static final long serialVersionUID = -4669025547633011027L;

	/**
	 * Game class Constructor
	 */
	public Game() {

	}

	/**
	 * Main method of Game Object of MainMenuScreen is created initialScreen is Set
	 * visible true {@code 	initialScreen.setVisible(true); }
	 *
	 * @param args argument of main function
	 */
	public static void main(String[] args) {
		MainMenuScreen initialScreen = new MainMenuScreen();
		initialScreen.setVisible(true);
	}
}
