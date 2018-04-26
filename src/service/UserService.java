/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: UserService
 */

package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import data.*;

public class UserService extends Observable implements UserServiceInterface {
    UserAccount user;
    private List<Group> groupList;
    private FactoryDAO factoryDAO;


    public UserService(Integer id){
        factoryDAO = new FactoryDAO();

        try {
            user = factoryDAO.findUserById(id);
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
            groupList = factoryDAO.findGroupbyUserId(user.getUserID());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Get groups tasks
        for (Group group : groupList) {
            List<Task> groupsTasks = null;
            try {
                groupsTasks = factoryDAO.findTaskbyGroupId(group.getGroupID());
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
                factoryDAO.save(group);
            } catch (SQLException e) {
                e.printStackTrace();
            }


        } else {
            assert (group.getUserID() != null);
            try {
                factoryDAO.update(group);
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
            factoryDAO.deleteGroupFromGroupID(group.getGroupID());
            factoryDAO.deleteTaskFromGroupID(group.getGroupID());
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
