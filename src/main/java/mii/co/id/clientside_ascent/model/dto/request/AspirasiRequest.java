/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author lenovo
 */

@Data
@NoArgsConstructor
public class AspirasiRequest {
    
    private Long approvalId;
    private String description;
    private String username;
    private Long aspirasiCategoryId;
}
