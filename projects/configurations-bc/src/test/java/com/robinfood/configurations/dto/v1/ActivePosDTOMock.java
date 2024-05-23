package com.robinfood.configurations.dto.v1;

import com.robinfood.configurations.dto.v1.models.ActivePosDTO;

public class ActivePosDTOMock {

    public static ActivePosDTO getSActivePosDTOTrue(){

        return ActivePosDTO.builder()
                .status(true)
                .build();
    }

    public static ActivePosDTO getSActivePosDTOFalse(){

        return ActivePosDTO.builder()
                .status(false)
                .build();
    }
}
