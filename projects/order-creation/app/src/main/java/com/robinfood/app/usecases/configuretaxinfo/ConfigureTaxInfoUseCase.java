package com.robinfood.app.usecases.configuretaxinfo;

import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.transactionrequestdto.DiscountDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDiscountDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductTaxDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.ServiceDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TaxesCountryEnum;
import com.robinfood.core.mappers.DiscountMappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RequiredArgsConstructor
@Component
@RefreshScope
@Slf4j
public class ConfigureTaxInfoUseCase implements IConfigureTaxInfoUseCase {

    @Value("${taxe-service-col}")
    private BigDecimal col;

    @Value("${taxe-service-mex}")
    private BigDecimal mex;

    @Override
    public void invoke(TransactionRequestDTO transactionRequestDTO) {

        for (final OrderDTO orderDTO : transactionRequestDTO.getOrders()) {

            HashMap<String, BigDecimal> configureValues = configureProduct(orderDTO, new HashMap<>());

            configureValues.put("TAX_SERVICE_PERCENTAGE_MEX", mex);

            configureValues.put("TAX_SERVICE_PERCENTAGE_COL", col);

            BigDecimal sumDiscountService = orderDTO.getServices().stream()
                    .map(ServiceDTO::getDiscount).reduce(BigDecimal.ZERO, BigDecimal::add);

            getInfoServices(transactionRequestDTO.getCompany().getId().toString(), orderDTO, configureValues);

            BigDecimal totalTaxesService = orderDTO.getServices().stream()
                    .map(ServiceDTO::getTaxPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal sumProductDiscount = orderDTO
                    .getDiscounts()
                    .stream()
                    .filter(DiscountDTO::getIsProductDiscount)
                    .map(DiscountDTO::getValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal sumDiscountOrderWithoutProductDiscount = orderDTO
                    .getDiscounts()
                    .stream()
                    .filter(item -> !item.getIsProductDiscount())
                    .map(DiscountDTO::getValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal totalDiscount = orderDTO
                    .getDiscounts()
                    .stream()
                    .map(DiscountDTO::getValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            configureValues.put(GlobalConstants.TOTAL_DISCOUNT, sumProductDiscount);

            BigDecimal totalTaxesWithService = configureValues.get(GlobalConstants.TOTAL_TAXES).add(totalTaxesService);

            configureValues.put(GlobalConstants.TOTAL_TAXES, totalTaxesWithService);

            setServiceAsDiscount(orderDTO, sumDiscountService);

            // Only send product discount due to the request is already discount to the total of the order
            BigDecimal subTotal = subTotalCalculation(orderDTO,
                    transactionRequestDTO.getCompany().getId().toString(),
                    configureValues
            );

            orderDTO.setSubtotal(subTotal);
            orderDTO.setTotalTaxes(totalTaxesWithService);
            orderDTO.setTotalDiscount(totalDiscount.add(sumDiscountService));
            orderDTO.setTotal(orderDTO.getTotal().subtract(sumDiscountOrderWithoutProductDiscount));

            log.info("End configuration with values: total consumption Discount : [{}] ," +
                            " total Discount : [{}], total taxes : [{}] , subtotal : [{}] ",
                    totalDiscount, totalDiscount, configureValues.get(GlobalConstants.TOTAL_TAXES), subTotal);
        }
    }

    private HashMap<String, BigDecimal> configureProduct(OrderDTO orderDTO, HashMap<String, BigDecimal> values) {

        BigDecimal totalTaxes = BigDecimal.ZERO;

        for (FinalProductDTO product : orderDTO.getFinalProducts()) {

            BigDecimal totalproductTaxes = product.getTaxes()
                    .stream()
                    .map(FinalProductTaxDTO::getTaxPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            totalTaxes = totalTaxes.add(totalproductTaxes);
            values.put(GlobalConstants.TOTAL_TAXES, totalTaxes);
            configureProductsDiscount(product.getDiscounts(), product, orderDTO);
        }

        return values;
    }

    private void configureProductsDiscount(
            List<FinalProductDiscountDTO> discounts,
            FinalProductDTO product,
            OrderDTO order
    ) {

        for (FinalProductDiscountDTO discount : discounts) {

            if (!discount.getIsProductDiscount().equals(Boolean.TRUE)) {
                break;
            }

            BigDecimal valueDiscount = discount.getValue().multiply(
                    BigDecimal.valueOf(
                            product.getQuantity()));

            order.getDiscounts().stream()
                    .filter(data -> Optional.ofNullable(data.getSKU())
                            .orElse(GlobalConstants.DEFAULT_STRING_VALUE).equals(product.getSku()))
                    .forEach(data -> DiscountMappers.builderDiscountDTO(data, product, valueDiscount));

            if (order.getDiscounts().stream().noneMatch(data -> data.getSKU().equals(product.getSku()))) {
                order.getDiscounts().add(DiscountMappers.builderDiscountDTO(new DiscountDTO(), product, valueDiscount));
            }
        }
    }

    private BigDecimal subTotalCalculation(
            OrderDTO order,
            String country,
            HashMap<String, BigDecimal> values
    ) {

        TaxesCountryEnum countryTax = TaxesCountryEnum.findCountry(country);
        values.put(GlobalConstants.TOTAL_ORDER, order.getTotal());
        return countryTax.calculeSubtotal(values);

    }

    private void getInfoServices(String country, OrderDTO orderDTO, Map<String, BigDecimal> values) {

        TaxesCountryEnum countryTax = TaxesCountryEnum.findCountry(country);

        if (!orderDTO.getServices().isEmpty()) {
            for (ServiceDTO service : orderDTO.getServices()) {
                countryTax.getTaxesServicies(service, values);
            }
        }
    }

    private void setServiceAsDiscount(OrderDTO orderDTO, BigDecimal value) {

        if (!orderDTO.getServices().isEmpty()) {
            orderDTO.getDiscounts().add(new DiscountDTO(
                    null,
                    Boolean.FALSE,
                    Boolean.FALSE,
                    null,
                    value,
                    null,
                    GlobalConstants.DISCOUNT_SERVICE,
                    GlobalConstants.DEFAULT_STRING_VALUE
            ));
        }
    }
}
