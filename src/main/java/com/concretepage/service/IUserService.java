package com.concretepage.service;

import java.util.List;

import com.concretepage.entity.UserInfo;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {
     @Secured({"ROLE_ADMIN", "ROLE_USER"})
     List<UserInfo> getAllUsers(String login);
     @Secured({"ROLE_ADMIN", "ROLE_USER"})
     UserInfo getUserById(int userId);
     @Secured({"ROLE_ADMIN"})
     boolean addUser(UserInfo userInfo);
     @Secured({"ROLE_ADMIN", "ROLE_USER"})
     void updateUser(UserInfo userInfo);
     @Secured({"ROLE_ADMIN", "ROLE_USER"})
     void deleteUser(int userId);


}
