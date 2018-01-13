package dataBase;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DataBaseConnection {

	public static final String dataBaseUserName = "root";
	public static final String dataBasePassWord = "";
	public static final String url = "jdbc:mysql://localhost:3306/playerdatabase";

	static Connection con;
	
	public static void establishConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(DataBaseConnection.url, 
					DataBaseConnection.dataBaseUserName, DataBaseConnection.dataBasePassWord);
			System.out.println("Connection established");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return con;
	}
	
}


