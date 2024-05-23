package com.robinfood.ordereports_bc_muyapp.usecases.getorderfinalproducts;

import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderDetailDTO;

public interface IGetOrderFinalProductsUseCase {

    ResponseOrderDetailDTO invoke(ResponseOrderDetailDTO responseOrderDetailDTO, Integer orderId);
}
