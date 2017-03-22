package com.ian.hrmapp.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ian.hrmapp.domain.User;
import static com.ian.hrmapp.util.common.HrmConstants.USERTABLE;

public class UserDynaSqlProvider {

	public String selectWithParam(Map<String, Object> params){
		String sql = new SQL(){
			{
				SELECT("*");
				FROM(USERTABLE);
				if (params.get("user") != null) {
					User user = (User)params.get("user");
					if (user.getLoginname() != null && !user.getLoginname().equals("")) {
						WHERE(" username LIKE CONCAT ('%', #{user.username}, '%'");
					}
					if (user.getStatus() != null && !user.getStatus().equals("")) {
						WHERE(" username LIKE CONCAT ('%', #{user.status}, '%'");
					}
				}
			}
		}.toString();
		if (params.get("pageModel") != null) {
			sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
		}
		return sql;
	}
	
	public String count(Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(USERTABLE);
				if (params.get("user") != null) {
					User user = (User)params.get("user");
					if (user.getLoginname() != null && !user.getLoginname().equals("")) {
						WHERE(" username LIKE CONCAT ('%', #{user.username}, '%'");
					}
					if (user.getStatus() != null && !user.getStatus().equals("")) {
						WHERE(" username LIKE CONCAT ('%', #{user.status}, '%'");
					}
				}
			}
		}.toString();
	}
	
	
	public String insertUser(User user){
		return new SQL(){
			{
				INSERT_INTO(USERTABLE);
				if (user.getUsername() != null && user.getUsername().equals("")) {
					VALUES("username","#{username}");
				}
				if (user.getStatus() != null && user.getStatus().equals("")) {
					VALUES("status","#{status}");
				}
				if (user.getPassword() != null && user.getPassword().equals("")) {
					VALUES("password","#{password}");
				}
				if (user.getLoginname() != null && user.getLoginname().equals("")) {
					VALUES("loginname","#{loginname}");
				}
			}
		}.toString();
	}
	
	public String updateUser(User user){
		return new SQL(){
			{
				UPDATE(USERTABLE);
				if (user.getUsername() != null) {
					SET(" username = #{username} ");
				}
				if (user.getLoginname() != null) {
					SET(" loginname = #{loginname} ");
				}
				if (user.getPassword() != null) {
					SET(" password = #{password} ");
				}
				if (user.getStatus() != null) {
					SET(" status = #{status} ");
				}
				if (user.getCreateDate() != null) {
					SET(" create_date = #{create_date} ");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
}
