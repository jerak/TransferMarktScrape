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
			setConnection(DataBaseConnection.establishConnection("jdbc:mysql://localhost:3306/playerscores"));

			//Clear current Table
			String sqlClearTable = "DELETE FROM spelersstatistieken";
			PreparedStatement ps = con.prepareStatement(sqlClearTable);
			ps.executeUpdate();

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

			for (Player player : playerList) {
				for (PersonalGame game : player.getPersonalGameList()) {
					ps.setString(1, player.getPlayerName());
					ps.setString(2, booleanToText(game.isStartingSquad()));
					ps.setString(3, booleanToText(game.isSubIn()));
					ps.setString(4, booleanToText(game.isSubOut()));
					ps.setString(5, booleanToText(game.getKeepZero()));
					ps.setInt(6, game.getGoals());
					ps.setInt(7, game.getHitPenalty());
					ps.setInt(8, game.getOwnGoal());
					ps.setInt(9, game.getAssists());
					ps.setInt(10, game.getYellowCard());
					ps.setInt(11, game.getDoubleYellowCard());
					ps.setInt(12, game.getStraightRedCard());
					ps.setInt(13, game.getMissedPenalty());
					ps.setInt(14, game.getSavedPenalty());
					ps.setInt(15, game.getHitPenaltyShootOut());
					ps.setInt(16, game.getMissedPenaltyShootOut());
					ps.setInt(17, game.getSavedPenaltyShootOut());

					ps.executeUpdate();
				}

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
