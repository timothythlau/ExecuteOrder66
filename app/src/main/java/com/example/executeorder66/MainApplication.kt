package com.example.executeorder66

import android.app.Application
import com.example.executeorder66.dagger.AppComponent
import com.example.executeorder66.dagger.DaggerAppComponent
import com.example.executeorder66.dagger.NetworkModule

class MainApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().networkModule(NetworkModule()).build()
    }
}