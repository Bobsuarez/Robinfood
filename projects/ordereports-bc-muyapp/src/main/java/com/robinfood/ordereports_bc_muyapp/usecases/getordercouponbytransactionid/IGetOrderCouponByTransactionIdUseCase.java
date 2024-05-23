package com.robinfood.ordereports_bc_muyapp.usecases.getordercouponbytransactionid;

import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseCouponsDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IGetOrderCouponByTransactionIdUseCase {

    CompletableFuture<List<ResponseCouponsDTO>> invoke(Integer transactionId);
}
