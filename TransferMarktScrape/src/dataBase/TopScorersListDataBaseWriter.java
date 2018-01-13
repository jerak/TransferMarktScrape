package dataBase;


import java.sql.PreparedStatement;
import java.util.List;

import com.mysql.jdbc.Connection;

import attributes.Player;


public class TopScorersListDataBaseWriter {

	private Connection con;
	
	private void setConnection(Connection connection){
		con = connection;
	}
	
	public void dataBaseWriter(List<Player> playerList) {

		try {
			setConnection(DataBaseConnection.establishConnection("jdbc:mysql://localhost:3306/playerscores"));

			//Clear current Table
			String sqlClearTable = "DELETE FROM spelergoals";
			PreparedStatement ps = con.prepareStatement(sqlClearTable);
			ps.executeUpdate();

			//Fill table with players

			for (Player player : playerList) {
				String mySQLString = "insert into spelergoals(naam, goals)values(?,?)";
				ps = con.prepareStatement(mySQLString);

				ps.setString(1, player.getPlayerName());
				ps.setInt(2, player.getGoals());
				ps.executeUpdate();
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
