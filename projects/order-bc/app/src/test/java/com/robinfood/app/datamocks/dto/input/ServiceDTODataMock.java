package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.order.ServiceDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ServiceDTODataMock {
    public ServiceDTO getDataDefault() {
        return new ServiceDTO(
                2000.00,
                1L,
                9407.4074,
                1L,
                8.0,
                592.5926,
                8000.00,
                10000.00
        );
    }

    public List<ServiceDTO> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }

    public List<ServiceDTO> getDataDefaultListForCalculate() {
        return Arrays.asList(
                new ServiceDTO(
                        1000.0,
                        1L,
                        9407.4074,
                        1L,
                        8.0,
                        592.5926,
                        8000.00,
                        10000.00),
                new ServiceDTO(
                        1000.0,
                        2L,
                        7481.4814,
                        1L,
                        8.0,
                        518.5186,
                        7000.00,
                        8000.00
                )
        );
    }
}
