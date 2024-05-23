package com.robinfood.core.dtos.transactionrequestdto;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.robinfood.core.constants.GlobalConstants.TIMEZONE_BY_DEVICE_DEFAULT;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDTO implements Serializable {

    private static final long serialVersionUID = -2408423642183839382L;

    @NotBlank
    private String ip;

    @NotNull
    private Long platform;

    private String timezone;

    @NotBlank
    private String version;

    public String getTimezone() {

        if (timezone == null || timezone.isEmpty()) {
            return TIMEZONE_BY_DEVICE_DEFAULT;
        }

        return this.timezone;
    }
}
