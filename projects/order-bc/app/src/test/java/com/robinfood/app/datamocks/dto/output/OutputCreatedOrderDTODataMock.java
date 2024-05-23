package com.robinfood.app.datamocks.dto.output;

import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.dtos.response.order.ResponseCreatedOrderDTO;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import lombok.Data;

@Data
public class OutputCreatedOrderDTODataMock {

    private String uid = "n1witsebnn1d";

    private String uuid = "50eaf34f-7252-46ef-9a69-2225b06e14be";
    private String orderNumber = "7000";
    private String orderInvoiceNumber = "34343434";
    private OrderStatusDTO orderStatusDTO = new OrderStatusDTO(
            1L,
            "Pedido"
    );

    public ResponseCreatedOrderDTO getDataDefault() {
        return new ResponseCreatedOrderDTO(
                0.0,
                Collections.emptyList(),
                1L,
                orderNumber,
                orderInvoiceNumber,
                orderStatusDTO,
                8900.0,
                0.0,
                BigDecimal.ZERO,
                8900.0,
                uid,
                uuid
        );
    }

    public List<ResponseCreatedOrderDTO> getDataDefaultList() {
        return Collections.singletonList(getDataDefault());
    }
}
