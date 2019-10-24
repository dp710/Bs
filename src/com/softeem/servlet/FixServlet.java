package com.softeem.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.bean.Fix;
import com.softeem.bean.Water;
import com.softeem.service.FixService;
import com.softeem.service.WaterService;
import com.softeem.util.BaseServlet;

public class FixServlet extends BaseServlet {
	
	
	protected void addfix(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, SQLException {
		String goods=request.getParameter("goods");
		String date=request.getParameter("date");
		String remarks=request.getParameter("remarks");
		String dormId=request.getParameter("dormId");
		
		Fix fix=new Fix(goods,Timestamp.valueOf(date),remarks,Integer.parseInt(dormId));
	    
		FixService fixservice=new FixService();
		fixservice.addfix(fix);
		response.sendRedirect("introduce1.jsp");
	}
	
	protected void findFix(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		
	String pageNumber=request.getParameter("pageNumber");
	FixService fixservice=new FixService();
	
	Map map= fixservice.findFix(Integer.parseInt(pageNumber));
	request.setAttribute("fixlist", map.get("fixlist"));
	request.setAttribute("pageCode", map.get("pageCode"));
	request.getRequestDispatcher("fix_find.jsp").forward(request,response);	
	}
   
	protected void deleteFix(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NumberFormatException, SQLException {
		
		String fixId = request.getParameter("fixId");
		
		FixService fixService = new FixService() ;
		
		fixService.deleteById(Integer.parseInt(fixId));
		
		request.getRequestDispatcher("FixServlet.action?flag=findFix&pageNumber=1").forward(request, response);
	}
	
	 protected void list(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException,
				NumberFormatException, SQLException {
			
			
			String pageNumber = request.getParameter("pageNumber");
			String dormId = request.getParameter("dormId");
			
			
			
			
			FixService fixService = new FixService();
			Map map = fixService.showFixAll(
					dormId==null||"".equals(dormId)? null:Integer.parseInt(dormId), 
					Integer.parseInt(pageNumber));
			
			request.setAttribute("fixlist", map.get("fixlist"));
			request.setAttribute("pageCode", map.get("pageCode"));
			request.setAttribute("mainPage", "fix.jsp");
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("dormId", dormId);
			
			//System.out.println(((List)map.get("dormitoryList")).size());
			
			//request.getRequestDispatcher("DormitoryServlet.action?flag=findDormitory&pageNumber=1").forward(request,response);
			request.getRequestDispatcher("fix_find.jsp").forward(request,response);
			
		}
	
}
