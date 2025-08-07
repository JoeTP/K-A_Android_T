package com.example.khatibalamytask.feature_news_list

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.khatibalamytask.core.ui.components.EmptyListStateUi
import com.example.khatibalamytask.core.ui.components.ErrorStateUi
import com.example.khatibalamytask.core.ui.components.LoadingStateUi
import com.example.khatibalamytask.core.ui.components.NewsItemCard
import com.example.khatibalamytask.core.utils.constants.AppValues.Companion.DEFAULT_PADDING
import com.example.khatibalamytask.core.utils.functions.openInBrowser
import com.example.khatibalamytask.domain.model.NewsArticle

@Composable
fun NewsListScreenUi(modifier: Modifier = Modifier, viewModel: NewsListViewModel = hiltViewModel()) {

    val uiState by viewModel.newsUiState.collectAsStateWithLifecycle()

    when (uiState) {
        is NewsListUiState.Loading -> LoadingStateUi(modifier)
        is NewsListUiState.Error -> {
            val error = uiState as NewsListUiState.Error
            ErrorStateUi(modifier = modifier, msg = error.message)
        }

        is NewsListUiState.Success -> {
            val data = (uiState as NewsListUiState.Success).newsList
            if (data.isEmpty()) {
                EmptyListStateUi(modifier)
            }else{
                HeadlinesSuccessState(modifier, articles = data)
            }
        }
    }
}

@Composable
fun HeadlinesSuccessState(modifier: Modifier = Modifier, articles: List<NewsArticle>) {

    val context = LocalContext.current

    LazyColumn(modifier.fillMaxSize(), contentPadding = PaddingValues(DEFAULT_PADDING), ) {
        items(articles) {
            NewsItemCard(
                title = it.title,
                description = it.description,
                imageUrl = it.urlToImage,
                publishedAt = it.publishedAt,
                sourceName = it.sourceName,
            ) {
                openInBrowser(context, it.url)
            }
            Spacer(modifier = Modifier.height(DEFAULT_PADDING))
        }
    }
}