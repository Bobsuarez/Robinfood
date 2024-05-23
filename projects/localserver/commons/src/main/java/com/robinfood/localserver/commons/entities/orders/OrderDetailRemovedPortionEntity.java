package com.robinfood.localserver.commons.entities.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class OrderDetailRemovedPortionEntity {

    private Long id;

    private String name;
}
