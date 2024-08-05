package com.itwill.springboot5.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostSearchRequestDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PostQuerydslTest {

	@Autowired
	private PostRepository postRepository;

//	@Test
	void testSearchById() {
		log.info("searchById() 테스트");
		Post p = postRepository.searchById(1L);
		System.out.println(p);
	}

//	@Test
	void testSearchByTitle() {
		List<Post> p = postRepository.searchByTitle("스");
		p.forEach(i -> System.out.println(i));
	}

//	@Test
	void testSearchByTitleOrContent() {
		List<Post> p = postRepository.searchByTitleOrContent("스");
		p.forEach(i -> System.out.println(i));
	}

//	@Test
	void testSearchByModifiedTime() {
		LocalDateTime firstTime = LocalDateTime.of(2024, 7, 30, 15, 0);
		LocalDateTime secondTime = LocalDateTime.now();
		List<Post> p = postRepository.searchByModifiedTime(firstTime, secondTime);
		p.forEach(i -> System.out.println(i));
	}

//	@Test
	void testSearchByAuthorAndTitle() {
		List<Post> p = postRepository.searchByAuthorAndTitle("작성자", "테스트");
		p.forEach(i -> System.out.println(i));
	}

	@Test
	void searchByCategoryTest() {
		PostSearchRequestDto postSearchRequestDto = PostSearchRequestDto.builder().category("a").keyword("스").build();
		List<Post> p = postRepository.searchByCategory(postSearchRequestDto);
		p.forEach(i -> System.out.println(i));
	}

//	@Test
	void testSearchByKeywords() {
		String[] kewords = { "hWsUrfY", "PWMQLTH", "성", "oOzLzry", "테" };
//		List<Post> p = postRepository.searchByKeywords(kewords);
		Pageable pageable = PageRequest.of(0, 5, Sort.by("id").descending());
		Page<Post> p = postRepository.searchByKeywords(kewords, pageable);
		p.forEach(i -> System.out.println(i));
	}
}
