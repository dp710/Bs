package com.softeem.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.bean.User;
import com.softeem.bean.Water;
import com.softeem.service.DormitoryService;
import com.softeem.service.StudentService;
import com.softeem.service.WaterService;
import com.softeem.util.BaseServlet;

public class WaterServlet extends BaseServlet {
	
	protected void deleteWater(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NumberFormatException, SQLException {
		
		String waterId = request.getParameter("waterId");
		
		WaterService waterService = new WaterService() ;
		
		waterService.deleteById(Integer.parseInt(waterId));
		
		request.getRequestDispatcher("WaterServlet.action?flag=findWater&pageNumber=1").forward(request, response);
	}
 
	protected void addwater(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, SQLException {
		String month=request.getParameter("month");
		String price=request.getParameter("price");
		String weight=request.getParameter("weight");
		String pay=request.getParameter("pay");
		String dormId=request.getParameter("dormId");
		
		Water water=new Water(Timestamp.valueOf(month),Double.parseDouble(price),Double.parseDouble(weight),Double.parseDouble(pay),Integer.parseInt(dormId));
	    
		WaterService waterservice=new WaterService();
		waterservice.addwater(water);
		response.sendRedirect("introduce.jsp");
	}
	
	protected void findWater(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		
	String pageNumber=request.getParameter("pageNumber");
	WaterService waterservice=new WaterService();
	
	Map map= waterservice.findWater(Integer.parseInt(pageNumber));
	request.setAttribute("waterlist", map.get("waterlist"));
	request.setAttribute("pageCode", map.get("pageCode"));
	request.getRequestDispatcher("water_find.jsp").forward(request,response);	
	}
	
	protected void findWaterById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		
         String waterId = request.getParameter("waterId");
		
         WaterService waterService = new WaterService() ;
         Water water=waterService.findWaterById(Integer.parseInt(waterId));
		 request.setAttribute("water", water);
		
		 request.getRequestDispatcher("water_update.jsp").forward(request, response);
	}
	
	 protected void updatewater(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {
	    	String id = request.getParameter("id");
	    	String month = request.getParameter("month");
	    	String price=request.getParameter("price");
	    	String weight=request.getParameter("weight");
	    	String pay=request.getParameter("pay");
	    	String dormId = request.getParameter("dormId");
	    	
	    	
	    	Water water=new Water(Integer.parseInt(id),Timestamp.valueOf(month),Double.parseDouble(price),Double.parseDouble(weight),Double.parseDouble(pay),Integer.parseInt(dormId));
	    	
	    	WaterService waterservice=new WaterService();
	    	waterservice.updatewater(water);
	    	request.getRequestDispatcher("WaterServlet.action?flag=findWater&pageNumber=1").forward(request, response);
	    	
	    }
	 
	 protected void searchWaterById(HttpServletRequest request,HttpServletResponse response)
	     throws ServletException, IOException, SQLException {
			
	         String userdormId = request.getParameter("userdormId");
			
	         WaterService waterservice = new WaterService() ;
	         Water water=waterservice.searchWaterById(Integer.parseInt(userdormId));
			 request.setAttribute("water", water);
			
			 request.getRequestDispatcher("searchwater.jsp").forward(request, response);
		}
	 
	 
	 protected void list(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException,
				NumberFormatException, SQLException {
			
			
			String pageNumber = request.getParameter("pageNumber");
			String dormId = request.getParameter("dormId");
			
			

			
			WaterService waterService = new WaterService();
			Map map = waterService.showWaterAll(
					dormId==null||"".equals(dormId)? null:Integer.parseInt(dormId), 
					Integer.parseInt(pageNumber));
			
			request.setAttribute("waterlist", map.get("waterlist"));
			request.setAttribute("pageCode", map.get("pageCode"));
			request.setAttribute("mainPage", "water.jsp");
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("dormId", dormId);
			
			//System.out.println(((List)map.get("dormitoryList")).size());
			
			//request.getRequestDispatcher("DormitoryServlet.action?flag=findDormitory&pageNumber=1").forward(request,response);
			request.getRequestDispatcher("water_find.jsp").forward(request,response);
			
		}
}
