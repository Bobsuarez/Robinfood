package com.robinfood.repository.dailyreportvoucher

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.posresolutionsequence.PosResolutionSequenceEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.extensions.toJson
import com.robinfood.core.mappers.posresolution.toPosResolutionEntityToPosResolutionDTO
import com.robinfood.network.api.OrderCreationQueriesAPI
import com.robinfood.network.di.DispatcherProvider
import com.robinfood.repository.mocks.dailyreportvoucher.DailyReportVoucherRequestDTOMocks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Response

@ExtendWith(MockitoExtension::class)
@ExperimentalCoroutinesApi
class PosResolutionSequenceRepositoryTest {

    @Mock
    private lateinit var orderCreationQueriesAPI: OrderCreationQueriesAPI

    @Mock
    private lateinit var dispatchers: DispatcherProvider

    @InjectMocks
    private lateinit var posResolutionSequenceRepository: PosResolutionSequenceRepository

    private val testDispatcher = TestCoroutineDispatcher()

    private val token = "token"

    private val posResolutionSequenceEntity = PosResolutionSequenceEntity(
        cancelledInvoices = 2,
        current = 1,
        effectiveInvoices = 20,
        endDate = "2023-02-13",
        endNumber = 3000,
        id = 1,
        initialDate = "2023-02-13",
        name = "Pos",
        posId = 1,
        prefix = "RMY",
        startNumber = 1000,
        statusId = 1,
        storeId = 27,
        typeDocument = "fiscal"
    )

    private val posResolutionSequenceEntityFail = PosResolutionSequenceEntity(
        cancelledInvoices = 2,
        current = 1,
        effectiveInvoices = 20,
        endDate = "2023-02-13",
        endNumber = 3000,
        id = null,
        initialDate = "2023-02-13",
        name = "Pos",
        posId = 1,
        prefix = "RMY",
        startNumber = 1000,
        statusId = 1,
        storeId = 27,
        typeDocument = "fiscal"
    )

    private val apiResponsePosResolutionSequenceEntity = APIResponseEntity(
        200,
        posResolutionSequenceEntity,
        "CO",
        "Pos resolution get successfully",
        "OK"
    )

    private val apiResponsePosResolutionSequenceFailEntity = APIResponseEntity(
        200,
        posResolutionSequenceEntityFail,
        "CO",
        "Pos resolution get successfully",
        "OK"
    )

    private val apiResponsePosResolutionSequenceErrorEntity = APIResponseEntity(
        500,
        posResolutionSequenceEntity,
        "CO",
        "Pos resolution does not exist",
        "Error"
    )

    private val apiResponsePosResolutionSequenceNullEntity = APIResponseEntity<PosResolutionSequenceEntity>(
        200,
        null,
        "CO",
        "Pos resolution get successfully",
        "OK"
    )

    private val dailyReportVoucherRequestDTOMocks = DailyReportVoucherRequestDTOMocks()
        .dailyReportVoucherRequestDTOMocks

    private val posResolutionReportDTOSuccess = posResolutionSequenceEntity
        .toPosResolutionEntityToPosResolutionDTO()

    @Test
    fun test_getPosResolutionSequence_when_repository_returns_OK() {
        runBlocking {
            `when`(dispatchers.io).thenReturn(testDispatcher)
            Mockito.`when`(
                orderCreationQueriesAPI.getPosResolutionSequence(
                    token,
                    dailyReportVoucherRequestDTOMocks.timeZone,
                    dailyReportVoucherRequestDTOMocks.posId,
                    dailyReportVoucherRequestDTOMocks.localDateStart,
                    dailyReportVoucherRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponsePosResolutionSequenceEntity))
            val result = posResolutionSequenceRepository.getPosResolutionSequence(
                dailyReportVoucherRequestDTOMocks,
                token
            )

            assertEquals(Result.Success(posResolutionReportDTOSuccess!!), result)
        }
    }

    @Test
    fun test_getPosResolutionSequence_when_repository_fails_converting() {
        runBlocking {
            `when`(dispatchers.io).thenReturn(testDispatcher)
            `when`(
                orderCreationQueriesAPI.getPosResolutionSequence(
                    token,
                    dailyReportVoucherRequestDTOMocks.timeZone,
                    dailyReportVoucherRequestDTOMocks.posId,
                    dailyReportVoucherRequestDTOMocks.localDateStart,
                    dailyReportVoucherRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponsePosResolutionSequenceFailEntity))

            val result = posResolutionSequenceRepository.getPosResolutionSequence(
                dailyReportVoucherRequestDTOMocks,
                token
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_getUserConfiguration_when_repository_returns_Error() {
        runBlocking {
            `when`(dispatchers.io).thenReturn(testDispatcher)
            `when`(
                orderCreationQueriesAPI.getPosResolutionSequence(
                    token,
                    dailyReportVoucherRequestDTOMocks.timeZone,
                    dailyReportVoucherRequestDTOMocks.posId,
                    dailyReportVoucherRequestDTOMocks.localDateStart,
                    dailyReportVoucherRequestDTOMocks.localDateEnd
                )
            ).thenReturn(
                Response.error(
                    500, ResponseBody.create(
                        MediaType.parse("application/json"),
                        apiResponsePosResolutionSequenceErrorEntity.toJson()
                    )
                )
            )
            val result = posResolutionSequenceRepository.getPosResolutionSequence(
                dailyReportVoucherRequestDTOMocks,
                token
            )
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun test_getUserConfiguration_when_repository_returns_Null() {
        runBlocking {
            `when`(dispatchers.io).thenReturn(testDispatcher)
            `when`(
                orderCreationQueriesAPI.getPosResolutionSequence(
                    token,
                    dailyReportVoucherRequestDTOMocks.timeZone,
                    dailyReportVoucherRequestDTOMocks.posId,
                    dailyReportVoucherRequestDTOMocks.localDateStart,
                    dailyReportVoucherRequestDTOMocks.localDateEnd
                )
            ).thenReturn(Response.success(apiResponsePosResolutionSequenceNullEntity))

            val result = posResolutionSequenceRepository.getPosResolutionSequence(
                dailyReportVoucherRequestDTOMocks,
                token
            )
            assertTrue(result is Result.Error)
        }
    }
}