package com.example.aviasales.data.network

sealed interface Resource<T> {
    data class Data<T>(val value: T) : Resource<T>
    data class ConnectionError<T>(val message: String) : Resource<T>
    data class NotFound<T>(val message: String) : Resource<T>
}
