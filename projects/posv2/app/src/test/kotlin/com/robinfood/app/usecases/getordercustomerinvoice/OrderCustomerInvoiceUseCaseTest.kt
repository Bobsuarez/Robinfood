package com.robinfood.app.usecases.getordercustomerinvoice

import com.robinfood.app.mocks.customerinvoice.PrintFormatDTOMock
import com.robinfood.core.helpers.JasperReportManagerHelper
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.HashMap
import kotlinx.coroutines.runBlocking
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.web.server.ResponseStatusException

@ExtendWith(MockitoExtension::class)
class OrderCustomerInvoiceUseCaseTest {

    @Mock
    private lateinit var getOrderCustomerInvoiceUseCase: IGetOrderCustomerInvoiceUseCase

    @InjectMocks
    private lateinit var orderCustomerInvoiceUseCase: OrderCustomerInvoiceUseCase

    private val token = "token"

    private val printFormatDTOMock = PrintFormatDTOMock().printFormatDTOMock

    private val printFormatDTOIsExternalFalseMock = PrintFormatDTOMock().printFormatDTOIsExternalFalseMock

    private val orderId = 27L

    @Mock
    private lateinit var jasperReportManagerHelper: JasperReportManagerHelper

    val inputStream =
        ByteArrayInputStream("resources/reports/dailyReportVoucher/report_daily_voucher_category_detail.jrxml".toByteArray())

    @Test
    fun test_Order_Customer_InvoiceUse_Case_invoke_when_service_returns_OK() {
        runBlocking {
            Mockito.`when`(
                getOrderCustomerInvoiceUseCase.invoke(
                    orderId,
                    token
                )
            ).thenReturn(printFormatDTOMock)

            Mockito.`when`(
                jasperReportManagerHelper.getSubReportToInputStream(
                    ArgumentMatchers.anyString()
                )
            ).thenReturn(inputStream)

            Mockito.`when`(
                jasperReportManagerHelper.convertCollectionToJRBeanCollectionDataSource(
                    ArgumentMatchers.any()
                )
            ).thenReturn(JRBeanCollectionDataSource(listOf(printFormatDTOMock)))

            Mockito.`when`(
                (jasperReportManagerHelper.export(
                    ArgumentMatchers.anyString(), HashMap(ArgumentMatchers.anyMap()),
                    ArgumentMatchers.anyList()
                ))
            ).thenReturn(ByteArrayOutputStream(20))


            val result = orderCustomerInvoiceUseCase.invoke(
                orderId,
                token
            )
            Assertions.assertNotNull(result)
        }
    }

    @Test
    fun test_Order_Customer_InvoiceUse_Case_invoke_when_service_Is_Delivery_External_False_returns_OK() {
        runBlocking {
            Mockito.`when`(
                getOrderCustomerInvoiceUseCase.invoke(
                    orderId,
                    token
                )
            ).thenReturn(printFormatDTOIsExternalFalseMock)

            Mockito.`when`(
                jasperReportManagerHelper.getSubReportToInputStream(
                    ArgumentMatchers.anyString()
                )
            ).thenReturn(inputStream)

            Mockito.`when`(
                jasperReportManagerHelper.convertCollectionToJRBeanCollectionDataSource(
                    ArgumentMatchers.any()
                )
            ).thenReturn(JRBeanCollectionDataSource(listOf(printFormatDTOMock)))

            Mockito.`when`(
                (jasperReportManagerHelper.export(
                    ArgumentMatchers.anyString(), HashMap(ArgumentMatchers.anyMap()),
                    ArgumentMatchers.anyList()
                ))
            ).thenReturn(ByteArrayOutputStream(20))

            val result = orderCustomerInvoiceUseCase.invoke(
                orderId,
                token
            )
            Assertions.assertNotNull(result)
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_Order_Customer_InvoiceUse_Case_invoke_when_service_Is_Delivery_External_False_returns_Not_Found() {
        runBlocking {
            assertThrows<ResponseStatusException> {
                Mockito.`when`(
                    getOrderCustomerInvoiceUseCase.invoke(
                        orderId,
                        token
                    )
                ).thenReturn(null)

                orderCustomerInvoiceUseCase.invoke(
                    orderId,
                    token
                )
            }
        }
    }
}