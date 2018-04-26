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

import Connection.ConnectionFactory;

//DAO Pattern- Data Access Object 
public class SQLGroupDAO implements GroupDAO {


    public List<Group> findGroupbyUserId(Integer id) throws SQLException {
        List<Group> list = new ArrayList<Group>();
        Group group = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionFactory.getInstance().getDBConnection();
            ps = connection.prepareStatement("SELECT * FROM Groups WHERE USERID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            try {
                while (rs.next()) {
                    group = new Group();
                    group.setName(rs.getString("NAME"));
                    group.setGroupID(rs.getInt("GROUPID"));
                    group.setUserID(rs.getInt("USERID"));
                    list.add(group);
                }
            } finally {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("Could not find groups");
            e.printStackTrace();
        }finally {
            if(ps != null) {ps.close();}
            if(connection != null) {connection.close();}
        }
        return list;
    }


    public void save(Group group) throws SQLException {
        Connection sqlConnection = null;
        PreparedStatement statement = null;

        try {
            sqlConnection = ConnectionFactory.getInstance().getDBConnection();
            statement = sqlConnection.prepareStatement("INSERT INTO Groups (NAME,USERID) VALUES (?,?)");
            statement.setString(1, group.getName());
            statement.setLong(2, group.getUserID());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Could not save group");
            e.printStackTrace();
        }finally {
            if(statement != null) {statement.close();}
            if(sqlConnection != null) {sqlConnection.close();}
        }
    }

    public void deleteGroupFromUserID(Integer id) throws SQLException {
        Connection sqlConnection = null;
        PreparedStatement statement = null;
        if (id != null) {
            try {
                sqlConnection = ConnectionFactory.getInstance().getDBConnection();
                statement = sqlConnection.prepareStatement("DELETE FROM Groups WHERE UserID = ?");
                statement.setLong(1, id);
                statement.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Could not delete group");
                e.printStackTrace();
            }finally {
                if(statement != null) {statement.close();}
                if(sqlConnection != null) {sqlConnection.close();}
            }
        }
    }

    public void deleteGroupFromGroupID(Integer id) throws SQLException {
        Connection sqlConnection = null;
        PreparedStatement statement = null;
        if (id != null) {
            try {
                sqlConnection = ConnectionFactory.getInstance().getDBConnection();
                statement = sqlConnection.prepareStatement("DELETE FROM Groups WHERE GroupID = ?");
                statement.setLong(1, id);
                statement.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Could not delete group");
                e.printStackTrace();
            }finally {
                if(statement != null) {statement.close();}
                if(sqlConnection != null) {sqlConnection.close();}
            }
        }
    }


    public void updateGroup(Group group) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionFactory.getInstance().getDBConnection();
            ps = connection.prepareStatement("UPDATE Groups SET name = ? WHERE GroupID = ?");
            ps.setString(1, group.getName());
            ps.setInt(2, group.getGroupID());
            ps.executeUpdate();
            System.out.println("Record updated into Groups table!");
        } catch (SQLException e) {
            System.out.println("Could not update record into Groups table");
            e.printStackTrace();
        }finally {
            if(ps != null) {ps.close();}
            if(connection != null) {connection.close();}
        }
    }

}
