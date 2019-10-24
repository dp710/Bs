package com.softeem.bean;

import java.sql.Timestamp;

public class Water {

   private Integer id;
   private Timestamp month;
   private double price;
   private double weight;
   private double pay;
   private Integer dormId;
  
	
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


public double getWeight() {
	return weight;
}


public void setWeight(double weight) {
	this.weight = weight;
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


	@Override
public String toString() {
	return "Water [dormId=" + dormId + ", id=" + id + ", month=" + month
			+ ", pay=" + pay + ", price=" + price + ", weight=" + weight + "]";
}


	public Water(Timestamp month, double price, double weight, double pay,
			Integer dormId) {
		super();
		this.month = month;
		this.price = price;
		this.weight = weight;
		this.pay = pay;
		this.dormId = dormId;
	}


	public Water() {
		super();
	}


	public Water(Integer id, Timestamp month, double price, double weight,
			double pay, Integer dormId) {
		super();
		this.id = id;
		this.month = month;
		this.price = price;
		this.weight = weight;
		this.pay = pay;
		this.dormId = dormId;
	}
	
}
