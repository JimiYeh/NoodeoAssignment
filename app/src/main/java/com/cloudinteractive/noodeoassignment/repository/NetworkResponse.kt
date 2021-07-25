package com.cloudinteractive.noodeoassignment.repository

import com.cloudinteractive.noodeoassignment.model.RespError
import java.io.IOException

sealed class NetworkResponse<out T : Any> {
    /**
     * Success response with body
     */
    data class Success<T : Any>(val body: T) : NetworkResponse<T>()

    /**
     * Failure response with body
     */
    data class ApiError(val body: RespError, val code: Int) : NetworkResponse<Nothing>()

    /**
     * Network error
     */
    data class NetworkError(val error: IOException) : NetworkResponse<Nothing>()

    /**
     * For example, json parsing error
     */
    data class UnknownError(val error: Throwable?) : NetworkResponse<Nothing>()
}
