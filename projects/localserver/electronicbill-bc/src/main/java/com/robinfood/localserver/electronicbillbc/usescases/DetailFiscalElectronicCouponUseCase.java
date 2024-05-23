package com.robinfood.localserver.electronicbillbc.usescases;

import static com.robinfood.localserver.commons.constants.SefazConstants.ROUNDING_SCALE;
import static com.robinfood.localserver.commons.constants.SefazConstants.VALUE_PATTERN;
import static com.robinfood.localserver.electronicbillbc.configs.constants.FiscalElectronicCouponConstants
        .SLIDE_RULE_VALUE;
import static com.robinfood.localserver.electronicbillbc.configs.constants.FiscalElectronicCouponConstants
        .TAG_DISCOUNT_PRODUCT_NAME;
import static com.robinfood.localserver.electronicbillbc.configs.constants.FiscalElectronicCouponConstants.TAG_NCM;
import static com.robinfood.localserver.electronicbillbc.configs.constants.FiscalElectronicCouponConstants
        .TAG_PRODUCT_ID;
import static com.robinfood.localserver.electronicbillbc.configs.constants.FiscalElectronicCouponConstants
        .TAG_PRODUCT_NAME;
import static com.robinfood.localserver.electronicbillbc.configs.constants.FiscalElectronicCouponConstants
        .TAG_QUANTITY_NAME;
import static com.robinfood.localserver.electronicbillbc.configs.constants.FiscalElectronicCouponConstants
        .TAG_SLIDE_RULE;
import static com.robinfood.localserver.electronicbillbc.configs.constants.FiscalElectronicCouponConstants
        .TAG_TAX_CODE_OPERATION_NAME;
import static com.robinfood.localserver.electronicbillbc.configs.constants.FiscalElectronicCouponConstants
        .TAG_UNIT_NAME;
import static com.robinfood.localserver.electronicbillbc.configs.constants.FiscalElectronicCouponConstants
        .TAG_VALUE_UNITY_NAME;
import static com.robinfood.localserver.electronicbillbc.configs.constants.FiscalElectronicCouponConstants
        .UNITY_VALUE;

import com.robinfood.localserver.commons.dtos.electronicbill.DetDTO;
import com.robinfood.localserver.commons.dtos.orders.billing.OrderBillingProductDTO;
import com.robinfood.localserver.commons.dtos.orders.billing.OrderBillingTaxesDTO;
import com.robinfood.localserver.commons.dtos.orders.billing.TreasuryTaxesDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament.ParameterDTO;
import com.robinfood.localserver.commons.exceptions.IncompleteDataException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Slf4j
public class DetailFiscalElectronicCouponUseCase implements IDetailFiscalElectronicCouponUseCase {

    @SneakyThrows
    @Override
    public List<DetDTO> invoke(List<OrderBillingProductDTO> listOrderBillingProductDTO,
                               BigDecimal totalDeductionAndDiscountOrder) {

        List<DetDTO> listDet = new ArrayList<>();
        BigDecimal totalDeductionAndDiscountOrderProduct = BigDecimal.ZERO;
        int count = 0;

        DecimalFormatSymbols decimalFormatSymbolsSeparator = new DecimalFormatSymbols();
        decimalFormatSymbolsSeparator.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat(VALUE_PATTERN, decimalFormatSymbolsSeparator);

        for (OrderBillingProductDTO productDTO : listOrderBillingProductDTO) {

            if (productDTO.getTreasuryCategory().getName() == null) {

                String message = "TreasuryCategory is null or Empty";

                log.error(message);

                throw new IncompleteDataException(message);
            }

            ParameterDTO treasuryCategory = productDTO.getTreasuryCategory().getParameters().get(0);

            count++;

            Map<String, String> product = new TreeMap<>();

            product.put(TAG_PRODUCT_ID, productDTO.getId().toString());
            product.put(TAG_NCM, productDTO.getNcm());
            product.put(TAG_PRODUCT_NAME, productDTO.getName());
            product.put(TAG_TAX_CODE_OPERATION_NAME, treasuryCategory.getValue());
            product.put(TAG_UNIT_NAME, UNITY_VALUE);
            product.put(TAG_QUANTITY_NAME, productDTO.getQuantity().toString());
            product.put(TAG_SLIDE_RULE, SLIDE_RULE_VALUE);
            product.put(TAG_VALUE_UNITY_NAME, decimalFormat.format(productDTO.getUnitPrice()));

            BigDecimal totalDiscountAndDeductionsProducts = BigDecimal.ZERO;

            totalDiscountAndDeductionsProducts = totalDiscountAndDeductionsProducts
                    .add(productDTO.getDiscount()
                            .add(productDTO.getDeduction())).setScale(
                            ROUNDING_SCALE, RoundingMode.HALF_DOWN);

            if (totalDiscountAndDeductionsProducts.compareTo(BigDecimal.ZERO) > 0) {

                totalDeductionAndDiscountOrderProduct = totalDeductionAndDiscountOrderProduct
                        .add(totalDiscountAndDeductionsProducts);

                product.put(
                        TAG_DISCOUNT_PRODUCT_NAME, totalDiscountAndDeductionsProducts.toString());
            }

            if (CollectionUtils.isEmpty(productDTO.getTaxes())) {

                String message = "taxes of product " + productDTO.getId() + " is null or Empty";

                log.error(message);

                throw new IncompleteDataException(message);
            }

            Map<String, Object> taxes = new TreeMap<>();

            for (OrderBillingTaxesDTO orderBillingTaxesDTO : productDTO.getTaxes()) {

                if (CollectionUtils.isEmpty(orderBillingTaxesDTO.getTreasuryTaxes())) {

                    String message = "treasuryTaxes of product " + productDTO.getId() + " is null or Empty";

                    log.error(message);

                    throw new IncompleteDataException(message);
                }

                Map<String, Object> treasuryTax = new TreeMap<>();

                for (TreasuryTaxesDTO treasuryTaxesDTO : orderBillingTaxesDTO.getTreasuryTaxes()) {

                    Map<String, String> paramsTax = treasuryTaxesDTO.getParameters().stream().collect(
                            Collectors.toMap(
                                    ParameterDTO::getName, ParameterDTO::getValue
                            )
                    );

                    treasuryTax.put(treasuryTaxesDTO.getName(), paramsTax);
                }

                taxes.put(orderBillingTaxesDTO.getTaxTypeName(), treasuryTax);
            }

            listDet.add(DetDTO.builder().prod(product).imposto(taxes).numItem(String.valueOf(count)).build());
        }

        BigDecimal differenceAmount = totalDeductionAndDiscountOrder.subtract(totalDeductionAndDiscountOrderProduct);

        roundAmount(differenceAmount, listDet);

        return listDet;
    }

    public void roundAmount(BigDecimal differenceAmount, List<DetDTO> listDet) {

        if (differenceAmount.compareTo(BigDecimal.ZERO) > 0) {
            listDet.stream()
                    .filter(detDTO -> detDTO.getProd().containsKey(TAG_DISCOUNT_PRODUCT_NAME))
                    .findFirst()
                    .map(detDTO -> detDTO.getProd()
                            .put(
                                    TAG_DISCOUNT_PRODUCT_NAME,
                                    BigDecimal.valueOf(Double.parseDouble(detDTO.getProd()
                                            .get(TAG_DISCOUNT_PRODUCT_NAME))).add(differenceAmount).toString())
                    );
        }
    }
}
