package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Connection.Admin;
import data.Group;
import data.SQLGroupDOA;
import data.SQLUserAccountDOA;
import data.UserAccount;

class TestGroupsDOA {

	SQLGroupDOA gate = null; 
	Group group1 = null; 
	Group group2 = null; 
	List<Group> list = null; 

	
	@BeforeEach
	void TestSave() {
		
		//CreateUserAccountsTable create_delete = new CreateUserAccountsTable(); 
		gate = new SQLGroupDOA(); 
		group1 = new Group(); 
		group2 = new Group(); 
		list = new ArrayList<Group>(); 
		
		group1.setName("group1");
		group1.setUserID(1);
		
		 
 
		try {
			Admin.deleteGroupsTable();
		} catch (SQLException e1) {
			e1.printStackTrace();
			fail("SQLExceptionThrown");
		}	
		
		try {
			Admin.createGroupsTable();
		} catch (SQLException e1) {
			e1.printStackTrace();
			fail("SQLExceptionThrown");
		}
		
		gate.save(group1);	
		
		
		try {
			list = gate.findGroupbyUserId(1);
		} catch (SQLException e) {
			fail("Data with ID 1 not found"); 
		}
		 
	}

	
	
	
	
	@Test
	void testDelete() throws SQLException{
		
		gate.deleteGroupFromUserID(1);

		assertThrows(RuntimeException.class, ()-> {gate.findGroupbyUserId(1);}); 
	}

}
