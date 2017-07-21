package com.freelance.android.kotlin.service

import com.freelance.android.kotlin.model.RedditNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Kyaw Khine on 07/20/2017.
 */
interface RedditNewsAPIService {
    @GET("/top.json")
    fun getTop(@Query("after") after: String,
               @Query("limit") limit: String)
            : Call<RedditNewsResponse>;
}