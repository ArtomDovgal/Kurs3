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
	@Autowired
	private final FlightService flightService;

	String string;

	public MainController(FlightService flightService) {
		this.flightService = flightService;
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home(@RequestParam("username") String username,Model model) {
		User user = userService.findByEmail(username);
		model.addAttribute("user",user);
		model.addAttribute("flights", flightService.getAll());
		model.addAttribute("hours",Integer.valueOf(0));
		model.addAttribute("arrivalPoints",flightService.getAllArrivalPoints());
		return "flight/flights";
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
}
