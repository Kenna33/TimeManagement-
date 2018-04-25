/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: ConnectionFactory
*/
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Creates single instance of itself: Singleton 
 * Abstract Factory 
 */
public class ConnectionFactory {

	private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DB_CONNECTION = "jdbc:derby:connection1;";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";
	
	private static ConnectionFactory INSTANCE;
	
	//Singleton Method 
	public static ConnectionFactory getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new ConnectionFactory();
		}
		return INSTANCE;
	}

	public Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
}
