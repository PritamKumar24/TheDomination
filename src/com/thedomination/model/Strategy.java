/**
 * 
 */
package com.thedomination.model;

/**
 * The Interface Strategy.
 *
 * @author Ankur
 */
public interface Strategy {

	/**
	 * Reinforcement phase.
	 *
	 * @param 
	 * @param 
	 */
	public String reinforcementPhase(String countryName, int num);

	/**
	 * Fortification phase.
	 *
	 * @param 
	 * @param 
	 */
	public void attackPhase(String countrynamefrom, String countrynameto, int numdice);

	/**
	 * Assign 
	 *
	 * @param 
	 * @param 
	 */

}
