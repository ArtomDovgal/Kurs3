package stu.cn.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stu.cn.ua.domain.TransportCategory;
import stu.cn.ua.service.TransportCategoryService;

@Controller
public class TransportCategoryController {

    @Autowired
    private final TransportCategoryService transportCategoryService;

    public TransportCategoryController(TransportCategoryService transportCategoryService) {
        this.transportCategoryService = transportCategoryService;
    }

    @GetMapping("/transportcategories")
    public String getTransportCategories(Model model){
        model.addAttribute("transportcategories",transportCategoryService.findAll());
        return "/transportcategory/transportcategories";
    }

    @RequestMapping("/transportcategory/transportcategory/{id}")
    public String showTransportCategoryById(@PathVariable String id, Model model)
    {
        model.addAttribute("transportcategory",transportCategoryService.findTransportCategoryById(Long.parseLong(id)));
        return "/transportcategory/transportcategory";
    }
    @RequestMapping("/transportcategory/transportcategory/new")
    public String newTransportCategory(Model model){
        model.addAttribute("transportcategory", new TransportCategory());
        return "/transportcategory/addUpdateTransportCategory";
    }
    @PostMapping
    @RequestMapping("/transportcategory/")
    public String saveOrUpdate(@ModelAttribute TransportCategory transportCategory){
        TransportCategory transportCategory1 =transportCategoryService.save(transportCategory);
        return "redirect:/transportcategory/transportcategory/"+transportCategory1.getTransportCategoryId();
    }

    @PostMapping
    @RequestMapping("/transportcategory/transportcategory/{id}/update")
    public String updatePassenger(@PathVariable String id, Model model){
        model.addAttribute("transportcategory",transportCategoryService.findTransportCategoryById(Long.parseLong(id)));
        return "/transportcategory/addUpdateTransportCategory";
    }

    @GetMapping("/transportcategory/transportcategory/delete/{id}")
    public String deletePassengerById(Model model, @PathVariable String id){
        transportCategoryService.deleteTransportCategoryById(Long.parseLong(id));
        return "redirect:/transportcategory/transportcategories";
    }
}
