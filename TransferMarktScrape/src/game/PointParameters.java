package game;

public class PointParameters {

	//RoundMultipliers
	public static final double[] RoundMultiplier = {1,1,1,1.125,1.25,1.375,1.5,2};
	
	//Starting Squad & Substitutes	
	public static final int StartingSquad = 10;
	public static final int SubstituteIn = 5;
	public static final int SubstituteOut = -3;
	
	//Clean Sheet
	public static final int CleanSheetGoalKeeper = 64;
	public static final int CleanSheetDefender = 32;
	public static final int CleanSheetMidfielder = 16;
	public static final int CleanSheetAttacker = 8;
	
	//Goals
	public static final int GoalGoalKeeper = 128;
	public static final int GoalDefender = 96;
	public static final int GoalMidfielder = 80;
	public static final int GoalAttacker = 64;
	
	//Assists
	public static final int AssistGoalKeeper = 64;
	public static final int AssistDefender = 48;
	public static final int AssistMidfielder = 40;
	public static final int AssistAttacker = 32;

	//Own-Goals
	public static final int OwnGoalGoalKeeper = -64;
	public static final int OwnGoalDefender = -80;
	public static final int OwnGoalMidfielder =  -96;
	public static final int OwnGoalAttacker = -128;
	
	//Cards
	public static final int YellowCards = -24;
	public static final int DoubleYellow = -24;
	public static final int RedCard = -64;
	
	//Regular Penalties
	public static final int RegularPenaltyHit = 64;
	public static final int RegularPenaltyMissed = -64;
	public static final int RegularPenaltySaved = 64;
	
	//Shoot-Out Penalties
	public static final int ShootOutPenaltyHit = 32;	
	public static final int ShootOutPenaltyMissed = -32;	
	public static final int ShootOutPenaltySaved = 32;
	
}
