package stu.cn.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stu.cn.ua.domain.Transport;
import stu.cn.ua.domain.TransportCategory;
import stu.cn.ua.service.TransportCategoryService;
import stu.cn.ua.service.TransportService;

import java.util.HashSet;

@Controller
public class TransportCategoryController {

    @Autowired
    private final TransportCategoryService transportCategoryService;
    private final TransportService transportService;

    public TransportCategoryController(TransportCategoryService transportCategoryService,
                                       TransportService transportService) {
        this.transportCategoryService = transportCategoryService;
        this.transportService = transportService;
    }

    @GetMapping("/transportcategories")
    public String getTransportCategories(Model model){
        model.addAttribute("transportcategories",transportCategoryService.findAll());
        model.addAttribute("amountOfTransportsOfEachVehicleType",transportCategoryService.countAmountOfTransportsOfEachVehicleType());

        return "/transportcategory/transportcategories";
    }

    @RequestMapping("transportcategory/{id}")
    public String showTransportCategoryById(@PathVariable String id, Model model)
    {
        model.addAttribute("transportcategory",transportCategoryService.findTransportCategoryById(Long.parseLong(id)));
        return "/transportcategory/transportcategory";
    }
    @RequestMapping("/transportcategory/new")
    public String newTransportCategory(Model model){
        model.addAttribute("transportcategory", new TransportCategory());
        model.addAttribute("allTransports", transportService.findAll());
        model.addAttribute("transportsToAdd", new HashSet<Transport>());
        return "/transportcategory/addUpdateTransportCategory";
    }
    @PostMapping
    @RequestMapping("transportcategory/")
    public String saveOrUpdate(@ModelAttribute TransportCategory transportCategory){
        TransportCategory transportCategory1 =transportCategoryService.save(transportCategory);
        return "redirect:"+transportCategory1.getTransportCategoryId();
    }

    @PostMapping
    @RequestMapping("/transportcategory/{id}/update")
    public String updatePassenger(@PathVariable String id, Model model){
        model.addAttribute("transportcategory",transportCategoryService.findTransportCategoryById(Long.parseLong(id)));
        return "/transportcategory/addUpdateTransportCategory";
    }

    @GetMapping("transportcategory/delete/{id}")
    public String deletePassengerById(Model model, @PathVariable String id){
        transportCategoryService.deleteTransportCategoryById(Long.parseLong(id));
        return "redirect:/transportcategories";
    }
}
