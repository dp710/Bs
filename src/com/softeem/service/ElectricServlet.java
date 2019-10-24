package com.softeem.service;


import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.bean.Electric;
import com.softeem.bean.Water;
import com.softeem.util.BaseServlet;

public class ElectricServlet extends BaseServlet {
	
	protected void deleteElectric(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NumberFormatException, SQLException {
		
		String electricId = request.getParameter("electricId");
		
		ElectricService electricService = new ElectricService() ;
		
		electricService.deleteById(Integer.parseInt(electricId));
		
		request.getRequestDispatcher("ElectricServlet.action?flag=findElectric&pageNumber=1").forward(request, response);
	}
	
	protected void addelectric(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, SQLException {
		String month=request.getParameter("month");
		String price=request.getParameter("price");
		String amount=request.getParameter("amount");
		String pay=request.getParameter("pay");
		String dormId=request.getParameter("dormId");
		
		Electric electric=new Electric(Timestamp.valueOf(month),Double.parseDouble(price),Double.parseDouble(amount),Double.parseDouble(pay),Integer.parseInt(dormId));
	    
		ElectricService electricservice=new ElectricService();
		electricservice.addelectric(electric);
		response.sendRedirect("introduce.jsp");
	}
	protected void findElectric(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		
	String pageNumber=request.getParameter("pageNumber");
	ElectricService electricservice=new ElectricService();
	
	Map map= electricservice.findElectric(Integer.parseInt(pageNumber));
	request.setAttribute("electriclist", map.get("electriclist"));
	request.setAttribute("pageCode", map.get("pageCode"));
	request.getRequestDispatcher("electric_find.jsp").forward(request,response);	
	}
	protected void findElectricById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		
         String electricId = request.getParameter("electricId");
		
         ElectricService electricService = new ElectricService() ;
         Electric electric=electricService.findElectricById(Integer.parseInt(electricId));
		 request.setAttribute("electric", electric);
		
		 request.getRequestDispatcher("electric_update.jsp").forward(request, response);
	}
	
	 protected void updateelectric(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {
	    	String id = request.getParameter("id");
	    	String month = request.getParameter("month");
	    	String price=request.getParameter("price");
	    	String amount=request.getParameter("amount");
	    	String pay=request.getParameter("pay");
	    	String dormId = request.getParameter("dormId");
	    	
	    	
	    	Electric electric=new Electric(Integer.parseInt(id),Timestamp.valueOf(month),Double.parseDouble(price),Double.parseDouble(amount),Double.parseDouble(pay),Integer.parseInt(dormId));
	    	
	    	ElectricService electricservice=new ElectricService();
	    	electricservice.updateelectric(electric);
	    	request.getRequestDispatcher("ElectricServlet.action?flag=findElectric&pageNumber=1").forward(request, response);
	    	
	    }
	 protected void searchElectricById(HttpServletRequest request,HttpServletResponse response)
     throws ServletException, IOException, SQLException {
		
         String userdormId = request.getParameter("userdormId");
		
         ElectricService electricservice = new ElectricService() ;
         Electric electric=electricservice.searchElectricById(Integer.parseInt(userdormId));
		 request.setAttribute("electric", electric);
		
		 request.getRequestDispatcher("searchelectric.jsp").forward(request, response);
	}
	 
	 protected void list(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException,
				NumberFormatException, SQLException {
			
			
			String pageNumber = request.getParameter("pageNumber");
			String dormId = request.getParameter("dormId");
			
			System.out.println(dormId);
			System.out.println(pageNumber);
			
			ElectricService electricService = new ElectricService();
			Map map = electricService.showElectricAll(
					dormId==null||"".equals(dormId)? null:Integer.parseInt(dormId), 
					Integer.parseInt(pageNumber));
			
			request.setAttribute("electriclist", map.get("electriclist"));
			request.setAttribute("pageCode", map.get("pageCode"));
			request.setAttribute("mainPage", "electric.jsp");
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("dormId", dormId);
			
			//System.out.println(((List)map.get("dormitoryList")).size());
			
			//request.getRequestDispatcher("DormitoryServlet.action?flag=findDormitory&pageNumber=1").forward(request,response);
			request.getRequestDispatcher("electric_find.jsp").forward(request,response);
			
		}
}
