package com.softeem.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseServlet extends HttpServlet{
	
	protected PrintWriter out ;
	protected HttpSession session ;
	protected ServletContext application ;
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		out = response.getWriter();
		session = request.getSession() ;
		application = this.getServletContext() ;
		
		String flag = request.getParameter("flag");
	
		Class claxx = this.getClass();//��ǰ�����������
		
		try{
			Method method = claxx.getDeclaredMethod(flag, HttpServletRequest.class ,HttpServletResponse.class );
			
			method.setAccessible(true);//ȡ�����ʼ��
			
			method.invoke(this, request,response);
		}catch (Exception e) {
			e.printStackTrace();
			////ע�⣺����service��ķ������쳣֮�󣬼������쳣�׳���
			//������TransactionFilter���ܲ����׳����쳣���̶�ִ������ع�����
			throw new RuntimeException(e);
		}


		
	}
}
