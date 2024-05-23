package com.robinfood.core.dtos.flags

data class FeatureFlagsDTO(
    val key: String,
    val value: Any?,
    /**
     * Boolean = 1
     * String = 2
     * Int = 3
     */
    val fieldType : Int
)
