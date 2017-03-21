package com.ian.hrmapp.util.tag;

import static com.ian.hrmapp.util.common.HrmConstants.PAGE_DEFAULT_SIZE;

public class PageModel {

	private int recordCount;
	
	private int pageIndex;
	
	private int pageSize = PAGE_DEFAULT_SIZE;
	
	private int totalSize;
	
	public int getRecordCount(){
		this.recordCount = this.recordCount <= 0 ? 0 : this.recordCount;
		return recordCount;
	}
	
	public void setRecordCount(int recordCount){
		this.recordCount = recordCount;
	}
	
	public int getPageInde(){
		this.pageIndex = this.pageIndex <= 0 ? 1 : this.pageIndex;
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public int getPageSize(){
		this.pageSize = this.pageSize <= PAGE_DEFAULT_SIZE ? PAGE_DEFAULT_SIZE : this.pageSize;
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getTotalSize(){
		if (this.getRecordCount() <= 0) {
			totalSize = 0;
		}else{
			totalSize = (this.getRecordCount() - 1) /  this.getPageSize() + 1;
		}
		return totalSize;
	}
	
	public int getFirstLimitParam(){
		return (this.getPageInde() - 1) * this.pageSize;
	}
}
