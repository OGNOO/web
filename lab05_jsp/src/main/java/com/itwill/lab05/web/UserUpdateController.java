package com.itwill.lab05.web;

import java.io.IOException;

import com.itwill.lab05.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "userUpdateController", urlPatterns = { "/user/myinfoUpdate" })
public class UserUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UserService userService = UserService.INSTANCE;

	private String refererUrl = null;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		refererUrl = req.getHeader("Referer");
		HttpSession session = req.getSession();

		String userId = (String) session.getAttribute("signedInUser");
		String email = req.getParameter("email");
		userService.update(userId, email);

		resp.sendRedirect(refererUrl);
	}
}
