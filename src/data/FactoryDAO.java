package data;

import java.sql.SQLException;
import java.util.List;

public class FactoryDAO {
    private GroupDAO groupDAO;
    private TaskDAO taskDAO;
    private UserAccountDAO userAccountDAO;

    public FactoryDAO(){
        groupDAO = new SQLGroupDAO();
        taskDAO = new SQLTaskDAO();
        userAccountDAO = new SQLUserAccountDAO();
    }

    public UserAccount findUserById(Integer id) throws SQLException{
        return userAccountDAO.findUserById(id);
    }

    public Integer findUserBySignIn(String userName, String password) throws SQLException{
        return userAccountDAO.findUserBySignIn(userName, password);
    }

    public void save(UserAccount user) throws SQLException{
        userAccountDAO.save(user);
    }

    public void deleteUser(Integer id) throws SQLException{
        userAccountDAO.deleteUser(id);
    }

    public List<Task> findTaskbyGroupId(Integer id) throws SQLException{
        return taskDAO.findTaskbyGroupId(id);
    }

    public void save(Task task) throws SQLException{
        taskDAO.save(task);
    }

    public void update(Task task) throws SQLException{
        taskDAO.updateTask(task);
    }

    public void deleteTaskFromTaskID(Integer id) throws SQLException{
        taskDAO.deleteTaskFromTaskID(id);
    }

    public void deleteTaskFromUserID(Integer id) throws SQLException{
        taskDAO.deleteTaskFromUserID(id);
    }

    public void deleteTaskFromGroupID(Integer id) throws SQLException{
        taskDAO.deleteTaskFromGroupID(id);
    }

    public List<Group> findGroupbyUserId(Integer id) throws SQLException{
        return groupDAO.findGroupbyUserId(id);
    }

    public void save(Group group) throws SQLException{
        groupDAO.save(group);
    }

    public void deleteGroupFromGroupID(Integer id) throws SQLException{
        groupDAO.deleteGroupFromGroupID(id);
    }

    public void update(Group group) throws SQLException{
        groupDAO.updateGroup(group);
    }

}
