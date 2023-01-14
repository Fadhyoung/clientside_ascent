/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.model;

import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author lenovo
 */
@Data
@NoArgsConstructor
public class Aspirasi{
    
    private Long id;
    private int like;
    private int dislike;
    private Date date;   
    private Approval approval;
    private String description;
    private User user;
    private List<Comment> comments;
    private AspirasiCategory aspirasiCategory;
   
}
