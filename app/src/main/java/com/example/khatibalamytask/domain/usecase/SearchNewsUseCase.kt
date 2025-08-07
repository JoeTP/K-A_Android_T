package com.example.khatibalamytask.domain.usecase

import com.example.khatibalamytask.core.utils.UseCase
import com.example.khatibalamytask.domain.model.NewsArticle
import com.example.khatibalamytask.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) : UseCase<SearchNewsParams, Flow<List<NewsArticle>>> {
    override suspend fun invoke(params: SearchNewsParams): Flow<List<NewsArticle>> {
        return newsRepository.searchNewsAndCacheQuery(params.query, params.loadMore)
    }
}

data class SearchNewsParams(val query: String, val loadMore: Boolean = false)