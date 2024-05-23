package com.robinfood.localprinterbc.controllers.printorderscontroller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.localprinterbc.dtos.orders.*;
import com.robinfood.localprinterbc.dtos.printer.PrinterDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateBrandGroupsDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateTypesDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.usecases.printordersusecase.IExecutePrintOrdersUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static com.robinfood.localprinterbc.configs.constants.APIConstants.*;
import static com.robinfood.localprinterbc.configs.constants.APIConstants.RESULT_CODE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(IPrintOrdersController.class)
class PrintOrdersControllerTest {


    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxNzE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlcl9vcl9sb2NhbHNlcnZlciJdLCJwZXIiOlsib3JjaF9tZW51X3NlcnZpY2VzIiwiY2hlY2tVc2VyRGF0YSIsImNoZWNrX3VzZXJfZGF0YSIsInRheGVzIiwic3luY09yZGVyIiwiQ1JFQVRFX1RSQU5TQUNUSU9OIiwiY2hlY2tDb21wYW55RGF0YSIsImNoZWNrX2NvbXBhbnlfZGF0YSIsIlRBWEVTX0JDX1NFUlZJQ0UiLCJUQVhFU19CQ19MSVNUX0lURU1fVEFYIiwibG95YWx0eV90cmFuc2FjdGlvbl9jcmVkaXRzIiwib3JkZXJfc3luYyIsIk9SREVSX1NZTkMiLCJiY19zZ2lfb3JkZXItcmVhZC1maW5hbmNlX2NhdGVnb3JpZXMiXSwidXNlciI6eyJ1c2VyX2lkIjoxMjM0NTY3LCJlbWFpbCI6ImpvaG5kb2VAbXljb21wYW55LmNvbSIsImNvdW50cnlfaWQiOjEsImNvbXBhbnlfaWQiOjEsImZpcnN0X25hbWUiOiJKaG9uIiwibGFzdF9uYW1lIjoiRG9lIiwicGhvbmUiOiI1NTUtNjM4MzAyMiIsIm1ldGFkYXRhIjp7InN0b3JlX2lkIjo1fX19.aZbT_gnMfCz6XlSJtkY0W0PLuyPFFmVaS8Y8phMhVe6wDDdLsxwYM1v0_UOK6Bg5UUuPPVnGD0Sj1y3aH-uSmg";
    private static final String BEARER_AUTH = "Bearer ";
    private static final String ENCODING = "utf-8";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IExecutePrintOrdersUseCase executePrintOrdersUseCase;

    final PrintingTemplateDTO printingTemplateDTO = PrintingTemplateDTO.builder()
            .printingTemplateBrands(
                    Arrays.asList(
                            PrintingTemplateBrandGroupsDTO
                                    .builder()
                                    .id(1L)
                                    .groupId(1L)
                                    .active(Boolean.TRUE)
                                    .printingTemplateId(1L)
                                    .createdAt(LocalDateTime.parse("2023-03-08T14:00:01.318967"))
                                    .updatedAt(LocalDateTime.parse("2023-03-08T14:00:01.318967"))
                                    .build()
                    )
            )
            .templates(
                    Arrays.asList(
                            TemplateDTO
                                    .builder()
                                    .templateId(1L)
                                    .groupId(1L)
                                    .groupName("Group")
                                    .templateString("<template>test</template>")
                                    .rules(new ArrayList<>())
                                    .templateType(PrintingTemplateTypesDTO.builder().build())
                                    .build(),
                            TemplateDTO
                                    .builder()
                                    .templateId(2L)
                                    .groupId(1L)
                                    .groupName("Group")
                                    .templateString("<template>test two</template>")
                                    .rules(new ArrayList<>())
                                    .templateType(PrintingTemplateTypesDTO.builder().build())
                                    .build()
                    )
            )
            .build();


    private final List<OrderDetailPaymentMethodDTO> orderDetailPaymentMethodDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailPaymentMethodDTO(
                            0.0,
                            8L,
                            "Cash",
                            0L,
                            29351.8516,
                            2348.1484,
                            BigDecimal.valueOf(31700.0),
                            "Efectivo ..... 15.400"
                    )
            )
    );

    private final OrderDetailChangedPortionDTO orderDetailChangedPortionDTO = new OrderDetailChangedPortionDTO(
            1L,
            "Portion",
            1L,
            "12345",
            2L,
            1.0
    );


    private final List<OrderDetailProductGroupPortionDTO> orderDetailProductGroupPortionDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupPortionDTO(
                            false,
                            orderDetailChangedPortionDTO,
                            BigDecimal.ZERO,
                            196L,
                            "Chorizo.",
                            108L,
                            0.0,
                            1,
                            8,
                            "176",
                            1L,
                            3.0,
                            null,
                            true
                    )
            )
    );

    private final List<OrderDetailRemovedPortionDTO> orderDetailRemovedPortionDTOS = Collections.singletonList(
            new OrderDetailRemovedPortionDTO(
                    1L,
                    "Rice",
                    null
            )
    );

    private final List<OrderDetailProductGroupDTO> orderDetailProductGroupDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupDTO(
                            10L,
                            "¿QUE QUIERES QUITAR DE TU PLATO?",
                            orderDetailProductGroupPortionDTOS,
                            orderDetailRemovedPortionDTOS,
                            "1760",
                            true,
                            true
                    )
            )
    );

    private final List<OrderDetailProductTaxDTO> orderDetailProductTaxDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductTaxDTO(
                            1L,
                            3928865L,
                            733.3334,
                            null,
                            null,
                            8.0
                    )
            )
    );

    private final List<OrderDetailProductDiscountDTO> orderDetailProductDiscountDTOS = Collections.singletonList(
            new OrderDetailProductDiscountDTO(
                    1L,
                    1L,
                    new BigDecimal(0)
            )
    );

    private final List<OrderDetailProductDTO> orderDetailProductDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductDTO(
                            1L,
                            "Muy paisa",
                            1L,
                            8900.0,
                            1L,
                            "muy",
                            1L,
                            "Category",
                            BigDecimal.valueOf(0L),
                            new BigDecimal(0),
                            new BigDecimal(0),
                            orderDetailProductDiscountDTOS,
                            1L,
                            orderDetailProductGroupDTOS,
                            17L,
                            "image.png",
                            1L,
                            1L,
                            "paisa",
                            1,
                            1L,
                            "MUY",
                            "1760",
                            orderDetailProductTaxDTOS,
                            9900.0,
                            11500.0,
                            "11,500.00",
                            null,
                            null,
                            null,
                            null,
                            true,
                            null,
                            null
                    )
            )
    );

    private final OrderDetailUserDTO orderDetailUserDTO = new OrderDetailUserDTO(
            "bruno@email",
            "bruno",
            1L,
            "jason",
            0L,
            "300"
    );

    private final List<OrderDetailDiscountDTO> orderDetailDiscountDTOS = Collections.singletonList(
            new OrderDetailDiscountDTO(
                    1L,
                    1L,
                    new BigDecimal(0)
            )
    );

    private final OrderDetailDTO orderDetailWithCouponsDTO = new OrderDetailDTO(
            new OrderBuyerDTO(
                    "464874849"
            ),
            1L,
            "muy",
            BigDecimal.valueOf(1L),
            "cop",
            List.of(CouponsDTO.builder().code("Cupon1").value(BigDecimal.valueOf(1L)).build()),
            1L,
            new BigDecimal(0),
            new BigDecimal(0),
            orderDetailDiscountDTOS,
            1L,
            null,
            1L,
            true,
            "",
            "Notes",
            "2024-06-15",
            "11:27:44",
            1L,
            "pos",
            "2005",
            "3483eghf",
            "test 1",
            "12345",
            true,
            "529177c8-77d9-11ed-a1eb-0242ac120002",
            orderDetailPaymentMethodDTOS,
            false,
            orderDetailProductDTOS,
            Arrays.asList(ServiceDTO.builder().id(1L).name("Domicilio").unitPrice(1000.0).build()),
            1L,
            "Pedido",
            3L,
            "muy 79",
            8900.0,
            0.0,
            BigDecimal.valueOf(8900.0),
            1L,
            "1234",
            orderDetailUserDTO,
            Collections.singletonList(orderDetailUserDTO),
            "2,85"
    );

    private final PrinterDTO printerDTO = new PrinterDTO("192.168.0.1", "Cocina 1");

    private final  PrintKitchenTicketDTO printKitchenTicketDTO =
            new PrintKitchenTicketDTO(orderDetailWithCouponsDTO, printerDTO);


    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void test_executePrintOrders_Is_Ok() throws Exception {

        String path = PRINT + PRINT_ORDERS;

        Map<String, Object> response = buildResponseOk();

        when(executePrintOrdersUseCase.printOrder(BEARER_AUTH+TOKEN, printKitchenTicketDTO))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .content(objectMapper.writeValueAsString(orderDetailWithCouponsDTO))
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private static Map<String, Object> buildResponseOk() {
        Map<String, Object>response = new HashMap<>();
        response.put("code", CODE_OK);
        response.put("message", MESSAGE_SUCCESS);
        response.put("status", CODE_OK);
        response.put("resultCode", RESULT_CODE);
        return response;
    }
}