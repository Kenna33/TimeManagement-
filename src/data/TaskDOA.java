package data;

import java.sql.SQLException;
import java.util.List;

public interface TaskDOA {
	public List<Task> findTaskbyUserId(Integer id);
	public void save(Task task) throws SQLException;
	public void deleteTask(Integer id) throws SQLException;
}
