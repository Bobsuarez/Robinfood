package com.robinfood.core.dtos.request.transaction;

import static com.robinfood.core.constants.GlobalConstants.TIMEZONE_BY_DEVICE_DEFAULT;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class RequestDeviceDTO {

    @NotBlank
    private final String ip;

    @Positive
    private final Long platform;

    @NotBlank
    private final String timezone;

    private final String version;

    public String getTimezone() {

        if (timezone == null || timezone.isEmpty()) {
            return TIMEZONE_BY_DEVICE_DEFAULT;
        }

        return this.timezone;
    }
}
