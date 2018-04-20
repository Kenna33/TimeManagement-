package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionFactory;

public class SQLGroupDOA implements GroupDOA{

	@Override
	public List<Group> findGroupbyUserId(Integer id) throws SQLException {
		List<Group> list = new ArrayList<Group>(); 
		Group group = null; 
		Connection dbConnection = null;
		Statement statement = null;
		
		try {
			dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
			statement = dbConnection.createStatement();
			String condition = "SELECT * FROM Groups WHERE USERID = " + id; 
			ResultSet rs = statement.executeQuery(condition);
			
			if(rs.next()) {
				group = new Group(); 
				String name = rs.getString("NAME"); 
				Integer groupId = rs.getInt("GROUPID"); 
				Integer userId = rs.getInt("USERID"); 
				
				group.setName(name);
				group.setGroupID(groupId);
				group.setUserID(userId);
				
				list.add(group); 
				
			}else {
				System.out.println("No data found for input query");
				throw new RuntimeException(); 
			}
			System.out.println("Record is found from Group table!");
			
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
		return list; 
	}

	@Override
	public void save(Group group) throws SQLException {
		String insertGroupSQL = null; 
		
		insertGroupSQL = "INSERT INTO Groups" + "(NAME,USERID) " + "VALUES"
					+ "('" + group.getName() +  
				    "', "  + group.getUserID() + ")";
		
		
		Connection dbConnection = null;
		Statement statement = null;
		
		try {
			dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
			statement = dbConnection.createStatement();
			System.out.println(insertGroupSQL);
			// execute insert SQL statement
			statement.executeUpdate(insertGroupSQL);
			System.out.println("Record is inserted into Groups table!");
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

	@Override
	public void deleteGroupFromUserID(Integer id) throws SQLException {
		if(id != null) {
			Connection dbConnection = null;
			Statement statement = null;
			String deleteGroupSQL = "DELETE FROM Groups WHERE UserID = " + id;
			try {
				dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
				statement = dbConnection.createStatement();
				System.out.println(deleteGroupSQL);
				// execute delete SQL statement
				statement.execute(deleteGroupSQL);
				System.out.println("Record is deleted from Group table!");
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

	@Override
	public void deleteGroupFromGroupID(Integer id) throws SQLException {
		if(id != null) {
			Connection dbConnection = null;
			Statement statement = null;
			String deleteGroupSQL = "DELETE FROM Groups WHERE GroupID = " + id;
			try {
				dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
				statement = dbConnection.createStatement();
				System.out.println(deleteGroupSQL);
				// execute delete SQL statement
				statement.execute(deleteGroupSQL);
				System.out.println("Record is deleted from Group table!");
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

}
