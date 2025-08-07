package com.example.khatibalamytask.data.remote

import com.example.khatibalamytask.data.remote.model.NewsResponse
import com.example.khatibalamytask.domain.model.NewsArticle
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getTopHeadlines(country: String): Flow<List<NewsArticle>>

    suspend fun searchNews(query: String, page: Int = 1, pageSize: Int = 20): Flow<List<NewsArticle>>
}