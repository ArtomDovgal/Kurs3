package stu.cn.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stu.cn.ua.domain.Transport;
import stu.cn.ua.service.TransportService;

@Controller
public class TransportController {
    @Autowired
    private final TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }


    @GetMapping("transports")
    public String getTransports(Model model){
        int d=transportService.countNumberOfSits();
        model.addAttribute("sumAllSeats",transportService.sumAllSeats());
        model.addAttribute("transports",transportService.findAll());
        return "transport/transports";
    }

//    @RequestMapping("transport/{id}")
//    public String showTransportById(@PathVariable String id, Model model)
//    {
//        model.addAttribute("transport",transportService.findTransportById(Long.parseLong(id)));
//        return "transport/transport";
//    }

    @RequestMapping("transport/new")
    public String newTransport(Model model){
        model.addAttribute("transport", new Transport());
        return "transport/AddUpdateTransport";
    }

    @PostMapping
    @RequestMapping("transport/")
    public String saveOrUpdate(@ModelAttribute Transport transport){
        transportService.save(transport);
        return "redirect:/transports";
    }

    @PostMapping
    @RequestMapping("transport/{id}/update")
    public String updateTransport(@PathVariable String id, Model model){
        model.addAttribute("transport",transportService.findTransportById(Long.parseLong(id)));
        return "transport/AddUpdateTransport";
    }
    @GetMapping("transport/delete/{id}")
    public String deleteTransportById( @PathVariable String id){
        transportService.deleteTransportById(Long.parseLong(id));
        return "redirect:/transports";
    }

//    @PostMapping
//    @RequestMapping("transports/sumSeats/")
//    public String sumSeats(Model model){
//        model.addAttribute("transports",transportService.findAll());
//        model.addAttribute("sumAllSeats",transportService.sumAllSeats());
//        return "transport/transports";
//    }
}