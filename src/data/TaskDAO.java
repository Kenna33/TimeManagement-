/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner
 * File Title: TaskDOA
 */
package data;

import java.sql.SQLException;
import java.util.List;

/*
 * Interface to show the methods of the Task Database Access Object
 * Uses the DOA Pattern
 */
public interface TaskDAO {
    public List<Task> findTaskbyGroupId(Integer id) throws SQLException;

    public void save(Task task) throws SQLException;

    public void updateTask(Task task) throws SQLException;

    public void deleteTaskFromTaskID(Integer id) throws SQLException;

    public void deleteTaskFromUserID(Integer id) throws SQLException;

    public void deleteTaskFromGroupID(Integer id) throws SQLException;
}
