package scrape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import attributes.Country;
import attributes.Match;
import attributes.Player;
import game.PointParameters;
import run.WebsiteDetails;

public class MatchScraper {

	List<Match> matchList = new ArrayList<>();

	public List<Match> scraperMatches(List<Country> countryList) throws IOException {

		for (int speelDag = 1; speelDag <= WebsiteDetails.speelDagen; speelDag++) {
			Document doc = Jsoup.connect(WebsiteDetails.matchHtml+speelDag).get();

			for(Element matchSheet : doc.select("a[class=ergebnis-link]")) {
				String matchURL = matchSheet.attr("abs:href");

				//Check if matchURL is correct, otherwise set it to correct one
				//				if (matchURL.contains("ticker")) {
				//					matchURL = WebsiteDetails.errorURLmatch;
				//				}

				Document matchDoc = Jsoup.connect(matchURL).get();
				String homeTeamName = matchDoc.select("div[class=sb-team sb-heim hide-for-small]").text().trim();
				String awayTeamName = matchDoc.select("div[class=sb-team sb-gast hide-for-small]").text().trim();

				//Get the countries corresponding to the countries in the match
				Country homeTeam = null;
				Country awayTeam = null;
				for (Country country : countryList) {
					if (country.getCountryName().equals(homeTeamName)) {
						homeTeam = country;
					}
					if (country.getCountryName().equals(awayTeamName)) {
						awayTeam = country;
					}
				}
				Match match = new Match(homeTeam, awayTeam);

				match.setGroupFase(isGroupFase(speelDag));
				match.setRoundMultiPlier(PointParameters.RoundMultiplier[speelDag-1]);
				matchList.add(match);

				//Get the line-ups for both teams and save them in the MatchList
				//Merge playerList of both countries in Match

				List<Player> playerListCountriesMatch = new ArrayList<>();
				playerListCountriesMatch.addAll(homeTeam.getPlayerList());
				playerListCountriesMatch.addAll(awayTeam.getPlayerList());

				Elements lineUpHome = matchDoc.select("div[class=aufstellung-spieler-container]");
				List<String> playerURLS = new ArrayList<>();
				for (Element playerBox : lineUpHome.select("a")) {
					//			Elements playerBox = lineUp.select("a");
					String playerURL = playerBox.attr("abs:href").trim();
					playerURLS.add(playerURL);
				}
				//Match Players in Match to Players in PlayerList
				for (String playerString : playerURLS) {
					for (Player player : playerListCountriesMatch) {
						if (playerString.equals(player.getPlayerURL())) {
							match.getAppearanceList().add(player);
							player.getMatchList().add(match);
							break;
						}
					}
				}

				//Scrape the goals of each match and store them in the corresponding matchLists
				//Home goals
				Elements goalAssistBox = matchDoc.select("div[id=sb-tore]");
				for (Element homeEvent : goalAssistBox.select("li[class=sb-aktion-heim]")) {
					Elements goalBox = homeEvent.select("div[class=sb-aktion-aktion]");
					Element goalScorerElement = goalBox.select("a").first();
					String goalScorerName = goalScorerElement.attr("title");

					//Check for own-goal
					String goalInformation = goalBox.text().toLowerCase();
					if (goalInformation.contains("own-goal") || goalInformation.contains("owngoal") ||
							goalInformation.contains("own goal")) {
						//Add corresponding player to own-goalscorerslist of match
						Player ownGoalScorer = retrievePlayerByName(goalScorerName, match.getAwayTeam());
						match.getOwnGoalListAway().put(ownGoalScorer, match.getOwnGoalListAway().get(ownGoalScorer)+1);
						match.getScore().put(match.getHomeTeam(), match.getScore().get(homeTeam)+1);

						//Check for penalty goal:
					} else if(goalInformation.contains("penalty")) {
						Player penaltyMaker = retrievePlayerByName(goalScorerName, match.getHomeTeam());
						match.getPenaltyHitListHome().put(penaltyMaker, match.getPenaltyHitListHome().get(penaltyMaker)+1);
						match.getScore().put(match.getHomeTeam(), match.getScore().get(homeTeam)+1);

					}

					//Goal is normal goal:
					else {
						//Add corresponding player to goalscorerslist of match
						Player goalScorer = retrievePlayerByName(goalScorerName, match.getHomeTeam());
						match.getGoalListHome().put(goalScorer, match.getGoalListHome().get(goalScorer)+1);
						match.getScore().put(homeTeam, match.getScore().get(homeTeam)+1);
					}

					//Check if assist was made:
					if (goalBox.select("a").size() == 2) {
						String assistMakerName = goalBox.select("a").get(1).attr("title");
						Player assistMaker = retrievePlayerByName(assistMakerName, match.getHomeTeam());
						//Increase the number of goals of goalscorer
						if (assistMaker != null) {
							match.getAssistListHome().put(assistMaker, match.getAssistListHome().get(assistMaker)+1);
						} 
					}
				}

				//Away goals
				for (Element awayEvent : goalAssistBox.select("li[class=sb-aktion-gast]")) {
					Elements goalBox = awayEvent.select("div[class=sb-aktion-aktion]");
					Element goalScorerElement = goalBox.select("a").first();
					String goalScorerName = goalScorerElement.attr("title");

					//Check for own-goal
					String goalInformation = goalBox.text().toLowerCase();
					if (goalInformation.contains("own-goal") || goalInformation.contains("owngoal") ||
							goalInformation.contains("own goal")) {
						//Add corresponding player to own-goalscorerslist of match
						Player ownGoalScorer = retrievePlayerByName(goalScorerName, match.getHomeTeam());
						match.getOwnGoalListHome().put(ownGoalScorer, match.getOwnGoalListHome().get(ownGoalScorer)+1);
						match.getScore().put(awayTeam, match.getScore().get(awayTeam)+1);

						//Check for penalty goal:
					} else if(goalInformation.contains("penalty")) {
						Player penaltyMaker = retrievePlayerByName(goalScorerName, match.getAwayTeam());
						match.getPenaltyHitListAway().put(penaltyMaker, match.getPenaltyHitListAway().get(penaltyMaker)+1);
						match.getScore().put(match.getAwayTeam(), match.getScore().get(awayTeam)+1);
					}

					//Goal is normal goal:
					else {
						//Add corresponding player to goalscorerslist of match
						Player goalScorer = retrievePlayerByName(goalScorerName, match.getAwayTeam());
						match.getGoalListAway().put(goalScorer, match.getGoalListAway().get(goalScorer)+1);
						match.getScore().put(match.getAwayTeam(), match.getScore().get(awayTeam)+1);
					}

					//Check if assist was made:
					if (goalBox.select("a").size() == 2) {
						String assistMakerName = goalBox.select("a").get(1).attr("title");
						Player assistMaker = retrievePlayerByName(assistMakerName, match.getAwayTeam());
						//Increase the number of goals of goalscorer
						if (assistMaker != null) {
							match.getAssistListAway().put(assistMaker, match.getAssistListAway().get(assistMaker)+1);
						} 
					}
				}

				scrapeCard(matchDoc, match);
				scrapeMissedPenaltyies(matchDoc, match);
				scrapeSubstitutions(matchDoc, match);
				scrapePenaltyShootOut(matchDoc, match); 
				calculateCleanSheet(match);
				printMatchScore(match);
				//						printSubs(match.getSubstitutePlayerHome());
				//						printSubs(match.getSubstitutePlayerAway());

				//			printPlayerStats(match.getYellowCardListHome(), "Yellow card(s) by "+ homeTeam.getCountryName());
				//			printPlayerStats(match.getYellowCardListAway(), "Yellow card(s) by " + awayTeam.getCountryName());
				//			printPlayerStats(match.getYellowRedCardListHome(), "Yellow/Red card(s) by "+ homeTeam.getCountryName());
				//			printPlayerStats(match.getYellowRedCardListAway(), "Yellow/Red card(s) by " + awayTeam.getCountryName());
				//			printPlayerStats(match.getRedCardListHome(), "Red card(s) by " + homeTeam.getCountryName());
				//			printPlayerStats(match.getRedCardListAway(), "Red card(s) by " + awayTeam.getCountryName());
				//			printPlayerStats(match.getPenaltyHitListHome(), "Penalties Hit by "+ homeTeam.getCountryName());
				//			printPlayerStats(match.getPenaltyHitListAway(), "Penalties Hit by " + awayTeam.getCountryName());
				//			printPlayerStats(match.getAssistListHome(), "Assists by " + homeTeam.getCountryName());
				//			printPlayerStats(match.getAssistListAway(), "Assists by "+ awayTeam.getCountryName());
				//			printPlayerStats(match.getGoalListHome(), "Goals by "+ homeTeam.getCountryName());
				//			printPlayerStats(match.getGoalListAway(), "Goals by "+ awayTeam.getCountryName());
				//			printPlayerStats(match.getOwnGoalListHome(), "Own-Goals by "+ homeTeam.getCountryName());
				//			printPlayerStats(match.getOwnGoalListAway(), "Own-Goals by " + awayTeam.getCountryName());
				//			printPlayerStats(match.getPenaltyMissListHome(), "Penalty Missed by "+ homeTeam.getCountryName());
				//			printPlayerStats(match.getPenaltyMissListAway(), "Penalty Missed by "+ awayTeam.getCountryName());

			}
		}
		return matchList;
	}

	public void scrapeSubstitutions(Document matchDocument, Match match) {
		Elements subsBox = matchDocument.select("div[id=sb-wechsel]");
		for (Element subLine :subsBox.select("li")) {
			String subInName = subLine.select("span[class=sb-aktion-wechsel-ein]").select("a").attr("title");
			String subOutName = subLine.select("span[class=sb-aktion-wechsel-aus]").select("a").attr("title");
			if(subLine.attr("class").equals("sb-aktion-heim")) {	
				Player subIn = retrievePlayerByName(subInName, match.getHomeTeam());
				Player subOut = retrievePlayerByName(subOutName, match.getHomeTeam());	
				match.getSubstitutePlayerHome().put(subOut, subIn);
				subIn.getMatchList().add(match);
			} else if (subLine.attr("class").equals("sb-aktion-gast")) {
				Player subIn = retrievePlayerByName(subInName, match.getAwayTeam());
				Player subOut = retrievePlayerByName(subOutName, match.getAwayTeam());
				match.getSubstitutePlayerAway().put(subOut, subIn);
				subIn.getMatchList().add(match);
			}
		}

	}

	public void scrapeMissedPenaltyies(Document matchDocument, Match match) {
		Elements penaltyBox = matchDocument.select("div[id=sb-verschossene]");
		for (Element penaltyLine : penaltyBox.select("li")) {
			String penaltyTakerName = penaltyBox.select("span[class=sb-aktion-wechsel-ein]").select("a").attr("title");
			String keeperName = penaltyBox.select("span[class=sb-aktion-wechsel-aus]").select("a").attr("title");
			String penaltyInformation = penaltyBox.select("div[class=sb-aktion-aktion]").text().toLowerCase();
			if(penaltyLine.attr("class").equals("sb-aktion-heim")) {	
				Player penaltyTaker = retrievePlayerByName(penaltyTakerName, match.getHomeTeam());
				Player keeper = retrievePlayerByName(keeperName, match.getAwayTeam());
				match.getPenaltyMissListHome().put(penaltyTaker, match.getPenaltyMissListHome().get(penaltyTaker)+1);
				if (penaltyInformation.contains("saved")) {
					match.getPenaltySavedAway().put(keeper, match.getPenaltySavedAway().get(keeper)+1);

				} else if (penaltyInformation.contains("missed")) {
				}

			} else if (penaltyLine.attr("class").equals("sb-aktion-gast")) {
				Player penaltyTaker = retrievePlayerByName(penaltyTakerName, match.getAwayTeam());
				Player keeper = retrievePlayerByName(keeperName, match.getHomeTeam());
				match.getPenaltyMissListAway().put(penaltyTaker, match.getPenaltyMissListAway().get(penaltyTaker)+1);
				if (penaltyInformation.contains("saved")) {
					match.getPenaltySavedHome().put(keeper, match.getPenaltySavedHome().get(keeper)+1);

				} else if (penaltyInformation.contains("missed")) {
				}
			}
		}
	}

	public void scrapeCard(Document matchDocument, Match match) {
		Elements cardBox = matchDocument.select("div[id=sb-karten]");
		for (Element cardLine : cardBox.select("li")) {
			String playerCardName = cardLine.select("div[class=sb-aktion-aktion]").select("a").attr("title");
			//Check for card-type
			String cardType = cardLine.select("div[class=sb-aktion-spielstand]").select("span").attr("class").toLowerCase().trim();

			//HomeTeam
			if(cardLine.attr("class").equals("sb-aktion-heim")) {
				Player cardPlayer = retrievePlayerByName(playerCardName, match.getHomeTeam());
				//check for yellow card
				if (cardType.equals("sb-sprite sb-gelb")) {
					match.getYellowCardListHome().put(cardPlayer, match.getYellowCardListHome().get(cardPlayer)+1);
				}
				//check for second yellow
				else if (cardType.equals("sb-sprite sb-gelbrot")) {
					match.getYellowRedCardListHome().put(cardPlayer, match.getYellowRedCardListHome().get(cardPlayer)+1);
				}
				//check for straight red
				else if (cardType.equals("sb-sprite sb-rot")) {
					match.getRedCardListHome().put(cardPlayer, match.getRedCardListHome().get(cardPlayer)+1);
				}

				//AwayTeam	
			} else if (cardLine.attr("class").equals("sb-aktion-gast")) {
				Player cardPlayer = retrievePlayerByName(playerCardName, match.getAwayTeam());			
				if (cardType.equals("sb-sprite sb-gelb")) {
					match.getYellowCardListAway().put(cardPlayer, match.getYellowCardListAway().get(cardPlayer)+1);
				}
				//check for second yellow
				else if (cardType.equals("sb-sprite sb-gelbrot")) {
					match.getYellowRedCardListAway().put(cardPlayer, match.getYellowRedCardListAway().get(cardPlayer)+1);
				}
				//check for straight red
				else if (cardType.equals("sb-sprite sb-rot")) {
					match.getRedCardListAway().put(cardPlayer, match.getRedCardListAway().get(cardPlayer)+1);
				}
			}
		}
	}

	public void scrapePenaltyShootOut(Document matchDocument, Match match) {
		Country homeTeam = match.getHomeTeam();
		Country awayTeam = match.getAwayTeam();
		Player keeperHome = null;
		Player keeperAway = null;

		for (Player player : match.getAppearanceList()) {
			if (player.getPosition().equals("Keeper") && player.getCountry() == homeTeam) { 
				keeperHome = player;
			}
			if (player.getPosition().equals("Keeper") && player.getCountry() == awayTeam) { 
				keeperAway = player;
			}
		}

		if(match.getSubstitutePlayerHome().containsKey(keeperHome)) {
			keeperHome = match.getSubstitutePlayerHome().get(keeperHome);
			if(match.getSubstitutePlayerHome().containsKey(keeperHome)) {
				keeperHome = match.getSubstitutePlayerHome().get(keeperHome);
				if(match.getSubstitutePlayerHome().containsKey(keeperHome)) {
					keeperHome = match.getSubstitutePlayerHome().get(keeperHome);
				}
			}
		}


		if(match.getSubstitutePlayerAway().containsKey(keeperAway)) {
			keeperAway = match.getSubstitutePlayerAway().get(keeperAway);
			if(match.getSubstitutePlayerAway().containsKey(keeperAway)) {
				keeperAway = match.getSubstitutePlayerAway().get(keeperAway);
				if(match.getSubstitutePlayerAway().containsKey(keeperAway)) {
					keeperAway = match.getSubstitutePlayerAway().get(keeperAway);
				}
			}
		}

		Elements shootOutBox = matchDocument.select("div[id=sb-elfmeterscheissen]");
		for (Element penaltyRow : shootOutBox.select("li")) {
			Elements penalty = penaltyRow.select("div[class=sb-aktion-aktion]");
			String penaltyTakerName = penalty.select("a").attr("title");
			String penaltyInfo = penalty.text().trim().toLowerCase();

			if(penaltyRow.attr("class").equals("sb-aktion-heim")) {
				Player penaltyTaker = retrievePlayerByName(penaltyTakerName, match.getHomeTeam());	
				if (penaltyInfo.contains("goal")) {
					match.getPenaltyShootOutHitListHome().put(penaltyTaker, match.getPenaltyShootOutHitListHome().get(penaltyTaker)+1);
				}
				else if (penaltyInfo.contains("saved")) {
					match.getPenaltyShootOutMissListHome().put(penaltyTaker, match.getPenaltyShootOutMissListHome().get(penaltyTaker)+1);
					match.getPenaltyShootOutSavedAway().put(keeperAway, match.getPenaltyShootOutSavedAway().get(keeperAway)+1);
				}
				else if (penaltyInfo.contains("missed")){
					match.getPenaltyShootOutMissListHome().put(penaltyTaker, match.getPenaltyShootOutMissListHome().get(penaltyTaker)+1);
				}

			} else if(penaltyRow.attr("class").equals("sb-aktion-gast")) {
				Player penaltyTaker = retrievePlayerByName(penaltyTakerName, match.getAwayTeam());
				if (penaltyInfo.contains("goal")) {
					match.getPenaltyShootOutHitListAway().put(penaltyTaker, match.getPenaltyShootOutHitListAway().get(penaltyTaker)+1);
				}
				else if (penaltyInfo.contains("saved")) {
					match.getPenaltyShootOutMissListAway().put(penaltyTaker, match.getPenaltyShootOutMissListAway().get(penaltyTaker)+1);
					match.getPenaltyShootOutSavedHome().put(keeperHome, match.getPenaltyShootOutSavedHome().get(keeperHome)+1);
				}
				else if (penaltyInfo.contains("missed")){
					match.getPenaltyShootOutMissListAway().put(penaltyTaker, match.getPenaltyShootOutMissListAway().get(penaltyTaker)+1);
				}
			}
		}
	}

	public Player retrievePlayerByName(String PlayerName, Country country) {
		Player playerInTeam = null;
		for (Player player : country.getPlayerList()) {
			if(player.getPlayerName().equals(PlayerName)) {
				playerInTeam = player;
				return playerInTeam;
			}
		}
		//System.out.println("Player not found");
		return null;
	}

	public void printPlayerStats(Map<Player, Integer> statsMap, String stattype) {
		for (Player player : statsMap.keySet()) {
			if (statsMap.get(player) > 0) {
				System.out.println("Matchoverview: " + player.getPlayerName() +": " + statsMap.get(player) + " " + stattype);
			}
		}
	}

	public void printMatchScore(Match match) {
		Map<Country, Integer> score = match.getScore();
		Country hometeam = match.getHomeTeam();
		Country awayteam = match.getAwayTeam();
		System.out.println(hometeam.getCountryName() + " " + score.get(hometeam) + "-" 
				+ score.get(awayteam) + " " + awayteam.getCountryName());
	}

	public void calculateCleanSheet(Match match) {
		Country homeTeam = match.getHomeTeam();
		Country awayTeam = match.getAwayTeam();
		List<Player> homeList = new ArrayList<>();
		List<Player> awayList = new ArrayList<>();

		for (Player player : match.getAppearanceList()) {
			if (player.getCountry() == homeTeam) {
				homeList.add(player);
			} else if (player.getCountry() == awayTeam) {
				awayList.add(player);
			} else System.out.println("ERROR");
		}

		//Check for incoming substitutes
		for (Player player : match.getSubstitutePlayerHome().keySet()) {
			homeList.add(match.getSubstitutePlayerHome().get(player));
		}
		for (Player player : match.getSubstitutePlayerAway().keySet()) {
			awayList.add(match.getSubstitutePlayerAway().get(player));
		}

		if (match.getScore().get(awayTeam) == 0) {
			match.getZeroAgainstListHome().addAll(homeList);
		}
		if (match.getScore().get(homeTeam) == 0) {
			match.getZeroAgainstListAway().addAll(awayList);
		}
	}

	public void printSubs(Map<Player, Player> subMap) {
		for (Player player : subMap.keySet()) {
			System.out.println(player.getPlayerName()+ " " + subMap.get(player).getPlayerName());
		}
	}

	private boolean isGroupFase(int speelRonde) {
		boolean isGroupMatch = false;
		if (speelRonde <= 3) {
			return isGroupMatch = true;
		}
		return isGroupMatch;
	}

}
