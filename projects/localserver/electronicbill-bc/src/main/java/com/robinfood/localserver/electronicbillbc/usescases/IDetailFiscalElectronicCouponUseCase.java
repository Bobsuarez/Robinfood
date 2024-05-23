package com.robinfood.localserver.electronicbillbc.usescases;

import com.robinfood.localserver.commons.dtos.electronicbill.DetDTO;
import com.robinfood.localserver.commons.dtos.orders.billing.OrderBillingProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IDetailFiscalElectronicCouponUseCase {

    List<DetDTO> invoke(List<OrderBillingProductDTO> listOrderBillingProductDTO,
                        BigDecimal totalDeductionAndDiscountOrder);
}
