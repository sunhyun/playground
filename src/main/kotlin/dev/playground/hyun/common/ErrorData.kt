package dev.playground.hyun.common

data class ErrorData(
    val code: String,
    val message: String?,
    val data: Any?,
)
