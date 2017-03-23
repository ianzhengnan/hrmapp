package com.ian.hrmapp.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ian.hrmapp.domain.Dept;

import static com.ian.hrmapp.util.common.HrmConstants.DEPTTABLE;

public class DeptDynaSqlProvider {

	public String selectWithParam(Map<String, Object> params){
		String sql = new SQL(){
			{
				SELECT("*");
				FROM(DEPTTABLE);
				if (params.get("dept") != null) {
					Dept dept = (Dept) params.get("dept");
					if(dept.getName() != null && !dept.getName().equals("")){
						WHERE(" name LIKE CONCAT ('%', #{dept.name}, '%')");
					}
				}
			}
		}.toString();
		
		if (params.get("pageModel") != null) {
			sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize}";
		}
		return sql;
	}
	
	public String count(Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(DEPTTABLE);
				if (params.get("dept") != null) {
					Dept dept = (Dept) params.get("dept");
					if(dept.getName() != null && !dept.getName().equals("")){
						WHERE(" name LIKE CONCAT ('%', #{dept.name}, '%')");
					}
				}
			}
		}.toString();
	}
	
	public String insertDept(Dept dept){
		return new SQL(){
			{
				INSERT_INTO(DEPTTABLE);
				if(dept.getName() != null && !dept.getName().equals("")){
					VALUES("name", "#{name}");
				}
				if(dept.getRemark() != null && !dept.getRemark().equals("")){
					VALUES("remark", "#{remark}");
				}
			}
		}.toString();
	}
	
	public String updateDept(Dept dept){
		return new SQL(){
			{
				UPDATE(DEPTTABLE);
				if(dept.getName() != null){
					SET(" name = #{name}");
				}
				if(dept.getRemark() != null){
					SET(" remark = #{remark}");
				}
				WHERE(" id = #{id}");
			}
		}.toString();
	}
}
