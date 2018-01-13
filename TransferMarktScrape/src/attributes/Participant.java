package attributes;

public class Participant {

	private String participantName;
	private Squad squadGroup;
	private Squad squadKO;

	public Participant(String participantName) {
		this.participantName = participantName;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public Squad getSquadGroup() {
		return squadGroup;
	}

	public void setSquadGroup(Squad squadGroup) {
		this.squadGroup = squadGroup;
	}

	public Squad getSquadKO() {
		return squadKO;
	}

	public void setSquadKO(Squad squadKO) {
		this.squadKO = squadKO;
	}

}
