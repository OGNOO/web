package com.itwill.springboot5.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostCreateDto;
import com.itwill.springboot5.dto.PostListItemDto;
import com.itwill.springboot5.dto.PostSearchRequestDto;
import com.itwill.springboot5.dto.PostUpdateDto;
import com.itwill.springboot5.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

	private final PostRepository postRepository;

	@Transactional(readOnly = true)
	public Page<PostListItemDto> list(int pageNo, Sort sort) {
		log.info("read(pageNo={}, sort={})", pageNo, sort);

		// Pageable 객체 생성
		Pageable pageable = PageRequest.of(pageNo, 5, sort);

		// 영속성(persistence/repository) 계층의 메서드를 호출해서 엔터티들의 리스트를 가져옴.
		Page<Post> list = postRepository.findAll(pageable);
		log.info("page.totalPages = {}", list.getTotalPages()); // 전체 페이지 개수
		log.info("page.number = {}", list.getNumber()); // 현재 페이지 번호
		log.info("page.hasPrevious = {}", list.hasPrevious()); // 이전 페이지가 있는 지 여부
		log.info("page.hasNext = {}", list.hasNext()); // 다음 페이지가 있는 지 여부

		// Page<Post> 객체를 Page<PostListItemDto> 타입으로 변환.
		// (x) -> PostListItemDto.fromEntity(x)
		Page<PostListItemDto> posts = list.map(PostListItemDto::fromEntity);

		return posts;
	}

	public int create(PostCreateDto postCreateDto) {
		log.info("새 글 작성 서비스");
		if (postCreateDto.getAuthor().isBlank() || postCreateDto.getContent().isBlank()
				|| postCreateDto.getTitle().isBlank()) {
			return 0;
		}
		System.out.println(postCreateDto.toEntity());
		postRepository.save(postCreateDto.toEntity());

		return 1;
	}

	@Transactional(readOnly = true)
	public Post detail(Long id) {
		Optional<Post> optionalPost = postRepository.findById(id);
		if (optionalPost.isPresent()) {
			return optionalPost.get();
		} else {
			return new Post();
		}
	}

	public int update(PostUpdateDto postUpdateDto) {
		if (postUpdateDto.getContent().isBlank() || postUpdateDto.getTitle().isBlank()) {
			return 0;
		}
		postRepository.save(postUpdateDto.toEntity());
		return 1;
	}

	public int deleteById(Long id) {
		postRepository.deleteById(id);
		return 1;
	}

	@Transactional(readOnly = true)
	public Page<PostListItemDto> search(PostSearchRequestDto postSearchRequestDto) {
		String keyword = null;
		if (postSearchRequestDto.getKeyword().isEmpty()) {
			keyword = "";
		} else {
			keyword = postSearchRequestDto.getKeyword();
		}
		String category = postSearchRequestDto.getCategory();
		Pageable pageable = PageRequest.of(postSearchRequestDto.getP(), 5, Sort.by("id").descending());
		Page<Post> list;
		switch (category) {
		case "t": {
			list = postRepository.findByTitleContainingIgnoreCase(keyword, pageable);
			break;
		}
		case "c": {
			list = postRepository.findByContentContainingIgnoreCase(keyword, pageable);
			break;
		}
		case "tc": {
			list = postRepository.findByTitleContainingOrContentContainingAllIgnoringCase(keyword, keyword, pageable);
			break;
		}
		case "a": {
			list = postRepository.findByAuthorContainingIgnoreCase(keyword, pageable);
			break;
		}
		default:
			list = postRepository.findByTitleContainingIgnoreCase(keyword, pageable);
		}
		Page<PostListItemDto> posts = list.map(PostListItemDto::fromEntity);
		
		return posts;
	}
}
