/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner
 * File Title: GroupDOA
 */
package data;

import java.sql.SQLException;
import java.util.List;

/*
 * Interface to show the methods of the Group Database Access Object
 * Uses the DOA Pattern
 */
public interface GroupDAO {

    public List<Group> findGroupbyUserId(Integer id) throws SQLException;

    public void save(Group group) throws SQLException;

    public void deleteGroupFromUserID(Integer id) throws SQLException;

    public void deleteGroupFromGroupID(Integer id) throws SQLException;

    public void updateGroup(Group group) throws SQLException;
}
