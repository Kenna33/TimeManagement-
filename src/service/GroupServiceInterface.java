package service;

import java.util.List;

import data.Group;
import data.Task;

public interface GroupServiceInterface {
	public List<Task> getTasks();
	public ServiceResponse savetask(Task task);
	public ServiceResponse deleteTask(Task task);
	public void updateTaskList();

}
