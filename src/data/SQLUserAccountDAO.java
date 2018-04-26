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

import Connection.ConnectionFactory;

//DAO Pattern: Data Access Object 
public class SQLUserAccountDAO implements UserAccountDAO {

    public void save(UserAccount user) throws SQLException {

        Connection sqlConnection = null;
        PreparedStatement statement = null;
        try {
            sqlConnection = ConnectionFactory.getInstance().getDBConnection();
            statement = sqlConnection.prepareStatement("INSERT INTO UserAccounts "
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
        }finally {
            if(statement != null) {statement.close();}
            if(sqlConnection != null) {sqlConnection.close();}
        }
    }

    public void deleteUser(Integer thisID) throws SQLException {

        Connection sqlConnection = null;
        PreparedStatement statement = null;

        if (thisID != null) {
            try {
                sqlConnection = ConnectionFactory.getInstance().getDBConnection();
                statement = sqlConnection.prepareStatement("DELETE FROM UserAccounts WHERE UserID = ?");
                statement.setLong(1, thisID);
                statement.executeUpdate();
                System.out.println("Record deleted from UserAccount table!");

            } catch (SQLException e) {
                System.out.println("Could not delete user");
                e.printStackTrace();
            }finally {
                if(statement != null) {statement.close();}
                if(sqlConnection != null) {sqlConnection.close();}
            }
        }
    }


    public UserAccount findUserById(Integer id) throws SQLException {

        UserAccount user = null;
        Connection sqlConnection = null;
        PreparedStatement statement = null;

        try {
            sqlConnection = ConnectionFactory.getInstance().getDBConnection();
            statement = sqlConnection.prepareStatement
                    ("SELECT * FROM USERACCOUNTS WHERE UserID = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            try {
                if (rs.next()) {
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
            } finally {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            if(statement != null) {statement.close();}
            if(sqlConnection != null) {sqlConnection.close();}
        }
        return user;
    }

    public Integer findUserBySignIn(String userName, String passwrd) throws SQLException {
        Integer Id = null;
        Connection sqlConnection = null;
        PreparedStatement statement = null;

        try {
            sqlConnection = ConnectionFactory.getInstance().getDBConnection();
            statement = sqlConnection.prepareStatement
                    ("SELECT UserID FROM USERACCOUNTS WHERE UserName = ? AND Password = ?");
            statement.setString(1, userName);
            statement.setString(2, passwrd);

            ResultSet rs = statement.executeQuery();
            try {
                if (rs.next()) {
                    Id = rs.getInt("USERID");
                    System.out.println("Record is found from UserAccount table!");
                } else {
                    System.out.println("Record not found from UserAccount table!");
                }
            }finally {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            if(statement != null) {statement.close();}
            if(sqlConnection != null) {sqlConnection.close();}
        }

        return Id;
    }
}
