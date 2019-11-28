package com.thedomination.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.thedomination.controller.CardOperations;
import com.thedomination.controller.MapOperations;
import com.thedomination.controller.PlayerOperations;
import com.thedomination.controller.TournamentController;
import com.thedomination.utilities.ReadInputCommand;
import com.thedomination.view.DominationCardView;
import com.thedomination.view.DominationPhaseView;
import com.thedomination.view.WorldDominationView;

/**
 *The HumanPlayer Class.
 * implements Strategy and Serailizable Class,
 * Initializes ReinforcementPhase Attack Phase and Fortification Phase.
 *
 * @author Ankur Singla
 */
public class HumanPlayer implements Strategy, Serializable{

	/** Generated Serilaized Id */
	private static final long serialVersionUID = 1L;
	/** DominationPhase Object */
	private DominationPhase dominationPhase;
	/** WorldDomination Object */
	private WorldDomination worldDomination;
	/** WorldDominationView Object */
	private WorldDominationView worldDominationView;
	/** DominationPhaseView Object */
	private DominationPhaseView dominationPhaseView;
	/** DominationCards Object */
	private DominationCards dominationCard;
	/** DominationCardView Object */
	private DominationCardView dominationCardView;

	/**
	 * Constructor for HumanPlayer class.
	 * 
	 */

	public HumanPlayer() {
		
		dominationPhase=new DominationPhase();
		worldDomination=new WorldDomination();
		worldDominationView=new WorldDominationView(worldDomination);
		dominationPhaseView=new DominationPhaseView(dominationPhase);
		dominationCard = new DominationCards();
		dominationCardView = new DominationCardView(dominationCard);

	}

	/**
	 * Method for reinforcementPhase.
	 * 
	 * @param countryName name of the new country.
	 * @param num         number of Player.
	 * @return Desired Message
	 */
	@Override
	public String reinforcementPhase(String countryName, int num) {
		String message="";
		if(PlayerOperations.getInstance().reinforceArmyFlag == false) {
			message = "Illegal Command";
			return message;
		}
		PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());
		CountryModel countryAssignedArmy = currentPlayer.searchCountry(countryName);
		if(countryAssignedArmy == null) {
			message = countryName + " doesn't belong to this player. TRY AGAIN!!";
			return message;
		}
		System.out.println(currentPlayer.getPlayerName()+" is going to reinforce his armies..");
		PlayerOperations.getInstance().getReInforcementArmies();
		
		currentPlayer.setnoOfArmyInPlayer(PlayerOperations.getInstance().getReInforceNoOfArmy());

		while(PlayerOperations.getInstance().getReInforceNoOfArmy()>0) {
			if(num>PlayerOperations.getInstance().getReInforceNoOfArmy() || num<=0) {
				System.out.println("!!!!Reinforcement Not Possible. Invalid no of armies !!!!");
				break;
			}

			countryAssignedArmy.setNoOfArmiesCountry(countryAssignedArmy.getNoOfArmiesCountry() + num);
			currentPlayer.setnoOfArmyInPlayer(currentPlayer.getnoOfArmyInPlayer()-num);
			PlayerOperations.getInstance().setReInforceNoOfArmy(PlayerOperations.getInstance().getReInforceNoOfArmy() - num);
			System.out.println("No of Armies left to assign "+ PlayerOperations.getInstance().getReInforceNoOfArmy());
			PlayerOperations.getInstance().playerWorldDominationStateChange(currentPlayer);

			break;
		}

		if(PlayerOperations.getInstance().getReInforceNoOfArmy() == 0) {
			PlayerOperations.getInstance().attackFlag =true;
			PlayerOperations.getInstance().reinforceArmyFlag = false;

			// Trigerring the attack phase
			dominationPhase.setCurrentGamePhase(DominationPhaseType.ATTACK);
			dominationPhase.setCurrentPlayerName(currentPlayer.getPlayerName());
			dominationPhase.setCurrentAction("Starting Attack");
		}
		return message;
	}

	/**
	 * Method for attackPhase.
	 * 
	 * @param countrynamefrom name of the country.
	 * @param countrynameto   name of the country.
	 * @param numdice         dice number
	 * 
	 */

	public void attackPhase(String countrynamefrom, String countrynameto, int numdice) {

		if (PlayerOperations.getInstance().attackFlag == false) {
			System.out.println("Invalid move");
		}
		else if(countrynamefrom.equalsIgnoreCase("-noattack")) {
			PlayerOperations.getInstance().fortifyArmyFlag = true;
			PlayerOperations.getInstance().attackFlag = false;
			System.out.println("No attack chosen");

			//Triggering Fortify Phase
			dominationPhase.setCurrentGamePhase(DominationPhaseType.FORTIFY);
			dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
			dominationPhase.setCurrentAction("Starting Fortify");
			

		}
		else {
			PlayerOperations.getInstance().defendFlag = false;
			PlayerOperations.getInstance().moveArmyFlag = false;
				
			PlayerOperations.getInstance().countrynamefrom = countrynamefrom;
			PlayerOperations.getInstance().countrynameto = countrynameto;
			
			int diceAttack[] = new int[numdice];

			PlayerModel tempPlayerModelAttackCountry = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());
			CountryModel loopCountryFrom = tempPlayerModelAttackCountry.searchCountry(countrynamefrom);

			CountryModel loopCountryTo = PlayerOperations.getInstance().modelOfDefender(countrynameto);
			int count=0;
			for(CountryModel loopCountry : tempPlayerModelAttackCountry.getPlayerCountryList()) {
				if(loopCountry.getNoOfArmiesCountry()<=1) {
					count++;
				}
			}
			if(count == tempPlayerModelAttackCountry.getPlayerCountryList().size()) {
				PlayerOperations.getInstance().attackFlag = false;

				System.out.println("Player doesnot have enough armies to attack any other territory. choose -noattack command");
			}
			else if(loopCountryFrom == null) {
				System.out.println("Attacking country doesnot belong to the player");
			}
			else if(tempPlayerModelAttackCountry.searchCountry(countrynameto) != null){
				System.out.println("Defending country cannot belong to the same player");
			}
			else if(MapOperations.getInstance().searchNeighbourCountry(countrynamefrom, PlayerOperations.getInstance().modelOfDefender(countrynameto).getCountryPosition()) == null) {
				System.out.println("Both of the countries should be neighbors");
			}
			else if(loopCountryFrom.getNoOfArmiesCountry()<2) {
				System.out.println("Armies of attacking country should be more than 1");
			}
			else if(loopCountryTo.getNoOfArmiesCountry()<1) {
				System.out.println("Defending country doesnot have any army");
			}
			else if(numdice>3 || numdice>loopCountryFrom.getNoOfArmiesCountry()-1 || numdice<=0) {
				System.out.println("Invalid number of dice");
			}
			else {
				System.out.print("Dice roll value ");
				System.out.println();
				for(int i=0; i<numdice; i++) {
					diceAttack[i]=PlayerOperations.getInstance().rollDice();
					System.out.print(diceAttack[i] + " ");
				}
				System.out.println();
				PlayerOperations.getInstance().setDiceAttackArray(PlayerOperations.getInstance().sortArray(diceAttack));
				System.out.println("Player " + tempPlayerModelAttackCountry.getPlayerName() + " is ready to attack");
				PlayerOperations.getInstance().defendFlag=true;
				PlayerOperations.getInstance().attackFlag = false;
			}		
		}

	}

	/**
	 * Method for allOutAttack.
	 * 
	 * @param countrynamefrom name of the country.
	 * @param countrynameto   name of the country.
	 * 
	 */
	@Override
	public void allOutAttack(String countrynamefrom, String countrynameto) {

		if(PlayerOperations.getInstance().attackFlag == false) {
			System.out.println("Invalid move");
		}
		else {
			PlayerOperations.getInstance().defendFlag=false;
			PlayerOperations.getInstance().moveArmyFlag = false;


			PlayerOperations.getInstance().countrynamefrom = countrynamefrom;
			PlayerOperations.getInstance().countrynameto = countrynameto;
			
			PlayerModel tempPlayerModelAttackCountry = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());
			CountryModel loopCountryFrom = tempPlayerModelAttackCountry.searchCountry(countrynamefrom);
			PlayerOperations.getInstance().attackFlag=true;

			CountryModel loopCountryTo = PlayerOperations.getInstance().modelOfDefender(countrynameto);
			int count=0;

			for(CountryModel loopCountry : tempPlayerModelAttackCountry.getPlayerCountryList()) {
				if(loopCountry.getNoOfArmiesCountry()<=1) {
					count++;
				}
			}
			if(count == tempPlayerModelAttackCountry.getPlayerCountryList().size()) {
				PlayerOperations.getInstance().fortifyArmyFlag = true;
				PlayerOperations.getInstance().attackFlag = false;
				System.out.println("Player doesnot have enough armies to attack any other territory. choose -noattack command");

			}
			else if(loopCountryFrom == null) {
				System.out.println("Attacking country doesnot belong to the player");
			}
			else if(tempPlayerModelAttackCountry.searchCountry(countrynameto) != null){
				System.out.println("Defending country cannot belong to the same player");
			}
			else if(MapOperations.getInstance().searchNeighbourCountry(countrynamefrom, PlayerOperations.getInstance().modelOfDefender(countrynameto).getCountryPosition()) == null) {
				System.out.println("Both of the countries should be neighbors");
			}	
			else if(loopCountryFrom.getNoOfArmiesCountry()<2) {
				System.out.println("Armies of attacking country should be more than 1");
			}
			else if(loopCountryTo.getNoOfArmiesCountry()<1) {
				System.out.println("Defending country doesnot have any army");
			}
			else {
				while(PlayerOperations.getInstance().attackFlag) {
					int diceAttack[] = new int[tempPlayerModelAttackCountry.searchCountry(countrynamefrom).getNoOfArmiesCountry() > 3 ? 3 : tempPlayerModelAttackCountry.searchCountry(countrynamefrom).getNoOfArmiesCountry()-1];
					System.out.println("Attacker dice roll");
					for(int i=0; i<(diceAttack.length);i++) {
						diceAttack[i]=PlayerOperations.getInstance().rollDice();
						System.out.print(diceAttack[i] + " ");
					}
					System.out.println();
					//PlayerOperations.getInstance().setDiceAttackArray(PlayerOperations.getInstance().sortArray(diceAttack));
					PlayerOperations.getInstance().setDiceAttackArray(PlayerOperations.getInstance().sortArray(diceAttack));
					int diceDefend[]=new int[loopCountryTo.getNoOfArmiesCountry() > 2 ? 2 : loopCountryTo.getNoOfArmiesCountry()];
					System.out.println("Defender dice roll");
					for(int i=0; i<(diceDefend.length);i++) {
						diceDefend[i]=PlayerOperations.getInstance().rollDice();
						System.out.print(diceDefend[i] + " ");
					}
					System.out.println();

					PlayerOperations.getInstance().setDiceDefendArray(PlayerOperations.getInstance().sortArray(diceDefend));
					
					for(int i = 0;i<(PlayerOperations.getInstance().getDiceDefendArray().length > PlayerOperations.getInstance().getDiceAttackArray().length ? PlayerOperations.getInstance().getDiceAttackArray().length : PlayerOperations.getInstance().getDiceDefendArray().length);i++) {
						if((PlayerOperations.getInstance().getDiceAttackArray()[i] <= PlayerOperations.getInstance().getDiceDefendArray()[i])) {
						
							loopCountryFrom.setNoOfArmiesCountry(loopCountryFrom.getNoOfArmiesCountry()-1);
							System.out.println("Attacker looses one army");
							System.out.println(loopCountryFrom.getCountryName() + " has armies " + loopCountryFrom.getNoOfArmiesCountry());

							//Observer call.
							PlayerOperations.getInstance().playerWorldDominationStateChange(tempPlayerModelAttackCountry);
						}
						else {
							loopCountryTo.setNoOfArmiesCountry(loopCountryTo.getNoOfArmiesCountry()-1);
							System.out.println("Defender looses one army");
							System.out.println(loopCountryTo.getCountryName() + " has armies " + loopCountryTo.getNoOfArmiesCountry());
							PlayerOperations.getInstance().playerWorldDominationStateChange(PlayerOperations.getInstance().returnDefendModel(countrynameto));
						}						
					}
					System.out.println();
					if(loopCountryTo.getNoOfArmiesCountry() == 0) {
						PlayerOperations.getInstance().attackFlag = false;
						PlayerOperations.getInstance().moveArmyFlag=true;
						System.out.println("Attacker has won " + countrynameto);
						
						System.out.println("Now move armies from " + loopCountryFrom.getCountryName() + " to " + loopCountryTo.getCountryName());
					}
					else if(loopCountryFrom.getNoOfArmiesCountry() == 1){
						PlayerOperations.getInstance().attackFlag = true;
						PlayerOperations.getInstance().moveArmyFlag = false;
						System.out.println("Attacker has lost" );
						System.out.println("Attacking country cannot attack any more. 1 army left on attacking country");
						System.out.println("Armies on " + countrynameto + " " + loopCountryTo.getNoOfArmiesCountry());
						break;
					}
				}
			}
		}
	}
	/**
	 * defendCoubtry method to 
	 * 
	 * @param numdice number of dice
	 */
	public void defendCountry(int numdice) {

		if(PlayerOperations.getInstance().defendFlag == false) {
			System.out.println("Invalid move");
		}
		else {
			PlayerOperations.getInstance().moveArmyFlag = false;
			PlayerOperations.getInstance().attackFlag = false; 
			PlayerModel tempPlayerModelAttackCountry = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());
			CountryModel loopCountryFrom = tempPlayerModelAttackCountry.searchCountry(PlayerOperations.getInstance().countrynamefrom);


			CountryModel loopCountryTo = PlayerOperations.getInstance().modelOfDefender(PlayerOperations.getInstance().countrynameto);

			if(numdice>2 || numdice>loopCountryTo.getNoOfArmiesCountry() || numdice<=0) {
				System.out.println("Invalid number of dice");
			}
			else {
				int diceDefend[] = new int[numdice];
				System.out.print("Dice roll value ");
				for(int i=0; i<numdice; i++) {
					diceDefend[i]=PlayerOperations.getInstance().rollDice();
					System.out.print(diceDefend[i] + " ");
				}
				System.out.println();

				PlayerOperations.getInstance().setDiceDefendArray(PlayerOperations.getInstance().sortArray(diceDefend));

				for(int i = 0;i<(PlayerOperations.getInstance().getDiceDefendArray().length > PlayerOperations.getInstance().getDiceAttackArray().length ? PlayerOperations.getInstance().getDiceAttackArray().length : PlayerOperations.getInstance().getDiceDefendArray().length);i++) {
					if((PlayerOperations.getInstance().getDiceAttackArray()[i] <= PlayerOperations.getInstance().getDiceDefendArray()[i])) {
						
						loopCountryFrom.setNoOfArmiesCountry(loopCountryFrom.getNoOfArmiesCountry()-1);
						System.out.println("Attacker looses one army");
						System.out.println(loopCountryFrom.getCountryName() + " has armies " + loopCountryFrom.getNoOfArmiesCountry());


						//Call of Observer Pattern
						PlayerOperations.getInstance().playerWorldDominationStateChange(tempPlayerModelAttackCountry);
					}
					else {
						loopCountryTo.setNoOfArmiesCountry(loopCountryTo.getNoOfArmiesCountry()-1);
						System.out.println("Defender looses one army");
						System.out.println(loopCountryTo.getCountryName() + " has armies " + loopCountryTo.getNoOfArmiesCountry());

						//call of obeserver
						PlayerOperations.getInstance().playerWorldDominationStateChange(PlayerOperations.getInstance().returnDefendModel(PlayerOperations.getInstance().countrynameto));
					}
					PlayerOperations.getInstance().defendFlag = false;
					PlayerOperations.getInstance().attackFlag = true;
				}

				if(loopCountryTo.getNoOfArmiesCountry() == 0) {
					System.out.println("Attacker has won " + PlayerOperations.getInstance().countrynameto);
					System.out.println("Armies on " + PlayerOperations.getInstance().countrynamefrom + " " + loopCountryFrom.getNoOfArmiesCountry());
					PlayerOperations.getInstance().moveArmyFlag=true;
					PlayerOperations.getInstance().attackFlag = false;
					PlayerOperations.getInstance().defendFlag = false;
					System.out.println("Now move the armies from " + PlayerOperations.getInstance().countrynamefrom + " " + PlayerOperations.getInstance().countrynameto);
				}
			}
		}
	}

	/**
	 * attackMove method to make a moving attack.
	 * @param num number of attacking armies.
	 */
	public void attackMove(int num) {
		if(PlayerOperations.getInstance().moveArmyFlag) {
			if(num>PlayerOperations.getInstance().modelOfDefender(PlayerOperations.getInstance().countrynamefrom).getNoOfArmiesCountry()-1 || num<PlayerOperations.getInstance().getDiceAttackArray().length) {
				System.out.println("Invalid number of armies.");
			}
			else {
				PlayerOperations.getInstance().modelOfDefender(PlayerOperations.getInstance().countrynameto).setNoOfArmiesCountry(num);
				PlayerOperations.getInstance().modelOfDefender(PlayerOperations.getInstance().countrynamefrom).setNoOfArmiesCountry(PlayerOperations.getInstance().modelOfDefender(PlayerOperations.getInstance().countrynamefrom).getNoOfArmiesCountry()-num);
				System.out.println("Armies have been moved");
				System.out.println("Armies on " + PlayerOperations.getInstance().countrynameto + " " + PlayerOperations.getInstance().modelOfDefender(PlayerOperations.getInstance().countrynameto).getNoOfArmiesCountry());
				System.out.println("Armies on " + PlayerOperations.getInstance().countrynamefrom + " " + PlayerOperations.getInstance().modelOfDefender(PlayerOperations.getInstance().countrynamefrom).getNoOfArmiesCountry());
				PlayerOperations.getInstance().moveArmyFlag = false;
				PlayerOperations.getInstance().attackFlag = true;
				PlayerModel defender = PlayerOperations.getInstance().returnDefendModel(PlayerOperations.getInstance().countrynameto);

				CountryModel removeCountry = PlayerOperations.getInstance().returnDefendModel(PlayerOperations.getInstance().countrynameto).RemoveCountry(PlayerOperations.getInstance().modelOfDefender(PlayerOperations.getInstance().countrynameto));

				//reset the continent control
				if(removeCountry.getBelongsTo().isHasWonContinent()==true) {
					ContinentModel tempContinent = MapOperations.getInstance().searchContinentWithCountryName(removeCountry.getCountryName());  
					tempContinent.setHasWonContinent(false);

				}
				PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerCountryList().add(removeCountry);

				//Call to observer for attacker
				PlayerOperations.getInstance().playerWorldDominationStateChange(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()));

				//Call to observer for defender
				PlayerOperations.getInstance().playerWorldDominationStateChange(defender);

				//Card got as player conquered a country
				CardOperations.getInstance().assignCard(true, PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()));

				if(defender.getPlayerArmiesInCountries() == 0 && defender.getPlayerCountryList().size() == 0) {
					System.out.println();
					System.out.println("****"+defender.getPlayerName() + " has lost the game."+"****");

					//remove cards of lost player and assign it to current player
					for(CardsModel tempCard : defender.getCardList()) {
						PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getCardList().add(tempCard);
					}
					defender.setCardList(null);
					
					PlayerOperations.getInstance().setLostPlayers(defender.getPlayerName());
					if(PlayerOperations.getInstance().getPlayerCounter() > PlayerOperations.getInstance().getPlayersList().indexOf(defender)+1) {
						PlayerOperations.getInstance().setPlayerCounter(PlayerOperations.getInstance().getPlayerCounter() -1);
					}
					PlayerOperations.getInstance().getPlayersList().remove(defender);
				}
				if(PlayerOperations.getInstance().getPlayersList().size()==1) {
					System.out.println();
					System.out.println("*****"+PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName()+" HAS WON THE GAME" +"*****");
					System.out.println();
					System.out.println("******* GAME END ************");
					
					if(TournamentController.getInstance().tournamentFlag == false)
						System.exit(0);
				}

			}

		}
		else {
			System.out.println("Illegal move");
		}
	}

	/**
	 * Method for FortificationPhase.
	 * 
	 * @param countrynamefrom name of the country.
	 * @param countrynameto   name of the country.
	 * @param numdice         dice number
	 * 
	 */
	@Override
	public String fortificationPhase(String fromCountry,String toCountry, String num) {
		String message="";
		if(PlayerOperations.getInstance().fortifyArmyFlag) {
			if(fromCountry.equalsIgnoreCase("-none")) {
				System.out.println("No countries chosen for fortification");
				PlayerOperations.getInstance().setPlayerCounter(PlayerOperations.getInstance().getPlayerCounter() +1);
				
				PlayerOperations.getInstance().fortifyArmyFlag = false;
				PlayerOperations.getInstance().reinforceArmyFlag = false;
				CardOperations.getInstance().setCardExchangeFlag(true);

				
				
				//Triggering Reinforcement phase
				dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
				dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
				dominationPhase.setCurrentAction("Starting Card Exchange");

				// Triggering Card exchange view 
				dominationCard.setPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
				dominationCard.setListCards(CardOperations.getInstance().cardStrings(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getCardList()));
							
				return message;
			}

			PlayerModel tempPlayerModel = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());
			CountryModel tempFromCountryModel = tempPlayerModel.searchCountry(fromCountry);
			CountryModel tempToCountryModel = tempPlayerModel.searchCountry(toCountry);
			if(tempFromCountryModel == null || tempToCountryModel == null) {
				message = "Fortification Not possible";
			}
			else if(Integer.parseInt(num)<=0 || Integer.parseInt(num)>tempFromCountryModel.getNoOfArmiesCountry()-1) {
				message="Invalid number of armies ";
			}
			else if(MapOperations.getInstance().searchNeighbourCountry(tempFromCountryModel.getCountryName(),tempToCountryModel.getCountryPosition()) == null) {
				message = "Countries are not neighbors, fortification Not possible";
			}
			else if(tempFromCountryModel.getNoOfArmiesCountry() > 1 && tempFromCountryModel.getNoOfArmiesCountry() > Integer.parseInt(num)) {
				tempToCountryModel.setNoOfArmiesCountry((tempToCountryModel.getNoOfArmiesCountry()+Integer.parseInt(num)));
				tempFromCountryModel.setNoOfArmiesCountry((tempFromCountryModel.getNoOfArmiesCountry()-Integer.parseInt(num)));
				System.out.println("Fortification Done.");
				message="";
				PlayerOperations.getInstance().fortifyArmyFlag = false;
				PlayerOperations.getInstance().setPlayerCounter(PlayerOperations.getInstance().getPlayerCounter() +1);
				PlayerOperations.getInstance().reinforceArmyFlag = false;
				//reinforceFlag = false;
				CardOperations.getInstance().setCardExchangeFlag(true);
				
				//Call to Card Exchange View
				//Triggering phase view observer		
				dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
				dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
				dominationPhase.setCurrentAction("Starting Card Exchange");

				dominationCard.setPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
				dominationCard.setListCards(CardOperations.getInstance().cardStrings(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getCardList()));

			}
			else {
				message="Fortification Not possible";		
			}
		}
		else {
			message = "Illegal Move";
		}
		return message;
	}
}
