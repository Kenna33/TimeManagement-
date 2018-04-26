package logIn;


import data.SQLUserAccountDAO;
import data.UserAccountDAO;

import java.sql.SQLException;

public class CheckLogin {

    private static Integer ID;

    public static boolean authenticate(String username, String password) {
        // get from database and check 
        Integer id = null;
        UserAccountDAO gate = new SQLUserAccountDAO();
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
