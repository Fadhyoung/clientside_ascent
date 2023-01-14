/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.controller;

import lombok.AllArgsConstructor;
import mii.co.id.clientside_ascent.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lenovo
 */
@Controller
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    
    private CommentService commentService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("comment", commentService.getAll());
        return "aspirasi/index";
    }
}
