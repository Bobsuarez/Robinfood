package com.robinfood.core.models.domain.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Brand {

    private String color;

    private Long countryId;

    private Long franchiseId;

    private Long id;

    private String image;

    private String name;

}
