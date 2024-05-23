package com.robinfood.core.models.retrofit.menu.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class BrandResponse {

    private String color;

    private Long countryId;

    private Long franchiseId;

    private Long id;

    private String image;

    private String name;

}
