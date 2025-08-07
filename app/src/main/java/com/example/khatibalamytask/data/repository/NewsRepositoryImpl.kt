package com.example.khatibalamytask.data.repository

import android.util.Log
import com.example.khatibalamytask.data.local.LocalDataSource
import com.example.khatibalamytask.data.pagination.PaginationState
import com.example.khatibalamytask.data.remote.RemoteDataSource
import com.example.khatibalamytask.domain.model.NewsArticle
import com.example.khatibalamytask.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : NewsRepository {

    private var currentQuery: String = ""
    private var paginationState = PaginationState()


    override suspend fun getTopHeadlines(country: String): Flow<List<NewsArticle>> =
        remoteDataSource.getTopHeadlines(country)


    override suspend fun searchNewsAndCacheQuery(
        query: String,
        loadMore: Boolean
    ): Flow<List<NewsArticle>> = flow {
        if (query != currentQuery) {
            currentQuery = query
            paginationState = paginationState.copy(
                currentPage = 1,
                isLastPage = false
            )
        }

        if (loadMore && (paginationState.isLoading || paginationState.isLastPage)) {
            return@flow
        }

        paginationState = paginationState.copy(isLoading = true)

        try {
            val articles = remoteDataSource.searchNews(
                query = query,
                page = paginationState.currentPage,
                pageSize = paginationState.pageSize
            ).first()

            paginationState = paginationState.copy(
                currentPage = paginationState.currentPage + 1,
                isLastPage = articles.size < paginationState.pageSize,
                isLoading = false
            )

            localDataSource.cacheSearch(query)

            emit(articles)
        } catch (e: Exception) {
            Log.e("TAG", "searchNewsAndCacheQuery: ", e)
            paginationState = paginationState.copy(isLoading = false)
        }
    }

    override fun getLastSearch(): String? {
        return localDataSource.getCachedSearch()
    }


}