package com.robinfood.app.usecases.revertcouponredeemed;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.rollbackcouponsredeemed.RollbackCouponsRedeemedUseCase;
import com.robinfood.core.dtos.ConfigRedeemCouponDTO;
import com.robinfood.core.dtos.redeemcoupon.RevertCouponRequestDTO;
import com.robinfood.core.dtos.redeemcoupon.RevertCouponResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.repository.coupons.ICouponSystemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CancellationException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RollbackFoodCoinsUseCaseTest {

    @Mock
    private ICouponSystemRepository mockCouponSystemRepository;

    @InjectMocks
    private RollbackCouponsRedeemedUseCase rollbackCouponsRedeemedUseCase;

    private final String token = "token";

    private final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();

    private final List<Long> redeemIds = Collections.singletonList(1L);

    private final RevertCouponRequestDTO revertCouponRequestDTO = new RevertCouponRequestDTO(
            redeemIds
    );

    private final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestDTO;

    @Test
    void test_When_Revert_Redeem_Success() {

        when(mockCouponSystemRepository.getRedeemCouponRedeemIds())
                .thenReturn(new ConfigRedeemCouponDTO(redeemIds));

        when(mockCouponSystemRepository.revertRedeemIds(token, revertCouponRequestDTO))
                .thenReturn(new RevertCouponResponseDTO());

        assertDoesNotThrow(
                () -> rollbackCouponsRedeemedUseCase.invoke(
                        token,
                        transactionRequestDTO
                )
        );
    }

    @Test
    void test_When_Revert_Redeem_Request_Null() {

        when(mockCouponSystemRepository.getRedeemCouponRedeemIds()).thenReturn(null);

        assertNull(
                rollbackCouponsRedeemedUseCase.invoke(
                        token,
                        transactionRequestDTO
                )
        );
    }

    @Test
    void test_When_Exception() {

        when(mockCouponSystemRepository.getRedeemCouponRedeemIds())
                .thenReturn(new ConfigRedeemCouponDTO(Collections.emptyList()));

        assertDoesNotThrow(
                () -> rollbackCouponsRedeemedUseCase.invoke(
                        token,
                        transactionRequestDTO
                )
        );
    }

    @Test
    void test_When_Exception_CancellationException() {

        when(mockCouponSystemRepository.getRedeemCouponRedeemIds())
                .thenThrow(new CancellationException());

        assertThrows(TransactionCreationException.class,
                () -> rollbackCouponsRedeemedUseCase.invoke(
                        token,
                        transactionRequestDTO
                )
        );
    }
}
