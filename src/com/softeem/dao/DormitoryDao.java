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
		//����¼��
		long count = query.query(conn, sql,new ScalarHandler<Long>());
		//���ҳ��
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
		List params = new ArrayList();//��Ϊ��֪��Ҫ���Ĳ����ж���,����ֻ�ܶ���Ϊ������ʽ
		
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
		
		long count = query.query(conn, sql.toString(), params.toArray(), new ScalarHandler<Long>());//����params��Ҫ��id׷��ֵ,
		                                                                                            //ֻ�ܶ���Ϊ���ϵ���query������Ҫ
		                                                                                            // ��paramsת����object���������
		return (int) Math.ceil(((double) count) / pageCount);
	}

}
