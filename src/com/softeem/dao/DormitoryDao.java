package com.softeem.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.softeem.bean.Dormitory;
import com.softeem.bean.User;
import com.softeem.util.BaseDao;
import com.softeem.util.ConnectionContext;

public class DormitoryDao extends BaseDao<Dormitory> {

	public DormitoryDao(Class claxx) {
		super(claxx);	
	}
	public List<Dormitory> findDormitory(Integer pageNumber) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "select * from t_dormitory   limit ? , ?" ;
		Object [] params = {(pageNumber-1)*pageCount , pageCount} ;
		list = query.query(conn, sql, params, new BeanListHandler<Dormitory>(Dormitory.class));
		return list;
	}
	
	public int findDormitoryMaxpage() throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "select count(*) from t_dormitory" ;
		//最大记录数
		long count = query.query(conn, sql,new ScalarHandler<Long>());
		//最大页数
		return (int) Math.ceil(((double) count) / pageCount);
	}
	
	public int updatedormitory(Dormitory dormitory) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "update t_dormitory set tel=?,number=?,remarks=? where id=?";
		Object [] params={dormitory.getTel(),dormitory.getNumber(),dormitory.getRemarks(),dormitory.getId()};
		return query.update(conn, sql, params);
	}
	
	@SuppressWarnings("deprecation")
	public List<Dormitory> findDormitoryUser( Integer number,Integer id , Integer pageNumber)
						throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		List params = new ArrayList();
		StringBuilder sql= new StringBuilder("select * from t_dormitory where 1=1");
		
		if(number==1){
			sql.append(" and number = 4");
		}
		if(number==0){
			sql.append(" and number < 4");
		}
		if(number==-1){
			sql.append(" and 1=1");
		}
		if(id!=null && !id.equals(-1)){
		sql.append(" and id=?  order by id desc ");
		params.add(id);
		
		}else
		{
		sql.append(" limit ?,? ");
		params.add((pageNumber-1)*pageCount);
		params.add(pageCount);
		}
		
		
		
		list = query.query(conn, sql.toString(), params.toArray(), new BeanListHandler<Dormitory>(Dormitory.class));
		return list;
	}

	
	public int findDormitoryUserMaxPage(Integer number,Integer id ) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		List params = new ArrayList();//因为不知道要传的参数有多少,所以只能定义为集合形式
		
		StringBuilder sql= new StringBuilder("select count(*) from t_dormitory  where 1=1 ");	
		if(number==1){
			sql.append(" and number = 4");
		}
		if(number==0){
			sql.append(" and number < 4");
		}
		if(number==-1){
			sql.append(" and 1=1");
		}
		
		if(id!=null && !id.equals(-1)){
			sql.append(" and id=? ");
			params.add(id);
			}
		
		long count = query.query(conn, sql.toString(), params.toArray(), new ScalarHandler<Long>());//由于params需要给id追加值,
		                                                                                            //只能定义为集合但是query方法中要
		                                                                                            // 将params转化成object对象的数组
		return (int) Math.ceil(((double) count) / pageCount);
	}

}
