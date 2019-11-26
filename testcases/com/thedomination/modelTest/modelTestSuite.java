package com.thedomination.modelTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * The model test suite for the test cases in modelTest.
 * 
 * @author Piyush
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ ContinentTest.class, PlayerModelTest.class,HumanPlayerTest.class })
public class modelTestSuite {

}
