package com.robinfood.changestatusbc.usecases.changeorderfinalproductportions;

import com.robinfood.changestatusbc.entities.OrderFinalProductPortionEntity;
import com.robinfood.changestatusbc.repositories.orderfinalproductportions.IOrderFinalProductPortionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.robinfood.changestatusbc.configs.constants.GlobalConstants.DEFAULT_BOOLEAN_FALSE_VALUE_AS_INT;
import static com.robinfood.changestatusbc.configs.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;
import static com.robinfood.changestatusbc.configs.constants.GlobalConstants.STATUS_CANCEL;
import static com.robinfood.changestatusbc.configs.constants.GlobalConstants.STATUS_PAID;
import static com.robinfood.changestatusbc.enums.AppTraceEnum.NO_SEARCH_FOR_FINAL_PRODUCTS;
import static com.robinfood.changestatusbc.enums.AppTraceEnum.SEARCH_FINAL_PRODUCTS;
import static com.robinfood.changestatusbc.enums.AppTraceEnum.VALUE_ORDER_ID_AND_STATUS;

@Service
@Slf4j
public class ChangeOrderFinalProductPortionsUseCase implements IChangeOrderFinalProductPortionsUseCase {

    private final IOrderFinalProductPortionRepository orderFinalProductPortionRepository;

    public ChangeOrderFinalProductPortionsUseCase(
            IOrderFinalProductPortionRepository orderFinalProductPortionRepository
    ) {
        this.orderFinalProductPortionRepository = orderFinalProductPortionRepository;
    }

    @Override
    public void invoke(Long orderId, String status) {

        log.info(VALUE_ORDER_ID_AND_STATUS.getMessageWithCode(), orderId, status);

        if (STATUS_PAID.equals(status) || STATUS_CANCEL.equals(status)) {

            log.info(SEARCH_FINAL_PRODUCTS.getMessageWithCode());

            List<OrderFinalProductPortionEntity> orderFinalProduct =
                    orderFinalProductPortionRepository.findOrderFinalProductPortionEntityByOrderId(orderId);

            if (!orderFinalProduct.isEmpty()) {

                orderFinalProduct.forEach(
                        (OrderFinalProductPortionEntity orderFinalProductPortion) -> {
                            if (STATUS_PAID.equals(status)) {
                                orderFinalProductPortion.setEffectiveSale(DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT);
                            } else {
                                orderFinalProductPortion.setEffectiveSale(DEFAULT_BOOLEAN_FALSE_VALUE_AS_INT);
                            }
                        });

                orderFinalProductPortionRepository.saveAll(orderFinalProduct);
            }
        }
        log.info(NO_SEARCH_FOR_FINAL_PRODUCTS.getMessageWithCode(), orderId, status);
    }
}
