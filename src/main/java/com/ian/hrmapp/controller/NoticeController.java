package com.ian.hrmapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ian.hrmapp.domain.Notice;
import com.ian.hrmapp.service.HrmService;

@Controller
public class NoticeController {

	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	@RequestMapping(value = "/notice/selectNotice")
	public String selectNotice(Model model, Integer pageIndex, @ModelAttribute Notice notice){
		
		return "notice/notice";
	}
}
