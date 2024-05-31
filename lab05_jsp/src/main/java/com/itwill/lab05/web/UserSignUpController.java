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

@WebServlet(name = "userSignUpController", urlPatterns = { "/user/signup" })
public class UserSignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(UserSignUpController.class);

	private final UserService userService = UserService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/user/signup.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 // 회원가입 폼에서 제출된 userid, password, email 요청 파라미터 값을 읽음.
		String userId = (String) req.getParameter("userId");
		String password = (String) req.getParameter("password");
		String email = (String) req.getParameter("email");
		log.debug(userId);

		User user = User.builder().userId(userId).password(password).email(email).build();
		
		int findUser = userService.select(user.getUserId());
		int res = 0;
		if (findUser == 1) {
			log.debug("이미 있는 계정");
		} else {
			log.debug("계정 생성");
			// 서비스 계층의 메서드를 호출해서 회원가입.
			res = userService.create(user);
		}

		String url;

		if (res == 1) {
			url = req.getContextPath() + "/user/signin";
		} else {
			url = req.getContextPath() + "/user/signup";
		}
		resp.sendRedirect(url);
	}
}
