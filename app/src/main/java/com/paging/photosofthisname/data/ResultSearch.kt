package com.paging.photosofthisname.data

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ResultSearch<out T : Any> {

    data class Success<out T : Any>(val data: T) : ResultSearch<T>()
    data class Error(val exception: Exception) : ResultSearch<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}
