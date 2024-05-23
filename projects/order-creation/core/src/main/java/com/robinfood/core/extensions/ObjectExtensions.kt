@file:JvmName("ObjectExtensions")

package com.robinfood.core.extensions

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun Any?.toJson(): String {
    return try {
        ObjectMapper().writeValueAsString(this)
    } catch (exception: JsonProcessingException) {
        "{}"
    }
}

/**
 * Converts a Json String to a Map<String, Any>
 */
fun String.serializeToMap(): HashMap<Any, Any>? {
    return try {
        val gson = Gson()
        gson.fromJson(this, object : TypeToken<HashMap<Any, Any>>() {}.type)
    } catch (exception: Exception) {
        hashMapOf("data" to this, "message" to "")
    }
}

/**
 * Returns the casted [this] element to [T] or null if
 * [this] is not of type [T]
 * Note: This extensions if for Kotlin code only
 */
inline fun <reified T> Any?.tryCast(): T? {
    return if (this is T) this else null
}