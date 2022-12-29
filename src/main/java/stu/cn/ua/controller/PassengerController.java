package stu.cn.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import stu.cn.ua.service.PassengerService;

@Controller
public class PassengerController {

    @Autowired
    PassengerService passengerService;
    @GetMapping({"","/","passenger"})
    public String getPassengers(Model model){
        model.addAttribute("passengers",passengerService.findAll());
        return "passenger";
    }

    @GetMapping("/passenger/delete/{id}")
    public String deletePassengerById(Model model, @PathVariable String id){
        //Long idPassenger = (Long) model.getAttribute("passenger.getPassengerId()");
        passengerService.deleteByPassengerId(Long.parseLong(id));
        return "redirect:/passenger";
    }

}