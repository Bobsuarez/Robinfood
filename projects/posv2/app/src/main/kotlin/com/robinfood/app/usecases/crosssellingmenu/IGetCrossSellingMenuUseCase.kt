package com.robinfood.app.usecases.crosssellingmenu

import com.robinfood.core.dtos.crosssellingmenu.CrossSellingMenuDTO
import com.robinfood.core.enums.Result

interface IGetCrossSellingMenuUseCase {

    suspend fun invoke(
            token: String,
            countryId: Long,
            flowId: Long,
            storeId: Long
    ): Result<CrossSellingMenuDTO>

}