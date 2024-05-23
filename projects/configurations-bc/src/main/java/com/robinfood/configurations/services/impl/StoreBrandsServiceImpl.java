package com.robinfood.configurations.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.StoreBrand;
import com.robinfood.configurations.repositories.jpa.StoreBrandRepository;
import com.robinfood.configurations.services.StoreBrandsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class StoreBrandsServiceImpl implements StoreBrandsService {

    private StoreBrandRepository storeBrandRepository;

    public StoreBrandsServiceImpl(StoreBrandRepository storeBrandRepository) {
        this.storeBrandRepository = storeBrandRepository;
    }

    @Override
    public int countStoreBrandByBrandIdAndStoreId(Long branId, Long storeId) {
        log.info("Counting storeBrands by storeId and BrandId");
        return storeBrandRepository.countByBrandIdAndStoreId(branId, storeId);
    }

    @Override
    public StoreBrand create(StoreBrand recievedStoreBrand) throws JsonProcessingException, BusinessRuleException {
        log.info("Creating storeBrands: {} ", recievedStoreBrand);
        Long brandId = recievedStoreBrand.getBrandCompany().getId();
        Long storeId = recievedStoreBrand.getStore().getId();

        if(countStoreBrandByBrandIdAndStoreId(brandId, storeId) > 0) {
            return null;
        }
        StoreBrand createdStoreBrand = storeBrandRepository.save(recievedStoreBrand);

        log.info("storeBrands saved successfully: {}", createdStoreBrand);
        return createdStoreBrand;
    }

}
