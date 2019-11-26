package com.thedomination.controllerTest;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.thedomination.builder.ConcreteGameBuilder;
import com.thedomination.builder.GameBuilder;
import com.thedomination.builder.GameDirector;
import com.thedomination.controller.MapOperations;
import com.thedomination.controller.PlayerOperations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.thedomination.builder.ConcreteGameBuilder;
import com.thedomination.builder.GameBuilder;
import com.thedomination.builder.GameDirector;
import com.thedomination.controller.MapOperations;
import com.thedomination.controller.PlayerOperations;
/**
 * The Unit Test  Class for  GameDirectorTest.
 */
public class GameDirectorTest {


	
	
	/**
	 * Sets the up before class.
	 * 
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	/**
	 * Tear down after class.
	 * 
	 * @throws Exception the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * setting up the values of continent and countries before conducting test.
	 * 
	 * @throws Exception the exception
	 */
	/**
	 *  Unit Test SaveGame used to check the saving of the Game
	 * 
	 */
@Test
public void saveGameTest() {
MapOperations.getInstance().loadMap("Asia.map");

GameDirector gameDirector = new GameDirector();
GameBuilder gameBuilder = new ConcreteGameBuilder();
gameDirector.setGameBuilder(gameBuilder);
assertNotNull(gameDirector.saveGame("new12"));

}

/**
 *  Unit Test to LoadGame  used to check the loading  of the Game
 * 
 */
@Test
public void loadGameTest() {
//LoadGameFile loadGame = new LoadGameFile();
GameDirector gameDirector = new GameDirector();
GameBuilder gameBuilder = new ConcreteGameBuilder();
gameDirector.setGameBuilder(gameBuilder);
String File = gameDirector.loadGameModel("");
assertNotNull(File);
}

}
