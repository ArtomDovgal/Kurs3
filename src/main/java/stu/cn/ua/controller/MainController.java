package stu.cn.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import stu.cn.ua.domain.User;
import stu.cn.ua.service.FlightService;
import stu.cn.ua.service.UserServiceImpl;

@Controller
public class MainController {

	@Autowired
	UserServiceImpl userService;

	String string;

	@GetMapping("/login")
	public String login() {
		return "security/login";
	}

	@GetMapping("/")
	public String home() {
		return "redirect:/flights";
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
}
