package com.robinfood.configurations.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PosIdDTO implements Serializable {

    private static final long serialVersionUID = -53267116123493528L;

    @JsonProperty(value = "posId")
    private Long posId;
}
