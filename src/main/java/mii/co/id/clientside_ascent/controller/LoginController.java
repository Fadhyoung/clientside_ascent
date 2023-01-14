/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.controller;

import lombok.AllArgsConstructor;
import mii.co.id.clientside_ascent.model.dto.request.LoginRequest;
import mii.co.id.clientside_ascent.service.LoginService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lenovo
 */
@Controller
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {
    
    private LoginService LoginService;
    
    @GetMapping
    public String loginView(LoginRequest loginRequest){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth instanceof AnonymousAuthenticationToken) {
            return "/login/index";
        }
        return "redirect:/aspirasiCategory";
    }
    
    @PostMapping
    public String login(LoginRequest loginRequest){
        if (!LoginService.login(loginRequest)) {
            return "redirect:/login?error=true";
        }
        return "redirect:/aspirasiCategory";
    }
}
