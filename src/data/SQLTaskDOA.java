package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionFactory;

public class SQLTaskDOA implements TaskDOA{

	
	@Override
	public List<Task> findTaskbyGroupId(Integer id){
		List<Task> list = new ArrayList<Task>(); 
		Task task = null; 
	
		try {
			Connection connection = ConnectionFactory.getInstance().getDBConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Tasks WHERE GROUPID = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				task = new Task();
				
				task.setTaskID(rs.getInt("TASKID"));
				task.setName(rs.getString("NAME"));
				task.setDueDate(rs.getDate("DUEDATE")); 
				task.setPriority(rs.getInt("PRIORITY"));
				task.setProgress(rs.getInt("PROGRESS"));
				task.setDescription(rs.getString("DESCRIPTION"));
				task.setGroupID(rs.getInt("GROUPID"));
				task.setUserID(rs.getInt("USERID"));
				
				list.add(task); 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	

	@Override
	public void save(Task task){
		
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
		} 
		
		/*
		Connection sqlConnection;
		try {
			sqlConnection = ConnectionFactory.getInstance().getDBConnection();
			PreparedStatement statement = sqlConnection.prepareStatement("INSERT INTO Tasks "
					+ "(NAME,DUEDATE,PRIORITY,PROGRESS,GROUPID,DESCRIPTION,USERID)"
					+ " VALUES (?,?,?,?,?,?,?)");
			statement.setString(1, task.getName());
			//statement.setDate(2, task.getDueDate());
			statement.setDate(2, task.getDueDate());
			statement.setInt(3, task.getPriority());
			statement.setInt(4, task.getProgress());
			statement.setLong(5, task.getGroupID());
			statement.setString(2, task.getDescription());
			statement.setLong(2, task.getUserID());

			statement.executeUpdate();

		} catch (SQLException e) {
			// Should replace with log message
			System.out.println("Could not save the task");
			e.printStackTrace();
		}
		*/
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





	@Override
	public void updateTask(Task task) {
		try {
			Connection connection = ConnectionFactory.getInstance().getDBConnection();
			PreparedStatement ps = connection.prepareStatement("UPDATE Tasks SET Name = ? , DueDate = ? "
					+ ", Description = ? , Priority = ? , Progress = ? WHERE TaskID = ?");
			ps.setString(1, task.getName());
			ps.setDate(2, task.getDueDate());
			ps.setString(3, task.getDescription());
			ps.setInt(4, task.getPriority());
			ps.setInt(5, task.getProgress());
			ps.setInt(6, task.getTaskID());

			System.out.println("Record is updated into UserAccounts table!");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
