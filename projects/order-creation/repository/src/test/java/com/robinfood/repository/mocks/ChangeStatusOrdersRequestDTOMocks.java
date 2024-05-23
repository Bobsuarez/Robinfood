package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.changestatusordersrequestdto.ChangeStatusOrdersRequestDTO;
import com.robinfood.core.dtos.changestatusordersrequestdto.OrderStatusRequestDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangeStatusOrdersRequestDTOMocks {

    private final List<OrderStatusRequestDTO> orderStatusRequestDTOS = new ArrayList<>(Arrays.asList(
            new OrderStatusRequestDTO(
                    "",
                    1L,
                    1L,
                    1L
            )
    ));

    public final ChangeStatusOrdersRequestDTO changeStatusOrdersRequestDTO = new ChangeStatusOrdersRequestDTO(
            orderStatusRequestDTOS
    );
}
