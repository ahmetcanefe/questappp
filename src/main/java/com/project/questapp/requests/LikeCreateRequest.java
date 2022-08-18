package com.project.questapp.requests;

import lombok.Data;

@Data
public class LikeCreateRequest {
	
	Long id;
	Long userId;
	Long postId;
	
	public LikeCreateRequest()
	{
		
	}
	public LikeCreateRequest(Long id, Long userId, Long postId) {
		super();
		this.id = id;
		this.userId = userId;
		this.postId = postId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	
}
