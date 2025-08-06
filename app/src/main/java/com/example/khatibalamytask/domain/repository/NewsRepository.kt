package com.example.khatibalamytask.domain.repository

import com.example.khatibalamytask.data.remote.model.NewsResponse
import com.example.khatibalamytask.domain.model.NewsArticle
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getTopHeadlines(country: String) : Flow<List<NewsArticle>>
    suspend fun searchNewsAndCacheQuery(query: String) : Flow<List<NewsArticle>>
}