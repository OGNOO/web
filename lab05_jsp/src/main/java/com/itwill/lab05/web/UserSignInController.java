package com.itwill.lab05.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.User;
import com.itwill.lab05.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "userSignInController", urlPatterns = { "/user/signin" })
public class UserSignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(UserSignInController.class);

	private final UserService userService = UserService.INSTANCE;

	// 주석 된 부분은 refererUrl = req.getHeader("Referer") <- 메서드를 사용해서 전 페이지로 되돌아가는 법
//	private String refererUrl = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		refererUrl = req.getHeader("Referer");
//		log.debug(refererUrl);

		req.getRequestDispatcher("/WEB-INF/views/user/signin.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");

		User dto = User.builder().userId(userId).password(password).build();
		User user = userService.singIn(dto.getUserId(), dto.getPassword());

		String refererUrl = req.getHeader("Referer");
		String target = null;
		// 이전 페이지로 리다이렉트
		if (user != null) {
			log.debug("로그인 성공");
			// 세션에 로그인 정보를 저장.
			HttpSession session = req.getSession();
			session.setAttribute("signedInUser", user.getUserId());
//			// 타겟 목적지(URL)로 이동. 방법: 1
//
//			if (refererUrl.contains("/lab05/user/sign")) {
//				resp.sendRedirect(req.getContextPath() + "/");
//			} else {
//				resp.sendRedirect(refererUrl);
//			}

			// 타겟 목적지(URL)로 이동. 방법: 2
			log.debug(refererUrl);
			int index = refererUrl.indexOf("target=");
			if (index != -1) {
				target = refererUrl.substring(index + 7);
			}
			log.debug("targetParam = {}", target);
			if (target != null) {
				String decodedURL = URLDecoder.decode(target, "UTF-8");
				resp.sendRedirect(decodedURL);
			} else {
				String url = req.getContextPath() + "/";
				resp.sendRedirect(url);
			}
		} else {
			log.debug("로그인 실패");
			// 다시 로그인 페이지로 이동
			resp.addCookie(new Cookie("result", "f"));
			resp.sendRedirect(refererUrl);
		}
	}
}
