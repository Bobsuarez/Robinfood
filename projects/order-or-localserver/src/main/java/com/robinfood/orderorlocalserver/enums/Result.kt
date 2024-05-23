package com.robinfood.orderorlocalserver.enums

import org.springframework.http.HttpStatus

/**
 * A generic class that defines the different type of answers a Http call can have.
 */
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Throwable, val httpStatus: HttpStatus) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}