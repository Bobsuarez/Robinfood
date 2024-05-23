package com.robinfood.app.datamocks.dto.output;

import com.robinfood.core.dtos.response.order.ResponseCreatedOrderDTO;
import com.robinfood.core.dtos.response.transaction.ResponseCreatedOrderTransactionDTO;

import java.util.List;

public class OutputCreatedOrderTransactionDTODataMock {

    private final List<ResponseCreatedOrderDTO> responseCreatedOrderDTO = new OutputCreatedOrderDTODataMock().getDataDefaultList();

    public ResponseCreatedOrderTransactionDTO getDataDefault() {
        return new ResponseCreatedOrderTransactionDTO(
                null,
                1L,
                responseCreatedOrderDTO,
                "50eaf34f-7252-46ef-9a69-2225b06e14be"
        );
    }

    public ResponseCreatedOrderTransactionDTO getDataDefaultWithoutCoupon() {
        return new ResponseCreatedOrderTransactionDTO(
                null,
                1L,
                responseCreatedOrderDTO,
                "50eaf34f-7252-46ef-9a69-2225b06e14be"
        );
    }
}
