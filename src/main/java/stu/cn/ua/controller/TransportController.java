package stu.cn.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stu.cn.ua.domain.Transport;
import stu.cn.ua.mapper.TransportMapper;
import stu.cn.ua.service.TransportCategoryService;
import stu.cn.ua.service.TransportService;

@Controller
public class TransportController {
    @Autowired
    private final TransportService transportService;

    @Autowired
    private final TransportCategoryService transportCategoryService;

    public TransportController(TransportService transportService, TransportCategoryService transportCategoryService) {
        this.transportService = transportService;
        this.transportCategoryService = transportCategoryService;
    }


    @GetMapping("transports")
    public String getTransports(Model model) {
        int d = transportService.countNumberOfSits();
        model.addAttribute("sumAllSeats", transportService.sumAllSeats());
        model.addAttribute("transports", transportService.findAll());
        return "transport/transports";
    }

//    @RequestMapping("transport/{id}")
//    public String showTransportById(@PathVariable String id, Model model)
//    {
//        model.addAttribute("transport",transportService.findTransportById(Long.parseLong(id)));
//        return "transport/transport";
//    }

    @RequestMapping("transport/new")
    public String newTransport(Model model) {
        model.addAttribute("transport", new TransportMapper());
        model.addAttribute("transportcategories", transportCategoryService.findAll());
        return "transport/AddUpdateTransport";
    }

    @PostMapping
    @RequestMapping("transport/")
    public String saveOrUpdate(@ModelAttribute TransportMapper transportMapper) {
        Transport transport = new Transport(transportMapper);
        transport.setTransportCategory(
                transportCategoryService.findTransportCategoryById(transportMapper.getTransportCategoryId()));
        transportService.save(transport);
        return "redirect:/transports";
    }

    @PostMapping
    @RequestMapping("transport/{id}/update")
    public String updateTransport(@PathVariable String id, Model model) {
        TransportMapper transportMapper = new TransportMapper(transportService.findTransportById(Long.parseLong(id)));
        model.addAttribute("transportcategories", transportCategoryService.findAll());
        model.addAttribute("transport", transportMapper);
        return "transport/AddUpdateTransport";
    }

    @GetMapping("transport/delete/{id}")
    public String deleteTransportById(@PathVariable String id) {
        transportService.deleteTransportById(Long.parseLong(id));
        return "redirect:/transports";
    }
}