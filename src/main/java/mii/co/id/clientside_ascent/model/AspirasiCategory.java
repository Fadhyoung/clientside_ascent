/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.model;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author lenovo
 */

@NoArgsConstructor
@Data
public class AspirasiCategory {
    
    private Long id;
    private String categoryName;
    private String categoryDescription;
    private Boolean onGoing;
    private List<Aspirasi> aspirasis;
    
}
