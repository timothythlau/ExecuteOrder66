package com.example.executeorder66.network

import retrofit2.Response
import javax.inject.Inject

class RedditRepository @Inject constructor(val service: RedditAPIService) {
    suspend fun getKotlinSubredditList() = safeApiCall { service.getRKotlin() }

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(response.errorBody()?.toString() ?: "Unknown Error")
            }

        } catch (e: Exception) {
            Result.Error(e.message ?: "Connection error")
        }
    }
}