package stu.cn.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stu.cn.ua.domain.Passenger;
import stu.cn.ua.service.PassengerService;

@Controller
public class PassengerController {

    @Autowired
    private PassengerService passengerService;
    @GetMapping("passenger")
    public String getPassengers(Model model){
        model.addAttribute("passengers",passengerService.findAll());
        return "passenger";
    }
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }
    @RequestMapping("/passengerShow/{id}")
    public String showPassengerById(@PathVariable String id, Model model)
    {
        model.addAttribute("passenger",passengerService.findPassengerById(Long.parseLong(id)));
        return "passengerShow";
    }
    @RequestMapping("passenger/new")
    public String newPassenger(Model model){
        model.addAttribute("passenger", new Passenger());
        return "addUpdatePassenger";
    }
    @PostMapping
    @RequestMapping("/passenger/")
    public String saveOrUpdate(@ModelAttribute Passenger passenger){
        Passenger passenger1=passengerService.save(passenger);
        return "redirect:/passengerShow/"+passenger1.getPassengerId();
    }
    @PostMapping
    @RequestMapping("/passenger/{id}/update")
    public String updatePassenger(@PathVariable String id, Model model){
model.addAttribute("passenger",passengerService.findPassengerById(Long.parseLong(id)));
        return "addUpdatePassenger";
    }
    @GetMapping("/passenger/delete/{id}")
    public String deletePassengerById(Model model, @PathVariable String id){
        passengerService.deleteByPassengerId(Long.parseLong(id));
        return "redirect:/passenger";
    }
}