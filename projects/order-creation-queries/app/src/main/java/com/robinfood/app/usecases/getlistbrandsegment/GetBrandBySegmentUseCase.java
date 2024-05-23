package com.robinfood.app.usecases.getlistbrandsegment;

import com.robinfood.core.dtos.configuration.BrandDTO;
import com.robinfood.core.dtos.report.salebysegment.BrandSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.response.BrandsDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.ItemDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.OrdersDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.PercentageSalesDifferenceDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.TotalDTOResponse;
import com.robinfood.core.mappers.report.salesegment.BrandDtoToBrandResponseMappers;
import com.robinfood.core.mappers.report.salesegment.ItemResponseMappers;
import com.robinfood.core.mappers.report.salesegment.OrdersSegmentToOrdersResponseMappers;
import com.robinfood.core.mappers.report.salesegment.PercentageToResponseMappers;
import com.robinfood.core.mappers.report.salesegment.TotalDTOResponseMappers;
import com.robinfood.core.mappers.report.salesegment.ValueAWeekBeforeToResponseMappers;
import com.robinfood.core.mappers.report.salesegment.ValueCurrentToResponseMappers;
import com.robinfood.core.utilities.CalculatorPercentageUtil;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_FALSE_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;

@Service
public class GetBrandBySegmentUseCase implements IGetBrandBySegmentUseCase {

    @Override
    public BrandsDTOResponse invoke(
            String currencyType,
            List<BrandSegmentDTO> brandSegmentDTOS,
            List<BrandDTO> brandDTOList
    ) {

        return BrandDtoToBrandResponseMappers.saleSegmentToResponse(
                toBuildListItems(brandDTOList, brandSegmentDTOS, currencyType),
                toBuildTotal(brandSegmentDTOS, currencyType));
    }

    private List<ItemDTOResponse> toBuildListItems(
            List<BrandDTO> brandDTOList,
            List<BrandSegmentDTO> brandSegmentDTOS,
            String currencyType
    ) {
        return brandSegmentDTOS.stream()
                .map((BrandSegmentDTO brand) -> {

                    final String nameBrand = getBrandName(brand.getId(), brandDTOList);
                    final OrdersDTOResponse orders = OrdersSegmentToOrdersResponseMappers
                            .ordersResponse(brand.getOrders());

                    return ItemResponseMappers
                            .itemResponseMappers(currencyType, brand.getId(), nameBrand, orders);

                }).collect(Collectors.toList());
    }

    private TotalDTOResponse toBuildTotal(List<BrandSegmentDTO> brandSegmentDTOS, String currencyType) {

        final BigDecimal sumResultSalesCurrent = sumValueSales(brandSegmentDTOS, DEFAULT_BOOLEAN_FALSE_VALUE);
        final BigDecimal sumResultSaleAWeekBefore = sumValueSales(brandSegmentDTOS, DEFAULT_BOOLEAN_TRUE_VALUE);

        return TotalDTOResponseMappers.totalToResponse(
                currencyType,
                percentageValueSalesCurrent(sumResultSaleAWeekBefore, sumResultSalesCurrent),
                ValueAWeekBeforeToResponseMappers.valueAssignment(sumResultSaleAWeekBefore),
                ValueCurrentToResponseMappers.valueAssignment(sumResultSalesCurrent)
        );
    }

    @NotNull
    private BigDecimal sumValueSales(List<BrandSegmentDTO> brandSegmentDTO, boolean isSalesAWeekBefore) {

        // Return sum sales a week before
        if (isSalesAWeekBefore) {
            return brandSegmentDTO
                    .stream()
                    .map(convertTotal -> convertTotal.getOrders().getSalesAWeekBefore().getValue())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        // Return sum sales current day
        return brandSegmentDTO
                .stream()
                .map(convertTotal -> convertTotal.getOrders().getSalesCurrent().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @NotNull
    private PercentageSalesDifferenceDTOResponse percentageValueSalesCurrent(
            BigDecimal valueAWeekBefore,
            BigDecimal valueCurrent
    ) {
        final BigDecimal resultCalculate = CalculatorPercentageUtil
                .getPercentageDifference(valueCurrent, valueAWeekBefore);

        return PercentageToResponseMappers.valueAssignment(resultCalculate);
    }

    private static String getBrandName(Long brandId, List<BrandDTO> listBrandDTO) {

        return Optional.ofNullable(listBrandDTO)
                .orElse(Collections.emptyList())
                .stream()
                .filter(brandsCompanyDTO -> brandsCompanyDTO.getId().equals(brandId))
                .map(BrandDTO::getName)
                .findFirst()
                .orElse(DEFAULT_STRING_VALUE);
    }
}
