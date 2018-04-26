package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import data.Group;
import data.GroupDAO;
import data.SQLGroupDAO;
import data.SQLTaskDAO;
import data.SQLUserAccountDAO;
import data.Task;
import data.TaskDAO;
import data.UserAccount;
import data.UserAccountDAO;

public class UserService extends Observable implements UserServiceInterface {
    UserAccount user;
    private List<Group> groupList;
    private UserAccountDAO userDOA;
    private GroupDAO groupDOA;
    private TaskDAO taskDOA;


    public UserService(Integer id){
        userDOA = new SQLUserAccountDAO();
        groupDOA = new SQLGroupDAO();
        taskDOA = new SQLTaskDAO();

        try {
            user = userDOA.findUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Group> getGroups() {
        if (groupList == null) {
            updateGroupList();
        }
        return groupList;
    }

    private void updateGroupList() {
        //get users groups
        try {
            groupList = groupDOA.findGroupbyUserId(user.getUserID());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Get groups tasks
        for (Group group : groupList) {
            List<Task> groupsTasks = null;
            try {
                groupsTasks = taskDOA.findTaskbyGroupId(group.getGroupID());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (group.getTaskList() == null) {
                group.setTaskList(new ArrayList<Task>());
            }
            group.getTaskList().addAll(groupsTasks);
        }
    }


    public ServiceResponse saveGroup(Group group) {
        if (group.getName().equals("")) {
            return new ServiceResponse(false, "Cannot Save group with no name!!");
        }

        // Save the group, or update if they have id
        if (group.getGroupID() == null) {
            group.setUserID(user.getUserID());
            try {
                groupDOA.save(group);
            } catch (SQLException e) {
                e.printStackTrace();
            }


        } else {
            assert (group.getUserID() != null);
            try {
                groupDOA.updateGroup(group);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        // Update the list that service provides
        updateGroupList();

        // Let everyone know that there is a new group
        setChanged();

        Map<String, Integer> changes = new HashMap<>();
        changes.put("new", groupList.size());

        notifyObservers(changes);

        return new ServiceResponse(true, "Save successful");

    }


    public ServiceResponse deleteGroup(Group group) {

        try {
            groupDOA.deleteGroupFromGroupID(group.getGroupID());
            taskDOA.deleteTaskFromGroupID(group.getGroupID());
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
    }

}
