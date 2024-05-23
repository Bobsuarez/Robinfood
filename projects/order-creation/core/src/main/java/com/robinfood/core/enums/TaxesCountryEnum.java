package com.robinfood.core.enums;

import com.robinfood.core.configs.ServicesPropertiesConfig;
import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.transactionrequestdto.ServiceDTO;
import com.robinfood.core.exceptions.TransactionCreationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Map;

import static com.robinfood.core.constants.GlobalConstants.NUMBER_DECIMAL_PLACES;

@Slf4j
public enum TaxesCountryEnum {

    BRASIL(GlobalConstants.TAXES_BRASIL) {

        @Override
        public BigDecimal calculeSubtotal (Map<String,BigDecimal> values) {

            log.info("Calculated sub-total Brasil of the order: [{}]",
                    values.get(GlobalConstants.TOTAL_ORDER));
            return values.get(GlobalConstants.TOTAL_ORDER)
                    .add(values.get(GlobalConstants.TOTAL_DISCOUNT));
        }
    },
    COLOMBIA(GlobalConstants.TAXES_COLOMBIA) {
        public void getTaxesServicies (ServiceDTO serviceDTO,Map<String,BigDecimal> value) {

            calculateservice(serviceDTO,service.getPercentageCalculation(value.get("TAX_SERVICE_PERCENTAGE_COL")),
                    value.get("TAX_SERVICE_PERCENTAGE_COL"));
        }
    } ,
    COLOMBIABOWLO(GlobalConstants.TAXES_COLOMBIABOWLO),
    MEXICOBOWLO(GlobalConstants.TAXES_MEXICOBOWLO),
    MEXICO(GlobalConstants.TAXES_MEXICO) {
        public void  getTaxesServicies (ServiceDTO serviceDTO,Map<String,BigDecimal> value) {
            calculateservice(serviceDTO,service.getPercentageCalculation(value.get("TAX_SERVICE_PERCENTAGE_MEX")),
                    value.get("TAX_SERVICE_PERCENTAGE_MEX"));
        }
    };

    private static ServicesPropertiesConfig service = new ServicesPropertiesConfig();
    private String code;

    TaxesCountryEnum(String code) {
        this.code = code;
    }

    public BigDecimal calculeSubtotal (Map<String,BigDecimal> values) {

        log.info("Calculated sub-total of the order: [{}]",
                values.get(GlobalConstants.TOTAL_ORDER));

        return values.get(GlobalConstants.TOTAL_ORDER)
                .add(values.get(GlobalConstants.TOTAL_DISCOUNT))
                .subtract(values.get(GlobalConstants.TOTAL_TAXES));

    }

    public void calculateservice (ServiceDTO serviceDTO, BigDecimal valuetax, BigDecimal taxpercentage) {

        BigDecimal totalService =  serviceDTO.getValue()
                .subtract(serviceDTO.getDiscount());

        BigDecimal subTotalService = totalService.divide(valuetax
                        ,NUMBER_DECIMAL_PLACES, RoundingMode.FLOOR)
                .add(serviceDTO.getDiscount());


        BigDecimal taxesServices = totalService
                .add(serviceDTO.getDiscount()).subtract(subTotalService);

        serviceDTO.setTaxPrice(taxesServices);

        serviceDTO.setTaxPercentage(taxpercentage);

        serviceDTO.setPriceNt(subTotalService);

        serviceDTO.setTotal(totalService);
    }

    public void  getTaxesServicies (ServiceDTO serviceDTO, Map<String,BigDecimal> value) {
        // Do nothing because is by default.
    }

    public static TaxesCountryEnum findCountry (String countryCode) {

        log.info("Find country by countryCode: [{}]",
                countryCode);
        return Arrays.stream(values()).filter(item -> item.code.equals(countryCode)).findFirst().orElseThrow(() ->
                        new TransactionCreationException(
                                HttpStatus.BAD_REQUEST,
                                "Error bad request parametro company.currency",
                                TransactionCreationErrors.TAXES_BC_ERROR
                        )
                );
    }
}
