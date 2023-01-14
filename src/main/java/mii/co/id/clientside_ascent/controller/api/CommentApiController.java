/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.controller.api;

import java.util.List;
import lombok.AllArgsConstructor;
import mii.co.id.clientside_ascent.model.Comment;
import mii.co.id.clientside_ascent.model.dto.request.CommentRequest;
import mii.co.id.clientside_ascent.service.CommentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lenovo
 */
@RestController
@RequestMapping("/api/comment")
@AllArgsConstructor
public class CommentApiController {
    
    private CommentService commentService;

    @GetMapping
    public List<Comment> getAll() {
        return commentService.getAll();
    }
    
    @GetMapping("/{id}")
    public Comment getById(@PathVariable Long id){
        return commentService.getById(id);
    }
    
    @PostMapping
    public Comment create(@RequestBody CommentRequest commentRequest){
        return commentService.create(commentRequest);
    }
    
    
    @DeleteMapping("/{id}")
    public Comment delete(@PathVariable Long id){
        return commentService.delete(id);
    }
    
}
