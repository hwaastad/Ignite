/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author helge
 */
@Data
@AllArgsConstructor
public class Customer implements Serializable {

    private static final long serialVersionUID = -3473803388873018116L;

    private String name;
    private Integer age;

}
