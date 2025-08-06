package com.example.khatibalamytask.feature_news_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khatibalamytask.domain.usecase.GetHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class NewsListViewModel @Inject constructor(private val getHeadlinesUseCase: GetHeadlinesUseCase) : ViewModel(){

    private val _headlineUiState = MutableStateFlow<NewsListUiState>(NewsListUiState.Loading)
    val headlineUiState = _headlineUiState

    init {
        getHeadlines()
    }

    private fun getHeadlines() = viewModelScope.launch {
        getHeadlinesUseCase("us").collect { result ->
            Log.i("TAG", "getHeadlines: $result")
            _headlineUiState.value = NewsListUiState.Success(result)
        }
    }

}