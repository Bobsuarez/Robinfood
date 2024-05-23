package com.robinfood.configurations.controllers.v1;

import static com.robinfood.configurations.constants.PermissionsConstants.PUBLIC;
import static com.robinfood.configurations.constants.PermissionsConstants.SERVICE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.controllers.v1.docs.SalePointControllerDocs;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.SalePointDTO;
import com.robinfood.configurations.models.CompanyBrand;
import com.robinfood.configurations.models.Pos;
import com.robinfood.configurations.services.CompanyBrandService;
import com.robinfood.configurations.services.PosService;
import com.robinfood.configurations.utils.SalePointUtil;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "v1/sale-points")
public class SalePointController implements SalePointControllerDocs {

    @Value("${url.base.logo}")
    private String logoBaseUrl;

    private final ObjectMapper objectMapper;
    private final PosService posService;
    private final CompanyBrandService companyBrandService;

    public SalePointController(ObjectMapper objectMapper,
                               PosService salePointService,
                               CompanyBrandService companyBrandService) {
        this.objectMapper = objectMapper;
        this.posService = salePointService;
        this.companyBrandService = companyBrandService;
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @BasicLog
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + PUBLIC + "') || hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<SalePointDTO>> find(
        @RequestParam(name = "pos_id") @Valid @NotNull @Min(1) Long posId,
        @RequestParam(name = "brand_id", required = false) @Valid @Min(1) Long brandId)
        throws JsonProcessingException {

        log.info("Getting POS with id {}", posId);
        Pos pos = posService.findById(posId);
        List<CompanyBrand> companyBrandList = companyBrandService.findAll();

        log.info("POS retrieved successfully {}.", objectMapper.writeValueAsString(pos));
        SalePointDTO salePointDTO = SalePointUtil.buildSalePointDTO(pos, brandId, logoBaseUrl, companyBrandList);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<SalePointDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Sale Point retrieved successfully")
                .data(salePointDTO)
                .build());
    }
}
