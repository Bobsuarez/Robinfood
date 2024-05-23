/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.robinfood.queue.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Bobsu
 */
@AllArgsConstructor
@Getter
public enum PropertiesConnectionEnum {

    CONNECTION_DEV_PROPERTIES("connection_dev"),
    CONNECTION_PROD_PROPERTIES("connection_prod"),;

    private final String configName;
    private final String[] properties = {
            "mysql-url", "user-mysql", "pass-mysql"
    };
}
