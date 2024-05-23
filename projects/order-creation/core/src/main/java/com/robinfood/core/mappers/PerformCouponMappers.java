package com.robinfood.core.mappers;

import static com.robinfood.core.constants.GlobalConstants.COUPON_TYPE_DEFAULT;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;
import static com.robinfood.core.constants.GlobalConstants.VALIDATION_PAYMENT_METHOD;

import com.robinfood.core.dtos.redeemcoupon.PerformCouponEntityDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponOrderDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponOriginDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponPaymentDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponRequestDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponUserDTO;
import com.robinfood.core.dtos.redeemcoupon.RedeemCouponPaymentMethodDTO;
import com.robinfood.core.dtos.redeemcoupon.RedeemCouponProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.CompanyDTO;
import com.robinfood.core.dtos.transactionrequestdto.CouponDTO;
import com.robinfood.core.dtos.transactionrequestdto.DeviceDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDTO;
import com.robinfood.core.dtos.transactionrequestdto.StoreDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.userentities.UserDetailEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public final class PerformCouponMappers {

    private PerformCouponMappers() {
    }

    public static PerformCouponRequestDTO toRedeemCouponRequestDTO(
            Long transactionId,
            TransactionRequestDTO transactionRequestDTO,
            List<FinalProductDTO> finalProductDTOS,
            StoreDTO storeDTO,
            CouponDTO couponDTO,
            UserDetailEntity userDetailDTO
    ) {
        return PerformCouponRequestDTO.builder()
                .code(couponDTO.getCode())
                .entity(toPerformCouponEntityDTO(transactionRequestDTO))
                .order(PerformCouponMappers.toRedeemCouponOrderDTO(
                        finalProductDTOS, transactionId, transactionRequestDTO
                ))
                .origin(PerformCouponMappers.toRedeemCouponOriginDTO(storeDTO, transactionRequestDTO.getCompany(),
                        transactionRequestDTO.getDevice(), transactionRequestDTO.getOrigin().getId()))
                .payment(
                        PerformCouponMappers.toRedeemCouponPaymentDTO(
                                transactionRequestDTO.getOrders(),
                                transactionRequestDTO.getPaymentMethods(),
                                transactionRequestDTO.getPaid()
                        )
                )
                .user(PerformCouponMappers.toRedeemCouponUserDTO(userDetailDTO))
                .build();
    }

    public static PerformCouponEntityDTO toPerformCouponEntityDTO(TransactionRequestDTO transactionRequestDTO) {
        return PerformCouponEntityDTO.builder()
                .id(COUPON_TYPE_DEFAULT)
                .reference(transactionRequestDTO.getUuid().toString())
                .source(transactionRequestDTO.getId())
                .build();
    }

    public static PerformCouponOrderDTO toRedeemCouponOrderDTO(
            List<FinalProductDTO> finalProductDTOS,
            Long transactionId,
            TransactionRequestDTO transactionRequestDTO
    ) {
        return new PerformCouponOrderDTO(
                transactionRequestDTO.getFlowId(),
                transactionId,
                PerformCouponMappers.toListRedeemCouponProductDTO(finalProductDTOS)
        );
    }

    public static List<RedeemCouponProductDTO> toListRedeemCouponProductDTO(
            List<FinalProductDTO> finalProducts
    ) {
        return finalProducts.stream()
                .map(PerformCouponProductMappers::toRedeemCouponProductDTO)
                .collect(Collectors.toList());
    }

    public static PerformCouponUserDTO toRedeemCouponUserDTO(
            UserDetailEntity userDetailDTO
    ) {
        List<PerformCouponUserDTO.Counter> counters =
                userDetailDTO.getCounters().stream().map(
                        PerformCouponMappers::toPerformCouponUserDTOCounter
                ).collect(Collectors.toList());

        return new PerformCouponUserDTO(
                userDetailDTO.getFirstName(),
                userDetailDTO.getLastName(),
                DEFAULT_INTEGER_VALUE,
                userDetailDTO.getPhoneCode(),
                userDetailDTO.getPhoneNumber(),
                userDetailDTO.getId(),
                userDetailDTO.getEmail(),
                userDetailDTO.getIsEmployee(),
                counters
        );
    }

    private static PerformCouponUserDTO.Counter toPerformCouponUserDTOCounter(
            UserDetailEntity.Counter userDetailEntityCounter
    ) {
        return PerformCouponUserDTO.Counter.builder()
                .key(userDetailEntityCounter.getKey())
                .reference(userDetailEntityCounter.getReference())
                .value(userDetailEntityCounter.getValue())
                .build();
    }

    public static PerformCouponOriginDTO toRedeemCouponOriginDTO(
            StoreDTO storeDTO,
            CompanyDTO companyDTO,
            DeviceDTO deviceDTO,
            Long originId
    ) {
        return new PerformCouponOriginDTO(
                companyDTO.getId(),
                deviceDTO.getIp(),
                originId,
                deviceDTO.getVersion(),
                storeDTO.getId(),
                deviceDTO.getTimezone()
        );
    }

    public static PerformCouponPaymentDTO toRedeemCouponPaymentDTO(
            Iterable<OrderDTO> orderDTOS,
            List<PaymentMethodDTO> paymentMethodDTOS,
            Boolean paid
    ) {
        BigDecimal total = BigDecimal.ZERO;

        for (OrderDTO order : orderDTOS) {
            BigDecimal totalProduct = order.getFinalProducts().stream()
                    .map(FinalProductDTO::getTotalDiscounts)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            total = total.add(totalProduct);
        }

        PaymentMethodDTO firstPayment = PaymentMethodDTO.builder().build();

        if (!paymentMethodDTOS.isEmpty()) {
            firstPayment = paymentMethodDTOS.get(0);
        }

        if (Boolean.FALSE.equals(paid)) {
            firstPayment = PaymentMethodDTO.builder()
                    .id(VALIDATION_PAYMENT_METHOD)
                    .build();
        }

        return new PerformCouponPaymentDTO(
                firstPayment.getId(),
                PerformCouponMappers.toListRedeemCouponPaymentDTO(
                        paymentMethodDTOS
                ),
                total
        );
    }

    public static List<RedeemCouponPaymentMethodDTO> toListRedeemCouponPaymentDTO(
            List<PaymentMethodDTO> paymentMethodDTOS
    ) {
        return paymentMethodDTOS.stream().map(PerformCouponMappers::toRedeemCouponPaymentMethodDTO)
                .collect(Collectors.toList());
    }

    public static RedeemCouponPaymentMethodDTO toRedeemCouponPaymentMethodDTO(
            PaymentMethodDTO paymentMethodDTO
    ) {
        return new RedeemCouponPaymentMethodDTO(
                paymentMethodDTO.getId(),
                paymentMethodDTO.getValue()
        );
    }
}
