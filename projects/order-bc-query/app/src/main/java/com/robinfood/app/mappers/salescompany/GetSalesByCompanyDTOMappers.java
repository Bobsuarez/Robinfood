package com.robinfood.app.mappers.salescompany;

import com.robinfood.core.dtos.report.sale.OrdersSalesDTO;
import com.robinfood.core.dtos.report.sale.SalesAWeekBeforeDTO;
import com.robinfood.core.dtos.report.sale.SalesCurrentDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class GetSalesByCompanyDTOMappers {

    public static OrdersSalesDTO buildSalesValues(BigDecimal salesAWeekBefore, BigDecimal salesCurrent) {

        return OrdersSalesDTO.builder()
                .salesCurrentDto(SalesCurrentDTO.builder()
                        .value(salesCurrent)
                        .build())
                .salesAWeekBeforeDto(SalesAWeekBeforeDTO.builder()
                        .value(salesAWeekBefore).build())
                .build();
    }
}
