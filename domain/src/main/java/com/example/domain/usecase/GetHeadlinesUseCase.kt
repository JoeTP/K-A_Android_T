package com.example.khatibalamytask.domain.usecase

import com.example.domain.usecase.UseCase
import com.example.khatibalamytask.domain.model.NewsArticle
import com.example.khatibalamytask.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetHeadlinesUseCase (private val newsRepository: NewsRepository) : UseCase<String, Flow<List<NewsArticle>>> {
    override suspend fun invoke(country: String): Flow<List<NewsArticle>> {
        return newsRepository.getTopHeadlines(country)
    }
}