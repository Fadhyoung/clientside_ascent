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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author lenovo
 */

@Service
public class AspirasiService {
    private RestTemplate restTemplate;

    @Autowired
    public AspirasiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${server.baseUrl}/aspirasi")
    private String url;

    public List<Aspirasi> getAll() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Basic QWVtb25kOkFlbW9uZDEyMzQ1");
        return restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Aspirasi>>() {
                }).getBody();
    }
    
    public Aspirasi getById(Long id) {
        Aspirasi yolo =  restTemplate.exchange(url + "/" + id, HttpMethod.GET, null, 
                new ParameterizedTypeReference<Aspirasi>() {
                }).getBody();
        
        return yolo;
    }
    
        public Aspirasi getByName(String name) {
        return restTemplate.exchange("http://localhost:8088/region/searchByName/"+ name, HttpMethod.GET, null, 
                new ParameterizedTypeReference<Aspirasi>() {
                }).getBody();
    }
        
    public List<Aspirasi> getAspirasissByAspirasiCategory(Long id){
        List<Aspirasi> aspirasis = getAll();
        List<Aspirasi> finalComments = new ArrayList<>();

        for (Aspirasi a : aspirasis) {
            if (a.getAspirasiCategory().getId() == id){
                finalComments.add(a);
            }
        }
        return finalComments;
    }
    
    public List<Aspirasi> getAspirasissByUser(String name){
        
        List<Aspirasi> aspirasis = getAll();
        List<Aspirasi> finalAspirasis = new ArrayList<>();

        System.out.println("namaya "+name);
        
        for (Aspirasi a : aspirasis) {
            if (a.getUser().getUsername().equals(name)){
                finalAspirasis.add(a);
            }
        }
        return finalAspirasis;
    }
            
    public List<Aspirasi> getApprovedAspirasis(){
        
        List<Aspirasi> aspirasis = getAll();
        List<Aspirasi> finalAspirasis = new ArrayList<>();

        System.out.println("namaya ");
        
        for (Aspirasi a : aspirasis) {
            if (a.getApproval().getId() == 2 ){
                finalAspirasis.add(a);
            }
        }
        return finalAspirasis;
    }
            
    public List<Aspirasi> getRejectedAspirasis(){
        
        List<Aspirasi> aspirasis = getAll();
        List<Aspirasi> finalAspirasis = new ArrayList<>();

        System.out.println("namaya ");
        
        for (Aspirasi a : aspirasis) {
            if (a.getApproval().getId() == 3 ){
                finalAspirasis.add(a);
            }
        }
        return finalAspirasis;
    }
        
    public Aspirasi update(Long id, AspirasiRequest aspirasiRequest) {
        Aspirasi yolo = restTemplate.exchange(url + "/approve/"  + id, HttpMethod.PUT, new HttpEntity(aspirasiRequest), 
                new ParameterizedTypeReference<Aspirasi>() {
                }).getBody();
        if (aspirasiRequest.getApprovalId() == 2){
            restTemplate.exchange("http://localhost:8088/mailSender/approved", HttpMethod.POST, null, 
                new ParameterizedTypeReference<Aspirasi>() {
                }).getBody();
        } else if (aspirasiRequest.getApprovalId() == 3){
            restTemplate.exchange("http://localhost:8088/mailSender/rejected", HttpMethod.POST, null, 
                new ParameterizedTypeReference<Aspirasi>() {
                }).getBody();
        }
        return yolo;
        
    }
    
    public Aspirasi create(AspirasiRequest aspirasiRequest) {
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity(aspirasiRequest),
                new ParameterizedTypeReference<Aspirasi>() {
                }).getBody();
    }
    
    public Aspirasi delete(Long id){
        return restTemplate.exchange(url+ "/" + id, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<Aspirasi>(){}).getBody();
    
    }
}
