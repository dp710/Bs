package com.softeem.bean;

import java.sql.Timestamp;

public class Electric {
	   private Integer id;
	   private Timestamp month;
	   private double price;
	   private double amount;
	   private double pay;
	   private Integer dormId;
	public Electric() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getMonth() {
		return month;
	}
	public void setMonth(Timestamp month) {
		this.month = month;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getPay() {
		return pay;
	}
	public void setPay(double pay) {
		this.pay = pay;
	}
	public Integer getDormId() {
		return dormId;
	}
	public void setDormId(Integer dormId) {
		this.dormId = dormId;
	}
	public Electric(Timestamp month, double price, double amount, double pay,
			Integer dormId) {
		super();
		this.month = month;
		this.price = price;
		this.amount = amount;
		this.pay = pay;
		this.dormId = dormId;
	}
	@Override
	public String toString() {
		return "Electric [amount=" + amount + ", dormId=" + dormId + ", id="
				+ id + ", month=" + month + ", pay=" + pay + ", price=" + price
				+ "]";
	}
	public Electric(Integer id, Timestamp month, double price, double amount,
			double pay, Integer dormId) {
		super();
		this.id = id;
		this.month = month;
		this.price = price;
		this.amount = amount;
		this.pay = pay;
		this.dormId = dormId;
	}
	
	
	     
}
