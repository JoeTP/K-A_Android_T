package com.example.khatibalamytask.data.repository

import com.example.khatibalamytask.data.local.LocalDataSource
import com.example.khatibalamytask.data.remote.RemoteDataSource
import com.example.khatibalamytask.data.remote.model.NewsResponse
import com.example.khatibalamytask.domain.model.NewsArticle
import com.example.khatibalamytask.domain.repository.NewsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource) :
    NewsRepository {
    override suspend fun getTopHeadlines(country: String): Flow<List<NewsArticle>> = remoteDataSource.getTopHeadlines(country)
}