package com.robinfood.configurations.controllers.v1;

import static com.robinfood.configurations.constants.ConfigurationsBCConstants.MIN_CHARACTERS_TO_SEARCH;
import static com.robinfood.configurations.constants.ConfigurationsBCConstants.UNSORTED;
import static com.robinfood.configurations.constants.PermissionsConstants.CONFIGURATIONS_PREFIX;
import static com.robinfood.configurations.constants.PermissionsConstants.LIST_COMPANY_BRANDS;
import static com.robinfood.configurations.constants.PermissionsConstants.PUBLIC;
import static com.robinfood.configurations.constants.PermissionsConstants.SERVICE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.controllers.v1.docs.CompanyControllerDocs;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.CompanyBodyHeaderDTO;
import com.robinfood.configurations.dto.v1.CompanyResponseDTO;
import com.robinfood.configurations.dto.v1.models.CompanyBrandResponseDTO;
import com.robinfood.configurations.dto.v1.models.CompanyDTO;
import com.robinfood.configurations.dto.v1.pageable.PageResponseDTO;
import com.robinfood.configurations.models.Company;
import com.robinfood.configurations.models.CompanyBrand;
import com.robinfood.configurations.services.CompanyBrandService;
import com.robinfood.configurations.services.CompanyService;
import com.robinfood.configurations.utils.PageableUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/v1/companies")
public class CompanyController implements CompanyControllerDocs {

    @Value("${url.base.logo}")
    private String logoBaseUrl;

    private final CompanyService companyService;

    private final CompanyBrandService companyBrandService;

    private final ModelMapper modelMapper;

    private final ObjectMapper objectMapper;

    public CompanyController(CompanyService companyService,
                             CompanyBrandService companyBrandService,
                             ModelMapper modelMapper, ObjectMapper objectMapper
    ) {
        this.companyService = companyService;
        this.companyBrandService = companyBrandService;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    @BasicLog
    @GetMapping(value = "/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + PUBLIC + "') || hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<CompanyDTO>> findById(Long companyId) {

        Company company = companyService.findById(companyId);
        CompanyDTO companyResponse = objectMapper.convertValue(company, CompanyDTO.class);
        companyResponse.setIdentification(company.getIdentification());
        companyResponse.setIdentificationNumber(company.getIdentification());

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.<CompanyDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("Company retrieved successfully")
                        .data(companyResponse)
                        .build());
    }

    @BasicLog
    @Override
    @GetMapping(value = "/{id}/brands", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(
            "hasRole('" + SERVICE + "')  || hasRole('" + CONFIGURATIONS_PREFIX + LIST_COMPANY_BRANDS + "')")
    public ResponseEntity<ApiResponseDTO<PageResponseDTO<CompanyBrandResponseDTO>>> listCompanyBrands(
            @PathVariable Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "store_id", required = false) Long storeId,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size, Sort sort
    )
            throws JsonProcessingException {

        String nameFilter = "";
        if (name != null && name.length() >= MIN_CHARACTERS_TO_SEARCH) {
            nameFilter = name;
        }

        Pageable pageable = PageableUtils.createPage(page, size, sort);
        log.info("Request received on company brand controller [listCompanyBrand] ");
        Page<CompanyBrand> companyBrandList = companyBrandService.list(id, nameFilter, storeId, pageable);

        log.info("Generating list channelDTO from list channels.");
        Page<CompanyBrandResponseDTO> companyBrandsDTOS = companyBrandList
                .map(companyBrand -> modelMapper.map(companyBrand, CompanyBrandResponseDTO.class));
        log.info("List DTO generated successfully.");

        List<CompanyBrandResponseDTO> companyBrandSorted = new ArrayList<>(companyBrandsDTOS.getContent());

        if (!sort.toString().equals(UNSORTED)) {
            sortObject(companyBrandSorted, sort.toString());
        }

        PageResponseDTO<CompanyBrandResponseDTO> pageResponse = PageableUtils
                .createPageResponse(companyBrandsDTOS, companyBrandSorted, sort);

        pageResponse.getContent()
                .stream()
                .forEach(companyBrand -> companyBrand.setLogo(logoBaseUrl.concat(companyBrand.getLogo())));

        return getApiResponseDTO(pageResponse);
    }

    private ResponseEntity<ApiResponseDTO<PageResponseDTO<CompanyBrandResponseDTO>>> getApiResponseDTO(
            PageResponseDTO<CompanyBrandResponseDTO> pageResponse
    ) {

        log.info("Creating ApiResponse from list company brands");
        ResponseEntity<ApiResponseDTO<PageResponseDTO<CompanyBrandResponseDTO>>> apiResponseDTOReasonsDTO =
                ResponseEntity.status(
                                HttpStatus.OK)
                        .body(ApiResponseDTO.<PageResponseDTO<CompanyBrandResponseDTO>>builder()
                                .code(HttpStatus.OK.value())
                                .message("Company brands retrieved successfully.")
                                .data(pageResponse)
                                .build());
        log.info("Api response obtained list company brands successfully.");

        return apiResponseDTOReasonsDTO;

    }

    @Override
    @BasicLog
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + PUBLIC + "') || hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<CompanyResponseDTO>> findAllCompanies(
            @RequestParam(value = "status", required = false) Long status
    ) {

        List<Company> companyAllList = Optional.ofNullable(companyService.findByAll(status))
                .filter(data -> !data.isEmpty())
                .orElseThrow(() ->
                        new EntityNotFoundException("Company list not found"));

        List<CompanyBodyHeaderDTO> companyListResponseDTO = new ArrayList<>();
        companyAllList.forEach(data -> companyListResponseDTO
                .add(CompanyBodyHeaderDTO.fromCompanyRepository(data)));
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.<CompanyResponseDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("Company retrieved successfully")
                        .data(CompanyResponseDTO.builder()
                                .companiesList(companyListResponseDTO)
                                .build())
                        .build());
    }


    public static void sortObject(List<CompanyBrandResponseDTO> companyBrandDTOList, String sort) {

        switch (sort) {
            case "name: ASC":
                companyBrandDTOList.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
                break;
            case "name: DESC":
                companyBrandDTOList.sort((o1, o2) -> o2.getName().compareToIgnoreCase(o1.getName()));
                break;
            case "id: ASC":
                companyBrandDTOList.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
                break;
            case "id: DESC":
                companyBrandDTOList.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
                break;
            default:
                break;
        }

    }

}
