package com.robinfood.app.mappers.queue;

import com.robinfood.core.dtos.OrderAddressDTO;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.core.dtos.OrderFinalProductDTO;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.TransactionFlowDTO;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.dtos.queue.AddressQueueDTO;
import com.robinfood.core.dtos.queue.BrandQueueDTO;
import com.robinfood.core.dtos.queue.DiscountDTO;
import com.robinfood.core.dtos.queue.OrderCreatedQueueDTO;
import com.robinfood.core.dtos.queue.PaymentsDTO;
import com.robinfood.core.dtos.queue.PickupTimeQueueDTO;
import com.robinfood.core.dtos.queue.ProductDTO;
import com.robinfood.core.dtos.queue.SizeQueueDTO;
import com.robinfood.core.dtos.queue.StoreDTO;
import com.robinfood.core.dtos.queue.UserDTO;
import com.robinfood.core.models.domain.PickupTime;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderPaidToQueueMappers {

    public OrderCreatedQueueDTO toOrderCreatedQueueDTO(
        OrderDTO orderDTO,
        TransactionFlowDTO transactionFlowDTO,
        List<OrderDiscountDTO> discountDTOS,
        OrderAddressDTO addressDTO,
        List<OrderFinalProductDTO> finalProductDTOS,
        List<OrderPaymentDTO> paymentDTOS,
        List<PickupTime> pickupTimes,
        UserDataDTO userDataDTO
    ) {
        return OrderCreatedQueueDTO.builder()
            .address(toAddressQueueDTO(addressDTO))
            .countryId(orderDTO.getCompanyId())
            .companyId(orderDTO.getCompanyId())
            .discount(BigDecimal.valueOf(orderDTO.getDiscounts()))
            .discounts(toDiscountDTOListQueue(discountDTOS))
            .id(orderDTO.getId())
            .invoice(Long.valueOf(orderDTO.getOrderInvoiceNumber()))
            .orderUid(orderDTO.getUid())
            .orderNumber(orderDTO.getOrderNumber())
            .flowId(transactionFlowDTO.getFlowId())
            .orderDate(orderDTO.getCreatedAt().toString())
            .paid(Boolean.TRUE)
            .payments(toPaymentsQueueDTO(paymentDTOS))
            .pickupTimes(toPickupTimesDTO(pickupTimes))
            .products(toProductDTOSQueue(finalProductDTOS))
            .store(toStoreDTOQueue(orderDTO))
            .subtotal(BigDecimal.valueOf(orderDTO.getSubtotal()))
            .tax(BigDecimal.valueOf(orderDTO.getTaxes()))
            .transactionId(orderDTO.getTransactionId())
            .total(BigDecimal.valueOf(orderDTO.getTotal()))
            .user(toUserDTOQueue(userDataDTO))
            .build();
    }

    private List<PickupTimeQueueDTO> toPickupTimesDTO(
        List<PickupTime> pickupTimes
    ) {
        if (Objects.isNull(pickupTimes)) {
            return Collections.emptyList();
        }

        List<PickupTimeQueueDTO> pickupTimeQueueDTOS = Collections.emptyList();

        pickupTimes
            .forEach(pickupTime ->
                pickupTimeQueueDTOS.addAll(
                    pickupTime.getStores().stream()
                        .map(store -> PickupTimeQueueDTO.builder()
                            .storeId(store.getId())
                            .pickupTime(store.getPickuptime())
                            .build()
                        ).collect(Collectors.toList())
                )
            );

        return pickupTimeQueueDTOS;
    }

    private List<PaymentsDTO> toPaymentsQueueDTO (
        List<OrderPaymentDTO> paymentDTOS
    ) {
        if (Objects.isNull(paymentDTOS)) {
            return Collections.emptyList();
        }

        return paymentDTOS.stream()
            .map(orderPaymentDTO -> PaymentsDTO.builder()
                .id(orderPaymentDTO.getPaymentMethodId())
                .subtotal(orderPaymentDTO.getSubtotal().toString())
                .tax(orderPaymentDTO.getTax().toString())
                .discount(orderPaymentDTO.getDiscount().toString())
                .value(orderPaymentDTO.getValue().toString())
                .build()
            ).collect(Collectors.toList());
    }

    private AddressQueueDTO toAddressQueueDTO(
        OrderAddressDTO addressDTO
    ) {
        if (Objects.isNull(addressDTO)) {
            return null;
        }

        return AddressQueueDTO.builder()
            .address(addressDTO.getAddress())
            .comment(addressDTO.getNotes())
            .latitude(addressDTO.getLatitude())
            .longitude(addressDTO.getLongitude())
            .zipCode(addressDTO.getZipCode())
            .build();
    }

    private UserDTO toUserDTOQueue(
        UserDataDTO userDataDTO
    ) {
       return UserDTO.builder()
           .email(userDataDTO.getEmail())
           .firstName(userDataDTO.getFirstName())
           .id(userDataDTO.getId())
           .lastName(userDataDTO.getLastName())
           .phoneNumber(userDataDTO.getMobile())
           .build();
    }

    private StoreDTO toStoreDTOQueue(
        OrderDTO orderDTO
    ) {
        return StoreDTO.builder()
            .id(orderDTO.getStoreId())
            .name(orderDTO.getStoreName())
            .build();
    }

    private List<ProductDTO> toProductDTOSQueue(
        List<OrderFinalProductDTO> finalProductDTOS
    ) {
        if (Objects.isNull(finalProductDTOS)) {
            return Collections.emptyList();
        }

        return finalProductDTOS.stream()
            .map(finalProductDTO -> ProductDTO.builder()
                .brand(toBrandQueueDTO(finalProductDTO))
                .discount(BigDecimal.valueOf(finalProductDTO.getDiscountPrice()))
                .id(finalProductDTO.getFinalProductId())
                .img(finalProductDTO.getImage())
                .name(finalProductDTO.getFinalProductName())
                .size(toSizeQueueDTO(finalProductDTO))
                .price(BigDecimal.valueOf(finalProductDTO.getBasePrice()))
                .productId(finalProductDTO.getArticleId())
                .quantity(finalProductDTO.getQuantity())
                .build()
            )
            .collect(Collectors.toList());
    }

    private BrandQueueDTO toBrandQueueDTO(
        OrderFinalProductDTO finalProductDTO
    ) {
        return BrandQueueDTO.builder()
            .id(finalProductDTO.getBrandMenuId())
            .sgiId(finalProductDTO.getBrandId())
            .name(finalProductDTO.getBrandName())
            .build();
    }

    private SizeQueueDTO toSizeQueueDTO (
        OrderFinalProductDTO finalProductDTO
    ) {
        return SizeQueueDTO.builder()
            .id(finalProductDTO.getSizeId())
            .name(finalProductDTO.getSizeName())
            .build();
    }

    private List<DiscountDTO> toDiscountDTOListQueue(
        List<OrderDiscountDTO> discountDTOS
    ) {
        if (Objects.isNull(discountDTOS)) {
            return Collections.emptyList();
        }

        return discountDTOS.stream()
            .map(discountDTO -> DiscountDTO.builder()
                .id(discountDTO.getId())
                .value(BigDecimal.valueOf(discountDTO.getDiscountValue()))
                .typeId(discountDTO.getOrderDiscountTypeId())
                .build()
            )
            .collect(Collectors.toList());
    }

}
