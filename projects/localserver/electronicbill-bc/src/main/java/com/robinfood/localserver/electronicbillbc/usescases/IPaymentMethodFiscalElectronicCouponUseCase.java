package com.robinfood.localserver.electronicbillbc.usescases;

import com.robinfood.localserver.commons.dtos.orders.billing.TreasuryPaymentsDTO;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IPaymentMethodFiscalElectronicCouponUseCase {

    List<Map<String, String>> invoke(List<TreasuryPaymentsDTO> listTreasuryPaymentsDTO,
                                     BigDecimal totalDeduction, BigDecimal totalOrder);
}
