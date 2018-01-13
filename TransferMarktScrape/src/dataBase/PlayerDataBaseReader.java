package dataBase;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Connection;

import attributes.Country;
import attributes.Player;

public class PlayerDataBaseReader {

	private Connection con;

	private void setConnection(Connection connection){
		con = connection;
	}

	public List<Player> dataBaseReader(List<Country> countryList) {

		List<Player> playerList = new ArrayList<>();

		try {
			setConnection(DataBaseConnection.establishConnection("jdbc:mysql://localhost:3306/playerdatabase"));

			Statement m_Statement = con.createStatement();
			String mySQLString = "select * from spelers";
			ResultSet m_ResultSet = m_Statement.executeQuery(mySQLString);
			Player player;
			Country country;

			while (m_ResultSet.next()) {
				String playerName = m_ResultSet.getString("naam");
				player = new Player(playerName);
				country = findCountryByName(m_ResultSet.getString("land"), countryList);
				player.setCountry(country);
				country.getPlayerList().add(player);
				String positie = m_ResultSet.getString("positie");
				player.setPosition(positie);
				String playerURL = m_ResultSet.getString("spelerURL");
				player.setPlayerURL(playerURL);
				playerList.add(player);
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return playerList;
	}

	private Country findCountryByName(String countryName, List<Country> countryList) {
		for(Country country : countryList) {
			if (country.getCountryName().equals(countryName)) {
				return country;
			}
		}
		System.out.println("Country not found");
		return null;
	}

}