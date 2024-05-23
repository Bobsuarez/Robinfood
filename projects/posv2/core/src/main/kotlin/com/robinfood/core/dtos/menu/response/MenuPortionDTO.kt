package com.robinfood.core.dtos.menu.response

abstract class MenuPortionDTO (
        open var id: Long,
        open var image: String,
        open var name: String,
        open var parentId: Long,
        open var sku: String
)