package com.example.feature_search.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.shared_ui_state.NewsListUiState
import com.example.core.viewmodel.SharedViewModel
import com.example.feature_search.R
import com.example.khatibalamytask.core.ui.components.EmptyListStateUi
import com.example.khatibalamytask.core.ui.components.ErrorStateUi
import com.example.khatibalamytask.core.ui.components.LoadingStateUi
import com.example.khatibalamytask.core.ui.components.NewsItemCard
import com.example.khatibalamytask.core.utils.constants.AppValues.Companion.DEFAULT_PADDING
import com.example.khatibalamytask.core.utils.functions.openInBrowser
import com.example.khatibalamytask.domain.model.NewsArticle
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun NewsListScreenUi(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
) {
    val uiState by viewModel.newsUiState.collectAsStateWithLifecycle()


    LaunchedEffect(viewModel.snackbarMessage) {
        viewModel.snackbarMessage.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    when (uiState) {
        is NewsListUiState.Loading -> LoadingStateUi(modifier)
        is NewsListUiState.Error -> ErrorStateUi(msg = (uiState as NewsListUiState.Error).message)
        is NewsListUiState.Success -> {
            val data = (uiState as NewsListUiState.Success).newsList
            if (data.isEmpty()) {
                EmptyListStateUi(modifier, R.drawable.nodata)
            } else {
                HeadlinesSuccessState(
                    modifier = modifier,
                    articles = data,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun HeadlinesSuccessState(
    modifier: Modifier = Modifier,
    articles: List<NewsArticle>,
    viewModel: SharedViewModel
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()

    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            if (visibleItems.isEmpty()) {
                false
            } else {
                val lastVisibleItem = visibleItems.last()
                val lastVisibleItemIndex = lastVisibleItem.index
                val totalItemsCount = layoutInfo.totalItemsCount
                lastVisibleItemIndex >= totalItemsCount - 5
            }
        }
    }

    LaunchedEffect(loadMore) {
        snapshotFlow { loadMore.value }
            .distinctUntilChanged()
            .collect { shouldLoadMore ->
                if (shouldLoadMore) {
                    viewModel.loadMore()
                }
            }
    }

    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(DEFAULT_PADDING)
    ) {
        items(articles) { article ->
            NewsItemCard(
                title = article.title,
                description = article.description,
                imageUrl = article.urlToImage,
                publishedAt = article.publishedAt,
                sourceName = article.sourceName,
            ) {
                openInBrowser(context, article.url)
            }
            Spacer(modifier = Modifier.height(DEFAULT_PADDING))
        }

        if (viewModel.isLoadingMore.value) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = DEFAULT_PADDING),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}