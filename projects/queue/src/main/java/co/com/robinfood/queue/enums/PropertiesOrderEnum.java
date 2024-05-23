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
public enum PropertiesOrderEnum {

    ORDER_OR_LOCALSERVER_POS_PROPERTIES("order_or_localserver_pos_config"),
    ORDER_OR_LOCALSERVER_OLD_PROPERTIES("order_or_localserver_old_config"),
    ORDER_POS_PROPERTIES("order_pos_config"),
    ORDER_OLD_PROPERTIES("order_old_config"),
    CHANGE_STATUS_POS_PROPERTIES("change_status_pos_config"),
    CHANGE_STATUS_OLD_PROPERTIES("change_status_old_config");

    private final String configName;
    private final String[] properties = {
            "broker-url", "user-amq", "pass-amq", "name-amq",
            "is-enable-topic", "is-enable-firm", "message"
    };
}
