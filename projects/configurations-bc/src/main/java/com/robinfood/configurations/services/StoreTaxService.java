package com.robinfood.configurations.services;

import com.robinfood.configurations.models.StoreTax;

import java.util.List;

public interface StoreTaxService {
    List<StoreTax> findByIdStore(Long storeId);
}
