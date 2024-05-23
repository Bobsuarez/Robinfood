package com.robinfood.core.dtos;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class ExistsTransactionUuidOrderUuidDTO implements Serializable {

    private boolean exits;

    private String message;
}
