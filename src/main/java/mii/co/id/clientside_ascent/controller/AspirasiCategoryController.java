/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.controller;

import lombok.AllArgsConstructor;
import mii.co.id.clientside_ascent.model.Aspirasi;
import mii.co.id.clientside_ascent.service.AspirasiCategoryService;
import mii.co.id.clientside_ascent.service.AspirasiService;
import mii.co.id.clientside_ascent.service.FileService;
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
@RequestMapping("/aspirasiCategory")
public class AspirasiCategoryController {
    
    private AspirasiCategoryService aspirasiCategoryService;
    private FileService fileService;
    private AspirasiService aspirasiService;
    
    @GetMapping
    public String home(Model model){
        model.addAttribute("aspirasiCategory", aspirasiCategoryService.getAll());
        return "index";
    }
    
    @GetMapping("/{id}")
    public String getAspirasiByCategory(@PathVariable Long id, Model model) {
        model.addAttribute("category", aspirasiCategoryService.getById(id));
        model.addAttribute("files", fileService.getByCategory(id));
        model.addAttribute("aspirasisByCategory", aspirasiCategoryService.getByAspirasiCategory(id));
        return "aspirasiCategory/index";
    }
    
}
