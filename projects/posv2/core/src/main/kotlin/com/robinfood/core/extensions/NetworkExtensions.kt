package com.robinfood.core.extensions

import com.robinfood.core.constants.APIConstants.UNAUTHORIZED_CODE
import com.robinfood.core.constants.APIConstants.UNAVAILABLE_CODE
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.AuthException
import com.robinfood.core.exceptions.OrchestratorException
import org.springframework.http.HttpStatus
import retrofit2.Response
import java.io.IOException

/**
 * Calls a http service safely because it checks if any exception happens
 * and checks if the response body is null or not.
 * Returns a [Result] containing the operation response
 */
suspend fun <T : Any> safeApiCall(
    call: suspend () -> Result<T>
): Result<T> = try {
    call.invoke()
} catch (e: Exception) {
    Result.Error(IOException(e), HttpStatus.INTERNAL_SERVER_ERROR)
}

fun <T : Any> Response<T>.callServices(): Result<T> {
    return try {
        val body = body()
        val code = code()
        val errorBody = errorBody()

        when {
            code == UNAUTHORIZED_CODE -> {
                val message = errorBody?.string()?.serializeToMap()
                Result.Error(
                    AuthException("JWT token is unauthorized: ${message?.get("message").toString()} ${message?.get("error").tryCast<List<String>>()?.firstOrNull().orEmpty()}"),
                    HttpStatus.UNAUTHORIZED
                )
            }
            code == UNAVAILABLE_CODE -> {
                Result.Error(
                    AuthException("Service is down " + errorBody.toString()),
                    HttpStatus.SERVICE_UNAVAILABLE
                )
            }
            errorBody != null -> {
                val message = errorBody.string().serializeToMap()
                Result.Error(
                    OrchestratorException(
                            message?.get("data"),
                            "${message?.get("message").toString()} ${message?.get("error").tryCast<List<String>>()?.firstOrNull().orEmpty()}"
                    ),
                    HttpStatus.valueOf(code)
                )
            }
            body == null -> Result.Error(
                    OrchestratorException("Body is null"),
                HttpStatus.valueOf(code)
            )
            else -> Result.Success(body)
        }
    } catch (exception: IOException) {
        Result.Error(exception, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
