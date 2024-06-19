package com.itwill.spring2.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostDetailsDto;
import com.itwill.spring2.dto.PostListAjaxDto;
import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.dto.PostSearchDto;
import com.itwill.spring2.dto.PostSortDto;
import com.itwill.spring2.dto.PostUpdateDto;
import com.itwill.spring2.repository.Post;
import com.itwill.spring2.repository.PostDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service // 스프링 컨테이너에 서비스 컴포넌트로 등록.
@RequiredArgsConstructor // final 필드들을 초기화하는 아규먼트를 갖는 생성자.
public class PostService {

	// 애너테이션을 사용한 의존성 주입(DI: Dependency Injection):
//	@Autowired
//	private PostDao postDao;

	// 생성자에 의한 의존성 주입
	// (1) final 필드 선언. (2) final 필드를 초기화하는 생성자 작성.
	private final PostDao postDao;

//	public PostService(PostDao postDao) {
//		this.postDao= postDao;
//	}
	public List<PostListDto> read() {
		log.debug("read()");

		List<Post> list = postDao.selectOrderByIdDesc();

//		List<PostListDto> result = new ArrayList<>();
//		for (Post p : list) {
//			result.add(PostListDto.fromEntity(p));
//		}
		if (!list.isEmpty()) {
			return list.stream().map(PostListDto::fromEntity).toList();
			// map((x) -> PostListDto.fromEntity(x))
		} else {
			return Collections.emptyList();
		}
	}

	public PostDetailsDto read(Integer id) {
		Optional<Post> optionalpost = Optional.ofNullable(postDao.selectByIdDesc(id));
		PostDetailsDto res = null;

		if (optionalpost.isPresent()) {
			Post post = optionalpost.get();
			res = PostDetailsDto.fromEntity(post);
		}
		return res;
	}

	public int create(PostCreateDto postCreateDto) {
		Post post = postCreateDto.toEntity(postCreateDto);

		if (post.getTitle().isBlank() || post.getAuthor().isBlank() || post.getContent().isBlank()) {
			return -1;
		}
		log.debug(post.toString());

		int res = postDao.insertPost(post);

		return res;
	}

	public int delete(Integer id) {
		int res = postDao.deleteById(id);

		return res;
	}

	public int update(PostUpdateDto postUpdateDto) {
		Post post = postUpdateDto.toEntity(postUpdateDto);

		if (post.getTitle().isBlank() || post.getAuthor().isBlank() || post.getContent().isBlank()) {
			return -1;
		}

		int res = postDao.updateById(post);

		return res;
	}

	
	public List<PostListAjaxDto> search(PostSearchDto postSearchDto) {
		List<Post> list = postDao.search(postSearchDto);
		
		if (!list.isEmpty()) {
			return list.stream().map(PostListAjaxDto::fromEntity).toList();
		} else {
			return Collections.emptyList();
		}
	}
	
	public List<PostListAjaxDto> sort(PostSortDto postSortDto) {
		List<Post> list = postDao.sort(postSortDto);
		if (!list.isEmpty()) {
			return list.stream().map(PostListAjaxDto::fromEntity).toList();
		}else {
			return Collections.emptyList();
		}
	}
}
