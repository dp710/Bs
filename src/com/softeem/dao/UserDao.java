package com.softeem.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.softeem.bean.Dormitory;
import com.softeem.bean.User;
import com.softeem.util.BaseDao;
import com.softeem.util.ConnectionContext;

public class UserDao extends BaseDao<User> {
	//一定要调用父类有参数的构造方法
	public UserDao(Class claxx) {
		super(claxx);
	}
	
	//此类所特有的方法不会写在BaseDao中
	public User login(User user) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "select * from t_user where id = ? and password = ?" ;
		Object [] params = {user.getId() , user.getPassword()};
		entity = query.query(conn ,sql, params , new BeanHandler<User>(User.class));
		return entity ;
	}

	public int findByid(Integer id) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "select count(*) from t_user where id = ?";
		return query.query(conn, sql, id, new ScalarHandler<Long>()).intValue();
	}
	
	public int findBynickName(String nickName) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "select count(*) from t_user where nickName = ?";
		return query.query(conn, sql, nickName, new ScalarHandler<Long>()).intValue();
	}
	
	public int updatePassword(String passWord,Integer id) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "update t_user set password=? where id=?";
		Object [] params={passWord,id};
		return query.update(conn, sql, params);
	}
	public int updateuser(User user) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "update t_user set nickName=? , sex=? , tel=? , email=? where id=?";
		Object [] params={user.getNickName(),user.getSex(),user.getTel(),user.getEmail(),user.getId()};
		return query.update(conn, sql, params);
	}
	public int updatestudentr(User user) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "update t_user set nickName=? , sex=? ,faculty=?,grade=?, tel=?, password=?, email=?,type=?,face=?,dormId=?,trueName=? where id=?";
		Object [] params={user.getNickName(),user.getSex(),user.getFaculty(),user.getGrade(),user.getTel(),user.getPassword(),user.getEmail(),user.getType(),user.getFace(),user.getDormId(),user.getTrueName(),user.getId()};
		return query.update(conn, sql, params);
	}
	public void savestudent(User user) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "insert into t_user values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object [] params = {
				user.getId(),
				user.getNickName(),
				user.getSex(),
				user.getFaculty(),
				user.getGrade(),
				user.getTel(),
				user.getPassword(),
				user.getEmail(),
				user.getType(),
				user.getFace(),	
				user.getDormId(),
				user.getTrueName()
				};
		 query.insert(conn, sql,  new ScalarHandler<Long>(), params);
	}
	public List<User> findStudent(Integer pageNumber) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "select * from t_user   limit ? , ?" ;
		Object [] params = {(pageNumber-1)*pageCount , pageCount} ;
		list = query.query(conn, sql, params, new BeanListHandler<User>(User.class));
		return list;
	}
	
	public int findStudentMaxpage() throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		String sql = "select count(*) from t_user" ;
		//最大记录数
		long count = query.query(conn, sql,new ScalarHandler<Long>());
		//最大页数
		return (int) Math.ceil(((double) count) / pageCount);
	}
	
	@SuppressWarnings("deprecation")
	public List<User> findStudent( Integer grade,Integer id , Integer pageNumber)
						throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		List params = new ArrayList();
		StringBuilder sql= new StringBuilder("select * from t_user where 1=1");
		
		if(grade==0){
			sql.append(" and grade='软件工程'");
		}
		if(grade==1){
			sql.append(" and grade='网络工程'");
		}
		if(grade==2){
			sql.append(" and grade='物联网工程'");
		}
		if(grade==3){
			sql.append(" and grade='计算机科学与技术1班'");
		}
		if(grade==4){
			sql.append(" and grade='计算机科学与技术2班'");
		}
		if(grade==-1){
			sql.append(" and 1=1");
		}
		if(id!=null && !id.equals(-1)){
		sql.append(" and id=?");
		params.add(id);
		
		}else
		{
		sql.append(" limit ?,? ");
		params.add((pageNumber-1)*pageCount);
		params.add(pageCount);
		}
		
		list = query.query(conn, sql.toString(), params.toArray(), new BeanListHandler<User>(User.class));
		return list;
	}

	
	public int findStudentMaxPage(Integer grade,Integer id ) throws SQLException{
		conn = ConnectionContext.getInstance().getConnection();
		List params = new ArrayList();//因为不知道要传的参数有多少,所以只能定义为集合形式
		
		StringBuilder sql= new StringBuilder("select count(*) from t_user  where 1=1 ");	
		if(grade==0){
			sql.append(" and grade='软件工程'");
		}
		if(grade==1){
			sql.append(" and grade='网络工程'");
		}
		if(grade==2){
			sql.append(" and grade='物联网工程'");
		}
		if(grade==3){
			sql.append(" and grade='计算机科学与技术1班'");
		}
		if(grade==4){
			sql.append(" and grade='计算机科学与技术2班'");
		}
		if(grade==-1){
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
