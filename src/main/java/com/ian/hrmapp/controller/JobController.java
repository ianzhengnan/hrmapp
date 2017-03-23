package com.ian.hrmapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ian.hrmapp.domain.Job;
import com.ian.hrmapp.service.HrmService;
import com.ian.hrmapp.util.tag.PageModel;

@Controller
public class JobController {

	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	@RequestMapping(value = "/job/selectJob")
	public String selectJob(Model model, Integer pageIndex, @ModelAttribute Job job){
		
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<Job> jobs = hrmService.findJobs(job, pageModel);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("jobs",jobs);
		return "job/job";
	}
	
	@RequestMapping(value = "/job/removeJob")
	public ModelAndView removeJob(String ids, ModelAndView mv){
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			hrmService.removeJobById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/job/selectJob");
		return mv;
	}
	
	@RequestMapping(value = "/job/addJob")
	public ModelAndView addJob(String flag, @ModelAttribute Job job, ModelAndView mv){
		if (flag.equals("1")) {
			mv.setViewName("job/showAddJob");
		}else{
			hrmService.addJob(job);
			mv.setViewName("redirect:/job/selectJob");
		}
		return mv;
	}
	
	@RequestMapping(value = "/job/updateJob")
	public ModelAndView updateJob(String flag, @ModelAttribute Job job, ModelAndView mv){
		if (flag.equals("1")) {
			mv.setViewName("job/showUpdateJob");
		}else{
			hrmService.modifyJob(job);
			mv.setViewName("redirect:/job/selectJob");
		}
		return mv;
	}
}
