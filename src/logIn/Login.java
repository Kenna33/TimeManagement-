package logIn;

import java.sql.SQLException;

import data.SQLUserAccountDOA;
import data.UserAccountDOA;

public class Login {
	
	private static Integer ID; 

	public static boolean authenticate(String username, String password){
        // get from database and check 
		Integer id = null; 
		UserAccountDOA gate = new SQLUserAccountDOA(); 
		try {
			id = gate.findUserBySignIn(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
