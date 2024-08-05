package com.itwill.springboot5.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.springboot5.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostQuerydsl{

	Page<Post> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

	Page<Post> findByContentContainingIgnoreCase(String keyword, Pageable pageable);

	Page<Post> findByTitleContainingOrContentContainingAllIgnoringCase(String title, String content, Pageable pageable);

	Page<Post> findByAuthorContainingIgnoreCase(String keyword, Pageable pageable);

	@Query("select p from Post p where upper(p.title) like upper('%' || :keyword || '%')"
			+ "OR upper(p.content) like upper('%' || :keyword || '%')")
	Page<Post> findByTitleOrContent(@Param("keyword") String keyword, Pageable pageable);
}
