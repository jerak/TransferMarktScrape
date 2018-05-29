package dataBase;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Connection;

import attributes.Country;

public class CountryDataBaseReader {

	private Connection con;

	private void setConnection(Connection connection){
		con = connection;
	}

	public List<Country> dataBaseReader() {

		List<Country> countryList = new ArrayList<>();

		try {
			setConnection(DataBaseConnection.getConnection());
			
			Statement m_Statement = con.createStatement();
			String mySQLString = "select * from landengegevens";
			ResultSet m_ResultSet = m_Statement.executeQuery(mySQLString);
			Country country;


			while (m_ResultSet.next()) {
				country = new Country(m_ResultSet.getString("landnaam"));
				countryList.add(country);
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countryList;
	}
}