/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.controller.api;

import java.util.List;
import lombok.AllArgsConstructor;
import mii.co.id.clientside_ascent.model.Aspirasi;
import mii.co.id.clientside_ascent.model.Comment;
import mii.co.id.clientside_ascent.model.User;
import mii.co.id.clientside_ascent.model.dto.request.AspirasiRequest;
import mii.co.id.clientside_ascent.service.AspirasiService;
import mii.co.id.clientside_ascent.service.CommentService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lenovo
 */
@RestController
@RequestMapping("/api/aspirasi")
@AllArgsConstructor
public class AspirasiApiController {
    
    private AspirasiService aspirasiService;
    private CommentService commentService;

    @GetMapping
    public List<Aspirasi> getAll() {
        return aspirasiService.getAll();
    }
    
    @GetMapping("/all-aspirasi")
    public List<Aspirasi> allUser(){
        return aspirasiService.getAll();
    }
    
    @GetMapping("/getByCategory/{id}")
    public List<Aspirasi> getByCategory(@PathVariable Long id) {
        return aspirasiService.getAspirasissByAspirasiCategory(id);
    }
    
    @GetMapping("/{id}")
    public Aspirasi getById(@PathVariable Long id, Model model){
        model.addAttribute("ascom", commentService.getCommentsByAspirasi(id));
        return aspirasiService.getById(id);
    }
    
    @GetMapping("/comments/{id}")
    public Model getCommentsByAspirasi(Model model, @PathVariable Long id){
        model.addAttribute("ascom", commentService.getCommentsByAspirasi(id));
        return model;
    }
    
    @PostMapping
    public Aspirasi create(@RequestBody AspirasiRequest aspirasiRequest){
        return aspirasiService.create(aspirasiRequest);
    }
    
    @PutMapping("/{id}")
    public String update(@PathVariable Long id,@RequestBody AspirasiRequest aspirasiRequest){
        aspirasiService.update(id, aspirasiRequest);
        return "redirect:api/aspirasiCategory";
    }
    
    
    @DeleteMapping("/delete/{id}")
    public Aspirasi delete(@PathVariable Long id){
        return aspirasiService.delete(id);
    }
    
}