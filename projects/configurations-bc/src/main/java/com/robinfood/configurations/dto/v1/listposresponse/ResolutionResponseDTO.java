package com.robinfood.configurations.dto.v1.listposresponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResolutionResponseDTO implements Serializable {

    @JsonProperty(value = "endDate")
    private LocalDateTime endDate;

    @JsonProperty(value = "finalNumber")
    private String finalNumber;

    @JsonProperty(value = "id")
    private Long id;

    private String invoiceNumberResolutions;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "prefix")
    private String prefix;

    @JsonProperty(value = "startDate")
    private LocalDateTime startDate;

    @JsonProperty(value = "startingNumber")
    private String startingNumber;

    @JsonProperty(value = "status")
    private Boolean status;
}
