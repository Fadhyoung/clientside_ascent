/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.service;

import java.io.IOException;
import java.util.List;
import mii.co.id.clientside_ascent.model.FileDB;
import mii.co.id.clientside_ascent.model.dto.request.FileRequest;
import mii.co.id.clientside_ascent.model.dto.response.ResponseFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lenovo
 */
@Service
public class FileService {
    private RestTemplate restTemplate;

    @Autowired
    public FileService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${server.baseUrl}/file")
    private String url;

    public List<FileDB> getAll() {
        System.out.println("masuk file service get all");
        return restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<FileDB>>() {
                }).getBody();
    }
    
    public List<ResponseFile> getByAspirasi(Long id) {
        List<ResponseFile> yolo =  restTemplate.exchange(url + "/getByAspirasi/" + id, HttpMethod.GET, null, 
                new ParameterizedTypeReference<List<ResponseFile>>() {
                }).getBody();
        
        return yolo;
    }
    
    public List<ResponseFile> getByCategory(Long id) {
        System.out.println("getByCategory client service");
        List<ResponseFile> yolo =  restTemplate.exchange(url + "/getByCategory/" + id, HttpMethod.GET, null, 
                new ParameterizedTypeReference<List<ResponseFile>>() {
                }).getBody();
        return yolo;
    }
    
    public boolean uploadByAspirasi(Long id, FileRequest FileRequest) throws IOException {
        System.out.println("masuk file service create");
        ResponseEntity<FileDB> res = restTemplate.exchange(url + "/uploadByAspirasi/" + id, HttpMethod.POST, new HttpEntity(FileRequest),
                new ParameterizedTypeReference<FileDB>() {
                }
        );
        return true;
    }
    
    public boolean uploadByCategory(Long id, FileRequest FileRequest) throws IOException {
        System.out.println("masuk file service create");
        ResponseEntity<FileDB> res = restTemplate.exchange(url + "/uploadByCategory/" + id, HttpMethod.POST, new HttpEntity(FileRequest),
                new ParameterizedTypeReference<FileDB>() {
                }
        );
        return true;
    }
    
    public FileDB delete(Long id){
        System.out.println("Your'e inside server delete service");
        return restTemplate.exchange(url+ "/" + id, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<FileDB>(){}).getBody();
    
    }
    
}
