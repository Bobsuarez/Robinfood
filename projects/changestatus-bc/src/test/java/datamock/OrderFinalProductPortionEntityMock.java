package datamock;

import com.robinfood.changestatusbc.entities.OrderFinalProductPortionEntity;

import java.util.Collections;
import java.util.List;

public class OrderFinalProductPortionEntityMock {

    public OrderFinalProductPortionEntity getDataDefault() {

        final OrderFinalProductPortionEntity orderFinalProductPortion = new OrderFinalProductPortionEntity();

        return OrderFinalProductPortionEntity.builder()
                .addition(true)
                .basePrice(0.0)
                .companyId(1L)
                .changedProduct(false)
                .createdAt(null)
                .discount(0.0)
                .dicUnitId(1L)
                .effectiveSale(0)
                .groupId(1L)
                .groupName("Escoge tu base")
                .groupSku("12276489554839470634")
                .id(1L)
                .operationDate("2022-03-10")
                .orderId(1234L)
                .orderFinalProductId(1L)
                .portionId(1L)
                .portionName("Ninguno")
                .portionSku("12276489554839470356")
                .portionStatus(0)
                .productId(1L)
                .productName("Ninguno")
                .quantity(0)
                .quantityFree(0)
                .storeId(1L)
                .unitsNumber(0.0)
                .updatedAt(null)
                .build();
    }

    public List<OrderFinalProductPortionEntity> getDataDefaultList() {
        return Collections.singletonList(getDataDefault());
    }
}
