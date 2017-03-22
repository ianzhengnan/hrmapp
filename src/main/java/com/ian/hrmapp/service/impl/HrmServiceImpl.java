package com.ian.hrmapp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ian.hrmapp.dao.DeptDao;
import com.ian.hrmapp.dao.DocumentDao;
import com.ian.hrmapp.dao.EmployeeDao;
import com.ian.hrmapp.dao.JobDao;
import com.ian.hrmapp.dao.NoticeDao;
import com.ian.hrmapp.dao.UserDao;
import com.ian.hrmapp.domain.Dept;
import com.ian.hrmapp.domain.Document;
import com.ian.hrmapp.domain.Employee;
import com.ian.hrmapp.domain.Job;
import com.ian.hrmapp.domain.Notice;
import com.ian.hrmapp.domain.User;
import com.ian.hrmapp.service.HrmService;
import com.ian.hrmapp.util.tag.PageModel;

@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService{

	private static final Logger logger = LogManager.getLogger(HrmServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private DeptDao deptDao;
	@Autowired
	private JobDao jobDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private DocumentDao documentDao;
	
	/**********************************用户服务接口的实现*****************************************/
	@Override
	@Transactional(readOnly = true)
	public User login(String loginname, String password) {
		logger.debug("HrmServiceImple >> login");
		return userDao.selectByLoginnameAndPassword(loginname, password);
	}

	@Override
	@Transactional(readOnly = true)
	public User findUserById(Integer id) {
		return userDao.selectById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findUser(User user, PageModel pageModel) {
		Map<String, Object> params = new HashMap<>();
		params.put("user", user);
		int recordCount = userDao.count(params);
		logger.debug("recordCount -- >> " + recordCount);
		pageModel.setRecordCount(recordCount);
		if (recordCount > 0) {
			params.put("pageModel", pageModel);
		}
		List<User> users = userDao.selectByPage(params);
		return users;
	}

	@Override
	public void removeUserById(Integer id) {
		userDao.deleteById(id);
	}

	@Override
	public void modifyUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void addUser(User user) {
		userDao.save(user);
	}
	/**********************************员工服务接口的实现*****************************************/
	@Override
	@Transactional(readOnly = true)
	public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
		Map<String, Object> params = new HashMap<>();
		params.put("employee",employee);
		int recordCount = employeeDao.count(params);
		logger.debug("recordCount -- >> " + recordCount);
		pageModel.setRecordCount(recordCount);
		if (recordCount > 0) {
			params.put("pageModel", pageModel);
		}
		List<Employee> employees = employeeDao.selectByPage(params);
		return employees;
	}

	@Override
	public void removeEmployeeById(Integer id) {
		employeeDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Employee findEmployeeById(Integer id) {
		return employeeDao.selectById(id);
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeDao.save(employee);
	}

	@Override
	public void modifyEmployee(Employee employee) {
		employeeDao.update(employee);
	}
	/**********************************部门服务接口的实现*****************************************/
	@Override
	@Transactional(readOnly = true)
	public List<Dept> findDept(Dept dept, PageModel pageModel) {
		Map<String, Object> params = new HashMap<>();
		params.put("dept",dept);
		int recordCount = deptDao.count(params);
		logger.debug("recordCount -- >> " + recordCount);
		pageModel.setRecordCount(recordCount);
		if (recordCount > 0) {
			params.put("pageModel", pageModel);
		}
		List<Dept> depts = deptDao.selectByPage(params);
		return depts;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Dept> findAllDept() {
		return deptDao.selectAllDepts();
	}

	@Override
	@Transactional(readOnly = true)
	public Dept findDeptById(Integer id) {
		return deptDao.selectById(id);
	}

	@Override
	public void removeDeptById(Integer id) {
		deptDao.deleteById(id);
	}

	@Override
	public void addDept(Dept dept) {
		deptDao.save(dept);
	}

	@Override
	public void modifyDept(Dept dept) {
		deptDao.update(dept);
	}
	/**********************************工作服务接口的实现*****************************************/
	@Override
	@Transactional(readOnly = true)
	public List<Job> findAllJobs() {
		return jobDao.selectAllJob();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Job> findJobs(Job job, PageModel pageModel) {
		Map<String, Object> params = new HashMap<>();
		params.put("job",job);
		int recordCount = jobDao.count(params);
		logger.debug("recordCount -- >> " + recordCount);
		pageModel.setRecordCount(recordCount);
		if (recordCount > 0) {
			params.put("pageModel", pageModel);
		}
		List<Job> jobs = jobDao.selectByPage(params);
		return jobs;
	}

	@Override
	@Transactional(readOnly = true)
	public Job findJobById(Integer id) {
		return jobDao.selectById(id);
	}

	@Override
	public void removeJobById(Integer id) {
		jobDao.deleteById(id);
	}

	@Override
	public void addJob(Job job) {
		jobDao.save(job);
	}

	@Override
	public void modifyJob(Job job) {
		jobDao.update(job);
	}
	/**********************************通知服务接口的实现*****************************************/
	@Override
	@Transactional(readOnly = true)
	public List<Notice> findNotices(Notice notice, PageModel pageModel) {
		Map<String, Object> params = new HashMap<>();
		params.put("notice",notice);
		int recordCount = noticeDao.count(params);
		logger.debug("recordCount -- >> " + recordCount);
		pageModel.setRecordCount(recordCount);
		if (recordCount > 0) {
			params.put("pageModel", pageModel);
		}
		List<Notice> notices = noticeDao.selectByPage(params);
		return notices;
	}

	@Override
	@Transactional(readOnly = true)
	public Notice findNoticeById(Integer id) {
		return noticeDao.selectById(id);
	}

	@Override
	public void removeNoticeById(Integer id) {
		noticeDao.deleteById(id);
	}

	@Override
	public void addNotice(Notice notice) {
		noticeDao.save(notice);
	}

	@Override
	public void modifyNotice(Notice notice) {
		noticeDao.update(notice);
	}
	/**********************************文档服务接口的实现*****************************************/
	@Override
	@Transactional(readOnly = true)
	public List<Document> findDocuments(Document document, PageModel pageModel) {
		Map<String, Object> params = new HashMap<>();
		params.put("document",document);
		int recordCount = documentDao.count(params);
		logger.debug("document recordCount -- >> " + recordCount);
		pageModel.setRecordCount(recordCount);
		if (recordCount > 0) {
			params.put("pageModel", pageModel);
		}
		List<Document> documents = documentDao.selectByPage(params);
		return documents;
	}

	@Override
	@Transactional(readOnly = true)
	public Document findDocumentById(Integer id) {
		return documentDao.selectById(id);
	}

	@Override
	public void removeDocumentById(Integer id) {
		documentDao.deleteById(id);
	}

	@Override
	public void addDocument(Document document) {
		documentDao.save(document);
	}

	@Override
	public void modifyDocument(Document document) {
		documentDao.update(document);
	}
}
