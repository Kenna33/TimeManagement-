/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: TestTasksDOA
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Connection.Admin;
import data.SQLTaskDAO;
import data.Task;

/*
 * This is a junit test class that tests each function
 * of the SQLTaskDAO 
 */
class TestTasksDAO {

	SQLTaskDAO gate = null; 
	Task task1 = null; 
	Task task2 = null; 
	List<Task> list = null; 
	
	/*
	 * Before each test this method deletes the previous task table,
	 * creates a table then saves a task with taskID and userID of 1 
	 * so table is ready to be tested by each junit test.
	 */
	@BeforeEach
	void TestSave() {
		
		gate = new SQLTaskDAO(); 
		task1 = new Task(); 
		task2 = new Task(); 
		list = new ArrayList<Task>(); 
		
		task1.setName("task1");
		task1.setUserID(1);
		task1.setProgress(1);
		task1.setDescription("This is my third task");
		
		@SuppressWarnings("deprecation")
		Date myDate = new Date(0, 0, 0); 
		task1.setDueDate(myDate);
		task1.setGroupID(1);
		task1.setPriority(1);
 
		
		try {
			Admin.deleteTasksTable();
		} catch (SQLException e1) {
			e1.printStackTrace();
			fail("SQLExceptionThrown");
		}	
		
		
		try {
			Admin.createTasksTable();
		} catch (SQLException e1) {
			e1.printStackTrace();
			fail("SQLExceptionThrown");
		}
		
		gate.save(task1);	
	}
	
	/*
	 * Test the findTaskByGroupID method of TaskDAO
	 */
	@Test
	void testFind() {
		List<Task> list; 
		list = gate.findTaskbyGroupId(1); 
		assert(list.size() == 1); 
	}
	
	/*
	 * Fail tests the findTaskByGroupID method
	 */
	@Test
	void failTestFind() {
		List<Task> list; 
		list = gate.findTaskbyGroupId(3); 
		assert(list.size() == 0); 
	}
	
	/*
	 * Test the deleteTaskFromTaskID method 
	 */
	@Test
	void testDeletebyTaskID(){
		List<Task> list = null; 
		gate.deleteTaskFromTaskID(1);
		
		list = gate.findTaskbyGroupId(1); 
		assert(list.size() == 0);
	}
	
	/*
	 * Test the deleteTaskFromGroupID method 
	 */
	@Test
	void testDeletebyGroupID(){
		List<Task> list = null; 
		gate.deleteTaskFromGroupID(1);
		
		list = gate.findTaskbyGroupId(1); 
		assert(list.size() == 0);
	}
	
	/*
	 * Test the deleteTaskFromUserID method 
	 */
	@Test
	void testDeletebyUserID(){
		List<Task> list = null; 
		gate.deleteTaskFromUserID(1);
		
		list = gate.findTaskbyGroupId(1); 
		assert(list.size() == 0);
	}
	
}

