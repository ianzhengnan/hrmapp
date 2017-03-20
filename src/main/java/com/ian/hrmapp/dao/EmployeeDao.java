package com.ian.hrmapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import com.ian.hrmapp.dao.provider.EmployeeDynaSqlProvider;
import com.ian.hrmapp.domain.Employee;

public interface EmployeeDao {

	@SelectProvider(type = EmployeeDynaSqlProvider.class, method = "count")
	Integer count(Map<String, Object> params);
	
	// 动态参数查询员工
	@SelectProvider(type = EmployeeDynaSqlProvider.class, method = "selectWithParam")
	@Results({
		@Result(id = true, column = "id", property = "id"),
		@Result(column = "CARD_ID", property = "cardId")
	})
	List<Employee> selectByPage(Map<String, Object> params);
}
