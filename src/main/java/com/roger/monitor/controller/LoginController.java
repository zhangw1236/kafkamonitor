package com.roger.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roger.monitor.vo.Login;

@Controller
public class LoginController {
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String doLogin(Model model) {
		return "login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String doLogin(Model model, Login login) {
		System.out.println("I am here" + login.getUsername() + ":" + login.getPassword());
		model.addAttribute("username", "roger");
		return "redirect:/greeting";
	}
}
