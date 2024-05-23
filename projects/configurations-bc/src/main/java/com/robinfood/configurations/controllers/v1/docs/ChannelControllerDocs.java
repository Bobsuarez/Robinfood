package com.robinfood.configurations.controllers.v1.docs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.models.ChannelDTO;
import com.robinfood.configurations.dto.v1.pageable.PageResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Channels")
public interface ChannelControllerDocs {

    @Operation(summary = "Channel info by channel id.")
    @ApiResponse(
            responseCode = "200",
            description = "Channel info.",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ChannelDTO.class))
            }
    )
    ResponseEntity<ApiResponseDTO<ChannelDTO>> findChannelById(
            @Parameter(name = "id", description = "Channel identifier.", required = true) Long id
    );

    @Operation(summary = "Channels List")
    @ApiResponse(
        responseCode = "200",
        description = "List of channels obtained successfully",
        content = {
            @Content(mediaType = "application/json",
                schema = @Schema(implementation = ChannelDTO.class))
        }
    )
    ResponseEntity<ApiResponseDTO<PageResponseDTO<ChannelDTO>>> listChannel(
        @Parameter(name = "name", description = "String to filter by channel name.") String name,
        @Parameter(name = "page", description = "Page to get data, starting with 0. "
            + "Omit wit size to get all data.") Integer page,
        @Parameter(name = "size",
            description = "Page size to get data. Omit with page to get all data.") Integer size,
        @Parameter(name = "sort", description = "Criteria to sort channels. All options support asc or"
            + " desc order: name,asc") Sort sort
    ) throws JsonProcessingException;

}
