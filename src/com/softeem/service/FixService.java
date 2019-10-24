package com.softeem.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softeem.bean.Fix;
import com.softeem.bean.Water;
import com.softeem.dao.FixDao;

public class FixService {

	private FixDao fixdao = new FixDao(Fix.class); 
	
	public int addfix(Fix fix) throws SQLException{
		String sql = "insert into t_fix values(0,?,?,?,?)";
		Object [] params =
		{
           fix.getGoods(),
           fix.getDate(),
           fix.getRemarks(),
           fix.getDormId()
				};
		return fixdao.save(sql, params);
	}
	
	public Map findFix(Integer pageNumber) throws SQLException{
		List<Fix> fixlist=fixdao.findFix(pageNumber);
		
		int maxPage = fixdao.findFixMaxpage();
		String pageCode = fixdao.genPagination("FixServlet.action", maxPage, pageNumber, "flag=findFix");
		
		HashMap map = new HashMap();
		map.put("fixlist", fixlist);
		map.put("pageCode", pageCode);
		
		return map;
		}
	
  public void deleteById(Integer fixId) throws SQLException{
		

		fixdao.deleteById("delete from t_fix where id=?", fixId);
		
	}
  public Map showFixAll(Integer dormId,Integer pageNumber) throws SQLException{
		
		List<Fix> fixlist = fixdao.findFixUser(dormId, pageNumber);
		
		int maxPage = fixdao.findFixUserMaxPage(dormId);
		String pageCode = fixdao.genPagination("FixServlet.action", maxPage, pageNumber, "flag=list&id="+((dormId==null)?"":dormId));
		
		HashMap map = new HashMap();
		map.put("fixlist", fixlist);
		map.put("pageCode", pageCode);
		
		return map;
	}
}
