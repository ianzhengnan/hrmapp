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

import com.ian.hrmapp.dao.provider.NoticeDynaSqlProvider;
import com.ian.hrmapp.domain.Notice;
import static com.ian.hrmapp.util.common.HrmConstants.NOTICETABLE;

public interface NoticeDao {

	// 动态查询
	@SelectProvider(type = NoticeDynaSqlProvider.class, method = "selectWithParam")
	@Results({
		@Result(id = true, column = "id", property = "id"),
		@Result(column = "CREATE_DATE", property = "createDate", javaType = Date.class),
		@Result(column = "USER_ID", property = "user", 
			one = @One(select = "com.ian.hrmapp.dao.UserDao.selectById", fetchType = FetchType.EAGER))
	})
	List<Notice> selectByPage(Map<String, Object> params);
	
	// 统计个数
	@SelectProvider(type = NoticeDynaSqlProvider.class, method = "count")
	Integer count(Map<String, Object> params);
	
	// 根据id选择公告
	@Select("select * from " + NOTICETABLE + " where id = #{id}")
	Notice selectById(Integer id);
	
	// 根据id删除公告
	@Delete("delete from " + NOTICETABLE + " where id = #{id}")
	void deleteById(Integer id);
	
	@SelectProvider(type = NoticeDynaSqlProvider.class, method = "insertNotice")
	void save(Notice notice);
	
	@SelectProvider(type = NoticeDynaSqlProvider.class, method = "updateNotice")
	void update(Notice notice);
}
