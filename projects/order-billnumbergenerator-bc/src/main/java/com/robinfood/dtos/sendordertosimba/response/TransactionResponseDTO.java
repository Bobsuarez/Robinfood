package com.robinfood.dtos.sendordertosimba.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
public class TransactionResponseDTO {

    private String fullName;
    private UUID orderUuid;
}
