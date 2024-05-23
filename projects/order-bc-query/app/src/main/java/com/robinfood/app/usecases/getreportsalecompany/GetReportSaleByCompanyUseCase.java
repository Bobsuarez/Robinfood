package com.robinfood.app.usecases.getreportsalecompany;

import com.robinfood.app.mappers.salescompany.GetCompanySaleDTOMappers;
import com.robinfood.app.mappers.salescompany.GetSalesByCompanyDTOMappers;
import com.robinfood.app.usecases.getsalereport.GetSaleReportUseCase;
import com.robinfood.core.dtos.report.sale.CompanySaleDTO;
import com.robinfood.core.dtos.report.sale.OrdersSalesDTO;
import com.robinfood.core.dtos.report.sale.SaleReportResponseDTO;
import com.robinfood.core.exceptions.AsyncOrderBcException;
import com.robinfood.core.utilities.KeyAnyWithTimezonesUtil;
import com.robinfood.core.utilities.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class GetReportSaleByCompanyUseCase implements IGetReportSaleByCompanyUseCase {

    private final GetSaleReportUseCase getSaleReportUseCase;

    @Value("#{'${pos.orders.status-cancelled}'.split(',')}")
    private List<Long> IDS_ORDERS_STATUS_CANCELLED_AND_DISCARDED;

    public GetReportSaleByCompanyUseCase(GetSaleReportUseCase getSaleReportUseCase) {
        this.getSaleReportUseCase = getSaleReportUseCase;
    }

    @Override
    public SaleReportResponseDTO invoke(
            List<Integer> companies,
            LocalDateTime dateTimeCurrent,
            List<String> timezones
    ) throws AsyncOrderBcException {

        final KeyAnyWithTimezonesUtil keyAnyWithTimezonesUtil = new KeyAnyWithTimezonesUtil(companies, timezones);
        Map<Integer, ZoneId> companiesWithTimezones = keyAnyWithTimezonesUtil.withZoneId();

        log.info("Companies with timezones: {}", companiesWithTimezones);

        Map<Integer, LocalDateTime> companiesWithLocaltime = keyAnyWithTimezonesUtil.withLocalTimeByZoneIds(
                dateTimeCurrent,
                companiesWithTimezones
        );

        final List<CompanySaleDTO> resultCompanySaleDTOList = new ArrayList<>();

        for (Integer idCompany : companies) {

            final LocalDateTime currentTime = companiesWithLocaltime.get(idCompany);
            final LocalDateTime dateLastTimeWeek = new LocalDateTimeUtil(currentTime).lastOfWeekSameHour();

            log.info(
                    "the company id: {} is consulted with the current date: {} and the date of a week ago: {}",
                    idCompany, currentTime, dateLastTimeWeek
            );

            final OrdersSalesDTO salesResultDTO = toSearchInThreadsSumTotal(
                    idCompany.longValue(), currentTime, dateLastTimeWeek);

            final CompanySaleDTO companySaleResultDTO = GetCompanySaleDTOMappers
                    .buildCompanySaleDto(idCompany.longValue(), salesResultDTO);

            resultCompanySaleDTOList.add(companySaleResultDTO);
        }

        return SaleReportResponseDTO.builder()
                .companyList(resultCompanySaleDTOList)
                .build();
    }

    private OrdersSalesDTO toSearchInThreadsSumTotal(
            Long idCompany,
            LocalDateTime currentTime,
            LocalDateTime dateLastTimeWeek
    ) throws AsyncOrderBcException {

        try {

            CompletableFuture<BigDecimal> sumDateLastTime = getSaleReportUseCase
                    .invoke(dateLastTimeWeek, idCompany, IDS_ORDERS_STATUS_CANCELLED_AND_DISCARDED);

            CompletableFuture<BigDecimal> sumDateTimeCurrent = getSaleReportUseCase
                    .invoke(currentTime, idCompany, IDS_ORDERS_STATUS_CANCELLED_AND_DISCARDED);

            CompletableFuture.allOf(sumDateLastTime, sumDateTimeCurrent).join();

            return GetSalesByCompanyDTOMappers
                    .buildSalesValues(sumDateLastTime.get(), sumDateTimeCurrent.get());

        } catch (Exception e) {
            throw new AsyncOrderBcException("[toSearchInThreadsSumTotal] Error when making query threads");
        }
    }
}
