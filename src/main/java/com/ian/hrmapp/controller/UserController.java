package com.ian.hrmapp.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ian.hrmapp.domain.User;
import com.ian.hrmapp.service.HrmService;
import com.ian.hrmapp.service.impl.HrmServiceImpl;
import com.ian.hrmapp.util.common.HrmConstants;
import com.ian.hrmapp.util.tag.PageModel;

@Controller
public class UserController {
	
	private static final Logger logger = LogManager.getLogger(HrmServiceImpl.class);

	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(value = "loginname", required = false) String loginname,
							@RequestParam(value = "password", required = false) String password,
							HttpSession httpSession,
							ModelAndView mv){
		
		User user = hrmService.login(loginname, password);
		
		if (user != null) {
			httpSession.setAttribute(HrmConstants.USER_SESSION, user);
			mv.setViewName("redirect:/main");
		}else{
			mv.addObject("message", "登录名或密码错误！请重新输入！");
			mv.setViewName("forward:/loginForm");
		}
		return mv;
	}
	
	@RequestMapping(value = "/{formName}", method = RequestMethod.GET)
	public String toForm(@PathVariable String formName){
		if (formName == null || formName.equals("")) {
			formName = HrmConstants.LOGIN;
		}
		
		return formName;
	}
		
	@RequestMapping(value = "/user/selectUser")
	public String selectUser(Integer pageIndex, 
							@ModelAttribute User user, 
							Model model){
		
		logger.debug("user = " + user);
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}else{
			pageModel.setPageIndex(0);
		}
		
		List<User> users = hrmService.findUser(user, pageModel);
		model.addAttribute("users",users);
		model.addAttribute("pageModel", pageModel);
		return "user/user";
	}
	
	@RequestMapping(value = "/user/removeUser")
	public ModelAndView removeUser(String ids, ModelAndView mv){
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			hrmService.removeUserById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/user/selectUser");
		return mv;
	}
	
	@RequestMapping(value = "/user/updateUser")
	public ModelAndView updateUser(
				String flag,
				@ModelAttribute User user,
				ModelAndView mv){
		
		if (flag.equals("1")) {
			User target = hrmService.findUserById(user.getId());
			mv.addObject("user", target);
			mv.setViewName("user/showUpdateUser");
		}else{
			hrmService.modifyUser(user);
			mv.setViewName("redirect:/user/selectUser");
		}
		
		return mv;
	}
	
	@InitBinder("user")
	public void InitBinderUser(WebDataBinder binder){
		binder.setFieldDefaultPrefix("user.");
	}
	
	@RequestMapping(value = "/user/addUser")
	public ModelAndView addUser(String flag, @ModelAttribute User user, ModelAndView mv){
		
		if (flag.equals("1")) {
			mv.setViewName("user/showAddUser");
		}else{
			hrmService.addUser(user);
			mv.setViewName("redirect:/user/selectUser");
		}
		return mv;
	}
	
}
