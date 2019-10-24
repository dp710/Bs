package com.softeem.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softeem.bean.Notice;
import com.softeem.bean.Visit;
import com.softeem.dao.VisitDao;

public class VisitService {
	
  private VisitDao visitdao = new VisitDao(Visit.class); 
  
  public void deleteById(Integer visitId) throws SQLException{
		

	visitdao.deleteById("delete from t_visit where id=?", visitId);
		
	}

	
	public int addvisit(Visit visit) throws SQLException{
		String sql = "insert into t_visit values(0,?,?,?,?,?)";
		Object [] params =
		{
         visit.getLname(),
         visit.getUserId(),
         visit.getStime(),
         visit.getEtime(),
         visit.getIdcard()
				};
		return visitdao.save(sql, params);
	}
	public Map findVisit(Integer pageNumber) throws SQLException{
		List<Visit> visitlist=visitdao.findVisit(pageNumber);
		
		int maxPage = visitdao.findVisitMaxpage();
		String pageCode = visitdao.genPagination("VisitServlet.action", maxPage, pageNumber, "flag=findVisit");
		
		HashMap map = new HashMap();
		map.put("visitlist", visitlist);
		map.put("pageCode", pageCode);
		
		return map;
		}
	 
	 public Visit findVisitById(Integer visitId) throws SQLException{
			

			return visitdao.findById("select * from t_visit where id=?",visitId);
			
		}
	 public int updatevisit(Visit visit) throws SQLException{
			return visitdao.updatevisit(visit);
		}
}
