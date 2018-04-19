package data;

import java.sql.SQLException;
import java.util.List;

public interface TaskDOA {
	public List<Task> findTaskbyUserId(Integer id) throws SQLException;
	public void save(Task task) throws SQLException;
	public void deleteTaskFromTaskID(Integer id) throws SQLException;
	public void deleteTaskFromUserID(Integer id) throws SQLException; 
}
