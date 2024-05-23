package com.robinfood.core.extensions

inline fun <T> List<T>.find(predicate: (T) -> Boolean): T? {
    for (element in this) if (predicate(element)) return element
    return null
}

inline fun <T> List<T>.findLast(predicate: (T) -> Boolean): T? {
    var last: T? = null
    for (element in this) {
        if (predicate(element)) {
            last = element
        }
    }
    return last
}

fun<T> List<T>.toQueryParam(): String {
    return this.joinToString(",")
}