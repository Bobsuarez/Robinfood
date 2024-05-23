package com.robinfood.configurations.controllers.v1.docs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.CreateStoreDTO;
import com.robinfood.configurations.dto.v1.CriteriaCommonDTO;
import com.robinfood.configurations.dto.v1.StoreDTO;
import com.robinfood.configurations.dto.v1.UpdateStoreDTO;
import com.robinfood.configurations.dto.v1.models.ChannelDTO;
import com.robinfood.configurations.dto.v1.pageable.PageResponseDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Store", description = "Allows to get information of store.")
public interface StoreControllerDocs {

    @Operation(summary = "Store info by store id.")
    @ApiResponse(responseCode = "200", description = "Store info.",
        content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = StoreDTO.class))
        }
    )
    ResponseEntity<ApiResponseDTO<StoreDTO>> findStoreById(
        @Parameter(name = "id", description = "Store identifier",
            required = true) Long storeId)
        throws BusinessRuleException, JsonProcessingException;

    @Operation(summary = "Create a Store")
    @ApiResponse(
        responseCode = "201",
        description = "Store created successfully",
        content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = StoreDTO.class))
        }
    )
    ResponseEntity<ApiResponseDTO<StoreDTO>> createStore(
        @Parameter(
            name = "create Store",
            description = "Store to be created"
        ) CreateStoreDTO createStoreDTO)
        throws JsonProcessingException, BusinessRuleException;

    @Operation(summary = "Update a Store")
    @ApiResponse(
        responseCode = "201",
        description = "Store Update successfully",
        content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = StoreDTO.class))
        }
    )
    ResponseEntity<ApiResponseDTO<StoreDTO>> updateStore(
        @Parameter(name = "id", description = "Store id to be updated") Long id,
        @Parameter(
            name = "Update Store",
            description = "Store to be Update"
        ) UpdateStoreDTO updateStoreDTO)
        throws JsonProcessingException, BusinessRuleException;

    @Operation(summary = "Store List")
    @ApiResponse(
        responseCode = "200",
        description = "List of store obtained successfully",
        content = {
            @Content(mediaType =  MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = StoreDTO.class))
        }
    )
    ResponseEntity<ApiResponseDTO<PageResponseDTO<StoreDTO>>> listStore(
        @Parameter(name = "name", description = "String to filter by store name.") String name,
        @Parameter(name = "company_country_id",
                description = "Number to filter by company country id.") Long companyCountryId,
        @Parameter(name = "page", description = "Page to get data, starting with 1. "
            + "Omit wit size to get all data.") Integer page,
        @Parameter(name = "size",
            description = "Page size to get data. Omit with page to get all data.") Integer size,
        @Parameter(name = "sort", description = "Criteria to sort status. All options support asc or"
            + " desc order: name,asc") Sort sort
    ) throws JsonProcessingException;

    @Operation(summary = "Channel info by store id.")
    @ApiResponse(
        responseCode = "200",
        description = "Channel info.",
        content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ChannelDTO.class))
        }
    )
    ResponseEntity<ApiResponseDTO<List<com.robinfood.configurations.dto.v1.ChannelDTO>>> findChannelByStoreId(
        @Parameter(name = "id", description = "Store identifier.", required = true) Long id
    );

    @Operation(summary = "Delete a store")
    @ApiResponse(responseCode = "200", description = "Delete store", content = {
        @Content(mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ApiResponseDTO.class))
    })
    ResponseEntity<ApiResponseDTO<String>> delete(
        @Parameter(name = "id", description = "Store to be deleted") Long id)
        throws JsonProcessingException;

    @Operation(summary = "All stores info.")
    @ApiResponse(responseCode = "200", description = "Stores info.",
        content = {
            @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema =
                @Schema(implementation = StoreDTO.class)
                )
            )
        }
    )
    ResponseEntity<ApiResponseDTO<List<StoreDTO>>> findStores(
        CriteriaCommonDTO criteria
    ) throws JsonProcessingException;

}
