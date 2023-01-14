/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.controller.api;

import lombok.AllArgsConstructor;
import mii.co.id.clientside_ascent.model.AspirasiCategory;
import mii.co.id.clientside_ascent.model.dto.request.AspirasiCategoryRequest;
import mii.co.id.clientside_ascent.model.dto.request.AspirasiRequest;
import mii.co.id.clientside_ascent.service.AspirasiCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lenovo
 */
@Controller
@AllArgsConstructor
@RequestMapping("api/aspirasiCategory")
public class AspirasiApiCategoryController {
    
    private AspirasiCategoryService aspirasiCategoryService;
    
    @GetMapping
    public String home(Model model){
        model.addAttribute("aspirasiCategory", aspirasiCategoryService.getAll());
        return "index";
    }
    
    @PostMapping
    public String create(@RequestBody AspirasiCategoryRequest aspirasiCategoryRequest){
        aspirasiCategoryService.create(aspirasiCategoryRequest);
        return "redirect:api/aspirasiCategory";
    }
    
    @PutMapping("/{id}")
    public String update(@PathVariable Long id,@RequestBody AspirasiCategoryRequest aspirasiCategoryRequest){
        aspirasiCategoryService.update(id, aspirasiCategoryRequest);
        return "redirect:api/aspirasiCategory";
    }
    
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        aspirasiCategoryService.delete(id);
        return "redirect:api/aspirasiCategory";
    }
    
    
    
}
