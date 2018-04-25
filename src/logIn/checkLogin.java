package logIn;

import java.sql.SQLException;

import data.SQLUserAccountDAO;
import data.UserAccountDAO;

public class checkLogin {
	
	private static Integer ID; 

	public static boolean authenticate(String username, String password){
        // get from database and check 
		Integer id = null; 
		UserAccountDAO gate = new SQLUserAccountDAO(); 
		id = gate.findUserBySignIn(username, password);
		
        if (id == null) {
            return false;
        }else {
        	//set id for accessing specific user info
        	ID = id; 
        	return true;
        }
    }

	public static Integer getID() {
		return ID;
	}

}
