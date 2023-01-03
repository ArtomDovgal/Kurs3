package stu.cn.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stu.cn.ua.domain.Flight;
import stu.cn.ua.service.FlightService;

@Controller
public class FlightController {

    @Autowired
    private final FlightService flightService;

    public FlightController(FlightService flightService){
        this.flightService = flightService;
    }

    @GetMapping("flights")
    public String getFlights(Model model){
        model.addAttribute("flights", flightService.getAll());
        return "flight/flights";
    }

    @RequestMapping("flight/{id}")
    public String showFlightById(@PathVariable String id, Model model)
    {
        model.addAttribute("flight", flightService.findFlightById(Long.parseLong(id)));
        return "flight/flight";
    }
    @RequestMapping("flight/new")
    public String newFlight(Model model){
        model.addAttribute("flight", new Flight());
        return "flight/AddUpdateFlight";
    }

    @PostMapping
    @RequestMapping("flight/")
    public String saveOrUpdate(@ModelAttribute Flight flight){
        Flight flight1=flightService.save(flight);
        return "redirect:"+flight1.getFlightId();
    }

    @PostMapping
    @RequestMapping("flight/{id}/update")
    public String updateFlight(@PathVariable String id, Model model){
        model.addAttribute("flight",flightService.findFlightById(Long.parseLong(id)));
        return "flight/AddUpdateFlight";
    }
    @GetMapping("flight/delete/{id}")
    public String deleteFlightById(Model model, @PathVariable String id){
        flightService.deleteById(Long.parseLong(id));
        return "redirect:/flights";
    }
}
