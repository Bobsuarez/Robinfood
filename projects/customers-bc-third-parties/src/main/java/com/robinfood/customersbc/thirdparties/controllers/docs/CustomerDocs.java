package com.robinfood.customersbc.thirdparties.controllers.docs;

import com.robinfood.customersbc.thirdparties.dtos.CreateCustomerDTO;
import com.robinfood.customersbc.thirdparties.dtos.CustomerDTO;
import com.robinfood.customersbc.thirdparties.dtos.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Customer")
public interface CustomerDocs {

    @Operation(summary = "Create customer")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "201",
                description = "Created customer",
                content = {
                    @Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = CustomerDTO.class)
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
    Mono<ResponseEntity<ResponseDTO<CustomerDTO>>> createCustomer(
        CreateCustomerDTO createCustomerDTO
    );
}
