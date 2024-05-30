package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.Post;
import com.itwill.lab05.service.PostService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "postCreateController", urlPatterns = { "/post/create" })
public class PostCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostCreateController.class);

	private final PostService postService = PostService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doGet()");

		// 새 글 작성 폼(양식)을 작성하는 뷰(JSP)로 이동.
		req.getRequestDispatcher("/WEB-INF/views/post/create.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doPost()");

		String title = (String) req.getParameter("title");
		String content = (String) req.getParameter("content");
		String author = (String) req.getParameter("author");

		log.debug("title = {}", title);
		log.debug("content = {}", content);
		log.debug("author = {}", author);

		Post post = Post.builder().title(title).content(content).author(author).build();
		int res = postService.create(post);
		log.debug("res = {}", res);

		String url;
		
		if (res == 1) {
			// insert 성공
			// 포스트 목록 페이지 이동.
			url = req.getContextPath() + "/post/list";
			log.debug("redirect:" + url);
			// PRG(Post-Redirect-Get)
		} else {
			// insert 실패			
			url = req.getContextPath() + "/post/create";
		}
		resp.sendRedirect(url);
	}
}
