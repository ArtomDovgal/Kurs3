package stu.cn.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stu.cn.ua.domain.Flight;
import stu.cn.ua.mapper.FlightMapper;
import stu.cn.ua.mapper.TicketMapper;
import stu.cn.ua.persistence.FlightRepository;
import stu.cn.ua.service.FlightLogService;
import stu.cn.ua.service.FlightService;
import stu.cn.ua.service.PassengerService;
import stu.cn.ua.service.TransportService;

import java.util.HashSet;

@Controller
public class FlightController {

    private final FlightService flightService;
    private final TransportService transportService;
    private final PassengerService passengerService;
    private final FlightLogService flightLogService;

    public FlightController(FlightService flightService, TransportService transportService,
                            FlightRepository flightRepository, PassengerService passengerService, FlightLogService flightLogService){
        this.flightService = flightService;
        this.transportService = transportService;
        this.passengerService = passengerService;
        this.flightLogService = flightLogService;
    }

    @PostMapping("flights/logs")
    public String getFlightsLog(Model model){
        model.addAttribute("flightLogs", flightLogService.getAll());
        return "flightLog/flightLogs";
    }

    @GetMapping("flights")
    public String getFlights(Model model){
        model.addAttribute("flights", flightService.getAll());
        model.addAttribute("hours",Integer.valueOf(0));
        model.addAttribute("arrivalPoints",flightService.getAllArrivalPoints());
        return "flight/flights";
    }

    @RequestMapping("flight/new")
    public String newFlight(Model model){
        model.addAttribute("transports",transportService.findAll());
        model.addAttribute("flight", new FlightMapper());
        return "flight/AddUpdateFlight";
    }

    @PostMapping
    @RequestMapping("/flight/")
    public String saveOrUpdate(@ModelAttribute FlightMapper flightMapper){
        Flight flight = new Flight(flightMapper);
        flight.setTransports(flightMapper.getTransportsByTransportIds(transportService));
        flightService.save(flight);
        return "redirect:/flights";
    }

    @PostMapping
    @RequestMapping("flight/{id}/update")
    public String updateFlight(@PathVariable String id, Model model){
        FlightMapper flightMapper = new FlightMapper(flightService.findFlightById(Long.parseLong(id)));
        model.addAttribute("transports",transportService.findAll());
        model.addAttribute("flight",flightMapper);
        return "flight/AddUpdateFlight";
    }

    @GetMapping("flight/delete/{id}")
    public String deleteFlightById(Model model, @PathVariable String id){
        flightService.deleteById(Long.parseLong(id));
        return "redirect:/flights";
    }

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
    public String delayFlights(@RequestParam Integer hours,@RequestParam String arrivalPoint){

        flightService.delayFlight(hours,arrivalPoint);
        return "redirect:/flights";
    }


    @PostMapping
    @RequestMapping("flights/search/")
    public String flightsSearch(@RequestParam(name = "searchWord") String searchWord,Model model){
        model.addAttribute("flights",flightService.searchFlights(searchWord));
        model.addAttribute("arrivalPoints",flightService.getAllArrivalPoints());
        return "flight/flights";
    }
    @RequestMapping("/ticket/new/{flightId}")
    public String newTicket(@PathVariable String flightId, Model model){
        Flight flight1=flightService.findFlightById(Long.parseLong(flightId));
        model.addAttribute("flights", new HashSet<Flight>(){{
        add(flight1);
        }});
        model.addAttribute("passengers",passengerService.findAll());
        model.addAttribute("ticket", new TicketMapper());
        return "/ticket/addUpdateTicket";
    }



}
