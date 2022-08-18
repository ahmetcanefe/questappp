package com.project.questapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Like;

public interface LikeRepository extends JpaRepository<Like,Long>{

	List<Like> findByUserIdAndPostId(Long userId, Long postId);

	List<Like> findByUserId(Long userId);

	List<Like> findByPostId(Long postId);

	@Query(value = "select * from p_like where post_id in :postIds limit 5", nativeQuery = true)
	List<Like> findUserLikesByPostId(@Param("postIds") List<Long> postIds);
}