package com.efishery.test.data.remote

sealed class Result <T> {
    object Nothing: Result<kotlin.Nothing>()
    class Loading<T> : Result<T>()
    class Success<T>(val data: T?) : Result<T>()
    class Error<T,X>(
        val message: String,
        val data: X? = null,
        val status_code: Int = 0
    ): Result<T>()

    companion object {
        val nothing = Nothing
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T?) = Success(data)
        fun <T,X> error(
            message: String,
            data: X? = null,
            code: Int = 0
        ) = Error<T,X>(message, data)
    }
}