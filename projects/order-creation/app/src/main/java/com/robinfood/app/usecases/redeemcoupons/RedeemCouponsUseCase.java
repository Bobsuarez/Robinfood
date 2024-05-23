package com.robinfood.app.usecases.redeemcoupons;

import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.ConfigRedeemCouponDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.CouponDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.userentities.UserDetailEntity;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.exceptions.WriteInTransactionException;
import com.robinfood.core.mappers.PerformCouponMappers;
import com.robinfood.repository.coupons.ICouponSystemRepository;
import com.robinfood.repository.transaction.ITransactionRepository;
import com.robinfood.repository.user.IUserRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE;
import static kotlin.collections.CollectionsKt.mapNotNull;

@Service
@Slf4j
public class RedeemCouponsUseCase implements IRedeemCouponsUseCase {

    private final ICouponSystemRepository couponSystemRepository;
    private final ITransactionRepository transactionRepository;
    private final IUserRepository userRepository;

    public RedeemCouponsUseCase(
            ICouponSystemRepository couponSystemRepository,
            ITransactionRepository transactionRepository,
            IUserRepository userRepository
    ) {
        this.couponSystemRepository = couponSystemRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    private CompletableFuture<TransactionCreationResult> isPaidFalse(TransactionRequestDTO transactionRequestDTO) {

        List<PerformCouponResponseDTO> couponList = transactionRequestDTO.getCoupons().stream().map(
                coupon ->
                        new PerformCouponResponseDTO(
                                coupon.getCode(), null,
                                GlobalConstants.COUPON_TYPE_DEFAULT,
                                coupon.getValue(), null))
                .collect(Collectors.toList());

        transactionRequestDTO.setPerformCouponResponseDTOS(couponList);

        return CompletableFuture.completedFuture(
                TransactionCreationResult.StepValidationSuccess.INSTANCE
        );
    }

    private CompletableFuture<TransactionCreationResult> isPaidTrue(
            String token,
            TransactionRequestDTO transactionRequestDTO
    ) {
        final Long transactionId = transactionRepository.getTransactionResponseDTO()
                .getTransactionCreationResponse()
                .getTransaction().getId();

        if (transactionId.equals(DEFAULT_LONG_VALUE)) {
            throw new WriteInTransactionException(
                    HttpStatus.PRECONDITION_FAILED,
                    "The total of the requested orders is different from the total of the created orders",
                    TransactionCreationErrors.INCONSISTENT_TRANSACTION_ERROR
            );
        }

        final List<PerformCouponResponseDTO> resultRedemption = new ArrayList<>();

        final List<FinalProductDTO> finalProductsOfAllOrders = new ArrayList<>();
        final OrderDTO getFirstOrder = transactionRequestDTO.getOrders().stream().findFirst().get();

        for (OrderDTO orderDTO : transactionRequestDTO.getOrders()) {
            finalProductsOfAllOrders.addAll(orderDTO.getFinalProducts());
        }

        UserDetailEntity userDetailDTO = userRepository
                .getUserDetail(token, transactionRequestDTO.getUser().getId())
                .join();

        resultRedemption.addAll(new ArrayList<>(mapNotNull(
                transactionRequestDTO.getCoupons(),
                (CouponDTO couponDTO) -> couponSystemRepository.performCouponRequest(token,
                        PerformCouponMappers.toRedeemCouponRequestDTO(
                                transactionId,
                                transactionRequestDTO,
                                finalProductsOfAllOrders,
                                getFirstOrder.getStore(),
                                couponDTO,
                                userDetailDTO
                        )
                )))
        );

        final List<String> redeemIds = resultRedemption
                .stream()
                .map(PerformCouponResponseDTO::getRedeemedId)
                .collect(Collectors.toList());

        final List<Long> redeemsLong = redeemIds.stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());

        transactionRequestDTO.setPerformCouponResponseDTOS(resultRedemption);
        couponSystemRepository.setRedeemCouponRedeemIds(new ConfigRedeemCouponDTO(redeemsLong));

        return CompletableFuture.completedFuture(
                TransactionCreationResult.StepValidationSuccess.INSTANCE
        );
    }

    @Override
    public CompletableFuture<TransactionCreationResult> invoke(
            @NonNull String token,
            @NonNull TransactionRequestDTO transactionRequestDTO
    ) {
        log.info("Starting to coupon redemption has started with request: {}", transactionRequestDTO);

        if (Boolean.FALSE.equals(transactionRequestDTO.getPaid())) {
            return isPaidFalse(transactionRequestDTO);
        }

        try {
            return isPaidTrue(token, transactionRequestDTO);
        } catch (
                CancellationException |
                        CompletionException |
                        TransactionCreationException exception
        ) {
            log.error("Error {} {} {}", HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage(), exception);
            throw new WriteInTransactionException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    exception.getLocalizedMessage()
            );
        }
    }
}
