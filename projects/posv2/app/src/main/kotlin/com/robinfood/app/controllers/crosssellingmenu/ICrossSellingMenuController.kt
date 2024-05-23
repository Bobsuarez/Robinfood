package com.robinfood.app.controllers.crosssellingmenu

import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.crosssellingmenu.CrossSellingMenuDTO
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import javax.servlet.http.HttpServletRequest

@Tag(name = "Cross-Selling Menu", description = "Requests for cross-selling menu related data")
interface ICrossSellingMenuController {

    suspend fun getCrossSellingMenu(
            countryId: Long,
            flowId: Long,
            httpServletRequest: HttpServletRequest,
            storeId: Long
    ): ResponseEntity<ApiResponseDTO<CrossSellingMenuDTO>>

}
