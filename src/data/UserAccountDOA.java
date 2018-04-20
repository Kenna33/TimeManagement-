package data;

import java.sql.SQLException;
import java.util.List;

public interface UserAccountDOA {
	public UserAccount findUserById(Integer id) throws SQLException;
	public Integer findUserBySignIn(String userName, String password) throws SQLException;
	public List<UserAccount> findUserbyGroupId(int id);
	public void save(UserAccount user) throws SQLException;
	public void deleteUser(Integer id) throws SQLException;
}
