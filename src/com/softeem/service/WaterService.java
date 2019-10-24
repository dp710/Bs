package com.softeem.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softeem.bean.Dormitory;
import com.softeem.bean.User;
import com.softeem.bean.Water;
import com.softeem.dao.DormitoryDao;
import com.softeem.dao.WaterDao;


public class WaterService {
	private WaterDao waterdao = new WaterDao(Water.class); 
	
	
	 public void deleteById(Integer waterId) throws SQLException{
			

			waterdao.deleteById("delete from t_water where id=?", waterId);
			
		}
	
	public int addwater(Water water) throws SQLException{
		String sql = "insert into t_water values(0,?,?,?,?,?)";
		Object [] params =
		{
           water.getMonth(),
		   water.getPrice(),
		   water.getWeight(),
		   water.getPay(),
		   water.getDormId()
				};
		return waterdao.save(sql, params);
	}
	
	public Map findWater(Integer pageNumber) throws SQLException{
	List<Water> waterlist=waterdao.findWater(pageNumber);
	
	int maxPage = waterdao.findWaterMaxpage();
	String pageCode = waterdao.genPagination("WaterServlet.action", maxPage, pageNumber, "flag=findWater");
	
	HashMap map = new HashMap();
	map.put("waterlist", waterlist);
	map.put("pageCode", pageCode);
	
	return map;
	}
	
	 public Water findWaterById(Integer waterId) throws SQLException{
			

			return waterdao.findById("select * from t_water where id=?",waterId);
			
		}
	 public int updatewater(Water water) throws SQLException{
			return waterdao.updatewater(water);
		}
	 
	 public Water searchWaterById(Integer userdormId) throws SQLException{
			String sql="select * from t_water where dormId=?";
			return waterdao.searchById(sql,userdormId);
			
		}
	 
	 
	 
	 public Map showWaterAll(Integer dormId,Integer pageNumber) throws SQLException{
			
			List<Water> waterlist = waterdao.findWaterUser(dormId, pageNumber);
			
			int maxPage = waterdao.findWaterUserMaxPage(dormId);
			String pageCode = waterdao.genPagination("WaterServlet.action", maxPage, pageNumber, "flag=list&id="+((dormId==null)?"":dormId));
			
			HashMap map = new HashMap();
			map.put("waterlist", waterlist);
			map.put("pageCode", pageCode);
			
			return map;
		}
}
