package com.robinfood.repository.co2;

import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.entities.CO2CalculateRequestEntity;
import com.robinfood.core.entities.CO2CalculateResponseEntity;
import com.robinfood.core.mappers.CO2CompensationMappers;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of ICO2Repository
 */

@Slf4j
public class CO2Repository implements ICO2Repository {

    private final ICO2RemoteDataSource co2RemoteDataSource;

    public CO2Repository(ICO2RemoteDataSource co2RemoteDataSource) {
        this.co2RemoteDataSource = co2RemoteDataSource;
    }

    @Override
    public CO2CalculateResponseEntity calculateCO2Compensation(
            String token,
            OrderDTO orderDTO
    ) {
        CO2CalculateRequestEntity co2CalculateRequestEntity =
                CO2CompensationMappers.toCO2CalculateRequestEntity(orderDTO);

        log.trace("Request CO2 Calculate {}", co2CalculateRequestEntity);

        CO2CalculateResponseEntity response = co2RemoteDataSource.calculateCO2Compensation(
            token, co2CalculateRequestEntity
        );

        log.trace("Response CO2 Calculate {}", response);

        return response;
    }

}
