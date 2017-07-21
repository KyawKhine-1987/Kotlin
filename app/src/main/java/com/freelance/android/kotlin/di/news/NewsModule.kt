package com.freelance.android.kotlin.di.news

import com.freelance.android.kotlin.service.NewsAPI
import com.freelance.android.kotlin.service.NewsRestAPI
import com.freelance.android.kotlin.service.RedditNewsAPIService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 *
 * @author juancho.
 */
@Module
class NewsModule {

    @Provides
    @Singleton
    fun provideNewsAPI(redditApi: RedditNewsAPIService): NewsAPI = NewsRestAPI(redditApi)

    @Provides
    @Singleton
    fun provideRedditApi(retrofit: Retrofit): RedditNewsAPIService = retrofit.create(RedditNewsAPIService::class.java)

    /**
     * NewsManager is automatically provided by Dagger as we set the @Inject annotation in the
     * constructor, so we can avoid adding a 'provider method' here.
     */
}
