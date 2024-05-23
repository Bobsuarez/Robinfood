package com.robinfood.app.mappers.salescompany;

import com.robinfood.core.dtos.report.sale.CompanySaleDTO;
import com.robinfood.core.dtos.report.sale.OrdersSalesDTO;
import org.springframework.stereotype.Component;

@Component
public class GetCompanySaleDTOMappers {

    public static CompanySaleDTO buildCompanySaleDto(Long idCompany, OrdersSalesDTO ordersSalesDTO) {

        return CompanySaleDTO.builder()
                .id(idCompany)
                .ordersSales(ordersSalesDTO)
                .build();
    }
}
