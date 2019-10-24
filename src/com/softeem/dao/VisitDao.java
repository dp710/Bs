package com.softeem.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.softeem.bean.Notice;
import com.softeem.bean.Visit;
import com.softeem.bean.Water;
import com.softeem.util.BaseDao;
import com.softeem.util.ConnectionContext;

public class VisitDao extends BaseDao<Visit> {

	public VisitDao(Class claxx) {
		super(claxx);
		
	}
	public List<Visit> findVisit(Integer pageNumber) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "select * from t_visit limit ?,?";
		Object [] params = {(pageNumber-1)*pageCount , pageCount} ;
		list = query.query(conn, sql, params, new BeanListHandler<Visit>(Visit.class));
		return list;
	}
	
	public int findVisitMaxpage() throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "select count(*) from t_visit" ;
		//最大记录数
		long count = query.query(conn, sql,new ScalarHandler<Long>());
		//最大页数
		return (int) Math.ceil(((double) count) / pageCount);
	}
	public int updatevisit(Visit visit) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "update t_visit set lname=? , userId=? ,stime=?,etime=?, idcard=? where id=?";
		Object [] params={visit.getLname(),visit.getUserId(),visit.getStime(),visit.getEtime(),visit.getIdcard(),visit.getId()};
		return query.update(conn, sql, params);
	}
}
