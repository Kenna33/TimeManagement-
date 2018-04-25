/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: SQLTaskDOA
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
public class SQLTaskDAO implements TaskDAO{
	
	
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
				System.out.println("Task found from Task table!");
			}

			
		} catch (SQLException e) {
			System.out.println("Could not find Tasks");
			e.printStackTrace();
		}
		
		return list;
	}
	

	@Override
	public void save(Task task){
		Connection sqlConnection;
		try {
			sqlConnection = ConnectionFactory.getInstance().getDBConnection();
			PreparedStatement statement = sqlConnection.prepareStatement("INSERT INTO Tasks "
					+ "(NAME,DUEDATE,PRIORITY,PROGRESS,GROUPID,DESCRIPTION,USERID)"
					+ " VALUES (?,DATE('" + task.getDueDate() + "'),?,?,?,?,?)");
			statement.setString(1, task.getName());
			statement.setInt(2, task.getPriority());
			statement.setInt(3, task.getProgress());
			statement.setLong(4, task.getGroupID());
			statement.setString(5, task.getDescription());
			statement.setLong(6, task.getUserID());

			statement.executeUpdate();
			System.out.println("Record inserted into Task table!");

		} catch (SQLException e) {
			System.out.println("Could not save the task");
			e.printStackTrace();
		}
	}

	@Override
	public void deleteTaskFromTaskID(Integer id){
		Connection sqlConnection;
		if(id != null) {
			try {
				sqlConnection = ConnectionFactory.getInstance().getDBConnection();
				PreparedStatement statement = sqlConnection.prepareStatement("DELETE FROM Tasks WHERE TaskID = ?");
				statement.setLong(1, id);
				statement.executeUpdate();
				System.out.println("Record deleted from Task table!");
				
			} catch (SQLException e) {
				System.out.println("Could not delete task");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteTaskFromUserID(Integer id){
		Connection sqlConnection;
		if(id != null) {
			try {
				sqlConnection = ConnectionFactory.getInstance().getDBConnection();
				PreparedStatement statement = sqlConnection.prepareStatement("DELETE FROM Tasks WHERE UserID = ?");
				statement.setLong(1, id);
				statement.executeUpdate();
				System.out.println("Record deleted from Task table!");
				
			} catch (SQLException e) {
				System.out.println("Could not delete task");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteTaskFromGroupID(Integer id){
		Connection sqlConnection;
		if(id != null) {
			try {
				sqlConnection = ConnectionFactory.getInstance().getDBConnection();
				PreparedStatement statement = sqlConnection.prepareStatement("DELETE FROM Tasks WHERE GroupID = ?");
				statement.setLong(1, id);
				statement.executeUpdate();
				System.out.println("Record deleted from Task table!");
				
			} catch (SQLException e) {
				System.out.println("Could not delete task");
				e.printStackTrace();
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

			System.out.println("Record is updated into Tasks table!");
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Could not update record in tasks table"); 
			e.printStackTrace();
		}
	}
}
