package com.softeem.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.softeem.bean.Dormitory;
import com.softeem.bean.Notice;
import com.softeem.bean.Water;
import com.softeem.util.BaseDao;
import com.softeem.util.ConnectionContext;

public class NoticeDao extends BaseDao<Notice> {

	public NoticeDao(Class claxx) {
		super(claxx);
		
	}
	public List<Notice> findNotice(Integer pageNumber) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "select * from t_notice order by id desc  limit ? , ?" ;
		Object [] params = {(pageNumber-1)*pageCount , pageCount} ;
		list = query.query(conn, sql, params, new BeanListHandler<Notice>(Notice.class));
		return list;
	}
	
	public int findNoticeMaxpage() throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "select count(*) from t_notice" ;
		//最大记录数
		long count = query.query(conn, sql,new ScalarHandler<Long>());
		//最大页数
		return (int) Math.ceil(((double) count) / pageCount);
	}
	public int updatenotice(Notice notice) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "update t_notice set title=? , publishTime=? ,userId=?,content=? where id=?";
		Object [] params={notice.getTitle(),notice.getPublishTime(),notice.getUserId(),notice.getContent(),notice.getId()};
		return query.update(conn, sql, params);
	}
	
	
	@SuppressWarnings("deprecation")
	public List<Notice> findNoticeUser( Integer userId , Integer pageNumber)
						throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		List params = new ArrayList();
		StringBuilder sql= new StringBuilder("select * from t_notice where 1=1");
		
		
		if(userId!=null && !userId.equals(-1)){
		sql.append(" and userId=? order by id  ");
		params.add(userId);
		
		}
		
		sql.append(" limit ?,? ");
		params.add((pageNumber-1)*pageCount);
		params.add(pageCount);
		
		
		list = query.query(conn, sql.toString(), params.toArray(), new BeanListHandler<Notice>(Notice.class));
		return list;
	}

	
	public int findNoticeUserMaxPage(Integer userId ) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		List params = new ArrayList();//因为不知道要传的参数有多少,所以只能定义为集合形式
		
		StringBuilder sql= new StringBuilder("select count(*) from t_notice  where 1=1 ");		
		if(userId!=null && !userId.equals(-1)){
			sql.append(" and userId=? ");
			params.add(userId);
			}
		
		long count = query.query(conn, sql.toString(), params.toArray(), new ScalarHandler<Long>());//由于params需要给id追加值,
		                                                                                            //只能定义为集合但是query方法中要
		                                                                                            // 将params转化成object对象的数组
		return (int) Math.ceil(((double) count) / pageCount);
	}
}
