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

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("passengers")
    public String getPassengers(Model model){
        model.addAttribute("passengers",passengerService.findAll());
        return "passenger/passengers";
    }
    @RequestMapping("passenger/{id}")
    public String showPassengerById(@PathVariable String id, Model model)
    {

        model.addAttribute("passenger",passengerService.findPassengerById(Long.parseLong(id)));
        return "passenger/passenger";
    }
    @RequestMapping("passenger/new")
    public String newPassenger(Model model){
        model.addAttribute("passenger", new Passenger());
        return "passenger/addUpdatePassenger";
    }
    @PostMapping
    @RequestMapping("passenger/")
    public String saveOrUpdate(@ModelAttribute Passenger passenger){
        Passenger passenger1=passengerService.save(passenger);
        return "redirect:"+passenger1.getPassengerId();
    }
    @PostMapping
    @RequestMapping("passenger/{id}/update")
    public String updatePassenger(@PathVariable String id, Model model){
        model.addAttribute("passenger",passengerService.findPassengerById(Long.parseLong(id)));
        return "/passenger/addUpdatePassenger";
    }
    @GetMapping("passenger/delete/{id}")
    public String deletePassengerById(Model model, @PathVariable String id){
        passengerService.deleteByPassengerId(Long.parseLong(id));
        return "redirect:/passengers";
    }

    @PostMapping
    @RequestMapping("/passengers/findByFullName/")
    public String updatePassenger(@RequestParam(name = "firstname") String firstname,
                                  @RequestParam(name = "lastname") String lastname, Model model){
        model.addAttribute("passengers",passengerService.findByFirstNameAndLastName(firstname,lastname));
        return "passenger/passengers";
    }
}