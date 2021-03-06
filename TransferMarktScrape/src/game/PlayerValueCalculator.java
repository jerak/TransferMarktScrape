package game;

import java.util.List;

import attributes.Player;

public class PlayerValueCalculator {

	public void calculatePlayerValue(List<Player> playerList) {

		int playerValue;
		int playerValueWebsite;
		double countryValueWebsite;
		int playerAge;

		for (Player player : playerList) {

			//Retrieve the required information
			playerValueWebsite = player.getPlayerValueWebsite();
			countryValueWebsite = player.getCountry().getCountryValue();
			playerAge = player.getAge();

			//Scale the playerValue of the website
			int playerValueWebsiteScaled = (int) (playerValueWebsite/PlayerValueParameters.scalePlayerValue);
			int countryValueWebsiteScaled = (int) (countryValueWebsite/PlayerValueParameters.scaleCountryValue);

			//Increase the value of the player if player has certain age			
			double ageFactor = 1;
			if (playerAge == 32) {
				ageFactor = PlayerValueParameters.ageFactor32;
			}
			else if (playerAge == 33) {
				ageFactor = PlayerValueParameters.ageFactor33;
			}
			else if (playerAge == 34) {
				ageFactor = PlayerValueParameters.ageFactor34;
			}
			else if (playerAge == 35) {
				ageFactor = PlayerValueParameters.ageFactor35;
			}
			else if (playerAge >= 36) {
				ageFactor = PlayerValueParameters.ageFactor36andOlder;
			}

			playerValue = (int) (ageFactor * playerValueWebsiteScaled * PlayerValueParameters.multiplierWebSiteValue 
					+ PlayerValueParameters.multiplierCountryValue * countryValueWebsiteScaled) +
					PlayerValueParameters.minimumPlayerValue; 
			int playerValueRounded = (int) playerValue /250000;
			playerValueRounded = playerValueRounded * 250000;

			player.setPlayerValue(playerValueRounded);
		}

	}


}
