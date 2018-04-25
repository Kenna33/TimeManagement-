/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: UserAccountDOA
 */
package data;


/*
 * Interface to show the methods of the UserAccount Database Access Object 
 * Uses the DOA Pattern 
 */
public interface UserAccountDAO {
	public UserAccount findUserById(Integer id);
	public Integer findUserBySignIn(String userName, String password);
	public void save(UserAccount user);
	public void deleteUser(Integer id);
	
}
