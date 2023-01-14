/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import mii.co.id.clientside_ascent.model.Aspirasi;
import mii.co.id.clientside_ascent.model.Comment;
import mii.co.id.clientside_ascent.model.dto.request.AspirasiRequest;
import mii.co.id.clientside_ascent.service.AspirasiService;
import mii.co.id.clientside_ascent.service.CommentService;
import mii.co.id.clientside_ascent.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lenovo
 */
@Controller
@AllArgsConstructor
@RequestMapping("/aspirasi")
public class AspirasiController {
    
    private AspirasiService aspirasiService;
    private CommentService commentService;
    private FileService fileService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("aspirasi", aspirasiService.getAll());
        model.addAttribute("comments", commentService.getAll());
        return "aspirasi/all";
    }
    
    @GetMapping("/all-aspirasi")
    public String getAllAspirasi() {
        return "aspirasi/get-all-aspirasi";
    }
    
    @GetMapping("/{id}")
    public String detailView(@PathVariable Long id, Model model) {
        model.addAttribute("aspirasi", aspirasiService.getById(id));
        model.addAttribute("listOfFile", fileService.getByAspirasi(id));
        model.addAttribute("comments", commentService.getCommentsByAspirasi(id));
        return "aspirasi/index";
    }
    
    @GetMapping("/approved")
    public String approvedView(Model model) {
        model.addAttribute("aspirasis", aspirasiService.getApprovedAspirasis());
        return "aspirasi/approved";
    }
    
    @GetMapping("/rejected")
    public String rejectedView(Model model) {
        model.addAttribute("aspirasis", aspirasiService.getRejectedAspirasis());
        return "aspirasi/rejected";
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

