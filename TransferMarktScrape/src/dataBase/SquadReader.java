package dataBase;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.Connection;

import attributes.Participant;
import attributes.Player;
import attributes.Squad;

public class SquadReader {

	private Connection con;

	private void setConnection(Connection connection){
		con = connection;
	}

	public List<Participant> squadReadGroup(List<Player> playerList) {
		List<Participant> participantList = new ArrayList<>();

		try {
			setConnection(DataBaseConnection.getConnection());

			//Fill table with players
			Statement m_Statement = con.createStatement();
			String mySQLString = "select * from deelnemerteamsgf";
			ResultSet m_ResultSet = m_Statement.executeQuery(mySQLString);
			String club;
			Player player;

			while (m_ResultSet.next()) {
				Squad squad = new Squad();
				Participant participant = new Participant(m_ResultSet.getString("deelnemeremail"));
				participant.setSquadGroup(squad);
				System.out.println(participant.getParticipantName());

				//Get the goalkeeper
				String dmNaam = m_ResultSet.getString("dm");
				club = getParenthesesContent(dmNaam);
				System.out.println(club);
				dmNaam = dmNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, dmNaam, playerList);
				participant.getSquadGroup().setDm(player);

				//Get the rb
				String raNaam = m_ResultSet.getString("rv");
				club = getParenthesesContent(raNaam);
				raNaam = raNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, raNaam, playerList);
				participant.getSquadGroup().setRa(player);

				//Get the rcv
				String rcvNaam = m_ResultSet.getString("rcv");
				club = getParenthesesContent(rcvNaam);
				rcvNaam = rcvNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, rcvNaam, playerList);
				participant.getSquadGroup().setRcv(player);

				//Get the lcv
				String lcvNaam = m_ResultSet.getString("lcv");
				club = getParenthesesContent(lcvNaam);
				lcvNaam = lcvNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, lcvNaam, playerList);
				participant.getSquadGroup().setLcv(player);

				//Get the la
				String laNaam = m_ResultSet.getString("lv");
				club = getParenthesesContent(laNaam);
				laNaam = laNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, laNaam, playerList);
				participant.getSquadGroup().setLa(player);

				//Get the rm
				String rmNaam = m_ResultSet.getString("rm");
				club = getParenthesesContent(rmNaam);
				rmNaam = rmNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, rmNaam, playerList);
				participant.getSquadGroup().setRm(player);

				//Get the cm
				String cmNaam = m_ResultSet.getString("cm");
				club = getParenthesesContent(cmNaam);
				cmNaam = cmNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, cmNaam, playerList);
				participant.getSquadGroup().setCm(player);

				//Get the lm
				String lmNaam = m_ResultSet.getString("lm");
				club = getParenthesesContent(lmNaam);
				lmNaam = lmNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, lmNaam, playerList);
				participant.getSquadGroup().setLm(player);	

				//Get the rb
				String rbNaam = m_ResultSet.getString("ra");
				club = getParenthesesContent(rbNaam);
				rbNaam = rbNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, rbNaam, playerList);
				participant.getSquadGroup().setRb(player);

				//Get the sp
				String spNaam = m_ResultSet.getString("sp");
				club = getParenthesesContent(spNaam);
				spNaam = spNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, spNaam, playerList);
				participant.getSquadGroup().setSp(player);

				//Get the lb
				String lbNaam = m_ResultSet.getString("la");
				club = getParenthesesContent(lbNaam);
				lbNaam = lbNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, lbNaam, playerList);
				participant.getSquadGroup().setLb(player);

				participantList.add(participant);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return participantList;

	}

	public void squadReadKO(List<Player> playerList, List<Participant> participantList) {

		try {
			setConnection(DataBaseConnection.getConnection());

			//Fill table with players
			Statement m_Statement = con.createStatement();
			String mySQLString = "select * from deelnemerteamsko";
			ResultSet m_ResultSet = m_Statement.executeQuery(mySQLString);
			Player player;
			String club; 
			
			while (m_ResultSet.next()) {
				Squad squad = new Squad();
				Participant participant = findParticipantbyName(m_ResultSet.getString("deelnemeremail"), participantList);
				participant.setSquadKO(squad);

				//Get the goalkeeper
				String dmNaam = m_ResultSet.getString("dm");
				club = getParenthesesContent(dmNaam);
				System.out.println(club);
				dmNaam = dmNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, dmNaam, playerList);
				participant.getSquadKO().setDm(player);

				//Get the rb
				String raNaam = m_ResultSet.getString("rv");
				club = getParenthesesContent(raNaam);
				raNaam = raNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, raNaam, playerList);
				participant.getSquadKO().setRa(player);

				//Get the rcv
				String rcvNaam = m_ResultSet.getString("rcv");
				club = getParenthesesContent(rcvNaam); 
				rcvNaam = rcvNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, rcvNaam, playerList);
				participant.getSquadKO().setRcv(player);

				//Get the lcv
				String lcvNaam = m_ResultSet.getString("lcv");
				club = getParenthesesContent(lcvNaam);
				lcvNaam = lcvNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, lcvNaam, playerList);
				participant.getSquadKO().setLcv(player);

				//Get the la
				String laNaam = m_ResultSet.getString("lv");
				club = getParenthesesContent(laNaam); 
				laNaam = laNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, laNaam, playerList);
				participant.getSquadKO().setLa(player);

				//Get the rm
				String rmNaam = m_ResultSet.getString("rm");
				club = getParenthesesContent(rmNaam); 
				rmNaam = rmNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, rmNaam, playerList);
				participant.getSquadKO().setRm(player);

				//Get the cm
				String cmNaam = m_ResultSet.getString("cm");
				club = getParenthesesContent(cmNaam);
				cmNaam = cmNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, cmNaam, playerList);
				participant.getSquadKO().setCm(player);

				//Get the lm
				String lmNaam = m_ResultSet.getString("lm");
				club = getParenthesesContent(lmNaam); 
				lmNaam = lmNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, lmNaam, playerList);
				participant.getSquadKO().setLm(player);	

				//Get the rb
				String rbNaam = m_ResultSet.getString("ra");
				club = getParenthesesContent(rbNaam); 
				rbNaam = rbNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, rbNaam, playerList);
				participant.getSquadKO().setRb(player);

				//Get the sp
				String spNaam = m_ResultSet.getString("sp");
				club = getParenthesesContent(spNaam);
				spNaam = spNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, spNaam, playerList);
				participant.getSquadKO().setSp(player);

				//Get the lb
				String lbNaam = m_ResultSet.getString("la");
				club = getParenthesesContent(lbNaam); 
				lbNaam = lbNaam.replaceAll("\\(.*\\)", "").trim();
				player = retrievePlayerByNameAndClub(club, lbNaam, playerList);
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

	public Player retrievePlayerByNameAndClub(String club, String playerName, List<Player> playerList) {
		for(Player player : playerList) {
			if(player.getPlayerName().equals(playerName) && player.getClubName().equals(club)) {
				System.out.println(player.getPlayerName());
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
