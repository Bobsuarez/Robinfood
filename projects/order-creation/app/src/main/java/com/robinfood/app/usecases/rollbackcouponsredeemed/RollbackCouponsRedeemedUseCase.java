package com.robinfood.app.usecases.rollbackcouponsredeemed;

import com.robinfood.core.dtos.ConfigRedeemCouponDTO;
import com.robinfood.core.dtos.redeemcoupon.RevertCouponRequestDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.repository.coupons.ICouponSystemRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletionException;

@Service
@Slf4j
public class RollbackCouponsRedeemedUseCase implements IRollbackCouponsRedeemedUseCase {

    private final ICouponSystemRepository couponSystemRepository;

    public RollbackCouponsRedeemedUseCase(ICouponSystemRepository couponSystemRepository) {
        this.couponSystemRepository = couponSystemRepository;
    }

    @Override
    public Object invoke(@NotNull String token, @NotNull TransactionRequestDTO transactionRequest) {

        log.info("Coupons rollback started");

        try {
            
            final ConfigRedeemCouponDTO configRedeemCouponDTO = couponSystemRepository.getRedeemCouponRedeemIds();

            if (configRedeemCouponDTO == null ||
                    configRedeemCouponDTO.getRedeemIds() == null ||
                    configRedeemCouponDTO.getRedeemIds().isEmpty()) {
                return null;
            }

            return couponSystemRepository.revertRedeemIds(
                    token,
                    new RevertCouponRequestDTO(configRedeemCouponDTO.getRedeemIds())
            );

        } catch (CancellationException | CompletionException exception) {

            log.error(String.valueOf(exception));

            throw new TransactionCreationException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    exception.getLocalizedMessage()
            );
        }
    }
}
