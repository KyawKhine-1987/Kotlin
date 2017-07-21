package com.freelance.android.kotlin.di

import android.app.Application
import android.content.Context
import com.freelance.android.kotlin.KotlinApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 * @author juancho.
 */
@Module
class AppModule(val app: KotlinApp) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApplication() : Application = app

}
