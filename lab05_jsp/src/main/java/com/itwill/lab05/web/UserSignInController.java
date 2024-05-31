package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.User;
import com.itwill.lab05.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "userSignInController", urlPatterns = { "/user/signin" })
public class UserSignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(UserSignInController.class);

	private final UserService userService = UserService.INSTANCE;

	private String refererUrl = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		refererUrl = req.getHeader("Referer");
		log.debug(refererUrl);

		req.getRequestDispatcher("/WEB-INF/views/user/signin.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = (String) req.getParameter("userId");
		String password = (String) req.getParameter("password");

		User dto = User.builder().userId(userId).password(password).build();
		User user = userService.singIn(dto.getUserId(), dto.getPassword());
		// 이전 페이지로 리다이렉트
		HttpSession httpSession = req.getSession();
		if (user != null) {
			log.debug("로그인 성공");
			httpSession.setAttribute("signedInUser", user.getUserId());
			// FIXME: 타겟 목적지(URL)로 이동.
			// 홈페이지로 이동.

			if (refererUrl.contains("/lab05/user/sign")) {
				resp.sendRedirect(req.getContextPath() + "/");
			} else {
//			resp.sendRedirect(req.getContextPath() + "/");
				resp.sendRedirect(refererUrl);
			}
		} else {
			log.debug("로그인 실패");
			httpSession.setAttribute("signedInUser", null);
			log.debug("user 없음: {}", (String) httpSession.getAttribute("user"));
			resp.sendRedirect(req.getContextPath() + "/user/signin");
		}
	}
}
