package com.robinfood.core.mappers.transactionv2;

import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;

public final class TransactionV2Mapper {

    private TransactionV2Mapper() {
    }

    public static TransactionRequestDTO transactionRequestDTOV1ToTransactionRequestDTOV2
            (TransactionRequestDTO transactionRequestDTO) {
        for (OrderDTO order : transactionRequestDTO.getOrders()) {
            orderDTOV1ToOrderDTOV2(order);
            trasactionDTOV1ToTrasactionDTOV2(transactionRequestDTO, order);
        }
        return transactionRequestDTO;
    }

    public static void orderDTOV1ToOrderDTOV2(OrderDTO order) {
        order.setCriteriaInfo(order.getCouponCriteriaInfo());
    }

    public static void trasactionDTOV1ToTrasactionDTOV2(TransactionRequestDTO transaction, OrderDTO order) {
        transaction.setCoupons(order.getCoupons());
    }

}

