package stu.cn.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stu.cn.ua.domain.Passenger;
import stu.cn.ua.domain.Ticket;
import stu.cn.ua.persistence.FlightRepository;
import stu.cn.ua.persistence.PassengerRepository;
import stu.cn.ua.persistence.TicketRepository;
import stu.cn.ua.service.FlightService;
import stu.cn.ua.service.PassengerService;
import stu.cn.ua.service.TicketService;

@Controller
public class TicketController {

    private final TicketService ticketService;
    private final PassengerService passengerService;
    private final FlightService flightService;

    @GetMapping("/ticket/ticket")
    public String getTicket(Model model){
        model.addAttribute("ticket",ticketService.findAll());
        return "ticket/ticket";
    }
    public TicketController(TicketService ticketService,
                            PassengerService passengerService,
                            FlightService flightService) {
        this.ticketService = ticketService;
        this.passengerService = passengerService;
        this.flightService = flightService;
    }
    @RequestMapping("/ticket/ticketShow/{id}")
    public String showTicketById(@PathVariable String id, Model model)
    {
        model.addAttribute("ticket",ticketService.findById(Long.parseLong(id)));
        return "/ticket/ticketShow";
    }
    @RequestMapping("/ticket/ticket/new")
    public String newTicket(Model model){
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("ticket", new Ticket());
        return "/ticket/addUpdateTicket";
    }
    @PostMapping
    @RequestMapping("/ticket/ticket/")
    public String saveOrUpdate(@ModelAttribute Ticket ticket){
        Long flightId=ticket.getFlight().getFlightId();
        Long passengerId=ticket.getPassenger().getPassengerId();
        Ticket ticket1=ticketService.save(ticket,flightId,passengerId);
        return "redirect:/ticket/ticketShow/"+ticket1.getTicketId();
    }
    @PostMapping
    @RequestMapping("/ticket/ticket/{id}/update")
    public String updateTicket(@PathVariable String id, Model model){
        model.addAttribute("ticket",ticketService.findById(Long.parseLong(id)));
        return "/ticket/addUpdateTicket";
    }
    @GetMapping("/ticket/ticket/delete/{id}")
    public String deleteTicketById(Model model, @PathVariable String id){
        ticketService.deleteByTicketId(Long.parseLong(id));
        return "redirect:/ticket/ticket";
    }
}