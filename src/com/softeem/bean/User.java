package com.softeem.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

//t_user表对象的实例类User
public class User {
	private Integer id ;
	private String nickName ;
	private String sex ;
	private String faculty ;
	private String grade ;
	private String tel ;
	private String password ;
	private String email ;
	private Integer type ;
	private String face ;
	private Integer dormId ;
	private String trueName ;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public Integer getDormId() {
		return dormId;
	}
	public void setDormId(Integer dormId) {
		this.dormId = dormId;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	@Override
	public String toString() {
		return "User [dormId=" + dormId + ", email=" + email + ", face=" + face
				+ ", faculty=" + faculty + ", grade=" + grade + ", id=" + id
				+ ", nickName=" + nickName + ", password=" + password
				+ ", sex=" + sex + ", tel=" + tel + ", trueName=" + trueName
				+ ", type=" + type + "]";
	}
	public User(){
		super();
	}
	public User(Integer id, String nickName, String sex, String tel,
			String email) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.sex = sex;
		this.tel = tel;
		this.email = email;
	}
	public User(Integer id, String nickName, String sex, String faculty,
			String grade, String tel, String password, String email,
			Integer type, String face, Integer dormId, String trueName) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.sex = sex;
		this.faculty = faculty;
		this.grade = grade;
		this.tel = tel;
		this.password = password;
		this.email = email;
		this.type = type;
		this.face = face;
		this.dormId = dormId;
		this.trueName = trueName;
	}
	
	
	
	

}
