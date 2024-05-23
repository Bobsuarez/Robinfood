package com.robinfood.repository.mocks.posrelatedtoastore

import com.robinfood.core.entities.posrelatedtoastore.StorePosEntity

class StorePosEntityMock {

    val storePosEntities = listOf(
        StorePosEntity(
            1L,
            "pos 1",
            ResolutionEntityMock().resolutionEntity
        ),
        StorePosEntity(
            2L,
            "pos 2",
            ResolutionEntityMock().resolutionEntity
        )
    )
}
