package service;

import java.util.List;

import data.Task;

public interface UserServiceInterface {
	public List<Task> getTasks();
	public ServiceResponse saveTask(Task task);
	public ServiceResponse deleteTask(Task task);
	public ServiceResponse updateTask(Task task);
	
}
