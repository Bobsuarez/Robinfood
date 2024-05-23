package com.robinfood.core.entities.changestatusordersrequestentities;

import java.util.List;
import lombok.Data;

@Data
public class ChangeStatusOrdersRequestEntity {

    private final List<OrderStatusRequestEntity> orders;
}
