package com.robinfood.configurations.controllers.v1.docs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.PosIdDTO;
import com.robinfood.configurations.dto.v1.StorePosDTO;
import com.robinfood.configurations.dto.v1.models.ActivePosDTO;
import com.robinfood.configurations.dto.v1.models.PosDTO;
import com.robinfood.configurations.dto.v1.models.UpdatePosDTO;
import com.robinfood.configurations.dto.v1.pageable.PageResponseDTO;
import com.robinfood.configurations.dto.v1.listposresponse.PosResponseDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Pos", description = "Allows to get information of pos.")
public interface PosControllerDocs {

    @Operation(summary = "POS id.")
    @ApiResponse(responseCode = "200", description = "Pos info.",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PosIdDTO.class))
            }
    )
    ResponseEntity<ApiResponseDTO<PosIdDTO>> findPosId(
            @Parameter(name = "store_id", description = "Store identifier",
                    required = true) Long storeId,
            @Parameter(name = "pos_type_id", description = "Pos type identifier",
                    required = true) Long posTypeId
    );

    ResponseEntity<ApiResponseDTO<List<PosDTO>>> findPosByStoreId(
            @Parameter(name = "storeId", description = "Store identifier", required = true)
                    Long storeId
    );

    @Operation(summary = "Create a Pos")
    @ApiResponse(
            responseCode = "201",
            description = "Pos created successfully",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StorePosDTO.class))
            }
    )
    ResponseEntity<ApiResponseDTO<StorePosDTO>> createPos(
            @Parameter(
                    name = "create Pos",
                    description = "pos to be created"
            ) StorePosDTO createPosDTO)
            throws JsonProcessingException, BusinessRuleException;

    @Operation(summary = "Update a Pos")
    @ApiResponse(
            responseCode = "201",
            description = "Pos updated successfully",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UpdatePosDTO.class))
            }
    )
    ResponseEntity<ApiResponseDTO<UpdatePosDTO>> updatePos(
            @Parameter(name = "id", description = "Pos identifier", required = true)
                    Long id,
            @Parameter(
                    name = "update Pos",
                    description = "pos updated"
            ) UpdatePosDTO updatePosDTO)
            throws JsonProcessingException, BusinessRuleException;

    @Operation(summary = "Activate and Deactivate a Pos")
    @ApiResponse(
            responseCode = "202",
            description = "Pos updated successfully",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ActivePosDTO.class))
            }
    )
    ResponseEntity<ApiResponseDTO<UpdatePosDTO>> activateAndDeactivatedPos(
            @Parameter(name = "id", description = "Pos identifier", required = true)
                    Long id,
            @Parameter(
                    name = "update Pos",
                    description = "pos updated"
            ) ActivePosDTO activePosDTO)
            throws JsonProcessingException, BusinessRuleException;

    ResponseEntity<ApiResponseDTO<PageResponseDTO<PosResponseDTO>>> listPosAndResolutions(
            @Parameter(name = "posName", description = "String to filter by company brand name.") String name,
            @Parameter(name = "status", description = "Long to filter by status del pos.") Long status,
            @Parameter(name = "storeId", description = "Number to filter by Id from the store.") Long storeId,
            @Parameter(name = "page", description = "Page to get data, starting with 1. "
                    + "Omit wit size to get all data.") Integer page,
            @Parameter(name = "size",
                    description = "Page size to get data. Omit with page to get all data.") Integer size,
            @Parameter(name = "sort", description = "Criteria to sort company brands. All options support asc or"
                    + " desc order: name,asc") Sort sort
    ) throws JsonProcessingException;

}
