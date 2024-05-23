package com.robinfood.customersbc.thirdparties.controllers.docs;

import com.robinfood.customersbc.thirdparties.dtos.ResponseDTO;
import com.robinfood.customersbc.thirdparties.dtos.ThirdPartyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Third parties")
public interface ThirdPartyDocs {

    @Operation(summary = "Get third parties")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "List of third parties",
                content = {
                    @Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = @ArraySchema(
                            schema = @Schema(implementation = ThirdPartyDTO.class)
                        )
                    )
                }
            ),
            @ApiResponse(
                responseCode = "401",
                description = "Unauthorized user",
                content = @Content(schema = @Schema(hidden = true))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid required arguments",
                content = {
                    @Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseDTO.class)
                    )
                }
            ),
        }
    )
    Mono<ResponseEntity<ResponseDTO<List<ThirdPartyDTO>>>> getThirdPartiesByExternalId(
        Long externalId
    );
}
