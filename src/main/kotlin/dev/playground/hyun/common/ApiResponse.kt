package dev.playground.hyun.common

import dev.playground.hyun.common.enum.ApiResultType

data class ApiResponse<T>(
    val result: ApiResultType,
    val data: T? = null,
    val error: ErrorData? = null
) {
    companion object {
        fun <T> success(data: T?): ApiResponse<T> {
            return ApiResponse(result = ApiResultType.SUCCESS, data = data)
        }

        fun <T> success(): ApiResponse<T> {
            return ApiResponse(result = ApiResultType.SUCCESS)
        }

        fun <T> failure(data: T?, error: ErrorData?): ApiResponse<T> {
            return ApiResponse(result = ApiResultType.FAIL, data = data, error = error)
        }

        fun <T> failure(error: ErrorData?): ApiResponse<T> {
            return ApiResponse(result = ApiResultType.FAIL, error = error)
        }
    }
}
