package com.robinfood.configurations.controllers.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.components.RestExceptionHandler;
import com.robinfood.configurations.controllers.v1.docs.StoreBrandsControllerDocs;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.AssignBrandToStoreRequestDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.CompanyBrand;
import com.robinfood.configurations.models.Store;
import com.robinfood.configurations.models.StoreBrand;
import com.robinfood.configurations.services.CompanyBrandService;
import com.robinfood.configurations.services.StoreBrandsService;
import com.robinfood.configurations.services.StoreService;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.robinfood.configurations.constants.PermissionsConstants.PUBLIC;
import static com.robinfood.configurations.constants.PermissionsConstants.SERVICE;

@Slf4j
@RestController
@RequestMapping(value = "/v1/store_brands")
public class StoreBrandsController implements StoreBrandsControllerDocs {

    private StoreService storeService;

    private CompanyBrandService companyBrandService;

    private StoreBrandsService storeBrandsService;


    public StoreBrandsController
        (StoreService storeService, CompanyBrandService companyBrandService,
            StoreBrandsService storeBrandsService) {
        this.storeService = storeService;
        this.companyBrandService = companyBrandService;
        this.storeBrandsService = storeBrandsService;
    }

    @BasicLog
    @Override
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + PUBLIC + "') || hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<StoreBrand>> insert
        (@RequestBody AssignBrandToStoreRequestDTO assignBrandToStoreRequestDTO)
        throws JsonProcessingException, BusinessRuleException {

        log.info("Starting insert storeBrand: {}", assignBrandToStoreRequestDTO);

        Long menuBrandId = assignBrandToStoreRequestDTO.getMenuBrandId();

        CompanyBrand companyBrand = companyBrandService.
            getByCompanyIdAndMenuBrandId(menuBrandId);

        if (companyBrand == null) {
            throw new BusinessRuleException(HttpStatus.NOT_FOUND, "Menu brand doesn't exists.");
        }

        Long storeId = assignBrandToStoreRequestDTO.getStoreId();

        StoreBrand storeBrand = new StoreBrand();
        Store store = storeService.findById(storeId);

        storeBrand.setStore(store);
        storeBrand.setBrandCompany(companyBrand);

        StoreBrand createdStoreBrand = storeBrandsService.create(storeBrand);

        log.info("Created storeBrand: {}", createdStoreBrand);

        if (createdStoreBrand == null) {
            throw new BusinessRuleException(HttpStatus.BAD_REQUEST, "StoreBrand already exists.");
        }

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ApiResponseDTO.<StoreBrand>builder()
                .code(HttpStatus.CREATED.value())
                .data(createdStoreBrand)
                .message("StoreBrand created successfully")
                .build());
    }
}
