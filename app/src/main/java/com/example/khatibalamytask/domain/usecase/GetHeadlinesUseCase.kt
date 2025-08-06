package com.example.khatibalamytask.domain.usecase

import com.example.khatibalamytask.core.utils.UseCase
import com.example.khatibalamytask.data.remote.model.NewsResponse
import com.example.khatibalamytask.domain.model.NewsArticle
import com.example.khatibalamytask.domain.repository.NewsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetHeadlinesUseCase @Inject constructor(private val newsRepository: NewsRepository) : UseCase<String, Flow<List<NewsArticle>>> {
    override suspend fun invoke(country: String): Flow<List<NewsArticle>> {
        return newsRepository.getTopHeadlines(country)
    }
}