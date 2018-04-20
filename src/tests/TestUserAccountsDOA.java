package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import data.Admin;
import data.SQLUserAccountDOA;
import data.UserAccount;


class TestUserAccountsDOA {
	SQLUserAccountDOA gate = null; 
	UserAccount user = null; 
	UserAccount user2 = null; 
	List<UserAccount> list = null; 

	
	@BeforeEach
	void TestSave() {
		
		//CreateUserAccountsTable create_delete = new CreateUserAccountsTable(); 
		gate = new SQLUserAccountDOA(); 
		user = new UserAccount();  
		user2 = new UserAccount();  
		list = new ArrayList<UserAccount>(); 
		
		user.setUserName("Kenna33");
		user.setPhoneNum("555-971-1155");
		user.setEmail("McKenna_Woodrow@baylor.edu");
		user.setPassword("Kenna123");
		
		list.add(user); 
 
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
		
		try {
			gate.save(user);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("SQLExceptionThrown");
		}	
		
		
		try {
			user2 = gate.findUser("SELECT * FROM USERACCOUNTS WHERE USERID = 1");
		} catch (SQLException e) {
			fail("Data with ID 1 not found"); 
		}
		
		user.setUserID(1);
		assert(user.equals(user2)); 
	}

	
	
	
	
	@Test
	void testDelete() throws SQLException{
		
		gate.deleteUser(1);

		assertThrows(RuntimeException.class, ()-> {gate.findUser("SELECT * FROM USERACCOUNTS WHERE USERID = 1");}); 
	}
	
		

}


