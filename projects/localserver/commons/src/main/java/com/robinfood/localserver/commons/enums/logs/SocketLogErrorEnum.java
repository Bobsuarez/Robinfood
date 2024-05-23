package com.robinfood.localserver.commons.enums.logs;

public enum SocketLogErrorEnum {
    WEB_SOCKET_DISCONNECT("001", "WebSocket IsOpen {}"),
    DISCONNECT_BY_SERVER("002", "Disconnected by server, reason is {}, error code is {}"),
    DISCONNECT_BY_CLIENT("003", "Disconnected by client, reason is {}, error code is {}");

    private String code;
    private String message;

    SocketLogErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage(){
        return this.code + " " + this.message;
    }
}
