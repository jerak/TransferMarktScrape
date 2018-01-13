package scrape;

import java.util.List;

import attributes.Country;
import attributes.Match;
import attributes.PersonalGame;
import attributes.Player;

public class CreatePersonalMatch {


	/*
	 * Regels opgenomen:
	 * Speler waarop overtreding is gemaakt bij penalty, krijgt assist
	 */

	public void createPersonalMatch (List<Match> matchList, List<Player> playerList) {

		//Calculate points for every match and add them to the corresponding players
		for (Player player : playerList) {
			for (Match match : player.getMatchList()) {
				PersonalGame game = new PersonalGame();
				if (match.isGroupFase()) {
					game.setGroupFase(true);
				} else {
					game.setGroupFase(false);
				}
				game.setHomeTeam(match.getHomeTeam());
				game.setAwayTeam(match.getAwayTeam());
				game.setRoundMultiplier(match.getRoundMultiPlier());

				//Check if player is home or away
				Country homeTeam = match.getHomeTeam();
				Country awayTeam = match.getAwayTeam();
				boolean home = false;
				if (homeTeam.getPlayerList().contains(player)) {
					home = true;
				} else if (awayTeam.getPlayerList().contains(player)) {
					home = false;
				}

				//Check for starting squad
				if(match.getAppearanceList().contains(player)) {
					game.setStartingSquad(true);		
				} else {
					game.setStartingSquad(false);		
				}

				//Check for substitute out
				if (home) {
					for (Player playerSub : match.getSubstitutePlayerHome().keySet()) {
						if(playerSub.equals(player)) {
							game.setSubOut(true);
							break;
						} else {
							game.setSubOut(false);
						}
					}
				}
				else {
					for (Player playerSub : match.getSubstitutePlayerAway().keySet()) {
						if(playerSub.equals(player)) {
							game.setSubOut(true);
							break;
						} else {
							game.setSubOut(false);
						}
					}

				}

				//Check for substitute in
				if (home) {
					for (Player playerSub : match.getSubstitutePlayerHome().keySet()) {
						if(match.getSubstitutePlayerHome().get(playerSub).equals(player)) {
							game.setSubIn(true);
							break;
						}
						else {
							game.setSubIn(false);
						}
					}
				} else {
					for (Player playerSub : match.getSubstitutePlayerAway().keySet()) {
						if(match.getSubstitutePlayerAway().get(playerSub).equals(player)) {
							game.setSubIn(true);
							break;
						}
						else {
							game.setSubIn(false);
						}
					}
				}

				//Check for goals
				if(home) {
					game.setGoals(match.getGoalListHome().get(player));
				}
				else  {
					game.setGoals(match.getGoalListAway().get(player));
				}

				//Check for zero against
				if(home) {
					if(match.getZeroAgainstListHome().contains(player)) {
						game.setKeepZero(true);
					}
					else {
						game.setKeepZero(false);
					}
				} 
				else {
					if(match.getZeroAgainstListAway().contains(player)) {
						game.setKeepZero(true);
					}
					else {
						game.setKeepZero(false);
					}
				}

				//Check for own-goals
				if(home) {
					game.setOwnGoal(match.getOwnGoalListHome().get(player));
				}
				else {
					game.setOwnGoal(match.getOwnGoalListAway().get(player));
				}

				//Check for assists
				if(home) {
					game.setAssists(match.getAssistListHome().get(player));
				}
				else {
					game.setAssists(match.getAssistListAway().get(player));
				}

				//Check for yellow cards
				if(home) {
					game.setYellowCard(match.getYellowCardListHome().get(player));	
				} else {
					game.setYellowCard(match.getYellowCardListAway().get(player));
				}

				//Check for double yellow/red
				if(home) {
					game.setDoubleYellowCard(match.getYellowRedCardListHome().get(player));
				} else {
					game.setDoubleYellowCard(match.getYellowRedCardListAway().get(player));
				}

				//Check for straight red
				if(home) {
					game.setStraightRedCard(match.getRedCardListHome().get(player));
				} else {
					game.setStraightRedCard(match.getRedCardListAway().get(player));
				}

				//Check for scoring penalty
				if(home) {
					game.setHitPenalty(match.getPenaltyHitListHome().get(player));
				} else {
					game.setHitPenalty(match.getPenaltyHitListAway().get(player));
				}

				//Check for saving penalty
				if(home) {
					game.setSavedPenalty(match.getPenaltySavedHome().get(player));
				} else {
					game.setSavedPenalty(match.getPenaltySavedAway().get(player));
				}

				//Check for missing penalty
				if(home) {
					game.setMissedPenalty(match.getPenaltyMissListHome().get(player));
				} else {
					game.setMissedPenalty(match.getPenaltyMissListAway().get(player));
				}

				//Check for scoring penalty shootout
				if(home) {
					game.setHitPenaltyShootOut(match.getPenaltyShootOutHitListHome().get(player));
				} else {
					game.setHitPenaltyShootOut(match.getPenaltyShootOutHitListAway().get(player));
				}

				//Check for missing penalty shootout
				if(home) {
					game.setMissedPenaltyShootOut(match.getPenaltyShootOutMissListHome().get(player));
				} else {
					game.setMissedPenaltyShootOut(match.getPenaltyShootOutMissListAway().get(player));
				}

				//Check for saving penalty shootout
				if(home) {
					game.setSavedPenaltyShootOut(match.getPenaltyShootOutSavedHome().get(player));
				} else {
					game.setSavedPenaltyShootOut(match.getPenaltyShootOutSavedAway().get(player));
				}

				player.getPersonalGameList().add(game);

			}
		}
		//		printPlayerStats(playerList);
	}

	public void printPlayerStats(List<Player> playerList) {
		for (Player player : playerList) {	
			for (PersonalGame game : player.getPersonalGameList()) {
				System.out.println(player.getPlayerName()+ " in " + 
						game.getHomeTeam().getCountryName() + " - " + game.getAwayTeam().getCountryName() + ":");
				printOnlyIfTrue(game.isStartingSquad(), " starting squad");
				printOnlyIfTrue(game.isSubIn(), "Gets substitution in");
				printOnlyIfTrue(game.isSubOut(), "Gets substitution out");
				printOnlyIfTrue(game.getKeepZero(), "Zero goals against");
				printOnlyIfLargerThanZero(game.getGoals(), "goals");
				printOnlyIfLargerThanZero(game.getAssists(), "assists");
				printOnlyIfLargerThanZero(game.getOwnGoal(), "own-goals");
				printOnlyIfLargerThanZero(game.getYellowCard(), "yellow cards");
				printOnlyIfLargerThanZero(game.getDoubleYellowCard(), "double yellow cards");
				printOnlyIfLargerThanZero(game.getStraightRedCard(), "straight red cards");
				printOnlyIfLargerThanZero(game.getHitPenalty(), "regular penalties hit");
				printOnlyIfLargerThanZero(game.getMissedPenalty(), "regular penalties missed");
				printOnlyIfLargerThanZero(game.getSavedPenalty(), "regular penalties saved");
				printOnlyIfLargerThanZero(game.getHitPenaltyShootOut(), "shoot-out penalties hit");
				printOnlyIfLargerThanZero(game.getMissedPenaltyShootOut(), "shoot-out penalties missed");
				printOnlyIfLargerThanZero(game.getSavedPenaltyShootOut(), "shoot-out penalties saved");
			}
		}
	}

	public void printOnlyIfLargerThanZero(Integer statistic, String text) {
		if (statistic > 0) {
			System.out.println(statistic + " " + text);
		}
	}

	private void printOnlyIfTrue(Boolean substitutBoolean, String text) {
		if (substitutBoolean) {
			System.out.println(text);
		}
	}
}