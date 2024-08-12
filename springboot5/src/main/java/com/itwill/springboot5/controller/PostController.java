package com.itwill.springboot5.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostCreateDto;
import com.itwill.springboot5.dto.PostListItemDto;
import com.itwill.springboot5.dto.PostSearchRequestDto;
import com.itwill.springboot5.dto.PostUpdateDto;
import com.itwill.springboot5.service.PostService;

//import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {

	private final PostService postService;
	// private final HttpSession session;

	@GetMapping("/list")
	public void list(@RequestParam(name = "p", defaultValue = "0") int pageNo, Model model) {
		log.info("list()");
		Page<PostListItemDto> postList = postService.list(pageNo, Sort.by("id").descending());
		model.addAttribute("postList", postList);

	}

	@PreAuthorize("isAuthenticated()") // => role 에 상관없이 아이디/비번으로만 인증하는 경우
	@GetMapping("/details/{id}")
	public String details(@PathVariable(name = "id") Long id, Model model) {
		log.info("details()");
		log.info("id={}", id);
		Post post = postService.detail(id);
		model.addAttribute("post", post);

		return "/post/details";
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	// @PreAuthorize("hasRole('USER')")
	@GetMapping("/create")
	public void createPage() {
		log.info("createPage()");
	}

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/create")
	public String create(PostCreateDto postCreateDto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		if (!userDetails.getUsername().equals(postCreateDto.getAuthor())) {
			return "redirect:/post/create";
		}

		log.info("create={}", postCreateDto);
		int res = postService.create(postCreateDto);
		if (res == 0) {
			return "redirect:/post/create";
		}

		return "redirect:/post/list";
	}

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/update")
	public String update(@RequestBody PostUpdateDto postUpdateDto) {
		log.info("update={}", postUpdateDto.toString());
		int res = postService.update(postUpdateDto);
		if (res == 0) {
			return "redirect:/post/details/" + postUpdateDto.getId();
		}

		return "redirect:/post/list";
	}

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id") Long id) {
		log.info("delete={}", id);
		int res = postService.deleteById(id);
		if (res == 0) {
			return "redirect:/post/details/" + id;
		}

		return "redirect:/post/list";
	}

	@GetMapping("/search")
	public String search(PostSearchRequestDto postSearchRequestDto, Model model) {
		log.info("searchDto={}", postSearchRequestDto);
		// if (postSearchRequestDto.getCategory() != null) {
		// session.setAttribute("category", postSearchRequestDto.getCategory());
		// } else {
		// postSearchRequestDto.setCategory((String) session.getAttribute("category"));
		// }
		// if (postSearchRequestDto.getKeyword() != null) {
		// session.setAttribute("keyword", postSearchRequestDto.getKeyword());
		// } else {
		// postSearchRequestDto.setKeyword((String) session.getAttribute("keyword"));
		// }
		Page<PostListItemDto> postList = postService.search(postSearchRequestDto);
		model.addAttribute("postList", postList);

		return "post/list";
	}

}
