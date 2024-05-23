package com.robinfood.configurations.controllers.v1.docs;

import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.CompanyHeadquarterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Company", description = "Allows to get information of company.")
public interface HeadquartersControllerDocs {

    @Operation(summary = "Company information.")
    @ApiResponse(responseCode = "200", description = "Company information.",
        content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = CompanyHeadquarterDTO.class))}

    )
    ResponseEntity<ApiResponseDTO<CompanyHeadquarterDTO>>
    findByCompanyCountryId(@PathVariable("companyCountryId") Long companyId);
}
