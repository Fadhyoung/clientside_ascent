/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.co.id.clientside_ascent.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author lenovo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFile {
  private Long id;
  private String name;
  private String url;
  private String type;
  private long size;
  
}
