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
	
		Class claxx = this.getClass();//当前类的描述对象
		
		try{
			Method method = claxx.getDeclaredMethod(flag, HttpServletRequest.class ,HttpServletResponse.class );
			
			method.setAccessible(true);//取消访问检查
			
			method.invoke(this, request,response);
		}catch (Exception e) {
			e.printStackTrace();
			////注意：调用service层的方法出异常之后，继续将异常抛出，
			//这样在TransactionFilter就能捕获到抛出的异常，继而执行事务回滚操作
			throw new RuntimeException(e);
		}


		
	}
}
