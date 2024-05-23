package com.robinfood.repository.mocks;

import com.robinfood.core.entities.changestatusordersrequestentities.ChangeStatusOrdersRequestEntity;
import com.robinfood.core.entities.changestatusordersrequestentities.OrderStatusRequestEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangeStatusOrdersRequestEntityMocks {

    final List<OrderStatusRequestEntity> orderStatusRequestEntityList = new ArrayList<>(Arrays.asList(
            new OrderStatusRequestEntity(
                    "",
                    1L,
                    1L,
                    1L
            )
    ));

    public final ChangeStatusOrdersRequestEntity changeStatusOrdersRequestEntity = new ChangeStatusOrdersRequestEntity(
            orderStatusRequestEntityList
    );
}
