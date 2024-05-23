package com.robinfood.localprinterbc.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.TWO;

public class InvoiceUtil {

    public static String getCurrentDateTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        return now.format(formatter);
    }

    public static String formatNumberDouble(Double number){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(number);
    }

    public static String formatNumberBigDecimal(BigDecimal number){
        BigDecimal scaledNumber = number.setScale(TWO, RoundingMode.HALF_UP);
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(scaledNumber);
    }

    public static String formatNumberDoubleNew(Double number) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        return decimalFormat.format(number);
    }

    public static String formatNumberBigDecimalNew(BigDecimal number) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        return decimalFormat.format(number);
    }

    public static double parseDouble(String numberString) {
        // Remove thousands separator
        String cleanedString = numberString.replaceAll("\\.", "");
        // Replace decimal separator with dot
        cleanedString = cleanedString.replace(",", ".");
        // Parse as double
        return Double.parseDouble(cleanedString);
    }

    public static String formatDoubleToString(Double value){
        String taxConvert = null;
        if(Objects.nonNull(value)){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            taxConvert = decimalFormat.format(value);
        }
        return taxConvert;
    }
}
