/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.controller.api;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mii.co.id.clientside_ascent.controller.LogoutController;
import mii.co.id.clientside_ascent.model.User;
import mii.co.id.clientside_ascent.model.dto.request.LoginRequest;
import mii.co.id.clientside_ascent.model.dto.request.UserRequest;
import mii.co.id.clientside_ascent.service.LoginService;
import mii.co.id.clientside_ascent.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author lenovo
 */

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserApiController {
    
    private UserService userService;
    private LogoutController logoutController;
    private LoginService loginService;
    
    @PostMapping()
    public String create(HttpServletRequest request, HttpServletResponse response,LoginRequest loginRequest, @RequestBody UserRequest userRequest){
        System.out.println("test dulu");
        System.out.println(userRequest.getUsername());
        loginRequest.setUsername("Aegon");
        loginRequest.setPassword("Aegon12345");
        loginService.login(loginRequest);
        userService.create(userRequest);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("redy to log out");
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        System.out.println("success create user");
        return "/login";
    }
    
    @PutMapping("/updateUser/{id}")
    public User update( @PathVariable Long id ,@RequestBody UserRequest userRequest){
        System.out.println("update service controller");
        return userService.update(id, userRequest);
    }
    
    
    
    @DeleteMapping("/delete/{id}")
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            userService.delete(id);
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }
    
    @PostMapping("/register")
    @ResponseBody
    public String processRegister(HttpServletRequest request, HttpServletResponse response,LoginRequest loginRequest, @RequestBody UserRequest userRequest) {
        System.out.println("masuk kontroller register");
        System.out.println(userRequest.getUsername());
        loginRequest.setUsername("Aegon");
        loginRequest.setPassword("Aegon12345");
        loginService.login(loginRequest);
        
        try {
            userRequest.setUrl(getSiteURL(request));
            userService.register(userRequest);  
        } catch (Exception e) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("redy to log out");
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User " + userRequest.getNim() + " is already exists!");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("redy to log out");
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        System.out.println("success create user");
        String n1 = request.getParameter("/login");
        return n1 ;
    }
     
    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }  
    
    @GetMapping("/all-user")
    public List<User> allUser(){
        return userService.getAll();
    }
    
}
