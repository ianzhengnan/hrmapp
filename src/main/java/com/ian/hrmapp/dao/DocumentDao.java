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

import com.ian.hrmapp.dao.provider.DocumentDynaSqlProvider;
import com.ian.hrmapp.domain.Document;
import static com.ian.hrmapp.util.common.HrmConstants.DOCUMENTTABLE;

public interface DocumentDao {

	@SelectProvider(type = DocumentDynaSqlProvider.class, method = "selectWithParam")
	@Results({
		@Result(id = true, column = "id", property = "id"),
		@Result(column = "CREATE_DATE", property = "createDate",javaType = Date.class),
		@Result(column = "USER_ID", property = "user", 
			one = @One(select="com.ian.hrmapp.dao.UserDao.selectById",fetchType = FetchType.EAGER))
	})
	List<Document> selectByPage(Map<String, Object> params);

	@SelectProvider(type = DocumentDynaSqlProvider.class, method="count")
	Integer count(Map<String, Object> params);
	
	@SelectProvider(type = DocumentDynaSqlProvider.class, method = "insertDocument")
	void save(Document document);
	
	@Select("select * from " + DOCUMENTTABLE + " where id = #{id}")
	Document selectById(Integer id);
	
	@Delete("delete from " + DOCUMENTTABLE + " where id = #{id}")
	void deleteById(Integer id);
	
	@SelectProvider(type = DocumentDynaSqlProvider.class, method = "updateDocument")
	void update(Document document);
	
}
