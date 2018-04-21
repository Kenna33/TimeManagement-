package service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.Group;
import data.GroupDOA;
import data.SQLGroupDOA;
import data.SQLUserAccountDOA;
import data.Task;
import data.TaskDOA;
import data.UserAccount;
import data.UserAccountDOA;

public class UserService implements UserServiceInterface{
	UserAccount user; 
	private List<Group> groupList;  
	private UserAccountDOA userDOA; 
	private GroupDOA groupDOA; 
	private TaskDOA taskDOA; 
	

	public UserService(Integer id) {
		userDOA = new SQLUserAccountDOA(); 
		groupDOA = new SQLGroupDOA(); 
		
		try {
			user = userDOA.findUserById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public List<Group> getGroups() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ServiceResponse saveGroup(Group group) {
		if (group.getName().equals("")) {
			return new ServiceResponse(false, "Cannot Save group with no name!!");
		}

		// Save the farmer, or update if they have id
		if (group.getGroupID() == null ? groupDOA.save(group) : groupDOA.updateGroup(group)) {

			/*
			// Save any cows that the farmer may now have
			for (Task task : group.getTaskList()) {
				taskDAO.s
			}
			*/

			// Update the list that service provides
			//updateFarmerList();

			// Let everyone know that there is a new farmer
			//setChanged();

			//Map<String, Integer> changes = new HashMap<>();
			//changes.put("new", farmers.size());

			//notifyObservers(changes);

			return new ServiceResponse(true, "Save successful");
		}
		return new ServiceResponse(false, "Save Failed");
	}


	@Override
	public ServiceResponse deleteGroup(Group group) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ServiceResponse updateGroup(Group group) {
		// TODO Auto-generated method stub
		return null;
	}



}
