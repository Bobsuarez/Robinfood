package com.robinfood.app.usecases.getlistpaymentbystore;

import com.robinfood.core.dtos.report.salebystore.PaymentMethodsOfStoreDTO;
import com.robinfood.core.entities.OrderEntity;

import java.util.List;

/**
 * Use case containing payment methods by store id logic
 */
public interface IGetListPaymentMethodsByStoreUseCase {

    List<PaymentMethodsOfStoreDTO> invoke(
            List<OrderEntity> listOrdersCurrent,
            List<OrderEntity> listOrdersLastWeek
    );
}
