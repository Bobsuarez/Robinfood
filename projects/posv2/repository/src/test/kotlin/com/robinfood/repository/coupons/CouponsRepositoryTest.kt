package com.robinfood.repository.coupons

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.coupons.CouponValidationResponseEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import com.robinfood.core.mappers.coupons.toCouponValidationResponseDTO
import com.robinfood.network.api.CouponsAPI
import com.robinfood.network.di.DispatcherProvider
import com.robinfood.repository.mocks.coupons.CouponValidationRequestEntityMocks
import java.math.BigDecimal
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineExceptionHandler
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Response

@ExtendWith(MockitoExtension::class)
@ExperimentalCoroutinesApi
class CouponsRepositoryTest {

    @Mock
    private lateinit var mockCouponsAPI: CouponsAPI

    @Mock
    private lateinit var dispatchers: DispatcherProvider

    @InjectMocks
    private lateinit var couponsRepository: CouponsRepository

    private val testDispatcher = TestCoroutineDispatcher()

    private val testCoroutineExceptionHandler = TestCoroutineExceptionHandler()

    private val couponValidationRequestEntityMocks = CouponValidationRequestEntityMocks()

    private val couponValidationRequestEntity = couponValidationRequestEntityMocks.couponValidationRequestEntity

    private val token = "token"

    private val couponValidationResponseEntity = CouponValidationResponseEntity(
            1L,
            BigDecimal.valueOf(2000.0),
            BigDecimal.valueOf(8900.0),
            1L
    )

    private val apiCouponValidationResponseEntity = APIResponseEntity(
            200,
            couponValidationResponseEntity,
            "CO",
            "Cupon validated successfully",
            "OK"
    )

    private val apiCouponValidationResponseErrorEntity = APIResponseEntity(
            500,
            couponValidationResponseEntity,
            "CO",
            "Cupon does not exist",
            "Error"
    )

    private val apiCouponValidationResponseNullEntity = APIResponseEntity<CouponValidationResponseEntity>(
            200,
            null,
            "CO",
            "Cupon validated successfully",
            "OK"
    )

    private val couponValidationResponseDTO = couponValidationResponseEntity.toCouponValidationResponseDTO()

    @Test
    fun test_ValidateCoupon_when_service_returns_OK() {
        runBlocking {
            `when`(dispatchers.io).thenReturn(testDispatcher)
            `when`(mockCouponsAPI.validateCoupon(
                    token,
                    couponValidationRequestEntity
            )).thenReturn(Response.success(apiCouponValidationResponseEntity))
            val result = couponsRepository.validateCoupon(
                    token,
                    couponValidationRequestEntity
            )
            assertEquals(Result.Success(couponValidationResponseDTO), result)
        }
    }

    @Test
    fun test_ValidateCoupon_when_service_returns_ERROR() {
        runBlocking {
            `when`(dispatchers.io).thenReturn(testDispatcher)
            `when`(mockCouponsAPI.validateCoupon(
                    token,
                    couponValidationRequestEntity
            )).thenReturn(Response.error(500, ResponseBody.create(
                    MediaType.parse("application/json"),
                    apiCouponValidationResponseErrorEntity.toJson()
            )))
            val result = couponsRepository.validateCoupon(
                    token,
                    couponValidationRequestEntity
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_ValidateCoupon_when_service_returns_NULL() {
        runBlocking {
            `when`(dispatchers.io).thenReturn(testDispatcher)
            `when`(mockCouponsAPI.validateCoupon(
                    token,
                    couponValidationRequestEntity
            )).thenReturn(Response.success(apiCouponValidationResponseNullEntity))
            val result = couponsRepository.validateCoupon(
                    token,
                    couponValidationRequestEntity
            )
            assertTrue(result is Result.Error)
        }
    }
}