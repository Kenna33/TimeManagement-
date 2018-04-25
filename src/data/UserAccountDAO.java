/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: UserAccountDOA
 */
package data;

import java.sql.SQLException;
import java.util.List;

/*
 * Interface to show the methods of the UserAccount Database Access Object 
 * Uses the DOA Pattern 
 */
public interface UserAccountDAO {
	public UserAccount findUserById(Integer id);
	public Integer findUserBySignIn(String userName, String password);
	//public List<UserAccount> findUserbyGroupId(int id);
	public void save(UserAccount user);
	public void deleteUser(Integer id);
	
}
