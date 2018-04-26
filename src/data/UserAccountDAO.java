/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner
 * File Title: UserAccountDOA
 */
package data;


import java.sql.SQLException;

/*
 * Interface to show the methods of the UserAccount Database Access Object
 * Uses the DOA Pattern
 */
public interface UserAccountDAO {
    public UserAccount findUserById(Integer id) throws SQLException;

    public Integer findUserBySignIn(String userName, String password) throws SQLException;

    public void save(UserAccount user) throws SQLException;

    public void deleteUser(Integer id) throws SQLException;

}
