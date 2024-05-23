package com.robinfood.app.usecases.witnesstape

import com.robinfood.app.mocks.StoreConfigurationsDTOMock
import com.robinfood.app.mocks.witnesstape.StoreOrderRequestDTOMocks
import com.robinfood.app.mocks.witnesstape.StoreOrdersDTOMocks
import com.robinfood.app.mocks.witnesstape.StoreResolutionSequenceDTOMocks
import com.robinfood.app.usecases.getstoreconfigurations.IGetStoreConfigurationUseCase
import com.robinfood.core.dtos.posresolutionsequence.PosResolutionSequenceDTO
import com.robinfood.core.dtos.witnesstape.StoreOrdersDTO
import com.robinfood.core.helpers.JasperReportManagerHelper
import com.robinfood.core.mappers.witnesstape.toWitnessTapePosDTOs
import com.robinfood.core.mappers.witnesstape.toWitnessTapeTableSalesDTO
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.HashMap
import kotlinx.coroutines.runBlocking
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class GetWitnessTapeReportUseCaseTest {

    @Mock
    private lateinit var getStoreConfigurationUseCase: IGetStoreConfigurationUseCase

    @Mock
    private lateinit var getStoreResolutionSequenceUseCase: IGetStoreResolutionSequenceUseCase

    @Mock
    private lateinit var getOrdersByStoreUseCase: IGetOrdersByStoreUseCase

    @InjectMocks
    private lateinit var getWitnessTapeReportUseCase: GetWitnessTapeReportUseCase

    @Mock
    private lateinit var jasperReportManagerHelper: JasperReportManagerHelper

    private val token = "token"

    private val ordersStoreDTOsMocks = StoreOrdersDTOMocks().storeOrdersDTOsMocks
    private val storeResolutionSequenceDTOs = StoreResolutionSequenceDTOMocks().storeResolutionSequenceEntities
    private val storeConfigurationsDTOMock = StoreConfigurationsDTOMock().storeConfigurationsDTOMock

    private val storeOrderRequestDTOMocks = StoreOrderRequestDTOMocks().storeOrderRequestDTOMocks

    val witnessTapeTableSalesDTOs = ordersStoreDTOsMocks.mapNotNull { storeOrdersDTO ->
        storeOrdersDTO.toWitnessTapeTableSalesDTO(1)
    }

    val witnessTapePosDTOs = storeResolutionSequenceDTOs.mapNotNull { posResolutionSequenceDTO ->
        posResolutionSequenceDTO.toWitnessTapePosDTOs()
    }

    val storeOrdersDTOEsEmpty: MutableList<StoreOrdersDTO> = ArrayList()
    val posResolutionSequenceDTOsEmpty: MutableList<PosResolutionSequenceDTO> = ArrayList()

    val storeId = 1L

    val inputStream =
        ByteArrayInputStream("resources/reports/dailyReportVoucher/report_daily_voucher_category_detail.jrxml".toByteArray())

    @Test
    fun test_Get_Witness_Tape_Report_UseCase_invoke_when_service_returns_OK() {
        runBlocking {
            Mockito.`when`(
                getStoreConfigurationUseCase.invoke(
                    storeId,
                    token
                )
            ).thenReturn(storeConfigurationsDTOMock)

            Mockito.`when`(
                getStoreResolutionSequenceUseCase.invoke(
                    storeOrderRequestDTOMocks,
                    token
                )
            ).thenReturn(storeResolutionSequenceDTOs)

            Mockito.`when`(
                getOrdersByStoreUseCase.invoke(
                    storeOrderRequestDTOMocks,
                    token
                )
            ).thenReturn(ordersStoreDTOsMocks)

            Mockito.`when`(
                jasperReportManagerHelper.getSubReportToInputStream(
                    ArgumentMatchers.anyString()
                )
            ).thenReturn(inputStream)

            Mockito.`when`(
                jasperReportManagerHelper.convertCollectionToJRBeanCollectionDataSource(
                    ArgumentMatchers.any()
                )
            ).thenReturn(JRBeanCollectionDataSource(listOf(witnessTapePosDTOs)))

            Mockito.`when`(
                (jasperReportManagerHelper.export(
                    ArgumentMatchers.anyString(), HashMap(ArgumentMatchers.anyMap()),
                    ArgumentMatchers.anyList()
                ))
            ).thenReturn(ByteArrayOutputStream(20))

            val result = getWitnessTapeReportUseCase.invoke(
                storeOrderRequestDTOMocks,
                token
            )
            Assertions.assertNotNull(result)
        }
    }

    @Test
    fun test_Get_Witness_Tape_Report_UseCase_invoke_when_service_returns_Empty_OK() {
        runBlocking {
            Mockito.`when`(
                getStoreConfigurationUseCase.invoke(
                    storeId,
                    token
                )
            ).thenReturn(storeConfigurationsDTOMock)

            Mockito.`when`(
                getStoreConfigurationUseCase.invoke(
                    storeId,
                    token
                )
            ).thenReturn(storeConfigurationsDTOMock)

            Mockito.`when`(
                getStoreResolutionSequenceUseCase.invoke(
                    storeOrderRequestDTOMocks,
                    token
                )
            ).thenReturn(posResolutionSequenceDTOsEmpty)

            Mockito.`when`(
                getOrdersByStoreUseCase.invoke(
                    storeOrderRequestDTOMocks,
                    token
                )
            ).thenReturn(storeOrdersDTOEsEmpty)

            Mockito.`when`(
                jasperReportManagerHelper.getSubReportToInputStream(
                    ArgumentMatchers.anyString()
                )
            ).thenReturn(inputStream)

            Mockito.`when`(
                jasperReportManagerHelper.convertCollectionToJRBeanCollectionDataSource(
                    ArgumentMatchers.any()
                )
            ).thenReturn(JRBeanCollectionDataSource(listOf(witnessTapePosDTOs)))

            Mockito.`when`(
                (jasperReportManagerHelper.export(
                    ArgumentMatchers.anyString(), HashMap(ArgumentMatchers.anyMap()),
                    ArgumentMatchers.anyList()
                ))
            ).thenReturn(ByteArrayOutputStream(20))

            val result = getWitnessTapeReportUseCase.invoke(
                storeOrderRequestDTOMocks,
                token
            )
            Assertions.assertNotNull(result)
        }
    }


}