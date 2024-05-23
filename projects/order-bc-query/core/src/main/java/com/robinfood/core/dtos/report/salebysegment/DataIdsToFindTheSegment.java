package com.robinfood.core.dtos.report.salebysegment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
public class DataIdsToFindTheSegment {

    private List<Long> brandsList;

    private List<Long> companiesList;

    private List<Long> channelsList;

    private LocalDateTime dateToSearch;

    private List<Long> paymentsMethodsList;

    private List<Long> storesList;

    private String timeZone;

}
