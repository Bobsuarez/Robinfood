package com.robinfood.core.dtos.menu.response

data class MenuSuggestedPortionResponseDTO(
        override var id: Long,
        override var image: String,
        override var name: String,
        override var parentId: Long,
        override var sku: String,
        val suggesteds: List<MenuSuggestedPortionDataDTO>
): MenuPortionDTO(id, image, name, parentId, sku)