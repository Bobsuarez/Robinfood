package com.robinfood.localserver.commons.utilities;

import com.robinfood.localserver.commons.dtos.http.PrintingRequestDTO;
import lombok.extern.slf4j.Slf4j;

import static com.robinfood.localserver.commons.constants.GlobalConstants.PRINT;
import static com.robinfood.localserver.commons.constants.GlobalConstants.REPRINT;
import static com.robinfood.localserver.commons.enums.logs.PrintLogEnum.PRINT_INVOICE_TICKET_FAILED;
import static com.robinfood.localserver.commons.enums.logs.PrintLogEnum.PRINT_INVOICE_TICKET_SUCCESSFULLY;
import static com.robinfood.localserver.commons.enums.logs.PrintLogEnum.REPRINT_INVOICE_TICKET_FAILED;
import static com.robinfood.localserver.commons.enums.logs.PrintLogEnum.REPRINT_INVOICE_TICKET_SUCCESSFULLY;
import static com.robinfood.localserver.commons.enums.logs.PrintLogEnum.REPRINT_KITCHEN_TICKET_FAILED;
import static com.robinfood.localserver.commons.enums.logs.PrintLogEnum.REPRINT_KITCHEN_TICKET_SUCCESSFULLY;

@Slf4j
public final class PrintLogs {
    private PrintLogs() {
    }

    /**
     * Decide which logs to take if kitchen or invoice
     *
     * @param printingRequestDTO request print order
     * @param successfulProcess  printing successful or failed
     * @param isInvoice          true if the printout is an invoice and false if it is a kitchen command
     * @return logs printing
     */
    public static String orchestratorPrintLogsInvoiceOrKitchen(
            PrintingRequestDTO printingRequestDTO,
            Boolean successfulProcess,
            Boolean isInvoice
    ) {
        if (isInvoice.equals(Boolean.TRUE)) {
            return getInvoiceLogMessage(printingRequestDTO, successfulProcess);
        }
        return getKitchenLogMessage(successfulProcess);
    }

    /**
     * Valid print or reprint
     *
     * @param isReprint true = reprint, false = print
     * @return word print or reprint
     */
    public static String getPrintOrReprint(Boolean isReprint) {
        if (isReprint.equals(Boolean.TRUE)) {
            return REPRINT;
        }
        return PRINT;
    }

    /**
     * Gets the logs corresponding to an impression or reprint of invoice
     *
     * @param printingRequestDTO request print order
     * @param successfulProcess  printing successful or failed
     * @return logs printing
     */
    public static String getInvoiceLogMessage(
            PrintingRequestDTO printingRequestDTO,
            Boolean successfulProcess) {
        String messageKey = "";
        if (successfulProcess.equals(Boolean.TRUE)) {
            if (printingRequestDTO.getIsReprint().equals(Boolean.TRUE)) {
                messageKey = REPRINT_INVOICE_TICKET_SUCCESSFULLY.getMessage();
            } else {
                messageKey = PRINT_INVOICE_TICKET_SUCCESSFULLY.getMessage();
            }
        }
        if (successfulProcess.equals(Boolean.FALSE)) {
            if (printingRequestDTO.getIsReprint().equals(Boolean.TRUE)) {
                messageKey = REPRINT_INVOICE_TICKET_FAILED.getMessage();
            } else {
                messageKey = PRINT_INVOICE_TICKET_FAILED.getMessage();
            }
        }
        return messageKey;
    }

    /**
     * Gets the logs corresponding to an impression or reprint of commands kitchen
     *
     * @param successfulProcess printing successful or failed
     * @return logs printing
     */
    private static String getKitchenLogMessage(Boolean successfulProcess) {
        if (successfulProcess.equals(Boolean.TRUE)) {
            return REPRINT_KITCHEN_TICKET_SUCCESSFULLY.getMessage();
        }
        return REPRINT_KITCHEN_TICKET_FAILED.getMessage();
    }
}
