package com.robinfood.dtos.v1.request;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.util.LocalDateTimeDeserializerUtil;
import com.robinfood.util.LocalDateTimeSerializerUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResolutionUpdateDTO  implements Serializable {

    @NotNull
    @JsonProperty(value = "confirmed")
    private Boolean confirmed;

    @NotBlank
    @JsonProperty(value = "document")
    private String document;

    @FutureOrPresent(message = "The current date cannot be less than or equal to the current date")
    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializerUtil.class)
    @JsonSerialize(using = LocalDateTimeSerializerUtil.class)
    @JsonProperty(value = "endDate")
    private LocalDateTime endDate;

    @Min(value = 0)
    @NotNull
    @JsonProperty(value = "finalNumber")
    private Integer finalNumber;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "invoiceNumber")
    private String invoiceNumber;

    @NotBlank
    @JsonProperty(value = "invoiceText")
    private String invoiceText;

    @NotBlank
    @JsonProperty(value = "name")
    private String name;

    @Min(value = 0)
    @NotNull
    @JsonProperty(value = "posId")
    private Long posId;

    @NotBlank
    @JsonProperty(value = "prefix")
    private String prefix;

    @NotBlank
    @JsonProperty(value = "serial")
    private String serial;

    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializerUtil.class)
    @JsonSerialize(using = LocalDateTimeSerializerUtil.class)
    @JsonProperty(value = "startDate")
    private LocalDateTime startDate;

    @Min(value = 0)
    @NotNull
    @JsonProperty(value = "startingNumber")
    private Integer startingNumber;

    @NotNull
    @JsonProperty(value = "status")
    private Boolean status;
}
