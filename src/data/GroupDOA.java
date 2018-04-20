package data;

import java.sql.SQLException;
import java.util.List;

public interface GroupDOA {
	public List<Group> findGroupbyUserId(Integer id) throws SQLException;
	public void save(Group group) throws SQLException;
	public void deleteGroupFromUserID(Integer id) throws SQLException;
	public void deleteGroupFromGroupID(Integer id) throws SQLException; 
	
}
