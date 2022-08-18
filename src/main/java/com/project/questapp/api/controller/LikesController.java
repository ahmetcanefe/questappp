package com.project.questapp.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.business.LikeManager;
import com.project.questapp.entities.Like;
import com.project.questapp.requests.LikeCreateRequest;
import com.project.questapp.responses.LikeResponse;

@RestController
@RequestMapping("/api/likes")
public class LikesController {

	private LikeManager likeManager;

	public LikesController(LikeManager likeManager) {
		this.likeManager = likeManager;
	}
	
	@GetMapping
	public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId, 
			@RequestParam Optional<Long> postId) {
		return likeManager.getAllLikesWithParam(userId, postId);
	}
	
	@PostMapping
	public Like createOneLike(@RequestBody LikeCreateRequest request) {
		return likeManager.createOneLike(request);
	}
	
	@GetMapping("/{likeId}")
	public Like getOneLike(@PathVariable Long likeId) {
		return likeManager.getOneLikeById(likeId);
	}
	
	@DeleteMapping("/{likeId}")
	public void deleteOneLike(@PathVariable Long likeId) {
		likeManager.deleteOneLikeById(likeId);
	}
}
