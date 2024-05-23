package com.robinfood.configurations.controllers.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.controllers.v1.docs.PosControllerDocs;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.PosIdDTO;
import com.robinfood.configurations.dto.v1.StorePosDTO;
import com.robinfood.configurations.dto.v1.listposresponse.PosResponseDTO;
import com.robinfood.configurations.dto.v1.models.ActivePosDTO;
import com.robinfood.configurations.dto.v1.models.PosDTO;
import com.robinfood.configurations.dto.v1.models.UpdatePosDTO;
import com.robinfood.configurations.dto.v1.pageable.PageResponseDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.Pos;
import com.robinfood.configurations.services.PosService;
import com.robinfood.configurations.utils.PageableUtils;
import com.robinfood.configurations.utils.PosUtil;
import com.robinfood.configurations.utils.SalePointUtil;
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
import org.springframework.web.bind.annotation.PatchMapping;
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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.robinfood.configurations.constants.ConfigurationsBCConstants.MIN_CHARACTERS_TO_SEARCH_POS;
import static com.robinfood.configurations.constants.ConfigurationsBCConstants.UNSORTED;
import static com.robinfood.configurations.constants.PermissionsConstants.CONFIGURATIONS_PREFIX;
import static com.robinfood.configurations.constants.PermissionsConstants.CREATE_POS;
import static com.robinfood.configurations.constants.PermissionsConstants.PUBLIC;
import static com.robinfood.configurations.constants.PermissionsConstants.SERVICE;
import static com.robinfood.configurations.constants.PermissionsConstants.UPDATE_POS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "v1/pos")
public class PosController implements PosControllerDocs {

    private final PosService posService;
    private final ModelMapper modelMapper;

    public PosController(
            PosService posService,
            ModelMapper modelMapper
    ) {
        this.posService = posService;
        this.modelMapper = modelMapper;
    }

    @BasicLog
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + PUBLIC + "') || hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<PosIdDTO>> findPosId(
            @RequestParam(name = "store_id") @NotNull @Min(1) Long storeId,
            @RequestParam(name = "pos_type_id") @NotNull @Min(1) Long posTypeId) {

        log.info("Getting POS id with store id %d and pos type id %d",
                storeId,
                posTypeId);

        final Long posId = posService.findPosId(storeId, posTypeId);
        log.info("POS id retrieved successfully Pos id {}.", posId);
        PosIdDTO posIdDTO = PosIdDTO.builder().posId(posId).build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.<PosIdDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("POS id retrieved successfully")
                        .data(posIdDTO)
                        .build());
    }

    @BasicLog
    @Override
    @GetMapping(value = "/stores/{storeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + PUBLIC + "') || hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<List<PosDTO>>> findPosByStoreId(
            @PathVariable("storeId") @NotNull @Min(1) Long storeId
    ) {

        List<Pos> listPos = posService.findByStoreId(storeId);
        List<PosDTO> listResponse = listPos.stream().map(x -> PosDTO.builder()
                .id(x.getId())
                .name(x.getName())
                .resolutions(SalePointUtil.getPosResolutionsActive(x.getResolutionList()))
                .build()).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.<List<PosDTO>>builder()
                        .code(HttpStatus.OK.value())
                        .message("POS retrieved successfully")
                        .data(listResponse)
                        .build());
    }

    @BasicLog
    @Override
    @PostMapping(value = "/stores/pos", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('" + SERVICE + "')  || hasRole('" + CONFIGURATIONS_PREFIX + CREATE_POS + "')")
    public ResponseEntity<ApiResponseDTO<StorePosDTO>> createPos(
            @Valid @RequestBody StorePosDTO storePosDTO
    ) throws JsonProcessingException, BusinessRuleException {

        StorePosDTO storePosResult = posService.create(storePosDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDTO.<StorePosDTO>builder()
                        .code(HttpStatus.CREATED.value())
                        .message("Pos created successfully")
                        .data(storePosResult)
                        .build());
    }

    @BasicLog
    @Override
    @PutMapping(value = "/stores/pos/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('" + SERVICE + "')  || hasRole('" + CONFIGURATIONS_PREFIX + UPDATE_POS + "')")
    public ResponseEntity<ApiResponseDTO<UpdatePosDTO>> updatePos(
            @PathVariable("id") @NotNull @Min(1) Long id,
            @Valid @RequestBody UpdatePosDTO updatePosDTO
    ) throws JsonProcessingException, BusinessRuleException {

        posService.update(id, updatePosDTO);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(ApiResponseDTO.<UpdatePosDTO>builder()
                        .code(HttpStatus.ACCEPTED.value())
                        .message("Pos updated successfully")
                        .data(null)
                        .build());
    }

    @BasicLog
    @Override
    @PatchMapping(value = "/stores/pos/{id}/active", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('" + SERVICE + "')  || hasRole('" + CONFIGURATIONS_PREFIX + UPDATE_POS + "')")
    public ResponseEntity<ApiResponseDTO<UpdatePosDTO>> activateAndDeactivatedPos(
            @PathVariable("id") @NotNull @Min(1) Long id,
            @Valid @RequestBody ActivePosDTO activePosDTO
    ) throws JsonProcessingException, BusinessRuleException {

        posService.activePos(activePosDTO, id);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(ApiResponseDTO.<UpdatePosDTO>builder()
                        .code(HttpStatus.ACCEPTED.value())
                        .message("Pos updated successfully")
                        .data(null)
                        .build());
    }

    @BasicLog
    @Override
    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('" + PUBLIC + "') || hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<PageResponseDTO<PosResponseDTO>>> listPosAndResolutions(
            @RequestParam(value = "posName", required = false) String name,
            @RequestParam(value = "status", required = false) Long status,
            @RequestParam(value = "storeId", required = false) Long storeId,
            @Valid @Min(1) @RequestParam(value = "page") Integer page,
            @Valid @Min(1) @RequestParam(value = "size") Integer size, Sort sort
    )
            throws JsonProcessingException {

        String nameFilter = "";
        if (name != null && name.length() >= MIN_CHARACTERS_TO_SEARCH_POS) {
            nameFilter = name;
        }

        page--;

        Pageable pageable = PageableUtils.createPage(page, size, sort);
        log.info("Request received on pos controller [posList] ");
        Page<Pos> posList = posService.list(nameFilter, status, storeId, pageable);

        log.info("Generating list pos from list pos.");
        Page<PosResponseDTO> posDTOS = posList
                .map(pos -> modelMapper.map(PosUtil.buildPosResponse(pos), PosResponseDTO.class));
        log.info("List DTO generated successfully.");

        List<PosResponseDTO> responsePosSorted = new ArrayList<>(posDTOS.getContent());

        if (!sort.toString().equals(UNSORTED)) {
            sortObject(responsePosSorted, sort.toString());
        }

        PageResponseDTO<PosResponseDTO> pageResponse = PageableUtils
                .createPageResponse(posDTOS, responsePosSorted, sort);

        return ResponseEntity.status(
                        HttpStatus.OK)
                .body(ApiResponseDTO.<PageResponseDTO<PosResponseDTO>>builder()
                        .code(HttpStatus.OK.value())
                        .message("Pos and resolutions retrieved successfully.")
                        .data(pageResponse)
                        .build());
    }

    public static void sortObject(List<PosResponseDTO> posResponseDTOList, String sort) {

        Comparator<PosResponseDTO> comparator = null;

        switch (sort) {
            case "posName: ASC":
                comparator = Comparator.comparing(PosResponseDTO::getName, String.CASE_INSENSITIVE_ORDER);
                break;
            case "posName: DESC":
                comparator = Comparator.comparing(PosResponseDTO::getName, String.CASE_INSENSITIVE_ORDER).reversed();
                break;
            case "id: ASC":
                comparator = Comparator.comparing(PosResponseDTO::getId);
                break;
            case "id: DESC":
                comparator = Comparator.comparing(PosResponseDTO::getId).reversed();
                break;
            default:
                break;
        }

        if (comparator != null) {
            posResponseDTOList.sort(comparator);
        }

    }
}
