package com.robinfood.localprinterbc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class PrintingTemplateTypeEntity {

    @JsonIgnore
    private LocalDateTime createdAt;

    @JsonIgnore
    private Long id;

    private String name;

    private String slug;

    private Boolean isPartial;

    @JsonIgnore
    private LocalDateTime updatedAt;

}
