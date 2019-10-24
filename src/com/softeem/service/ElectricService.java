package com.softeem.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softeem.bean.Electric;
import com.softeem.bean.Water;
import com.softeem.dao.ElectricDao;

public class ElectricService {
	private ElectricDao electricdao = new ElectricDao(Electric.class); 
	
	
	 public void deleteById(Integer electricId) throws SQLException{
			

		 electricdao.deleteById("delete from t_electric where id=?", electricId);
			
		}
	
	public int addelectric(Electric electric) throws SQLException{
		String sql = "insert into t_electric values(0,?,?,?,?,?)";
		Object [] params =
		{
				electric.getMonth(),
				electric.getPrice(),
				electric.getAmount(),
				electric.getPay(),
				electric.getDormId()
				};
		return electricdao.save(sql, params);
	}
	public Map findElectric(Integer pageNumber) throws SQLException{
		List<Electric> electriclist=electricdao.findElectric(pageNumber);
		
		int maxPage = electricdao.findElectricMaxpage();
		String pageCode = electricdao.genPagination("ElectricServlet.action", maxPage, pageNumber, "flag=findElectric");
		
		HashMap map = new HashMap();
		map.put("electriclist", electriclist);
		map.put("pageCode", pageCode);
		
		return map;
		}
	
	 public Electric findElectricById(Integer electricId) throws SQLException{
			

			return electricdao.findById("select * from t_electric where id=?",electricId);
			
		}
	 public int updateelectric(Electric electric) throws SQLException{
			return electricdao.updateelectric(electric);
		}
	 public Electric searchElectricById(Integer userdormId) throws SQLException{
			String sql="select * from t_electric where dormId=?";
			return electricdao.searchById(sql,userdormId);
			
		}
	 
	 public Map showElectricAll(Integer dormId,Integer pageNumber) throws SQLException{
			
			List<Electric> electriclist = electricdao.findElectricUser(dormId, pageNumber);
			
			int maxPage = electricdao.findElectricUserMaxPage(dormId);
			String pageCode = electricdao.genPagination("ElectricServlet.action", maxPage, pageNumber, "flag=list&id="+((dormId==null)?"":dormId));
			
			HashMap map = new HashMap();
			map.put("electriclist", electriclist);
			map.put("pageCode", pageCode);
			
			return map;
		}
}
