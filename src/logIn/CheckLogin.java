/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: CheckLogin
 */
package logIn;


import data.FactoryDAO;

import java.sql.SQLException;

/*
 * This class has on method authenticate which checks to 
 * see if the given username and email is in the database 
 * through the DAOFactory
 */
public class CheckLogin {

    private static Integer ID;

    public static boolean authenticate(String username, String password) {
        // get from database and check 
        Integer id = null;
        FactoryDAO gate = new FactoryDAO();
        try {
            id = gate.findUserBySignIn(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (id == null) {
            return false;
        } else {
            //set id for accessing specific user info
            ID = id;
            return true;
        }
    }

    public static Integer getID() {
        return ID;
    }

}
