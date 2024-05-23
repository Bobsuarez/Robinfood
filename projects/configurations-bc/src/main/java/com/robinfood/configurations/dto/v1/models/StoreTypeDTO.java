package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreTypeDTO implements Serializable {

    private static final long serialVersionUID = 2296370118259237703L;

    @JsonProperty(value = "id")
    private Long id;

    @Schema(example = "Store type name")
    @JsonProperty(value = "name")
    private String name;

}
