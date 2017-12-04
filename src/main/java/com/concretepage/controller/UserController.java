package com.concretepage.controller;
import java.util.List;

import com.concretepage.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
	public ResponseEntity<List<UserInfo>> getAllUsers() {
		List<UserInfo> list = userService.getAllUsers();
		return new ResponseEntity<List<UserInfo>>(list, HttpStatus.OK);
	}

	@PostMapping("user")
	public ResponseEntity<Void> addUser(UserInfo userInfo, UriComponentsBuilder builder) {

        boolean flag = userService.addUser(userInfo);
        if (!flag) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/userInfo/{user_id}").buildAndExpand(userInfo.getId()).toUri());
        return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@DeleteMapping(value="user")
	public ResponseEntity<Void> deleteUser(
			@RequestParam(value = "user_id") Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
} 