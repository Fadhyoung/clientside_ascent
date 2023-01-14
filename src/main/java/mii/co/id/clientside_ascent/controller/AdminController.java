/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.controller;

import lombok.AllArgsConstructor;
import mii.co.id.clientside_ascent.service.AspirasiCategoryService;
import mii.co.id.clientside_ascent.service.AspirasiService;
import mii.co.id.clientside_ascent.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lenovo
 */
@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    
    private AspirasiService aspirasiService;
    private CommentService commentService;
    private AspirasiCategoryService aspirasiCategoryService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("aspirasi", aspirasiService.getAll());
        model.addAttribute("comments", commentService.getAll());
        return "admin/index";
    }
    
    @GetMapping("/{id}")
    public String detailView(@PathVariable Long id, Model model) {
        model.addAttribute("category", aspirasiCategoryService.getById(id));
        model.addAttribute("aspirasis", aspirasiService.getAspirasissByAspirasiCategory(id));
        return "admin/index";
    }
    
    /*
    
    @GetMapping("/create")
    public String createView(AspirasiRequest aspirasiRequest, Model model){
    
    return "country/create-form";
    }
    
    @PostMapping
    public String create(AspirasiRequest aspirasiRequest){
    aspirasiService.create(aspirasiRequest);
    return "redirect:/country";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
    aspirasiService.delete(id);
    return "redirect:/country";
    }*/
}
