package game;

import java.util.List;

import attributes.PersonalGame;
import attributes.Player;

public class PlayerPointCalculator {

	public void pointCalculator(List<Player> playerList) {
		for (Player player : playerList) {
			String position = player.getPosition();		
			int currentGoals = player.getGoals();

			for (PersonalGame game : player.getPersonalGameList()) {
				int currentGroupPoints = player.getGroupPoints();
				int currentKOPoints = player.getKoPoints();
				int currentPoints = player.getTotalPoints();
				int matchPoints = 0;

				//Check for starting squad
				if(game.isStartingSquad()) {
					matchPoints += PointParameters.StartingSquad;
				}
				//Check for substitute in
				if(game.isSubIn()) {
					matchPoints += PointParameters.SubstituteIn;
				}
				//Check for substitute out
				if(game.isSubOut()) {
					matchPoints += PointParameters.SubstituteOut;
				}

				//Check for clean-sheet
				if(game.getKeepZero()) {
					switch (position) {
					case "Keeper":
						matchPoints += PointParameters.CleanSheetGoalKeeper;
						break;
					case "Verdediger":
						matchPoints += PointParameters.CleanSheetDefender;
						break;
					case "Middenvelder":
						matchPoints += PointParameters.CleanSheetMidfielder;
						break;
					case "Aanvaller":
						matchPoints += PointParameters.CleanSheetAttacker;
						break;
					default:
						System.out.println("ERROR");
						break;
					}
				}
				//Check for goals
				switch (position) {
				case "Keeper":
					matchPoints += PointParameters.GoalGoalKeeper * game.getGoals();
					break;
				case "Verdediger":
					matchPoints += PointParameters.GoalDefender * game.getGoals();
					break;
				case "Middenvelder":
					matchPoints += PointParameters.GoalMidfielder * game.getGoals();
					break;
				case "Aanvaller":
					matchPoints += PointParameters.GoalAttacker * game.getGoals();
					break;
				default:
					System.out.println("ERROR");
					break;
				}
				currentGoals += game.getGoals();

				//Check for assists
				switch (position) {
				case "Keeper":
					matchPoints += PointParameters.AssistGoalKeeper * game.getAssists();
					break;
				case "Verdediger":
					matchPoints += PointParameters.AssistDefender * game.getAssists();
					break;
				case "Middenvelder":
					matchPoints += PointParameters.AssistMidfielder * game.getAssists();
					break;
				case "Aanvaller":
					matchPoints += PointParameters.AssistAttacker * game.getAssists();
					break;
				default:
					System.out.println("ERROR");
					break;
				}

				//Check for own-goals
				switch (position) {
				case "Keeper":
					matchPoints += PointParameters.OwnGoalGoalKeeper * game.getOwnGoal();
					break;
				case "Verdediger":
					matchPoints += PointParameters.OwnGoalDefender * game.getOwnGoal();
					break;
				case "Middenvelder":
					matchPoints += PointParameters.OwnGoalMidfielder * game.getOwnGoal();
					break;
				case "Aanvaller":
					matchPoints += PointParameters.OwnGoalAttacker * game.getOwnGoal();
					break;
				default:
					System.out.println("ERROR");
					break;
				}

				//Check for yellow cards
				matchPoints += PointParameters.YellowCards * game.getYellowCard();

				//Check for double yellow cards
				matchPoints += PointParameters.DoubleYellow * game.getDoubleYellowCard();

				//Check for red card
				matchPoints += PointParameters.RedCard * game.getStraightRedCard();

				//Check for regular penalty hit
				matchPoints += PointParameters.RegularPenaltyHit * game.getHitPenalty();
				currentGoals += game.getHitPenalty();

				//Check for regular penalty missed
				matchPoints += PointParameters.RegularPenaltyMissed * game.getMissedPenalty();

				//Check for regular penalty saved
				matchPoints += PointParameters.RegularPenaltySaved * game.getSavedPenalty();

				//Check for shoot-out penalty hit
				matchPoints += PointParameters.ShootOutPenaltyHit * game.getHitPenaltyShootOut();

				//Check for shoot-out penalty missed
				matchPoints += PointParameters.ShootOutPenaltyMissed * game.getMissedPenaltyShootOut();

				//Check for shoot-out penalty saved
				matchPoints += PointParameters.ShootOutPenaltySaved * game.getSavedPenaltyShootOut();

				int matchPointsMultiplied = (int) (matchPoints*game.getRoundMultiplier());
				
				player.setTotalPoints(currentPoints+matchPointsMultiplied);
				
				if(game.isGroupFase()) {
					player.setGroupPoints(currentGroupPoints + matchPointsMultiplied);
				} else {
					player.setKoPoints(currentKOPoints + matchPointsMultiplied);
				}
				
				game.setPoints(matchPoints);
				
				player.setGoals(currentGoals);
			}

		}
	}
}
