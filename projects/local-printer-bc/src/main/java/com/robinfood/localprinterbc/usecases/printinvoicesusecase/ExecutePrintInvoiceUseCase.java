package com.robinfood.localprinterbc.usecases.printinvoicesusecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.output.TcpIpOutputStream;
import com.robinfood.localprinterbc.common.ImageCommon;
import com.robinfood.localprinterbc.common.PaymentMethodEnum;
import com.robinfood.localprinterbc.dtos.invoice.InvoiceDetailDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailPaymentMethodDTO;
import com.robinfood.localprinterbc.dtos.printer.PrinterDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.dtos.store.StoreDTO;
import com.robinfood.localprinterbc.usecases.createcustomlog.ICreateCustomLogUseCase;
import com.robinfood.localprinterbc.usecases.templateconfiguration.IGetTemplateConfigurationUseCase;
import com.robinfood.localprinterbc.utils.CommandsEscPos;
import com.robinfood.localprinterbc.utils.InvoicePrinterUtil;
import com.robinfood.localprinterbc.utils.InvoiceUtil;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.LABEL;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.LINE_BREAK_NUMBER_6;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.REGEX_LINE;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TOTAL_WITCH_27;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.VALUE;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.BRAZIL;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.TEMPLATE_TYPE_INVOICE;
import static com.robinfood.localprinterbc.enums.ErrorLogEnum.MESSAGE_ORDER_INVOICE_EMPTY;
import static com.robinfood.localprinterbc.enums.ErrorLogEnum.MESSAGE_PRINTER_INVOICE_EMPTY;
import static com.robinfood.localprinterbc.enums.ErrorLogEnum.MESSAGE_TEMPLATE_INVOICE_EMPTY;
import static com.robinfood.localprinterbc.enums.InfoLogEnum.MESSAGE_PRINTING_INVOICE_COMPLETED;
import static com.robinfood.localprinterbc.enums.InfoLogEnum.MESSAGE_RECEIVED_FOR_PRINT_INVOICE;

@Service
@Slf4j
public class ExecutePrintInvoiceUseCase implements IExecutePrintInvoiceUseCase {

    @Value("${command.printer.port}")
    private Integer commandPort;

    @Value("${app.store.name.change}")
    private String jsonStores;

    private static final Pattern patternLine = Pattern.compile(REGEX_LINE);

    private final IGetTemplateConfigurationUseCase getTemplateConfigurationUseCase;

    private final ICreateCustomLogUseCase createCustomLogUseCase;

    public ExecutePrintInvoiceUseCase(
            IGetTemplateConfigurationUseCase getTemplateConfigurationUseCase,
            ICreateCustomLogUseCase createCustomLogUseCase) {

        this.getTemplateConfigurationUseCase = getTemplateConfigurationUseCase;
        this.createCustomLogUseCase = createCustomLogUseCase;
    }

    @Override
    public Map<String, Object> printInvoice(String token, InvoiceDetailDTO invoiceDetailDTO) throws IOException {

        log.info(MESSAGE_RECEIVED_FOR_PRINT_INVOICE.getMessage() + " {}", invoiceDetailDTO);

        OrderDetailDTO orderDetailDTO = invoiceDetailDTO.getOrder();

        if (Objects.isNull(orderDetailDTO)) {
            log.error(MESSAGE_ORDER_INVOICE_EMPTY.getMessage());
            throw new IOException("the order is obligatory");
        }

        createCustomLogUseCase.invoke(orderDetailDTO);

        InvoicePrinterUtil.getGroupsWithAdditions(orderDetailDTO);
        InvoicePrinterUtil.orderedPortions(orderDetailDTO);

        PrinterDTO printerDTO = invoiceDetailDTO.getPrinter();
        if (Objects.isNull(printerDTO)) {
            log.error(MESSAGE_PRINTER_INVOICE_EMPTY.getMessage());
            throw new IOException("the printer's ip is mandatory");
        }

        InvoicePrinterUtil.testConnectionPOS(printerDTO.getIp(), commandPort);
        log.info("Pos Printer  Connection Test Successful...");

        printLogInvoiceDetailtDTO(invoiceDetailDTO);

        InvoicePrinterUtil.calculateServices(orderDetailDTO);

        StoreDTO store = invoiceDetailDTO.getStore();

        setPaymentMethod(orderDetailDTO, store);

        PrintingTemplateDTO printingTemplateDTO = getTemplateConfigurationUseCase.invoke(token,
                orderDetailDTO.getStoreId());

        Optional<TemplateDTO> template = getTemplate(printingTemplateDTO);

        String templateInvoice = "";

        if (template.isPresent()) {
            templateInvoice = template.get().getTemplateString();
        } else {
            log.error(MESSAGE_TEMPLATE_INVOICE_EMPTY.getMessage());
            throw new IOException("no configuration template");
        }

        BigDecimal totalValue = getTotalValue(orderDetailDTO);

        ImageCommon.BrandEnum brandEnum =
                ImageCommon.BrandEnum.findByBrandId(orderDetailDTO.getBrandId());

        Map<String, Object> data = CommandsEscPos.buildInformationData(orderDetailDTO,
                totalValue, brandEnum, invoiceDetailDTO, jsonStores);

        String host = printerDTO.getIp();

        log.info("Preparing to print invoice...");

        printInvoice(data, host, commandPort, templateInvoice, orderDetailDTO.getId());

        log.info(MESSAGE_PRINTING_INVOICE_COMPLETED.getMessage());
        return buildResponse();
    }

    private static Optional<TemplateDTO> getTemplate(PrintingTemplateDTO printingTemplateDTO) {
        return printingTemplateDTO.getTemplates()
                .stream()
                .filter(templateDTO -> templateDTO.getTemplateType().getSlug().equals(TEMPLATE_TYPE_INVOICE))
                .findFirst();
    }

    private static Map<String, Object> buildResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("code", "200");
        response.put("message", "success");
        response.put("status", "200");
        response.put("resultCode", "ok");
        return response;
    }

    private void printInvoice(Map<String, Object> data, String host, int port, String templateInvoice, Long orderId)
            throws IOException {
        TcpIpOutputStream outputStream = new TcpIpOutputStream(host, port);
        EscPos escpos = new EscPos(outputStream);
        //para imprimir caracteres especiales de brazil
        escpos.setCharacterCodeTable(EscPos.CharacterCodeTable.CP850_Multilingual);
        final Template template = Mustache.compiler().nullValue("").compile(templateInvoice);

        String result = template.execute(data);

        final String[] lines = patternLine.split(result);
        StringBuilder valueToPrint = new StringBuilder();
        for (String line : lines) {
            Map<String, String> map = CommandsEscPos.getLabelAndValue(line.trim());
            String label = map.get(LABEL);
            String value = map.get(VALUE);
            CommandsEscPos.buildEscPosPrint(escpos, label, value, orderId);
            valueToPrint.append(value);
            valueToPrint.append("\n");
        }
        log.info("----------PRINT----------");
        log.info(valueToPrint.toString());
        log.info("----------END_PRINT----------");
        escpos.feed(LINE_BREAK_NUMBER_6);
        escpos.cut(EscPos.CutMode.FULL);

        escpos.close();
    }

    private static String buildTextWithFillPoints(String label, Object value, int totalWidth) {
        StringBuilder sb = new StringBuilder();
        sb.append(label);

        String valueString = "$" + value;
        sb.append(".".repeat(Math.max(0, totalWidth - (label.length() + valueString.length()))));
        sb.append(valueString);

        return sb.toString();
    }

    private static void setPaymentMethod(OrderDetailDTO orderDetailDTO, StoreDTO store) {
        for (OrderDetailPaymentMethodDTO iterable : orderDetailDTO.getPaymentMethods()) {
            PaymentMethodEnum paymentMethod = PaymentMethodEnum.findById(iterable.getId());
            String name = paymentMethod.getName();
            if (store.getCompany().getCountry().getId().equals(BRAZIL)) {
                name = paymentMethod.getNameBR();
            }

            String value = InvoiceUtil.formatNumberBigDecimalNew(iterable.getValue());

            iterable.setNamePaymentMethodAndValue(buildTextWithFillPoints(name, value, TOTAL_WITCH_27));
            iterable.setName(name);
        }
    }

    private static void printLogInvoiceDetailtDTO(InvoiceDetailDTO invoiceDetailDTO) throws JsonProcessingException {
        // Crear el ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // Convertir el objeto OrderDetailDTO a JSON
        String json = objectMapper.writeValueAsString(invoiceDetailDTO);
        log.info("InvoiceDetailDTO {} ", json);
    }

    public static BigDecimal getTotalValue(OrderDetailDTO orderDetailDTO) {
        return orderDetailDTO.getPaymentMethods()
                .stream().map(OrderDetailPaymentMethodDTO::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
