/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.controller;

import lombok.AllArgsConstructor;
import mii.co.id.clientside_ascent.model.Aspirasi;
import mii.co.id.clientside_ascent.model.dto.request.LoginRequest;
import mii.co.id.clientside_ascent.model.dto.request.UserRequest;
import mii.co.id.clientside_ascent.service.AspirasiService;
import mii.co.id.clientside_ascent.service.CommentService;
import mii.co.id.clientside_ascent.service.LoginService;
import mii.co.id.clientside_ascent.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lenovo
 */
@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    
    private AspirasiService aspirasiService;
    private CommentService commentService;
    private UserService userService;
    private LoginService loginService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("user", aspirasiService.getAll());
        return "user/index";
    }
    
    @GetMapping("/updateForm/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        model.addAttribute("user",userService.getById(id));
        return "./user/update-user" ;
    }
    
    @GetMapping("/detailuser/{name}")
    public String index(@PathVariable String name ,Model model){
        Aspirasi a = new Aspirasi();
        model.addAttribute("user", userService.getByName(name));
        model.addAttribute("aspirasis", aspirasiService.getAspirasissByUser(name));
        return "user/index";
    }
    
    @GetMapping("/all-user")
    public String allUser(Model model){
        model.addAttribute("users", userService.getAll());
        return "user/get-all-user";
    }
    
    @GetMapping("/{id}")
    public String detailView(@PathVariable Long id, Model model) {
        model.addAttribute("aspirasi", aspirasiService.getById(id));
        model.addAttribute("comments", commentService.getCommentsByAspirasi(id));
        return "aspirasi/index";
    }
    
    @GetMapping("/createUser")
    public String createView(LoginRequest loginRequest, Model model){
        return "user/create-user";
    }
    
    /*
    
    
    
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
    aspirasiService.delete(id);
    return "redirect:/country";
    }*/
}
