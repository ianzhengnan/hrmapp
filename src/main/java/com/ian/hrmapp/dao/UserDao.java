package com.ian.hrmapp.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ian.hrmapp.dao.provider.UserDynaSqlProvider;
import com.ian.hrmapp.domain.User;
import static com.ian.hrmapp.util.common.HrmConstants.USERTABLE;

import java.util.List;
import java.util.Map;;

public interface UserDao {

	@Select("select * from " + USERTABLE + " where loginname = #{loginname} and password = #{password}")
	User selectByLoginnameAndPassword(@Param("loginname") String loginname,
										@Param("password") String password);
	
	@Select("select * from " + USERTABLE + " where id = #{id}")
	User selectById(Integer id);
	
	@Delete("delete from " + USERTABLE + " where id = #{id}")
	void deleteById(Integer id);
	
	// 动态修改用户
	@UpdateProvider(type=UserDynaSqlProvider.class, method = "updateUser")
	void updateUser(User user);
	
	// 动态查询
	@SelectProvider(type = UserDynaSqlProvider.class, method = "selectWithParam")
	List<User> selectByPage(Map<String, Object> params);
	
	// 根据参数查询用户总数
	@SelectProvider(type = UserDynaSqlProvider.class, method = "count")
	Integer count(Map<String, Object> params);
	
	// 动态插入用户
	@SelectProvider(type = UserDynaSqlProvider.class, method = "insertUser")
	void save(User user);
	
	
}
