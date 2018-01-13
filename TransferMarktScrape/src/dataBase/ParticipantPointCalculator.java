package dataBase;

import java.sql.PreparedStatement;
import java.util.List;

import com.mysql.jdbc.Connection;

import attributes.Participant;
import attributes.Squad;

public class ParticipantPointCalculator {

	private Connection con;


	private void setConnection(Connection connection){
		con = connection;
	}

	public void pointCalculator(List<Participant> participantList) {

		try {
			setConnection(DataBaseConnection.establishConnection("jdbc:mysql://localhost:3306/playerscores"));

			String sqlClearTable = "DELETE FROM deelnemerdatabase";
			PreparedStatement ps = con.prepareStatement(sqlClearTable);
			ps.executeUpdate();

			for (Participant participant : participantList) {
				double participantScoreGroup = 0;
				double participantScoreKO = 0;
				double participantScoreTotal = 0;
				Squad squadGroup = participant.getSquadGroup();
				Squad squadKO = participant.getSquadKO();

				//Calculate group score for each participant
				participantScoreGroup += squadGroup.getDm().getGroupPoints();
				participantScoreGroup += squadGroup.getRa().getGroupPoints();
				participantScoreGroup += squadGroup.getRcv().getGroupPoints();
				participantScoreGroup += squadGroup.getLcv().getGroupPoints();
				participantScoreGroup += squadGroup.getLa().getGroupPoints();
				participantScoreGroup += squadGroup.getRm().getGroupPoints();
				participantScoreGroup += squadGroup.getCm().getGroupPoints();
				participantScoreGroup += squadGroup.getLm().getGroupPoints();
				participantScoreGroup += squadGroup.getRb().getGroupPoints();
				participantScoreGroup += squadGroup.getSp().getGroupPoints();
				participantScoreGroup += squadGroup.getLb().getGroupPoints();

				//Calculate KO score for each participant
				participantScoreKO += squadKO.getDm().getKoPoints();
				participantScoreKO += squadKO.getRa().getKoPoints();
				participantScoreKO += squadKO.getRcv().getKoPoints();
				participantScoreKO += squadKO.getLcv().getKoPoints();
				participantScoreKO += squadKO.getLa().getKoPoints();
				participantScoreKO += squadKO.getRm().getKoPoints();
				participantScoreKO += squadKO.getCm().getKoPoints();
				participantScoreKO += squadKO.getLm().getKoPoints();
				participantScoreKO += squadKO.getRb().getKoPoints();
				participantScoreKO += squadKO.getSp().getKoPoints();
				participantScoreKO += squadKO.getLb().getKoPoints();

				participantScoreTotal = participantScoreGroup + participantScoreKO;

				//Write the score to the database
				String mySQLString = "insert into deelnemerdatabase(deelnemernaam, deelnemerpunten)values(?,?)";
				ps = con.prepareStatement(mySQLString);
				ps.setString(1, participant.getParticipantName());
				ps.setDouble(2, participantScoreTotal);
				ps.executeUpdate();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
}