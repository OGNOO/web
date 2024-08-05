package com.itwill.springboot5.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostSearchRequestDto;

/*
 * QueryDsl 사용:
 * 1. build.gradle 파일에 의존성과 빌드 옵션을 추가.
 * 2. 프로젝트 이름(우클릭)-> Gradle -> Refresh Gradle Project
 * 3. Window -> Show view -> Other -> Gradle -> Gradle Tasks, Gradle Executions Open
 * 4. Gradle Tasks -> 프로젝트 선택 -> build -> clean(우클릭) -> run Gradle Task
 * 5. Gradle Tasks -> 프로젝트 선택 -> build -> build(우클릭) -> run Gradle Task
 * 6. 프로젝트 이름(우클릭)-> Gradle -> Refresh Gradle Project
 *   build/gradle/guerydsl 폴더에 com.itwill.springboot.domain 패키지가 생성
 *   패키지에는 Q클래스들(2개)이 자동 생성.
 * 7. 인터페이스(예: PostQuerydsl) 작성
 * 8. 인터페이스 구현하는 클래스(PostQuerydslImpl)를 작성.
 *   (1) QuerydslRepositorySupport 상속
 *     - 상위 클래스가 기본 생성자를 갖고 있지 않기 때문에, 아규먼트를 갖는 super(arg)를 명시 해야함
 *   (2) PostQuerydsl 인터페이스를 구현
 *     - 추상 메서드의 body를 구현
 * 9. JpaRepository를 상속받는 인터페이스(PostRepository) 에서 PostQuerydsl 인터페이스를 상속.
 * 10
 */
public interface PostQuerydsl {

	Post searchById(Long id);

	List<Post> searchByTitle(String keyword);
	
	List<Post> searchByContent(String keyword);
	
	List<Post> searchByTitleOrContent(String keyword);

	List<Post> searchByModifiedTime(LocalDateTime from, LocalDateTime to);

	List<Post> searchByAuthorAndTitle(String author, String title);
	
	List<Post> searchByCategory(PostSearchRequestDto postSearchRequestDto);

	List<Post> searchByKeywords(String[] keywords);

	Page<Post> searchByKeywords(String[] keywords, Pageable pageable);

}
