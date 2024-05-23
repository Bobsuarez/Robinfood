package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.configurations.dto.v1.AbstractBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ChannelDTO extends AbstractBaseDTO {

    private static final long serialVersionUID = 6701745083527747614L;

    @Schema(example = "new channel")
    @JsonProperty(value = "name")
    private String name;

}
