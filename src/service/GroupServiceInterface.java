package service;

import java.util.List;

import data.Group;
import data.Task;

public interface GroupServiceInterface {
	public List<Task> getGroups();
	public ServiceResponse savegroup(Group group);
	public ServiceResponse deleteGroupk(Task group);
	public ServiceResponse updateGroup(Task group);

}
