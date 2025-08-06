package com.example.khatibalamytask.data.remote

import com.example.khatibalamytask.data.mapper.toDomain
import com.example.khatibalamytask.domain.model.NewsArticle
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSourceImpl @Inject constructor(private val newsApiService: NewsApiService) : RemoteDataSource {

    override suspend fun getTopHeadlines(country: String): Flow<List<NewsArticle>> = flow {
        val response = newsApiService.getTopHeadlines(country)
        emit(response.articles.map { it.toDomain() })
    }

    override suspend fun searchNews(query: String): Flow<List<NewsArticle>> = flow {
        val response = newsApiService.getNews(query)
        emit(response.articles.map { it.toDomain() })
    }

}