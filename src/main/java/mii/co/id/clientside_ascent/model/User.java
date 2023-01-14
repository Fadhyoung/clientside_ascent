/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author lenovo
 */
@Data
@NoArgsConstructor
public class User{
    
    private Long id;
    private Long nim;
    private String username;
    private String password;
    private String email;
    private String notelp;
    private String verificationCode;
    private Boolean isAccountLocked;
    private Boolean isEnabled;
    private List<Aspirasi> aspirasi;
    private List<Role> roles;
    private List<Comment> comments;
}
