package com.robinfood.app.usecases.getactivesalestoorderbycompanies;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.orderSales.ResponseOrderActiveSalesDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.ordersalesbycompanies.IOrderSalesByCompaniesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GetActiveSalesToOrderByCompaniesUseCase implements IGetActiveSalesToOrderByCompaniesUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    private final IOrderSalesByCompaniesRepository orderSalesByCompaniesRepository;

    public GetActiveSalesToOrderByCompaniesUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IOrderSalesByCompaniesRepository orderSalesByCompaniesRepository
    ) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.orderSalesByCompaniesRepository = orderSalesByCompaniesRepository;
    }

    @Override
    public Result<ResponseOrderActiveSalesDTO> invoke(
            List<Integer> idsCompanies,
            String dateTimeCurrent,
            List<String> timezones
    ) {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        return orderSalesByCompaniesRepository.getSalesToOrderByCompanies(
                idsCompanies,
                dateTimeCurrent,
                timezones,
                token.getAccessToken()
        );
    }
}
