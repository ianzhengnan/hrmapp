package com.ian.hrmapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.ian.hrmapp.dao.provider.DeptDynaSqlProvider;
import com.ian.hrmapp.domain.Dept;

import static com.ian.hrmapp.util.common.HrmConstants.DEPTTABLE;

public interface DeptDao {

	@SelectProvider(type = DeptDynaSqlProvider.class, method = "selectWithParam")
	List<Dept> selectByPage(Map<String, Object> params);
	
	@Select("select * from " + DEPTTABLE )
	List<Dept> selectAllDepts();
	
	@SelectProvider(type = DeptDynaSqlProvider.class, method = "count")
	Integer count(Map<String , Object> params);
	
	@Select("select * from " + DEPTTABLE + " where id = #{id}")
	Dept selectById(int id);
	
	@Delete(" delete from " + DEPTTABLE + " where id = #{id}")
	void deleteById(Integer id);
	
	@SelectProvider(type = DeptDynaSqlProvider.class, method = "insertDept")
	void save(Dept dept);
	
	@SelectProvider(type = DeptDynaSqlProvider.class, method = "updateDept")
	void update(Dept dept);
	
}
