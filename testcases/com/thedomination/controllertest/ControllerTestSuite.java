
package com.thedomination.controllerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


import com.thedomination.controllerTest.PlayerOperationTest;



/**
 * controllerTestSuite main test suite for controllerTestSuite.
 * 
 * @author Piyush
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ MapOperationTest.class, PlayerOperationTest.class  ,GameDirectorTest.class,TournamentControllerTest.class })
public class ControllerTestSuite {

}
