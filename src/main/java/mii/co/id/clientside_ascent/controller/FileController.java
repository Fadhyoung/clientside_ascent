/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.controller;

import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import mii.co.id.clientside_ascent.model.FileDB;
import mii.co.id.clientside_ascent.model.dto.request.FileRequest;
import mii.co.id.clientside_ascent.model.dto.response.ResponseFile;
import mii.co.id.clientside_ascent.service.FileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lenovo
 */
@Controller
@AllArgsConstructor
@RequestMapping("/file")
public class FileController {
    
    private FileService fileService;
    
    @GetMapping
    public String fileView(){
        return "util/file";
    }
    
    @GetMapping("/getFileByCategory/{id}")
    public Model getFileByCategory(@PathVariable Long id, Model model){
        System.out.println("get all files by category");
        model.addAttribute("filesByCategory", fileService.getByCategory(id));
        for (ResponseFile f : fileService.getByCategory(id)){
            System.out.println(f.getName());
        }
        return model;
    }
        
    @PostMapping("/uploadByAspriasi/{id}")
    public String uploadByAspirasi( @PathVariable Long id ,@RequestParam("file") MultipartFile file) throws IOException{
        System.out.println("masuk upload controler " + id);
        System.out.println(file.getOriginalFilename());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileRequest fileDB = new FileRequest();
        fileDB.setName(fileName);
        fileDB.setType(file.getContentType());
        fileDB.setData(file.getBytes());
        fileService.uploadByAspirasi(id, fileDB);
        return "util/file";
    }
    
    @PostMapping("/uploadByCategory/{id}")
    public String uploadByCategory( @PathVariable Long id ,@RequestParam("file") MultipartFile file) throws IOException{
        System.out.println("masuk upload controler " + id);
        System.out.println(file.getOriginalFilename());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileRequest fileDB = new FileRequest();
        fileDB.setName(fileName);
        System.out.println("name : " + fileDB.getName());
        fileDB.setType(file.getContentType());
        fileDB.setData(file.getBytes());
        fileService.uploadByCategory(id, fileDB);
        return "util/file";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        System.out.println("Your'e inside client delete controller");
        fileService.delete(id);
        return "util/file";
    }
    
    
}
