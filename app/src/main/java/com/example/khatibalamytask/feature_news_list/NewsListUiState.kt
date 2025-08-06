package com.example.khatibalamytask.feature_news_list

import com.example.khatibalamytask.domain.model.NewsArticle

sealed class NewsListUiState {
    object Loading : NewsListUiState()
    data class Success(val newsList: List<NewsArticle>) : NewsListUiState()
    data class Error(val message: String) : NewsListUiState()

}