/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: TestGroupsDOA
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Connection.Admin;
import data.Group;
import data.SQLGroupDAO;

/*
 * This is a junit test class that tests each function
 * of the SQLGroupDAO 
 */
class TestGroupsDAO {

	SQLGroupDAO gate = null; 
	Group group1 = null; 
	Group group2 = null; 
	List<Group> list = null; 

	/*
	 * Before each test this method deletes the previous group table,
	 * creates a table then saves a group with groupID and userID of 1 
	 * so table is ready to be tested by each junit test.
	 */
	@BeforeEach
	void TestSave() {
		 
		gate = new SQLGroupDAO(); 
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
		
		try {
			gate.save(group1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	/*
	 * Test the findGroupByUserID method of GroupDAO
	 */
	@Test
	void testFind() {
		List<Group> list = null; 
		try {
			list = gate.findGroupbyUserId(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		assert(list.size() == 1); 
	}

	/*
	 * Fail tests the findGroupByUserID method
	 */
	@Test
	void failTestFind() {
		List<Group> list = null; 
		try {
			list = gate.findGroupbyUserId(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		assert(list.size() == 0); 
	}
	
	/*
	 * Test the deleteGroupFromUserID method 
	 */
	@Test
	void testDeletebyUserID(){
		List<Group> list = null; 
		try {
			gate.deleteGroupFromUserID(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			list = gate.findGroupbyUserId(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		assert(list.size() == 0);
	}
	
	/*
	 * Test the deleteGroupFromGroupID method 
	 */
	@Test
	void testDeleteGrouID(){
		List<Group> list = null; 
		try {
			gate.deleteGroupFromGroupID(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			list = gate.findGroupbyUserId(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		assert(list.size() == 0);
	}
}