package com.robinfood.localserver.commons.dtos.http;

import com.robinfood.localserver.commons.dtos.orders.OrderDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiResponseOrderOrQueriesDTO {

    private List<OrderDetailDTO> data;

    private String locale;

    private String message;

}
