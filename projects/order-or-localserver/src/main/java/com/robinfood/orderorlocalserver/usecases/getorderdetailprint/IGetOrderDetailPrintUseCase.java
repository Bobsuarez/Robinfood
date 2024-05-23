package com.robinfood.orderorlocalserver.usecases.getorderdetailprint;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailDTO;
import com.robinfood.orderorlocalserver.enums.Result;

import java.util.List;

public interface IGetOrderDetailPrintUseCase {

    Result<List<OrderDetailDTO>> invoke(
            List<Long> orderIds,
            List<String> orderUids,
            List<String> orderUuid
    );
}
