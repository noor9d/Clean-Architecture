package com.dani.cleanarchitecture.core

sealed class ApiStates<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null): ApiStates<T>(data)
    class Success<T>(data: T?): ApiStates<T>(data)
    class Error<T>(message: String, data: T? = null): ApiStates<T>(data, message)
}