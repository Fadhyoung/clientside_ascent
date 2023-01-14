/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.service;

import java.util.ArrayList;
import java.util.List;
import mii.co.id.clientside_ascent.model.Aspirasi;
import mii.co.id.clientside_ascent.model.AspirasiCategory;
import mii.co.id.clientside_ascent.model.dto.request.AspirasiCategoryRequest;
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
public class AspirasiCategoryService {
    private RestTemplate restTemplate;
    private AspirasiService aspirasiService;

    @Autowired
    public AspirasiCategoryService(RestTemplate restTemplate, AspirasiService aspirasiService) {
        this.restTemplate = restTemplate;
        this.aspirasiService = aspirasiService;
    }

    @Value("${server.baseUrl}/aspirasiCategory")
    private String url;

    public List<AspirasiCategory> getAll() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Basic QWVtb25kOkFlbW9uZDEyMzQ1");
        return restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AspirasiCategory>>() {
                }).getBody();
    }
    
    public AspirasiCategory getById(Long id) {
        AspirasiCategory yolo =  restTemplate.exchange(url + "/" + id, HttpMethod.GET, null, 
                new ParameterizedTypeReference<AspirasiCategory>() {
                }).getBody();
        
        return yolo;
    }
    
        public AspirasiCategory getByName(String name) {
        return restTemplate.exchange("http://localhost:8088/region/searchByName/"+ name, HttpMethod.GET, null, 
                new ParameterizedTypeReference<AspirasiCategory>() {
                }).getBody();
    }
       
    
    public List<Aspirasi> getByAspirasiCategory(Long id) {
        
        List<Aspirasi> aspirasis = aspirasiService.getAll();
        List<Aspirasi> finalAspirasis = new ArrayList<>();
        
        for (Aspirasi a : aspirasis){
            if(a.getAspirasiCategory().getId() == id){
                finalAspirasis.add(a);
            }
        }
        return finalAspirasis;
    } 
        
    public AspirasiCategory update(Long id, AspirasiCategoryRequest aspirasiCategoryRequest) {
        AspirasiCategory yolo = restTemplate.exchange(url + "/" + id, HttpMethod.PUT, new HttpEntity(aspirasiCategoryRequest), 
                new ParameterizedTypeReference<AspirasiCategory>() {
                }).getBody();

        return yolo;
        
    }
    
    public AspirasiCategory create(AspirasiCategoryRequest aspirasiCategoryRequest) {
        
        aspirasiCategoryRequest.setOnGoing(Boolean.TRUE);
        
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity(aspirasiCategoryRequest),
                new ParameterizedTypeReference<AspirasiCategory>() {
                }).getBody();
    }
    
    public AspirasiCategory delete(Long id){
        return restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<AspirasiCategory>(){}).getBody();
    
    }
}
