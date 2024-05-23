@file:JvmName("ObjectExtensions")

package com.robinfood.core.extensions

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Converts a Json String to a Map<String, Any>
 */
fun String.serializeToMap(): HashMap<Any, Any>? {
    return try {
        val gson = Gson()
        gson.fromJson(this, object : TypeToken<HashMap<Any, Any>>() {}.type)
    } catch (exception: Exception) {
        exception.printStackTrace()
        null
    }
}

/**
 * Converts any object to a Json String
 */
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