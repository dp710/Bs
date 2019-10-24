package com.softeem.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.bean.Dormitory;
import com.softeem.bean.User;
import com.softeem.service.DormitoryService;
import com.softeem.service.StudentService;
import com.softeem.service.UserService;
import com.softeem.util.BaseServlet;

public class DormitoryServlet extends BaseServlet {
	
	protected void findDormitory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		
	String pageNumber=request.getParameter("pageNumber");
	DormitoryService dormitoryservice=new DormitoryService();
	
	Map map= dormitoryservice.findDormitory(Integer.parseInt(pageNumber));
	request.setAttribute("dormitorylist", map.get("dormitorylist"));
	request.setAttribute("pageCode", map.get("pageCode"));
	request.getRequestDispatcher("dormitory_find.jsp").forward(request,response);	
	}
	
	protected void findDormitoryById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		
         String dormitoryId = request.getParameter("dormitoryId");
		
         DormitoryService dormitoryService = new DormitoryService() ;
		
         Dormitory dormitory=dormitoryService.findDormitoryById(Integer.parseInt(dormitoryId));
		request.setAttribute("dormitory", dormitory);
		
		request.getRequestDispatcher("dormitory_update.jsp").forward(request, response);
	}
	
	 protected void updatedormitory(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {
	    	String id = request.getParameter("id");
	    	String tel = request.getParameter("tel");
	    	String number = request.getParameter("number");
	    	String remarks=request.getParameter("remarks");
	    	Dormitory dormitory=new Dormitory(Integer.parseInt(id),tel,Integer.parseInt(number),remarks);
	    	
	    	DormitoryService dormitoryservice=new DormitoryService();
	    	dormitoryservice.updatedormitory(dormitory);
	    	request.getRequestDispatcher("DormitoryServlet.action?flag=findDormitory&pageNumber=1").forward(request, response);
	    	
	    }
	 protected void list(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException,
				NumberFormatException, SQLException {
			
			
			String pageNumber = request.getParameter("pageNumber");
			String number = request.getParameter("number");
			String id = request.getParameter("id");
			
			
			
			DormitoryService dormitoryService = new DormitoryService();
			Map map = dormitoryService.showDormitoryAll(
					number==null||"".equals(number)? null:Integer.parseInt(number),
					id==null||"".equals(id)? null:Integer.parseInt(id), 
					Integer.parseInt(pageNumber));
			
			request.setAttribute("dormitorylist", map.get("dormitorylist"));
			request.setAttribute("pageCode", map.get("pageCode"));
			request.setAttribute("mainPage", "dormitory.jsp");
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("number", number);
			request.setAttribute("id", id);
			
			//System.out.println(((List)map.get("dormitoryList")).size());
			
			//request.getRequestDispatcher("DormitoryServlet.action?flag=findDormitory&pageNumber=1").forward(request,response);
			request.getRequestDispatcher("dormitory_find.jsp").forward(request,response);
			
		}
	
}
