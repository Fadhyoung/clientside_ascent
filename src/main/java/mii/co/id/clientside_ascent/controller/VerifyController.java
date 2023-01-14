/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mii.co.id.clientside_ascent.model.dto.request.GeneralDTO;
import mii.co.id.clientside_ascent.model.dto.request.LoginRequest;
import mii.co.id.clientside_ascent.service.LoginService;
import mii.co.id.clientside_ascent.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lenovo
 */
@Controller
@AllArgsConstructor
@RequestMapping("/verify")
public class VerifyController {
    
    private UserService userService;
    private LoginService loginService;
    
    @GetMapping("/verifySuccess")
    public String verifySuccess(HttpServletRequest request, HttpServletResponse response){
        return "user/verifySuccess";
    }
    
    @GetMapping
    public String verifyUser(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response, LoginRequest loginRequest, GeneralDTO generalDTO, BindingResult result) {
        
        
        
        System.out.println("verify Controller" + code);
        loginRequest.setUsername("Aegon");
        loginRequest.setPassword("Aegon12345");
        loginService.login(loginRequest);
        generalDTO.setStrValue(code);
        Boolean user = userService.getByVerificationCode(generalDTO);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (result.hasErrors()) {
            return "errors/errorVerify";
        }
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        if (user) {
            System.out.println("true");
            return "/user/verifySuccess";
        } else {
            System.out.println("false");
            return "verify_fail";
        }
        
    }
        
    
}
