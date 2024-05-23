package com.robinfood.app.usecases.getorderspaymentmethodsbyposid;

import com.robinfood.core.dtos.GetOrderPaymentMethodsDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Use case that grouped details of the payment methods.
 */
public interface IGetOrdersPaymentMethodsByPosIdUseCase {

    /**
     * retrieves the grouped details of the payment methods
     *
     * @param posId          Filter by pos_id from the orders
     * @param localDateStart Start date to consult the records.
     * @param localDateEnd   End date to consult the records.
     * @return List<GetOrderPaymentMethodsDTO> payment method details list for voucher report
     */
    List<GetOrderPaymentMethodsDTO> invoke(
            LocalDate localDateStart,
            LocalDate localDateEnd,
            Long posId,
            String timeZone
    );

}
