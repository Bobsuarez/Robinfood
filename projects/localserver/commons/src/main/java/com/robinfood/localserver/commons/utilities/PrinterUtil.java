package com.robinfood.localserver.commons.utilities;

import com.robinfood.localserver.commons.dtos.printer.PrinterConfigurationDTO;
import com.robinfood.localserver.commons.dtos.printer.PrinterDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Slf4j
public final class PrinterUtil {

    private static final Pattern SPACE_PATTERN = Pattern.compile("\\s");
    private static final String DEFAULT_STRING_VALUE = "";

    private PrinterUtil() {
    }

    /**
     * *
     *
     * @param printerConfiguration Configuration of printer by store
     * @param name                 name pos order
     * @return Ip printer
     */
    public static PrinterDTO getPrinterByName(PrinterConfigurationDTO printerConfiguration, String name) {

        final String formattedName = formatName(name);

        List<PrinterDTO> printers = printerConfiguration.getPrinters();
        return printers.stream()
                .filter(printer -> formatName(printer.getName()).equals(formattedName))
                .findFirst()
                .orElse(null);
    }

    private static String formatName(String name) {
        return name.toUpperCase(Locale.ENGLISH).replaceAll(SPACE_PATTERN.pattern(), DEFAULT_STRING_VALUE);
    }
}
