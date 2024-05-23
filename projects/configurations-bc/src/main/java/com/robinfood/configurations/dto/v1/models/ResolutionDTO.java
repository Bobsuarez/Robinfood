package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.configurations.dto.v1.AbstractBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResolutionDTO extends AbstractBaseDTO {

    private static final long serialVersionUID = -5951288982854027787L;

    @Schema(example = "1234")
    @JsonProperty(value = "id")
    private Long id;

    @Schema(example = "true")
    @JsonProperty(value = "status")
    private Integer status;

    @Schema(example = "123456789")
    @JsonProperty(value = "startingNumber")
    private String startingNumber;

    @Schema(example = "1234567890")
    @JsonProperty(value = "finalNumber")
    private String finalNumber;

    @Schema(example = "resolution")
    @JsonProperty(value = "name")
    private String name;

    @Schema(example = "2022-01-14T21:18:32")
    @JsonProperty(value = "startDate")
    private LocalDateTime startDate;

    @Schema(example = "2022-01-14T21:18:32")
    @JsonProperty(value = "endDate")
    private LocalDateTime endDate;

    @Schema(example = "res")
    @JsonProperty(value = "prefix")
    private String prefix;

    @Schema(example = "text")
    @JsonProperty(value = "invoiceText")
    private String invoiceText;

    @Schema(example = "ABC1234")
    @JsonProperty(value = "serial")
    private String serial;

    @Schema(example = "ABC123")
    @JsonProperty(value = "invoiceNumberResolutions")
    private String invoiceNumberResolutions;

    @Schema(example = "doc")
    @JsonProperty(value = "document")
    private String document;
}
