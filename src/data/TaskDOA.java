package data;

import java.sql.SQLException;
import java.util.List;

public interface TaskDOA {
	public List<Task> findTaskbyGroupId(Integer id);
	public void save(Task task);
	public void updateTask(Task task);
	public void deleteTaskFromTaskID(Integer id) throws SQLException;
	public void deleteTaskFromUserID(Integer id) throws SQLException; 
	public void deleteTaskFromGroupID(Integer id) throws SQLException; 
}
