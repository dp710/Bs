package com.softeem.bean;

import java.sql.Timestamp;

public class Notice {

	private Integer id ;
	private String title;
	private String content;
	private Timestamp publishTime;
	private Integer userId ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Notice [content=" + content + ", id=" + id + ", publishTime="
				+ publishTime + ", title=" + title + ", userId=" + userId + "]";
	}
	public Notice(String title, String content, Timestamp publishTime,
			Integer userId) {
		super();
		this.title = title;
		this.content = content;
		this.publishTime = publishTime;
		this.userId = userId;
	}
	public Notice() {
		super();
	}
	
	public Notice(Integer id, String title, String content,
			Timestamp publishTime, Integer userId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.publishTime = publishTime;
		this.userId = userId;
	}
	
	
}
