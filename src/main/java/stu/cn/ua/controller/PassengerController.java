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

    @PostMapping
    @RequestMapping("passenger/search/")
    public String flightsSearch(@RequestParam(name = "searchWord") String searchWord, Model model) {
        model.addAttribute("flights", passengerService.searchPassenger(searchWord));
        return "passenger/passengers";
    }

    @GetMapping("passengers")
    public String getPassengers(Model model) {
        model.addAttribute("passengers", passengerService.findAll());
        model.addAttribute("amountOfPassengerOfEachPrivilege", passengerService.countAmountOfPassengersOfEachPrivileges());
        return "passenger/passengers";
    }

    @RequestMapping("passenger/new")
    public String newPassenger(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "passenger/AddUpdatePassenger";
    }

    @PostMapping
    @RequestMapping("passenger/")
    public String saveOrUpdate(@ModelAttribute Passenger passenger) {
        passengerService.save(passenger);
        return "redirect:/passengers";
    }

    @PostMapping
    @RequestMapping("passenger/{id}/update")
    public String updatePassenger(@PathVariable String id, Model model) {
        model.addAttribute("passenger", passengerService.findPassengerById(Long.parseLong(id)));
        return "passenger/AddUpdatePassenger";
    }

    @GetMapping("passenger/delete/{id}")
    public String deletePassengerById(@PathVariable String id) {
        passengerService.deleteByPassengerId(Long.parseLong(id));
        return "redirect:/passengers";
    }

    @PostMapping
    @RequestMapping("/passengers/findByFullName/")
    public String findPassenger(@RequestParam(name = "firstname") String firstname,
                                @RequestParam(name = "lastname") String lastname, Model model) {
        model.addAttribute("passengers", passengerService.findByFirstNameAndLastName(firstname, lastname));
        return "passenger/findPassengerByFullName";
    }
}
