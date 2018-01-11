package com.concretepage.service;

import java.util.Arrays;
import java.util.List;

import com.concretepage.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IUserDAO;

@Service
public class UserService implements IUserService, UserDetailsService {

    private final static int workload = 4;
    @Autowired
    private IUserDAO userDAO;

    @Override
    public UserInfo getUserById(int userId) {
        UserInfo obj = userDAO.getUserById(userId);
        return obj;
    }


    @Override
    public List<UserInfo> getAllUsers(String login) {
        return userDAO.getAllUsers(login);
    }

    @Override
    public synchronized boolean addUser(UserInfo userInfo) {
        if (userDAO.userExists(userInfo.getLogin(), userInfo.getPassword())) {
            return false;
        } else {
            String password = userInfo.getPassword();
            userInfo.setPassword(passwordEncode(password));
            userDAO.addUser(userInfo);
            return true;
        }
    }

    private static String passwordEncode(String password) {
        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(password, salt);
    }

    @Override
    public void updateUser(UserInfo userInfo) {
        userDAO.updateUser(userInfo);
    }

    @Override
    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        UserInfo activeUserInfoInfo = userDAO.getActiveUser(userName);
        GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfoInfo.getRole());
        UserDetails userDetails = new User(activeUserInfoInfo.getLogin(),
                activeUserInfoInfo.getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}
