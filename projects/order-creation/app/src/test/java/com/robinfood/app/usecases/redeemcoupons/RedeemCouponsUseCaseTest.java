package com.robinfood.app.usecases.redeemcoupons;

import com.robinfood.app.mocks.TransactionCreationResponseDTOMock;
import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.core.dtos.ConfigTransactionResponseDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.userentities.UserDetailEntity;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.exceptions.WriteInTransactionException;
import com.robinfood.repository.coupons.ICouponSystemRepository;
import com.robinfood.repository.transaction.ITransactionRepository;
import com.robinfood.repository.user.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RedeemCouponsUseCaseTest {

    @Mock
    private ICouponSystemRepository mockCouponSystemRepository;

    @Mock
    private ITransactionRepository mockTransactionRepository;

    @Mock
    private IUserRepository mockUserRepository;

    @InjectMocks
    private RedeemCouponsUseCase redeemCouponsUseCase;

    private final TransactionRequestDTO paidTransactionRequestWithCoupons = new TransactionRequestDTOMock().paidTransactionRequestDTO;

    final String token = "token";

    @Test
    void test_When_Transaction_Has_Payments_Success() {
        when(mockCouponSystemRepository.performCouponRequest(anyString(), any()))
                .thenReturn(new PerformCouponResponseDTO(
                        "200",
                        1L,
                        1L,
                        BigDecimal.valueOf(3000.0),
                        "1"
                ));

        when(mockTransactionRepository.getTransactionResponseDTO())
                .thenReturn(new ConfigTransactionResponseDTO(
                                TransactionCreationResponseDTOMock.build()
                        )
                );

        when(mockUserRepository.getUserDetail(token, 1L))
                .thenReturn(CompletableFuture.completedFuture(
                        new UserDetailEntity(
                                1L,
                                "User",
                                "Test",
                                "57",
                                "9999999999",
                                "test@test.com",
                                false,
                                Collections.singletonList(
                                        UserDetailEntity.Counter.builder()
                                                .key("test")
                                                .reference(1L)
                                                .value(1L)
                                                .build()
                                )
                        ))
                );

        when(mockUserRepository.getUserDetail(token, 1L))
                .thenReturn(CompletableFuture.completedFuture(
                        new UserDetailEntity(
                                1L,
                                "User",
                                "Test",
                                "57",
                                "9999999999",
                                "test@test.com",
                                false,
                                Collections.singletonList(
                                        UserDetailEntity.Counter.builder()
                                                .key("test")
                                                .reference(1L)
                                                .value(1L)
                                                .build()
                                )
                        ))
                );

        final TransactionCreationResult result = redeemCouponsUseCase.invoke(
                token,
                paidTransactionRequestWithCoupons
        ).join();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
    }

    @Test
    void test_When_Transaction_Created_Orders_Are_Not_The_Same_As_Orders_In_Request() {
        when(mockTransactionRepository.getTransactionResponseDTO())
                .thenReturn(new ConfigTransactionResponseDTO(
                        TransactionCreationResponseDTOMock.buildTransactionIdZero()
                ));

        assertThrows(
                WriteInTransactionException.class,
                () -> redeemCouponsUseCase.invoke(token, paidTransactionRequestWithCoupons).join()
        );
    }

    @Test
    void test_When_Transaction_not_paid() {

        paidTransactionRequestWithCoupons.setPaid(Boolean.FALSE);

        final TransactionCreationResult result = redeemCouponsUseCase.invoke(
                token,
                paidTransactionRequestWithCoupons
        ).join();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
    }

    @Test
    void test_When_Transaction_Does_Not_Have_A_Coupon() {

        when(mockCouponSystemRepository.performCouponRequest(anyString(), any())).thenReturn(null);

        when(mockTransactionRepository.getTransactionResponseDTO()).thenReturn(new ConfigTransactionResponseDTO(
                        TransactionCreationResponseDTOMock.build()
                )
        );

        when(mockUserRepository.getUserDetail(token, 1L))
                .thenReturn(CompletableFuture.completedFuture(
                        new UserDetailEntity(
                                1L,
                                "User",
                                "Test",
                                "57",
                                "9999999999",
                                "test@test.com",
                                false,
                                Collections.singletonList(
                                        UserDetailEntity.Counter.builder()
                                                .key("test")
                                                .reference(1L)
                                                .value(1L)
                                                .build()
                                )
                        ))
                );

        final TransactionCreationResult result = redeemCouponsUseCase.invoke(
                token,
                paidTransactionRequestWithCoupons
        ).join();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);

    }

    @Test
    void test_When_There_Is_A_Error_Then_Throw_Exception() {

        when(mockTransactionRepository.getTransactionResponseDTO())
                .thenReturn(new ConfigTransactionResponseDTO(
                                TransactionCreationResponseDTOMock.build()
                        )
                );

        when(mockCouponSystemRepository.performCouponRequest(anyString(), any())).thenThrow(CancellationException.class);

        when(mockUserRepository.getUserDetail(token, 1L))
                .thenReturn(CompletableFuture.completedFuture(
                        new UserDetailEntity(
                                1L,
                                "User",
                                "Test",
                                "57",
                                "9999999999",
                                "test@test.com",
                                false,
                                Collections.singletonList(
                                        UserDetailEntity.Counter.builder()
                                                .key("test")
                                                .reference(1L)
                                                .value(1L)
                                                .build()
                                )
                        ))
                );

        assertThrows(WriteInTransactionException.class, () -> redeemCouponsUseCase
                .invoke(token, paidTransactionRequestWithCoupons));
    }

    @Test
    void test_When_Transaction_Is_Not_Paid() {

        // Arrange
        TransactionRequestDTO request = paidTransactionRequestWithCoupons;
        request.setPaid(Boolean.FALSE);

        // Act
        final TransactionCreationResult result = redeemCouponsUseCase.invoke(
                token,
                request
        ).join();

        // Assert
        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
    }

    @Test
    void test_Params_Is_Null() {
        assertThrows(
                NullPointerException.class,
                () -> redeemCouponsUseCase.invoke(null, null)
        );
    }

}
