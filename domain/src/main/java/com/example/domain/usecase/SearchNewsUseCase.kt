package com.example.khatibalamytask.domain.usecase

import com.example.domain.usecase.UseCase
import com.example.domain.usecase.params.SearchNewsParams
import com.example.khatibalamytask.domain.model.NewsArticle
import com.example.khatibalamytask.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNewsUseCase(private val newsRepository: NewsRepository) : UseCase<SearchNewsParams, Flow<List<NewsArticle>>> {
    override suspend fun invoke(params: SearchNewsParams): Flow<List<NewsArticle>> {
        return newsRepository.searchNewsAndCacheQuery(params.query, params.loadMore)
    }
}

