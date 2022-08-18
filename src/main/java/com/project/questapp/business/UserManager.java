package com.project.questapp.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Like;
import com.project.questapp.entities.User;
import com.project.questapp.repositories.CommentRepository;
import com.project.questapp.repositories.LikeRepository;
import com.project.questapp.repositories.PostRepository;
import com.project.questapp.repositories.UserRepository;

@Service
public class UserManager {

	UserRepository userRepository;
	LikeRepository likeRepository;
	CommentRepository commentRepositry;
	PostRepository postRepository;

	
	
	public UserManager(UserRepository userRepository, LikeRepository likeRepository, CommentRepository commentRepositry,
			PostRepository postRepository) {
		super();
		this.userRepository = userRepository;
		this.likeRepository = likeRepository;
		this.commentRepositry = commentRepositry;
		this.postRepository = postRepository;
	}

	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	public User getUserById(Long id)
	{
		return userRepository.findById(id).orElse(null);
	}
	
	public User addUser(User user)
	{
		return userRepository.save(user);
	}
	
	public User updateUser(Long id, User user)
	{
		java.util.Optional<User> existingUser = userRepository.findById(id);
	    if(existingUser.isPresent()) {
	    	User foundUser = existingUser.get();
	    	foundUser.setUserName(user.getUserName());
	    	foundUser.setPassword(user.getPassword());
	    	userRepository.save(foundUser);
	    	return foundUser;
	    }
	    else {
	    	return null;
	    }
	}
	
	public void deleteUser(Long id)
	{
		userRepository.deleteById(id);
	}
	
	public User getUserByUserName(String userName)
	{
		return userRepository.findByUserName(userName);
	}
	
	public List<Object> getUserActivity(Long userId)
	{
		List<Long> postIds = postRepository.findTopByUserId(userId);
		if(postIds.isEmpty())
		{ 
			return null;
		}
		List<Comment> comments = commentRepositry.findUserCommentsByPostId(postIds);
		List<Like> likes = likeRepository.findUserLikesByPostId(postIds);
	
	    List<Object> result = new ArrayList<>();
	    result.addAll(comments);
	    result.addAll(likes);
	    return result;
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
