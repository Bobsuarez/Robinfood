package com.robinfood.localserver.commons.utilities;

import com.robinfood.localserver.commons.dtos.printer.PrinterConfigurationDTO;
import com.robinfood.localserver.commons.dtos.printer.PrinterDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PrinterUtilTest {

    private final PrinterConfigurationDTO printerConfigurationDTO = PrinterConfigurationDTO
            .builder()
            .printers(List.of(
                    PrinterDTO.builder()
                            .name("Caja 1")
                            .ip("10.0.10.1")
                            .build()

            ))
            .build();

    private final PrinterConfigurationDTO PrintersIsEmpty = PrinterConfigurationDTO
            .builder()
            .printers(new ArrayList<>())
            .build();


    @Test
    void test_PrinterUtil_GetPrinterByName_Response_Ok() {
        PrinterDTO printerDTO = PrinterUtil.getPrinterByName(printerConfigurationDTO, "Caja 1");
        Assertions.assertEquals(printerDTO.getName(), printerConfigurationDTO.getPrinters().get(0).getName());
    }

    @Test
    void test_PrinterUtil_GetPrinterByName_Response_Null() {
        PrinterDTO printerDTO = PrinterUtil.getPrinterByName(PrintersIsEmpty, "Caja 1");
        Assertions.assertNull(printerDTO);
    }
}
