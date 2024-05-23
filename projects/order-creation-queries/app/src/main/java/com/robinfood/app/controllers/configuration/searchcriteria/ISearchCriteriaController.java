package com.robinfood.app.controllers.configuration.searchcriteria;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.searchcriteria.SearchCriteriaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Get Search Criteria Filter",
        description = "Allows you to obtain search criteria information"
)
public interface ISearchCriteriaController {

    @Operation(summary = "Get Search Criteria Filter")
    @ApiResponse(
            responseCode = "200", description = "Search Criteria Filter.",
            content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponseDTO.class)
                    )
            }
    )
    ResponseEntity<ApiResponseDTO<List<SearchCriteriaDTO>>> invoke();

}
