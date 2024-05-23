package com.robinfood.core.dtos.productfinancecategorydto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductFinanceCategoryResponseDTO implements Serializable {

    private final Long productId;

    private final ProductFinanceCategoryDTO category;
}
