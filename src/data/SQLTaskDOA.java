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
	public List<Task> findTaskbyGroupId(Integer id) throws SQLException {
		List<Task> list = new ArrayList<Task>(); 
		Task task = null; 
		Connection dbConnection = null;
		Statement statement = null;
		
		try {
			dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
			statement = dbConnection.createStatement();
			String condition = "SELECT * FROM Tasks WHERE GROUPID = " + id; 
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
				
				task.setTaskID(Id);
				task.setName(name);
				task.setDueDate(duedate); 
				task.setPriority(priority);
				task.setProgress(progress);
				task.setDescription(description);
				task.setGroupID(groupId);
				task.setUserID(userId);
				
				list.add(task); 
				
			}else {
				System.out.println("No data found for input query");
				throw new RuntimeException(); 
			}
			System.out.println("Record is found from Tasks table!");
			
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
	public void save(Task task) throws SQLException {
		String insertTaskSQL = null; 
	
		insertTaskSQL = "INSERT INTO Tasks" + "(NAME,DUEDATE,PRIORITY,PROGRESS,GROUPID,DESCRIPTION,USERID) " + "VALUES"
					+ "('" + task.getName() + "', DATE('" + task.getDueDate() + "'), " + 
					task.getPriority() + ", " + task.getProgress()  + ", " + task.getGroupID()
					+ ", '" + task.getDescription() + "', " + task.getUserID() + ")";
		
		
		Connection dbConnection = null;
		Statement statement = null;
		
		try {
			dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
			statement = dbConnection.createStatement();
			System.out.println(insertTaskSQL);
			// execute insert SQL statement
			statement.executeUpdate(insertTaskSQL);
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

	@Override
	public void deleteTaskFromTaskID(Integer id) throws SQLException {
		if(id != null) {
			Connection dbConnection = null;
			Statement statement = null;
			String deleteTaskSQL = "DELETE FROM Tasks WHERE TaskID = " + id;
			try {
				dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
				statement = dbConnection.createStatement();
				System.out.println(deleteTaskSQL);
				// execute delete SQL statement
				statement.execute(deleteTaskSQL);
				System.out.println("Record is deleted from Tasks table!");
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
	public void deleteTaskFromUserID(Integer id) throws SQLException {
		if(id != null) {
			Connection dbConnection = null;
			Statement statement = null;
			String deleteTaskSQL = "DELETE FROM Tasks WHERE UserID = " + id;
			try {
				dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
				statement = dbConnection.createStatement();
				System.out.println(deleteTaskSQL);
				// execute delete SQL statement
				statement.execute(deleteTaskSQL);
				System.out.println("Record is deleted from Tasks table!");
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
	public void deleteTaskFromGroupID(Integer id) throws SQLException {
		if(id != null) {
			Connection dbConnection = null;
			Statement statement = null;
			String deleteTaskSQL = "DELETE FROM Tasks WHERE GroupID = " + id;
			try {
				dbConnection = ConnectionFactory.getInstance().getDBConnection(); 
				statement = dbConnection.createStatement();
				System.out.println(deleteTaskSQL);
				// execute delete SQL statement
				statement.execute(deleteTaskSQL);
				System.out.println("Records deleted from Tasks table!");
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
