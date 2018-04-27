/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: GroupListModel
 */
package model;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;

import data.Group;
import service.UserServiceInterface;

/*
 * This class sets the framework for displaying and updating the different
 * groups, which is implemented in the HomePage 
 */
public class GroupListModel extends AbstractListModel<Group> implements Observer {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private UserServiceInterface userService;

    public GroupListModel(UserServiceInterface usi) {
        userService = usi;
    }

    @Override
    public int getSize() {
        return userService.getGroups().size();
    }

    @Override
    public Group getElementAt(int index) {
        return userService.getGroups().get(index);
    }

    @Override
    public void update(Observable o, Object arg) {
        @SuppressWarnings("unchecked")
		Map<String, Integer> changes = (Map<String, Integer>) arg;
        if (changes.containsKey("new")) {
            fireIntervalAdded(this, changes.get("new"), changes.get("new"));
        }
        if (changes.containsKey("remove")) {
            fireIntervalRemoved(this, changes.get("remove"), changes.get("remove"));
        }
    }

}
