package run;

import java.io.IOException;
import java.util.List;
import attributes.*;
import dataBase.*;
import game.*;
import scrape.*;

public class Engine {

	public void run() throws IOException {
		//Establishing connection which can be used throughout the program (only 1 connection established)
		DataBaseConnection.establishConnection();

		CountryPlayerScraper playerScraper = new CountryPlayerScraper();
		List<Country> countryList = playerScraper.scrapeTeamList();
		playerScraper.scrapeCountryFlags();
		playerScraper.scrapeTeamStats();
		List<Player> playerList = playerScraper.scrapePlayerList();
		playerScraper.checkDuplicatePlayers();
		
		PlayerValueCalculator playerValueCalculator = new PlayerValueCalculator();
		playerValueCalculator.calculatePlayerValue(playerList);
		
		PlayerDataBaseWriter dbWriterPlayer = new PlayerDataBaseWriter();
		dbWriterPlayer.dataBaseWriter(playerList);

		CountryWriter countryWriter = new CountryWriter();
		countryWriter.dataBaseWriter(countryList);

		System.out.println("Executed successfully");
	}


}
