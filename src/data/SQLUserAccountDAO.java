package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.ConnectionFactory;

//DAO Pattern: Data Access Object 
public class SQLUserAccountDAO implements UserAccountDAO{

	public void save(UserAccount user){ 
		/*
		String insertTableSQL = null; 
		if(user.getUserID() == null) {
			insertTableSQL = "INSERT INTO UserAccounts" + "(USERNAME,EMAIL,PHONENUM,PASSWORD) " + "VALUES"
					+ "('" + user.getUserName() + "'" + ", '" + user.getEmail() + "', '" +
					user.getPhoneNum() + "', '" + 
					user.getPassword() + "')";
		}
		
		Connection dbConnection = null;
		Statement statement = null;
		
		try {
			dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
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
		*/
		Connection sqlConnection;
		try {
			sqlConnection = ConnectionFactory.getInstance().getDBConnection();
			PreparedStatement statement = sqlConnection.prepareStatement("INSERT INTO UserAccounts "
					+ "(USERNAME,EMAIL,PHONENUM,PASSWORD) "
					+ " VALUES (?,?,?,?)");
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPhoneNum());
			statement.setString(4, user.getPassword());

			statement.executeUpdate();
			System.out.println("Record inserted into UserAccount table!");

		} catch (SQLException e) {
			System.out.println("Could not save the task");
			e.printStackTrace();
		}
	}
	
	public void deleteUser(Integer thisID){
		/*
		if(thisID != null) {
			Connection dbConnection = null;
			Statement statement = null;
			String deleteTableSQL = "DELETE FROM UserAccounts WHERE UserID = " + thisID;
			try {
				dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
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
		*/
		Connection sqlConnection;
		if(thisID != null) {
			try {
				sqlConnection = ConnectionFactory.getInstance().getDBConnection();
				PreparedStatement statement = sqlConnection.prepareStatement("DELETE FROM UserAccounts WHERE UserID = ?");
				statement.setLong(1, thisID);
				statement.executeUpdate();
				System.out.println("Record deleted from UserAccount table!");
				
			} catch (SQLException e) {
				System.out.println("Could not delete user");
				e.printStackTrace();
			}
		}
	}
	
	/*
	public UserAccount findUser(String condition) throws SQLException {
		UserAccount user = null; 
		Connection dbConnection = null;
		Statement statement = null;
		
		try {
			dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
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
				user.setUserID(id);
				user.setUserName(UserName);
				user.setEmail(email);
				user.setPhoneNum(phoneNum);
				user.setPassword(password);		
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

	@Override
	public List<UserAccount> findUserbyGroupId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

*/
	@Override
	public UserAccount findUserById(Integer id){
		/*
		UserAccount user = null; 
		Connection dbConnection = null;
		Statement statement = null;
		
		try {
			dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
			statement = dbConnection.createStatement();
			String condition = "SELECT * FROM USERACCOUNTS WHERE USERID = " + id; 
			ResultSet rs = statement.executeQuery(condition);
			
			
			if(rs.next()) {
				user = new UserAccount(); 
				int Id = rs.getInt("USERID");
				String UserName = rs.getString("USERNAME"); 
				String email = rs.getString("EMAIL"); 
				String phoneNum = rs.getString("PHONENUM"); 
				String password = rs.getString("PASSWORD");
				user.setUserID(Id);
				user.setUserName(UserName);
				user.setEmail(email);
				user.setPhoneNum(phoneNum);
				user.setPassword(password);	
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
		*/
		UserAccount user = null; 
		Connection sqlConnection;

		try {
			sqlConnection = ConnectionFactory.getInstance().getDBConnection();
			PreparedStatement statement = sqlConnection.prepareStatement
					("SELECT * FROM USERACCOUNTS WHERE UserID = ?"); 
		    statement.setInt(1, id);
		    ResultSet rs = statement.executeQuery();
			
		    if(rs.next()) {
				user = new UserAccount(); 
				int Id = rs.getInt("USERID");
				String UserName = rs.getString("USERNAME"); 
				String email = rs.getString("EMAIL"); 
				String phoneNum = rs.getString("PHONENUM"); 
				String password = rs.getString("PASSWORD");
				user.setUserID(Id);
				user.setUserName(UserName);
				user.setEmail(email);
				user.setPhoneNum(phoneNum);
				user.setPassword(password);	
				System.out.println("Record is found from UserAccount table!");
			}else {
				System.out.println("User not found from UserAccount table");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return user;  
	}

	@Override
	public Integer findUserBySignIn(String userName, String passwrd){
		Integer Id = null; 
		Connection sqlConnection;

		try {
			sqlConnection = ConnectionFactory.getInstance().getDBConnection();
			PreparedStatement statement = sqlConnection.prepareStatement
					("SELECT UserID FROM USERACCOUNTS WHERE UserName = ? AND Password = ?"); 
		    statement.setString(1, userName);
		    statement.setString(2, passwrd);
		    
		    ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				Id = rs.getInt("USERID");
				System.out.println("Record is found from UserAccount table!");
			}else {
				System.out.println("Record not found from UserAccount table!");
			}	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return Id; 
	}
}
