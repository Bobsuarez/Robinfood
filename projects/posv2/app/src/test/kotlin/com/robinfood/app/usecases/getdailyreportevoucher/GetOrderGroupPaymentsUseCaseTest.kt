package com.robinfood.app.usecases.getdailyreportevoucher

import com.robinfood.app.mocks.DailyReportVoucherRequestDTOMocks
import com.robinfood.app.mocks.OrderGroupPaymentDTOMocks
import com.robinfood.core.enums.Result
import com.robinfood.repository.dailyreportvoucher.IOrdersGroupPaymentsRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

@ExtendWith(MockitoExtension::class)
class GetOrderGroupPaymentsUseCaseTest {

    @Mock
    private lateinit var ordersGroupPaymentsRepository: IOrdersGroupPaymentsRepository

    @InjectMocks
    private lateinit var getOrderGroupPaymentsUseCase: GetOrderGroupPaymentsUseCase

    private val token = "token"

    private val orderGroupPaymentDTOMocks = OrderGroupPaymentDTOMocks().orderGroupPaymentDTOs

    private val dailyReportVoucherRequestDTOMocks = DailyReportVoucherRequestDTOMocks()
        .dailyReportVoucherRequestDTOMocks

    @Test
    fun test_Get_Order_Group_Payments_UseCase_invoke_when_service_returns_OK() {
        runBlocking {
            Mockito.`when`(
                ordersGroupPaymentsRepository.getOrderGroupPayments(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(Result.Success(orderGroupPaymentDTOMocks))

            val result = getOrderGroupPaymentsUseCase.invoke(
                dailyReportVoucherRequestDTOMocks,
                token
            )
            Assertions.assertEquals(orderGroupPaymentDTOMocks, result)
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_Get_Order_Group_Payments_UseCase_invoke_when_service_returns_Error() {
        runBlocking {
            assertThrows<ResponseStatusException> {
                Mockito.`when`(
                    ordersGroupPaymentsRepository.getOrderGroupPayments(
                        dailyReportVoucherRequestDTOMocks,
                        token
                    )
                ).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

                getOrderGroupPaymentsUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            }
        }
    }
}