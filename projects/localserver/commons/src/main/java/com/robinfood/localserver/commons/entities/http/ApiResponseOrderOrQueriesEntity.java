package com.robinfood.localserver.commons.entities.http;

import com.robinfood.localserver.commons.entities.orders.OrderDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiResponseOrderOrQueriesEntity {

    private List<OrderDetailEntity> data;

    private String locale;

    private String message;

}
