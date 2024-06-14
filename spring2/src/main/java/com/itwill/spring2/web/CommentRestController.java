package com.itwill.spring2.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.dto.CommentCreateDto;
import com.itwill.spring2.dto.CommentItemDto;
import com.itwill.spring2.dto.CommentUpdateDto;
import com.itwill.spring2.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // 리턴하는 값은 뷰 이름이 아니라, 클라이언트로 직접 전송(응답) 되는 데이터.
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentRestController {
	private final CommentService commentService; // 생성자에 의한 의존성 주입.

	@GetMapping("/all/{postId}")
	public ResponseEntity<List<CommentItemDto>> getAllComment(@PathVariable Integer postId) {
		// @PathVariable: 요청 주소의 일부가 변수처럼 변할 수 있는 값일 때,
		// 디스패쳐 서블릿이 요청 주소를 분석해서 메서드의 아규먼트로 전달.
		// 1. @PathVariable(name = "postId") 처럼 패스 변수의 이름을 명시하거나,
		// 2. 패스 변수의 이름을 명시하지 않고 메서드의 파라미터 이름으로 패스 변수를 찾으려면
		// (Eclipse) 프로젝트 이름 오른쪽 클릭 -> Properties -> Java Compiler ->
		// "Store information about method parameters (usable via reflection)" 항목을 체크.
		log.debug("getAllComment() = {}", postId);

		// 서비스 컴포넌트의 메서드를 호출해서 해당 포스트의 댓글 목록을 가져옴
		List<CommentItemDto> list = commentService.readByPostId(postId);

		// ResponseEntity<T>: 서버가 클라이언트로 보내는 데이터와 응답코드를 함께 설정할 수 있는 타입.
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{cid}")
	public ResponseEntity<CommentItemDto> read(@PathVariable Integer cid) {
		CommentItemDto commentItemDto = commentService.readById(cid);
		log.debug(commentItemDto.toString());

		return ResponseEntity.ok(commentItemDto);
	}

	@PostMapping("")
	public ResponseEntity<Integer> registerComment(@RequestBody CommentCreateDto commentCreateDto) {
		// @RequestBody: Ajax 요청의 요청 패킷 몸통(body)에 포함된 데이터를 읽어서 자바 객체로 변환.
		log.debug(commentCreateDto.toString());

		int res = commentService.create(commentCreateDto);

		return ResponseEntity.ok(res);
	}
	
	@PutMapping("/{cid}")
	public ResponseEntity<Integer> updateComment(@PathVariable Integer cid, @RequestBody CommentUpdateDto commentUpdateDto){
		commentUpdateDto.setId(cid);
		
		int res = commentService.update(commentUpdateDto);
		
		return ResponseEntity.ok(res);
	}
	
	@DeleteMapping("/{cid}")
	public ResponseEntity<Integer> deleteComment(@PathVariable Integer cid){
		int res = commentService.delete(cid);
		
		return ResponseEntity.ok(res);
	}

	// REST 컨트롤러 메서드가 자바 객체를 리턴하면
	// jackson-databind 라이브러리가 자바 객체를 JSON 문자열로 변환을 담당하고,
	// JSON 문자열이 클라이언트로 전송(응답)됨.
	// jackson-databind 라이브러리의 역할:
	// 1. 직렬화(serialization): 자바 객체 -> JSON
	// 2. 역직렬화(de-serialization): JSON -> 자바 객체
	// jackson-databind 라이브러리에서
	// Java 8 이후에 생긴 날짜/시간 타입(LocalDate, LocalDateTime)을 JSON으로 변환하기 위해서는
	// jackson-datatype-jsr310 모듈이 필요함.

}
