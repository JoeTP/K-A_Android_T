package com.example.core.shared_ui_state

import com.example.khatibalamytask.domain.model.NewsArticle

sealed class NewsListUiState {
    object Loading : NewsListUiState()
    data class Success(val newsList: List<NewsArticle>) : NewsListUiState()
    data class Error(val message: String) : NewsListUiState()

}