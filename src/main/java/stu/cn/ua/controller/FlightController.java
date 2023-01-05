package stu.cn.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stu.cn.ua.domain.Flight;
import stu.cn.ua.persistence.FlightRepository;
import stu.cn.ua.service.FlightService;
import stu.cn.ua.service.TransportService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class FlightController {

    @Autowired
    private final FlightService flightService;

    @Autowired
    private final TransportService transportService;
    private final FlightRepository flightRepository;

//    Integer numArtem;

    class Numb{
        Integer numberOfDays;

        public Integer getNumberOfDays() {
            return numberOfDays;
        }

        public void setNumberOfDays(Integer numberOfDays) {
            this.numberOfDays = numberOfDays;
        }
    }
    Numb numberOfDaysClass = new Numb();


    public FlightController(FlightService flightService, TransportService transportService,
                            FlightRepository flightRepository){
        this.flightService = flightService;
        this.transportService = transportService;
        this.flightRepository = flightRepository;
    }

    @GetMapping("flights")
    public String getFlights(Model model){
        model.addAttribute("flights", flightService.getAll());
       model.addAttribute("numberOfDaysClass",numberOfDaysClass);
       // model.addAttribute("numberOfDaysClass",numArtem);
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
        model.addAttribute("transports",transportService.findAll());
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
        model.addAttribute("transports",transportService.findAll());
        model.addAttribute("flight",flightService.findFlightById(Long.parseLong(id)));
        return "flight/AddUpdateFlight";
    }
    @GetMapping("flight/delete/{id}")
    public String deleteFlightById(Model model, @PathVariable String id){
        flightService.deleteById(Long.parseLong(id));
        return "redirect:/flights";
    }

    @PostMapping
    @RequestMapping("flights/delayflight/")
    public String delayFlights(@ModelAttribute Numb numberOfDaysClass){

        flightService.delayFlight(numberOfDaysClass.numberOfDays);
        return "redirect:/flights";
    }

//    @PostMapping
//    @RequestMapping("flights/delayflight/")
//    public String delayFlights(@ModelAttribute Integer numArtem){
//
//        flightService.delayFlight(numArtem);
//        return "redirect:/flights";
//    }


}
