package com.softeem.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.registry.Query;

import com.softeem.bean.Dormitory;
import com.softeem.bean.User;
import com.softeem.dao.UserDao;

//用户业务层
public class UserService {

	private UserDao userDao = new UserDao(User.class); 
	
	public User login(User user) throws SQLException{
		return userDao.login(user);
		
	}
	
	public boolean checkid( Integer id ) throws SQLException{
		if(userDao.findByid(id)>0){
			return true;
		}else{
			return false ;
		}
	}
	
	public boolean checknickName( String nickName ) throws SQLException{
		if(userDao.findBynickName(nickName)>0){
			return true;
		}else{
			return false ;
		}
	}
	
	public void saveUser(User user) throws SQLException{
		
		 userDao.savestudent(user);
	}
	public int updateuser(User user) throws SQLException{
		return userDao.updateuser(user);
	}
	
	public User findPassWord(Integer id) throws SQLException{
		String sql="select password from t_user where id=?";
		return userDao.findById(sql, id);
	}
	
	public int updatePassword(String passWord,Integer id) throws SQLException{
		return userDao.updatePassword(passWord, id);
	}
	
	public Map showUserAll( Integer grade,Integer id,Integer pageNumber) throws SQLException{
		
		List<User> userlist = userDao.findStudent(grade,id, pageNumber);
		
		int maxPage = userDao.findStudentMaxPage(grade,id);
		String pageCode = userDao.genPagination("UserServlet.action", maxPage, pageNumber, "flag=list&grade="+((grade==null)?"":grade)+"&id="+((id==null)?"":id));
		
		HashMap map = new HashMap();
		map.put("userlist", userlist);
		map.put("pageCode", pageCode);
		
		return map;
	}
	
}
