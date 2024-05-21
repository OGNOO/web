package com.itwill.lab04;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class FirstServlet
 */
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        System.out.println("FirstServlet ����...");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // WAS(Web Application Server): �� ��û(request)/����(response)�� ó���ϴ� ���α׷�. Tomcat.
    // doGet(): get ����� ��û�� ���� �� WAS�� ȣ���ϴ� �޼���.
    // doPost(): post ����� ��û�� ���� �� WAS�� ȣ���ϴ� �޼���.
    // �Ķ���� request: Ŭ���̾�Ʈ�� ������ ���� ��û�� ����, ��ɵ��� ���� ��ü.
    // �Ķ���� response: ������ Ŭ���̾�Ʈ�� ���� ������ ����, ��ɵ��� ���� ��ü.
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
        System.out.println("FirstServlet::doGet() ȣ��");
		
        // WAS�� Ŭ���̾�Ʈ�� ������ ����(response)�� ����Ʈ Ÿ��(���� ����/���ڵ�)�� ����:
        // ���������� �ѱ��� ������ �ʵ��� �ϱ� ���ؼ�.
        response.setContentType("text/html; charset=UTF-8");
        
        response.getWriter()
		    .append("<!doctype html>")
		    .append("<html>")
		    .append("<head>")
		    .append("<meta charset='UTF-8' />")
		    .append("<title>Servlet</title>")
		    .append("</head>")
		    .append("<body>")
		    .append("<h1>ù��° Servlet</h1>")
		    .append("<a href='/lab04'>�ε��� ������</a>")
		    .append("</body>")
		    .append("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		System.out.println("FirstServlet::doPost() ȣ��");
        doGet(request, response);
	}

}