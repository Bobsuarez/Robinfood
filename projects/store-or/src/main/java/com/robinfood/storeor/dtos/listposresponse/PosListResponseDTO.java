package com.robinfood.storeor.dtos.listposresponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PosListResponseDTO implements Serializable {

    private String code;

    private Long id;

    private String name;

    private Long posTypeId;

    private ResolutionResponseDTO resolution;

    private Boolean status;

    private StorePosResponseDTO store;

}
