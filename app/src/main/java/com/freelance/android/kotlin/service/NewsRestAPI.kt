package com.freelance.android.kotlin.service

import com.freelance.android.kotlin.model.RedditNewsResponse
import retrofit2.Call
import javax.inject.Inject

class NewsRestAPI @Inject constructor(private val redditApi: RedditNewsAPIService) : NewsAPI {

    override fun getNews(after: String, limit: String): Call<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }
}