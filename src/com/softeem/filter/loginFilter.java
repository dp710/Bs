package com.softeem.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.bean.User;



public class loginFilter implements Filter {

	
	
	public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // ������Ϊs
		
	
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

     
        if(httpRequest.getRequestURI().contains("login.jsp") || httpRequest.getRequestURI().contains("image.jsp")){
        	// ��֤ͨ��������
            chain.doFilter(request, response);
        }else{
        
	        User user = (User) httpRequest.getSession().getAttribute("user");
	
	        // ���û�е�¼����ת����¼ҳ��
	        if (user == null) {
	        	httpRequest.setAttribute("error", "���ȵ�¼");
	            httpResponse.sendRedirect("login.jsp");
	        }
	        else{
	            // ��֤ͨ��������
	            chain.doFilter(request, response);
	        }
        }
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
