package com.itwill.springboot5.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.springboot5.domain.Comment;
import com.itwill.springboot5.domain.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

//	Page<Comment> findByPostId(Long id, Pageable pageable);

	Page<Comment> findByPost(Post post, Pageable pageable);

}
