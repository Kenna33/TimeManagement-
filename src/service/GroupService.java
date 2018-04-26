/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: GroupService
 */
package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import data.*;

public class GroupService extends Observable implements GroupServiceInterface {
    private Group group;
    List<Task> taskList;
    private FactoryDAO factoryDAO;

    public GroupService(Group g) {
        factoryDAO = new FactoryDAO();
        group = g;
    }

    @Override
    public List<Task> getTasks() {
        if (taskList == null) {
            updateTaskList();
        }
        return taskList;
    }

    @Override
    public ServiceResponse savetask(Task task) {
        boolean changed = false;
        if (task.getName().equals("")) {
            return new ServiceResponse(false, "Cannot Save task with no name!!");
        }

        // Save the task, or update if they have id
        if (task.getTaskID() == null) {
            task.setUserID(group.getUserID());
            task.setGroupID(group.getGroupID());
            try {
                factoryDAO.save(task);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            assert (group.getUserID() != null);
            assert (group.getGroupID() != null);
            try {
                factoryDAO.update(task);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            changed = true;
        }


        // Update the list that service provides
        updateTaskList();

        // Let everyone know that there is a new group
        setChanged();

        List<Task> taskList;
        try {
            taskList = factoryDAO.findTaskbyGroupId(group.getGroupID());
            group.setTaskList(taskList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        notifyObservers(group);

        return new ServiceResponse(true, "Save successful");
    }

    @Override
    public ServiceResponse deleteTask(Task task) {

        try {
            factoryDAO.deleteTaskFromTaskID(task.getTaskID());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Update the list that service provides
        updateTaskList();

        // Let everyone know that there is a new farmer
        setChanged();


        List<Task> taskList;
        try {
            taskList = factoryDAO.findTaskbyGroupId(group.getGroupID());
            group.setTaskList(taskList);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        notifyObservers(group);

        // Return success message
        return new ServiceResponse(true, "Deletion Successful");

    }

    @Override
    public void updateTaskList() {
        try {
            taskList = factoryDAO.findTaskbyGroupId(group.getUserID());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (group.getTaskList() == null) {
            group.setTaskList(new ArrayList<Task>());
        }
        group.getTaskList().addAll(taskList);
    }


}
