package stu.cn.ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stu.cn.ua.domain.Ticket;
import stu.cn.ua.mapper.TicketMapper;
import stu.cn.ua.service.FlightService;
import stu.cn.ua.service.PassengerService;
import stu.cn.ua.service.TicketService;

@Controller
public class TicketController {

    private final TicketService ticketService;
    private final PassengerService passengerService;
    private final FlightService flightService;


    @GetMapping("/tickets")
    public String getTickets(Model model){
        model.addAttribute("tickets",ticketService.findAll());
        return "/ticket/tickets";
    }

    @GetMapping("/ticket")
    public String getTicket(Model model){
        model.addAttribute("ticket",ticketService.findAll());
        return "ticket/tickets";
    }
    public TicketController(TicketService ticketService,
                            PassengerService passengerService,
                            FlightService flightService) {
        this.ticketService = ticketService;
        this.passengerService = passengerService;
        this.flightService = flightService;
    }
    @RequestMapping("/ticket/{id}")
    public String showTicketById(@PathVariable String id, Model model)
    {
        model.addAttribute("ticket",ticketService.findById(Long.parseLong(id)));
        return "ticket/ticket";
    }
    @RequestMapping("/ticket/new")
    public String newTicket(Model model){
        model.addAttribute("flights",flightService.getAll());
        model.addAttribute("passengers",passengerService.findAll());
        model.addAttribute("ticket", new TicketMapper());
        return "/ticket/addUpdateTicket";
    }
    @PostMapping
    @RequestMapping("/ticket/")
    public String saveOrUpdate(@ModelAttribute TicketMapper ticketMapper){
        Ticket ticket  = new Ticket(ticketMapper);
        Long passengerId = passengerService.findPassengerById(ticketMapper.getPassenger_id()).getPassengerId();
        Long flightId= flightService.findFlightById(ticketMapper.getFlight_id()).getFlightId();
        Ticket ticket1=ticketService.save(ticket,flightId,passengerId);
        return "redirect:"+ticket1.getTicketId();
    }
    @PostMapping
    @RequestMapping("ticket/{id}/update")
    public String updateTicket(@PathVariable String id, Model model){
        model.addAttribute("flights",flightService.getAll());
        model.addAttribute("passengers",passengerService.findAll());
        TicketMapper ticketMapper = new TicketMapper(ticketService.findById(Long.parseLong(id)));
        model.addAttribute("ticket", ticketMapper);
        return "/ticket/addUpdateTicket";
    }
    @GetMapping("ticket/delete/{id}")
    public String deleteTicketById(Model model, @PathVariable String id){
        ticketService.deleteByTicketId(Long.parseLong(id));
        return "redirect:/tickets";
    }
}