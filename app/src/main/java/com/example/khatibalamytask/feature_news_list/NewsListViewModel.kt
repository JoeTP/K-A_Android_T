package com.example.khatibalamytask.feature_news_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khatibalamytask.domain.usecase.GetHeadlinesUseCase
import com.example.khatibalamytask.domain.usecase.GetLastSearchUseCase
import com.example.khatibalamytask.domain.usecase.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getHeadlinesUseCase: GetHeadlinesUseCase,
    private val searchNewsUseCase: SearchNewsUseCase,
    private val getLastSearchUseCase: GetLastSearchUseCase
) : ViewModel() {

    private val _newsUiState = MutableStateFlow<NewsListUiState>(NewsListUiState.Loading)
    val newsUiState = _newsUiState

    private val searchQuery = MutableStateFlow("")
    var searchQueryHistory = ""
        private set

    init {
        getLastSearch()
    }

    fun searchingQueryChange(query: String) {
        this.searchQuery.value = query
        searchNews()
    }

    fun getLastSearch() = viewModelScope.launch {
        val query = getLastSearchUseCase(Unit)
        if (query != null) {
            searchQueryHistory = query
//            Log.d(TAG, "getLastSearch: $")
            searchingQueryChange(query)
        } else {
            getHeadlines()
        }
    }

    fun searchNews() = viewModelScope.launch {
        searchQuery.debounce(800L).collect { query ->
            if (query.isNotEmpty()) {
                searchNewsUseCase(query).collect { result ->
                    Log.i("TAG", "searchNews: $result")
                    _newsUiState.value = NewsListUiState.Success(result)
                }
            }
        }
    }

    fun getHeadlines() = viewModelScope.launch {
        getHeadlinesUseCase("us").collect { result ->
            Log.i("TAG", "getHeadlines: $result")
            _newsUiState.value = NewsListUiState.Success(result)
        }
    }

}