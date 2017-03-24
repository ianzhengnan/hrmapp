package com.ian.hrmapp.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ian.hrmapp.domain.Document;
import com.ian.hrmapp.domain.User;
import com.ian.hrmapp.service.HrmService;
import com.ian.hrmapp.util.common.HrmConstants;
import com.ian.hrmapp.util.tag.PageModel;


@Controller
public class DocumentController {

	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;

	@RequestMapping(value = "/document/selectDocument")
	public String selectDocument(
			Model model, 
			Integer pageIndex,
			@ModelAttribute Document document){
		
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<Document> documents = hrmService.findDocuments(document, pageModel);
		model.addAttribute("documents", documents);
		model.addAttribute("pageModel", pageModel);
		
		return "document/document";
	}
	
	@RequestMapping(value = "/document/addDocument")
	public ModelAndView addDocument(
			String flag,
			@ModelAttribute Document document,
			ModelAndView mv,
			HttpSession session) throws Exception{
		
		if (flag.equals("1")) {
			mv.setViewName("document/showAddDocument");
		}else{
			String path = session.getServletContext().getRealPath("/upload/");
			String fileName = document.getFile().getOriginalFilename();
			document.getFile().transferTo(new File(path + File.separator + fileName));
			document.setFileName(fileName);
			User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
			document.setUser(user);
			hrmService.addDocument(document);
			mv.setViewName("redirect:/document/selectDocument");
		}
		return mv;
	}
	
	@RequestMapping(value = "/document/removeDocument")
	public ModelAndView removeDocument(String ids, ModelAndView mv){
		
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			hrmService.removeDocumentById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/document/selectDocument");
		return mv;
	}
	
	@RequestMapping(value = "/document/updateDocument")
	public ModelAndView updateDocument(
			String flag,
			@ModelAttribute Document document,
			ModelAndView mv,
			HttpSession session) throws Exception{
		if (flag.equals("1")) {
			Document target = hrmService.findDocumentById(document.getId());
			mv.addObject("document", target);
			mv.setViewName("document/showUpdateDocument");
		}else{
			String path = session.getServletContext().getRealPath("/upload/");
			String fileName = document.getFile().getOriginalFilename();
			if (fileName != null) {
				document.getFile().transferTo(new File(path + File.separator + fileName));
				document.setFileName(fileName);				
			}
			hrmService.modifyDocument(document);
			mv.setViewName("redirect:/document/selectDocument");
		}
		return mv;
	}
	
	@RequestMapping(value = "/document/downLoad")
	public ResponseEntity<byte[]> downLoad(
			Integer id,
			HttpSession session) throws Exception{
		
		Document target = hrmService.findDocumentById(id);
		String fileName = target.getFileName();
		String path = session.getServletContext().getRealPath("/upload/");
		
		File file = new File(path + File.separator + fileName);
		
		HttpHeaders headers = new HttpHeaders();
		
		String downloadFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		
		headers.setContentDispositionFormData("attachment", downloadFileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
		
	}
}
