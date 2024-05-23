package com.robinfood.localprinterbc.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;
import com.github.anastaciocintra.escpos.barcode.BarCode;
import com.github.anastaciocintra.escpos.barcode.QRCode;
import com.github.anastaciocintra.escpos.image.Bitonal;
import com.github.anastaciocintra.escpos.image.BitonalThreshold;
import com.github.anastaciocintra.escpos.image.EscPosImage;
import com.github.anastaciocintra.escpos.image.GraphicsImageWrapper;
import com.github.anastaciocintra.escpos.image.CoffeeImageImpl;
import com.github.anastaciocintra.escpos.image.BitImageWrapper;
import com.robinfood.localprinterbc.common.ImageCommon;
import com.robinfood.localprinterbc.dtos.invoice.InvoiceDetailDTO;
import com.robinfood.localprinterbc.dtos.orders.ElectronicBillDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderElectronicBillingDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderThirdPartyDTO;
import com.robinfood.localprinterbc.dtos.store.CountryDTO;
import com.robinfood.localprinterbc.dtos.store.RenameStoreDTO;
import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TOTAL_WITCH_40;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TOTAL_WITCH_27;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TEXT_CENTER;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TEXT_CENTER_SIZE_2;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TEXT_CENTER_SIZE_3;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.FONT_SIZE_1;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.FONT_SIZE_2;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.FONT_SIZE_3;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.FONT_SIZE_4;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.LINE_BREAK_1;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.LINE_BREAK_2;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.JUSTIFICATION_CENTER;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.JUSTIFICATION_RIGHT;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.JUSTIFICATION_LEFT;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.LINE_BREAK_NUMBER_1;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.LINE_BREAK_NUMBER_2;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TEXT_BOLD_CENTER;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TEXT_BOLD_RIGHT;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.REGEX_LABEL_AND_VALUE;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TITTLE_BOLD_CENTER_FONT_SIZE_2;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TITTLE_CENTER_FONT_SIZE_2;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.BARCODE;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TEXT_BOLD;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TEXT;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TEXT_RIGHT;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.SEPARATOR;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.SEPARATOR_VALUE;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.QR_CODE;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.QR_CODE_PERSONALIZED_BR;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.IMAGE;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.BARCODE_WIDTH;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.BARCODE_PERSONALIZED_BR;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TEXT_BOLD_LEFT_SIZE_2;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TEXT_BOLD_RIGHT_SIZE_2;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TEXT_LEFT_SIZE_2;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TEXT_LEFT_SIZE_3;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.BARCODE_HEIGHT_100;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TEXT_BOLD_LEFT_SIZE_3;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TEXT_BOLD_RIGHT_SIZE_3;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TEXT_RIGHT_SIZE_2;
import static com.robinfood.localprinterbc.configs.constants.EscPosCostants.TEXT_RIGHT_SIZE_3;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_LOGO;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_HAS_CO2;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_ORDER_TYPE;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_SUB_TOTAL;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_DISCOUNT;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_TAX;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_TOTAL;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_VALUE_PAYMENT_METHODS;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_QR_FOUND;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_VALUE_QR_CODE;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_DATETIME;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_TOTAL_FINISH;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.LABEL_SUB_TOTAL;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.LABEL_DISCOUNT;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.LABEL_TOTAL;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.LABEL_TAX;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.VALUE_QR_CODE;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.VALUE_ORDER_INTERNAL;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.VALUE_ORDER;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_VALUE_CO2;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.VALUE_COMPENSATION_CO2;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.BRAZIL;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.LABEL_SUB_TOTAL_BR;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.LABEL_DISCOUNT_BR;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.FILE_PATH_BAR_CODE;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.FILE_PATH_QR;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.VALUE_ORDER_INTERNAL_BR;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_SHOW_QR;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_QR_COL;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_FULL_NAME_FE;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_CUDE_FE;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_DOCUMENT_NUMBER_FE;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_EMAIL_FE;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.KEY_PHONE_FE;

@Slf4j
public class CommandsEscPos {

    private static final Pattern pattern = Pattern.compile(REGEX_LABEL_AND_VALUE);

    private static Style buildStyle(int fontSize,
                                    String justification,
                                    Boolean bold){
        Style style = new Style();
        buildFontSize(style, fontSize);

        buildJustificationText(justification, style);

        if(Boolean.TRUE.equals(bold)){
            style.setBold(true);
        }

        return style;
    }

    private static void buildJustificationText(String justification, Style style) {
        switch(justification) {
            case JUSTIFICATION_CENTER:
                style.setJustification(EscPosConst.Justification.Center);
                break;
            case JUSTIFICATION_RIGHT:
                style.setJustification(EscPosConst.Justification.Right);
                break;
            default:
                style.setJustification(EscPosConst.Justification.Left_Default);
                break;
        }
    }

    private static void buildFontSize(Style style, int fontSize) {
        switch(fontSize) {
            case FONT_SIZE_1:
                style.setFontSize(Style.FontSize._1, Style.FontSize._1);
                break;
            case FONT_SIZE_2:
                style.setFontSize(Style.FontSize._2, Style.FontSize._2);
                break;
            case FONT_SIZE_3:
                style.setFontSize(Style.FontSize._3, Style.FontSize._3);
                break;
            default:
                style.setFontSize(Style.FontSize._4, Style.FontSize._4);
                break;
        }
    }




    public static void buildEscPosPrint(EscPos escpos, String label, String value, Long orderId) throws RuntimeException  {

            Map<String, Runnable> operations = new HashMap<>();

            operations.put(TITTLE_BOLD_CENTER_FONT_SIZE_2, () ->
                    getWriteLFWitchStyle(escpos, value, FONT_SIZE_2, JUSTIFICATION_CENTER, true));

            operations.put(TITTLE_CENTER_FONT_SIZE_2, () ->
                    getWriteLFWitchStyle(escpos, value, FONT_SIZE_2, JUSTIFICATION_CENTER, false));

            operations.put(LINE_BREAK_1, () -> getFeed(escpos, LINE_BREAK_NUMBER_1));

            operations.put(LINE_BREAK_2, () -> getFeed(escpos, LINE_BREAK_NUMBER_2));

            operations.put(BARCODE, () -> getBarcode(escpos, value));

            operations.put(BARCODE_PERSONALIZED_BR, () -> getBarcodePersonalizedBR(escpos, value, orderId));

            operations.put(TEXT_CENTER_SIZE_2, () ->
                    getWriteLFWitchStyle(escpos, value, FONT_SIZE_2, JUSTIFICATION_CENTER, false));

            operations.put(TEXT_CENTER_SIZE_3, () ->
                    getWriteLFWitchStyle(escpos, value, FONT_SIZE_3, JUSTIFICATION_CENTER, false));

            operations.put(TEXT_BOLD, () ->
                    getWriteLFWitchStyle(escpos, value, FONT_SIZE_1, JUSTIFICATION_LEFT, true));

            operations.put(TEXT_BOLD_LEFT_SIZE_2, () ->
                getWriteLFWitchStyle(escpos, value, FONT_SIZE_2, JUSTIFICATION_LEFT, true));

            operations.put(TEXT_BOLD_LEFT_SIZE_3, () ->
                getWriteLFWitchStyle(escpos, value, FONT_SIZE_3, JUSTIFICATION_LEFT, true));

            operations.put(TEXT_BOLD_RIGHT, () ->
                    getWriteLFWitchStyle(escpos, value, FONT_SIZE_1, JUSTIFICATION_RIGHT, true));

            operations.put(TEXT_BOLD_RIGHT_SIZE_2, () ->
                getWriteLFWitchStyle(escpos, value, FONT_SIZE_2, JUSTIFICATION_RIGHT, true));

            operations.put(TEXT_BOLD_RIGHT_SIZE_3, () ->
                getWriteLFWitchStyle(escpos, value, FONT_SIZE_3, JUSTIFICATION_RIGHT, true));

            operations.put(TEXT_BOLD_CENTER, () ->
                getWriteLFWitchStyle(escpos, value, FONT_SIZE_1, JUSTIFICATION_CENTER, true));

            operations.put(TEXT, () ->
                    getWriteLFWitchStyle(escpos, value, FONT_SIZE_1, JUSTIFICATION_LEFT, false));

            operations.put(TEXT_LEFT_SIZE_2, () ->
                getWriteLFWitchStyle(escpos, value, FONT_SIZE_2, JUSTIFICATION_LEFT, false));

            operations.put(TEXT_LEFT_SIZE_3, () ->
                getWriteLFWitchStyle(escpos, value, FONT_SIZE_3, JUSTIFICATION_LEFT, false));

            operations.put(TEXT_CENTER, () ->
                    getWriteLFWitchStyle(escpos, value, FONT_SIZE_1, JUSTIFICATION_CENTER, false));

            operations.put(TEXT_RIGHT, () ->
                    getWriteLFWitchStyle(escpos, value, FONT_SIZE_1, JUSTIFICATION_RIGHT, false));

            operations.put(TEXT_RIGHT_SIZE_2, () ->
                getWriteLFWitchStyle(escpos, value, FONT_SIZE_2, JUSTIFICATION_RIGHT, false));

            operations.put(TEXT_RIGHT_SIZE_3, () ->
                getWriteLFWitchStyle(escpos, value, FONT_SIZE_3, JUSTIFICATION_RIGHT, false));

            operations.put(SEPARATOR, () -> getSeparator(escpos, SEPARATOR_VALUE));

            operations.put(QR_CODE, () -> getQrCode(escpos, value));

            operations.put(QR_CODE_PERSONALIZED_BR, () -> getQrPersonalizedBR(escpos, value, orderId));

            operations.put(IMAGE, () -> getImage(escpos, ImageCommon.BrandEnum.findByName(value)));

            Runnable defaultOperation = () ->
                    getWriteLFWitchStyle(escpos, value, FONT_SIZE_4, JUSTIFICATION_RIGHT, false);


            operations.getOrDefault(label.trim(), defaultOperation).run();

    }

    /**
     * El texto aparece en la parte inferior
     * @param escpos
     * @param value
     * @throws RuntimeException
     */
    private static void getBarcode(EscPos escpos, String value) throws RuntimeException{
        try{
            BarCode barcode = new BarCode();
            barcode.setHRIPosition(BarCode.BarCodeHRIPosition.BelowBarCode);
            barcode.setJustification(EscPosConst.Justification.Center);
            barcode.setBarCodeSize(BARCODE_WIDTH, BARCODE_HEIGHT_100);
            escpos.feed(LINE_BREAK_NUMBER_2);
            escpos.write(barcode, value.trim());
        }catch (IllegalArgumentException e){
            throw  new RuntimeException(e);
        } catch (IOException e){
            throw  new RuntimeException(e);
        }
    }


    /**
     * El texto aparece en la parte superior
     * @param escpos
     * @param value
     * @throws RuntimeException
     */
    private static void getBarcodePersonalizedBR(EscPos escpos, String value, Long orderId) throws RuntimeException {
        String nameBarCode = "bar_code_"+orderId+".png";
        try{

            GenerateBarCode.buildBarCode(value,nameBarCode);

            Bitonal algorithm = new BitonalThreshold(127);
            BufferedImage  githubBufferedImage = GenerateBarCode.getImage(nameBarCode);
            EscPosImage escposImage = new EscPosImage(new CoffeeImageImpl(githubBufferedImage), algorithm);

            BitImageWrapper imageWrapper = new BitImageWrapper();
            escpos.write(imageWrapper, escposImage);

        }catch (IllegalArgumentException e){
            throw  new RuntimeException(e);
        } catch (IOException e){
            throw  new RuntimeException(e);
        } finally {
            GenerateBarCode.deleteImageBarCode(FILE_PATH_BAR_CODE+nameBarCode);
        }
    }

    private static void getQrCode(EscPos escpos, String value) throws RuntimeException{
        try{
            String decodedUrl = URLDecoder.decode(value, "UTF-8");
            QRCode qrcode = new QRCode();
            qrcode.setSize(4);
            qrcode.setJustification(EscPosConst.Justification.Center);
            escpos.write(qrcode, decodedUrl);
        } catch (IOException e){
            throw  new RuntimeException(e);
        }
    }

    private static void getQrPersonalizedBR(EscPos escpos, String value, Long orderId) throws RuntimeException{
        String nameQr = "qr_code_"+orderId+".png";
        try{
            GenerateQR.generateQRCode(value, nameQr , 350, 350);
            Bitonal algorithm = new BitonalThreshold(127);
            BufferedImage  githubBufferedImage = GenerateQR.getImage(nameQr);
            EscPosImage escposImage = new EscPosImage(new CoffeeImageImpl(githubBufferedImage), algorithm);

            BitImageWrapper imageWrapper = new BitImageWrapper();
            imageWrapper.setJustification(EscPosConst.Justification.Center);
            escpos.write(imageWrapper, escposImage);

        } catch (IOException e){
            throw  new RuntimeException(e);
        } finally {
            GenerateQR.deleteImageQR(FILE_PATH_QR+nameQr);
        }
    }



    private static void getImage(EscPos escpos, ImageCommon.BrandEnum brandEnum)throws RuntimeException{
        try {
            Bitonal algorithm = new BitonalThreshold(127);
            BufferedImage githubBufferedImage = ImageCommon.getImage(brandEnum);
            EscPosImage escposImage = null;

            escposImage =  new EscPosImage(new CoffeeImageImpl(githubBufferedImage), algorithm);



            GraphicsImageWrapper imageWrapper = new GraphicsImageWrapper();

            imageWrapper.setJustification(EscPosConst.Justification.Center);
            escpos.write(imageWrapper, escposImage);

        } catch (IOException e) {
            throw  new RuntimeException(e);
        } catch (RuntimeException e) {
            throw  new RuntimeException(e);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    private static void getWriteLFWitchStyle(EscPos escpos,
                                               String value,
                                               int fontSize,
                                               String justification,
                                               boolean bold) throws RuntimeException {
        try {
            escpos.writeLF(buildStyle(fontSize, justification, bold), value);
        } catch (UnsupportedEncodingException e) {
            throw  new RuntimeException(e);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    private static void getFeed(EscPos escpos, Integer lineBreakNumber)throws RuntimeException  {
        try {
             escpos.feed(lineBreakNumber);

        }catch (IllegalArgumentException e){
            throw  new RuntimeException(e);
        } catch (IOException e){
            throw  new RuntimeException(e);
        }
    }

    private static void getSeparator(EscPos escpos, String separator) throws RuntimeException {
        try {
             escpos.writeLF(separator);
        } catch (UnsupportedEncodingException e) {
            throw  new RuntimeException(e);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    public static  Map<String, String>  getLabelAndValue(String input) {
        Matcher matcher = pattern.matcher(input);

        boolean find = false;
        String label =null;
        while (matcher.find()) {
            String labelFound = matcher.group();
            if(!find){
                label = labelFound;
                find = true;
            }

            input = input.replace(labelFound, "");
        }
        Map<String, String>data = new HashMap<>();
        data.put("label", label);
        data.put("value", input);

        return data;
    }

    public static Map<String, Object> buildInformationData(OrderDetailDTO orderDetailDTO,
                                                            BigDecimal totalValue,
                                                            ImageCommon.BrandEnum brandEnum,
                                                            InvoiceDetailDTO invoiceDetailDTO,
                                                            String jsonStores)
    {
        renameStore(invoiceDetailDTO, jsonStores, orderDetailDTO.getStoreId());

        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> data = oMapper.convertValue(invoiceDetailDTO, Map.class);

        String orderType = VALUE_ORDER_INTERNAL + orderDetailDTO.getUid()
                +" ("+orderDetailDTO.getOrderNumber()+")";

        if(Boolean.TRUE.equals(orderDetailDTO.getOrderIsIntegration())){
            orderType = VALUE_ORDER + orderDetailDTO.getOrderIntegrationId()
                    +" ("+orderDetailDTO.getOrderIntegrationCode()+")";
        }

        data.put(KEY_HAS_CO2, Boolean.FALSE);

        if (orderDetailDTO.getCo2Total().compareTo(BigDecimal.ZERO) > 0) {
            data.put(KEY_HAS_CO2, Boolean.TRUE);

            data.put(KEY_VALUE_CO2,buildTextWithFillPoints(VALUE_COMPENSATION_CO2,
                    InvoiceUtil.formatNumberBigDecimalNew(orderDetailDTO.getCo2Total()), TOTAL_WITCH_40));
        }
        data.put(KEY_SHOW_QR, Boolean.TRUE);

        if(brandEnum.equals(ImageCommon.BrandEnum.HOGAO) ||
           brandEnum.equals(ImageCommon.BrandEnum.BOWLO_BR) ||
           brandEnum.equals(ImageCommon.BrandEnum.ABUNDANTE) ||
           brandEnum.equals(ImageCommon.BrandEnum.ABUNDANTE_TURBO)){

            data.put(KEY_SHOW_QR, Boolean.FALSE);

        }

        data.put(KEY_LOGO, brandEnum.getName());

        data.put(KEY_SUB_TOTAL, buildTextWithFillPoints(LABEL_SUB_TOTAL,
                InvoiceUtil.formatNumberDoubleNew(orderDetailDTO.getSubtotal()), TOTAL_WITCH_40));
        data.put(KEY_DISCOUNT, buildTextWithFillPoints(LABEL_DISCOUNT,
                InvoiceUtil.formatNumberBigDecimalNew(orderDetailDTO.getDiscount()), TOTAL_WITCH_40));

        Optional<CountryDTO> country = Optional.ofNullable(invoiceDetailDTO.getStore().getCompany().getCountry());

        if (country.map(CountryDTO::getId).orElse(0L).equals(BRAZIL)) {
            data.put(KEY_SUB_TOTAL, buildTextWithFillPoints(LABEL_SUB_TOTAL_BR,
                    InvoiceUtil.formatNumberDoubleNew(orderDetailDTO.getSubtotal()), TOTAL_WITCH_40));
            data.put(KEY_DISCOUNT, buildTextWithFillPoints(LABEL_DISCOUNT_BR,
                    InvoiceUtil.formatNumberBigDecimalNew(orderDetailDTO.getDiscount()), TOTAL_WITCH_40));
            orderType = VALUE_ORDER_INTERNAL_BR + orderDetailDTO.getUid()
                    +" ("+orderDetailDTO.getOrderNumber()+")";
        }
        data.put(KEY_ORDER_TYPE, orderType);
        data.put(KEY_TAX, buildTextWithFillPoints(LABEL_TAX,
                InvoiceUtil.formatNumberDoubleNew(orderDetailDTO.getTax()), TOTAL_WITCH_40));
        data.put(KEY_TOTAL, buildTextWithFillPoints(LABEL_TOTAL,
                InvoiceUtil.formatNumberBigDecimalNew(orderDetailDTO.getTotal()), TOTAL_WITCH_40));
        data.put(KEY_TOTAL_FINISH, buildTextWithFillPoints(LABEL_TOTAL,
                orderDetailDTO.getTotal(), TOTAL_WITCH_40));
        data.put(KEY_VALUE_PAYMENT_METHODS, buildTextWithFillPoints(LABEL_TOTAL,
                InvoiceUtil.formatNumberBigDecimalNew(totalValue), TOTAL_WITCH_27));
        data.put(KEY_VALUE_QR_CODE, VALUE_QR_CODE);
        data.put(KEY_DATETIME, InvoiceUtil.getCurrentDateTime());

        Boolean qrFound = Boolean.TRUE;
        if(Objects.isNull(orderDetailDTO.getElectronicBill())||
                Objects.isNull(orderDetailDTO.getElectronicBill().getOrderElectronicBilling())||
                Objects.isNull(orderDetailDTO.getElectronicBill().getOrderElectronicBilling().getQR())){
            qrFound = Boolean.FALSE;
        }

        data.put(KEY_QR_FOUND, qrFound);

        String encodedUrl = URLEncoder.encode(
                Optional.ofNullable(orderDetailDTO.getElectronicBill())
                        .map(ElectronicBillDTO::getOrderElectronicBilling)
                        .map(OrderElectronicBillingDTO::getQR)
                        .orElse(""),
                StandardCharsets.UTF_8
        );

        data.put(KEY_QR_COL, encodedUrl);

        String fullNameFE = Optional.ofNullable(orderDetailDTO.getElectronicBill())
                .map(ElectronicBillDTO::getOrderThirdParty)
                .map(OrderThirdPartyDTO::getFullName)
                .orElse("--");

        data.put(KEY_FULL_NAME_FE, fullNameFE);

        String documentNumberFE = Optional.ofNullable(orderDetailDTO.getElectronicBill())
                .map(ElectronicBillDTO::getOrderThirdParty)
                .map(OrderThirdPartyDTO::getDocumentNumber)
                .orElse("--");

        data.put(KEY_DOCUMENT_NUMBER_FE, documentNumberFE);

        String phoneFE = Optional.ofNullable(orderDetailDTO.getElectronicBill())
                .map(ElectronicBillDTO::getOrderThirdParty)
                .map(OrderThirdPartyDTO::getPhone)
                .orElse("--");

        data.put(KEY_PHONE_FE, phoneFE);

        String emailFE = Optional.ofNullable(orderDetailDTO.getElectronicBill())
                .map(ElectronicBillDTO::getOrderThirdParty)
                .map(OrderThirdPartyDTO::getEmail)
                .orElse("--");

        data.put(KEY_EMAIL_FE, emailFE);

        String cudeFE = Optional.ofNullable(orderDetailDTO.getElectronicBill())
                .map(ElectronicBillDTO::getOrderElectronicBilling)
                .map(OrderElectronicBillingDTO::getCUDE)
                .orElse("--");

        data.put(KEY_CUDE_FE, cudeFE);

        return data;
    }

    private static String buildTextWithFillPoints(String label, Object value, int totalWidth) {
        StringBuilder sb = new StringBuilder();
        sb.append(label);

        String valueString = "$" + value;
        sb.append(".".repeat(Math.max(0, totalWidth - (label.length() + valueString.length()))));
        sb.append(valueString);

        return sb.toString();
    }

    private static void renameStore(InvoiceDetailDTO invoiceDetailDTO, String jsonStores, Long storeId) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<RenameStoreDTO> listStores = Arrays.asList(objectMapper.readValue(jsonStores, RenameStoreDTO[].class));

            Optional<RenameStoreDTO> renameStoreDTO = getStoreToRename(listStores, storeId);

            if(renameStoreDTO.isPresent()){
                invoiceDetailDTO.getStore().setName(renameStoreDTO.get().getNewStoreName());
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static Optional<RenameStoreDTO> getStoreToRename(List<RenameStoreDTO> listStores, Long storeId) {
        return listStores.stream()
                .filter(data -> data.getStoreId() == storeId)
                .findFirst();
    }
}
