package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import data.Group;
import data.SQLGroupDOA;
import data.SQLTaskDOA;
import data.SQLUserAccountDOA;
import data.Task;
import data.TaskDOA;

public class GroupService extends Observable implements GroupServiceInterface{
	private Group group; 
	List<Task> taskList; 
	private TaskDOA taskDOA;  

	public GroupService(Group g) {
		taskDOA = new SQLTaskDOA(); 
		
		group = g;
	}

	@Override
	public List<Task> getTasks() {
		if (taskList == null) {
			updateTaskList();
		}
		return taskList;
	}

	@Override
	public ServiceResponse savetask(Task task) {
		boolean changed = false; 
		if (task.getName().equals("")) {
			return new ServiceResponse(false, "Cannot Save task with no name!!");
		}

		// Save the task, or update if they have id
		if (task.getTaskID() == null) {
			task.setUserID(group.getUserID());
			task.setGroupID(group.getGroupID());
			taskDOA.save(task); 
		}else {	
			assert(group.getUserID() != null);
			assert(group.getGroupID() != null); 
			taskDOA.updateTask(task);
			changed = true; 
		}
	

		// Update the list that service provides
		updateTaskList(); 

		// Let everyone know that there is a new group
		setChanged();

		/*
		Map<String, Integer> changes = new HashMap<>();
		changes.put("new", taskList.size());
		*/
		
		/*
		if(changed) {
			group.setTaskList(taskList);
		}else {
			group.getTaskList().add(task); 
		}
		*/ 
		List<Task> taskList; 
		taskList = taskDOA.findTaskbyGroupId(group.getGroupID()); 
		group.setTaskList(taskList);
		
		notifyObservers(group);
		
		return new ServiceResponse(true, "Save successful");
	}

	@Override
	public ServiceResponse deleteTask(Task task) {
		// Delete the group
		try {
			taskDOA.deleteTaskFromTaskID(task.getTaskID());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Find where the farmer was in the list
		//int positionRemoved = taskList.indexOf(task);

		// Update the list that service provides
		updateTaskList();

		// Let everyone know that there is a new farmer
		setChanged();

		/*
		Map<String, Integer> changes = new HashMap<>();
		changes.put("remove", positionRemoved);
		*/
		//group.getTaskList().remove(task); 
		
		List<Task> taskList; 
		taskList = taskDOA.findTaskbyGroupId(group.getGroupID()); 
		group.setTaskList(taskList);
		
		notifyObservers(group);

		// Return success message
		return new ServiceResponse(true, "Deletion Successful");
			
	}

	@Override
	public void updateTaskList() {
		 taskList = taskDOA.findTaskbyGroupId(group.getUserID());
		 
		 if(group.getTaskList() == null) {
				group.setTaskList(new ArrayList<Task>());
			}
			group.getTaskList().addAll(taskList);
	}

	
}
