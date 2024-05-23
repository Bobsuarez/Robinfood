package com.robinfood.app.controllers.userconfiguration

import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.userconfiguration.UserConfigurationResponseDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import javax.servlet.http.HttpServletRequest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

/**
 * Exposes the API that handles all data related to pos
 */
@Tag(name = "UserConfiguration", description = "Requests for get User configuration pos related data")
interface IUserConfigurationController {

    /**
     * Sends request to get user pos configuration
     *
     * [userId] The User id to get configuration
     * [httpServletRequest] the authentication token to be used
     * @return The configurations pos by store
     */
    @Operation(summary = "Sends a request to get user pos configuration")
    @ApiResponses(
            ApiResponse(
                    responseCode = "200",
                    description = "This sends a request to get pos configuration by user",
                    content = [(Content(
                            array = ArraySchema(schema = Schema(implementation = UserConfigurationResponseDTO::class)),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    ))]
            )
    )
    suspend fun getUserPosConfiguration(
        userId: Long,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<ApiResponseDTO<UserConfigurationResponseDTO>>
}