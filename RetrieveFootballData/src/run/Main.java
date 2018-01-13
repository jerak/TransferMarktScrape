package run;

import java.io.IOException;

import com.mysql.jdbc.Connection;

public class Main {

	static Connection con;

	public static void main(String[] args) throws IOException {

		System.out.println("Retrieving Player Data");
		Engine engine = new Engine();
		engine.run();

	}
}
