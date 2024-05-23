package com.robinfood.localprinterbc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class PrintingTemplateBrandGroupsEntity {

    @JsonIgnore
    private Boolean active;

    private Long brandId;

    @JsonIgnore
    private LocalDateTime createdAt;

    @JsonIgnore
    private Long id;

    private Long groupId;

    private Long printingTemplateId;

    @JsonIgnore
    private LocalDateTime updatedAt;

}
