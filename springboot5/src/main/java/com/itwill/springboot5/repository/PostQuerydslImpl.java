package com.itwill.springboot5.repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.domain.QPost;
import com.itwill.springboot5.dto.PostSearchRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostQuerydslImpl extends QuerydslRepositorySupport implements PostQuerydsl {

	public PostQuerydslImpl() {
		super(Post.class);
	}

	@Override
	public Post searchById(Long id) {
		log.info("searchById(id={})", id);
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post); // select p from Post p
		query.where(post.id.eq(id)); // where p.id = ?
		Post entity = query.fetchOne();

		return entity;
	}

	@Override
	public List<Post> searchByTitle(String keyword) {
		QPost post = QPost.post;
//		JPQLQuery<Post> query = from(post).where(post.title.containsIgnoreCase(keyword)).orderBy(post.id.desc());
//		List<Post> postList = query.fetch();

		List<Post> postList = from(post).where(post.title.containsIgnoreCase(keyword)).orderBy(post.id.desc()).fetch();

		return postList;
	}

	@Override
	public List<Post> searchByContent(String keyword) {
		QPost post = QPost.post;

		List<Post> postList = from(post).where(post.content.containsIgnoreCase(keyword)).orderBy(post.id.desc())
				.fetch();

		return postList;
	}

	@Override
	public List<Post> searchByTitleOrContent(String keyword) {
		QPost post = QPost.post;

		List<Post> postList = from(post)
				.where(post.content.containsIgnoreCase(keyword).or(post.title.containsIgnoreCase(keyword)))
				.orderBy(post.id.desc()).fetch();

		return postList;
	}

	@Override
	public List<Post> searchByModifiedTime(LocalDateTime from, LocalDateTime to) {
		QPost post = QPost.post;
		List<Post> postList = from(post).where(post.modifiedTime.between(from, to)).fetch();

		return postList;
	}

	@Override
	public List<Post> searchByAuthorAndTitle(String author, String title) {
		QPost post = QPost.post;
		List<Post> postList = from(post).where(post.author.eq(author).and(post.title.containsIgnoreCase(title)))
				.fetch();

		return postList;
	}

	@Override
	public List<Post> searchByCategory(PostSearchRequestDto postSearchRequestDto) {
		String category = postSearchRequestDto.getCategory();
		String keyword = postSearchRequestDto.getKeyword();
		QPost post = QPost.post;
		// BooleanBuilder : where() 메서드의 아규먼트인 booleanExpression 객체를 생성할 수 있는 객체
		BooleanBuilder booleanBuilder = new BooleanBuilder();

		switch (category) {
		case "t": {
			booleanBuilder.and(post.title.containsIgnoreCase(keyword));
			break;
		}
		case "c": {
			booleanBuilder.and(post.content.containsIgnoreCase(keyword));
			break;
		}
		case "tc": {
			booleanBuilder.and(post.title.containsIgnoreCase(keyword)).or(post.content.containsIgnoreCase(keyword));
			break;
		}
		case "a": {
			booleanBuilder.and(post.author.containsIgnoreCase(keyword));
			break;
		}
		default:
			booleanBuilder.and(post.title.containsIgnoreCase(keyword));
		}

		List<Post> postList = from(post).where(booleanBuilder).fetch();

		return postList;
	}

	@Override
	public List<Post> searchByKeywords(String[] keywords) {
		log.info("keywords={}", Arrays.asList(keywords));
		QPost post = QPost.post;
//		String[] upperKeywords = new String[keywords.length];
//
//		int i = 0;
//		for (String k : keywords) {
//			upperKeywords[i] = k.toUpperCase();
//			i++;
//		}
//		List<Post> postList = from(post).where((post.title).toUpperCase().in(upperKeywords)).fetch();
//
//		return postList;

		BooleanBuilder booleanBuilder = new BooleanBuilder();
		for (String k : keywords) {
			booleanBuilder.or(post.title.containsIgnoreCase(k).or(post.content.containsIgnoreCase(k)));
		}
		List<Post> postList = from(post).where(booleanBuilder).orderBy(post.id.desc()).fetch();

		return postList;
	}

	@SuppressWarnings("null")
	@Override
	public Page<Post> searchByKeywords(String[] keywords, Pageable pageable) {
		log.info("searchByKeywords(keyword={}, Pageable={})", Arrays.asList(keywords), pageable);

		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post);
		BooleanBuilder builder = new BooleanBuilder();
		for (String k : keywords) {
			builder.or(post.title.containsIgnoreCase(k).or(post.content.containsIgnoreCase(k)));
		}
		query.where(builder);

		// Paging & Sorting 적용
		getQuerydsl().applyPagination(pageable, query);

		// 한 페이지에 표시할 데이터를 fetch.
		List<Post> list = query.fetch();
		log.info("list.size = {}", list.size());

		// 전체 레코드 개수를 fetch.
		long count = query.fetchCount();
		log.info("fetch count = {}", count);

		// Page<T> 객체를 생성.
		Page<Post> page = new PageImpl<>(list, pageable, count);

		return page;
	}

}
