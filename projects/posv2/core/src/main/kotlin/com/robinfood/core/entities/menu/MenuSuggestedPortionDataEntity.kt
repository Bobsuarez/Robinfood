package com.robinfood.core.entities.menu

open class MenuSuggestedPortionDataEntity(
        dicUnitId: Long?,
        id: Long?,
        image: String?,
        name: String?,
        parentId: Long?,
        quantity: Double?,
        sku: String?
) : MenuPortionEntity(id, dicUnitId, name, image, parentId, quantity, sku)