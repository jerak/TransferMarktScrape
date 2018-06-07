package attributes;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private String playerName;
	private int squadNumber;
	private int groupPoints;
	private int koPoints;
	private int totalPoints;
	private int goals;
	private String playerURL;
	private List<Match> matchList;
	private String clubName;
	private Country country;
	private int playerValueWebsite;
	private int playerValue;
	private int age;
	private String position;
	private List<PersonalGame> personalGameList;
	private String faceImagePath;
	private String logoImagePath;

	public Player(String playerName) {
		this.playerName = playerName;
		matchList = new ArrayList<>();
		personalGameList = new ArrayList<>();
		totalPoints = 0;
		groupPoints = 0;
		koPoints = 0;
	}
	
	public int getPlayerValueWebsite() {
		return playerValueWebsite;
	}

	public void setPlayerValueWebsite(int playerValueWebsite) {
		this.playerValueWebsite = playerValueWebsite;
	}

	public void setSquadNumber(int squadNumber) {
		this.squadNumber = squadNumber;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getClubName() {
		return clubName;
	}

	public String getPlayerURL() {
		return playerURL;
	}

	public void setPlayerURL(String playerURL) {
		this.playerURL = playerURL;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPlayerValue() {
		return playerValue;
	}

	public void setPlayerValue(int playerValue) {
		this.playerValue = playerValue;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country countryName) {
		this.country = countryName;
	}

	public List<Match> getMatchList() {
		return matchList;
	}

	public void setMatchList(List<Match> matchList) {
		this.matchList = matchList;
	}

	public List<PersonalGame> getPersonalGameList() {
		return personalGameList;
	}

	public void setPersonalGameList(List<PersonalGame> personalGameList) {
		this.personalGameList = personalGameList;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int points) {
		this.totalPoints = points;
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}


	public int getSquadNumber() {
		return squadNumber;
	}

	public void setSquadNumber(String squadNumber) {
		if(squadNumber.equals("-")) {
			this.squadNumber = 0;
		} else {
			this.squadNumber = Integer.parseInt(squadNumber);
		}

	}

	public int getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = Integer.parseInt(getParenthesesContent(age));
	}

	public static String getParenthesesContent(String str){
		return str.substring(str.indexOf('(')+1,str.indexOf(')'));
	}

	public int getGroupPoints() {
		return groupPoints;
	}

	public void setGroupPoints(int groupPoints) {
		this.groupPoints = groupPoints;
	}

	public int getKoPoints() {
		return koPoints;
	}

	public void setKoPoints(int koPoints) {
		this.koPoints = koPoints;
	}

	public String getFaceImagePath() {
		return faceImagePath;
	}

	public void setFaceImagePath(String faceImagePath) {
		this.faceImagePath = faceImagePath;
	}

	public String getLogoImagePath() {
		return logoImagePath;
	}

	public void setLogoImagePath(String logoImagePath) {
		this.logoImagePath = logoImagePath;
	}
}
