package stu.cn.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stu.cn.ua.domain.Flight;
import stu.cn.ua.persistence.FlightRepository;
import stu.cn.ua.service.FlightService;
import stu.cn.ua.service.TransportService;

@Controller
public class FlightController {

    @Autowired
    private final FlightService flightService;

    @Autowired
    private final TransportService transportService;
    private final FlightRepository flightRepository;

    public FlightController(FlightService flightService, TransportService transportService,
                            FlightRepository flightRepository){
        this.flightService = flightService;
        this.transportService = transportService;
        this.flightRepository = flightRepository;
    }


    @GetMapping("flights")
    public String getFlights(Model model){
        model.addAttribute("flights", flightService.getAll());
       model.addAttribute("hours",Integer.valueOf(0));
       model.addAttribute("arrivalPoints",flightService.getAllArrivalPoints());
        return "flight/flights";
    }

//    @RequestMapping("flight/{id}")
//    public String showFlightById(@PathVariable String id, Model model)
//    {
//        model.addAttribute("flight", flightService.findFlightById(Long.parseLong(id)));
//        return "flight/flights";
//    }

    @RequestMapping("flight/new")
    public String newFlight(Model model){
        model.addAttribute("transports",transportService.findAll());
        model.addAttribute("flight", new Flight());
        return "flight/AddUpdateFlight";
    }

    @PostMapping
    @RequestMapping("flight/")
    public String saveOrUpdate(@ModelAttribute Flight flight){
        flightService.save(flight);
        return "redirect:/flights";
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

//    @GetMapping
//    @RequestMapping("flight/flight/")
//    public String  toFlights()
//    {
//        return "redirect:/flights";
//    }
    @PostMapping
    @RequestMapping("flights/byarrivalpoint/")
    public String flightsByArrivalPoint(@RequestParam String arrivalPoint,Model model){
        model.addAttribute("hours",Integer.valueOf(0));
        model.addAttribute("flights",flightService.findAllByArrivalPoint(arrivalPoint));
        model.addAttribute("arrivalPoints",flightService.getAllArrivalPoints());
        return "flight/flightByArrivalPoint";
    }

    @PostMapping
    @RequestMapping("flights/delayflight/")
    public String delayFlights(@RequestParam Integer hours){

        flightService.delayFlight(hours);
        return "redirect:/flights";
    }

@PostMapping
@RequestMapping("flights/search/")
public String flightsSearch(@RequestParam(name = "searchWord") String searchWord,Model model){
    model.addAttribute("flights",flightService.searchFlights(searchWord));
  //  model.addAttribute("numberOfDaysClass",numberOfDaysClass);
    model.addAttribute("arrivalPoints",flightService.getAllArrivalPoints());
    // model.addAttribute("numberOfDaysClass",numArtem);
    return "flight/flights";
}

}
