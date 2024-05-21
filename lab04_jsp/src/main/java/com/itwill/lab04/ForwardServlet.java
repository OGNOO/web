package com.itwill.lab04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ForwardServlet
 */
@WebServlet(name = "forwardServlet", urlPatterns = { "/ex3" })
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ForwardServlet::doGet() ȣ��");
		
		// ��û�� ���� WAS�� web.xml �Ǵ� @WebServlet �������̼ǿ��� ������ URL mapping �� ����
		// ��û�� ó���� �� �ִ� ���� Ŭ���� ��ü�� �޼���(doGet, doPost)�� ȣ��
		// ���������� HTML �ڵ带 �ۼ��ؼ� ������ ������ ��.
		// �������� HTML �� �ۼ��ϴ� ���� �ʹ� ���ŷο�.
		// ������ JSP�� ��û�� �����ϰ�, JSP�� HTML �ڵ带 �ۼ��ϴ� ���� �� ����.
		request.getRequestDispatcher("example.jsp").forward(request, response);
		
		// "forward" ����� �� ������ �̵�:
		// - ���� WAS�� ���� �� ���ø����̼� �ȿ����� �������� �̵��ϴ� ���.
		// - ���� ��û �ּҰ� �ٲ��� ����.
		// - request, response ��ü�� ������.
		// - �ٸ� WAS �Ǵ� �ٸ� �� ���ø����̼��� �������δ� ������(forward)�� �� ����!
		// 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
