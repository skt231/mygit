package com.drug.ex;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.drug.dto.AdminDto;
import com.drug.ex.HomeController;
import com.drug.service.IAdminService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private IAdminService service;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "redirect:admin/selectAll";
	}
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "redirect:admin/selectAll";
	}
	@RequestMapping(value = "/admin/update", method = RequestMethod.GET)
	public String update(AdminDto adminDto) {
		System.out.println(adminDto);
		return "admin/update";
	}
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	public String updateDB(AdminDto adminDto,RedirectAttributes rttr) throws Exception{
		service.update(adminDto);
		rttr.addFlashAttribute("msg","success");
		return "redirect:/admin/selectAll";
	}
	
	@RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
	public String delete(String id,RedirectAttributes rttr) throws Exception {
		service.delete(id);
		rttr.addFlashAttribute("msg","success");
		return "redirect:/admin/selectAll";
	}
	
	@RequestMapping(value = "/admin/selectId", method = RequestMethod.GET)
	public String selectName(String id,Model model) throws Exception {
		model.addAttribute("dto", service.selectId(id) );
		return "admin/selectId";
	}
	
	@RequestMapping(value = "/admin/selectAll", method = RequestMethod.GET)
	public String selectAll(Model model) throws Exception {
		model.addAttribute("list", service.selectAll() );
		return "admin/selectAll";
	}
	@RequestMapping(value = "/admin/insert", method = RequestMethod.GET)
	public String insert() {
		return "admin/insert";
	}
	@RequestMapping(value = "/admin/insert", method = RequestMethod.POST)
	public String insertDB(AdminDto adminDto,RedirectAttributes rttr) throws Exception{
		service.insert(adminDto);
		rttr.addFlashAttribute("msg","success");
		return "redirect:/admin/selectAll";
	}
	
}