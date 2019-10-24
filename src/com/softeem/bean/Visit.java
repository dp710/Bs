package com.softeem.bean;

import java.sql.Timestamp;

public class Visit {
	private Integer id ;
	private String lname;
	private Integer userId;
	private Timestamp stime;
	private Timestamp etime;
	private String idcard;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Timestamp getStime() {
		return stime;
	}
	public void setStime(Timestamp stime) {
		this.stime = stime;
	}
	public Timestamp getEtime() {
		return etime;
	}
	public void setEtime(Timestamp etime) {
		this.etime = etime;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	@Override
	public String toString() {
		return "Visit [etime=" + etime + ", id=" + id + ", idcard=" + idcard
				+ ", lname=" + lname + ", stime=" + stime + ", userId="
				+ userId + "]";
	}
	
	public Visit(String lname, Integer userId, Timestamp stime,
			Timestamp etime, String idcard) {
		super();
		this.lname = lname;
		this.userId = userId;
		this.stime = stime;
		this.etime = etime;
		this.idcard = idcard;
	}
	public Visit() {
		super();
	}
	public Visit(Integer id, String lname, Integer userId, Timestamp stime,
			Timestamp etime, String idcard) {
		super();
		this.id = id;
		this.lname = lname;
		this.userId = userId;
		this.stime = stime;
		this.etime = etime;
		this.idcard = idcard;
	}
	
	
}
