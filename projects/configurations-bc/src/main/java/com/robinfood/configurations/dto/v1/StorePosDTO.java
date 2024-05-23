package com.robinfood.configurations.dto.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.configurations.dto.v1.models.CreatePosDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StorePosDTO implements Serializable {

    private static final long serialVersionUID = 4547180375121285617L;

    @NotNull
    @JsonProperty(value = "pos")
    @Valid
    List<CreatePosDTO> pos;

    @NotNull
    @JsonProperty(value = "storeId")
    private Long storeId;
}
