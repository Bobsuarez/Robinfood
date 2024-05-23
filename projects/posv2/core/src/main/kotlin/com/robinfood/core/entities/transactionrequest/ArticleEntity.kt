package com.robinfood.core.entities.transactionrequest

data class ArticleEntity(
    val id: Long,
    val menuHallProductId: Long,
    val typeId: Long,
    val typeName: String
)
