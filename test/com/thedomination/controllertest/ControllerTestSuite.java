package com.thedomination.controllertest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * ControllerTestSuite for MapOperationTest ,PlayerObserverTest ,PlayerOperationTest.
 * @author Piyush
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ PlayerOperationTest.class, MapOperationTest.class , PlayerObserverTest.class })
public class ControllerTestSuite {

}
