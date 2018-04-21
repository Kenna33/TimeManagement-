package service;

import java.util.List;

import data.Group;
import data.Task;
import data.UserAccount;

public interface UserServiceInterface {
	public List<Group> getGroups();
	public ServiceResponse saveGroup(Group group);
	public ServiceResponse deleteGroup(Group group);
	public ServiceResponse updateGroup(Group group);
}
