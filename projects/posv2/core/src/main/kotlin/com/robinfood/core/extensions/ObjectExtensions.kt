@file:JvmName("ObjectExtensions")

package com.robinfood.core.extensions

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.robinfood.core.constants.GlobalConstants.CHECKBOX_SELECTION_TYPE
import com.robinfood.core.constants.GlobalConstants.GROUP_TYPE_ALTERNATIVE
import com.robinfood.core.constants.GlobalConstants.GROUP_TYPE_DEFAULT
import com.robinfood.core.constants.GlobalConstants.GROUP_TYPE_REMOVABLE
import com.robinfood.core.constants.GlobalConstants.SINGLE_SELECTION_TYPE
import com.robinfood.core.constants.GlobalConstants.STEPPER_SELECTION_TYPE
import com.robinfood.core.dtos.menu.response.MenuProductGroupDTO

/**
 * Converts a Json String to a Map<String, Any>
 */
fun String.serializeToMap(): HashMap<Any, Any>? {
    return try {
        val gson = Gson()
        gson.fromJson(this, object : TypeToken<HashMap<Any, Any>>() {}.type)
    } catch (exception: Exception) {
        null
    }
}

fun Any?.toJson(): String {
    return try {
        ObjectMapper().writeValueAsString(this)
    } catch (exception: JsonProcessingException) {
        exception.printStackTrace()
        "{}"
    }
}

/**
 * Convert MenuProductGroupDTO GroupTypeId to SelectionTypeId
 */
fun MenuProductGroupDTO.toGroupSelectionType(): Int {
    return when(groupTypeId) {
        GROUP_TYPE_DEFAULT -> STEPPER_SELECTION_TYPE
        GROUP_TYPE_REMOVABLE -> CHECKBOX_SELECTION_TYPE
        GROUP_TYPE_ALTERNATIVE -> SINGLE_SELECTION_TYPE
        else -> CHECKBOX_SELECTION_TYPE
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