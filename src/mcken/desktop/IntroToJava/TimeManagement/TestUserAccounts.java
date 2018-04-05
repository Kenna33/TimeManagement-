package mcken.desktop.IntroToJava.TimeManagement;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TestUserAccounts {
	UserAccountGateway gate = null; 
	UserAccount user = null; 
	UserAccount user2 = null; 
	List<UserAccount> list = null; 

	
	@BeforeEach
	void TestSave() {
		
		//CreateUserAccountsTable create_delete = new CreateUserAccountsTable(); 
		gate = new UserAccountGateway(); 
		user = new UserAccount();  
		user2 = new UserAccount();  
		list = new ArrayList<UserAccount>(); 
		
		user.setAdmin(true);
		user.setUserName("Kenna33");
		user.setPhoneNum("555-971-1155");
		user.setEmail("McKenna_Woodrow@baylor.edu");
		user.setPassword("Kenna123");
		
		list.add(user); 
 
		try {
			CreateUserAccountsTable.deleteTable();
		} catch (SQLException e1) {
			e1.printStackTrace();
			fail("SQLExceptionThrown");
		}	
		
		try {
			CreateUserAccountsTable.createUserAccountTable();
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
			user2 = gate.findUser("SELECT * FROM USERACCOUNTS WHERE USERID = 100");
		} catch (SQLException e) {
			fail("Data with ID 100 not found"); 
		}
		
		user.setUserID(100);
		assert(user.equals(user2)); 
	}
	
	
	@Test
	void testDelete() throws SQLException{
		
		gate.delete(100);

		assertThrows(RuntimeException.class, ()-> {gate.findUser("SELECT * FROM USERACCOUNTS WHERE USERID = 100");}); 
	}
	
		

}


