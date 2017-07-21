package com.freelance.android.kotlin.service

import com.freelance.android.kotlin.model.RedditNewsResponse
import retrofit2.Call
/**
 * News API
 *
 * @author juancho.
 */
interface NewsAPI {
    fun getNews(after: String, limit: String): Call<RedditNewsResponse>
}