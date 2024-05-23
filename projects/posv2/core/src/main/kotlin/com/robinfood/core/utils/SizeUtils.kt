package com.robinfood.core.utils

import com.robinfood.core.constants.GlobalConstants.DEFAULT_MUY_MUY
import com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE

/**
 * Returns the size name from size id
 */
fun getSizeName(sizeId: Long?): String {
    if (sizeId == null) {
        return DEFAULT_STRING_VALUE
    }
    var sizeName: String = DEFAULT_STRING_VALUE
    var muyMuy: String = DEFAULT_MUY_MUY

    val sizeMap = mapOf(
            1L to "MUY",
            2L to muyMuy,
            3L to "",
            4L to "MUY",
            5L to muyMuy,
            6L to "",
            7L to "Hambre",
            8L to "Mucha Hambre",
            9L to "Hambre",
            10L to "Mucha Hambre",
            11L to "x3",
            12L to "x4",
            13L to "Mediana",
            14L to "Grande",
            15L to "Sencilla",
            16L to "Doble",
            17L to "Burrito",
            18L to "Bowl",
            19L to "MUY",
            20L to muyMuy,
            21L to "",
            22L to "Sencilla",
            23L to "Doble"
    )

    if (sizeMap.containsKey(sizeId)) {
        sizeName = sizeMap[sizeId]!!
    }

    return sizeName
}