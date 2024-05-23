package com.robinfood.storeor.dtos.configurationposbystore;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreResolutionsDTO {

    @Valid
    @NotNull(message = "The field 'resolutions' cannot be null")
    @NotEmpty(message = "The field 'resolutions' cannot be Empty")
    private List<ResolutionDTO> resolutions;

    private Long storeId;
}
