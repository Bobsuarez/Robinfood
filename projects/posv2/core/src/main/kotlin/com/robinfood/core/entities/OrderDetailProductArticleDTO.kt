package com.robinfood.core.entities

data class OrderDetailProductArticleDTO (
    var id: Long,
    var menuHallProductId: Long,
    var typeId: Long,
    var typeName: String
)