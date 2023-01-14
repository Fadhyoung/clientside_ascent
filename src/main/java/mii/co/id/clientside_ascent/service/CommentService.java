/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.service;

import java.util.ArrayList;
import java.util.List;
import mii.co.id.clientside_ascent.model.Aspirasi;
import mii.co.id.clientside_ascent.model.Comment;
import mii.co.id.clientside_ascent.model.dto.request.AspirasiRequest;
import mii.co.id.clientside_ascent.model.dto.request.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author lenovo
 */
@Service
public class CommentService {
    private RestTemplate restTemplate;

    @Autowired
    public CommentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${server.baseUrl}/comment")
    private String url;

    public List<Comment> getAll() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Basic QWVtb25kOkFlbW9uZDEyMzQ1");
        return restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Comment>>() {
                }).getBody();
    }
    
    public Comment getById(Long id) {
        Comment yolo =  restTemplate.exchange(url + "/" + id, HttpMethod.GET, null, 
                new ParameterizedTypeReference<Comment>() {
                }).getBody();
        
        return yolo;
    }
    
        public Aspirasi getByName(String name) {
        return restTemplate.exchange("http://localhost:8088/region/searchByName/"+ name, HttpMethod.GET, null, 
                new ParameterizedTypeReference<Aspirasi>() {
                }).getBody();
    }
        
    public List<Comment> getCommentsByAspirasi(Long id){
        List<Comment> comments = getAll();
        List<Comment> finalComments = new ArrayList<>();
        
        for (Comment c : comments) {
            if (c.getAspirasi().getId() == id){
                finalComments.add(c);
            }
        }
        return finalComments;
    }
        
    public Aspirasi update(Long id, Aspirasi aspirasi) {
        Aspirasi yolo = restTemplate.exchange("http://localhost:8088/region/"+ id, HttpMethod.PUT, new HttpEntity(aspirasi), 
                new ParameterizedTypeReference<Aspirasi>() {
                }).getBody();

        return yolo;
        
    }
    
    public Comment create(CommentRequest commentRequest) {
        
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity(commentRequest),
                new ParameterizedTypeReference<Comment>() {
                }).getBody();
    }
    
    public Comment delete(Long id){
        return restTemplate.exchange(url + "/" + + id, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<Comment>(){}).getBody();
    
    }
}
