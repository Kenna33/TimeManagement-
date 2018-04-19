package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionFactory;

public class SQLTaskDOA implements TaskDOA{

	public SQLTaskDOA() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public List<Task> findTaskbyUserId(Integer id) {
		/*
		List<Task> list = new ArrayList<Task>(); 
		Task task = null; 
		Connection dbConnection = null;
		Statement statement = null;
		
		try {
			dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
			statement = dbConnection.createStatement();
			String condition = "SELECT * FROM Tasks WHERE USERID = " + id; 
			ResultSet rs = statement.executeQuery(condition);
			
			if(rs.next()) {
				task = new Task(); 
				Integer Id = rs.getInt("TASKID");
				String name = rs.getString("NAME"); 
				Date duedate = rs.getDate("DUEDATE"); 
				int priority = rs.getInt("PRIORITY"); 
				int progress = rs.getInt("PROGRESS");
				String description = rs.getString("DESCRIPTION"); 
				Integer groupId = rs.getInt("GROUPID"); 
				Integer userId = rs.getInt("USERID"); 
				
				//	
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
	}
	

	@Override
	public void save(Task task) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTask(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
