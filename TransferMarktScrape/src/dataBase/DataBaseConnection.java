package dataBase;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DataBaseConnection {

	public static final String dataBaseUserName = "root";
	public static final String dataBasePassWord = "";

	static Connection con;
	
	public static Connection establishConnection(String url){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(url, 
					DataBaseConnection.dataBaseUserName, DataBaseConnection.dataBasePassWord);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
}


