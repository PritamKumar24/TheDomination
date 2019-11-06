package com.thedomination.maintestsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.thedomination.controllertest.MapOperationTest;
import com.thedomination.controllertest.PlayerOperationTest;
import com.thedomination.modeltest.ContinentTest;
import com.thedomination.modeltest.PlayerModelTest;
import com.thedomination.utilitiestest.MapReaderTest;

/**
 * MainTestSuite for all the test classes.
 * @author Piyush
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ PlayerOperationTest.class, MapOperationTest.class,
		ContinentTest.class,MapReaderTest.class ,PlayerModelTest.class,MapReaderTest.class,PlayerObserverTest.class})
public class mainTestSuite {

}
