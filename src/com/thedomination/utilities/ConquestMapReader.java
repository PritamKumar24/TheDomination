package com.thedomination.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import com.thedomination.controller.MapOperations;
import com.thedomination.model.ContinentModel;
import com.thedomination.model.CountryModel;



public class ConquestMapReader {

	ArrayList<ContinentModel> continentModels = new ArrayList<>();
	ArrayList<CountryModel> countryModels = new ArrayList<>();

	public boolean riskmap=false;

	int countryPosition=0;

	public boolean isRiskmap() {
		return riskmap;
	}

	public void setRiskmap(boolean riskmap) {
		this.riskmap = riskmap;
	}

//	public boolean isConquestmap() {
//		return MapLocator.conquestmap;
//	}
//
//	public void setConquestmap(boolean conquestmap) {
//		MapLocator.conquestmap = conquestmap;
//	}

	public MapOperations parseAndValidateConuestMap(String filePath) {

		boolean isContinent = false;
		boolean isCountry = false;
		String[] neighbourCountries = null;
		String targetCountry = null;

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				if (currentLine.isEmpty())
					continue;
				if (currentLine.equalsIgnoreCase("[Continents]")) {
					isContinent = true;
					isCountry = false;
					continue;
				}

				if (currentLine.equalsIgnoreCase("[Continents]")) {
					isContinent = true;
					isCountry = false;
					continue;
				}
				if (currentLine.equalsIgnoreCase("[Territories]")) {
					isCountry = true;
					//setConquestmap(true);
					MapOperations.getInstance().conquestMap = true;
					isContinent = false;
					if (!isContinent && continentModels.size() == 0) {
						String valErrorMessage = "Map is invalid as there are no continents defined";
						MapOperations.getInstance().setValErrorFlag(true);
						MapOperations.getInstance().setErrorMsg(valErrorMessage);
						break;
					}

					continue;
				}

				if (isContinent) {
					if (currentLine.indexOf('=') > 0) {
						String[] continentValues = currentLine.split("=");
						if (continentValues.length > 0) {
							ContinentModel continentModel = new ContinentModel(continentValues[0]);
							if (continentValues.length > 1)
								continentModel.setControlValue(Integer.valueOf(continentValues[1]));
							continentModels.add(continentModel);
						}
					} else {
						String valErrorMessage = "Map is invalid as there are no territories tag defined";
						MapOperations.getInstance().setValErrorFlag(true);
						MapOperations.getInstance().setErrorMsg(valErrorMessage);
						break;
					}
					continue;
				}

				if (isCountry) {
					String[] countryValues = currentLine.split(",");
					if (countryValues.length < 5) {
						targetCountry = countryValues[0];
					}
					CountryModel countryModel = new CountryModel();
					for (int i = 0; i < countryValues.length; i++) {
						if (i == 0) {
							countryModel.setCountryName(countryValues[i]);
							countryModel.setCountryPosition(++countryPosition);
						} else if (i == 1 || i == 2) {
							// do nothing as we have skipped the coordinates
						} else if (i == 3) {
							ContinentModel continentModel = new ContinentModel(countryValues[i]);
							countryModel.setBelongsTo(continentModel);
						} else {
							countryModel.ConquestaddNeighbour(countryValues[i]);

						}
					}
					countryModels.add(countryModel);
					if (countryValues.length > 0) {
						for (ContinentModel continentModelValue : continentModels) {
							if (continentModelValue.getContinentName().trim()
									.equalsIgnoreCase(countryModel.getBelongsTo().getContinentName().trim())) {
								continentModelValue.addCountry(countryModel);
							}
						}

					}
					targetCountry = countryValues[0];
				}


			}
			if(MapOperations.getInstance().conquestMap==false)
			return null;

			MapOperations.getInstance().setContinentsList(continentModels);
			if (countryModels.size() == 0 && !MapOperations.getInstance().isValErrorFlag()) {
				String valErrorMessage = "Map is invalid as there are no countries defined.";
				MapOperations.getInstance().setValErrorFlag(true);
				MapOperations.getInstance().setErrorMsg(valErrorMessage);
				return MapOperations.getInstance();
			} else if (countryModels.size() < 3 && !MapOperations.getInstance().isValErrorFlag()) {
				String valErrorMessage = "Map is invalid as there should be minimum three countries defined in the map as there are minimum three players which can play.";
				MapOperations.getInstance().setValErrorFlag(true);
				MapOperations.getInstance().setErrorMsg(valErrorMessage);
				return MapOperations.getInstance();
			} else {
				MapOperations.getInstance().setCountryList(countryModels);

			}
			fillNeighboursList();
			validateIfCountryHasNeighbour(targetCountry, countryModels, MapOperations.getInstance());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return MapOperations.getInstance();
	}

			public void validateIfCountryHasNeighbour(String targetCountry, ArrayList<CountryModel> countryModels,
					MapOperations mapHierarchyModel) {
				// TODO Auto-generated method stub
				boolean neighbourFlag = false;
				if (targetCountry != null) {
					for (CountryModel loopCountry : countryModels) {
						ArrayList<String> neighbours = loopCountry.getListOfNeighbours();
						for (String country : neighbours) {
							if (country.equalsIgnoreCase(targetCountry)) {
								neighbourFlag = true;
							}
						}
					}
					if (neighbourFlag) {
						System.out.println("Map is valid");
					}
					else{
						String valErrorMessage = "Map is invalid as there is no connectivity from or to country "
								+ targetCountry;
						mapHierarchyModel.setValErrorFlag(true);
						mapHierarchyModel.setErrorMsg(valErrorMessage);
				}

				}
			}

	public void fillNeighboursList() {
		for(CountryModel sourceCountry : countryModels) {
			for(CountryModel neighbourCountry : countryModels) {
				if(sourceCountry.searchNeighboursCountry(neighbourCountry.getCountryName()) !=null) {
					sourceCountry.getListOfNewNeighbours().add(countryModels.indexOf(neighbourCountry)+1);
				}
			}
		}
	}

}
