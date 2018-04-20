package service;

import java.sql.SQLException;
import java.util.List;

import data.Group;
import data.GroupDOA;
import data.SQLGroupDOA;
import data.SQLUserAccountDOA;
import data.UserAccount;
import data.UserAccountDOA;

public class UserService implements UserServiceInterface{
	UserAccount user; 
	private UserAccountDOA userDOA; 
	private GroupDOA groupDOA; 
	

	public UserService() {
		userDOA = new SQLUserAccountDOA(); 
		groupDOA = new SQLGroupDOA(); 
		
	}


	@Override
	public List<Group> getGroups() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ServiceResponse savegroup(Group group) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ServiceResponse deleteGroupk(Group group) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ServiceResponse updateGroup(Group group) {
		// TODO Auto-generated method stub
		return null;
	}



}
