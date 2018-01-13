package dataBase;

import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.util.List;

import com.mysql.jdbc.Connection;

import attributes.Player;


public class PlayerDataBaseWriter {

	private Connection con;


	private void setConnection(Connection connection){
		con = connection;
	}

	public void dataBaseWriter(List<Player> playerList) {

		try {
			setConnection(DataBaseConnection.getConnection());

			//Clear current Table
			String sqlClearTable = "DELETE FROM spelers";
			PreparedStatement ps = con.prepareStatement(sqlClearTable);
			ps.executeUpdate();

			//Fill table with players

			for (Player player : playerList) {
				String mySQLString = "insert into spelers(naam,land,club,positie,waarde,spelerfoto,clublogo, spelerURL)values(?,?,?,?,?,?,?,?)";
				ps = con.prepareStatement(mySQLString);
				File faceFile = new File(player.getFaceImagePath());
				FileInputStream fis = new FileInputStream(faceFile);
				File logoFile = new File(player.getLogoImagePath());
				FileInputStream fisLogo = new FileInputStream(logoFile);
				
				ps.setString(1, player.getPlayerName());
				ps.setString(2, player.getCountry().getCountryName());
				ps.setString(3, player.getClubName());
				ps.setString(4, player.getPosition());
				ps.setInt(5, player.getPlayerValue());
				ps.setBinaryStream(6, fis);
				ps.setBinaryStream(7, fisLogo);
				ps.setString(8, player.getPlayerURL());
				ps.executeUpdate();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
