package com.thedomination.controllerTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import com.thedomination.controller.PlayerOperations;
import com.thedomination.controller.TournamentController;
import com.thedomination.model.PlayerModel;

/**
 * The tournamentTest class to check the test cases for tournament mode.
 * 
 * @author Piyush
 *
 */
class TournamentControllerTest {
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * The tournamentTesting test case to check tournament mode.
	 */
	@Test
	void tournamentTesting() {
		TournamentController.getInstance().setMapFiles("domination.map");

		TournamentController.getInstance().setPlayerStrategy("Cheater");

		TournamentController.getInstance().setPlayerName("Ravi");
		PlayerModel currentPlayer = PlayerOperations.getInstance().searchPlayer("Ravi");

		TournamentController.getInstance().setPlayerStrategy("Benevolent");

		TournamentController.getInstance().setPlayerName("Kishan");
		PlayerModel currentPlayer1 = PlayerOperations.getInstance().searchPlayer("Kishan");

		TournamentController.getInstance().setNoOfGames(5);

		TournamentController.getInstance().setNoOfTurns(8);

		TournamentController.getInstance().startTournament();

	}

}
