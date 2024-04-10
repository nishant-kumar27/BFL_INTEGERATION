package com.bfl.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserLoginController {

	@RequestMapping("/")
	public String root() {
		return "redirect:/home";
	}
	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView view = new ModelAndView();
		view.setViewName("home");
		return view;
	}

	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@RequestMapping("/user")
	public String userIndex() {
		return "user/home";
	}
	/*@RequestMapping(value = "/{[path:[^\\.]*}")
	public String redirect() {
		return "redirect:/home";
	} */
}
