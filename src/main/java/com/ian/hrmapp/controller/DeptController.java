package com.ian.hrmapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ian.hrmapp.domain.Dept;
import com.ian.hrmapp.service.HrmService;
import com.ian.hrmapp.util.tag.PageModel;

@Controller
public class DeptController {

	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	@RequestMapping(value = "/dept/selectDept")
	public String selectDept(Model model, Integer pageIndex, @ModelAttribute Dept dept){
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex); 
		}
		List<Dept> depts = hrmService.findDept(dept, pageModel);
		model.addAttribute("depts", depts);
		model.addAttribute("pageModel", pageModel);
		return "dept/dept";
	}
	
	@RequestMapping(value = "/dept/removeDept")
	public ModelAndView removeDept(String ids, ModelAndView mv){
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			hrmService.removeDeptById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/dept/selectDept");
		return mv;
		
	}
	
	@RequestMapping(value = "/dept/addDept")
	public ModelAndView addDept(String flag, @ModelAttribute Dept dept, ModelAndView mv){
		if(flag.equals("1")){
			mv.setViewName("dept/showAddDept");
		}else{
			hrmService.addDept(dept);
			mv.setViewName("redirect:/dept/selectDept");
		}
		return mv;
	}
	
	@RequestMapping(value = "/dept/updateDept")
	public ModelAndView updateDept(String flag, @ModelAttribute Dept dept, ModelAndView mv){
		if (flag.equals("1")) {
			Dept target = hrmService.findDeptById(dept.getId());
			mv.addObject("dept", target);
			mv.setViewName("dept/showUpdateDept");
		}else{
			hrmService.modifyDept(dept);
			mv.setViewName("redirect:/dept/selectDept");
		}
		return mv;
	}
	
}
