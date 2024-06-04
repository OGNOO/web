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

@WebServlet(name = "myInfoController", urlPatterns = { "/user/myInfo" })
public class MyInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MyInfoController.class);

	private final UserService userService = UserService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doGet()");

		// 세션 id 값 읽음
		// 세션 객체 가져오기
		HttpSession session = req.getSession();

		// 세션 값 가져오기
		String userId = (String) session.getAttribute("signedInUser");
		User user = userService.read(userId);

		int pwlen = user.getPassword().length();
		 // StringBuilder 객체 생성
        StringBuilder stars = new StringBuilder();

        // 별 문자열 생성
        for (int i = 0; i < pwlen; i++) {
            stars.append("*");
        }
		user.setPassword(String.valueOf(stars));
		
		log.debug(String.valueOf(pwlen));
		log.debug(String.valueOf(stars));

		req.setAttribute("user", user);

		req.getRequestDispatcher("/WEB-INF/views/user/myInfo.jsp").forward(req, resp);
	}
}
