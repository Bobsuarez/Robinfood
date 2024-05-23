package com.robinfood.core.entities.menu

open class MenuSuggestedPortionResponseEntity(
        val changes: List<MenuSuggestedPortionDataEntity>?,
        id: Long?,
        image: String?,
        name: String?,
        parentId: Long?,
        sku: String?
): MenuPortionEntity(null, id, image, name, parentId, null, sku)