package com.softeem.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softeem.bean.Dormitory;
import com.softeem.bean.User;
import com.softeem.dao.DormitoryDao;

public class DormitoryService {
  
	private DormitoryDao dormitorydao=new DormitoryDao(Dormitory.class);
	
	public Map findDormitory(Integer pageNumber) throws SQLException{
	List<Dormitory> dormitorylist=dormitorydao.findDormitory(pageNumber);
	
	int maxPage = dormitorydao.findDormitoryMaxpage();
	String pageCode = dormitorydao.genPagination("DormitoryServlet.action", maxPage, pageNumber, "flag=findDormitory");
	
	HashMap map = new HashMap();
	map.put("dormitorylist", dormitorylist);
	map.put("pageCode", pageCode);
	
	return map;
	}
	
	 public Dormitory findDormitoryById(Integer dormitoryId) throws SQLException{
			return dormitorydao.findById("select * from t_dormitory where id=?", dormitoryId);
			
		}
	 
	 public int updatedormitory(Dormitory dormitory) throws SQLException{
			return dormitorydao.updatedormitory(dormitory);
		}
	 
		public Map showDormitoryAll( Integer number,Integer id,Integer pageNumber) throws SQLException{
			
			List<Dormitory> dormitorylist = dormitorydao.findDormitoryUser(number,id, pageNumber);
			
			int maxPage = dormitorydao.findDormitoryUserMaxPage(number,id);
			String pageCode = dormitorydao.genPagination("DormitoryServlet.action", maxPage, pageNumber, "flag=list&number="+((number==null)?"":number)+"&id="+((id==null)?"":id));
			
			HashMap map = new HashMap();
			map.put("dormitorylist", dormitorylist);
			map.put("pageCode", pageCode);
			
			return map;
		}
		
}
