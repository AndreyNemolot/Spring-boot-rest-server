package com.concretepage.dao;
import java.util.List;
import com.concretepage.entity.UserInfo;

public interface IUserDAO {
    List<UserInfo> getAllUsers(String login);
    UserInfo getUserById(int userId);
    void addUser(UserInfo userInfo);
    void updateUser(UserInfo userInfo);
    void deleteUser(int userId);
    boolean userExists(String login, String password);
    UserInfo getActiveUser(String userName);

}
 