package com.robinfood.core.entities.transactionrequestentities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequestEntity {

    private Long id;

    private String name;

    private String sku;
}
