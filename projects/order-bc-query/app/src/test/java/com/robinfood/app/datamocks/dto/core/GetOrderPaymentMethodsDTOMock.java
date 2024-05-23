package com.robinfood.app.datamocks.dto.core;

import com.robinfood.core.dtos.GetOrderPaymentMethodsDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetOrderPaymentMethodsDTOMock {

    public  static List<GetOrderPaymentMethodsDTO> orderPaymentsMethods = new ArrayList<>(Arrays.asList(
            GetOrderPaymentMethodsDTO.builder()
                    .id(11L)
                    .name("daviplata")
                    .shortName("daviplata")
                    .transactions(1L)
                    .typeId(6L)
                    .value(2.0)
                    .build()
    ));
}
