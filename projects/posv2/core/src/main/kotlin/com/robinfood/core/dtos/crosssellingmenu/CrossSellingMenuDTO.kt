package com.robinfood.core.dtos.crosssellingmenu

import com.robinfood.core.dtos.menu.response.MenuProductDetailCrossSellingDTO

data class CrossSellingMenuDTO (
        val products: List<MenuProductDetailCrossSellingDTO>
)