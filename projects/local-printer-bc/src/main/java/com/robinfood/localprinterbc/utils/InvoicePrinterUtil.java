package com.robinfood.localprinterbc.utils;

import com.robinfood.localprinterbc.dtos.orders.AdditionDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductGroupDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductGroupPortionDTO;
import com.robinfood.localprinterbc.dtos.orders.ServiceDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TOTAL_WITCH_40;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.LABEL_DISCOUNT;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.LABEL_SUB_TOTAL;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.LABEL_TAX;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.LABEL_TOTAL;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants
        .MESSAGE_HOST_INTERRUPTED_OR_EXECUTION_FAILED;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.MESSAGE_HOST_TIMEOUT;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.MESSAGE_HOST_UNKNOWN_EXCEPTION;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.VALUE_PERCENTAGE;
import static com.robinfood.localprinterbc.enums.ErrorLogEnum.MESSAGE_INVOICE_HOST_CONNECTION_REFUSED;
import static com.robinfood.localprinterbc.enums.ErrorLogEnum.MESSAGE_PING_FAILED;

@Slf4j
public class InvoicePrinterUtil {

    public static void testConnectionPOS(String host, Integer port) throws IOException {

        int timeoutMillis = 3000;

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Boolean> connectionTask = () -> {
            try (Socket socket = new Socket(host, port)) {
                return Boolean.TRUE;
            } catch (java.net.NoRouteToHostException e) {
                log.error(MESSAGE_PING_FAILED.getMessage() + "{} {}", MESSAGE_HOST_INTERRUPTED_OR_EXECUTION_FAILED
                        , host, e);
                return Boolean.FALSE;
            } catch (java.io.IOException e) {
                log.error(MESSAGE_PING_FAILED.getMessage() + "{} {}", MESSAGE_HOST_TIMEOUT, host, e);
                return Boolean.FALSE;
            } catch (Exception e) {
                log.info(MESSAGE_PING_FAILED.getMessage() + "{} {}", MESSAGE_HOST_UNKNOWN_EXCEPTION, host, e);
                return Boolean.FALSE;
            }
        };

        Future<Boolean> future = executor.submit(connectionTask);

        try {
            Boolean isConnected = future.get(timeoutMillis, TimeUnit.MILLISECONDS);
            if (!isConnected) {
                throw new IOException("Connection refused");
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error(MESSAGE_INVOICE_HOST_CONNECTION_REFUSED.getMessage() + "{} {}",
                    MESSAGE_HOST_INTERRUPTED_OR_EXECUTION_FAILED, host);
            throw new IOException("Connection refused " + e);
        } catch (TimeoutException e) {
            log.error(MESSAGE_INVOICE_HOST_CONNECTION_REFUSED.getMessage() + "{} {}", MESSAGE_HOST_TIMEOUT, host);
            throw new IOException("Connection refused " + e);
        } catch (Exception e) {
            log.error(MESSAGE_INVOICE_HOST_CONNECTION_REFUSED.getMessage() + "{} {}", MESSAGE_HOST_UNKNOWN_EXCEPTION
                    , host);
            throw new IOException("Connection refused " + e);
        }

        executor.shutdown();
    }

    /**
     * metodo que separa las adiciones de los platos principales
     * para agregarlas en otra seccion de la factura
     *
     * @param orderDetailDTO
     */
    public static void getGroupsWithAdditions(OrderDetailDTO orderDetailDTO) {
        for (OrderDetailProductDTO product : orderDetailDTO.getProducts()) {
            product.setListAddition(buildGroupsWithAdditions(product.getGroups()));
        }
    }

    private static List<AdditionDTO> buildGroupsWithAdditions(List<OrderDetailProductGroupDTO> groups) {
        if (groups == null) {
            return Collections.emptyList();
        }

        return groups.stream()
                .flatMap(gr -> gr.getPortions().stream())
                .filter(OrderDetailProductGroupPortionDTO::getAddition)
                .map(portion -> new AdditionDTO(portion.getQuantity(), portion.getName()))
                .collect(Collectors.toList());
    }

    public static void orderedPortions(OrderDetailDTO orderDetailDTO) {
        orderDetailDTO.getProducts().forEach(products ->
                products.setTotalFullFormat(InvoiceUtil.formatNumberDoubleNew(products.getTotal()))
        );
    }

    public static void calculateServices(OrderDetailDTO orderDetailDTO) {
        if (!Objects.isNull(orderDetailDTO.getServices())) {
            Double valueDiscount = Double.valueOf(0.0);
            for (ServiceDTO service : orderDetailDTO.getServices()) {
                String total = InvoiceUtil.formatNumberDoubleNew(service.getUnitPrice());
                service.setTotal(buildTextWithFillPoints(LABEL_TOTAL, total, TOTAL_WITCH_40));
                String subTotalString = InvoiceUtil.formatNumberDoubleNew(service.getUnitPrice() / VALUE_PERCENTAGE);
                Double subTotal = InvoiceUtil.parseDouble(subTotalString);
                service.setPriceNt(buildTextWithFillPoints(LABEL_SUB_TOTAL, subTotalString, TOTAL_WITCH_40));
                String taxPriceString = InvoiceUtil.formatNumberDoubleNew(service.getUnitPrice() - subTotal);
                service.setTaxPrice(buildTextWithFillPoints(LABEL_TAX, taxPriceString, TOTAL_WITCH_40));
                service.setDiscount(buildTextWithFillPoints(LABEL_DISCOUNT,
                        InvoiceUtil.formatNumberDoubleNew(valueDiscount), TOTAL_WITCH_40));
            }
        }
    }

    private static String buildTextWithFillPoints(String label, Object value, int totalWidth) {
        StringBuilder sb = new StringBuilder();
        sb.append(label);

        String valueString = "$" + value;
        sb.append(".".repeat(Math.max(0, totalWidth - (label.length() + valueString.length()))));
        sb.append(valueString);

        return sb.toString();
    }
}
