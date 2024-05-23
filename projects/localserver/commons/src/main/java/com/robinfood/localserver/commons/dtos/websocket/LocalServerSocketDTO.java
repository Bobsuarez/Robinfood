package com.robinfood.localserver.commons.dtos.websocket;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class LocalServerSocketDTO {

    @Value("${websocket.client.server_endpoint}")
    private String serverEndpoint;

    @Value("${websocket.client.id}")
    private String clientId;

    @Value("${websocket.client.secret}")
    private String secret;

    @Value("${websocket.client.storeid}")
    private String storeId;

    @Value("${websocket.client.pingintervalms}")
    private Long pingIntervalMs;
}
