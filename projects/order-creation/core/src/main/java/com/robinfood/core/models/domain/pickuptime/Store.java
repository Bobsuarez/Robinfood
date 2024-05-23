package com.robinfood.core.models.domain.pickuptime;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Store {

    private List<Brand> brands;

    private Long id;

    private Long pickupTime;

}
