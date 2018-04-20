package service;

import java.util.List;

import data.Group;
import data.Task;
import data.UserAccount;

public interface UserServiceInterface {
	public List<Group> getGroups();
	public ServiceResponse savegroup(Group group);
	public ServiceResponse deleteGroupk(Group group);
	public ServiceResponse updateGroup(Group group);
}
