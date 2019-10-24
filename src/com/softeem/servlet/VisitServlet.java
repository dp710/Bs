package com.softeem.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.bean.Notice;
import com.softeem.bean.Visit;
import com.softeem.service.NoticeService;
import com.softeem.service.VisitService;
import com.softeem.service.WaterService;
import com.softeem.util.BaseServlet;

public class VisitServlet extends BaseServlet {
	
	
	protected void deleteVisit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NumberFormatException, SQLException {
		
		String visitId = request.getParameter("visitId");
		
		VisitService visitService = new VisitService() ;
		
		visitService.deleteById(Integer.parseInt(visitId));
		
		request.getRequestDispatcher("VisitServlet.action?flag=findVisit&pageNumber=1").forward(request, response);
	}
	
	protected void addvisit(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, SQLException {
		String lname=request.getParameter("lname");
		String userId=request.getParameter("userId");
		String stime=request.getParameter("stime");
		String etime=request.getParameter("etime");
		String idcard=request.getParameter("idcard");
		
		Visit visit=new Visit(lname, Integer.parseInt(userId), Timestamp.valueOf(stime), Timestamp.valueOf(etime), idcard);
	   VisitService visitservice=new VisitService();
	   visitservice.addvisit(visit);
	   response.sendRedirect("introduce.jsp");
	}
	
	protected void findVisit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		
	String pageNumber=request.getParameter("pageNumber");
	VisitService visitservice=new VisitService();
	
	Map map= visitservice.findVisit(Integer.parseInt(pageNumber));
	request.setAttribute("visitlist", map.get("visitlist"));
	request.setAttribute("pageCode", map.get("pageCode"));
	request.getRequestDispatcher("visit_find.jsp").forward(request,response);	
	}
	
	protected void findVisitById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		
         String visitId = request.getParameter("visitId");
		
         VisitService visitService = new VisitService() ;
         Visit visit=visitService.findVisitById(Integer.parseInt(visitId));
		 request.setAttribute("visit", visit);
		
		 request.getRequestDispatcher("visit_update.jsp").forward(request, response);
	}
	
	 protected void updatevisit(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {
	    	String id = request.getParameter("id");
	    	String lname = request.getParameter("lname");
	    	String userId=request.getParameter("userId");
	    	String stime=request.getParameter("stime");
	    	String etime=request.getParameter("etime");
	    	String idcard=request.getParameter("idcard");
	    	
	    	
	    	Visit visit=new Visit(Integer.parseInt(id),lname,Integer.parseInt(userId),Timestamp.valueOf(stime),Timestamp.valueOf(etime),idcard);
	    	
	    	VisitService visitservice=new VisitService();
	    	visitservice.updatevisit(visit);  	
	    	request.getRequestDispatcher("VisitServlet.action?flag=findVisit&pageNumber=1").forward(request, response);
	    	
	    }
	
}
