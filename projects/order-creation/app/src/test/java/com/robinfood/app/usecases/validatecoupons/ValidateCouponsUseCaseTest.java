package com.robinfood.app.usecases.validatecoupons;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.distributediscounts.IDistributeDiscountsUseCase;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.userentities.UserDetailEntity;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.mappers.PerformCouponMappers;
import com.robinfood.repository.coupons.ICouponSystemRepository;
import com.robinfood.repository.user.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.GlobalConstants.NUMBER_DECIMAL_PLACES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateCouponsUseCaseTest {

    @Mock
    private ICouponSystemRepository mockCouponSystemRepository;

    @Mock
    private IDistributeDiscountsUseCase distributeDiscountsUseCase;

    @Mock
    private IUserRepository mockUserRepository;

    @Mock
    private PerformCouponMappers mockPerformCouponMappers;

    @InjectMocks
    private ValidateCouponUseCase validateCouponsUseCase;

    private final TransactionRequestDTO transactionRequestWithCoupons = new TransactionRequestDTOMock().paidTransactionRequestDTO;
    private final TransactionRequestDTO paidTransactionRequestWithCouponsDTO = new TransactionRequestDTOMock().paidTransactionRequestWithCouponsDTO;

    private final String token = "token";

    @Test
    void test_Validate_Coupons_Happy_Path() {
        when(mockCouponSystemRepository.performCouponRequest(anyString(), any()))
                .thenReturn(
                        new PerformCouponResponseDTO(
                                "200",
                                1L,
                                1L,
                                BigDecimal.valueOf(3000.0),
                                "1"
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

        final TransactionCreationResult result = validateCouponsUseCase
                .invoke(token, transactionRequestWithCoupons)
                .join();
        assertEquals(TransactionCreationResult.StepValidationSuccess.INSTANCE, result);
    }

    @Test
    void test_Validate_Coupons_With_Transaction_Not_Paid() {

        // Arrange
        TransactionRequestDTO request = transactionRequestWithCoupons;
        request.setPaid(Boolean.FALSE);

        when(mockCouponSystemRepository.performCouponRequest(anyString(), any()))
                .thenReturn(
                        new PerformCouponResponseDTO(
                                "200",
                                1L,
                                1L,
                                BigDecimal.valueOf(3000.0),
                                "1"
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

        final TransactionCreationResult result = validateCouponsUseCase
                .invoke(token, request)
                .join();
        assertEquals(TransactionCreationResult.StepValidationSuccess.INSTANCE, result);
    }

    @Test
    void test_Validate_Coupons_When_Coupon_Is_Greater_Than_Order() {
        when(mockCouponSystemRepository.performCouponRequest(anyString(), any()))
                .thenReturn(
                        new PerformCouponResponseDTO(
                                "200",
                                1L,
                                1L,
                                BigDecimal.valueOf(80000.0),
                                "1"
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

        final TransactionCreationResult result = validateCouponsUseCase
                .invoke(token, transactionRequestWithCoupons)
                .join();

        assertEquals(TransactionCreationResult.StepValidationSuccess.INSTANCE, result);

        assertEquals(
                BigDecimal.valueOf(7900.00).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY),
                transactionRequestWithCoupons
                        .getOrders()
                        .get(0)
                        .getTotal()
        );
    }

    @Test
    void test_Validate_Coupons_When_Request_Coupons_Is_Empty() {

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

        final TransactionCreationResult result = validateCouponsUseCase
                .invoke(token, paidTransactionRequestWithCouponsDTO)
                .join();

        assertEquals(TransactionCreationResult.StepValidationSuccess.INSTANCE, result);

        assertEquals(
                new BigDecimal("7900.0000"),
                paidTransactionRequestWithCouponsDTO
                        .getOrders()
                        .get(0)
                        .getTotal()
        );
    }

    @Test
    void test_Params_Is_Null() {
        assertThrows(
                NullPointerException.class,
                () -> validateCouponsUseCase.invoke(null, null)
        );
    }
}
