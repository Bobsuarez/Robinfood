package com.robinfood.app.enums;

import com.robinfood.core.constants.GlobalConstants;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.Map;

@Slf4j
public enum CountryEnum {

    BRASIL(GlobalConstants.BRASIL_CODE) {

        @Override
        public Double calculeSubtotal ( Map<String,Double> values) {

            log.info("Calculated sub-total Brasil of the product: [{}]",
                    values.get(GlobalConstants.PRODUCT_PRICE));

            return values.get(GlobalConstants.PRODUCT_PRICE);
        }

    },
    COLOMBIA(GlobalConstants.COLOMBIA_CODE),
    MEXICO(GlobalConstants.MEXICO_CODE);

    private final Long code;

    CountryEnum(Long code) {
        this.code = code;
    }

    public Double calculeSubtotal (Map<String,Double> values) {

        log.info("Calculated sub-total of the product Colombia Mexico : [{}]",
                values.get(GlobalConstants.PRODUCT_PRICE));

        return values.get(GlobalConstants.PRODUCT_PRICE) - values.get(GlobalConstants.TOTAL_TAXES);

    }

    public Double calculetotal (Map<String,Double> values) {

        log.info("Calculated sub-total of the product: [{}]",
                values.get(GlobalConstants.PRODUCT_PRICE));

        return values.get(GlobalConstants.PRODUCT_PRICE) - values.get(GlobalConstants.TOTAL_DISCOUNT);

    }

    public static CountryEnum findCountry (Long countryCode) {

        log.info("Find country by countryCode: [{}]",
                countryCode);

        return Arrays.stream(values()).filter(item -> item.code.equals(countryCode))
                .findFirst().orElse( CountryEnum.COLOMBIA);

    }
}
