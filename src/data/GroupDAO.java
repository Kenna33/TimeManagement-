/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: GroupDOA
 */
package data;

import java.util.List;

/*
 * Interface to show the methods of the Group Database Access Object 
 * Uses the DOA Pattern 
 */
public interface GroupDAO {
	
	public List<Group> findGroupbyUserId(Integer id);
	public void save(Group group);
	public void deleteGroupFromUserID(Integer id);
	public void deleteGroupFromGroupID(Integer id); 
	public void updateGroup(Group group); 
}
