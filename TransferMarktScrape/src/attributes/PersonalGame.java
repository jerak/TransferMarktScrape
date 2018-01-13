package attributes;

public class PersonalGame {

	private Country HomeTeam;
	private Country AwayTeam;
	private boolean isGroupFase;
	private boolean StartingSquad;
	private boolean SubIn;
	private boolean SubOut;
	private boolean KeepZero;
	private double RoundMultiplier;
	private Integer Goals;
	private Integer Assists;
	private Integer OwnGoal;
	private Integer YellowCard;
	private Integer DoubleYellowCard;
	private Integer StraightRedCard;
	private Integer MissedPenalty;
	private Integer HitPenalty;
	private Integer SavedPenalty;
	private Integer MissedPenaltyShootOut;
	private Integer SavedPenaltyShootOut;
	private Integer HitPenaltyShootOut;
	private Integer Points;
	private Integer totalGoals;

	public PersonalGame() {	
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

	public double getRoundMultiplier() {
		return RoundMultiplier;
	}

	public void setRoundMultiplier(double roundMultiplier) {
		RoundMultiplier = roundMultiplier;
	}

	public Integer getGoals() {
		return Goals;
	}

	public void setGoals(Integer goals) {
		Goals = goals;
	}

	public Integer getAssists() {
		return Assists;
	}

	public void setAssists(Integer assists) {
		Assists = assists;
	}

	public boolean getKeepZero() {
		return KeepZero;
	}

	public void setKeepZero(boolean keepZero) {
		KeepZero = keepZero;
	}

	public Integer getOwnGoal() {
		return OwnGoal;
	}

	public void setOwnGoal(Integer ownGoal) {
		OwnGoal = ownGoal;
	}

	public Integer getYellowCard() {
		return YellowCard;
	}

	public void setYellowCard(Integer yellowCard) {
		YellowCard = yellowCard;
	}

	public Integer getDoubleYellowCard() {
		return DoubleYellowCard;
	}

	public void setDoubleYellowCard(Integer doubleYellowCard) {
		DoubleYellowCard = doubleYellowCard;
	}

	public Integer getStraightRedCard() {
		return StraightRedCard;
	}

	public void setStraightRedCard(Integer straightRedCard) {
		StraightRedCard = straightRedCard;
	}

	public Integer getMissedPenalty() {
		return MissedPenalty;
	}

	public void setMissedPenalty(Integer missedPenalty) {
		MissedPenalty = missedPenalty;
	}

	public Integer getHitPenalty() {
		return HitPenalty;
	}

	public void setHitPenalty(Integer hitPenalty) {
		HitPenalty = hitPenalty;
	}

	public Integer getSavedPenalty() {
		return SavedPenalty;
	}

	public void setSavedPenalty(Integer savedPenalty) {
		SavedPenalty = savedPenalty;
	}

	public Integer getMissedPenaltyShootOut() {
		return MissedPenaltyShootOut;
	}

	public void setMissedPenaltyShootOut(Integer missedPenaltyShootOut) {
		MissedPenaltyShootOut = missedPenaltyShootOut;
	}

	public Integer getSavedPenaltyShootOut() {
		return SavedPenaltyShootOut;
	}

	public void setSavedPenaltyShootOut(Integer savedPenaltyShootOut) {
		SavedPenaltyShootOut = savedPenaltyShootOut;
	}

	public Integer getHitPenaltyShootOut() {
		return HitPenaltyShootOut;
	}

	public void setHitPenaltyShootOut(Integer hitPenaltyShootOut) {
		HitPenaltyShootOut = hitPenaltyShootOut;
	}

	public boolean isStartingSquad() {
		return StartingSquad;
	}

	public void setStartingSquad(boolean startingSquad) {
		StartingSquad = startingSquad;
	}

	public boolean isSubIn() {
		return SubIn;
	}

	public void setSubIn(boolean subIn) {
		SubIn = subIn;
	}

	public boolean isSubOut() {
		return SubOut;
	}

	public void setSubOut(boolean subOut) {
		SubOut = subOut;
	}
	
	public String getMatchName() {
		String matchName = HomeTeam.getCountryName() + " - " + AwayTeam.getCountryName();
		return matchName;
	}

	public Integer getPoints() {
		return Points;
	}

	public void setPoints(Integer points) {
		Points = points;
	}

	public Integer getTotalGoals() {
		totalGoals = Goals + HitPenalty;
		return totalGoals;
	}

	public boolean isGroupFase() {
		return isGroupFase;
	}

	public void setGroupFase(boolean isGroupFase) {
		this.isGroupFase = isGroupFase;
	}



}
