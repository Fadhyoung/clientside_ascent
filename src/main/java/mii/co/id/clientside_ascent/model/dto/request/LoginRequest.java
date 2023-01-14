/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author lenovo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    
    private String username;
    private String password;
    
}
