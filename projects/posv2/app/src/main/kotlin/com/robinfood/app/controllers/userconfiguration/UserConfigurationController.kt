package com.robinfood.app.controllers.userconfiguration

import com.robinfood.app.usecases.userconfiguration.IGetUserPosConfigurationUseCase
import com.robinfood.core.constants.APIConstants.API_V1
import com.robinfood.core.constants.APIConstants.USER
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.userconfiguration.UserConfigurationResponseDTO
import com.robinfood.core.enums.Result
import javax.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(API_V1 + USER)
class UserConfigurationController(
        private val getUserPosStoreUseCase: IGetUserPosConfigurationUseCase
): IUserConfigurationController {
    /**
     * Sends request to get user pos configuration
     *
     * [userId] The user id request
     * [httpServletRequest] the authentication token to be used
     * @return The Configurations Pos By User
     */
    @GetMapping("/{userId}/pos-configuration")
    override suspend fun getUserPosConfiguration(
        @PathVariable userId: Long,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<ApiResponseDTO<UserConfigurationResponseDTO>> {
        val token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)
        return when(val userConfigurationResponseDTO = getUserPosStoreUseCase.invoke(token, userId)) {
            is Result.Error -> ResponseEntity(
                    ApiResponseDTO(userConfigurationResponseDTO.exception.localizedMessage),
                    userConfigurationResponseDTO.httpStatus
            )
            is Result.Success -> ResponseEntity(
                    ApiResponseDTO(
                            userConfigurationResponseDTO.data,
                            "User pos configuration by User retrieved successfully"
                    ),
                    HttpStatus.OK
            )
        }
    }
}