package com.concretepage.controller;
import java.util.List;

import com.concretepage.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.concretepage.service.IUserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value="user", method= RequestMethod.GET)
		public ResponseEntity<UserInfo> getUserById(
			@RequestParam(value = "user_id") Integer id) {
		UserInfo userInfo = userService.getUserById(id);
		return new ResponseEntity<>(userInfo, HttpStatus.OK);
	}


	@GetMapping("users")
	public ResponseEntity<List<UserInfo>> getAllUsers(
			@RequestParam(value = "login") String login) {
		List<UserInfo> list = userService.getAllUsers(login);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping("user")
	public ResponseEntity<Void> addUser(UserInfo userInfo) {
        boolean flag = userService.addUser(userInfo);
        if (!flag) {
        	return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(value="user")
	public ResponseEntity<Void> deleteUser(
			@RequestParam(value = "user_id") Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
} 