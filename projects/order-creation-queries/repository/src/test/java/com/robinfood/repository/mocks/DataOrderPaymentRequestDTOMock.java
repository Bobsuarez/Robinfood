package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.orderpayment.DataOrderPaymentRequestDTO;

import java.time.LocalDate;

public class DataOrderPaymentRequestDTOMock {

   public static DataOrderPaymentRequestDTO getDataDefault() {

       return DataOrderPaymentRequestDTO.builder()
               .localDateStart(LocalDate.now())
               .localDateEnd(LocalDate.now())
               .posId(1L)
               .timeZone("America/Bogotá")
               .build();
   }
}
