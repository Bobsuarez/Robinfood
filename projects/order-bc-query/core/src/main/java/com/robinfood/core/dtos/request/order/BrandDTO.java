package com.robinfood.core.dtos.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class BrandDTO {

    private Long franchiseId;

    private Long id;

    @NotBlank
    private String name;
}
