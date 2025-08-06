package com.example.khatibalamytask.data.remote

import com.example.khatibalamytask.core.utils.constants.COUNTRY_KEY
import com.example.khatibalamytask.core.utils.constants.EVERYTHING_EP
import com.example.khatibalamytask.core.utils.constants.QUERY_KEY
import com.example.khatibalamytask.core.utils.constants.TOP_HEADLINES_EP
import com.example.khatibalamytask.data.remote.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET(EVERYTHING_EP)
    suspend fun getNews(@Query(QUERY_KEY) query: String) : NewsResponse

    @GET(TOP_HEADLINES_EP)
    suspend fun getTopHeadlines(@Query(COUNTRY_KEY) country: String) : NewsResponse
}