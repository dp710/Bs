package com.softeem.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.softeem.bean.Dormitory;
import com.softeem.bean.User;
import com.softeem.bean.Water;
import com.softeem.util.BaseDao;
import com.softeem.util.ConnectionContext;

public class WaterDao  extends BaseDao<Water>{

	public WaterDao(Class claxx) {
		super(claxx);
	}
	
	public List<Water> findWater(Integer pageNumber) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "select * from t_water   limit ? , ?" ;
		Object [] params = {(pageNumber-1)*pageCount , pageCount} ;
		list = query.query(conn, sql, params, new BeanListHandler<Water>(Water.class));
		return list;
	}
	
	public int findWaterMaxpage() throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "select count(*) from t_water" ;
		//最大记录数
		long count = query.query(conn, sql,new ScalarHandler<Long>());
		//最大页数
		return (int) Math.ceil(((double) count) / pageCount);
	}
	
	public int updatewater(Water water) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "update t_water set month=? , price=? ,weight=?,pay=?, dormId=? where id=?";
		Object [] params={water.getMonth(),water.getPrice(),water.getWeight(),water.getPay(),water.getDormId(),water.getId()};
		return query.update(conn, sql, params);
	}
	
	public Water searchById(String sql,Integer userdormId) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		return query.query(conn, sql, userdormId, new BeanHandler<Water>(Water.class));
		 
	}
	@SuppressWarnings("deprecation")
	public List<Water> findWaterUser( Integer dormId , Integer pageNumber)
						throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql= "select * from t_water where dormId=? limit ?,?";
		Object [] params={dormId,(pageNumber - 1) * pageCount, pageCount};
	    list = query.query(conn, sql, params, new BeanListHandler<Water>(Water.class));
		return list;
	}

	
	public int findWaterUserMaxPage(Integer dormId ) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql= "select count(*) from t_water  where dormId=? ";	
		
		long count = query.query(conn, sql, dormId, new ScalarHandler<Long>());
		return (int) Math.ceil(((double) count) / pageCount);
	}
}
