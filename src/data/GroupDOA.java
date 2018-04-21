package data;

import java.sql.SQLException;
import java.util.List;

public interface GroupDOA {
	public List<Group> findGroupbyUserId(Integer id) throws SQLException;
	public boolean save(Group group);
	public void deleteGroupFromUserID(Integer id) throws SQLException;
	public void deleteGroupFromGroupID(Integer id) throws SQLException; 
	public boolean updateGroup(Group group); 
}
