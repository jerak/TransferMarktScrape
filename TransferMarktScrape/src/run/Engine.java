package run;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import attributes.*;
import dataBase.*;
import game.*;
import scrape.*;

public class Engine {

	public void run() throws IOException {
		//Establishing connection which can be used throughout the program (only 1 connection established)

		CountryDataBaseReader countryDataBaseReader = new CountryDataBaseReader();
		List<Country> countryList = countryDataBaseReader.dataBaseReader();

		PlayerDataBaseReader playerDataBaseReader = new PlayerDataBaseReader();
		List<Player> playerList = playerDataBaseReader.dataBaseReader(countryList);

		PlayerValueCalculator playerValueCalculator = new PlayerValueCalculator();
		playerValueCalculator.calculatePlayerValue(playerList);

		MatchScraper matchscraper = new MatchScraper();
		List<Match> matchList = matchscraper.scraperMatches(countryList);

		CreatePersonalMatch personalMatchCreator = new CreatePersonalMatch();
		personalMatchCreator.createPersonalMatch(matchList, playerList);

		PlayerPointCalculator pointCalculator = new PlayerPointCalculator();
		pointCalculator.pointCalculator(playerList);

		PlayerMatchDataBaseWriter dbWriterMatch = new PlayerMatchDataBaseWriter();
		dbWriterMatch.dataBaseWriter(playerList);

		PlayerMatchPointsDataBaseWriter dbWriterMatchPoints = new PlayerMatchPointsDataBaseWriter();
		dbWriterMatchPoints.dataBaseWriter(playerList);

		StatisticDataBaseWriter dbWriterStatistics = new StatisticDataBaseWriter();
		dbWriterStatistics.dataBaseWriter(playerList);

		TopScorersListDataBaseWriter dbWriterTopScorers = new TopScorersListDataBaseWriter();
		dbWriterTopScorers.dataBaseWriter(playerList);

		PlayerPointDataBaseWriter dbWriterPoints = new PlayerPointDataBaseWriter();
		dbWriterPoints.dataBaseWriter(playerList);

		//SquadReader squadReader = new SquadReader();
		//List<Participant> participantList = squadReader.squadReadGroup(countryList);
		//squadReader.squadReadKO(countryList, participantList);

		//ParticipantPointCalculator participantPointCalculator = new ParticipantPointCalculator();
		//participantPointCalculator.pointCalculator(participantList);

		System.out.println("Executed successfully");
	}


}
