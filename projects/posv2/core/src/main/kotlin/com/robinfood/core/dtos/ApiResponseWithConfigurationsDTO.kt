package com.robinfood.core.dtos

import com.fasterxml.jackson.annotation.JsonInclude
import com.robinfood.core.constants.GlobalConstants.DEFAULT_LOCALE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_MESSAGE
import org.springframework.http.HttpStatus
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiResponseWithConfigurationsDTO<T, Z>(
    var data: T?,
    var functionalities: Z? = null,
    var locale: String = DEFAULT_LOCALE,
    var message: String = DEFAULT_MESSAGE,
    var status: Int? = null
) {
    constructor(data: T?, functionalities: Z?) : this(
        data = data,
        functionalities = functionalities,
        locale = DEFAULT_LOCALE,
        message = DEFAULT_MESSAGE
    )
    constructor(data: T?, functionalities: Z?, message: String, status: HttpStatus) : this(
        data = data,
        functionalities = functionalities,
        locale = DEFAULT_LOCALE,
        message = message,
        status = status.value()
    )
    constructor(data: T?, functionalities: Z?, message: String) : this(
        data = data,
        functionalities = functionalities,
        locale = DEFAULT_LOCALE,
        message = message
    )
    constructor(message: String) : this(
        data = null,
        functionalities = null,
        locale = DEFAULT_LOCALE,
        message = message
    )
}