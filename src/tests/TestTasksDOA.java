package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Connection.Admin;
import data.SQLTaskDOA;
import data.SQLUserAccountDOA;
import data.Task;
import data.UserAccount;

class TestTasksDOA {

	SQLTaskDOA gate = null; 
	Task task1 = null; 
	Task task2 = null; 
	List<Task> list = null; 
	
	@BeforeEach
	void TestSave() {
		
		//CreateUserAccountsTable create_delete = new CreateUserAccountsTable(); 
		gate = new SQLTaskDOA(); 
		task1 = new Task(); 
		task2 = new Task(); 
		list = new ArrayList<Task>(); 
		
		task1.setName("task1");
		task1.setUserID(1);
		task1.setProgress(1);
		task1.setDescription("This is my third task");
		
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
		
		
		
		list = gate.findTaskbyGroupId(1);
		
		
		//assert(list.contains(task1)); 
	}
	
	
	@Test
	void testDelete() throws SQLException{
		
		gate.deleteTaskFromUserID(1);

		assertThrows(RuntimeException.class, ()-> {gate.findTaskbyGroupId(1);}); 
	}
	
	
	
}

