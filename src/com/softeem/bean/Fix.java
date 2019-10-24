package com.softeem.bean;

import java.sql.Timestamp;

public class Fix {
	private Integer id ;
	private String goods;
	private Timestamp date;
	private String remarks;
	private Integer dormId ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getDormId() {
		return dormId;
	}
	public void setDormId(Integer dormId) {
		this.dormId = dormId;
	}
	@Override
	public String toString() {
		return "Fix [date=" + date + ", dormId=" + dormId + ", goods=" + goods
				+ ", id=" + id + ", remarks=" + remarks + "]";
	}
	public Fix(String goods, Timestamp date, String remarks, Integer dormId) {
		super();
		this.goods = goods;
		this.date = date;
		this.remarks = remarks;
		this.dormId = dormId;
	}
	public Fix() {
		super();
	}
	
	
	
}
