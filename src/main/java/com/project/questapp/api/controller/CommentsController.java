package com.project.questapp.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.questapp.entities.Comment;
import com.project.questapp.requests.CommentCreateRequest;
import com.project.questapp.requests.CommentUpdateRequest;
import com.project.questapp.business.CommentManager;

@RestController
@RequestMapping("/comments")
public class CommentsController {

	private CommentManager commentManager;
	
	@Autowired
	public CommentsController(CommentManager commentManager)
	{
		this.commentManager = commentManager;
	}
	
	@GetMapping("/getAll")
	public List<Comment> getAllCemments(@RequestParam Optional<Long> userId,
			@RequestParam Optional<Long> postId)
	{
		return commentManager.getAllCommentsWithParam(userId,postId);
	}
	
	@GetMapping("/{commentId}")
	public Comment getById(@PathVariable Long commentId)
	{
		return commentManager.getCommentById(commentId);
	}
	
	@PostMapping()
	public Comment add(@RequestBody CommentCreateRequest commentRequest)
	{
		return commentManager.add(commentRequest);
	}
	
	@PutMapping("/{commentId}")
	public Comment update(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentRequest)
	{
		return commentManager.update(commentId, commentRequest);
	}
	
	@DeleteMapping("/{commentId}")
	public void delete(@PathVariable Long commentId)
	{
		commentManager.delete(commentId);
	}
	
	

}
