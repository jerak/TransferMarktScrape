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

		CountryDataBaseReader countryDataBaseReader = new CountryDataBaseReader();
		List<Country> countryList = countryDataBaseReader.dataBaseReader();

		PlayerDataBaseReader playerDataBaseReader = new PlayerDataBaseReader();
		List<Player> playerList = playerDataBaseReader.dataBaseReader(countryList);

//		PlayerValueCalculator playerValueCalculator = new PlayerValueCalculator();
//		playerValueCalculator.calculatePlayerValue(playerList);

		MatchScraper matchscraper = new MatchScraper();
		List<Match> matchList = matchscraper.scraperMatches(countryList);

		CreatePersonalMatch personalMatchCreator = new CreatePersonalMatch();
		personalMatchCreator.createPersonalMatch(matchList, playerList);

		System.out.println("Calculating Points");
		PlayerPointCalculator pointCalculator = new PlayerPointCalculator();
		pointCalculator.pointCalculator(playerList);

		//		System.out.println("Writing Matches To Database");
		//		PlayerMatchDataBaseWriter dbWriterMatch = new PlayerMatchDataBaseWriter();
		//		dbWriterMatch.dataBaseWriter(playerList);
		//		
		//		System.out.println("Writing Points To Database");
		//		PlayerMatchPointsDataBaseWriter dbWriterMatchPoints = new PlayerMatchPointsDataBaseWriter();
		//		dbWriterMatchPoints.dataBaseWriter(playerList);

		for (Player player : playerList) {
			System.out.println(player.getPlayerName() + " " + player.getGroupPoints() + " " + player.getKoPoints());
		}
		
		System.out.println("Reading Squads");
		SquadReader squadReader = new SquadReader();
		List<Participant> participantList = squadReader.squadReadGroup(playerList);
		squadReader.squadReadKO(playerList, participantList);

		System.out.println("Calculating Squad Points");
		ParticipantPointCalculator participantPointCalculator = new ParticipantPointCalculator();
		participantPointCalculator.pointCalculator(participantList);

		System.out.println("Writing Player Statistics To Database");
		StatisticDataBaseWriter dbWriterStatistics = new StatisticDataBaseWriter();
		dbWriterStatistics.dataBaseWriter(playerList);

		System.out.println("Writing Topscorers to Database");
		TopScorersListDataBaseWriter dbWriterTopScorers = new TopScorersListDataBaseWriter();
		dbWriterTopScorers.dataBaseWriter(playerList);

		System.out.println("Writing Total Points to Database");
		PlayerPointDataBaseWriter dbWriterPoints = new PlayerPointDataBaseWriter();
		dbWriterPoints.dataBaseWriter(playerList);

		System.out.println("Executed successfully");
	}


}
