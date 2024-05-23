@file:JvmName("ListExtensions")
package com.robinfood.core.extensions

fun <T> List<T>.find(predicate: (T) -> Boolean): T? {
    for (element in this) if (predicate(element)) return element
    return null
}