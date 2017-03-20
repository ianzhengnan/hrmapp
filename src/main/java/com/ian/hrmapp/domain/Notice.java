package com.ian.hrmapp.domain;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1948078739151730583L;

	private Integer id;
	private String title;
	private String content;
	private Date createDate;
	private User user;
	
	public Notice(){
		
	}

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
