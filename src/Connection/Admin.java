/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: Admin
*/
package Connection;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Admin methods static only to be called from 
 * tests and back end management of database, 
 * Not for user to know about
 */
public class Admin {
	
	public static void createUserAccountTable() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String createTableSQL = "CREATE TABLE UserAccounts"
				+"(UserID bigint NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1), " 
				+"UserName varchar(40) NOT NULL, " 
				+"Email varchar(40) NOT NULL, "  
				+"PhoneNum varchar(40) NOT NULL,"
				+ "Password varchar(40) NOT NULL, "
				+ "PRIMARY KEY (UserID) " + ")";;
		
		try {
			dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
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

	
	public static void deleteUserAccountsTable() throws SQLException {
		
		Connection dbConnection = null;
		Statement statement = null;
		String createString ="DROP TABLE UserAccounts";
		
		
		try {
			dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
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
	
	
	
	public static void createTasksTable() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String createTableSQL = "CREATE TABLE Tasks"
				+"(TaskID bigint NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1), " 
				+"Name varchar(40) NOT NULL, " 
				+"DueDate date NOT NULL, "  
				+"Priority integer NOT NULL,"
				+ "Progress integer NOT NULL, "
				+ "GroupID bigint NOT NULL, "
				+ "Description varchar(40) NOT NULL, "
				+ "UserID bigint NOT NULL, "
				+ "PRIMARY KEY (TaskID) " + ")";;
		
		try {
			dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
			statement = dbConnection.createStatement();
			System.out.println(createTableSQL);
			// execute the SQL statement
			statement.execute(createTableSQL);
			System.out.println("Table Tasks is created!");
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
	
	public static void deleteTasksTable() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String createString ="DROP TABLE Tasks";
		
		try {
			dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
			statement = dbConnection.createStatement();
			System.out.println(createString);
			// execute the SQL statement
			statement.execute(createString);
			System.out.println("Table Tasks is deleted!");
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
	
	public static void createGroupsTable() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String createTableSQL = "CREATE TABLE Groups"
				+"(GroupID bigint NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1), " 
				+"Name varchar(40) NOT NULL, " 
				+ "UserID bigint NOT NULL, "
				+ "PRIMARY KEY (GroupID) " + ")";;
		
		try {
			dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
			statement = dbConnection.createStatement();
			System.out.println(createTableSQL);
			// execute the SQL statement
			statement.execute(createTableSQL);
			System.out.println("Table Groups is created!");
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
	
	public static void deleteGroupsTable() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String createString ="DROP TABLE Groups";
		
		try {
			dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
			statement = dbConnection.createStatement();
			System.out.println(createString);
			// execute the SQL statement
			statement.execute(createString);
			System.out.println("Table Groups is deleted!");
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


