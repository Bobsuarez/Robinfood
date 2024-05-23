package com.robinfood.configurations.controllers.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.controllers.v1.docs.BrandControllerDocs;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.models.BrandDTO;
import com.robinfood.configurations.dto.v1.pageable.PageResponseDTO;
import com.robinfood.configurations.models.Brand;
import com.robinfood.configurations.services.BrandService;
import com.robinfood.configurations.utils.JsonUtils;
import com.robinfood.configurations.utils.PageableUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.robinfood.configurations.constants.ConfigurationsBCConstants.UNSORTED;
import static com.robinfood.configurations.constants.PermissionsConstants.PUBLIC;
import static com.robinfood.configurations.constants.PermissionsConstants.SERVICE;

@Slf4j
@RestController
@RequestMapping(value = "/v1/brands")
public class BrandController implements BrandControllerDocs {

    private final BrandService brandService;

    private final ModelMapper modelMapper;

    public BrandController(
            BrandService brandService,
            ModelMapper modelMapper
    ) {
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    /**
     * Método que consulta la marcas
     *
     * @return respuesta JSON de las marcas
     */
    @Override
    @BasicLog
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "")
    @PreAuthorize("hasRole('" + PUBLIC + "') || hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<PageResponseDTO<BrandDTO>>> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "enabled", required = false) Boolean enabled,
            Sort sort
    ) throws JsonProcessingException {

        log.info("Getting Brands");

        Integer pageSize = size;

        if (pageSize == null) {
            log.trace("Count brands in service.");
            pageSize = (int) brandService.count();
            log.trace("brands count {}  in service.", pageSize);

            if (pageSize == 0) {
                PageResponseDTO<BrandDTO> pageResponse = PageableUtils
                        .createEmptyPageBrandResponse(sort);

                return getApiResponseDTO(pageResponse);
            }
        }

        Pageable pageable = PageableUtils.createPage(page, pageSize, sort);

        Page<Brand> brandList = brandService.list(pageable, enabled);

        log.info("Generating list BrandsDTO.");

        Page<BrandDTO> brandDTOS = brandList
                .map(brand -> modelMapper.map(brand, BrandDTO.class));

        log.info("List brandDTO generated successfully. {}", JsonUtils.convertToJson(brandDTOS));

        List<BrandDTO> brandSorted = new ArrayList<>(brandDTOS.getContent());

        if (!sort.toString().equals(UNSORTED)) {
            PageableUtils.sortBrands(brandSorted, sort.toString());
        }

        PageResponseDTO<BrandDTO> pageResponse = PageableUtils
                .createPageResponse(brandDTOS, brandSorted, sort);

        return getApiResponseDTO(pageResponse);
    }

    private ResponseEntity<ApiResponseDTO<PageResponseDTO<BrandDTO>>> getApiResponseDTO(
            PageResponseDTO<BrandDTO> pageResponse) throws JsonProcessingException {

        log.info("Creating ApiResponse from list brands");
        ResponseEntity<ApiResponseDTO<PageResponseDTO<BrandDTO>>> apiResponseBrandsDTO =
                ResponseEntity.status(
                                HttpStatus.OK)
                        .body(ApiResponseDTO.<PageResponseDTO<BrandDTO>>builder()
                                .code(HttpStatus.OK.value())
                                .message("Brands retrieved successfully.")
                                .data(pageResponse)
                                .build());

        log.info("Api response obtained list BrandDTO successfully. {}",
                JsonUtils.convertToJson(apiResponseBrandsDTO));

        return apiResponseBrandsDTO;

    }
}
