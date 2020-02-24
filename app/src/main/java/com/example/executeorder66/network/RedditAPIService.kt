package com.example.executeorder66.network

import com.example.executeorder66.network.models.RedditResponse
import retrofit2.Response
import retrofit2.http.GET

interface RedditAPIService {
    @GET("/r/kotlin/.json")
    suspend fun getRKotlin(): Response<RedditResponse>
}