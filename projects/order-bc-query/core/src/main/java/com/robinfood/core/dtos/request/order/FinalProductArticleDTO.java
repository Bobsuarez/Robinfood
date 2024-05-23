package com.robinfood.core.dtos.request.order;

import lombok.Data;

@Data
public class FinalProductArticleDTO {

    private final Long id;

    private final Long menuHallProductId;

    private final Long typeId;

}
