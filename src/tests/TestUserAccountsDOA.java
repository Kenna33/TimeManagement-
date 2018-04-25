/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: TestUserAccountsDOA
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import Connection.Admin;
import data.SQLUserAccountDAO;
import data.UserAccount;


/*
 * This is a junit test class that tests each function
 * of the SQLUserAccountDAO 
 */
class TestUserAccountsDOA {
	SQLUserAccountDAO gate = null; 
	UserAccount user = null; 
	UserAccount user2 = null; 

	
	@BeforeEach
	void TestSave() {
		
		gate = new SQLUserAccountDAO(); 
		user = new UserAccount();  
		user2 = new UserAccount();  
		
		user.setUserName("Kenna33");
		user.setPhoneNum("555-971-1155");
		user.setEmail("McKenna_Woodrow@baylor.edu");
		user.setPassword("Kenna123");
 
		try {
			Admin.deleteUserAccountsTable();
		} catch (SQLException e1) {
			e1.printStackTrace();
			fail("SQLExceptionThrown");
		}	
		
		try {
			Admin.createUserAccountTable();
		} catch (SQLException e1) {
			e1.printStackTrace();
			fail("SQLExceptionThrown");
		}
		
		gate.save(user);	
		
		user2 = gate.findUserById(1);
		user.setUserID(1);
		assert(user.equals(user2)); 
	}

	
	@Test
	void testDeletewithID() throws SQLException{
		UserAccount user; 
		gate.deleteUser(1);
		user = gate.findUserById(1); 
		assert(user == null); 
	}
	
	@Test
	void testFindwithPassword() throws SQLException {
		Integer id;  
		id = gate.findUserBySignIn("Kenna33", "Kenna123"); 
		assert(id == 1);
	}
	
	@Test
	void failtestFindwithPassword() throws SQLException {
		Integer id;  
		
		id = gate.findUserBySignIn("Kenna3", "Kenna13"); 
		assert(id == null);
	}

}


