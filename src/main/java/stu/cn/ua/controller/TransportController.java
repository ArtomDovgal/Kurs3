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


    @GetMapping("/transports")
    public String getTransports(Model model){
        int d=transportService.countNumberOfSits();
        model.addAttribute("transports",transportService.findAll());
        return "/transport/transports";
    }

    @RequestMapping("transport/{id}")
    public String showTransportById(@PathVariable String id, Model model)
    {
        model.addAttribute("transport",transportService.findTransportById(Long.parseLong(id)));
        return "transport/transport";
    }
    @RequestMapping("transport/new")
    public String newTransport(Model model){
        model.addAttribute("transport", new Transport());
        return "transport/addUpdateTransport";
    }
    @PostMapping
    @RequestMapping("transport/")
    public String saveOrUpdate(@ModelAttribute Transport transport){
        Transport transport1 =transportService.save(transport);
        return "redirect:"+transport1.getTransportId();
    }

    @PostMapping
    @RequestMapping("transport/{id}/update")
    public String updatePassenger(@PathVariable String id, Model model){
        model.addAttribute("transport",transportService.findTransportById(Long.parseLong(id)));
        return "/transport/addUpdateTransport";
    }
    @GetMapping("transport/delete/{id}")
    public String deletePassengerById(Model model, @PathVariable String id){
        transportService.deleteTransportById(Long.parseLong(id));
        return "redirect:/transports";
    }
}
