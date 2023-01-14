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
@Data
@NoArgsConstructor
public class Approval {

    private Long id;
    private String name;
    private List<Aspirasi> aspirasis;
    
}
