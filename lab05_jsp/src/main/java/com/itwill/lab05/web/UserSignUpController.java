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

	private static final Logger log = LoggerFactory.getLogger(PostCreateController.class);

	private final UserService userService = UserService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/user/signup.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = (String) req.getParameter("userId");
		String password = (String) req.getParameter("password");
		String email = (String) req.getParameter("email");
		log.debug(userId);

		User user = User.builder().userId(userId).password(password).email(email).build();

		int res = userService.create(user);

		String url;

		if (res == 1) {
			url = req.getContextPath();
		} else {
			url = req.getContextPath() + "/user/signin";
		}
		resp.sendRedirect(url);
	}
}
