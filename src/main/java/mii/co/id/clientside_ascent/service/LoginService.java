/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.service;

import java.util.List;
import java.util.stream.Collectors;
import mii.co.id.clientside_ascent.model.dto.request.LoginRequest;
import mii.co.id.clientside_ascent.model.dto.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author lenovo
 */
@Service
public class LoginService {

    private RestTemplate restTemplate;

    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${server.baseUrl}/login")
    private String url;

    public Boolean login(LoginRequest loginRequest) {
        ResponseEntity<LoginResponse> res = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity(loginRequest),
                new ParameterizedTypeReference<LoginResponse>() {
                }
        );
        if (res.getStatusCode() == HttpStatus.OK) {
            setPrinciple(res.getBody(), loginRequest.getPassword());
            return true;
        }    
        return false;
    }
    
    public void setPrinciple(LoginResponse res, String password){
        List<GrantedAuthority> authorities =  res.getAuthorities()
                .stream().map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
        
        UsernamePasswordAuthenticationToken authenticationToken = 
                new UsernamePasswordAuthenticationToken(res.getUsername(), password, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
