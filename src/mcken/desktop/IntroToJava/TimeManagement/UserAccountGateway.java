package mcken.desktop.IntroToJava.TimeManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class UserAccountGateway {

	private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DB_CONNECTION = "jdbc:derby:connection1;";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";
	
	public void save(UserAccount user) throws SQLException { 
		String insertTableSQL = null; 
		if(user.getUserID() == null) {
			insertTableSQL = "INSERT INTO UserAccounts" + "(USERNAME,EMAIL,PHONENUM,PASSWORD,ADMIN) " + "VALUES"
					+ "('" + user.getUserName() + "'" + ", '" + user.getEmail() + "', '" +
					user.getPhoneNum() + "', '" + 
					user.getPassword() + "', " + user.getAdmin()  + ")";
		}
		
		Connection dbConnection = null;
		Statement statement = null;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(insertTableSQL);
			// execute insert SQL statement
			statement.executeUpdate(insertTableSQL);
			System.out.println("Record is inserted into UserAccounts table!");
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
	
	public void delete(Integer thisID) throws SQLException {
		if(thisID != null) {
			Connection dbConnection = null;
			Statement statement = null;
			String deleteTableSQL = "DELETE FROM UserAccounts WHERE UserID = " + thisID;
			try {
				dbConnection = getDBConnection();
				statement = dbConnection.createStatement();
				System.out.println(deleteTableSQL);
				// execute delete SQL statement
				statement.execute(deleteTableSQL);
				System.out.println("Record is deleted from UserAccounts table!");
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
	
	public UserAccount findUser(String condition) throws SQLException {
		UserAccount user = null; 
		Connection dbConnection = null;
		Statement statement = null;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(condition);
			// execute delete SQL statement
			ResultSet rs = statement.executeQuery(condition);
			
			if(rs.next()) {
				user = new UserAccount(); 
				int id = rs.getInt("USERID");
				String UserName = rs.getString("USERNAME"); 
				String email = rs.getString("EMAIL"); 
				String phoneNum = rs.getString("PHONENUM"); 
				String password = rs.getString("PASSWORD");
				Boolean admin = rs.getBoolean("ADMIN"); 
				user.setUserID(id);
				user.setUserName(UserName);
				user.setEmail(email);
				user.setPhoneNum(phoneNum);
				user.setPassword(password);
				user.setAdmin(admin); 		
			}else {
				System.out.println("No data found for input query");
				throw new RuntimeException(); 
			}
			System.out.println("Record is found from EMPLOYEE table!");
			
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
	
		return user; 
	}
	
	private static Connection getDBConnection() {
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
