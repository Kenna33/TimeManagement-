/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner
 * File Title: Group
 */

package data;

import java.util.List;

/*
 * Group data object used to representing attributes
 * inside database and multiplicity relationship with tasks
 * and a user
 *
 * Each group can only belong to one user
 * Each group can have multiple tasks
 */
public class Group {

    private String name;
    private Integer groupID;
    private Integer userID;
    private List<Task> taskList;

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Group name=" + name + ", id=" + groupID + "]";
    }

}
