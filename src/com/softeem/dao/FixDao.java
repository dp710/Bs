package com.softeem.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.softeem.bean.Fix;
import com.softeem.bean.Visit;
import com.softeem.bean.Water;
import com.softeem.util.BaseDao;
import com.softeem.util.ConnectionContext;

public class FixDao extends BaseDao<Fix>{

	public FixDao(Class claxx) {
		super(claxx);
	}
	public List<Fix> findFix(Integer pageNumber) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "select * from t_fix limit ?,?";
		Object [] params = {(pageNumber-1)*pageCount , pageCount} ;
		list = query.query(conn, sql, params, new BeanListHandler<Fix>(Fix.class));
		return list;
	}
	
	public int findFixMaxpage() throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "select count(*) from t_fix" ;
		//最大记录数
		long count = query.query(conn, sql,new ScalarHandler<Long>());
		//最大页数
		return (int) Math.ceil(((double) count) / pageCount);
	}
	
	@SuppressWarnings("deprecation")
	public List<Fix> findFixUser( Integer dormId , Integer pageNumber)
						throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql= "select * from t_fix where dormId=? limit ?,?";
		Object [] params={dormId,(pageNumber - 1) * pageCount, pageCount};
	    list = query.query(conn, sql, params, new BeanListHandler<Fix>(Fix.class));
		return list;
	}

	
	public int findFixUserMaxPage(Integer dormId ) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql= "select count(*) from t_fix  where dormId=? ";	
		
		long count = query.query(conn, sql, dormId, new ScalarHandler<Long>());
		return (int) Math.ceil(((double) count) / pageCount);
	}
	
	
}
