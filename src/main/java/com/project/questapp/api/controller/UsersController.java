package com.project.questapp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.business.UserManager;
import com.project.questapp.entities.User;
import com.project.questapp.repositories.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	private UserManager userManager;
	
	@Autowired
	public UsersController(UserManager userManager)
	{
		this.userManager = userManager;
	}
	
	@GetMapping("/getAll")
	public List <User> getAllUsers()
	{
		return this.userManager.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id)
	{
		return this.userManager.getUserById(id);
	}
	
	@PostMapping("/add")
	public User AddUser(@RequestBody User user)
	{
		return this.userManager.addUser(user);
	}
	
	@PutMapping("/{id}")
	public User UpdateUser(@PathVariable Long id, @RequestBody User user)
	{
		return this.userManager.updateUser(id, user);
	}
	
	@DeleteMapping("/delete")
	public void deleteUser(@PathVariable Long id)
	{
		userManager.deleteUser(id);
	}
	
	@GetMapping("/activity/{userId}")
	public List<Object> getUserActivity(@PathVariable Long userId)
	{
		return userManager.getUserActivity(userId);
	}
	
	
	
 
}
