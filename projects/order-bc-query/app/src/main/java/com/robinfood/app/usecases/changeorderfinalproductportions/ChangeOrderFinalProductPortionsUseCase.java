package com.robinfood.app.usecases.changeorderfinalproductportions;

import com.robinfood.core.entities.OrderFinalProductPortionEntity;
import com.robinfood.repository.orderfinalproductportions.IOrderFinalProductPortionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_FALSE_VALUE_AS_INT;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;

@AllArgsConstructor
@Component
@Slf4j
public class ChangeOrderFinalProductPortionsUseCase implements IChangeOrderFinalProductPortionsUseCase{

    private static final String STATUS_PAID = "ORDER_APPROVED_PAYMENT";

    private static final String STATUS_CANCEL = "ORDER_CANCEL";

    private final IOrderFinalProductPortionRepository orderFinalProductPortionRepository;


    @Override
    public void invoke(Long orderId, String status) {

        if(STATUS_PAID.equals(status) || STATUS_CANCEL.equals(status)){

            log.info("Get all OrderFinalProductPortionEntity by orderId {}", orderId);

            List<OrderFinalProductPortionEntity> orderFinalProduct =
                orderFinalProductPortionRepository.findOrderFinalProductPortionEntityByOrderId(orderId);

            if(!orderFinalProduct.isEmpty()) {

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
    }
}
