package com.robinfood.localserver.commons.enums.logs;

public enum StatusLogEnum {

    MESSAGE_ORDER_RECEIVED_SUCCESSFULLY("010", "Message received from socket successfully"),
    CHANGE_LOCAL_SUUCESSFULLY("060", "Local change Successfully"),
    CHANGE_LOCAL_FAILED("061", "Local change failed"),
    CHANGE_CLOUD_SUUCESSFULLY("062", "Cloud change Successfully"),
    CHANGE_CLOUD_FAILED("063", "Cloud change failed"),
    CREATE_ORDER_LOCAL_SUCCESSFULLY("064", "Detail of the order created on the local server successfully"),
    CREATE_ORDER_LOCAL_FAILED("065", "Detail of the order created on the local server failed"),
    CREATE_ORDER_COMMAND_EXECUTION_LOCAL_SUCCESSFULLY("066", "Created Order command execution locally successfully"),
    CREATE_ORDER_COMMAND_EXECUTION_LOCAL_FAILED("067", "Created Order command execution locally failed"),
    GET_ORDER_LOCAL_SUCCESSFULLY("068", "Get Order locally successfully"),
    GET_ORDER_LOCAL_FAILED("069", "Get Order locally Failed"),
    GET_ORDER_FROM_ORDER_QUERIES_FAILED("070", "Get Order Detail Order Queries Failed"),
    GET_ALL_ORDER_BY_STATUS_ID_SUCCESSFULLY("071", "Get All Order By StatusId successfully"),
    GET_ALL_ORDER_BY_STATUS_ID_FAILED("072", "Get All Order By StatusId  Failed");

    private String code;
    private String message;

    StatusLogEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage(){
        return this.code + " " + this.message;
    }

}
