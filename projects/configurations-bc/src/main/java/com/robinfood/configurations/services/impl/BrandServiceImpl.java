package com.robinfood.configurations.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.models.Brand;
import com.robinfood.configurations.models.CompanyBrand;
import com.robinfood.configurations.repositories.jpa.BrandRepository;
import com.robinfood.configurations.repositories.jpa.CompanyBrandRepository;
import com.robinfood.configurations.services.BrandService;
import com.robinfood.configurations.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final CompanyBrandRepository companyBrandRepository;

    public BrandServiceImpl(
            BrandRepository brandRepository,
            CompanyBrandRepository companyBrandRepository
    ) {
        this.brandRepository = brandRepository;
        this.companyBrandRepository = companyBrandRepository;
    }

    /**
     * Método que consulta la marcas por compañia
     *
     * @return objeto de marca por compañia
     */
    @Override
    public CompanyBrand getByBrandIdAndCompanyId(Long brandId, Long companyId) {

        return companyBrandRepository.findByBrandsIdAndCompanyId(brandId, companyId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Brand with brandId %d and companyId %d does not exists.",
                                brandId, companyId)));
    }

    /**
     * Método para consultar las marcas
     *
     * @return Lista de marcas
     */
    @Override
    public Page<Brand> list(Pageable pageable, Boolean enabled) {

        log.info("Listing brands");
        Page<Brand> getBrands = brandRepository.findAllPaginated(pageable, enabled);
        log.info("List of brands obtained successfully {}", JsonUtils.convertToJson(getBrands));

        return getBrands;
    }

    @Override
    public long count() {
        return brandRepository.count();
    }

}
