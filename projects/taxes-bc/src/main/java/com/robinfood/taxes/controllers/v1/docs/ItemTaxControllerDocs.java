package com.robinfood.taxes.controllers.v1.docs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.TaxableItemDTO;
import com.robinfood.taxes.dto.v1.ItemListDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Item Taxes")
public interface ItemTaxControllerDocs {

    @Operation(summary = "List item taxes")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "List items successfully",
            content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TaxableItemDTO.class))
            }
        )
    })
    ResponseEntity<ApiResponseDTO<List<TaxableItemDTO>>> list(
        @Parameter(name = "list item taxes", description = "Items to be listed")
            ItemListDTO itemlist) throws JsonProcessingException, BusinessRuleException;
}
