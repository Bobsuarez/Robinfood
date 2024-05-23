package com.robinfood.app.usecases.validatecoupons;

import com.robinfood.app.usecases.distributediscounts.IDistributeDiscountsUseCase;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponRequestDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.CouponDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.userentities.UserDetailEntity;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.mappers.PerformCouponMappers;
import com.robinfood.repository.coupons.ICouponSystemRepository;
import com.robinfood.repository.user.IUserRepository;
import kotlin.collections.CollectionsKt;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_COUPON_ORDER_ID;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;
import static com.robinfood.core.enums.logs.OrderLogEnum.ORDER_VALIDATE_PERFORM_COUPON_REQUEST;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@Service
@Slf4j
public class ValidateCouponUseCase implements IValidateCouponUseCase {

    private final ICouponSystemRepository couponSystemRepository;
    private final IUserRepository userRepository;
    @Qualifier("distributeCouponsUseCase")
    private final IDistributeDiscountsUseCase distributeCouponsUseCase;
    private final PerformCouponMappers performCouponMappers;

    public ValidateCouponUseCase(
            ICouponSystemRepository couponSystemRepository,
            IDistributeDiscountsUseCase distributeCouponsUseCase,
            IUserRepository userRepository,
            PerformCouponMappers performCouponMappers
    ) {
        this.couponSystemRepository = couponSystemRepository;
        this.distributeCouponsUseCase = distributeCouponsUseCase;
        this.userRepository = userRepository;
        this.performCouponMappers = performCouponMappers;
    }

    public CompletableFuture<TransactionCreationResult> invoke(
            @NonNull String token,
            @NonNull TransactionRequestDTO transactionRequest
    ) {
        log.info("Validate coupons has started with request: {}", objectToJson(transactionRequest));

        final List<FinalProductDTO> finalProductsOfAllOrders = new ArrayList<>();
        final OrderDTO getFirstOrder = transactionRequest.getOrders().stream().findFirst().get();

        for (OrderDTO orderDTO : transactionRequest.getOrders()) {
            finalProductsOfAllOrders.addAll(orderDTO.getFinalProducts());
        }

        final List<PerformCouponResponseDTO> couponValidations = new ArrayList<>();
        final List<CouponDTO> orderCoupons = transactionRequest.getCoupons();

        UserDetailEntity userDetailDTO = userRepository
                .getUserDetail(token, transactionRequest.getUser().getId()
                ).join();

        for (CouponDTO couponDTO : orderCoupons) {

            final PerformCouponRequestDTO performCouponRequestDTO = performCouponMappers.toRedeemCouponRequestDTO(
                    DEFAULT_COUPON_ORDER_ID,
                    transactionRequest,
                    finalProductsOfAllOrders,
                    getFirstOrder.getStore(),
                    couponDTO,
                    userDetailDTO
            );

            log.info(ORDER_VALIDATE_PERFORM_COUPON_REQUEST.getMessage(), objectToJson(performCouponRequestDTO));

            couponValidations.add(
                    couponSystemRepository.performCouponRequest(token, performCouponRequestDTO)
            );
        }

        if (!couponValidations.isEmpty() && Boolean.TRUE.equals(transactionRequest.getPaid())) {

            BigDecimal couponsTotal = CollectionsKt.map(couponValidations, PerformCouponResponseDTO::getDiscount)
                    .stream()
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            if (couponsTotal.compareTo(transactionRequest.getTotalMinusCoupons()) > DEFAULT_INTEGER_VALUE) {
                couponsTotal = transactionRequest.getTotal();
            }

            transactionRequest.setSubtotalOnlyWithDiscount(transactionRequest.getTotal().add(couponsTotal));

            final Long discountId = couponValidations.get(DEFAULT_INTEGER_VALUE).getCodeId();

            for (OrderDTO orderDTO : transactionRequest.getOrders()) {
                distributeCouponsUseCase.invoke(orderDTO, couponsTotal, discountId, transactionRequest);
            }
        }

        return CompletableFuture.completedFuture(
                TransactionCreationResult.StepValidationSuccess.INSTANCE
        );
    }
}
