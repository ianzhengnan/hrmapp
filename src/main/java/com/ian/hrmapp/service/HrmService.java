package com.ian.hrmapp.service;

import java.util.List;

import com.ian.hrmapp.domain.Dept;
import com.ian.hrmapp.domain.Document;
import com.ian.hrmapp.domain.Employee;
import com.ian.hrmapp.domain.Job;
import com.ian.hrmapp.domain.Notice;
import com.ian.hrmapp.domain.User;
import com.ian.hrmapp.util.tag.PageModel;

public interface HrmService {

	/**
	 * 用户登录
	 * @param loginname
	 * @param password
	 * @return User对象
	 */
	User login(String loginname, String password);
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	User findUserById(Integer id);

	/**
	 * 获得所有用户
	 * @param user
	 * @param pageModel
	 * @return User对象的List集合
	 */
	List<User> findUser(User user, PageModel pageModel);
	
	/**
	 * 根据id删除用户
	 * @param id
	 */
	void removeUserById(Integer id);
	
	/**
	 * 修改用户
	 * @param user
	 */
	void modifyUser(User user);
	
	/**
	 * 添加用户
	 * @param user
	 */
	void addUser(User user);
	
	/**
	 * 获得所有员工
	 * @param employee
	 * @param pageModel
	 * @return
	 */
	List<Employee> findEmployee(Employee employee, PageModel pageModel);
	
	/**
	 * 删除员工
	 * @param id
	 */
	void removeEmployeeById(Integer id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Employee findEmployeeById(Integer id);
	
	/**
	 * 
	 * @param employee
	 */
	void addEmployee(Employee employee);
	
	/**
	 * 
	 * @param employee
	 */
	void modifyEmployee(Employee employee);
	
	
	/**
	 * 获得所有部门, 分页查询
	 * @param dept
	 * @param pageModel
	 * @return
	 */
	List<Dept> findDept(Dept dept, PageModel pageModel);
	
	/**
	 * 获得所有部门
	 * @return
	 */
	List<Dept> findAllDept();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Dept findDeptById(Integer id);
	
	/**
	 * 
	 * @param id
	 */
	void removeDeptById(Integer id);
	
	/**
	 * 
	 * @param dept
	 */
	void addDept(Dept dept);
	
	/**
	 * 
	 * @param dept
	 */
	void modifyDept(Dept dept);
	
	/**
	 * 
	 * @return
	 */
	List<Job> findAllJobs();
	/**
	 * 
	 * @param job
	 * @param pageModel
	 * @return
	 */
	List<Job> findJobs(Job job, PageModel pageModel);
	/**
	 * 
	 * @param id
	 * @return
	 */
	Job findJobById(Integer id);
	/**
	 * 
	 * @param id
	 */
	void removeJobById(Integer id);
	/**
	 * 
	 * @param job
	 */
	void addJob(Job job);
	/**
	 * 
	 * @param job
	 */
	void modifyJob(Job job);
	
	/**
	 * 
	 * @param notice
	 * @param pageModel
	 * @return
	 */
	List<Notice> findNotices(Notice notice, PageModel pageModel);
	/**
	 * 
	 * @param id
	 * @return
	 */
	Notice findNoticeById(Integer id);
	/**
	 * 
	 * @param id
	 */
	void removeNoticeById(Integer id);
	void addNotice(Notice notice);
	void modifyNotice(Notice notice);
	
	List<Document> findDocuments(Document document, PageModel pageModel);
	Document findDocumentById(Integer id);
	void removeDocumentById(Integer id);
	void addDocument(Document document);
	void modifyDocument(Document document);
}
