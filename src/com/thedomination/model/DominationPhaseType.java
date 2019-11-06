
package com.thedomination.model;

/**
 * <h2> Domination Phase Type Class </h2>
 * This class is used to display the name of the game phase currently being played.
 *
 * @author Aditi Bhayana
 * @version 2.0
 */
public enum DominationPhaseType {
	
	/** The startup. */
	STARTUP("Startup"),
	
	/** The reinforcement. */
	REINFORCEMENT("Reinforcement"),
	
	/** The attack. */
	ATTACK("Attack"),
	
	/** The fortify. */
	FORTIFY("Fortify");
	
	/** The phase name. */
	private final String phaseName;
	
	/**
	 * Instantiates a new Phase name.
	 *
	 * @param phaseName the name of phase
	 */
	private DominationPhaseType(String phaseName) {
		this.phaseName = phaseName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return this.phaseName;
	}

}
