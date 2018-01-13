package dataBase;

import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import com.mysql.jdbc.Connection;

import attributes.Country;

import java.util.List;

public class CountryWriter {

	private Connection con;


	private void setConnection(Connection connection){
		con = connection;
	}

	public void dataBaseWriter(List<Country> countryList) {

		try {
			setConnection(DataBaseConnection.getConnection());

			//Clear current Table
			String sqlClearTable = "DELETE FROM landengegevens";
			PreparedStatement ps = con.prepareStatement(sqlClearTable);
			ps.executeUpdate();

			//Fill table with players

			for (Country country : countryList) {
				String mySQLString = "insert into landengegevens(landnaam, vlag)values(?,?)";
				ps = con.prepareStatement(mySQLString);
				File file = new File(country.getFlagPath());
				FileInputStream fis = new FileInputStream(file);

				ps.setString(1, country.getCountryName());
				ps.setBinaryStream(2, fis);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
