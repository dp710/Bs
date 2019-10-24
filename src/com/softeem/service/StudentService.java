package com.softeem.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softeem.bean.User;
import com.softeem.dao.UserDao;



public class StudentService {
  
    private UserDao userdao=new UserDao(User.class);
	
	public Map findStudent(Integer pageNumber) throws SQLException{
	List<User> userlist=userdao.findStudent(pageNumber);
	
	int maxPage = userdao.findStudentMaxpage();
	String pageCode = userdao.genPagination("StudentServlet.action", maxPage, pageNumber, "flag=findStudent");
	
	HashMap map = new HashMap();
	map.put("userlist", userlist);
	map.put("pageCode", pageCode);
	
	return map;
	}
	 public void deleteById(Integer userId) throws SQLException{
			

			userdao.deleteById("delete from t_user where id=?", userId);
			
		}
	 public void saveStudent(User user) throws SQLException{
			
		 userdao.savestudent(user);
	}
	 public User findStudentById(Integer userId) throws SQLException{
			

			return userdao.findById("select * from t_user where id=?", userId);
			
		}
	 public int updatestudent(User user) throws SQLException{
			return userdao.updatestudentr(user);
		}
}
