package com.example.khatibalamytask.data.repository

import com.example.khatibalamytask.data.local.LocalDataSource
import com.example.khatibalamytask.data.remote.RemoteDataSource
import com.example.khatibalamytask.domain.model.NewsArticle
import com.example.khatibalamytask.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : NewsRepository {

    override suspend fun getTopHeadlines(country: String): Flow<List<NewsArticle>> =
        remoteDataSource.getTopHeadlines(country)

    override suspend fun searchNewsAndCacheQuery(query: String): Flow<List<NewsArticle>> {
        localDataSource.cacheSearch(query)
        return remoteDataSource.searchNews(query)
    }

    override fun getLastSearch(): String? {
        return localDataSource.getCachedSearch()
    }


}