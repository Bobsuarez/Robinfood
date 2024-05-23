package com.robinfood.core.dtos;

import lombok.Data;

@Data
public class DeviceDTO {

    private final String ip;

    private final Integer platform;

    private final String version;
}

