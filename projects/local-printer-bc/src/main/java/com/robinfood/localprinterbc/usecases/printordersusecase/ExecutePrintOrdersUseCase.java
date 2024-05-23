package com.robinfood.localprinterbc.usecases.printordersusecase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.anastaciocintra.escpos.EscPos;

import com.github.anastaciocintra.output.TcpIpOutputStream;
import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductDTO;
import com.robinfood.localprinterbc.dtos.orders.PrintKitchenTicketDTO;
import com.robinfood.localprinterbc.dtos.printer.PrinterDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateBrandGroupsDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.usecases.applyrules.IApplyRulesUseCase;
import com.robinfood.localprinterbc.usecases.createcustomlog.ICreateCustomLogUseCase;
import com.robinfood.localprinterbc.usecases.templateconfiguration.IGetTemplateConfigurationUseCase;
import com.robinfood.localprinterbc.utils.CommandsEscPos;
import com.robinfood.localprinterbc.utils.OrderPrinterUtil;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Objects;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.robinfood.localprinterbc.configs.constants.APIConstants.CODE_OK;
import static com.robinfood.localprinterbc.configs.constants.APIConstants.MESSAGE_SUCCESS;
import static com.robinfood.localprinterbc.configs.constants.APIConstants.RESULT_CODE;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.REGEX_LINE;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.LINE_BREAK_NUMBER_6;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.ORDER_BRAND_ID;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.TEMPLATE_TYPE_COMMAND;
import static com.robinfood.localprinterbc.enums.ErrorLogEnum.MESSAGE_ORDER_EMPTY;
import static com.robinfood.localprinterbc.enums.ErrorLogEnum.MESSAGE_PRINTER_EMPTY;
import static com.robinfood.localprinterbc.enums.ErrorLogEnum.MESSAGE_TEMPLATE_EMPTY;
import static com.robinfood.localprinterbc.enums.InfoLogEnum.MESSAGE_ORDER_TO_PRINT;
import static com.robinfood.localprinterbc.enums.InfoLogEnum.MESSAGE_PRINTING_COMPLETED;
import static com.robinfood.localprinterbc.enums.InfoLogEnum.MESSAGE_RECEIVED_FOR_PRINT;

@Service
@Slf4j
public class ExecutePrintOrdersUseCase implements IExecutePrintOrdersUseCase {

    private final ICreateCustomLogUseCase createCustomLogUseCase;
    private final IGetTemplateConfigurationUseCase getTemplateConfigurationUseCase;
    private final IApplyRulesUseCase applyRulesUseCase;
    @Value("${command.printer.port}")
    private Integer commandPort;
    private static final Pattern patternLine = Pattern.compile(REGEX_LINE);

    public ExecutePrintOrdersUseCase(
            ICreateCustomLogUseCase createCustomLogUseCase,
            IGetTemplateConfigurationUseCase getTemplateConfigurationUseCase,
            IApplyRulesUseCase applyRulesUseCase
    ) {

        this.createCustomLogUseCase = createCustomLogUseCase;
        this.getTemplateConfigurationUseCase = getTemplateConfigurationUseCase;
        this.applyRulesUseCase = applyRulesUseCase;
    }

    /**
     * metodo encargado de imprimir una comanda.
     *
     * @param token                 autorizacion
     * @param printKitchenTicketDTO dto de la orden y los datos de la impresora
     */
    @Override
    public Map<String, Object> printOrder(
            String token,
            PrintKitchenTicketDTO printKitchenTicketDTO
    ) throws IOException {

        log.info(MESSAGE_RECEIVED_FOR_PRINT.getMessage() + " {}", printKitchenTicketDTO);

        OrderDetailDTO orderDetailDTO = printKitchenTicketDTO.getOrder();

        if (Objects.isNull(orderDetailDTO)) {
            log.error(MESSAGE_ORDER_EMPTY.getMessage());
            throw new IOException("the order is obligatory");
        }

        createCustomLogUseCase.invoke(orderDetailDTO);

        PrinterDTO printerDTO = printKitchenTicketDTO.getPrinter();

        if (Objects.isNull(printerDTO)) {
            log.error(MESSAGE_PRINTER_EMPTY.getMessage());
            throw new IOException("the printer's ip is mandatory");
        }

        String commandIp = printerDTO.getIp();

        OrderPrinterUtil.testConnectionPOS(commandIp, commandPort);

        PrintingTemplateDTO printingTemplateDTO = getTemplateConfigurationUseCase.invoke(token,
                orderDetailDTO.getStoreId());

        TemplateDTO template = getTemplate(printingTemplateDTO, orderDetailDTO.getBrandId()).orElse(null);
        log.info("use case: ExecutePrintOrdersUseCase,  method: printOrder,  param template {}", template);

        if (Objects.isNull(template)) {
            log.error(MESSAGE_TEMPLATE_EMPTY.getMessage());
            throw new IOException("Template does not exist");
        }

        List<OrderDetailDTO> list = groupProductsByBrand(orderDetailDTO);

        int bound = list.size();

        for (int i = 0; i < bound; i++) {
            OrderPrintDTO orderPrintDTO = applyRulesUseCase.invoke(list.get(i), template);
            log.info(MESSAGE_ORDER_TO_PRINT.getMessage() + " {}", orderPrintDTO);
            log.info("------------------------------------------------------------------");
            ObjectMapper oMapper = new ObjectMapper();
            Map<String, Object> dataOrder = oMapper.convertValue(orderPrintDTO, Map.class);
            log.info("dataOrder: " + dataOrder);

            dataOrder.put("kitchenTicketNumber", (i + 1) + " / " + bound);
            dataOrder.put("orderBrandId", "0-0");
            dataOrder.put("brandName", list.get(i).getProducts().get(0).getBrandName());

            if (Objects.nonNull(orderPrintDTO.getUid())) {

                dataOrder.put(ORDER_BRAND_ID, orderPrintDTO.getUid());
                dataOrder.put("orderBrandId", dataOrder.get(ORDER_BRAND_ID));
            }

            dataOrder.put("dateTime", LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a")));

            String templateString = template.getTemplateString();

            printCommand(commandIp, commandPort, dataOrder, templateString);
            log.info(MESSAGE_PRINTING_COMPLETED.getMessage());
        }

        log.info("printOrder finish...");
        return buildResponseOk();
    }

    /**
     * metodo que obtiene una plantilla por su id o por defecto.
     *
     * @param printingTemplateDTO dto que contiene plantillas por marca
     * @return Optional<TemplateDTO>
     */
    private static Optional<TemplateDTO> getTemplate(PrintingTemplateDTO printingTemplateDTO, Long branId) {

        Optional<PrintingTemplateBrandGroupsDTO> printingTemplateBrandGroups =
                getPrintingTemplateBrandGroupsDTO(printingTemplateDTO, branId);

        return printingTemplateBrandGroups.map(printingTemplateBrandGroupsDTO ->
                getTemplateById(printingTemplateDTO, printingTemplateBrandGroupsDTO.getBrandId())
        ).orElseGet(
                () -> getTemplateByDefault(printingTemplateDTO)
        );
    }

    /**
     * metodo que obtiene el id de la plantilla por medio del id de la marca(brandId)
     *
     * @param printingTemplateDTO dto que contiene plantillas por marca
     * @param brandId             id de la marca
     * @return Optional<PrintingTemplateBrandGroupsDTO>
     */
    private static Optional<PrintingTemplateBrandGroupsDTO> getPrintingTemplateBrandGroupsDTO
    (PrintingTemplateDTO printingTemplateDTO, Long brandId) {
        return printingTemplateDTO.getPrintingTemplateBrands()
                .stream()
                .filter(printingTemplateBrandGroupsDTO -> printingTemplateBrandGroupsDTO
                        .getBrandId().equals(brandId))
                .findFirst();
    }

    /**
     * metodo que busca una plantilla dado su id
     *
     * @param printingTemplateDTO dto que contiene plantillas por marca
     * @param templateId          id de la plantilla
     * @return Optional<TemplateDTO>
     */
    private static Optional<TemplateDTO> getTemplateById(PrintingTemplateDTO printingTemplateDTO, Long templateId) {
        if (printingTemplateDTO.getTemplates() != null) {
            return printingTemplateDTO.getTemplates()
                    .stream()
                    .filter(templateDTO -> templateDTO.getTemplateId().equals(templateId) &&
                            templateDTO.getTemplateType().getSlug().equals(TEMPLATE_TYPE_COMMAND))
                    .findFirst();
        }
        return Optional.empty();
    }


    /**
     * metodo que obtiene una plantilla por defecto, para este caso sera la primera.
     *
     * @param printingTemplateDTO dto que contiene plantillas por marca
     * @return Optional<TemplateDTO>
     */
    private static Optional<TemplateDTO> getTemplateByDefault(PrintingTemplateDTO printingTemplateDTO) {
        return printingTemplateDTO.getTemplates().stream().findFirst();
    }

    private void printCommand(String host, Integer port, Map<String, Object> data,
                              String templateString) throws IOException {
        log.info("Started ExecutePrintOrdersUseCase method printCommand(). " +
                "This is data {}", data.toString());

        TcpIpOutputStream outputStream = new TcpIpOutputStream(host, port);
        EscPos escpos = new EscPos(outputStream);
        //se utiliza para imprimir caracteres especiales de brazil
        escpos.setCharacterCodeTable(EscPos.CharacterCodeTable.CP850_Multilingual);
        final Template template = Mustache.compiler().nullValue("").compile(templateString);

        String rs = template.execute(data);
        log.info(rs);

        final String[] lineas = patternLine.split(rs);
        StringBuilder valueToPrint = new StringBuilder();
        for (String linea : lineas) {

            Map<String, String> map = CommandsEscPos.getLabelAndValue(linea.trim());
            String label = map.get("label");
            String value = map.get("value");
            log.info("Linea --> " + linea);

            CommandsEscPos.buildEscPosPrint(escpos, label, value, null);
            valueToPrint.append(value);
            valueToPrint.append("\n");
        }
        log.info("----------PRINT_ORDER----------");
        log.info(valueToPrint.toString());
        log.info("----------END_PRINT_ORDER----------");
        escpos.feed(LINE_BREAK_NUMBER_6);
        escpos.cut(EscPos.CutMode.FULL);
        escpos.close();
    }


    private static List<OrderDetailDTO> groupProductsByBrand(OrderDetailDTO orderDetailDTO) {
        List<OrderDetailDTO> list = new ArrayList<>();

        // group by OrderDetailProductDTO
        Map<Long, List<OrderDetailProductDTO>> groupOfProductsByBrands = orderDetailDTO.getProducts().stream()
                .collect(Collectors.groupingBy(OrderDetailProductDTO::getBrandId));

        separateOrders(orderDetailDTO, list, groupOfProductsByBrands);

        return list;
    }

    private static void separateOrders(OrderDetailDTO orderDetailDTO, List<OrderDetailDTO> list,
                                       Map<Long, List<OrderDetailProductDTO>> groupOfProductsByBrands) {
        groupOfProductsByBrands.forEach((Long key, List<OrderDetailProductDTO> value) ->
                list.add(buildOrderDetailDTO(orderDetailDTO, value))
        );
    }

    private static OrderDetailDTO buildOrderDetailDTO(OrderDetailDTO orderDetailDTO,
                                                      List<OrderDetailProductDTO> value) {

        OrderDetailDTO orderDetail = orderDetailDTO.clone();
        orderDetail.setProducts(value);
        return orderDetail;
    }

    private static Map<String, Object> buildResponseOk() {
        Map<String, Object> response = new HashMap<>();
        response.put("code", CODE_OK);
        response.put("message", MESSAGE_SUCCESS);
        response.put("status", CODE_OK);
        response.put("resultCode", RESULT_CODE);
        return response;
    }


}
