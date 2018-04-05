package mcken.desktop.IntroToJava.TimeManagement;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateUserAccountsTable {
	
	private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DB_CONNECTION = "jdbc:derby:connection1;";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";

	/*
	public static void main(String[] argv) {
		try {
			createUserAccountTable(); 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	*/

	public static void createUserAccountTable() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String createTableSQL = "CREATE TABLE UserAccounts"
				+"(UserID bigint NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 100, INCREMENT BY 1), " 
				+"UserName varchar(40) NOT NULL, " 
				+"Email varchar(40) NOT NULL, "  
				+"PhoneNum varchar(40) NOT NULL,"
				+ "Password varchar(40) NOT NULL, "
				+ "Admin boolean, "
				+ "PRIMARY KEY (UserID) " + ")";;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(createTableSQL);
			// execute the SQL statement
			statement.execute(createTableSQL);
			System.out.println("Table \"UserAccounts\" is created!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}

	public static Connection getDBConnection() {
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
	
	public static void deleteTable() throws SQLException {
		
		Connection dbConnection = null;
		Statement statement = null;
		String createString ="DROP TABLE UserAccounts";
		
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(createString);
			// execute the SQL statement
			statement.execute(createString);
			System.out.println("Table UserAccounts is deleted!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}
}


