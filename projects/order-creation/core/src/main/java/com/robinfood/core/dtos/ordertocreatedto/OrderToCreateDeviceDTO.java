package com.robinfood.core.dtos.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderToCreateDeviceDTO implements Serializable {

    private static final long serialVersionUID = -753737995512739216L;

    @JsonProperty("ip")
    private String ip;

    @JsonProperty("platform")
    private Long platform;

    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("version")
    private String version;

}
