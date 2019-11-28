package com.thedomination.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.thedomination.builder.ConcreteGameBuilder;
import com.thedomination.builder.GameBuilder;
import com.thedomination.builder.GameDirector;
import com.thedomination.controller.CardOperations;
import com.thedomination.controller.MapOperations;
import com.thedomination.controller.PlayerOperations;
import com.thedomination.view.DominationCardView;
import com.thedomination.view.DominationPhaseView;
import com.thedomination.view.WorldDominationView;
/**
 *The BenevolentPlayer Class.
 * implements Strategy and Serailizable Class,
 * Initializes ReinforcementPhase Attack Phase and Fortification Phase.
 *
 * @author Pritam Kumar
 */
public class BenevolentPlayer implements Strategy, Serializable{

	/** Generated Serilaized Id */
	private static final long serialVersionUID = 1L;
	/** DominationPhase  Object*/
	private DominationPhase dominationPhase;
	/** WorldDomination  Object*/
	private WorldDomination worldDomination;
	/** WorldDominationView  Object*/
	private WorldDominationView worldDominationView;
	/** DominationPhaseView  Object*/
	private DominationPhaseView dominationPhaseView;
	/** DominationCards  Object*/
	private DominationCards dominationCard;
	/** DominationCardView  Object*/
	private DominationCardView dominationCardView;

	/**
	 * Constructor for BenevolentPlayer class.
	 * 
	 */
	public BenevolentPlayer() {
		dominationPhase=new DominationPhase();
		worldDomination=new WorldDomination();
		worldDominationView=new WorldDominationView(worldDomination);
		dominationPhaseView=new DominationPhaseView(dominationPhase);
		dominationCard = new DominationCards();
		dominationCardView = new DominationCardView(dominationCard);
	}
	/**
	 * Method for reinforcementPhase.
	 * @param countryName name of the new country.
	 * @param num number of Player.
	 * @return Desired Message
	 * 	 */
	@Override
	public String reinforcementPhase(String countryName, int num) {
		PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());

		//exchange cards
		System.out.println(CardOperations.getInstance().selfCardExchange(currentPlayer.getCardList()));

		//get & set the reinforcement armies to player
		PlayerOperations.getInstance().getReInforcementArmies();
		currentPlayer.setnoOfArmyInPlayer(PlayerOperations.getInstance().getReInforceNoOfArmy());

		System.out.println("Benevolent Reinforcement Phase Starts - player name " + currentPlayer.getPlayerName());
		System.out.println();
		while (currentPlayer.getnoOfArmyInPlayer() > 0) {

			CountryModel countryModel = getCountryWithMinArmies(currentPlayer);
			System.out.println("Reinforcement done on " + countryModel.getCountryName());
			countryModel.addNoOfArmiesCountry();
			currentPlayer.reduceArmyInPlayer();

			//Call to Observer
			PlayerOperations.getInstance().playerWorldDominationStateChange(currentPlayer);
		}
		System.out.println("Benevolent Reinforcement Phase Ends - player name " + currentPlayer.getPlayerName());

		// Trigerring the attack phase
		dominationPhase.setCurrentGamePhase(DominationPhaseType.ATTACK);
		dominationPhase.setCurrentPlayerName(currentPlayer.getPlayerName());
		dominationPhase.setCurrentAction("Starting Attack");

		return "";
	}

	/**
	 * Method for attackPhase.
	 * @param countrynamefrom name of the country.
	 * @param countrynameto name of the country.
	 * @param numdice dice number
	 * 
	 * 
	 **/
	@Override
	public void attackPhase(String countrynamefrom, String countrynameto, int numdice) {
		PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());
		System.out.println("Benevolent attack start player name " + currentPlayer.getPlayerName());
		System.out.println("Choose not to attack");
		System.out.println("Benevolent attack end player name " + currentPlayer.getPlayerName());
		System.out.println();

		//Triggering Fortify Phase
		dominationPhase.setCurrentGamePhase(DominationPhaseType.FORTIFY);
		dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
		dominationPhase.setCurrentAction("Starting Fortify");
	}

	/**
	 * Method for FortificationPhase.
	 * @param countrynamefrom name of the country.
	 * @param countrynameto name of the country.
	 * @param numdice dice number
	 * 
	 * */
	@Override
	public String fortificationPhase(String fromCountry, String toCountry, String num) {

		String message ="";
		PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());
		System.out.println("Benevolent Fortification Phase Starts - player name " + currentPlayer.getPlayerName());
		System.out.println();
		if(currentPlayer.getPlayerCountryList().size() < 2) {
			System.out.println("Fortification Not possible"); 
			System.out.println();
			System.out.println("Benevolent Fortification Phase Ends - player name " + currentPlayer.getPlayerName());

			PlayerOperations.getInstance().setPlayerCounter(PlayerOperations.getInstance().getPlayerCounter() +1);
			CardOperations.getInstance().setCardExchangeFlag(true);

			//save to the file
			System.out.println();
			GameDirector gameDirector = new GameDirector();
			GameBuilder gameBuilder = new ConcreteGameBuilder();
			gameDirector.setGameBuilder(gameBuilder);
			gameDirector.buildGame();
			gameDirector.saveGame("AutoSavedGame");


			//Triggering phase view observer		
			dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
			dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
			dominationPhase.setCurrentAction("Starting Card Exchange");
			//Call to Card Exchange View
			dominationCard.setPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
			dominationCard.setListCards(CardOperations.getInstance().cardStrings(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getCardList()));

			return message;
		}

		for(int i=0; i<currentPlayer.getPlayerCountryList().size(); i++) {
			CountryModel tempCountry = currentPlayer.getPlayerCountryList().get(i);
			if(tempCountry.getNoOfArmiesCountry()>1) {
				break;
			}
			else if(i == currentPlayer.getPlayerCountryList().size()-1) {
				System.out.println("Not enough armies for fortification ");
				System.out.println();
				System.out.println("Benevolent Fortification Phase Ends - player name " + currentPlayer.getPlayerName());

				PlayerOperations.getInstance().setPlayerCounter(PlayerOperations.getInstance().getPlayerCounter() +1);
				CardOperations.getInstance().setCardExchangeFlag(true);

				System.out.println();
				GameDirector gameDirector = new GameDirector();
				GameBuilder gameBuilder = new ConcreteGameBuilder();
				gameDirector.setGameBuilder(gameBuilder);
				gameDirector.buildGame();
				gameDirector.saveGame("AutoSavedGame");

				//Triggering phase view observer		
				dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
				dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
				dominationPhase.setCurrentAction("Starting Card Exchange");
				//Call to Card Exchange View
				dominationCard.setPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
				dominationCard.setListCards(CardOperations.getInstance().cardStrings(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getCardList()));

				return message;
			}
		}

		List <CountryModel> sortedList= sortCountries();

		for(CountryModel loopSortedList : sortedList) {
			for(CountryModel neighbourCountry : currentPlayer.getPlayerCountryList()) {
				if(!(neighbourCountry.getCountryName().equalsIgnoreCase(loopSortedList.getCountryName())) 
						&& neighbourCountry.getNoOfArmiesCountry()>1
						&& MapOperations.getInstance().searchNeighbourCountry(loopSortedList.getCountryName(), neighbourCountry.getCountryPosition())!=null) {

					loopSortedList.setNoOfArmiesCountry(loopSortedList.getNoOfArmiesCountry()+(neighbourCountry.getNoOfArmiesCountry()-1));
					neighbourCountry.setNoOfArmiesCountry(1);
					System.out.println("Fortification Done from " + loopSortedList.getCountryName() + " to " + neighbourCountry.getCountryName());
					System.out.println();
					System.out.println("Benevolent Fortification Phase Ends - player name " + currentPlayer.getPlayerName());

					PlayerOperations.getInstance().setPlayerCounter(PlayerOperations.getInstance().getPlayerCounter() +1);
					CardOperations.getInstance().setCardExchangeFlag(true);

					System.out.println();
					GameDirector gameDirector = new GameDirector();
					GameBuilder gameBuilder = new ConcreteGameBuilder();
					gameDirector.setGameBuilder(gameBuilder);
					gameDirector.buildGame();
					gameDirector.saveGame("AutoSavedGame");


					//Triggering phase view observer		
					dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
					dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
					dominationPhase.setCurrentAction("Starting Card Exchange");
					//Call to Card Exchange View
					dominationCard.setPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
					dominationCard.setListCards(CardOperations.getInstance().cardStrings(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getCardList()));

					return message;
				}	
			}
		}

		System.out.println("No country found for fortification");
		System.out.println();
		System.out.println("Benevolent Fortification Phase Ends - player name " + currentPlayer.getPlayerName());

		PlayerOperations.getInstance().setPlayerCounter(PlayerOperations.getInstance().getPlayerCounter() +1);
		CardOperations.getInstance().setCardExchangeFlag(true);

		GameDirector gameDirector = new GameDirector();
		GameBuilder gameBuilder = new ConcreteGameBuilder();
		gameDirector.setGameBuilder(gameBuilder);
		gameDirector.buildGame();
		gameDirector.saveGame("AutoSavedGame");

		//Phase View
		dominationPhase.setCurrentGamePhase(DominationPhaseType.REINFORCEMENT);
		dominationPhase.setCurrentPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
		dominationPhase.setCurrentAction("Starting Card Exchange");

		//Card View
		dominationCard.setPlayerName(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getPlayerName());
		dominationCard.setListCards(CardOperations.getInstance().cardStrings(PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter()).getCardList()));

		return "";
	}

	/**
	 * Method for allOutAttack.
	 * @param countrynamefrom name of the country.
	 * @param countrynameto name of the country.
	 * 
	 * */
	@Override
	public void allOutAttack(String countrynamefrom, String countrynameto) {
	}

	/**
	 * method to attack
	 * @param num of armies
	 */
	@Override
	public void attackMove(int num) {
	}

	/**
	 * method to defend
	 * @param num of dice
	 */
	@Override
	public void defendCountry(int numdice) {
	}


	/**
	 * get the countries with minimum army
	 * @param playerModel
	 * @return
	 */
	private CountryModel getCountryWithMinArmies(PlayerModel playerModel) {
		CountryModel countryModel = playerModel.getPlayerCountryList().get(0);
		int noOfArmies = countryModel.getNoOfArmiesCountry();
		for (CountryModel country : playerModel.getPlayerCountryList()) {
			if (country.getNoOfArmiesCountry() < noOfArmies) {
				countryModel = country;
				noOfArmies = country.getNoOfArmiesCountry();
			}
		}
		return countryModel;
	}

	/**
	 * Method to sort the countries based on armies	
	 * @return sortedList of countries based on armies
	 */

	public List<CountryModel> sortCountries() {
		PlayerModel currentPlayer = PlayerOperations.getInstance().currentPlayer(PlayerOperations.getInstance().getPlayerCounter());
		List<CountryModel> sortedList = new ArrayList<CountryModel>(currentPlayer.getPlayerCountryList());
		Collections.sort(sortedList, new SortCountriesWithArmies());
		return sortedList;
	}
}

/**
 * sorts the countries according to armies
 * @author Manpreet Singh
 *
 */
class SortCountriesWithArmies implements Comparator<CountryModel>{
	@Override
	public int compare(CountryModel firstCountry, CountryModel secondCountry) {
		return firstCountry.getNoOfArmiesCountry() - secondCountry.getNoOfArmiesCountry();
	}

}
