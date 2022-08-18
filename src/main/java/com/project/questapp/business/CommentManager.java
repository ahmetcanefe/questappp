package com.project.questapp.business;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repositories.CommentRepository;
import com.project.questapp.requests.CommentCreateRequest;
import com.project.questapp.requests.CommentUpdateRequest;

@Service
public class CommentManager {

	private CommentRepository commentRepository;
	private UserManager userManager;
	private PostManager postManager;
	
	
	public CommentManager(CommentRepository commentRepository, UserManager userManager, PostManager postManager) {
		this.commentRepository = commentRepository;
		this.userManager = userManager;
		this.postManager = postManager;
	}


	public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId)
	{
		if(userId.isPresent()&&postId.isPresent())
		{
			return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
		}
		else if(userId.isPresent())
		{
			return commentRepository.findByUserId(userId.get());
		}
		else if(postId.isPresent())
		{
			return commentRepository.findByPostId(postId.get());
		}
		else {
			return commentRepository.findAll();
		}
	}
	
	public Comment getCommentById(Long commentId)
	{
		return commentRepository.findById(commentId).orElse(null);
	}
	
	public Comment add(CommentCreateRequest commentRequest)
	{
		User user = userManager.getUserById(commentRequest.getUserId());
		Post post = postManager.getOnePostById(commentRequest.getPostId());
		if(user!=null && post!=null)
		{
			Comment comment = new Comment();
			comment.setText(commentRequest.getText());
			comment.setUser(user);
			comment.setPost(post);
			return commentRepository.save(comment);
		}
		return null;		
	}
	
	public Comment update(Long commentId, CommentUpdateRequest commentRequest)
	{
		Optional<Comment> comment = commentRepository.findById(commentId);
	    if(comment.isPresent())
	    {
	    	Comment existingComment = comment.get();
	    	existingComment.setText(commentRequest.getText());
	    	return commentRepository.save(existingComment);
	    }
	    return null;
	}
	
	public void delete(Long commentId)
	{
		commentRepository.deleteById(commentId);
	}
}
