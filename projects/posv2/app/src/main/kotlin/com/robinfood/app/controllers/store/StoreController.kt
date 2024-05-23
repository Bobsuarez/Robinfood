package com.robinfood.app.controllers.store

import com.robinfood.app.usecases.getstoredeliveryplatforms.IGetStoreDeliveryPlatformsUseCase
import com.robinfood.core.constants.APIConstants.API_V1
import com.robinfood.core.constants.APIConstants.STORE
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.store.StoreDeliveryPlatformDTO
import com.robinfood.core.enums.Result

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.servlet.http.HttpServletRequest

/**
 * Implementation of 'IStoreController'
 */
@RestController
@RequestMapping(API_V1 + STORE)
class StoreController(
        private val getStoreDeliveryPlatformsUseCase: IGetStoreDeliveryPlatformsUseCase
) : IStoreController {

    @GetMapping("/{storeId}/delivery-platforms")
    override suspend fun getDeliveryPlatforms(
            httpServletRequest: HttpServletRequest,
            @RequestParam(value = "page") page: Int?,
            @RequestParam(value = "size") size: Int?,
            @PathVariable storeId: Long
    ): ResponseEntity<ApiResponseDTO<List<StoreDeliveryPlatformDTO>>> {
        val token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)
        return when (
            val resultStoreDeliveryPlatforms = getStoreDeliveryPlatformsUseCase.invoke(
                    page,
                    size,
                    storeId,
                    token
            )
        ) {
            is Result.Success -> {
                var responseStatus = HttpStatus.OK
                var responseMessage = "Store delivery platforms retrieved successfully"
                if (resultStoreDeliveryPlatforms.data.isEmpty()) {
                    responseMessage = "There are no platforms assigned to the store"
                    responseStatus = HttpStatus.NO_CONTENT
                }
                ResponseEntity(
                        ApiResponseDTO(
                                resultStoreDeliveryPlatforms.data,
                                responseMessage
                        ),
                        responseStatus
                )
            }
            is Result.Error -> throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    resultStoreDeliveryPlatforms.exception.localizedMessage
            )
        }
    }
}
