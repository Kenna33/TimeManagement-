/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: TaskDOA
 */
package data;

import java.util.List;

/*
 * Interface to show the methods of the Task Database Access Object 
 * Uses the DOA Pattern 
 */
public interface TaskDAO {
	public List<Task> findTaskbyGroupId(Integer id);
	public void save(Task task);
	public void updateTask(Task task);
	public void deleteTaskFromTaskID(Integer id);
	public void deleteTaskFromUserID(Integer id); 
	public void deleteTaskFromGroupID(Integer id); 
}
