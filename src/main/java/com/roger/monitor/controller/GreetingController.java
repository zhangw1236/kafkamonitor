package com.roger.monitor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.roger.monitor.service.UserMapperService;
import com.roger.monitor.vo.Employee;

@Controller
@RequestMapping("/greeting")
public class GreetingController {
	private static Logger logger = LoggerFactory.getLogger(GreetingController.class);
	@Autowired
	private UserMapperService userMapperService;

	@RequestMapping(method = RequestMethod.GET)
	public String greeting(@RequestParam(value = "username", required = false, defaultValue = "0") String name, Model model) {
		Employee e = userMapperService.findByFirstName(name);
		if(e == null) {
			model.addAttribute("name", "World");
		} else {
			model.addAttribute("name", e.getSecondName());
		}
		return "greeting";
	}
}