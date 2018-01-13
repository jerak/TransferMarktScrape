package attributes;

import java.util.ArrayList;
import java.util.List;

public class Country {

	private String CountryName;
	private String CountryHTML;
	private double CountryValue;
	private Integer FifaRanking;
	private List<Player> PlayerList;
	private String flagPath;
	
	
	public Country(String CountryName) {
		this.CountryName = CountryName;
		PlayerList = new ArrayList<>();
	}

	public List<Player> getPlayerList() {
		return PlayerList;
	}

	public void setPlayerList(List<Player> playerList) {
		PlayerList = playerList;
	}

	public String getCountryName() {
		return CountryName;
	}

	public void setCountryName(String countryName) {
		CountryName = countryName;
	}

	public String getCountryHTML() {
		return CountryHTML;
	}

	public void setCountryHTML(String countryHTML) {
		CountryHTML = countryHTML;
	}

	public double getCountryValue() {
		return CountryValue;
	}

	public Integer getFifaRanking() {
		return FifaRanking;
	}

	public void setFifaRanking(Integer fifaRanking) {
		FifaRanking = fifaRanking;
	}

	public void setCountryValue(double countryValue) {
		CountryValue = countryValue;
	}

	public String getFlagPath() {
		return flagPath;
	}

	public void setFlagPath(String flagURL) {
		this.flagPath = flagURL;
	}
	
}
