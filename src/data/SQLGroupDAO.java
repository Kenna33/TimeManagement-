/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: SQLGroupDAO
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import Connection.ConnectionFactory;

//DAO Pattern- Data Access Object 
public class SQLGroupDAO implements GroupDAO{
	
	@Override
	public List<Group> findGroupbyUserId(Integer id){
		List<Group> list = new ArrayList<Group>(); 
		Group group = null; 

		try {
			Connection connection = ConnectionFactory.getInstance().getDBConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Groups WHERE USERID = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				group = new Group();
				group.setName(rs.getString("NAME"));
				group.setGroupID(rs.getInt("GROUPID"));
				group.setUserID(rs.getInt("USERID"));
				list.add(group); 
			}
			
		} catch (SQLException e) {
			System.out.println("Could not find groups");
			e.printStackTrace();
		}	
		return list;
	}
 
	
	@Override
	public void save(Group group) {
		Connection sqlConnection;
		try {
			sqlConnection = ConnectionFactory.getInstance().getDBConnection();
			PreparedStatement statement = sqlConnection.prepareStatement("INSERT INTO Groups (NAME,USERID) VALUES (?,?)");
			statement.setString(1, group.getName());
			statement.setLong(2, group.getUserID());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Could not save group");
			e.printStackTrace();
		}
	}

	@Override
	public void deleteGroupFromUserID(Integer id){
		Connection sqlConnection;
		if(id != null) {
			try {
				sqlConnection = ConnectionFactory.getInstance().getDBConnection();
				PreparedStatement statement = sqlConnection.prepareStatement("DELETE FROM Groups WHERE UserID = ?");
				statement.setLong(1, id);
				statement.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("Could not delete group");
				e.printStackTrace();
			}
		}
	}

	
	@Override
	public void deleteGroupFromGroupID(Integer id){
		Connection sqlConnection;
		if(id != null) {
			try {
				sqlConnection = ConnectionFactory.getInstance().getDBConnection();
				PreparedStatement statement = sqlConnection.prepareStatement("DELETE FROM Groups WHERE GroupID = ?");
				statement.setLong(1, id);
				statement.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("Could not delete group");
				e.printStackTrace();
			}
		}
	}


	@Override
	public void updateGroup(Group group) {
			try {
				Connection connection = ConnectionFactory.getInstance().getDBConnection();
				PreparedStatement ps = connection.prepareStatement("UPDATE Groups SET name = ? WHERE GroupID = ?");
				ps.setString(1, group.getName());
				ps.setInt(2, group.getGroupID());
				ps.executeUpdate();
				System.out.println("Record updated into Groups table!"); 
			} catch (SQLException e) {
				System.out.println("Could not update record into Groups table"); 
				e.printStackTrace();
			}
	}
		

}
