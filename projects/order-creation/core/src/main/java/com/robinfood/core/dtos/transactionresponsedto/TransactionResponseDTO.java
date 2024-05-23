package com.robinfood.core.dtos.transactionresponsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TransactionResponseDTO implements Serializable {

    private static final long serialVersionUID = -3433570172214638557L;

    private Long id;

    private String createdAt;

    private List<OrderResponseDTO> orders;

    private String uuid;

}
