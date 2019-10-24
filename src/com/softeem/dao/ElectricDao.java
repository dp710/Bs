package com.softeem.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.softeem.bean.Electric;
import com.softeem.bean.Water;
import com.softeem.util.BaseDao;
import com.softeem.util.ConnectionContext;

public class ElectricDao extends BaseDao<Electric>{

	public ElectricDao(Class claxx) {
		super(claxx);
		
	}
	public List<Electric> findElectric(Integer pageNumber) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "select * from t_electric   limit ? , ?" ;
		Object [] params = {(pageNumber-1)*pageCount , pageCount} ;
		list = query.query(conn, sql, params, new BeanListHandler<Electric>(Electric.class));
		return list;
	}
	
	public int findElectricMaxpage() throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "select count(*) from t_electric" ;
		//最大记录数
		long count = query.query(conn, sql,new ScalarHandler<Long>());
		//最大页数
		return (int) Math.ceil(((double) count) / pageCount);
	}
	public int updateelectric(Electric electric) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "update t_electric set month=? , price=? ,amount=?,pay=?, dormId=? where id=?";
		Object [] params={electric.getMonth(),electric.getPrice(),electric.getAmount(),electric.getPay(),electric.getDormId(),electric.getId()};
		return query.update(conn, sql, params);
	}
	public Electric searchById(String sql,Integer userdormId) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		return query.query(conn, sql, userdormId, new BeanHandler<Electric>(Electric.class));
		 
	}
	
	@SuppressWarnings("deprecation")
	public List<Electric> findElectricUser( Integer dormId , Integer pageNumber)
						throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql= "select * from t_electric where dormId=? limit ?,?";
		Object [] params={dormId,(pageNumber - 1) * pageCount, pageCount};
	    list = query.query(conn, sql, params, new BeanListHandler<Electric>(Electric.class));
		return list;
	}

	
	public int findElectricUserMaxPage(Integer dormId ) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql= "select count(*) from t_electric  where dormId=? ";	
		
		long count = query.query(conn, sql, dormId, new ScalarHandler<Long>());
		return (int) Math.ceil(((double) count) / pageCount);
	}
}
