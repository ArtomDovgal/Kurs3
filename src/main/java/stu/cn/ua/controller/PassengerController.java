package stu.cn.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import stu.cn.ua.service.PassengerService;

@Controller
public class PassengerController {

    @Autowired
    PassengerService passengerService;
    @GetMapping({"","/","passengers"})
    public String getPassengers(Model model){
        model.addAttribute("passengers",passengerService.findAll());
        return "passenger";
  }

}
