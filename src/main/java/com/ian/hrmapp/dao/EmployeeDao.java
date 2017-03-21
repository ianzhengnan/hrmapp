package com.ian.hrmapp.dao;

import java.util.Date;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import com.ian.hrmapp.dao.provider.EmployeeDynaSqlProvider;
import com.ian.hrmapp.domain.Employee;
import static com.ian.hrmapp.util.common.HrmConstants.EMPLOYEETABLE;


public interface EmployeeDao {

	@SelectProvider(type = EmployeeDynaSqlProvider.class, method = "count")
	Integer count(Map<String, Object> params);
	
	// 动态参数查询员工
	@SelectProvider(type = EmployeeDynaSqlProvider.class, method = "selectWithParam")
	@Results({
		@Result(id = true, column = "id", property = "id"),
		@Result(column = "CARD_ID", property = "cardId"),
		@Result(column = "POST_CODE", property = "postCode"),
		@Result(column = "QQ_NUM", property = "qqNum"),
		@Result(column = "BIRTHDAY", property = "birthday", javaType = Date.class),
		@Result(column = "CREATE_DATE", property = "createDate", javaType = Date.class),
		@Result(column = "DEPT_ID", property = "dept", 
			one = @One(select = "com.ian.hrmapp.dao.DeptDao.selectById", fetchType = FetchType.EAGER)),
		@Result(column = "JOB_ID", property = "job", 
			one = @One(select = "com.ian.hrmapp.dao.JobDao.selectById", fetchType = FetchType.EAGER))
	})
	List<Employee> selectByPage(Map<String, Object> params);
	
	// 动态保存员工
	@SelectProvider(type = EmployeeDynaSqlProvider.class, method = "insertEmployee")
	void save(Employee employee);
	
	// 根据ID删除员工
	@Delete(" delete from " + EMPLOYEETABLE + " where id = #{id}")
	void deleteById(Integer id);
	
	// 根据ID得到员工
	@Select("select * from " + EMPLOYEETABLE + " where id = #{id}")
	@Results({
		@Result(id = true, column = "id", property = "id"),
		@Result(column = "CARD_ID", property = "cardId"),
		@Result(column = "POST_CODE", property = "postCode"),
		@Result(column = "QQ_NUM", property = "qqNum"),
		@Result(column = "BIRTHDAY", property = "birthday", javaType = Date.class),
		@Result(column = "CREATE_DATE", property = "createDate", javaType = Date.class),
		@Result(column = "DEPT_ID", property = "dept", 
			one = @One(select = "com.ian.hrmapp.dao.DeptDao.selectById", fetchType = FetchType.EAGER)),
		@Result(column = "JOB_ID", property = "job", 
			one = @One(select = "com.ian.hrmapp.dao.JobDao.selectById", fetchType = FetchType.EAGER))
	})
	Employee selectById(Integer id);
	
	@SelectProvider(type = EmployeeDynaSqlProvider.class, method = "updateEmployee")
	void update(Employee employee);
	
	
}
