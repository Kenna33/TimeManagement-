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

import Connection.ConnectionFactory;

//DAO Pattern- Data Access Object 
public class SQLTaskDAO implements TaskDAO {


    public List<Task> findTaskbyGroupId(Integer id) throws SQLException {
        List<Task> list = new ArrayList<Task>();
        Task task = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = ConnectionFactory.getInstance().getDBConnection();
            ps = connection.prepareStatement("SELECT * FROM Tasks WHERE GROUPID = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            try {
                while (rs.next()) {
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
            } finally {
                rs.close();
            }

        } catch (SQLException e) {
            System.out.println("Could not find Tasks");
            e.printStackTrace();
        }finally {
            if(ps != null) {ps.close();}
            if(connection != null) {connection.close();}
        }

        return list;
    }


    public void save(Task task) throws SQLException {
        Connection sqlConnection = null;
        PreparedStatement statement = null;
        try {
            sqlConnection = ConnectionFactory.getInstance().getDBConnection();
            statement = sqlConnection.prepareStatement("INSERT INTO Tasks "
                    + "(NAME,DUEDATE,PRIORITY,PROGRESS,GROUPID,DESCRIPTION,USERID)"
                    + " VALUES (?,?,?,?,?,?,?)");
            statement.setString(1, task.getName());
            statement.setString(2,task.getDueDate());
            statement.setInt(3, task.getPriority());
            statement.setInt(4, task.getProgress());
            statement.setLong(5, task.getGroupID());
            statement.setString(6, task.getDescription());
            statement.setLong(7, task.getUserID());

            statement.executeUpdate();
            System.out.println("Record inserted into Task table!");

        } catch (SQLException e) {
            System.out.println("Could not save the task");
            e.printStackTrace();
        }finally {
            if(statement != null) {statement.close();}
            if(sqlConnection != null) {sqlConnection.close();}
        }
    }

    public void deleteTaskFromTaskID(Integer id) throws SQLException {
        Connection sqlConnection = null;
        PreparedStatement statement = null;
        if (id != null) {
            try {
                sqlConnection = ConnectionFactory.getInstance().getDBConnection();
                statement = sqlConnection.prepareStatement("DELETE FROM Tasks WHERE TaskID = ?");
                statement.setLong(1, id);
                statement.executeUpdate();
                System.out.println("Record deleted from Task table!");

            } catch (SQLException e) {
                System.out.println("Could not delete task");
                e.printStackTrace();
            }finally {
                if(statement != null) {statement.close();}
                if(sqlConnection != null) {sqlConnection.close();}
            }
        }
    }

    public void deleteTaskFromUserID(Integer id) throws SQLException {
        Connection sqlConnection = null;
        PreparedStatement statement = null;
        if (id != null) {
            try {
                sqlConnection = ConnectionFactory.getInstance().getDBConnection();
                statement = sqlConnection.prepareStatement("DELETE FROM Tasks WHERE UserID = ?");
                statement.setLong(1, id);
                statement.executeUpdate();
                System.out.println("Record deleted from Task table!");


            } catch (SQLException e) {
                System.out.println("Could not delete task");
                e.printStackTrace();
            }finally {
                if(statement != null) {statement.close();}
                if(sqlConnection != null) {sqlConnection.close();}
            }
        }
    }

    public void deleteTaskFromGroupID(Integer id) throws SQLException {
        Connection sqlConnection = null;
        PreparedStatement statement = null;
        if (id != null) {
            try {
                sqlConnection = ConnectionFactory.getInstance().getDBConnection();
                statement = sqlConnection.prepareStatement("DELETE FROM Tasks WHERE GroupID = ?");
                statement.setLong(1, id);
                statement.executeUpdate();
                System.out.println("Record deleted from Task table!");

            } catch (SQLException e) {
                System.out.println("Could not delete task");
                e.printStackTrace();
            }finally {
                if(statement != null) {statement.close();}
                if(sqlConnection != null) {sqlConnection.close();}
            }
        }
    }


    public void updateTask(Task task) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getInstance().getDBConnection();
            ps = connection.prepareStatement("UPDATE Tasks SET Name = ? , DueDate = ? "
                    + ", Description = ? , Priority = ? , Progress = ? WHERE TaskID = ?");
            ps.setString(1, task.getName());
            ps.setString(2, task.getDueDate());
            ps.setString(3, task.getDescription());
            ps.setInt(4, task.getPriority());
            ps.setInt(5, task.getProgress());
            ps.setInt(6, task.getTaskID());

            System.out.println("Record is updated into Tasks table!");
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Could not update record in tasks table");
            e.printStackTrace();
        }finally {
            if(ps != null) {ps.close();}
            if(connection != null) {connection.close();}

        }
    }
}
