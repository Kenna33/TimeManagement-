package service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Logger;

import data.Group;
import data.GroupDOA;
import data.SQLGroupDOA;
import data.SQLUserAccountDOA;
import data.Task;
import data.TaskDOA;
import data.UserAccount;
import data.UserAccountDOA;

public class UserService extends Observable implements UserServiceInterface{
	private final static Logger LOGGER = Logger.getLogger(UserService.class.getName());
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
			LOGGER.severe("Exception: "+e.getMessage());
		}
	}


	@Override
	public List<Group> getGroups() {
		if (groupList == null) {
			updateGroupList();
		}
		return groupList;
	}
	
	private void updateGroupList() {
		//get users groups 
		groupList = groupDOA.findGroupbyUserId(user.getUserID());

		// Get groups tasks
		/*
		for (Group group : groupList) {
			List<Task> groupsTasks = taskDOA.findTaskbyGroupId(group.getGroupID());
			group.getTaskList().addAll(groupsTasks);
		}
		*/
	}


	@Override
	public ServiceResponse saveGroup(Group group) {
		if (group.getName().equals("")) {
			return new ServiceResponse(false, "Cannot Save group with no name!!");
		}

		// Save the farmer, or update if they have id
			if (group.getGroupID() == null) {
				group.setUserID(user.getUserID());
				groupDOA.save(group); 
				
	
			}else {	
				assert(group.getUserID() != null);
				groupDOA.updateGroup(group);
				
			}
			
	
				/*
				// Save any cows that the farmer may now have
				for (Task task : group.getTaskList()) {
					taskDAO.s
				}
				*/
	
				// Update the list that service provides
				updateGroupList(); 
	
				// Let everyone know that there is a new group
				setChanged();
	
				Map<String, Integer> changes = new HashMap<>();
				changes.put("new", groupList.size());
	
				notifyObservers(changes);
				
				return new ServiceResponse(true, "Save successful");
		
	}


	@Override
	public ServiceResponse deleteGroup(Group group) {

		// Delete the farmer
		try {
			groupDOA.deleteGroupFromGroupID(group.getGroupID());
			

		} catch (SQLException e) {
			e.printStackTrace();
		}

			// Find where the farmer was in the list
			int positionRemoved = groupList.indexOf(group);

			// Update the list that service provides
			updateGroupList();

			// Let everyone know that there is a new farmer
			setChanged();

			Map<String, Integer> changes = new HashMap<>();
			changes.put("remove", positionRemoved);

			notifyObservers(changes);

			// Return success message
			return new ServiceResponse(true, "Deletion Successful");
			
		//return new ServiceResponse(false, "Deletion Failed");
	}

}
