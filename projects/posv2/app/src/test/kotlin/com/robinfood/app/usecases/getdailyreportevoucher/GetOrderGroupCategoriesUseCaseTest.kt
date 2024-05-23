package com.robinfood.app.usecases.getdailyreportevoucher

import com.robinfood.app.mocks.DailyReportVoucherRequestDTOMocks
import com.robinfood.app.mocks.OrderCategoryDTOsMocks
import com.robinfood.core.enums.Result
import com.robinfood.repository.dailyreportvoucher.IOrderGroupCategoriesRepository
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
class GetOrderGroupCategoriesUseCaseTest {

    @Mock
    private lateinit var orderGroupCategoriesRepository: IOrderGroupCategoriesRepository

    @InjectMocks
    private lateinit var getOrderGroupCategoriesUseCase: GetOrderGroupCategoriesUseCase

    private val token = "token"

    private val orderCategoryDTOsMocks = OrderCategoryDTOsMocks().orderCategoryEntityMocks

    private val dailyReportVoucherRequestDTOMocks = DailyReportVoucherRequestDTOMocks()
        .dailyReportVoucherRequestDTOMocks

    @Test
    fun test_Get_Order_Group_Categories_UseCase_invoke_when_service_returns_OK() {
        runBlocking {
            Mockito.`when`(
                orderGroupCategoriesRepository.getOrderGroupCategories(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(Result.Success(orderCategoryDTOsMocks))

            val result = getOrderGroupCategoriesUseCase.invoke(
                dailyReportVoucherRequestDTOMocks,
                token
            )
            Assertions.assertEquals(orderCategoryDTOsMocks, result)
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_Get_Order_Group_Categories_UseCase_invoke_when_service_returns_Error() {
        runBlocking {
            assertThrows<ResponseStatusException> {
                Mockito.`when`(
                    orderGroupCategoriesRepository.getOrderGroupCategories(
                        dailyReportVoucherRequestDTOMocks,
                        token
                    )
                ).thenReturn(Result.Error(Exception("Some error"), HttpStatus.BAD_REQUEST))

                getOrderGroupCategoriesUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            }
        }
    }
}