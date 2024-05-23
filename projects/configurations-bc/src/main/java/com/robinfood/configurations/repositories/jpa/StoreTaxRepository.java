package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.models.StoreTax;

import java.util.List;

public interface StoreTaxRepository extends  SoftDeleteRepository<StoreTax, Long>{

    List<StoreTax> findByStoresIdIs(Long storeId);
}
