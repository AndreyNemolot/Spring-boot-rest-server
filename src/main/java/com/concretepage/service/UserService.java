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
import org.springframework.stereotype.Service;

import com.concretepage.dao.IUserDAO;

@Service
public class UserService implements IUserService, UserDetailsService {

	@Autowired
	private IUserDAO userDAO;

	@Override
	public UserInfo getUserById(int userId) {
		UserInfo obj = userDAO.getUserById(userId);
		return obj;
	}

	@Override
	public List<UserInfo> getAllUsers(){
		return userDAO.getAllUsers();
	}

	@Override
	public synchronized boolean addUser(UserInfo userInfo){
       if (userDAO.userExists(userInfo.getLogin(), userInfo.getPassword())) {
    	   return false;
       } else {
    	   userDAO.addUser(userInfo);
    	   return true;
       }
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
