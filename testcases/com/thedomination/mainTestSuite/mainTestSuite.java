package com.thedomination.mainTestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * The main test suite for all the classes.
 * 
 * @author Piyush
 *
 */
@RunWith(Suite.class)
@SuiteClasses({com.thedomination.controllerTest.ControllerTestSuite.class,com.thedomination.modelTest.modelTestSuite.class,com.thedomination.utilitiesTest.utilitiesTestSuite.class})
public class mainTestSuite {

}
