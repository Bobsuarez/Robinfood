package com.robinfood.app.controllers.reports

import com.robinfood.app.POSApplication
import com.robinfood.app.usecases.getdailyreportevoucher.IGetDailyReportVoucherUseCase
import com.robinfood.app.usecases.getordercustomerinvoice.OrderCustomerInvoiceUseCase
import com.robinfood.app.usecases.witnesstape.IGetWitnessTapeReportUseCase
import com.robinfood.core.constants.APIConstants
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.report.ReportResponseDTO
import com.robinfood.core.dtos.witnesstape.StoreOrderRequestDTO
import com.robinfood.core.extensions.toJson
import java.time.LocalDate
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
@SpringBootTest(classes = [POSApplication::class])
@TestPropertySource(
    properties = [
        "jwt-token-prefix=Bearer ",
        "jwt-token-secret=secretForTesting",
        "jwt-token-aud=internal",
        "jwt-token-mod=posv2"
    ]
)
class GetReportsPdfControllerTest {

    companion object {
        private const val BEARER_AUTH = "Bearer "
        private const val TOKEN =
            "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxODE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlckJjIiwib3JkZXJDcmVhdGlvblF1ZXJpZXMiLCJwb3N2MiJdLCJwZXIiOlsicG9zX2NyZWF0ZV9vcmRlciIsIm9yZGVyc19yZWplY3Rfb3JkZXIiXSwidXNlciI6eyJ1c2VyX2lkIjoxMjM0NTY3LCJlbWFpbCI6ImpvaG5kb2VAbXljb21wYW55LmNvbSIsImNvdW50cnlfaWQiOjEsImNvbXBhbnlfaWQiOjEsImZpcnN0X25hbWUiOiJKaG9uIiwibGFzdF9uYW1lIjoiRG9lIiwicGhvbmUiOiI1NTUtNjM4MzAyMiIsIm1ldGFkYXRhIjp7InN0b3JlX2lkIjo1fX19.vUjmr0KoXohM-3bjMX8FmaSsPZDR5X_9soqhpFiwyBLpDW6WbfkRQzJf00lTzYEjpUbsXxk-khhkOcpQQhIwrQ"
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var getWitnessTapeReportUseCase: IGetWitnessTapeReportUseCase

    @MockBean
    private lateinit var mockGetDailyReportVoucherUseCase: IGetDailyReportVoucherUseCase

    @MockBean
    private lateinit var mockOrderCustomerInvoiceUseCase: OrderCustomerInvoiceUseCase

    private val reportResponseDTO = ReportResponseDTO.Builder()
        .reportByteArray("array")
        .fileName("fileTest")
        .build()

    val storeOrderRequestDTO = StoreOrderRequestDTO(
        LocalDate.parse("2023-02-01"),
        LocalDate.parse("2023-02-01"),
        1,
        "America/Bogota"
    )

    val result = ResponseEntity(
        ApiResponseDTO(
            reportResponseDTO,
            "Report Witness Tape retrieved successfully"
        ),
        HttpStatus.OK
    )

    @Test
    @Throws(Exception::class)
    fun test_getWitnessTapePdf_Should_Return_Ok() {
        runBlocking {
            Mockito.`when`(
                getWitnessTapeReportUseCase.invoke(
                    storeOrderRequestDTO,
                    BEARER_AUTH + TOKEN
                )
            ).thenReturn(reportResponseDTO)

            val url =
                "${APIConstants.API_V1}${APIConstants.REPORT}/1/witness-tape?localDateEnd=2023-02-01&localDateStart=2023-02-01"
            val result = mockMvc.perform(
                MockMvcRequestBuilders
                    .get(url)
                    .header(
                        HttpHeaders.AUTHORIZATION,
                        BEARER_AUTH + TOKEN
                    )
                    .header("TimeZone", "America/Bogota")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
                .andReturn()
            mockMvc.perform(MockMvcRequestBuilders.asyncDispatch(result))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(
                    MockMvcResultMatchers.content().json(
                        ApiResponseDTO(
                            reportResponseDTO,
                            "Report Witness retrieved successfully"
                        ).toJson()
                    )
                )
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_getDailyReportVoucherPdf_Should_Return_Ok() {
        runBlocking {
            Mockito.`when`(
                mockGetDailyReportVoucherUseCase.invoke(
                    storeOrderRequestDTO,
                    BEARER_AUTH + TOKEN
                )
            ).thenReturn(reportResponseDTO)

            val url =
                "${APIConstants.API_V1}${APIConstants.REPORT}/1/voucher-daily?localDateEnd=2023-02-01&localDateStart=2023-02-01"
            val result = mockMvc.perform(
                MockMvcRequestBuilders
                    .get(url)
                    .header(
                        HttpHeaders.AUTHORIZATION,
                        BEARER_AUTH + TOKEN
                    )
                    .header("TimeZone", "America/Bogota")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
                .andReturn()
            mockMvc.perform(MockMvcRequestBuilders.asyncDispatch(result))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(
                    MockMvcResultMatchers.content().json(
                        ApiResponseDTO(
                            reportResponseDTO,
                            "Report Daily Voucher retrieved successfully"
                        ).toJson()
                    )
                )
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_getOrderCustomerInvoice_Should_Return_Ok() {
        runBlocking {
            Mockito.`when`(
                mockOrderCustomerInvoiceUseCase.invoke(
                    1L,
                    BEARER_AUTH + TOKEN
                )
            ).thenReturn(reportResponseDTO)

            val url =
                "${APIConstants.API_V1}${APIConstants.REPORT}/order/1/invoice"
            val result = mockMvc.perform(
                MockMvcRequestBuilders
                    .get(url)
                    .header(
                        HttpHeaders.AUTHORIZATION,
                        BEARER_AUTH + TOKEN
                    )
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
                .andReturn()
            mockMvc.perform(MockMvcRequestBuilders.asyncDispatch(result))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(
                    MockMvcResultMatchers.content().json(
                        ApiResponseDTO(
                            reportResponseDTO,
                            "Order Customer Invoice retrieved successfully"
                        ).toJson()
                    )
                )
        }
    }

}