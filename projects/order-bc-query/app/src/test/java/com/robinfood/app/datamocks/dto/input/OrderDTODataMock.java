package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.DeductionDTO;
import com.robinfood.core.dtos.request.order.BrandDTO;
import com.robinfood.core.dtos.request.order.DeliveryDTO;
import com.robinfood.core.dtos.request.order.DiscountDTO;
import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.dtos.request.order.OrderDTO;
import com.robinfood.core.dtos.request.order.OrderFlagDTO;
import com.robinfood.core.dtos.request.order.ServiceDTO;
import com.robinfood.core.dtos.request.order.StoreDTO;
import com.robinfood.core.dtos.request.transaction.RequestOriginDTO;
import com.robinfood.core.dtos.response.order.ResponseCreatedOrderDTO;
import com.robinfood.core.entities.OrderDiscountEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class OrderDTODataMock {

    private BrandDTO brandDTO = new BrandDTODataMock().getDataDefault();
    private List<DiscountDTO> discountDTOList = new DiscountDTODataMock().getDataDefaultList();
    private List<DiscountDTO> discountDTOListProductDiscount = new DiscountDTODataMock().getDataDefaultList_With_Product();
    private List<FinalProductDTO> finalProductDTOList = new FinalProductDTODataMock().getDataDefaultList(
            1L);
    private RequestOriginDTO originDTO = new OriginDTODataMock().getDataDefault();
    private List<ServiceDTO> serviceDTOList = new ServiceDTODataMock().getDataDefaultList();
    private StoreDTO orderStoreDTO = new StoreDataDTODataMock().getDataDefault();
    private OrderFlagDTO orderFlagDTO = new OrderFlagDTODataMock().getDataDefault();
    private DeliveryDTO deliveryDTO = new DeliveryDTODataMock().getDataDefault();

    public OrderDTO getDataDefault(Long deliveryTypeId) {
        return new OrderDTO(
                1L,
                brandDTO,
                0.0,
                "ABC123",
                deliveryTypeId,
                discountDTOList,
                finalProductDTOList,
                BigDecimal.ONE,
                orderFlagDTO,
                true,
                1L,
                1L,
                "Anything",
                34343434L,
                "7000",
                originDTO,
                true,
                1L,
                serviceDTOList,
                orderStoreDTO,
                BigDecimal.ZERO,
                8900.00,
                0.0,
                0.0,
                1L,
                1L,
                "m6ux5o0xsc5a",
                "50eaf34f-7252-46ef-9a69-2225b06e14be",
                1L,
                8900.00,
                0.0,
                0.0

        );
    }

    public OrderDTO getDataDefaultFoodCoins(Long deliveryTypeId) {
        return new OrderDTO(
                1L,
                brandDTO,
                0.0,
                "ABC123",
                deliveryTypeId,
                discountDTOList,
                finalProductDTOList,
                null,
                orderFlagDTO,
                true,
                1L,
                1L,
                "Anything",
                34343434L,
                "7000",
                originDTO,
                true,
                1L,
                serviceDTOList,
                orderStoreDTO,
                BigDecimal.ZERO,
                8900.00,
                0.0,
                0.0,
                1L,
                1L,
                "m6ux5o0xsc5a",
                "50eaf34f-7252-46ef-9a69-2225b06e14be",
                1L,
                8900.00,
                0.0,
                0.0
        );
    }

    public OrderDTO getDataDefaultDeduction(Long deliveryTypeId) {
        OrderDTO order = new OrderDTO(
                1L,
                brandDTO,
                0.0,
                "ABC123",
                deliveryTypeId,
                discountDTOList,
                finalProductDTOList,
                BigDecimal.ZERO,
                orderFlagDTO,
                true,
                1L,
                1L,
                "Anything",
                34343434L,
                "7000",
                originDTO,
                true,
                1L,
                serviceDTOList,
                orderStoreDTO,
                BigDecimal.valueOf(100),
                8900.00,
                0.0,
                0.0,
                1L,
                1L,
                "m6ux5o0xsc5a",
                "50eaf34f-7252-46ef-9a69-2225b06e14be",
                1L,
                8900.00,
                0.0,
                0.0
        );
        order.setDeductions(List.of(new DeductionDTO(
                1L,
                BigDecimal.valueOf(2000)
        )));
        return order;
    }

    public OrderDTO getDataDefault_With_Product(Long deliveryTypeId) {
        return new OrderDTO(
                1L,
                brandDTO,
                0.0,
                "ABC123",
                deliveryTypeId,
                discountDTOListProductDiscount,
                finalProductDTOList,
                BigDecimal.ZERO,
                orderFlagDTO,
                true,
                1L,
                1L,
                "Anything",
                34343434L,
                "7000",
                originDTO,
                true,
                1L,
                serviceDTOList,
                orderStoreDTO,
                BigDecimal.ZERO,
                8900.00,
                0.0,
                0.0,
                1L,
                1L,
                "m6ux5o0xsc5a",
                "50eaf34f-7252-46ef-9a69-2225b06e14be",
                1L,
                8900.00,
                0.0,
                0.0
        );
    }

    public OrderDTO getDataDefault_No_Product_Discount(Long deliveryTypeId) {
        return new OrderDTO(
                1L,
                brandDTO,
                0.0,
                "ABC123",
                deliveryTypeId,
                new ArrayList<>(),
                finalProductDTOList,
                BigDecimal.ZERO,
                orderFlagDTO,
                true,
                1L,
                1L,
                "Anything",
                34343434L,
                "7000",
                originDTO,
                true,
                1L,
                serviceDTOList,
                orderStoreDTO,
                BigDecimal.ZERO,
                8900.00,
                0.0,
                0.0,
                1L,
                1L,
                "m6ux5o0xsc5a",
                "50eaf34f-7252-46ef-9a69-2225b06e14be",
                1L,
                8900.00,
                0.0,
                0.0
        );
    }

    public OrderDTO getDataDefaultNotPaid() {
        return new OrderDTO(
                1L,
                brandDTO,
                0.0,
                "ABC123",
                4L,
                discountDTOList,
                finalProductDTOList,
                BigDecimal.ZERO,
                orderFlagDTO,
                true,
                1L,
                1L,
                "Anything",
                34343434L,
                "7000",
                originDTO,
                false,
                1L,
                serviceDTOList,
                orderStoreDTO,
                BigDecimal.ZERO,
                8900.00,
                0.0,
                0.0,
                1L,
                1L,
                "m6ux5o0xsc5a",
                "50eaf34f-7252-46ef-9a69-2225b06e14be",
                1L,
                8900.00,
                0.0,
                0.0
        );
    }

    public ResponseCreatedOrderDTO getResponseCreatedOrder() {
        return new ResponseCreatedOrderDTO(
                0.0,
                Collections.emptyList(),
                1L,
                null,
                null,
                null,
                8900.0,
                0.0,
                BigDecimal.ZERO,
                8900.0,
                null,
                "50eaf34f-7252-46ef-9a69-2225b06e14be"
        );
    }

    public OrderDTO getDataWithoutServices(Long deliveryTypeId) {
        return new OrderDTO(
                1L,
                brandDTO,
                0.0,
                "ABC123",
                deliveryTypeId,
                discountDTOList,
                finalProductDTOList,
                BigDecimal.ONE,
                orderFlagDTO,
                true,
                1L,
                1L,
                "Anything",
                34343434L,
                "7000",
                originDTO,
                true,
                1L,
                Collections.emptyList(),
                orderStoreDTO,
                BigDecimal.ZERO,
                8900.00,
                0.0,
                0.0,
                1L,
                1L,
                "m6ux5o0xsc5a",
                "50eaf34f-7252-46ef-9a69-2225b06e14be",
                1L,
                8900.00,
                0.0,
                0.0
        );
    }

    public OrderDTO getDataNullServices(Long deliveryTypeId) {
        return new OrderDTO(
                1L,
                brandDTO,
                0.0,
                "ABC123",
                deliveryTypeId,
                discountDTOList,
                finalProductDTOList,
                BigDecimal.ONE,
                orderFlagDTO,
                true,
                1L,
                1L,
                "Anything",
                34343434L,
                "7000",
                originDTO,
                true,
                1L,
                null,
                orderStoreDTO,
                BigDecimal.ZERO,
                8900.00,
                0.0,
                0.0,
                1L,
                1L,
                "m6ux5o0xsc5a",
                UUID.randomUUID().toString(),
                1L,
                8900.00,
                0.0,
                0.0
        );
    }

    public OrderDTO getDataDefaultWithOrderIdIsNull() {
        return new OrderDTO(
                1L,
                brandDTO,
                0.0,
                "ABC123",
                1L,
                discountDTOList,
                finalProductDTOList,
                BigDecimal.ONE,
                orderFlagDTO,
                true,
                null,
                1L,
                "Anything",
                34343434L,
                "7000",
                originDTO,
                true,
                1L,
                serviceDTOList,
                orderStoreDTO,
                BigDecimal.ZERO,
                8900.00,
                0.0,
                0.0,
                1L,
                1L,
                "m6ux5o0xsc5a",
                "50eaf34f-7252-46ef-9a69-2225b06e14be",
                1L,
                8900.00,
                0.0,
                0.0
        );
    }

    public static List<OrderDiscountEntity> getOrderDiscountEntityListMock = List.of(OrderDiscountEntity.builder()
            .discountId(1782L)
            .discountValue(8900.0)
            .orderId(1L)
            .orderDiscountTypeId(3L)
            .orderFinalProductId(1L)
            .build());

    public List<OrderDTO> getDataDefaultList() {
        return Collections.singletonList(getDataDefault(1L));
    }

    public List<OrderDTO> getDataDefaultDeliveryExternalList() {
        return Collections.singletonList(getDataDefault(4L));
    }

    public List<OrderDTO> getDataDefaultDeliveryPosList() {
        return Collections.singletonList(getDataDefault(2L));
    }

    public List<OrderDTO> getDataDefaultListDeduction() {
        return Collections.singletonList(getDataDefaultDeduction(4L));
    }

    public List<OrderDTO> getDataDefaultFoodCoins() {
        return Collections.singletonList(getDataDefaultFoodCoins(4L));
    }

    public List<OrderDTO> getDataWithDeliveryTypeIdThree() {
        return Collections.singletonList(getDataDefault(3L));
    }

    public List<OrderDTO> getData_No_Product_Discount() {
        return Collections.singletonList(getDataDefault_No_Product_Discount(3L));
    }

    public List<OrderDTO> getData_with_Product_Discount() {
        return Collections.singletonList(getDataDefault_With_Product(3L));
    }

    public List<OrderDTO> getDataDefaultListNotPaid() {
        return Collections.singletonList(getDataDefaultNotPaid());
    }

    public List<OrderDTO> getData_Without_Services() {
        return Collections.singletonList(getDataWithoutServices(3L));
    }

    public List<OrderDTO> getData_Null_Services() {
        return Collections.singletonList(getDataWithoutServices(3L));
    }

}
