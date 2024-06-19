package com.itwill.spring2.repository;

import java.util.List;

import com.itwill.spring2.dto.PostSearchDto;
import com.itwill.spring2.dto.PostSortDto;

public interface PostDao {
	// post-mapper.xml에서 id="selectOrderByIdDesc"인 SQL을 실행하는 메서드.
	List<Post> selectOrderByIdDesc();

	Post selectByIdDesc(Integer id);

	int insertPost(Post post);

	int deleteById(Integer id);
	
	int updateById(Post post);
	
	List<Post> search(PostSearchDto dto);
	
	List<Post> sort(PostSortDto dto);
}
