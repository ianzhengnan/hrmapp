package com.ian.hrmapp.domain;

import java.io.Serializable;

public class Dept implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -425933760559459342L;

	private Integer id;
	private String name;
	private String remark;
	
	public Dept(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
