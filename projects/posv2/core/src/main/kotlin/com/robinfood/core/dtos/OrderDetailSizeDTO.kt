package com.robinfood.core.dtos

import com.robinfood.core.dtos.menu.response.MenuSizeGroupDTO

data class OrderDetailSizeDTO(
    val id: Long,
    val groups: List<OrderDetailProductGroupDTO>,
    val name: String,
    var suggestedGroups: List<MenuSizeGroupDTO>//los que son prosibles ofrecer
)