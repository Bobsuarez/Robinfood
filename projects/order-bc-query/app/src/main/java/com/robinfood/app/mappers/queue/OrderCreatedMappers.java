package com.robinfood.app.mappers.queue;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

import com.robinfood.core.dtos.OrderCouponDTO;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.queue.AddressQueueDTO;
import com.robinfood.core.dtos.queue.BrandQueueDTO;
import com.robinfood.core.dtos.queue.CouponDTO;
import com.robinfood.core.dtos.queue.DiscountDTO;
import com.robinfood.core.dtos.queue.OrderCreatedQueueDTO;
import com.robinfood.core.dtos.queue.PaymentsDTO;
import com.robinfood.core.dtos.queue.ProductDTO;
import com.robinfood.core.dtos.queue.SizeQueueDTO;
import com.robinfood.core.dtos.queue.StoreDTO;
import com.robinfood.core.dtos.queue.UserDTO;
import com.robinfood.core.dtos.request.order.BrandDTO;
import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.dtos.request.order.FinalProductSizeDTO;
import com.robinfood.core.dtos.request.order.OrderDTO;
import com.robinfood.core.dtos.request.transaction.AddressDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.dtos.request.transaction.RequestUserDTO;
import com.robinfood.core.dtos.response.order.ResponseCreatedOrderDTO;
import com.robinfood.core.dtos.response.order.ResponseOrderDiscountDTO;
import com.robinfood.core.dtos.response.transaction.ResponseCreatedOrderTransactionDTO;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedMappers {

    public List<OrderCreatedQueueDTO> toOrderCreatedDTOs(
        RequestOrderTransactionDTO requestTransactionDTO,
        ResponseCreatedOrderTransactionDTO responseTransactionDTO,
        List<OrderPaymentDTO> orderPaymentDTOS
    ) {

        return IntStream.range(DEFAULT_INTEGER_VALUE, responseTransactionDTO.getOrders().size())
            .mapToObj((int index) -> {
                OrderDTO requestOrderDTO = requestTransactionDTO.getOrders().get(index);
                ResponseCreatedOrderDTO responseOrderDTO = responseTransactionDTO
                    .getOrders().get(index);

                return toOrderCreatedDTO(
                    requestTransactionDTO,
                    requestOrderDTO,
                    responseTransactionDTO,
                    responseOrderDTO,
                    orderPaymentDTOS
                );
            }).collect(Collectors.toList());
    }

    /**
     *
     * @param requestTransactionDTO     Request transaction
     * @param requestOrderDTO           Request order
     * @param responseTransactionDTO    Created transaction (Database)
     * @param responseOrderDTO          Created order (Database)
     * @param orderPaymentDTOS          Created payment methods (Database)
     *
     * @return {@OrderCreatedQueueDTO}
     */
    private OrderCreatedQueueDTO toOrderCreatedDTO(
        RequestOrderTransactionDTO requestTransactionDTO,
        OrderDTO requestOrderDTO,
        ResponseCreatedOrderTransactionDTO responseTransactionDTO,
        ResponseCreatedOrderDTO responseOrderDTO,
        List<OrderPaymentDTO> orderPaymentDTOS
    ) {

        final List<CouponDTO> couponDTOS = toCouponsDTO(requestTransactionDTO.getCouponResponseEntities());

        OrderCreatedQueueDTO orderCreatedQueueDTO = new OrderCreatedQueueDTO();
        orderCreatedQueueDTO.setAddress(toAddressDTO(requestTransactionDTO.getAddress()));
        orderCreatedQueueDTO.setCoupons(couponDTOS);
        orderCreatedQueueDTO.setTransactionUuid(requestTransactionDTO.getUuid().toString());
        orderCreatedQueueDTO.setId(requestOrderDTO.getId());
        orderCreatedQueueDTO.setOrderUid(responseOrderDTO.getUid());
        orderCreatedQueueDTO.setOrderUuid(requestOrderDTO.getUuid());
        orderCreatedQueueDTO.setOrderNumber(requestOrderDTO.getOrderNumber());
        orderCreatedQueueDTO.setOrderDate(responseTransactionDTO.getCreatedAt().toString());
        orderCreatedQueueDTO.setPaid(requestTransactionDTO.getPaid());
        orderCreatedQueueDTO.setTransactionId(requestTransactionDTO.getId());
        orderCreatedQueueDTO.setFlowId(requestTransactionDTO.getFlowId());
        orderCreatedQueueDTO.setCountryId(requestTransactionDTO.getCompany().getId());
        orderCreatedQueueDTO.setProducts(toFinalProductDTOs(requestOrderDTO.getFinalProducts()));
        orderCreatedQueueDTO.setStore(toStoreDTO(requestOrderDTO.getStore()));
        orderCreatedQueueDTO.setUser(toUserDTO(requestTransactionDTO.getUser()));
        orderCreatedQueueDTO.setDiscounts(toDiscountDTOs(responseOrderDTO.getDiscounts()));
        orderCreatedQueueDTO.setCompanyId(requestTransactionDTO.getCompany().getId());
        orderCreatedQueueDTO.setTimeZone(requestTransactionDTO.getDevice().getTimezone());
        orderCreatedQueueDTO.setInvoice(Long.valueOf(responseOrderDTO.getOrderInvoiceNumber()));
        orderCreatedQueueDTO.setSubtotal(BigDecimal.valueOf(responseOrderDTO.getSubtotal()));
        orderCreatedQueueDTO.setTax(BigDecimal.valueOf(responseOrderDTO.getTaxPrice()));
        orderCreatedQueueDTO.setDiscount(BigDecimal.valueOf(responseOrderDTO.getDiscountPrice()));
        orderCreatedQueueDTO.setTotal(BigDecimal.valueOf(responseOrderDTO.getTotal()));
        orderCreatedQueueDTO.setPayments(toPaymentsDTO(responseOrderDTO, orderPaymentDTOS));

        return orderCreatedQueueDTO;
    }

    private List<CouponDTO> toCouponsDTO(List<OrderCouponDTO> orderCouponDTOS) {
        return orderCouponDTOS.stream().map(orderCouponDTO -> CouponDTO.builder()
                .typeId(orderCouponDTO.getCouponType())
                .reference(orderCouponDTO.getCode())
                .redeemedId(orderCouponDTO.getRedeemedId())
                .value(orderCouponDTO.getValue())
                .build()).collect(Collectors.toList());
    }

    private List<PaymentsDTO> toPaymentsDTO (
        ResponseCreatedOrderDTO responseOrderDTO,
        List<OrderPaymentDTO> orderPaymentDTOS
    ) {
        if (Objects.isNull(orderPaymentDTOS)) {
            return Collections.emptyList();
        }

        return orderPaymentDTOS.stream()
            .filter(orderPaymentDTO -> orderPaymentDTO.getOrderId().equals(responseOrderDTO.getId()))
            .map(orderPaymentDTO -> PaymentsDTO.builder()
                .id(orderPaymentDTO.getPaymentMethodId())
                .subtotal(orderPaymentDTO.getSubtotal().toString())
                .tax(orderPaymentDTO.getTax().toString())
                .discount(orderPaymentDTO.getDiscount().toString())
                .value(orderPaymentDTO.getValue().toString())
                .build()
            ).collect(Collectors.toList());
    }

    private AddressQueueDTO toAddressDTO(
        AddressDTO addressDTO
    ) {
        if (Objects.isNull(addressDTO)) {
            return null;
        }

        return AddressQueueDTO.builder()
            .address(addressDTO.getAddress())
            .comment(addressDTO.getExtra())
            .latitude(addressDTO.getLatitude())
            .longitude(addressDTO.getLongitude())
            .zipCode(addressDTO.getZipCode())
            .build();
    }

    private List<ProductDTO> toFinalProductDTOs(List<FinalProductDTO> finalProducts) {

        return finalProducts.stream()
            .map(product ->
                new ProductDTO(
                    product.getId(),
                    product.getArticle().getId(),
                    product.getImage(),
                    product.getName(),
                    toSizeQueueDTO(product.getSize()),
                    toBrandQueueDTO(product.getBrand()),
                    BigDecimal.valueOf(product.getValue()),
                    product.getTotalDiscounts(),
                    product.getQuantity()
                )
            )
            .collect(Collectors.toList());
    }

    private BrandQueueDTO toBrandQueueDTO(
        BrandDTO brandDTO
    ) {
        return BrandQueueDTO.builder()
            .id(brandDTO.getId())
            .sgiId(brandDTO.getFranchiseId())
            .name(brandDTO.getName())
            .build();
    }

    private SizeQueueDTO toSizeQueueDTO(
        FinalProductSizeDTO finalProductSizeDTO
    ) {
        return SizeQueueDTO.builder()
            .id(finalProductSizeDTO.getId())
            .name(finalProductSizeDTO.getName())
            .build();
    }

    private StoreDTO toStoreDTO(com.robinfood.core.dtos.request.order.StoreDTO store) {

        if (store != null) {
            StoreDTO storeDTO = new StoreDTO();
            storeDTO.setId(store.getId());
            storeDTO.setName(store.getName());
            return storeDTO;
        }
        return null;

    }

    private UserDTO toUserDTO(RequestUserDTO user) {

        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(user.getEmail());
            userDTO.setFirstName(user.getName());
            userDTO.setLastName(user.getLastName());
            userDTO.setId(user.getId());
            userDTO.setPhoneCode(user.getPhoneCode());
            userDTO.setPhoneNumber(user.getMobile());
            return userDTO;
        }
        return null;
    }

    private List<DiscountDTO> toDiscountDTOs(
        List<ResponseOrderDiscountDTO> orderDiscounts
    ) {
        if (orderDiscounts != null) {
            return orderDiscounts.stream().map(discountApplied ->
                new DiscountDTO(
                    discountApplied.getId(),
                    null,
                    discountApplied.getValue(),
                    discountApplied.getTypeId(),
                    null
                )
            ).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
