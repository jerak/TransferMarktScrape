package dataBase;

import java.sql.PreparedStatement;
import java.util.List;

import com.mysql.jdbc.Connection;

import attributes.PersonalGame;
import attributes.Player;


public class PlayerMatchPointsDataBaseWriter {

	private Connection con;

	private void setConnection(Connection connection){
		con = connection;
	}
	
	public void dataBaseWriter(List<Player> playerList) {

		try {
			setConnection(DataBaseConnection.establishConnection("jdbc:mysql://localhost:3306/playerscores"));

			//Clear current Table
			String sqlClearTable = "DELETE FROM puntenperwedstrijd";
			PreparedStatement ps = con.prepareStatement(sqlClearTable);
			ps.executeUpdate();

			//Fill table with players
			for (Player player : playerList) {
				for (PersonalGame game : player.getPersonalGameList()) {
					String mySQLString = "insert into puntenperwedstrijd(naam,wedstrijdnaam,punten)values(?,?,?)";
					ps = con.prepareStatement(mySQLString);
					ps.setString(1, player.getPlayerName());
					ps.setString(2, game.getMatchName());
					ps.setInt(3, game.getPoints());
					ps.executeUpdate();
				}
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
