package com.robinfood.core.dtos

import com.robinfood.core.dtos.flags.FlagsDTO
data class OrderDetailWithFlagsDTO(
    val listOrderDetail: List<OrderDetailDTO>,
    val flags: FlagsDTO
)
