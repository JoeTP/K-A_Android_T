package com.example.khatibalamytask.feature_news_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khatibalamytask.domain.model.NewsArticle
import com.example.khatibalamytask.domain.usecase.GetHeadlinesUseCase
import com.example.khatibalamytask.domain.usecase.GetLastSearchUseCase
import com.example.khatibalamytask.domain.usecase.SearchNewsParams
import com.example.khatibalamytask.domain.usecase.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getHeadlinesUseCase: GetHeadlinesUseCase,
    private val searchNewsUseCase: SearchNewsUseCase,
    private val getLastSearchUseCase: GetLastSearchUseCase
) : ViewModel() {

    private val _snackbarMessage = MutableSharedFlow<String>()
    val snackbarMessage: SharedFlow<String> = _snackbarMessage
    private val _newsUiState = MutableStateFlow<NewsListUiState>(NewsListUiState.Loading)
    val newsUiState = _newsUiState

    private val _searchQuery = MutableStateFlow("")
    var searchQueryHistory = ""
        private set

    private var currentArticles = listOf<NewsArticle>()

    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore


    init {
        getLastSearch()
        observeSearchQuery()
    }


    //    fun searchingQueryChange(query: String) {
//        this._searchQuery.value = query
//        viewModelScope.launch {
//            searchNews()
//        }
//    }
    fun searchingQueryChange(query: String) {
        _searchQuery.value = query
    }


    fun getLastSearch() = viewModelScope.launch {
        val query = getLastSearchUseCase(Unit)
        query?.let {
            searchQueryHistory = it
            _searchQuery.value = it
        } ?: run {
            loadHeadlines()
        }
    }

    private suspend fun searchNews(loadMore: Boolean = false) {
        if (loadMore) {
            _isLoadingMore.value = true
        } else {
            _newsUiState.value = NewsListUiState.Loading
            currentArticles = emptyList()
        }

        val params = SearchNewsParams(
            query = _searchQuery.value,
            loadMore = loadMore
        )
        searchNewsUseCase(params)
            .debounce(2000L)
            .catch { e ->
                _snackbarMessage.emit(e.message ?: "An error occurred")
//                    _newsUiState.value = NewsListUiState.Error(e.message ?: "An error occurred")
            }
            .collect { newArticles ->
                currentArticles = if (loadMore) {
                    currentArticles + newArticles
                } else {
                    newArticles
                }
                _newsUiState.value = NewsListUiState.Success(currentArticles)
            }
        _isLoadingMore.value = false
    }

    private fun loadHeadlines() {
        viewModelScope.launch {
            _newsUiState.value = NewsListUiState.Loading
            getHeadlinesUseCase("us")
                .catch { e ->
                    _newsUiState.value = NewsListUiState.Error(e.message ?: "An error occurred")
                }
                .collect { articles ->
                    _newsUiState.value = NewsListUiState.Success(articles)
                }
        }
    }


    private fun observeSearchQuery() = viewModelScope.launch {
        _searchQuery
            .debounce(700.milliseconds)
            .collect { query ->
                if (query.isNotEmpty()) {
                    searchQueryHistory = query
                    searchNews(loadMore = false)
                }
            }
    }


    fun loadMore() = viewModelScope.launch {
        searchNews(loadMore = true)
    }
}