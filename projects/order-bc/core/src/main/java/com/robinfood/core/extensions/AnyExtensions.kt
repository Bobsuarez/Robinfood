@file:JvmName("ObjectExtensions")

package com.robinfood.core.extensions

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

fun Any?.toJson(): String {
    return try {
        ObjectMapper().writeValueAsString(this)
    } catch (exception: JsonProcessingException) {
        "{}"
    }
}

/**
 * Returns the casted [this] element to [T] or null if
 * [this] is not of type [T]
 */
@Suppress("UNCHECKED_CAST")
fun <T> Any?.tryCast(): T? {
    return this as? T
}