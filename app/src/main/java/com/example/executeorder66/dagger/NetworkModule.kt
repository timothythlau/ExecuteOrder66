package com.example.executeorder66.dagger

import com.example.executeorder66.BuildConfig
import com.example.executeorder66.network.RedditAPIService
import com.example.executeorder66.network.RedditRepository
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {
    @Provides
    @Singleton
    fun providesRedditRetrofit(): Retrofit {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging =
                HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BASIC) }
            builder.addInterceptor(logging)
            builder.addInterceptor(OkHttpProfilerInterceptor())
        }

        return Retrofit.Builder()
            .client(builder.build())
            .baseUrl("https://www.reddit.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesRedditAPIService(retrofit: Retrofit): RedditAPIService {
        return retrofit.create(RedditAPIService::class.java)
    }

    @Provides
    @Singleton
    fun providesRedditRepository(service: RedditAPIService): RedditRepository {
        return RedditRepository(service)
    }
}