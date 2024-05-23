package com.robinfood.localserver.commons.entities.createorderlocal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class OrderLocalResponseEntity {

    private Long brandId;

    private String brandName;

    private String data;

    private Long id;

    private Long originId;

    private String originName;

    private Boolean printed;

    private Long statusId;

    private String statusName;

    private String uid;

}
