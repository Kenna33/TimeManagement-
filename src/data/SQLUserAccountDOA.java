package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import Connection.ConnectionFactory;


public class SQLUserAccountDOA implements UserAccountDOA{

	
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
	}
	
	public void deleteUser(Integer thisID) throws SQLException {
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
	}
	
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

	@Override
	public List<UserAccount> findUserbyGroupId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAccount findUserById(Integer id) throws SQLException {
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
				Boolean admin = rs.getBoolean("ADMIN"); 
				user.setUserID(Id);
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
}
