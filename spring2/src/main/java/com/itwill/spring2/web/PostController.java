package com.itwill.spring2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostDetailsDto;
import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.dto.PostUpdateDto;
import com.itwill.spring2.service.PostService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor // final 필드들을 초기화하는 생성자.
@RequestMapping("/post")
//-> PostController 클래스의 모든 컨트롤러 메서드의 매핑 주소는 "/post" 로 시작.
public class PostController {

	private final PostService postService; // 생성자에 의한 의존성 주입

	@GetMapping("/list")
	public void list(Model model) {
		log.debug("list()");

		// 서비스 컴포넌트의 메서드를 호출, 포스트 목록을 읽어옴 -> 뷰에 전달.
		List<PostListDto> list = postService.read();
		model.addAttribute("posts", list);
	}

	@GetMapping("/details")
	public String details(@RequestParam(name = "id") Integer id, Model model) {
		PostDetailsDto post = postService.read(id);
		if (post != null) {
			model.addAttribute("post", post);

			return "/post/details";
		} else {
			return "redirect:/post/list";
		}
	}

	@GetMapping("/create")
	public void getCreate() {
	}

	@PostMapping("/create")
	public String postCreate(@ModelAttribute PostCreateDto postCreateDto) {
		int res = postService.create(postCreateDto);

		if (res == 1) {
			return "redirect:/post/list";
		} else {
			return "redirect:/post/create";
		}
	}

//	@GetMapping({ "/details", "/modify" })
//    //-> GET 방식의 "/post/details", "/post/modify" 2개의 요청을 처리하는 메서드.
//    public void details(@RequestParam(name = "id") int id, Model model) {
//        log.debug("details(id={})", id);
//        
//        // 서비스 컴포넌트의 메서드를 호출해서 해당 id의 포스트를 검색(select).
//        Post post = postService.read(id);
//        
//        // 뷰에 데이터를 전달하기 위해서 model 객체에 post를 속성으로 추가.
//        model.addAttribute("post", post);
//        
//        // 리턴 타입이 void이므로 뷰의 이름은
//        // (1) 요청 주소가 /post/details인 경우 /WEB-INF/views/post/details.jsp
//        // (2) 요청 주소가 /post/modify인 경우 /WEB-INF/views/post/modify.jsp
//    }	

	@Autowired
	private HttpSession session;

	@GetMapping("/modify")
	public String modify(@RequestParam(name = "id") Integer id, Model model) {
		PostDetailsDto post = postService.read(id);
		if (post != null) {
			model.addAttribute("post", post);

			session.setAttribute("modifyId", post.getId());

			return "post/modify";
		} else {
			return "redirect:/post/list";
		}
	}

	@GetMapping("/delete")
	public String delete(@RequestParam(name = "id") Integer id) {
		postService.delete(id);

		return "redirect:/post/list";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute PostUpdateDto postUpdateDto) {
		Integer modifyId = (Integer) session.getAttribute("modifyId");
		postUpdateDto.setId(modifyId);
		int res = postService.update(postUpdateDto);

		if (res == 1) {
			return "redirect:/post/details?id=" + modifyId;
		} else {
			return "redirect:/post/modify?id=" + modifyId;
		}
	}

//	@GetMapping("/search")
//	public String search(@ModelAttribute PostSearchDto postSearchDto, Model model) {
//		log.debug(postSearchDto.toString());
//		List<PostListDto> list = postService.search(postSearchDto);
//		model.addAttribute("posts", list);
//
//		return "post/list";
//	}

}
