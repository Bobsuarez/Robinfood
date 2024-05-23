package com.robinfood.datamock;

import com.robinfood.dtos.sendordertosimba.response.TransactionResponseDTO;

import java.util.UUID;

public class TransactionResponseDTOMock {

    public static TransactionResponseDTO getDefault() {

        return TransactionResponseDTO.builder()
                .fullName("Eduardo suarez")
                .orderUuid(UUID.randomUUID())
                .build();
    }
}
