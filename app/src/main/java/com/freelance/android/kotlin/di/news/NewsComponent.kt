package com.freelance.android.kotlin.di.news

import com.freelance.android.kotlin.di.AppModule
import com.freelance.android.kotlin.di.NetworkModule
import com.freelance.android.kotlin.features.news.NewsFragment
import dagger.Component
import javax.inject.Singleton

/**
 *
 * @author juancho.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NewsModule::class,
        NetworkModule::class)
)
interface NewsComponent {

    fun inject(newsFragment: NewsFragment)

}