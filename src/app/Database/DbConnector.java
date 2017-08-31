package app.Database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DbConnector {
	
	public Connection connection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");

		return DriverManager.getConnection("jdbc:mysql://localhost:3306/quizedb","root","kurs");
	}
}
