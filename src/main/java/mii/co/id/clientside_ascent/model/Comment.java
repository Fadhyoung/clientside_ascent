/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.model;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author lenovo
 */
@Data
@NoArgsConstructor
public class Comment {
    
    private Long id;
    private String comment;
    private int like;
    private int dislike;
    private Date date;    
    private User user;
    private Comment parentComment;
    private Aspirasi aspirasi;
    
}
