package com.robinfood.localserver.commons.utilities;

import com.robinfood.localserver.commons.dtos.http.PrintingRequestDTO;
import com.robinfood.localserver.commons.mocks.dtos.PrintingRequestDTOMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.robinfood.localserver.commons.constants.GlobalConstants.PRINT;
import static com.robinfood.localserver.commons.constants.GlobalConstants.REPRINT;

@ExtendWith(MockitoExtension.class)
class PrintLogsTest {
    final PrintingRequestDTO printingRequestDTO = new PrintingRequestDTOMock().getDefaultData();

    @Test
    void test_PrintLogs_GetPrinterByName_Response_Reprint() {
        String print = PrintLogs.getPrintOrReprint(Boolean.FALSE);
        Assertions.assertEquals(PRINT, print);
    }

    @Test
    void test_PrintLogs_GetPrinterByName_Response_Print() {
        String print = PrintLogs.getPrintOrReprint(Boolean.TRUE);
        Assertions.assertEquals(REPRINT, print);
    }

    @Test
    void test_PrintLogs_getInvoiceLogMessage_RePrint_Success_Response_Logs() {
        printingRequestDTO.setIsReprint(Boolean.TRUE);
        String messageKey = PrintLogs.getInvoiceLogMessage(printingRequestDTO, Boolean.TRUE);
        Assertions.assertEquals("054 reprinted invoice ticket Successfully", messageKey);
    }

    @Test
    void test_PrintLogs_getInvoiceLogMessage_Print_Success_Response_Logs() {
        printingRequestDTO.setIsReprint(Boolean.FALSE);
        String messageKey = PrintLogs.getInvoiceLogMessage(printingRequestDTO, Boolean.TRUE);
        Assertions.assertEquals("051 printed invoice ticket Successfully", messageKey);
    }

    @Test
    void test_PrintLogs_getInvoiceLogMessage_RePrint_Fail_Response_Logs() {
        printingRequestDTO.setIsReprint(Boolean.TRUE);
        String messageKey = PrintLogs.getInvoiceLogMessage(printingRequestDTO, Boolean.FALSE);
        Assertions.assertEquals("080 reprint invoice ticket failed", messageKey);
    }

    @Test
    void test_PrintLogs_getInvoiceLogMessage_Print_Fail_Response_Logs() {
        printingRequestDTO.setIsReprint(Boolean.FALSE);
        String messageKey = PrintLogs.getInvoiceLogMessage(printingRequestDTO, Boolean.FALSE);
        Assertions.assertEquals("076 printed invoice ticket failed", messageKey);
    }

    @Test
    void test_PrintLogs_orchestratorPrintLogsInvoiceOrKitchen_Print_Fail_Response_Logs() {
        printingRequestDTO.setIsReprint(Boolean.FALSE);
        String messageKey = PrintLogs.orchestratorPrintLogsInvoiceOrKitchen(printingRequestDTO, Boolean.FALSE,
                Boolean.TRUE);
        Assertions.assertEquals("076 printed invoice ticket failed", messageKey);
    }

    @Test
    void test_PrintLogs_orchestratorPrintLogsInvoiceOrKitchen_Print_Fail_Kitchen_Response_Logs() {
        printingRequestDTO.setIsReprint(Boolean.TRUE);
        String messageKey = PrintLogs.orchestratorPrintLogsInvoiceOrKitchen(printingRequestDTO, Boolean.FALSE,
                Boolean.FALSE);
        Assertions.assertEquals("079 reprinted kitchen ticket failed", messageKey);
    }

    @Test
    void test_PrintLogs_orchestratorPrintLogsInvoiceOrKitchen_Print_Success_Kitchen_Response_Logs() {
        printingRequestDTO.setIsReprint(Boolean.TRUE);
        String messageKey = PrintLogs.orchestratorPrintLogsInvoiceOrKitchen(printingRequestDTO, Boolean.TRUE,
                Boolean.FALSE);
        Assertions.assertEquals("055 reprinted kitchen ticket Successfully", messageKey);
    }
}
