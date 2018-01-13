package attributes;

import java.util.Map;

public class Squad {

	private Map<Player, String> squadPlayers;
	private Player dm;
	private Player ra;
	private Player rcv;
	private Player lcv;
	private Player la;
	private Player rm;
	private Player cm;
	private Player lm;
	private Player rb;
	private Player sp;
	private Player lb;
	
	public Squad() {

	}

	public Player getDm() {
		return dm;
	}

	public void setDm(Player dm) {
		this.dm = dm;
	}

	public Player getRa() {
		return ra;
	}

	public void setRa(Player ra) {
		this.ra = ra;
	}

	public Player getRcv() {
		return rcv;
	}

	public void setRcv(Player rcv) {
		this.rcv = rcv;
	}

	public Player getLcv() {
		return lcv;
	}

	public void setLcv(Player lcv) {
		this.lcv = lcv;
	}

	public Player getLa() {
		return la;
	}

	public void setLa(Player la) {
		this.la = la;
	}

	public Player getRm() {
		return rm;
	}

	public void setRm(Player rm) {
		this.rm = rm;
	}

	public Player getCm() {
		return cm;
	}

	public void setCm(Player cm) {
		this.cm = cm;
	}

	public Player getLm() {
		return lm;
	}

	public void setLm(Player lm) {
		this.lm = lm;
	}

	public Player getRb() {
		return rb;
	}

	public void setRb(Player rb) {
		this.rb = rb;
	}

	public Player getSp() {
		return sp;
	}

	public void setSp(Player sp) {
		this.sp = sp;
	}

	public Player getLb() {
		return lb;
	}

	public void setLb(Player lb) {
		this.lb = lb;
	}

	public Map<Player, String> getSquadPlayers() {
		return squadPlayers;
	}

	public void setSquadPlayers(Map<Player, String> squadPlayers) {
		this.squadPlayers = squadPlayers;
	}


}
