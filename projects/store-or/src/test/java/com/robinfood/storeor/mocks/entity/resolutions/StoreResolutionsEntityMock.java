package com.robinfood.storeor.mocks.entity.resolutions;

import com.robinfood.storeor.entities.configurationposbystore.ResolutionEntity;
import com.robinfood.storeor.entities.configurationposbystore.StoreResolutionsEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StoreResolutionsEntityMock {

    public StoreResolutionsEntity defaultData() {

        return StoreResolutionsEntity.builder()
                .resolutions(
                        List.of(
                                ResolutionEntity.builder()
                                        .id(1L)
                                        .confirmed(Boolean.TRUE)
                                        .document("Doc Aut DIAN")
                                        .endDate(LocalDateTime.parse("2100-08-31 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                        .finalNumber(5)
                                        .invoiceNumber("18764007762306")
                                        .invoiceText("Doc Aut DIAN #18764007762306 Fecha 2020/11/19 -" +
                                                " 2021/11/19 De 0 hasta 5 Reg. Comun")
                                        .name("Caja 1 - Muy Calle 83")
                                        .posId(1L)
                                        .prefix("RM93")
                                        .serial("123ABCD")
                                        .startDate(LocalDateTime.parse("2020-11-19 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                        .startingNumber(0)
                                        .status(Boolean.TRUE)
                                        .build()
                        )

                )
                .storeId(1L)
                .build();
    }
}
