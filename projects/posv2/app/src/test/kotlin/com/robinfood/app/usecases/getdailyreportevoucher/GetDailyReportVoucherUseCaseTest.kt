package com.robinfood.app.usecases.getdailyreportevoucher

import com.robinfood.app.mocks.DailyReportVoucherRequestDTOMocks
import com.robinfood.app.mocks.OrderCategoryDTOsMocks
import com.robinfood.app.mocks.OrderGroupPaymentDTOMocks
import com.robinfood.app.mocks.PosResolutionSequenceDTOMocks
import com.robinfood.app.mocks.StoreConfigurationsDTOMock
import com.robinfood.app.mocks.dailyreportevoucher.StorePosDTOMock
import com.robinfood.app.usecases.getposrelatedtoastore.IGetConfigurationPosByStoreUseCase
import com.robinfood.app.usecases.getstoreconfigurations.IGetStoreConfigurationUseCase
import com.robinfood.core.dtos.witnesstape.StoreOrderRequestDTO
import com.robinfood.core.helpers.JasperReportManagerHelper
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import java.util.Collections
import kotlinx.coroutines.runBlocking
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyMap
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.web.server.ResponseStatusException

@ExtendWith(MockitoExtension::class)
class GetDailyReportVoucherUseCaseTest {

    @Mock
    private lateinit var getPosResolutionUseCase: IGetPosResolutionUseCase

    @Mock
    private lateinit var getStoreConfigurationUseCase: IGetStoreConfigurationUseCase

    @Mock
    private lateinit var getOrderGroupCategoriesUseCase: IGetOrderGroupCategoriesUseCase

    @Mock
    private lateinit var getOrderGroupPaymentsUseCase: IGetOrderGroupPaymentsUseCase

    @Mock
    private lateinit var getConfigurationPosByStoreUseCase: IGetConfigurationPosByStoreUseCase

    @Mock
    private lateinit var jasperReportManagerHelper: JasperReportManagerHelper

    @InjectMocks
    private lateinit var getDailyReportVoucherUseCase: GetDailyReportVoucherUseCase

    private val token = "token"

    private val posResolutionSequenceDTOMocks = PosResolutionSequenceDTOMocks().posResolutionSequenceDTO
    private val posResolutionSequenceDTONullMocks = PosResolutionSequenceDTOMocks().posResolutionSequenceDTONull
    private val posResolutionSequenceIdNullDTOMocks = PosResolutionSequenceDTOMocks().posResolutionSequenceLengthDTO
    private val storeConfigurationsDTOMock = StoreConfigurationsDTOMock().storeConfigurationsDTOMock
    private val storeConfigurationsBrazilDTOMock = StoreConfigurationsDTOMock().storeConfigurationsDTOBrazilMock
    private val orderCategoryDTOsMocks = OrderCategoryDTOsMocks().orderCategoryEntityMocks
    private val orderCategoryDTOsNullIdMocks = OrderCategoryDTOsMocks().orderCategoryEntityNullMocks
    private val orderGroupPaymentDTOMocks = OrderGroupPaymentDTOMocks().orderGroupPaymentDTOs
    private val orderGroupPaymentDTOIdNullMocks = OrderGroupPaymentDTOMocks().orderGroupPaymentIdNullDTOs
    private val storePosDTOMock = StorePosDTOMock().storePosDTOs

    private val dailyReportVoucherRequestDTOMocks = DailyReportVoucherRequestDTOMocks()
        .dailyReportVoucherRequestDTOMocks

    val storeOrderRequestDTO = StoreOrderRequestDTO(
        LocalDate.parse("2023-02-01"),
        LocalDate.parse("2023-02-01"),
        27L,
        "America/Bogota"
    )

    private val storeId = 27L

    val inputStream =
        ByteArrayInputStream("resources/reports/dailyReportVoucher/report_daily_voucher_category_detail.jrxml".toByteArray())


    @Test
    @Throws(Exception::class)
    fun test_Get_Daily_Report_Voucher_UseCase_invoke_when_service_category_Id_Null_returns_Not_Found() {
        runBlocking {
            assertThrows<ResponseStatusException> {
                Mockito.`when`(
                    getConfigurationPosByStoreUseCase.invoke(
                        storeId,
                        token
                    )
                ).thenReturn(Collections.emptyList())

                Mockito.`when`(
                    getStoreConfigurationUseCase.invoke(
                        storeId,
                        token
                    )
                ).thenReturn(storeConfigurationsDTOMock)

                getDailyReportVoucherUseCase.invoke(
                    storeOrderRequestDTO,
                    token
                )
            }
        }
    }

    @Test
    fun test_Get_Daily_Report_Voucher_UseCase_invoke_when_service_category_Id_Null_returns_OK() {

        runBlocking {

            Mockito.`when`(
                getConfigurationPosByStoreUseCase.invoke(
                    storeId,
                    token
                )
            ).thenReturn(storePosDTOMock)

            Mockito.`when`(
                getPosResolutionUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(posResolutionSequenceDTOMocks)

            Mockito.`when`(
                getStoreConfigurationUseCase.invoke(
                    storeId,
                    token
                )
            ).thenReturn(storeConfigurationsDTOMock)


            Mockito.`when`(
                getOrderGroupCategoriesUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(orderCategoryDTOsNullIdMocks)

            Mockito.`when`(
                getOrderGroupPaymentsUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(orderGroupPaymentDTOIdNullMocks)

            Mockito.`when`(
                jasperReportManagerHelper.getSubReportToInputStream(
                    anyString()
                )
            ).thenReturn(inputStream)

            Mockito.`when`(
                jasperReportManagerHelper.convertCollectionToJRBeanCollectionDataSource(
                    any()
                )
            ).thenReturn(JRBeanCollectionDataSource(orderCategoryDTOsMocks))

            Mockito.`when`(
                (jasperReportManagerHelper.export(anyString(), HashMap(anyMap()), anyList()))
            ).thenReturn(ByteArrayOutputStream(20))

            val result = getDailyReportVoucherUseCase.invoke(
                storeOrderRequestDTO,
                token
            )
            Assertions.assertNotNull(result)
        }
    }

    @Test
    fun test_Get_Daily_Report_Voucher_UseCase_invoke_when_service_returns_OK() {
        runBlocking {
            Mockito.`when`(
                getConfigurationPosByStoreUseCase.invoke(
                    storeId,
                    token
                )
            ).thenReturn(storePosDTOMock)

            Mockito.`when`(
                getPosResolutionUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(posResolutionSequenceDTOMocks)

            Mockito.`when`(
                getStoreConfigurationUseCase.invoke(
                    storeId,
                    token
                )
            ).thenReturn(storeConfigurationsDTOMock)


            Mockito.`when`(
                getOrderGroupCategoriesUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(orderCategoryDTOsMocks)

            Mockito.`when`(
                getOrderGroupPaymentsUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(orderGroupPaymentDTOMocks)

            Mockito.`when`(
                jasperReportManagerHelper.getSubReportToInputStream(
                    anyString()
                )
            ).thenReturn(inputStream)

            Mockito.`when`(
                jasperReportManagerHelper.convertCollectionToJRBeanCollectionDataSource(
                    any()
                )
            ).thenReturn(JRBeanCollectionDataSource(orderCategoryDTOsMocks))

            Mockito.`when`(
                (jasperReportManagerHelper.export(anyString(), HashMap(anyMap()), anyList()))
            ).thenReturn(ByteArrayOutputStream(20))

            val result = getDailyReportVoucherUseCase.invoke(
                storeOrderRequestDTO,
                token
            )
            Assertions.assertNotNull(result)
        }
    }

    @Test
    fun test_Get_Daily_Report_Voucher_UseCase_invoke_when_service_returns_Brasil_OK() {
        runBlocking {
            Mockito.`when`(
                getConfigurationPosByStoreUseCase.invoke(
                    storeId,
                    token
                )
            ).thenReturn(storePosDTOMock)

            Mockito.`when`(
                getPosResolutionUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(posResolutionSequenceDTOMocks)

            Mockito.`when`(
                getStoreConfigurationUseCase.invoke(
                    storeId,
                    token
                )
            ).thenReturn(storeConfigurationsBrazilDTOMock)


            Mockito.`when`(
                getOrderGroupCategoriesUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(orderCategoryDTOsMocks)

            Mockito.`when`(
                getOrderGroupPaymentsUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(orderGroupPaymentDTOMocks)

            Mockito.`when`(
                jasperReportManagerHelper.getSubReportToInputStream(
                    anyString()
                )
            ).thenReturn(inputStream)

            Mockito.`when`(
                jasperReportManagerHelper.convertCollectionToJRBeanCollectionDataSource(
                    any()
                )
            ).thenReturn(JRBeanCollectionDataSource(orderCategoryDTOsMocks))

            Mockito.`when`(
                (jasperReportManagerHelper.export(anyString(), HashMap(anyMap()), anyList()))
            ).thenReturn(ByteArrayOutputStream(20))

            val result = getDailyReportVoucherUseCase.invoke(
                storeOrderRequestDTO,
                token
            )
            Assertions.assertNotNull(result)
        }
    }

    @Test
    fun test_Get_Daily_Report_Voucher_UseCase_invoke_when_pos_with_box_service_returns_OK() {
        runBlocking {
            Mockito.`when`(
                getConfigurationPosByStoreUseCase.invoke(
                    storeId,
                    token
                )
            ).thenReturn(storePosDTOMock)

            Mockito.`when`(
                getPosResolutionUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(posResolutionSequenceIdNullDTOMocks)

            Mockito.`when`(
                getStoreConfigurationUseCase.invoke(
                    storeId,
                    token
                )
            ).thenReturn(storeConfigurationsDTOMock)

            Mockito.`when`(
                getOrderGroupCategoriesUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(orderCategoryDTOsMocks)

            Mockito.`when`(
                getOrderGroupPaymentsUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(orderGroupPaymentDTOMocks)

            Mockito.`when`(
                jasperReportManagerHelper.getSubReportToInputStream(
                    anyString()
                )
            ).thenReturn(inputStream)

            Mockito.`when`(
                jasperReportManagerHelper.convertCollectionToJRBeanCollectionDataSource(
                    any()
                )
            ).thenReturn(JRBeanCollectionDataSource(orderCategoryDTOsMocks))

            Mockito.`when`(
                (jasperReportManagerHelper.export(anyString(), HashMap(anyMap()), anyList()))
            ).thenReturn(ByteArrayOutputStream(20))

            val result = getDailyReportVoucherUseCase.invoke(
                storeOrderRequestDTO,
                token
            )
            Assertions.assertNotNull(result)
        }
    }

    @Test
    fun test_Get_Daily_Report_Voucher_UseCase_invoke_when_service_category_Id_Null_returns_Error() {
        runBlocking {
            Mockito.`when`(
                getConfigurationPosByStoreUseCase.invoke(
                    storeId,
                    token
                )
            ).thenReturn(storePosDTOMock)

            Mockito.`when`(
                getPosResolutionUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(null)

            Mockito.`when`(
                getStoreConfigurationUseCase.invoke(
                    storeId,
                    token
                )
            ).thenReturn(storeConfigurationsDTOMock)

            Mockito.`when`(
                (jasperReportManagerHelper.export(anyString(), HashMap(anyMap()), anyList()))
            ).thenReturn(ByteArrayOutputStream(20))

            val result = getDailyReportVoucherUseCase.invoke(
                storeOrderRequestDTO,
                token
            )
            Assertions.assertNotNull(result)
        }
    }

    @Test
    fun test_Get_Daily_Report_Voucher_UseCase_invoke_when_service_name_pos_null_returns_OK() {
        runBlocking {
            Mockito.`when`(
                getConfigurationPosByStoreUseCase.invoke(
                    storeId,
                    token
                )
            ).thenReturn(storePosDTOMock)

            Mockito.`when`(
                getPosResolutionUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(posResolutionSequenceDTONullMocks)

            Mockito.`when`(
                getStoreConfigurationUseCase.invoke(
                    storeId,
                    token
                )
            ).thenReturn(storeConfigurationsDTOMock)


            Mockito.`when`(
                getOrderGroupCategoriesUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(orderCategoryDTOsMocks)

            Mockito.`when`(
                getOrderGroupPaymentsUseCase.invoke(
                    dailyReportVoucherRequestDTOMocks,
                    token
                )
            ).thenReturn(orderGroupPaymentDTOMocks)

            Mockito.`when`(
                jasperReportManagerHelper.getSubReportToInputStream(
                    anyString()
                )
            ).thenReturn(inputStream)

            Mockito.`when`(
                jasperReportManagerHelper.convertCollectionToJRBeanCollectionDataSource(
                    any()
                )
            ).thenReturn(JRBeanCollectionDataSource(orderCategoryDTOsMocks))

            Mockito.`when`(
                (jasperReportManagerHelper.export(anyString(), HashMap(anyMap()), anyList()))
            ).thenReturn(ByteArrayOutputStream(20))

            val result = getDailyReportVoucherUseCase.invoke(
                storeOrderRequestDTO,
                token
            )
            Assertions.assertNotNull(result)
        }
    }

}