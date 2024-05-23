package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.ArticleDTO
import com.robinfood.core.entities.transactionrequest.ArticleEntity

fun ArticleDTO.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
            id = id,
            menuHallProductId = menuHallProductId,
            typeId = typeId,
            typeName = typeName
    )
}