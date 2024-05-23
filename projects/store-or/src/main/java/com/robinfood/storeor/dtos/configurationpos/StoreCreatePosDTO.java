package com.robinfood.storeor.dtos.configurationpos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreCreatePosDTO {

    @NotNull
    @JsonProperty(value = "pos")
    @Valid
    List<PosDTO> pos;

    @NotNull
    @JsonProperty(value = "storeId")
    private Long storeId;
}
