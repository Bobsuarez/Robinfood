package com.robinfood.storeor.entities.configurationposbystore;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class ResolutionEntity {

    @NotNull
    @Schema(example = "True")
    @JsonProperty(value = "confirmed")
    private Boolean confirmed;

    @NotBlank
    @Schema(example = "Doc Aut DIAN")
    @JsonProperty(value = "document")
    private String document;

    @FutureOrPresent(message = "The current date cannot be less than or equal to the current date")
    @NotNull
    @Schema(example = "2023-07-15")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "endDate")
    private LocalDateTime endDate;

    @Min(value = 0)
    @NotNull
    @Schema(example = "1")
    @JsonProperty(value = "finalNumber")
    private Integer finalNumber;

    private final Long id;

    @NotBlank
    @Schema(example = "1000000000")
    @JsonProperty(value = "invoiceNumber")
    private String invoiceNumber;

    private String invoiceNumberResolutions;

    @NotBlank
    @Schema(example = "Doc Aut DIAN #123 Date 2020/11/19 - 2021/11/19 From 0 To 5")
    @JsonProperty(value = "invoiceText")
    private String invoiceText;

    @NotBlank
    @Schema(example = "Pos 1 - Headquarter 83")
    @JsonProperty(value = "name")
    private String name;

    @Min(value = 0)
    @NotNull
    @Schema(example = "1")
    @JsonProperty(value = "posId")
    private Long posId;

    @NotBlank
    @Schema(example = "1")
    @JsonProperty(value = "prefix")
    private String prefix;

    @NotBlank
    @Schema(example = "123ABCD")
    @JsonProperty(value = "serial")
    private String serial;

    @Schema(example = "2020-11-19")
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "startDate")
    private LocalDateTime startDate;

    @Min(value = 0)
    @NotNull
    @Schema(example = "0")
    @JsonProperty(value = "startingNumber")
    private Integer startingNumber;

    @NotNull
    @Schema(example = "false")
    @JsonProperty(value = "status")
    private Boolean status;
}
