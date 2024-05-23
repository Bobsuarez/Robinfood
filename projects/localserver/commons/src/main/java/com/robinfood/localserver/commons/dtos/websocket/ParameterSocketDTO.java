package com.robinfood.localserver.commons.dtos.websocket;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
public class ParameterSocketDTO {

    private String storeId;

    private String message;
}
