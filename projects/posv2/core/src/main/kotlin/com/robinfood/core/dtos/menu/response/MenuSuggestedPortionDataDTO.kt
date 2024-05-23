package com.robinfood.core.dtos.menu.response

data class MenuSuggestedPortionDataDTO(
        override var id: Long,
        override var image: String,
        override var name: String,
        override var parentId: Long,
        override var sku: String,
        var unitId: Long,
        var unitNumber: Double
) : MenuPortionDTO(id, image, name, parentId, sku)