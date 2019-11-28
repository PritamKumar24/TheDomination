/**
 * 
 */
package com.thedomination.model;

/**
 * The Interface Strategy.
 *
 * @author Ankur Singla
 */
public interface Strategy {

	/**
	 * Reinforcement phase.
	 *
	 * @param countryName
	 * @param num
	 */
	public String reinforcementPhase(String countryName, int num);

	/**
	 * Fortification phase.
	 *
	 * @param fromCountry
	 * @param toCountry
	 * @param num
	 */
	public String fortificationPhase(String fromCountry,String toCountry, String num);

	/**
	 * Attack phase.
	 *
	* @param fromCountry
	 * @param toCountry
	 * @param num
	 */
	public void attackPhase(String countrynamefrom, String countrynameto, int numdice);

	/**
	 * allOutAttack
	 @param fromCountry attack
	 * @param toCountry attack
	 */

	public void allOutAttack(String countrynamefrom, String countrynameto);
	/**
	 * attackMove
	 *
	 * @param num number of armies to move
	 */
	public void attackMove(int num);
	/**
	 * defendCountry
	 *
	 * @param numdice number of dices defender will use
	 */
	public void defendCountry(int numdice);


}
