package service;

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
		if (task.getName().equals("")) {
			return new ServiceResponse(false, "Cannot Save task with no name!!");
		}

		// Save the task, or update if they have id
			if (task.getTaskID() == null) {
				task.setGroupID(group.getGroupID());
				taskDOA.save(task); 
			}else {	
				assert(group.getUserID() != null);
				assert(group.getGroupID() != null); 
				taskDOA.updateTask(task);
			}
			
	
				// Update the list that service provides
				updateTaskList(); 
	
				// Let everyone know that there is a new group
				setChanged();
	
				Map<String, Integer> changes = new HashMap<>();
				changes.put("new", taskList.size());
	
				notifyObservers(changes);
				
				return new ServiceResponse(true, "Save successful");
	}

	@Override
	public ServiceResponse deleteTask(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTaskList() {
		 taskList = taskDOA.findTaskbyGroupId(group.getUserID());
	}

	
}
