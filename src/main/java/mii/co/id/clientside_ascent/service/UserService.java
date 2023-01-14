/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import mii.co.id.clientside_ascent.model.User;
import mii.co.id.clientside_ascent.model.dto.request.GeneralDTO;
import mii.co.id.clientside_ascent.model.dto.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author lenovo
 */

@Service
public class UserService {
    private RestTemplate restTemplate;

    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${server.baseUrl}/user")
    private String url;

    public List<User> getAll() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Basic QWVtb25kOkFlbW9uZDEyMzQ1");
        return restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {
                }).getBody();
    }
    
    public User getById(Long id) {
        User yolo =  restTemplate.exchange(url + "/" + id, HttpMethod.GET, null, 
                new ParameterizedTypeReference<User>() {
                }).getBody();
        
        return yolo;
    }
    
    public User getByName(String name) {
         
        return restTemplate.exchange(url+ "/findUserByName/" + name, HttpMethod.GET, null,
                new ParameterizedTypeReference<User>() {
                }).getBody();
    }
       
        
    public User update(Long id, UserRequest userRequest) {
        System.out.println("masuk sini lah ya");
        User yolo = restTemplate.exchange(url + "/"  + id, HttpMethod.PUT, new HttpEntity(userRequest), 
                new ParameterizedTypeReference<User>() {
                }).getBody();
        return yolo;
        
    }
    
    public boolean create(UserRequest userRequest) {
        System.out.println("masuk usre service create");
        ResponseEntity<User> res = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity(userRequest),
                new ParameterizedTypeReference<User>() {
                }
        );
        return true;
    }
    
    public User delete(Long id){
        return restTemplate.exchange(url+ "/" + id, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<User>(){}).getBody();
    
    }
    
    public void register(UserRequest userRequest) {
        System.out.println("masuk service register");
        ResponseEntity<User> res = restTemplate.exchange(url + "/register", HttpMethod.POST, new HttpEntity(userRequest),
                new ParameterizedTypeReference<User>() {
                }
        );
    }
    
    public Boolean getByVerificationCode(GeneralDTO generalDTO) {
        System.out.println("user verify service " + generalDTO.getStrValue());
        System.out.println("user verification code service");
        User user = restTemplate.exchange(url + "/verify", HttpMethod.POST, new HttpEntity(generalDTO), 
                new ParameterizedTypeReference<User>() {
                }).getBody();
        System.out.println(user.getUsername()); 
        
        if (user.getVerificationCode().equals(generalDTO.getStrValue())) {
            System.out.println("ya");
            return true;
        } else {
            System.out.println("tidak");
            return false;
        }
    }
}
