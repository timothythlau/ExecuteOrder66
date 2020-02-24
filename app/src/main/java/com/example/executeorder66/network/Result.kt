package com.example.executeorder66.network

sealed class Result<out T> {
    data class Success<out T>(val body: T) : Result<T>()
    data class Error(val exception: String) : Result<Nothing>()
}