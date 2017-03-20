package com.ian.hrmapp.domain;

import java.io.Serializable;

public class Job implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5765544804523969017L;

	private Integer id;
	private String name;
	private String remark;
	
	public Job(){
		
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
