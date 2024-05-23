package com.robinfood.app.usecases.getreportordersalescompanies;

import com.google.gson.JsonObject;
import com.robinfood.app.usecases.getactivesalestoorderbycompanies.IGetActiveSalesToOrderByCompaniesUseCase;
import com.robinfood.app.usecases.getactivecompanies.IGetActiveCompaniesUseCase;
import com.robinfood.app.usecases.getlambdaexchange.IGetLambdaExchangeUseCase;
import com.robinfood.core.dtos.configuration.CompaniesDTO;
import com.robinfood.core.dtos.orderSales.ResponseOrderActiveSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.ResponseOrderSalesDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.utilities.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GetReportOrderSalesCompaniesUseCase implements IGetReportOrderSalesCompaniesUseCase {

    private final IBuildResponseReportUseCase buildResponseReportUseCase;

    private final IBuildTotalCompaniesOrderSalesUseCase buildTotalCompaniesOrderSalesUseCase;

    private final IGetActiveSalesToOrderByCompaniesUseCase getActiveSalesToOrderByCompaniesUseCase;

    private final IGetActiveCompaniesUseCase getActiveCompaniesUseCase;

    private final IGetLambdaExchangeUseCase getLambdaExchangeUseCase;

    public GetReportOrderSalesCompaniesUseCase(
            IBuildResponseReportUseCase buildResponseReportUseCase,
            IBuildTotalCompaniesOrderSalesUseCase buildTotalCompaniesOrderSalesUseCase,
            IGetActiveSalesToOrderByCompaniesUseCase getActiveSalesToOrderByCompaniesUseCase,
            IGetActiveCompaniesUseCase getActiveCompaniesUseCase,
            IGetLambdaExchangeUseCase getLambdaExchangeUseCase
    ) {
        this.buildResponseReportUseCase = buildResponseReportUseCase;
        this.buildTotalCompaniesOrderSalesUseCase = buildTotalCompaniesOrderSalesUseCase;
        this.getActiveSalesToOrderByCompaniesUseCase = getActiveSalesToOrderByCompaniesUseCase;
        this.getActiveCompaniesUseCase = getActiveCompaniesUseCase;
        this.getLambdaExchangeUseCase = getLambdaExchangeUseCase;
    }

    @Override
    public Result<ResponseOrderSalesDTO> invoke(
            LocalDateTime dateTimeCurrent,
            List<Integer> idsCompanies
    ) {

        CompaniesDTO listCompanies = getCompanies();

        final List<String> timezones= listCompanies.getCompanies()
                .stream()
                .map(companyDTO -> companyDTO.getCountry().getTimezone())
                .collect(Collectors.toList());

        ResponseOrderActiveSalesDTO responseOrderActiveSales = getSalesToOrderByCompanies(
                dateTimeCurrent,
                idsCompanies,
                timezones
        );

        JsonObject lambdaExchange = getLambdaExchanges(
                dateTimeCurrent.toLocalDate().toString(),
                DateUtil.lastOfWeekSameHour(dateTimeCurrent).toLocalDate().toString()
        );

        ResponseOrderSalesDTO responseOrderSalesDTO = buildResponseReportUseCase.invoke(
                responseOrderActiveSales,
                lambdaExchange,
                listCompanies,
                dateTimeCurrent.toLocalDate().toString(),
                DateUtil.lastOfWeekSameHour(dateTimeCurrent).toLocalDate().toString()
        );

        responseOrderSalesDTO.setTotalCompanies(buildTotalCompaniesOrderSalesUseCase
                .invoke(responseOrderSalesDTO));

        return new Result.Success(responseOrderSalesDTO);
    }

    private ResponseOrderActiveSalesDTO getSalesToOrderByCompanies(
            LocalDateTime dateTimeCurrent,
            List<Integer> idsCompanies,
            List<String> timezones
    ) {

        Result<ResponseOrderActiveSalesDTO> responseOrderActiveSalesDTO = getActiveSalesToOrderByCompaniesUseCase
                .invoke(idsCompanies, dateTimeCurrent.toString(), timezones);

        if (responseOrderActiveSalesDTO instanceof Result.Error) {
            throw new ResponseStatusException(
                    ((Result.Error) responseOrderActiveSalesDTO).getHttpStatus(),
                    ((Result.Error) responseOrderActiveSalesDTO).getException().getMessage()
            );
        }

        return ((Result.Success<ResponseOrderActiveSalesDTO>) responseOrderActiveSalesDTO)
                .getData();
    }

    public CompaniesDTO getCompanies() {

        Result<CompaniesDTO> responseListCompanies = getActiveCompaniesUseCase.invoke();

        if (responseListCompanies instanceof Result.Error) {
            throw new ResponseStatusException(
                    ((Result.Error) responseListCompanies).getHttpStatus(),
                    ((Result.Error) responseListCompanies).getException().getMessage()
            );
        }

        return ((Result.Success<CompaniesDTO>) responseListCompanies)
                .getData();
    }

    public JsonObject getLambdaExchanges(String currentDate, String previousDate) {

        Result<JsonObject> responseLambdaExchangeMap = getLambdaExchangeUseCase
                .invoke(currentDate, previousDate);

        if (responseLambdaExchangeMap instanceof Result.Error) {
            throw new ResponseStatusException(
                    ((Result.Error) responseLambdaExchangeMap).getHttpStatus(),
                    ((Result.Error) responseLambdaExchangeMap).getException().getMessage()
            );
        }

        return ((Result.Success<JsonObject>) responseLambdaExchangeMap)
                .getData();
    }
}
