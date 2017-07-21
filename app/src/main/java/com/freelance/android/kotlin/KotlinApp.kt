package com.freelance.android.kotlin

import android.app.Application
import com.freelance.android.kotlin.di.AppModule
import com.freelance.android.kotlin.di.news.NewsComponent
import com.freelance.android.kotlin.di.news.DaggerNewsComponent

/**
 * Created by Kyaw Khine on 07/20/2017.
 */
class KotlinApp : Application() {

    companion object {
        lateinit var newsComponent: NewsComponent
    }

    override fun onCreate() {
        super.onCreate()
        newsComponent = DaggerNewsComponent.builder()
                .appModule(AppModule(this))
                //.newsModule(NewsModule()) Module with empty constructor is implicitly created by dagger.
                .build()
    }
}