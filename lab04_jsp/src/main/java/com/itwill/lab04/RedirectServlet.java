package com.itwill.lab04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class RedirectServlet
 */
@WebServlet(name = "redirectServlet", urlPatterns = { "/ex4" })
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("RedirectServlet::doGet() ȣ��");
		
		response.sendRedirect("ex3"); // ���� WAS �� �������� �̵�
//		response.sendRedirect("https://www.naver.com"); // �ٸ� �� ������ ������ �̵�.
		// "redirect" ����� ������ �̵�:
		// ���� ��û(request) --> redirect ���� (response) --> ���û --> ����
		// ���� ��û �ּҰ� �̵��ϴ� �������� �ٲ�.
		// ���� ��û�� request, response ��ü�� �̵��ϴ� �������� ���޵��� ����!
		// ���� WAS �Ӹ� �ƴ϶�, �ٸ� �� ����, �ٸ� �� ���ø����̼����ε� �̵��� ����.
		
	}

}
