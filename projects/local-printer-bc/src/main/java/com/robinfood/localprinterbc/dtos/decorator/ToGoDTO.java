package com.robinfood.localprinterbc.dtos.decorator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ToGoDTO {

    private String title;

    @Builder.Default
    private Boolean toGo = Boolean.FALSE;
}
