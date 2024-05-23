package com.robinfood.core.entities.transactionrequestentities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalProductArticleEntity {

    private Long id;

    private Long menuHallProductId;

    private Long typeId;
}
