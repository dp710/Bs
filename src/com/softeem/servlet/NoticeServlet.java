package com.softeem.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.bean.Notice;
import com.softeem.bean.Water;
import com.softeem.service.DormitoryService;
import com.softeem.service.NoticeService;
import com.softeem.service.WaterService;
import com.softeem.util.BaseServlet;

public class NoticeServlet extends BaseServlet {
  
	protected void addnotice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		    String title=request.getParameter("title");
		    String content=request.getParameter("content");
		    String publishTime=request.getParameter("publishTime");
		    String userId=request.getParameter("userId");
		    
		   Notice notice=new Notice(title,content,Timestamp.valueOf(publishTime),Integer.parseInt(userId));
	       NoticeService noticeservice=new NoticeService();
	       noticeservice.addnotice(notice);
	       response.sendRedirect("introduce.jsp");
	}
	protected void findNotice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		
	String pageNumber=request.getParameter("pageNumber");
	NoticeService noticeservice=new NoticeService();
	
	Map map= noticeservice.findNotice(Integer.parseInt(pageNumber));
	request.setAttribute("noticelist", map.get("noticelist"));
	request.setAttribute("pageCode", map.get("pageCode"));
	request.getRequestDispatcher("notice_find.jsp").forward(request,response);	
	}
	
	protected void deleteNotice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NumberFormatException, SQLException {
		
		String noticeId = request.getParameter("noticeId");
		
		NoticeService noticeService = new NoticeService() ;
		
		noticeService.deleteById(Integer.parseInt(noticeId));
		
		request.getRequestDispatcher("NoticeServlet.action?flag=findNotice&pageNumber=1").forward(request, response);
	}
	
	protected void findNoticeById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		
         String noticeId = request.getParameter("noticeId");
		
         NoticeService noticeService = new NoticeService() ;
         Notice notice=noticeService.findNoticeById(Integer.parseInt(noticeId));
		 request.setAttribute("notice", notice);
		
		 request.getRequestDispatcher("notice_update.jsp").forward(request, response);
	}
	
	 protected void updatenotice(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {
	    	String id = request.getParameter("id");
	    	String title = request.getParameter("title");
	    	String publishTime=request.getParameter("publishTime");
	    	String userId=request.getParameter("userId");
	    	String content=request.getParameter("content");
	    	
	    	
	    	Notice notice=new Notice(Integer.parseInt(id),title,content,Timestamp.valueOf(publishTime),Integer.parseInt(userId));
	    	
	    	NoticeService noticeservice=new NoticeService();
	    	noticeservice.updatenotice(notice);
	    	request.getRequestDispatcher("NoticeServlet.action?flag=findNotice&pageNumber=1").forward(request, response);
	    	
	    }
	 
	 protected void list(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException,
				NumberFormatException, SQLException {
			
			
			String pageNumber = request.getParameter("pageNumber");
			String userId = request.getParameter("userId");
			
			NoticeService noticeService = new NoticeService();
			Map map =noticeService.showNoticeAll(
					userId==null||"".equals(userId)? null:Integer.parseInt(userId), 
					Integer.parseInt(pageNumber));
			
			request.setAttribute("noticelist", map.get("noticelist"));
			request.setAttribute("pageCode", map.get("pageCode"));
			request.setAttribute("mainPage", "notice.jsp");
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("userId", userId);
			
			//System.out.println(((List)map.get("dormitoryList")).size());
			
			//request.getRequestDispatcher("DormitoryServlet.action?flag=findDormitory&pageNumber=1").forward(request,response);
			request.getRequestDispatcher("notice_find.jsp").forward(request,response);
			
		}
}
