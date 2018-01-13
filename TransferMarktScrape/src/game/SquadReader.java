package game;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.Connection;

import attributes.Country;
import attributes.Participant;
import attributes.Player;
import attributes.Squad;
import dataBase.DataBaseConnection;

public class SquadReader {

	private Connection con;

	private void setConnection(Connection connection){
		con = connection;
	}

	public List<Participant> squadReadGroup(List<Country> countryList) {
		List<Participant> participantList = new ArrayList<>();

		try {
			setConnection(DataBaseConnection.establishConnection("jdbc:mysql://localhost:3306/playerscores"));

			//Fill table with players
			Statement m_Statement = con.createStatement();
			String mySQLString = "select * from deelnemerteamsgf";
			ResultSet m_ResultSet = m_Statement.executeQuery(mySQLString);
			Country country;
			Player player;

			while (m_ResultSet.next()) {
				Squad squad = new Squad();
				Participant participant = new Participant(m_ResultSet.getString("deelnemernaam"));
				participant.setSquadGroup(squad);

				//Get the goalkeeper
				String dmNaam = m_ResultSet.getString("dm");
				country = retrieveCountryByName(getParenthesesContent(dmNaam), countryList);
				dmNaam = dmNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, dmNaam);
				participant.getSquadGroup().setDm(player);

				//Get the rb
				String raNaam = m_ResultSet.getString("ra");
				country = retrieveCountryByName(getParenthesesContent(raNaam), countryList);
				raNaam = raNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, raNaam);
				participant.getSquadGroup().setRa(player);

				//Get the rcv
				String rcvNaam = m_ResultSet.getString("rcv");
				country = retrieveCountryByName(getParenthesesContent(rcvNaam), countryList);
				rcvNaam = rcvNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, rcvNaam);
				participant.getSquadGroup().setRcv(player);

				//Get the lcv
				String lcvNaam = m_ResultSet.getString("lcv");
				country = retrieveCountryByName(getParenthesesContent(lcvNaam), countryList);
				lcvNaam = lcvNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, lcvNaam);
				participant.getSquadGroup().setLcv(player);

				//Get the la
				String laNaam = m_ResultSet.getString("la");
				country = retrieveCountryByName(getParenthesesContent(laNaam), countryList);
				laNaam = laNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, laNaam);
				participant.getSquadGroup().setLa(player);

				//Get the rm
				String rmNaam = m_ResultSet.getString("rm");
				country = retrieveCountryByName(getParenthesesContent(rmNaam), countryList);
				rmNaam = rmNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, rmNaam);
				participant.getSquadGroup().setRm(player);

				//Get the cm
				String cmNaam = m_ResultSet.getString("cm");
				country = retrieveCountryByName(getParenthesesContent(cmNaam), countryList);
				cmNaam = cmNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, cmNaam);
				participant.getSquadGroup().setCm(player);

				//Get the lm
				String lmNaam = m_ResultSet.getString("lm");
				country = retrieveCountryByName(getParenthesesContent(lmNaam), countryList);
				lmNaam = lmNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, lmNaam);
				participant.getSquadGroup().setLm(player);	

				//Get the rb
				String rbNaam = m_ResultSet.getString("rb");
				country = retrieveCountryByName(getParenthesesContent(rbNaam), countryList);
				rbNaam = rbNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, rbNaam);
				participant.getSquadGroup().setRb(player);

				//Get the sp
				String spNaam = m_ResultSet.getString("sp");
				country = retrieveCountryByName(getParenthesesContent(spNaam), countryList);
				spNaam = spNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, spNaam);
				participant.getSquadGroup().setSp(player);

				//Get the lb
				String lbNaam = m_ResultSet.getString("lb");
				country = retrieveCountryByName(getParenthesesContent(lbNaam), countryList);
				lbNaam = lbNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, lbNaam);
				participant.getSquadGroup().setLb(player);

				participantList.add(participant);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return participantList;

	}

	public void squadReadKO(List<Country> countryList, List<Participant> participantList) {

		try {
			setConnection(DataBaseConnection.establishConnection("jdbc:mysql://localhost:3306/playerscores"));

			//Fill table with players
			Statement m_Statement = con.createStatement();
			String mySQLString = "select * from deelnemerteamsko";
			ResultSet m_ResultSet = m_Statement.executeQuery(mySQLString);
			Country country;
			Player player;

			while (m_ResultSet.next()) {
				Squad squad = new Squad();
				Participant participant = findParticipantbyName(m_ResultSet.getString("deelnemernaam"), participantList);
				participant.setSquadKO(squad);

				//Get the goalkeeper
				String dmNaam = m_ResultSet.getString("dm");
				country = retrieveCountryByName(getParenthesesContent(dmNaam), countryList);
				dmNaam = dmNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, dmNaam);
				participant.getSquadKO().setDm(player);

				//Get the rb
				String raNaam = m_ResultSet.getString("ra");
				country = retrieveCountryByName(getParenthesesContent(raNaam), countryList);
				raNaam = raNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, raNaam);
				participant.getSquadKO().setRa(player);

				//Get the rcv
				String rcvNaam = m_ResultSet.getString("rcv");
				country = retrieveCountryByName(getParenthesesContent(rcvNaam), countryList);
				rcvNaam = rcvNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, rcvNaam);
				participant.getSquadKO().setRcv(player);

				//Get the lcv
				String lcvNaam = m_ResultSet.getString("lcv");
				country = retrieveCountryByName(getParenthesesContent(lcvNaam), countryList);
				lcvNaam = lcvNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, lcvNaam);
				participant.getSquadKO().setLcv(player);

				//Get the la
				String laNaam = m_ResultSet.getString("la");
				country = retrieveCountryByName(getParenthesesContent(laNaam), countryList);
				laNaam = laNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, laNaam);
				participant.getSquadKO().setLa(player);

				//Get the rm
				String rmNaam = m_ResultSet.getString("rm");
				country = retrieveCountryByName(getParenthesesContent(rmNaam), countryList);
				rmNaam = rmNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, rmNaam);
				participant.getSquadKO().setRm(player);

				//Get the cm
				String cmNaam = m_ResultSet.getString("cm");
				country = retrieveCountryByName(getParenthesesContent(cmNaam), countryList);
				cmNaam = cmNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, cmNaam);
				participant.getSquadKO().setCm(player);

				//Get the lm
				String lmNaam = m_ResultSet.getString("lm");
				country = retrieveCountryByName(getParenthesesContent(lmNaam), countryList);
				lmNaam = lmNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, lmNaam);
				participant.getSquadKO().setLm(player);	

				//Get the rb
				String rbNaam = m_ResultSet.getString("rb");
				country = retrieveCountryByName(getParenthesesContent(rbNaam), countryList);
				rbNaam = rbNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, rbNaam);
				participant.getSquadKO().setRb(player);

				//Get the sp
				String spNaam = m_ResultSet.getString("sp");
				country = retrieveCountryByName(getParenthesesContent(spNaam), countryList);
				spNaam = spNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, spNaam);
				participant.getSquadKO().setSp(player);

				//Get the lb
				String lbNaam = m_ResultSet.getString("lb");
				country = retrieveCountryByName(getParenthesesContent(lbNaam), countryList);
				lbNaam = lbNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndCountry(country, lbNaam);
				participant.getSquadKO().setLb(player);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private Participant findParticipantbyName(String participantName, List<Participant> particpantList) {
		for(Participant participant : particpantList) {
			if (participant.getParticipantName().equals(participantName)) {
				return participant;
			}
		}
		return null;
	}

	public Country retrieveCountryByName(String countryString, List<Country> countryList) {
		Country country = null;
		for (Country countryInList : countryList) {
			if(countryInList.getCountryName().equals(countryString)) {
				country = countryInList;
				return country;
			}
		}
		System.out.println("Country not found");
		return null;
	}

	public Player retrievePlayerByNameAndCountry(Country country, String playerName) {
		Player player = null;

		for(Player playerInList : country.getPlayerList()) {
			if(playerInList.getPlayerName().equals(playerName)) {
				player = playerInList;
				return player;
			}
		}
		System.out.println("Player not found");
		return null;
	}

	public static String getParenthesesContent(String str){
		final Pattern pattern = Pattern.compile("^.*\\((.*)\\).*$");
		final Matcher matcher = pattern.matcher(str);
		if (matcher.matches()){
			return matcher.group(1);
		} else {
			return null;
		}
	}
}
