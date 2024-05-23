package com.robinfood.app.controllers.crosssellingmenu

import com.robinfood.app.usecases.crosssellingmenu.IGetCrossSellingMenuUseCase
import com.robinfood.core.constants.APIConstants.API_V1
import com.robinfood.core.constants.APIConstants.CROSS_SELLING_MENU
import com.robinfood.core.constants.APIConstants.STORE
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.crosssellingmenu.CrossSellingMenuDTO
import com.robinfood.core.dtos.menu.MenuDTO
import com.robinfood.core.enums.Result
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(API_V1 + STORE)
class CrossSellingMenuController(
        private val getCrossSellingMenu: IGetCrossSellingMenuUseCase
) : ICrossSellingMenuController {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/{storeId}/${CROSS_SELLING_MENU}")
    override suspend fun getCrossSellingMenu(
            @RequestParam(value = "countryId") countryId: Long,
            @RequestParam(value = "flowId") flowId: Long,
            httpServletRequest: HttpServletRequest,
            @PathVariable storeId: Long
    ): ResponseEntity<ApiResponseDTO<CrossSellingMenuDTO>> {

        log.info("Started Cross-sellingMenuController method getCrossSellingMenu(). " +
                "Parameters are storeId = {}, countryId = {}, flowId = {}", storeId, countryId, flowId)

        val token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)
        val menuResult = getCrossSellingMenu.invoke(
                token,
                countryId,
                flowId,
                storeId
        )
        return when (menuResult) {
            is Result.Error -> {
                log.info("Finished getCrossSellingMenu(). Error was {}", menuResult)
                ResponseEntity( ApiResponseDTO(menuResult.exception.localizedMessage),
                        menuResult.httpStatus)
            }
            is Result.Success -> {
                log.info("Finished getCrossSellingMenu(). menuResult.data = {}", menuResult.data)
                ResponseEntity(ApiResponseDTO(menuResult.data, "Cross-Selling Menu retrieved successfully"), HttpStatus.OK)
            }
        }
    }
}
