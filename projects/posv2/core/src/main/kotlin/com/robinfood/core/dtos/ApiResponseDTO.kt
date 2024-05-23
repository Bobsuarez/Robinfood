package com.robinfood.core.dtos

import com.fasterxml.jackson.annotation.JsonInclude
import com.robinfood.core.constants.GlobalConstants.DEFAULT_LOCALE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_MESSAGE
import org.springframework.http.HttpStatus

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiResponseDTO<T>(
        var data: T?,
        var locale: String = DEFAULT_LOCALE,
        var message: String = DEFAULT_MESSAGE,
        var status: Int? = null
) {
    constructor(data: T?) : this(
            data = data,
            locale = DEFAULT_LOCALE,
            message = DEFAULT_MESSAGE
    )

    constructor(data: T?, message: String, status: HttpStatus) : this(
            data = data,
            locale = DEFAULT_LOCALE,
            message = message,
            status = status.value()
    )

    constructor(data: T?, message: String) : this(
            data = data,
            locale = DEFAULT_LOCALE,
            message = message
    )

    constructor(message: String) : this(
            data = null,
            locale = DEFAULT_LOCALE,
            message = message
    )
}