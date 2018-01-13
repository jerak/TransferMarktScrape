package attributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Match {

	private Country HomeTeam;
	private Country AwayTeam;
	private boolean isGroupFase;
	private Map<Country, Integer> Score;
	private List<Player> AppearanceList;
	private Map<Player, Integer> GoalListHome;
	private Map<Player, Integer> GoalListAway;
	private Map<Player, Integer> AssistListHome;
	private Map<Player, Integer> AssistListAway;
	private Map<Player, Integer> YellowCardListHome;
	private Map<Player, Integer> YellowCardListAway;
	private Map<Player, Integer> YellowRedCardListHome;
	private Map<Player, Integer> YellowRedCardListAway;
	private Map<Player, Integer> RedCardListHome;
	private Map<Player, Integer> RedCardListAway;
	private Map<Player, Integer> PenaltyHitListHome;
	private Map<Player, Integer> PenaltyHitListAway;
	private Map<Player, Integer> PenaltyMissListHome;
	private Map<Player, Integer> PenaltyMissListAway;
	private Map<Player, Integer> PenaltyShootOutHitListHome;
	private Map<Player, Integer> PenaltyShootOutHitListAway;
	private Map<Player, Integer> PenaltyShootOutMissListHome;
	private Map<Player, Integer> PenaltyShootOutMissListAway;
	private Map<Player, Integer> PenaltyShootOutSavedHome;
	private Map<Player, Integer> PenaltyShootOutSavedAway;
	private Map<Player, Integer> OwnGoalListHome;
	private Map<Player, Integer> OwnGoalListAway;
	private Map<Player, Integer> PenaltySavedHome;
	private Map<Player, Integer> PenaltySavedAway;
	private Map<Player, Player> SubstitutePlayerHome;
	private Map<Player, Player> SubstitutePlayerAway;
	private List<Player> ZeroAgainstListHome;
	private List<Player> ZeroAgainstListAway;
	private double RoundMultiPlier;

	public Match(Country homeTeam, Country awayTeam) {
		this.HomeTeam = homeTeam;
		this.AwayTeam = awayTeam;
		AppearanceList = new ArrayList<>();
		ZeroAgainstListHome = new ArrayList<>();
		ZeroAgainstListAway = new ArrayList<>();
		SubstitutePlayerHome = new HashMap<Player, Player>();
		SubstitutePlayerAway = new HashMap<Player, Player>();
		Score = makeEmptyScoreMap(homeTeam, awayTeam);
		GoalListHome = makeEmptyMap(homeTeam);
		GoalListAway = makeEmptyMap(awayTeam);
		AssistListHome = makeEmptyMap(homeTeam);
		AssistListAway = makeEmptyMap(awayTeam);
		YellowCardListHome = makeEmptyMap(homeTeam);
		YellowCardListAway = makeEmptyMap(awayTeam);
		YellowRedCardListHome = makeEmptyMap(homeTeam);
		YellowRedCardListAway = makeEmptyMap(awayTeam);
		RedCardListHome = makeEmptyMap(homeTeam);
		RedCardListAway = makeEmptyMap(awayTeam);
		PenaltyHitListHome = makeEmptyMap(homeTeam);
		PenaltyHitListAway = makeEmptyMap(awayTeam);
		PenaltyMissListHome = makeEmptyMap(homeTeam);
		PenaltyMissListAway = makeEmptyMap(awayTeam);
		PenaltyShootOutHitListHome = makeEmptyMap(homeTeam);
		PenaltyShootOutHitListAway = makeEmptyMap(awayTeam);
		PenaltyShootOutMissListHome = makeEmptyMap(homeTeam);
		PenaltyShootOutMissListAway = makeEmptyMap(awayTeam);
		PenaltyShootOutSavedHome = makeEmptyMap(homeTeam);
		PenaltyShootOutSavedAway = makeEmptyMap(awayTeam);
		OwnGoalListHome = makeEmptyMap(homeTeam);
		OwnGoalListAway = makeEmptyMap(awayTeam);
		PenaltySavedHome = makeEmptyMap(homeTeam);
		PenaltySavedAway = makeEmptyMap(awayTeam);
	}

	public Country getHomeTeam() {
		return HomeTeam;
	}

	public void setHomeTeam(Country homeTeam) {
		HomeTeam = homeTeam;
	}

	public Country getAwayTeam() {
		return AwayTeam;
	}

	public void setAwayTeam(Country awayTeam) {
		AwayTeam = awayTeam;
	}

	public List<Player> getAppearanceList() {
		return AppearanceList;
	}

	public void setAppearanceList(List<Player> appearanceList) {
		AppearanceList = appearanceList;
	}

	public Map<Player, Integer> getGoalListHome() {
		return GoalListHome;
	}

	public void setGoalListHome(Map<Player, Integer> goalListHome) {
		GoalListHome = goalListHome;
	}

	public Map<Player, Integer> getGoalListAway() {
		return GoalListAway;
	}

	public void setGoalListAway(Map<Player, Integer> goalListAway) {
		GoalListAway = goalListAway;
	}

	public Map<Player, Integer> getAssistListHome() {
		return AssistListHome;
	}

	public void setAssistListHome(Map<Player, Integer> assistListHome) {
		AssistListHome = assistListHome;
	}

	public Map<Player, Integer> getAssistListAway() {
		return AssistListAway;
	}

	public void setAssistListAway(Map<Player, Integer> assistListAway) {
		AssistListAway = assistListAway;
	}

	public Map<Player, Integer> getYellowCardListHome() {
		return YellowCardListHome;
	}

	public void setYellowCardListHome(Map<Player, Integer> yellowCardListHome) {
		YellowCardListHome = yellowCardListHome;
	}

	public Map<Player, Integer> getYellowCardListAway() {
		return YellowCardListAway;
	}

	public void setYellowCardListAway(Map<Player, Integer> yellowCardListAway) {
		YellowCardListAway = yellowCardListAway;
	}

	public Map<Player, Integer> getRedCardListHome() {
		return RedCardListHome;
	}

	public void setRedCardListHome(Map<Player, Integer> redCardListHome) {
		RedCardListHome = redCardListHome;
	}

	public Map<Player, Integer> getRedCardListAway() {
		return RedCardListAway;
	}

	public void setRedCardListAway(Map<Player, Integer> redCardListAway) {
		RedCardListAway = redCardListAway;
	}

	public Map<Player, Integer> getPenaltyHitListHome() {
		return PenaltyHitListHome;
	}

	public void setPenaltyHitListHome(Map<Player, Integer> penaltyHitListHome) {
		PenaltyHitListHome = penaltyHitListHome;
	}

	public Map<Player, Integer> getPenaltyHitListAway() {
		return PenaltyHitListAway;
	}

	public void setPenaltyHitListAway(Map<Player, Integer> penaltyHitListAway) {
		PenaltyHitListAway = penaltyHitListAway;
	}

	public Map<Player, Integer> getPenaltyMissListHome() {
		return PenaltyMissListHome;
	}

	public void setPenaltyMissListHome(Map<Player, Integer> penaltyMissListHome) {
		PenaltyMissListHome = penaltyMissListHome;
	}

	public Map<Player, Integer> getPenaltyMissListAway() {
		return PenaltyMissListAway;
	}

	public void setPenaltyMissListAway(Map<Player, Integer> penaltyMissListAway) {
		PenaltyMissListAway = penaltyMissListAway;
	}

	public Map<Player, Integer> getOwnGoalListHome() {
		return OwnGoalListHome;
	}

	public void setOwnGoalListHome(Map<Player, Integer> ownGoalListHome) {
		OwnGoalListHome = ownGoalListHome;
	}

	public Map<Player, Integer> getOwnGoalListAway() {
		return OwnGoalListAway;
	}

	public void setOwnGoalListAway(Map<Player, Integer> ownGoalListAway) {
		OwnGoalListAway = ownGoalListAway;
	}

	public List<Player> getZeroAgainstListHome() {
		return ZeroAgainstListHome;
	}

	public void setZeroAgainstListHome(List<Player> zeroAgainstListHome) {
		ZeroAgainstListHome = zeroAgainstListHome;
	}

	public List<Player> getZeroAgainstListAway() {
		return ZeroAgainstListAway;
	}

	public void setZeroAgainstListAway(List<Player> zeroAgainstListAway) {
		ZeroAgainstListAway = zeroAgainstListAway;
	}

	public double getRoundMultiPlier() {
		return RoundMultiPlier;
	}

	public void setRoundMultiPlier(double roundmultiplier) {
		RoundMultiPlier = roundmultiplier;
	}

	public Map<Country, Integer> getScore() {
		return Score;
	}

	public void setScore(Map<Country, Integer> score) {
		this.Score = score;
	}

	public Map<Player, Integer> makeEmptyMap(Country country) {
		List<Player> playerList = country.getPlayerList();
		Map<Player, Integer> emptyMap = new HashMap<>();
		for (Player player : playerList) {
			emptyMap.put(player, 0);
		}
		return emptyMap;	
	}

	public Map<Country, Integer> makeEmptyScoreMap(Country country1, Country country2) {
		Map<Country, Integer> scoreMap = new HashMap<>();
		scoreMap.put(country1, 0);
		scoreMap.put(country2, 0);
		return scoreMap;
	}

	public Map<Player, Integer> getYellowRedCardListHome() {
		return YellowRedCardListHome;
	}

	public void setYellowRedCardListHome(Map<Player, Integer> yellowRedCardListHome) {
		YellowRedCardListHome = yellowRedCardListHome;
	}

	public Map<Player, Integer> getYellowRedCardListAway() {
		return YellowRedCardListAway;
	}

	public void setYellowRedCardListAway(Map<Player, Integer> yellowRedCardListAway) {
		YellowRedCardListAway = yellowRedCardListAway;
	}

	public Map<Player, Integer> getPenaltySavedHome() {
		return PenaltySavedHome;
	}

	public void setPenaltySavedHome(Map<Player, Integer> penaltySavedHome) {
		PenaltySavedHome = penaltySavedHome;
	}

	public Map<Player, Integer> getPenaltySavedAway() {
		return PenaltySavedAway;
	}

	public void setPenaltySavedAway(Map<Player, Integer> penaltySavedAway) {
		PenaltySavedAway = penaltySavedAway;
	}

	public Map<Player, Player> getSubstitutePlayerHome() {
		return SubstitutePlayerHome;
	}

	public void setSubstitutePlayerHome(Map<Player, Player> substitutePlayerHome) {
		this.SubstitutePlayerHome = substitutePlayerHome;
	}

	public Map<Player, Player> getSubstitutePlayerAway() {
		return SubstitutePlayerAway;
	}

	public void setSubstitutePlayerAway(Map<Player, Player> substitutePlayerAway) {
		this.SubstitutePlayerAway = substitutePlayerAway;
	}

	public Map<Player, Integer> getPenaltyShootOutHitListHome() {
		return PenaltyShootOutHitListHome;
	}

	public void setPenaltyShootOutHitListHome(Map<Player, Integer> penaltyShootOuHitListHome) {
		PenaltyShootOutHitListHome = penaltyShootOuHitListHome;
	}

	public Map<Player, Integer> getPenaltyShootOutHitListAway() {
		return PenaltyShootOutHitListAway;
	}

	public void setPenaltyShootOutHitListAway(Map<Player, Integer> penaltyShootOutHitListAway) {
		PenaltyShootOutHitListAway = penaltyShootOutHitListAway;
	}

	public Map<Player, Integer> getPenaltyShootOutMissListHome() {
		return PenaltyShootOutMissListHome;
	}

	public void setPenaltyShootOutMissListHome(Map<Player, Integer> penaltyShootOutMissListHome) {
		PenaltyShootOutMissListHome = penaltyShootOutMissListHome;
	}

	public Map<Player, Integer> getPenaltyShootOutMissListAway() {
		return PenaltyShootOutMissListAway;
	}

	public void setPenaltyShootOutMissListAway(Map<Player, Integer> penaltyShootOutMissListAway) {
		PenaltyShootOutMissListAway = penaltyShootOutMissListAway;
	}

	public Map<Player, Integer> getPenaltyShootOutSavedHome() {
		return PenaltyShootOutSavedHome;
	}

	public void setPenaltyShootOutSavedHome(Map<Player, Integer> penaltyShootOutSavedHome) {
		PenaltyShootOutSavedHome = penaltyShootOutSavedHome;
	}

	public Map<Player, Integer> getPenaltyShootOutSavedAway() {
		return PenaltyShootOutSavedAway;
	}

	public void setPenaltyShootOutSavedAway(Map<Player, Integer> penaltyShootOutSavedAway) {
		PenaltyShootOutSavedAway = penaltyShootOutSavedAway;
	}

	public boolean isGroupFase() {
		return isGroupFase;
	}

	public void setGroupFase(boolean isGroupFase) {
		this.isGroupFase = isGroupFase;
	}

}
