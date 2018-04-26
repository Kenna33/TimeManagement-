/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: UserServiceInterface
 */

package service;

import java.util.List;

import data.Group;

//facade pattern 
public interface UserServiceInterface {
    public List<Group> getGroups();

    public ServiceResponse saveGroup(Group group);

    public ServiceResponse deleteGroup(Group group);

}
