package com.example.khatibalamytask.domain.usecase

import com.example.khatibalamytask.core.utils.UseCase
import com.example.khatibalamytask.domain.model.NewsArticle
import com.example.khatibalamytask.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) : UseCase<String, Flow<List<NewsArticle>>> {
    override suspend fun invoke(query: String): Flow<List<NewsArticle>> {
        return newsRepository.searchNewsAndCacheQuery(query)
    }
}