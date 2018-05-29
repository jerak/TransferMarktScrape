package dataBase;

import java.sql.PreparedStatement;
import java.util.List;

import com.mysql.jdbc.Connection;

import attributes.PersonalGame;
import attributes.Player;

public class StatisticDataBaseWriter {


	private Connection con;

	private void setConnection(Connection connection){
		con = connection;
	}

	public void dataBaseWriter(List<Player> playerList) {

		try {
			setConnection(DataBaseConnection.getConnection());

			//Clear current Table
			String sqlClearTable = "DELETE FROM spelersstatistieken";
			PreparedStatement ps = con.prepareStatement(sqlClearTable);
			ps.executeUpdate();

			for (Player player : playerList) {
				//Fill table with players
				String mySQLString = "insert into spelersstatistieken("+
						"naam," +
						"basis," + 
						"invalbeurten," +
						"uitvalbeurten,"+ 
						"nulgehouden,"+
						"regulieregoal,"+ 
						"regulierepenalties,"+ 
						"eigendoelpunten," +
						"assists," +
						"gelekaarten," +
						"tweedegelekaarten," +
						"directrodekaarten," +
						"penaltygemist," +
						"penaltygestopt," +
						"shootoutgeraakt," +
						"shootoutgemist," +
						"shootoutgestopt" +
						")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				ps = con.prepareStatement(mySQLString);

				int totalNumberOfStartingSquad = 0;
				int totalNumberOfSubIn = 0;
				int totalNumberofSubOut = 0;
				int totalNumberOfKeepZero = 0;
				int totalNumberofGoals = 0;
				int totalNumberOfPenaltyHit = 0;
				int totalNumberOfOwnGoals = 0;
				int totalNumberOfAssists = 0;
				int totalNumberOfYellow = 0;
				int totalNumberOfDoubleYellow = 0;
				int totalNumberOfRed = 0;
				int totalNumberOfMissed = 0;
				int totalNumberOfSaved = 0;
				int totalNumberOfPenaltySO = 0;
				int totalNumberOfMissedSO = 0;
				int totalNumberOfSavedSO = 0;
				for (PersonalGame game : player.getPersonalGameList()) {
					totalNumberOfStartingSquad += (game.isStartingSquad()  ? 1 : 0);
					totalNumberOfSubIn += (game.isSubIn()  ? 1 : 0);
					totalNumberofSubOut += (game.isSubOut()  ? 1 : 0);
					totalNumberOfKeepZero += (game.getKeepZero()  ? 1 : 0);
					totalNumberofGoals += game.getGoals();
					totalNumberOfPenaltyHit += game.getHitPenalty();
					totalNumberOfOwnGoals += game.getOwnGoal();
					totalNumberOfAssists += game.getAssists();
					totalNumberOfYellow += game.getYellowCard();
					totalNumberOfDoubleYellow += game.getDoubleYellowCard();
					totalNumberOfRed += game.getStraightRedCard();
					totalNumberOfMissed += game.getMissedPenalty();
					totalNumberOfSaved += game.getSavedPenalty();
					totalNumberOfPenaltySO += game.getHitPenaltyShootOut();
					totalNumberOfMissedSO += game.getMissedPenaltyShootOut();
					totalNumberOfSavedSO += game.getSavedPenaltyShootOut();	
				}

				ps.setString(1, player.getPlayerName());
				ps.setInt(2, totalNumberOfStartingSquad);
				ps.setInt(3, totalNumberOfSubIn);
				ps.setInt(4, totalNumberofSubOut);
				ps.setInt(5, totalNumberOfKeepZero);
				ps.setInt(6, totalNumberofGoals);
				ps.setInt(7, totalNumberOfPenaltyHit);
				ps.setInt(8, totalNumberOfOwnGoals);
				ps.setInt(9, totalNumberOfAssists);
				ps.setInt(10, totalNumberOfYellow);
				ps.setInt(11, totalNumberOfDoubleYellow);
				ps.setInt(12, totalNumberOfRed);
				ps.setInt(13, totalNumberOfMissed);
				ps.setInt(14, totalNumberOfSaved);
				ps.setInt(15, totalNumberOfPenaltySO);
				ps.setInt(16, totalNumberOfMissedSO);
				ps.setInt(17, totalNumberOfSavedSO);
				ps.executeUpdate();
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String booleanToText(boolean Bool) {
		if (Bool) {
			return "Yes";
		} else
			return "No";
	}

}
