package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.report.salebysegment.DataIdsToFindTheSegment;

import java.time.LocalDateTime;
import java.util.List;

public class DataIdsToFindTheSegmentDTOMock {

    public static DataIdsToFindTheSegment getDefault(){
       return DataIdsToFindTheSegment.builder()
               .channelsList(List.of(1L,2L))
               .companiesList(List.of(1L,2L))
               .brandsList(List.of(1L,2L))
               .paymentsMethodsList(List.of(1L,2L))
               .storesList(List.of(1L))
               .dateToSearch(LocalDateTime.now())
               .timeZone("America/Bogota")
               .build();
    }

    public static DataIdsToFindTheSegment getDefaultStoreNull(){
        return DataIdsToFindTheSegment.builder()
                .channelsList(List.of(1L,2L))
                .companiesList(List.of(1L,2L))
                .brandsList(List.of(1L,2L))
                .paymentsMethodsList(List.of(1L,2L))
                .storesList(null)
                .dateToSearch(LocalDateTime.now())
                .timeZone("America/Bogota")
                .build();
    }
}
