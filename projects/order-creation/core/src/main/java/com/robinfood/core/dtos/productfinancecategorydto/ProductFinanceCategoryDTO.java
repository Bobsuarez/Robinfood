package com.robinfood.core.dtos.productfinancecategorydto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductFinanceCategoryDTO implements Serializable {

    private final Long id;

    private final String name;
}
