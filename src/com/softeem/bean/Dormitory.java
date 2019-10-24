package com.softeem.bean;

public class Dormitory {
	private Integer id ;
	private String tel;
	private Integer number;
	private String remarks;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	@Override
	public String toString() {
		return "Dormitory [id=" + id + ", number=" + number + ", remarks="
				+ remarks + ", tel=" + tel + "]";
	}
	public Dormitory(Integer id, String tel, Integer number, String remarks) {
		super();
		this.id = id;
		this.tel = tel;
		this.number = number;
		this.remarks = remarks;
	}
	public Dormitory() {
		super();
	}
	
	
}
