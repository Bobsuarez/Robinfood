package com.robinfood.configurations.controllers.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.controllers.v1.docs.StoreControllerDocs;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.CreateStoreDTO;
import com.robinfood.configurations.dto.v1.CriteriaCommonDTO;
import com.robinfood.configurations.dto.v1.StoreDTO;
import com.robinfood.configurations.dto.v1.StoreTaxDTO;
import com.robinfood.configurations.dto.v1.UpdateStoreDTO;
import com.robinfood.configurations.dto.v1.models.StateDTO;
import com.robinfood.configurations.dto.v1.pageable.PageResponseDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.City;
import com.robinfood.configurations.models.Company;
import com.robinfood.configurations.models.Store;
import com.robinfood.configurations.models.StoreIdentifierType;
import com.robinfood.configurations.models.StoreTax;
import com.robinfood.configurations.models.StoreType;
import com.robinfood.configurations.models.Zone;
import com.robinfood.configurations.services.ChannelService;
import com.robinfood.configurations.services.StoreService;
import com.robinfood.configurations.services.StoreTaxService;
import com.robinfood.configurations.utils.JsonUtils;
import com.robinfood.configurations.utils.PageableUtils;
import com.robinfood.configurations.utils.StoreUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.robinfood.configurations.constants.ConfigurationsBCConstants.MIN_CHARACTERS_TO_SEARCH;
import static com.robinfood.configurations.constants.ConfigurationsBCConstants.UNSORTED;
import static com.robinfood.configurations.constants.PermissionsConstants.CONFIGURATIONS_PREFIX;
import static com.robinfood.configurations.constants.PermissionsConstants.CREATE_STORE;
import static com.robinfood.configurations.constants.PermissionsConstants.DELETE_STORE;
import static com.robinfood.configurations.constants.PermissionsConstants.LIST_STORES;
import static com.robinfood.configurations.constants.PermissionsConstants.PUBLIC;
import static com.robinfood.configurations.constants.PermissionsConstants.SERVICE;
import static com.robinfood.configurations.constants.PermissionsConstants.UPDATE_STORE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "v1/stores")
public class StoreController implements StoreControllerDocs {

    private final StoreService storeService;

    private final ChannelService channelService;

    private final StoreTaxService storeTaxService;

    private final ObjectMapper objectMapper;

    public StoreController(StoreService storeService,
        ChannelService channelService, StoreTaxService storeTaxService, ObjectMapper objectMapper) {
        this.storeService = storeService;
        this.channelService = channelService;
        this.storeTaxService = storeTaxService;
        this.objectMapper = objectMapper;
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @BasicLog
    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + PUBLIC + "') || hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<StoreDTO>> findStoreById(
        @PathVariable("id") @Valid @NotNull @Min(1) Long id)
        throws JsonProcessingException {

        log.info("Getting Store info with store id {}", id);
        Store store = storeService.findById(id);
        List<StoreTax> storeTax = storeTaxService.findByIdStore(id);
        log.info("Store info retrieved successfully {}.", store);
        StoreDTO storeDTO = objectMapper.convertValue(store, StoreDTO.class);
        StateDTO stateDTO = objectMapper.convertValue(store.getCity().getState(), StateDTO.class);

        List<StoreTaxDTO> storeTaxDTOS = storeTax.stream().map(item ->
                StoreTaxDTO.builder().value(item.getValue()).build())
                .collect(Collectors.toList());

        storeDTO.setState(stateDTO);
        storeDTO.setFlowTax(storeTaxDTOS.stream().findFirst().orElse(new StoreTaxDTO()));
        storeDTO.setTimezone(store.getCity().getTimezone());
        storeDTO.getCity().setCode(store.getCity().getCode());

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<StoreDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Store retrieved successfully")
                .data(storeDTO)
                .build());
    }

    @BasicLog
    @Override
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize(
        "hasRole('" + SERVICE + "')  || hasRole('" + CONFIGURATIONS_PREFIX + CREATE_STORE + "')")
    public ResponseEntity<ApiResponseDTO<StoreDTO>> createStore(
        @Valid @RequestBody CreateStoreDTO createStoreDTO)
        throws BusinessRuleException, JsonProcessingException {
        log.info("Request received on store controller [createStore] with body: {}",
            JsonUtils.convertToJson(createStoreDTO));

        log.info("Generating Store object from storeDTO {}", JsonUtils.convertToJson(createStoreDTO));
        Store receivedStore = generateStoreFromCreationDto(createStoreDTO);
        log.info("Store object generated successfully.");

        Store createdStore = storeService.create(receivedStore);

        log.info("Generating store DTO from created store.");
        StoreDTO mappedStore = StoreUtil.buildStoreDTO(createdStore);
        log.info("DTO generated successfully. {}", JsonUtils.convertToJson(mappedStore));

        log.info("Creating ApiResponse from createStore");
        ResponseEntity<ApiResponseDTO<StoreDTO>> response = ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ApiResponseDTO.<StoreDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("Store created successfully")
                .data(mappedStore)
                .build());
        log.info("Api response created from createStore successfully. {}", JsonUtils.convertToJson(response));

        return response;
    }

    @BasicLog
    @Override
    @PutMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(
        "hasRole('" + SERVICE + "')  || hasRole('" + CONFIGURATIONS_PREFIX + UPDATE_STORE + "')")
    public ResponseEntity<ApiResponseDTO<StoreDTO>> updateStore(
        @PathVariable Long id, @Valid @RequestBody UpdateStoreDTO updateStoreDTO)
        throws BusinessRuleException, JsonProcessingException {
        log.info("Request received on store controller [updateStore] with body: {}",
            JsonUtils.convertToJson(updateStoreDTO));

        log.info("Generating Store object from storeDTO {}", JsonUtils.convertToJson(updateStoreDTO));
        Store receivedStore = generateStoreFromUpdateDto(updateStoreDTO);
        log.info("Store object generated successfully.");

        Store createdStore = storeService.update(id, receivedStore);

        log.info("Generating store DTO from update store.");
        StoreDTO mappedStore = StoreUtil.buildStoreDTO(createdStore);
        log.info("DTO generated successfully. {}", JsonUtils.convertToJson(mappedStore));

        log.info("Creating ApiResponse from updateStore");
        ResponseEntity<ApiResponseDTO<StoreDTO>> response = ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ApiResponseDTO.<StoreDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("Store updated successfully")
                .data(mappedStore)
                .build());
        log.info("Api response created from updateStore successfully. {}", JsonUtils.convertToJson(response));

        return response;
    }

    @BasicLog
    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(
        "hasRole('" + SERVICE + "')  || hasRole('" + CONFIGURATIONS_PREFIX + LIST_STORES + "')")
    public ResponseEntity<ApiResponseDTO<PageResponseDTO<StoreDTO>>> listStore(
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "company_country_id", required = false) Long companyCountryId,
        @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
        @RequestParam(value = "size", required = false) Integer size, Sort sort)
        throws JsonProcessingException {

        String nameFilter = null;
        if (name != null && name.length() >= MIN_CHARACTERS_TO_SEARCH) {
            nameFilter = name;
        }

        Long companyCountryIdFilter = null;
        if (companyCountryId != null && companyCountryId > 0) {
            companyCountryIdFilter = companyCountryId;
        }

        Integer pageSize = size;

        if (pageSize == null) {
            log.trace("Count store in service.");
            pageSize = storeService.countByFilter(nameFilter, companyCountryIdFilter);
            log.trace("campaign count {}  in service.", pageSize);

            if (pageSize == 0) {
                PageResponseDTO<StoreDTO> pageResponse = PageableUtils
                    .createEmptyPageResponse(sort);

                return getApiResponseDTO(pageResponse);
            }

        }

        Pageable pageable = PageableUtils.createPage(page, pageSize, sort);

        log.info("Request received on store controller [listStore] ");
        Page<Store> storeList = storeService.list(nameFilter, companyCountryIdFilter, pageable);

        log.info("Generating list store DTO from list store.");
        Page<StoreDTO> storeDTOS = storeList
            .map(StoreUtil::buildStoreDTO);
        log.info("List DTO generated successfully. {}", JsonUtils.convertToJson(storeDTOS));

        List<StoreDTO> campaignSorted = new ArrayList<>(storeDTOS.getContent());

        if (!sort.toString().equals(UNSORTED)) {
            PageableUtils.sortStores(campaignSorted, sort.toString());
        }

        PageResponseDTO<StoreDTO> pageResponse = PageableUtils
            .createPageResponse(storeDTOS, campaignSorted, sort);

        return getApiResponseDTO(pageResponse);

     }

    @BasicLog
    @Override
    @GetMapping(value = "/{id}/channels", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<List<com.robinfood.configurations.dto.v1.ChannelDTO>>> findChannelByStoreId(
        @PathVariable("id") @Valid @NotNull @Min(1) Long id
    ) {
        log.info("Getting Channels info with store id {}", id);

        List<com.robinfood.configurations.dto.v1.ChannelDTO> channels = channelService.findByStoreId(id);

        log.info("Channels info retrieved successfully {}.", channels);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<List<com.robinfood.configurations.dto.v1.ChannelDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Channel retrieved successfully")
                .data(channels)
                .build());
    }

    @BasicLog
    @Override
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + DELETE_STORE + "')")
    public ResponseEntity<ApiResponseDTO<String>> delete(@PathVariable Long id)
        throws JsonProcessingException {

        log.info("Deleting Store.");
        storeService.delete(id);
        log.trace("Store with id {} removed successfully", id);

        return ResponseEntity.ok(ApiResponseDTO.<String>builder()
            .code(HttpStatus.NO_CONTENT.value())
            .message("Store deleted successfully")
            .build());
    }

    @BasicLog
    @Override
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + PUBLIC + "') || hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<List<StoreDTO>>> findStores(
        @Valid CriteriaCommonDTO criteria
    ) throws JsonProcessingException {

        log.info("Getting Stores info.");
        List<Store> stores = storeService.findStores(criteria.getSearch());
        log.info("Stores info retrieved successfully {}.", objectMapper.writeValueAsString(stores));

        List<StoreDTO> storesDTO = objectMapper.convertValue(stores, new TypeReference<>() {});

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<List<StoreDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Stores retrieved successfully")
                .data(storesDTO)
                .build());
    }

    private ResponseEntity<ApiResponseDTO<PageResponseDTO<StoreDTO>>> getApiResponseDTO(
        PageResponseDTO<StoreDTO> pageResponse) throws JsonProcessingException {

        log.info("Creating ApiResponse from list store");
        ResponseEntity<ApiResponseDTO<PageResponseDTO<StoreDTO>>> apiResponseDTOCampaignsDTO =
            ResponseEntity.status(
                    HttpStatus.OK)
                .body(ApiResponseDTO.<PageResponseDTO<StoreDTO>>builder()
                    .code(HttpStatus.OK.value())
                    .message("Store retrieved successfully.")
                    .data(pageResponse)
                    .build());

        log.info("Api response obtained list StoreDTO successfully. {}",
            JsonUtils.convertToJson(apiResponseDTOCampaignsDTO));

        return apiResponseDTOCampaignsDTO;

    }

    private Store generateStoreFromCreationDto(CreateStoreDTO createStoreDTO) {
        return Store.builder()
            .name(createStoreDTO.getName())
            .internalName(createStoreDTO.getInternalName())
            .city(new City(createStoreDTO.getCityId()))
            .address(createStoreDTO.getAddress())
            .company(Company.builder().id(createStoreDTO.getCompanyCountryId()).build())
            .email(createStoreDTO.getEmail())
            .phone(createStoreDTO.getPhone())
            .location(createStoreDTO.getLocation())
            .storeType(new StoreType(createStoreDTO.getStoreTypeId()))
            .zones(new Zone(createStoreDTO.getZoneId()))
            .identifier(createStoreDTO.getIdentifier())
            .storeIdentifierType(new StoreIdentifierType(createStoreDTO.getStoreIdentifierTypeId()))
            .currencySymbol(createStoreDTO.getCurrencySymbol())
            .currencyType(createStoreDTO.getCurrencyType())
            .taxRegime(createStoreDTO.getTaxRegime())
            .uuid(createStoreDTO.getUuid())
            .build();
    }

    private Store generateStoreFromUpdateDto(UpdateStoreDTO updateStoreDTO) {
        return Store.builder()
            .name(updateStoreDTO.getName())
            .internalName(updateStoreDTO.getInternalName())
            .city(new City(updateStoreDTO.getCityId()))
            .address(updateStoreDTO.getAddress())
            .company(Company.builder().id(updateStoreDTO.getCompanyId()).build())
            .email(updateStoreDTO.getEmail())
            .phone(updateStoreDTO.getPhone())
            .location(updateStoreDTO.getLocation())
            .storeType(new StoreType(updateStoreDTO.getStoreTypeId()))
            .zones(new Zone(updateStoreDTO.getZoneId()))
            .identifier(updateStoreDTO.getIdentifier())
            .storeIdentifierType(new StoreIdentifierType(updateStoreDTO.getStoreIdentifierTypeId()))
            .currencySymbol(updateStoreDTO.getCurrencySymbol())
            .currencyType(updateStoreDTO.getCurrencyType())
            .taxRegime(updateStoreDTO.getTaxRegime())
            .uuid(updateStoreDTO.getUuid())
            .build();
    }

}
