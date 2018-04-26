/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner
 * File Title: UserAccount
 */

package data;

import java.util.List;

/*
 * User Account data object used to representing attributes
 * inside database and multiplicity relationship with groups
 *
 * Each user can have multiple groups with multiple tasks within
 * them
 */
public class UserAccount {

    private Integer UserID;
    private String UserName;
    private String Email;
    private String PhoneNum;
    private String Password;
    private List<Group> groupList;

    public UserAccount() {
        UserID = null;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Email == null) ? 0 : Email.hashCode());
        result = prime * result + ((Password == null) ? 0 : Password.hashCode());
        result = prime * result + ((PhoneNum == null) ? 0 : PhoneNum.hashCode());
        result = prime * result + ((UserID == null) ? 0 : UserID.hashCode());
        result = prime * result + ((UserName == null) ? 0 : UserName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserAccount other = (UserAccount) obj;
        if (Email == null) {
            if (other.Email != null)
                return false;
        } else if (!Email.equals(other.Email))
            return false;
        if (Password == null) {
            if (other.Password != null)
                return false;
        } else if (!Password.equals(other.Password))
            return false;
        if (PhoneNum == null) {
            if (other.PhoneNum != null)
                return false;
        } else if (!PhoneNum.equals(other.PhoneNum))
            return false;
        if (UserID == null) {
            if (other.UserID != null)
                return false;
        } else if (!UserID.equals(other.UserID))
            return false;
        if (UserName == null) {
            if (other.UserName != null)
                return false;
        } else if (!UserName.equals(other.UserName))
            return false;
        return true;
    }


}
