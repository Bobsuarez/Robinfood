package com.robinfood.core.entities;

import java.util.List;
import lombok.Data;

@Data
public class GroupEntity {

    private final Long id;

    private final List<PortionEntity> portions;

    private final String sku;
}
