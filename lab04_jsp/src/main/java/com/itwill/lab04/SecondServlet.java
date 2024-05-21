package com.itwill.lab04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class SecondServlet
 */
// ���� URL(��û �ּ�) ���� ���:
// 1. web.xml(���� ������, deployment descriptor)���� <servlet>, <servlet-mapping>�� �����ϰų�,
// 2. ���� Ŭ�������� @WebServlet �ֳ����̼����� ����.
// (����) web.xml �Ǵ� �ֳ����̼� �� �� �ϳ��� ����.
@WebServlet(name = "secondServlet", urlPatterns = { "/ex2" })
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		System.out.println("SecondServlet::doGet() �޼��� ȣ��");
		
		// WAS�� Ŭ���̾�Ʈ�� ������ ����Ʈ Ÿ�� ����:
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.append("<html>")
		    .append("<body>")
		    .append("<h1>�ι�° Servlet</h1>")
		    .append("<a href='/lab04'>�ε��� ������</a>")
		    .append("</body>")
		    .append("</html>");
		
	}

}