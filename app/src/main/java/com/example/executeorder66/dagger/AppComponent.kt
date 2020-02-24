package com.example.executeorder66.dagger

import com.example.executeorder66.articleList.ListActivity
import com.example.executeorder66.network.RedditRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun redditRepository(): RedditRepository

    fun inject(listActivity: ListActivity)
}