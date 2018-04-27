/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: GroupServiceInterface
 */

package service;

import java.util.List;

import data.Task;

//facade pattern 
public interface GroupServiceInterface {
    public List<Task> getTasks();

    public ServiceResponse savetask(Task task);

    public ServiceResponse deleteTask(Task task);

    public void updateTaskList();

}
