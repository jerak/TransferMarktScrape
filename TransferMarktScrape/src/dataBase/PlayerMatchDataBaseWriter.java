package dataBase;

import java.sql.PreparedStatement;
import java.util.List;

import com.mysql.jdbc.Connection;

import attributes.PersonalGame;
import attributes.Player;
import run.WebsiteDetails;

public class PlayerMatchDataBaseWriter {

	private Connection con;

	private void setConnection(Connection connection){
		con = connection;
	}

	public void dataBaseWriter(List<Player> playerList) {

		try {
			setConnection(DataBaseConnection.establishConnection("jdbc:mysql://localhost:3306/playerscores"));	


			//Clear current Table
			String sqlClearTable = "DELETE FROM spelerwedstrijden";
			PreparedStatement ps = con.prepareStatement(sqlClearTable);
			ps.executeUpdate();

			//Fill table with players
			for (Player player : playerList) {
				String mySQLString = makeStringDependentOfMatches(WebsiteDetails.speelDagen);
				ps = con.prepareStatement(mySQLString);
				List<PersonalGame> gameList = player.getPersonalGameList();
				ps.setString(1, player.getPlayerName());

				for (int i = 0; i <= gameList.size()-1; i++) {
					ps.setString(2+i, gameList.get(i).getMatchName());
				}
				for (int i = gameList.size(); i <= WebsiteDetails.speelDagen-1; i++) {
					ps.setString(2+i, null);
				}
				ps.executeUpdate();
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String makeStringDependentOfMatches(int Speeldagen) {
		String sqlString = "insert into spelerwedstrijden(naam";
		for (int i = 1; i <= Speeldagen; i++) {
			sqlString = sqlString + ",wedstrijd" + i;
		}

		sqlString = sqlString + ")values(?";

		for (int i = 1; i <= Speeldagen; i++) {
			sqlString = sqlString + ",?";
		}

		sqlString = sqlString + ")";
		return sqlString;
	}
}
