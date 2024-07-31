package com.itwill.springboot5.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.itwill.springboot5.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PostRepositoryTest {

	@Autowired
	private PostRepository postRepository;

//	@Test
	void testFindAll() {
		assertThat(postRepository).isNotNull();
		log.debug("findAll() 테스트");
		List<Post> res = postRepository.findAll();
		res.forEach(i -> System.out.println(i));
	}

//	@Test
	void testInsert() {
		Post p = Post.builder().title("테스트").content("내용").author("작성자").build();
		log.info("save 전 : {}", p);
		postRepository.save(p);
		log.info("save 후 : {}", p);
	}

//	@Test
	void testUpdate() {
		Post entity = postRepository.findById(2L).orElseThrow();
		Post p = Post.builder().id(entity.getId()).title("테스트2").content("내용2").author("?").build();
		log.info("save 전 : {}", p);
		postRepository.save(p);
		log.info("save 후 : {}", p);
	}

//	@Test
	void testDelete() {
		postRepository.deleteById(6L);
	}

//	@Test
	void testSearch() {
		String keyword = "테스트2";
		int page = 0;
		Pageable pageable = PageRequest.of(page, 5, Sort.by("id").descending());
		Page<Post> posts = postRepository.findByTitleContainingIgnoreCase(keyword, pageable);
		posts.forEach(i -> System.out.println(i));
	}

//	@Test
	void testSelectTitleOrContent() {
		String keyword = "테";
		int page = 0;
		Pageable pageable = PageRequest.of(page, 5, Sort.by("id").descending());
		Page<Post> posts = postRepository.findByTitleContainingOrContentContainingAllIgnoringCase(keyword, keyword, pageable);
		posts.forEach(i -> System.out.println(i));
	}
	
	@Test
	void testMyFindByTitleOrContent() {
		String keyword = "테";
		int page = 0;
		Pageable pageable = PageRequest.of(page, 5, Sort.by("id").descending());
		Page<Post> posts = postRepository.findByTitleOrContent(keyword, pageable);
		posts.forEach(i -> System.out.println(i));
	}
}
