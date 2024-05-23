package com.robinfood.localprinterbc.usecases.printordersusecase;

import com.robinfood.localprinterbc.dtos.decorator.GroupPortionsPrintDTO;
import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.decorator.ToGoDTO;
import com.robinfood.localprinterbc.dtos.orders.CouponsDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderBuyerDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailChangedPortionDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDiscountDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailPaymentMethodDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductDiscountDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductGroupDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductGroupPortionDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductTaxDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailRemovedPortionDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailUserDTO;
import com.robinfood.localprinterbc.dtos.orders.PrintKitchenTicketDTO;
import com.robinfood.localprinterbc.dtos.orders.ServiceDTO;
import com.robinfood.localprinterbc.dtos.printer.PrinterDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateBrandGroupsDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateTypesDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.entities.PrintingTemplateBrandGroupsEntity;
import com.robinfood.localprinterbc.entities.PrintingTemplateEntity;
import com.robinfood.localprinterbc.entities.PrintingTemplateTypeEntity;
import com.robinfood.localprinterbc.entities.TemplateEntity;
import com.robinfood.localprinterbc.mappers.printingtemplate.IPrintingTemplateMapper;
import com.robinfood.localprinterbc.repositories.templateconfigurationrepository.ITemplateConfigurationRepository;
import com.robinfood.localprinterbc.usecases.applyrules.IApplyRulesUseCase;
import com.robinfood.localprinterbc.usecases.createcustomlog.ICreateCustomLogUseCase;
import com.robinfood.localprinterbc.usecases.templateconfiguration.IGetTemplateConfigurationUseCase;
import com.robinfood.localprinterbc.utils.OrderPrinterUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.robinfood.localprinterbc.configs.constants.APIConstants.CODE_OK;
import static com.robinfood.localprinterbc.configs.constants.APIConstants.MESSAGE_SUCCESS;
import static com.robinfood.localprinterbc.configs.constants.APIConstants.RESULT_CODE;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.TEMPLATE_TYPE_COMMAND;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.TEMPLATE_TYPE_INVOICE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExecutePrintOrdersUseCaseTest {

    @InjectMocks
    private ExecutePrintOrdersUseCase executePrintOrdersUseCase;
    @Mock
    private IGetTemplateConfigurationUseCase getTemplateConfigurationUseCase;

    @Mock
    private IPrintingTemplateMapper printingTemplateMapper;

    @Mock
    private ITemplateConfigurationRepository templateConfigurationRepository;

    @Mock
    private IApplyRulesUseCase applyRulesUseCase;

    @Mock
    private ICreateCustomLogUseCase createCustomLogUseCase;

    private static final String BEARER_AUTH = "Bearer ";
    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxNzE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlcl9vcl9sb2NhbHNlcnZlciJdLCJwZXIiOlsib3JjaF9tZW51X3NlcnZpY2VzIiwiY2hlY2tVc2VyRGF0YSIsImNoZWNrX3VzZXJfZGF0YSIsInRheGVzIiwic3luY09yZGVyIiwiQ1JFQVRFX1RSQU5TQUNUSU9OIiwiY2hlY2tDb21wYW55RGF0YSIsImNoZWNrX2NvbXBhbnlfZGF0YSIsIlRBWEVTX0JDX1NFUlZJQ0UiLCJUQVhFU19CQ19MSVNUX0lURU1fVEFYIiwibG95YWx0eV90cmFuc2FjdGlvbl9jcmVkaXRzIiwib3JkZXJfc3luYyIsIk9SREVSX1NZTkMiLCJiY19zZ2lfb3JkZXItcmVhZC1maW5hbmNlX2NhdGVnb3JpZXMiXSwidXNlciI6eyJ1c2VyX2lkIjoxMjM0NTY3LCJlbWFpbCI6ImpvaG5kb2VAbXljb21wYW55LmNvbSIsImNvdW50cnlfaWQiOjEsImNvbXBhbnlfaWQiOjEsImZpcnN0X25hbWUiOiJKaG9uIiwibGFzdF9uYW1lIjoiRG9lIiwicGhvbmUiOiI1NTUtNjM4MzAyMiIsIm1ldGFkYXRhIjp7InN0b3JlX2lkIjo1fX19.aZbT_gnMfCz6XlSJtkY0W0PLuyPFFmVaS8Y8phMhVe6wDDdLsxwYM1v0_UOK6Bg5UUuPPVnGD0Sj1y3aH-uSmg";
    private final Long storeId = 3L;


    final PrintingTemplateEntity printingTemplateEntity = PrintingTemplateEntity.builder()
            .printingTemplateBrands(
                    Arrays.asList(
                            PrintingTemplateBrandGroupsEntity
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
                            TemplateEntity
                                    .builder()
                                    .templateId(1L)
                                    .groupId(1L)
                                    .groupName("Group")
                                    .templateString("<template>test</template>")
                                    .rules(new ArrayList<>())
                                    .templateType(PrintingTemplateTypeEntity.builder().build())
                                    .build(),
                            TemplateEntity
                                    .builder()
                                    .templateId(2L)
                                    .groupId(1L)
                                    .groupName("Group")
                                    .templateString("<template>test two</template>")
                                    .rules(new ArrayList<>())
                                    .templateType(PrintingTemplateTypeEntity.builder().build())
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
                            "Efectivo .... 15.400"
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

    private final PrintKitchenTicketDTO printKitchenTicketDTO =
            new PrintKitchenTicketDTO(orderDetailWithCouponsDTO, printerDTO);

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(executePrintOrdersUseCase, "commandPort", 9200);
    }

    @Test
    void test_PrintOrder_When_Brand_Exist_Return_Correctly() throws IOException {
        String token = BEARER_AUTH+TOKEN;
        Map<String, Object> response = buildResponseOk();

        PrintingTemplateDTO printingTemplateDTO = getPrintingTemplateDTO(1L, TEMPLATE_TYPE_COMMAND);

        when(getTemplateConfigurationUseCase.invoke(token, storeId)).thenReturn(printingTemplateDTO);

        TemplateDTO template  = printingTemplateDTO.getTemplates().get(0);

        when(applyRulesUseCase.invoke(any(OrderDetailDTO.class), eq(template))).thenReturn(
                buildOrderPrintDTO()
        );

        Mockito.doNothing().when(createCustomLogUseCase).invoke(any(OrderDetailDTO.class));

        final Map<String, Object> responseUseCase =  executePrintOrdersUseCase.printOrder(token, printKitchenTicketDTO);
        assertEquals(responseUseCase, response);
        verify(createCustomLogUseCase).invoke(any(OrderDetailDTO.class));
    }

    @Test
    void test_printOrder_when_template_is_type_invoice_Return_IOException() throws IOException {
        String token = BEARER_AUTH+TOKEN;
        Map<String, Object> response = buildResponseOk();

        PrintingTemplateDTO printingTemplateDTO = getPrintingTemplateDTO(1L, TEMPLATE_TYPE_INVOICE);

        when(getTemplateConfigurationUseCase.invoke(token, storeId)).thenReturn(printingTemplateDTO);

        TemplateDTO template  = printingTemplateDTO.getTemplates().get(1);

        IOException ioException = assertThrows(IOException.class,
                () -> executePrintOrdersUseCase.printOrder(token, printKitchenTicketDTO));
    }



    @Test
    void test_PrintOrder_When_Uid_Is_Null_Return_Correctly() throws IOException {
        String token = BEARER_AUTH+TOKEN;
        Map<String, Object> response = buildResponseOk();

        PrintingTemplateDTO printingTemplateDTO = getPrintingTemplateDTO(1L, TEMPLATE_TYPE_COMMAND);

        when(getTemplateConfigurationUseCase.invoke(token, storeId)).thenReturn(printingTemplateDTO);

        TemplateDTO template  = printingTemplateDTO.getTemplates().get(0);

        OrderPrintDTO orderPrintDTO = buildOrderPrintDTO();
        orderPrintDTO.setUid(null);
        when(applyRulesUseCase.invoke(any(OrderDetailDTO.class), eq(template))).thenReturn(
            orderPrintDTO
        );

        final Map<String, Object> responseUseCase =  executePrintOrdersUseCase.printOrder(token, printKitchenTicketDTO);
        assertEquals(responseUseCase, response);
    }
    @Test
    void test_PrintOrder_When_TemplateString_Is_Null_Return_Correctly() throws IOException {
        String token = BEARER_AUTH+TOKEN;

        PrintingTemplateDTO printingTemplateDTO = getPrintingTemplateDTO(1L, TEMPLATE_TYPE_COMMAND);
        printingTemplateDTO.setTemplates(null);
        when(getTemplateConfigurationUseCase.invoke(token, storeId)).thenReturn(printingTemplateDTO);

        IOException ioException = assertThrows(IOException.class,
                () -> executePrintOrdersUseCase.printOrder(token, printKitchenTicketDTO));
    }

    @Test
    void test_PrintOrder_When_Order_Is_Null_Return_Correctly() throws IOException {
        String token = BEARER_AUTH+TOKEN;

        printKitchenTicketDTO.setOrder(null);

        IOException ioException = assertThrows(IOException.class,
                () -> executePrintOrdersUseCase.printOrder(token, printKitchenTicketDTO));
    }

    @Test
    void test_PrintOrder_When_Printer_Is_Null_Return_Correctly() throws IOException {
        String token = BEARER_AUTH+TOKEN;

        printKitchenTicketDTO.setPrinter(null);

        IOException ioException = assertThrows(IOException.class,
                () -> executePrintOrdersUseCase.printOrder(token, printKitchenTicketDTO));
    }


    @Test
    void test_PrintOrder_When_Brand_Not_Exist_Return_Correctly() throws IOException {
        String token = BEARER_AUTH+TOKEN;
        String commandIp = "commandPrinterIp";
        String commandPort = "commandPrinterPort";
        Map<String, Object> response = buildResponseOk();
        PrintingTemplateDTO printingTemplateDTO = getPrintingTemplateDTO(2L, TEMPLATE_TYPE_COMMAND);
        when(getTemplateConfigurationUseCase.invoke(token, storeId)).thenReturn(printingTemplateDTO);

        TemplateDTO template  = printingTemplateDTO.getTemplates().get(0);
        when(applyRulesUseCase.invoke(any(OrderDetailDTO.class), eq(template))).thenReturn(
                buildOrderPrintDTO()
        );

        mockStatic(OrderPrinterUtil.class);
        doNothing().when(OrderPrinterUtil.class);
        OrderPrinterUtil.testConnectionPOS(anyString(), anyInt());

        final Map<String, Object> responseUseCase =  executePrintOrdersUseCase.printOrder(token, printKitchenTicketDTO);
        assertEquals(responseUseCase, response);
    }

    private PrintingTemplateDTO getPrintingTemplateDTO(Long branId, String slug){
        return  PrintingTemplateDTO.builder()
                .printingTemplateBrands(
                        Arrays.asList(
                                PrintingTemplateBrandGroupsDTO
                                        .builder()
                                        .id(1L)
                                        .brandId(branId)
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
                                        .templateString(getTemplate())
                                        .rules(new ArrayList<>())
                                        .templateType(PrintingTemplateTypesDTO.builder().slug(slug).build())
                                        .build(),
                                TemplateDTO
                                        .builder()
                                        .templateId(2L)
                                        .groupId(1L)
                                        .groupName("Group")
                                        .templateString(null)
                                        .rules(new ArrayList<>())
                                        .templateType(PrintingTemplateTypesDTO.builder().slug(slug).build())
                                        .build()
                        )
                )
                .build();

    }



    private OrderPrintDTO buildOrderPrintDTO(){
        List<OrderDetailProductGroupPortionDTO>listItem = new ArrayList<>();
        listItem.add(OrderDetailProductGroupPortionDTO.builder().quantity(1).name("producto 1").build());

        GroupPortionsPrintDTO groupPortionsPrintDTO = new GroupPortionsPrintDTO();
        groupPortionsPrintDTO.setTitle("Titulo");
        groupPortionsPrintDTO.setHasItem(Boolean.TRUE);
        groupPortionsPrintDTO.setItems(listItem);




        List<OrderDetailProductDTO>listOrderDetailProductDTO = new ArrayList<>();
        OrderDetailProductDTO ob = new OrderDetailProductDTO();
        ob.setToInclude(groupPortionsPrintDTO);
        ob.setQuantity(1);
        ob.setName("Name");


        return  OrderPrintDTO.builder()
                .brandName("Brand Name Test")
                .originName("Origin Name")
                .toGo(ToGoDTO.builder().title("Para Llevar").toGo(Boolean.TRUE).build())
                .suggestedProducts(listOrderDetailProductDTO)
                .uid("1234")
                .build();
    }


    private String getTemplate(){
        return "<TITTLE_CENTER_FONT_SIZE_2>{{kitchenTicketNumber}}</TITTLE_CENTER_FONT_SIZE_2> |#| \n" +// Titulo
                "<LINE_BREAK_1> |#| \n" + //Salto de linea
                "<DEFATULT>default</DEFATULT> |#| \n" + //Salto de linea
                "<TITTLE_CENTER_FONT_SIZE_2>{{brandName}}</TITTLE_CENTER_FONT_SIZE_2> |#| \n" + // Nombre de la marca
                "<BARCODE>{{orderBrandId}}</BARCODE> |#| \n" +// Codigo de barra.
                "<LINE_BREAK_1> |#| \n" +//Salto de linea
                "<TEXT_BOLD>{{originName}}</TEXT_BOLD> |#| \n" +
                "<LINE_BREAK_2> |#| \n" +//Salto de linea
                "{{#toGo.toGo}}\n" + // Condicional que pregunta si es 'Para llevar el pedido'
                "<LINE_BREAK_1> |#| \n" +//Salto de linea
                "<TEXT_CENTER_SIZE_3>TextCenterSize3</TEXT_CENTER_SIZE_3> |#| \n" +
                "<TEXT_CENTER_SIZE_2>{{toGo.title}}</TEXT_CENTER_SIZE_2> |#| \n" +
                "{{/toGo.toGo}}\n" +// Find del condicional que pregunta si es 'Para llevar el pedido'

                "{{#userAndIdOrder}}\n" +// Condicional para saber si un usuario realizo la orden
                "<LINE_BREAK_1> |#| \n" +//Salto de linea
                "<TITTLE_CENTER_FONT_SIZE_2>{{userAndIdOrder.user}}</TITTLE_CENTER_FONT_SIZE_2> |#| \n" +// Nombre del usuario
                "<LINE_BREAK_1> |#| \n" +//Salto de linea
                "<TITTLE_CENTER_FONT_SIZE_2>{{userAndIdOrder.id}}</TITTLE_CENTER_FONT_SIZE_2> |#| \n" +// identificacion del usuario
                "{{/userAndIdOrder}}\n" +// Fin del condicional para saber si un usuario realizo la orden

                "<LINE_BREAK_1> |#| \n" +//Salto de linea
                "<SEPARATOR> |#| \n" +//Separador, se representa '-----'

                "{{#suggestedProducts}}\n" +// Ciclo repetitivo de las sugerencias del producto
                "<TEXT_BOLD>{{quantity}}x {{name}}</TEXT_BOLD> |#| \n" + // Cantidad y nombre del plato

                "{{#toInclude.hasItem}}\n" +// Condicion que pregunta si existe algo para incluir
                "<TEXT_BOLD>{{toInclude.title}}</TEXT_BOLD> |#| \n" + // Nombre del titulo 'Ingredientes'
                "{{#toInclude.items}}\n" + // Ciclo repetitivo de los ingredientes del producto
                "<TEXT>{{quantity}} x {{{name}}}</TEXT> |#| \n" + // Texto que indica el nombre y cantidad de ingrediente
                "{{/toInclude.items}}\n" +// Fin del ciclo repetitivo de los ingredientes del producto
                "{{/toInclude.hasItem}}\n" +// Fin del condicional que pregunta si existe algo para incluir

                "{{#toChange.hasItem}}\n" +// Condicion que pregunta si existe algo para cambiar
                "<TEXT_BOLD>{{toChange.title}}</TEXT_BOLD> |#| \n" +// Titulo de cambiar
                "{{#toChange.items}}\n" +// Ciclo repetitivo de los cambios que se van a realizar
                "<TEXT>{{{symbol}}}{{{name}}} -> {{{changedPortion.name}}}</TEXT> |#| \n" + // Cambios
                "{{/toChange.items}}\n" +//Fin del ciclo repetitivo de los cambios que se van a realizar
                "{{/toChange.hasItem}}\n" +// Fin de la Condicion que pregunta si existe algo para cambiar

                "{{#toRemove.hasItem}}\n" +// Condicion que pregunta si existe algo para quitar
                "<TEXT_BOLD>{{toRemove.title}}</TEXT_BOLD> |#| \n" +// Titulo de quitar
                "{{#toRemove.items}}\n" +// Ciclo repetitivo de los productos o ingredientes a quitar
                "<TEXT>{{{symbol}}}{{{name}}}</TEXT> |#| \n" + // Quitar
                "{{/toRemove.items}}\n" +// Fin del ciclo repetitivo de los productos o ingredientes a quitar
                "{{/toRemove.hasItem}}\n" +// Condicion que pregunta si existe algo para quitar

                "{{#toAdd.hasItem}}\n" +// Condicion que pregunta si existe algo para agregar
                "<TEXT_BOLD>{{toAdd.title}}</TEXT_BOLD> |#| \n" +// Titulo de agregar
                "{{#toAdd.items}}\n" +// Ciclo repetitivo de lo que se va adicionar
                "<TEXT> {{{symbol}}} {{quantity}} x {{{name}}}</TEXT> |#| \n" + // Adicionar
                "{{/toAdd.items}}\n" +// Fin del ciclo repetitivo de lo que se va adicionar
                "{{/toAdd.hasItem}}\n" +//Fin de la condicion que pregunta si existe algo para agregar

                "{{/suggestedProducts}}\n" +// Fin del ciclo repetitivo de las sugerencias del producto
                "<SEPARATOR> |#| \n" +//Separador, se representa '-----'

                "{{#drinksAndDesserts.hasItem}}\n" +//Condicion que pregunta si hay bebidas y postres
                "<LINE_BREAK_1> |#| \n" +//Salto de linea
                "<TEXT_BOLD>{{{drinksAndDesserts.title}}}</TEXT_BOLD> |#| \n" +// Titulo de bebidas y postres
                "{{#drinksAndDesserts.items}}\n" +// Ciclo repetitivo de las bebidas o postres
                "<TEXT> {{quantity}} x {{name}}</TEXT> |#| \n" + // Cantidad de bebidas o postres
                "{{/drinksAndDesserts.items}}\n" +// Fin del ciclo repetitivo de las bebidas o postres
                "<LINE_BREAK_1> |#| \n" +//Salto de linea
                "{{/drinksAndDesserts.hasItem}}\n" +//Fin del Condicional que pregunta si hay bebidas y postres

                "{{#notes}}\n" +// Condicional que indica si hay notas
                "<LINE_BREAK_1> |#| \n" +//Salto de linea
                "<TEXT_BOLD>Notas</TEXT_BOLD> |#| \n" +// Titulo
                "<TEXT>{{notes}}</TEXT> |#| \n" +// Texto dinamico de las notas
                "<LINE_BREAK_1> |#| \n" +//Salto de linea
                "{{/notes}}\n" +//Fin del Condicional que indica si hay notas
                "<TEXT>{{dateTime}}</TEXT>"; // Texto que muestra la fecha y hora
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