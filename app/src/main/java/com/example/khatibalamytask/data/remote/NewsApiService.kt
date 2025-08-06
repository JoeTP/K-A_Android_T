package com.example.khatibalamytask.data.remote

import com.example.khatibalamytask.core.utils.constants.AppStrings.Companion.COUNTRY_KEY
import com.example.khatibalamytask.core.utils.constants.AppStrings.Companion.EVERYTHING_EP
import com.example.khatibalamytask.core.utils.constants.AppStrings.Companion.QUERY_KEY
import com.example.khatibalamytask.core.utils.constants.AppStrings.Companion.TOP_HEADLINES_EP
import com.example.khatibalamytask.data.remote.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET(EVERYTHING_EP)
    suspend fun getNews(
        @Query(QUERY_KEY) query: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ) : NewsResponse

    @GET(TOP_HEADLINES_EP)
    suspend fun getTopHeadlines(
        @Query(COUNTRY_KEY) country: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ) : NewsResponse
}