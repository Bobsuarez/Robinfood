package com.robinfood.core.dtos.orderhistory.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class OrderHistoryRequestDTO {

    final List<Long> channelsId;

    final Integer currentPage;

    final Boolean isDelivery;

    final LocalDate localDateEnd;

    final LocalDate localDateStart;

    final Integer perPage;

    final String searchText;

    final Long storeId;

    final String timeZone;

}
