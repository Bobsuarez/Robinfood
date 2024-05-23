package com.robinfood.core.extensions

import com.robinfood.core.constants.APIConstants.UNAUTHORIZED_CODE
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.AuthException
import com.robinfood.core.exceptions.BusinessCapabilityException
import org.springframework.http.HttpStatus
import retrofit2.Call
import java.io.IOException

/**
 * Calls a http service safely because it checks if any exception happens
 * and checks if the response body is null or not.
 * Returns a [Result] containing the operation response
 */
fun <T : Any> Call<T>.safeAPICall(): Result<T> {
    return try {
        val response = execute()
        val body = response.body()
        val code = response.code()
        val errorBody = response.errorBody()
        when {
            code == UNAUTHORIZED_CODE -> {
                Result.Error(
                    AuthException("JWT token for service ${request().url()} is unauthorized"),
                    HttpStatus.UNAUTHORIZED
                )
            }
            errorBody != null -> {
                val message = errorBody.string().serializeToMap()

                Result.Error(
                    BusinessCapabilityException(
                        "${
                            message?.get("message").toString()
                        } ${
                            message?.get("error").tryCast<List<String>>()?.firstOrNull().orEmpty()
                        } ${message?.get("data") ?: ""} in service ${request()?.url()}".replace("\\s{2,}".toRegex(), " ")
                    ), HttpStatus.valueOf(code)
                )
            }
            body == null -> Result.Error(
                BusinessCapabilityException("Response body when calling service: ${request().url()} body is null"),
                HttpStatus.valueOf(code)
            )
            response.isSuccessful -> Result.Success(body)
            else -> Result.Error(
                BusinessCapabilityException(body.toString()),
                HttpStatus.valueOf(code)
            )
        }
    } catch (exception: IOException) {

        Result.Error(exception, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
