package com.example.giphy.domain.model

sealed interface Result<T> {
    data class Success<T>(val value: T) : Result<T>
    data class Error<T>(val e: Exception? = null) : Result<T>
    companion object {
        fun <T, V> Result<T>.map(transform: (T) -> V) : Result<V> = when(this) {
            is Success -> Success(transform(value))
            is Error -> Error(e)
        }
    }
}